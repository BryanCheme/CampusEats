package servicio;

import adaptadores.FuenteDatos;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fabrica.FabricaFuentes;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import modelo.*;
import pipeline.*;
import repositorio.RepoAnalitica;
import estadisticas.Metrica;

public class ServicioAnalitica {

    private final RepoAnalitica repo;
    private final List<Metrica> metricas;

    public ServicioAnalitica(RepoAnalitica repo, List<Metrica> metricas) {
        this.repo = repo;
        this.metricas = List.copyOf(metricas);
    }

    @SuppressWarnings("unchecked")
    public void cargar() {
        try {
            JsonNode fuentes = new ObjectMapper().readTree(Paths.get("config/fuentes.json").toFile()).path("fuentes");
            Map<String, FuenteDatos<?>> adaptadores = new HashMap<>();
            for (JsonNode fuente : fuentes) {
                String tipo = fuente.path("tipo").asText();
                Path ruta = Paths.get(fuente.path("ruta").asText());
                adaptadores.put(tipo, FabricaFuentes.crear(tipo, ruta));
            }
            for (String tipo : List.of("app_json", "pasarela_csv", "menu_csv")) {
                if (!adaptadores.containsKey(tipo)) {
                    throw new IllegalStateException("Falta fuente: " + tipo);
                }
            }
            List<Pago> pagos = ((FuenteDatos<Pago>) adaptadores.get("pasarela_csv")).leer();
            List<LineaPedido> iniciales = ((FuenteDatos<LineaPedido>) adaptadores.get("app_json")).leer();
            List<Plato> menu = ((FuenteDatos<Plato>) adaptadores.get("menu_csv")).leer();
            System.out.println("Lineas iniciales: " + iniciales.size());
            Pipeline pipeline = new Pipeline();
            pipeline.agregarFiltro(new FiltroDuplicados());
            pipeline.agregarFiltro(new FiltroCantidadValida());
            pipeline.agregarFiltro(new FiltroSoloPagados());
            pipeline.agregarFiltro(new FiltroPagoAprobado(pagos));
            List<LineaPedido> limpias = pipeline.ejecutar(iniciales);
            repo.guardarLineas(limpias);
            repo.guardarPagos(pagos);
            Set<String> ids = new HashSet<>();
            for (LineaPedido l : limpias) {
                ids.add(l.idPedido);
            }
            System.out.println("Lineas validas: " + limpias.size());
            System.out.println("Pedidos validos: " + ids.size());
            System.out.println("Platos cargados del menu: " + menu.size());
            for (Metrica m : metricas) {
                if (m instanceof estadisticas.MetricaPlatosTop top) {
                    top.configurarMenu(menu);
                }
            }
        } catch (Exception ex) {
            throw new IllegalStateException("Error al cargar fuentes configuradas", ex);
        }
    }

    public void resumen(String desde, String hasta) {
        List<LineaPedido> lineas = repo.lineasEntre(desde, hasta);
        System.out.println("Resumen del " + desde + " al " + hasta);
        System.out.println("Lineas del periodo: " + lineas.size());
        for (Metrica metrica : metricas) {
            System.out.println(metrica.nombre() + ": " + metrica.calcular(lineas));
        }
    }
}
