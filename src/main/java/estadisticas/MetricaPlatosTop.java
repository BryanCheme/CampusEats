package estadisticas;

import java.util.*;
import java.util.stream.Collectors;
import modelo.*;

public class MetricaPlatosTop implements Metrica {

    private final Map<String, String> nombres = new HashMap<>();

    public void configurarMenu(List<Plato> menu) {
        nombres.clear();
        for (Plato p : menu) {
            nombres.put(String.valueOf(p.idPlato), sinTildes(p.nombre));
        }
    }

    private String sinTildes(String s) {
        return java.text.Normalizer.normalize(s, java.text.Normalizer.Form.NFD).replaceAll("\\p{M}+", "");
    }

    public String nombre() {
        return "Platos mas vendidos";
    }

    public String calcular(List<LineaPedido> lineas) {
        Map<String, Integer> cantidades = new HashMap<>();
        for (LineaPedido l : lineas) {
            cantidades.merge(l.platoNombre, l.cantidad, Integer::sum);
        }
        return cantidades.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed().thenComparing(Map.Entry::getKey))
                .limit(5).map(e -> nombres.getOrDefault(e.getKey(), "Plato " + e.getKey()) + " (" + e.getValue() + " unidades)").collect(Collectors.joining(", "));
    }
}
