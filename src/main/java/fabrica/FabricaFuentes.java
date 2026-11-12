package fabrica;

import adaptadores.*;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.Function;

public class FabricaFuentes {

    private static final Map<String, Function<Path, FuenteDatos<?>>> TIPOS = Map.of(
            "app_json", AppPedidosJsonAdapter::new,
            "pasarela_csv", PasarelaCsvAdapter::new,
            "menu_csv", MenuCsvAdapter::new);

    public static FuenteDatos<?> crear(String tipo, Path ruta) {
        Function<Path, FuenteDatos<?>> constructor = TIPOS.get(tipo);
        if (constructor == null) {
            throw new IllegalArgumentException("Tipo de fuente desconocido: " + tipo);
        }
        return constructor.apply(ruta);
    }
}
