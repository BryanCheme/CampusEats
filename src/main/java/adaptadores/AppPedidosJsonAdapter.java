package adaptadores;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import modelo.LineaPedido;

public class AppPedidosJsonAdapter implements FuenteDatos<LineaPedido> {
    private final Path ruta;

    public AppPedidosJsonAdapter(Path ruta) { this.ruta = ruta; }

    @Override
    public List<LineaPedido> leer() {
        List<LineaPedido> resultado = new ArrayList<>();
        try {
            JsonNode pedidos = new ObjectMapper().readTree(ruta.toFile());
            if (!pedidos.isArray()) throw new IllegalArgumentException("El JSON debe contener una lista de pedidos");
            int ocurrencia = 0;
            for (JsonNode pedido : pedidos) {
                ocurrencia++;
                String id = pedido.path("numeroPedido").asText();
                String fecha = pedido.path("registradoEn").asText();
                BigDecimal total = new BigDecimal(pedido.path("total").asText());
                boolean pagado = false;
                String fechaPagado = "";
                for (JsonNode evento : pedido.path("historialEstados")) {
                    if ("PAGADO".equalsIgnoreCase(evento.path("estado").asText())) {
                        pagado = true;
                        fechaPagado = evento.path("fechaHora").asText();
                        break;
                    }
                }
                for (JsonNode linea : pedido.path("lineas")) {
                    resultado.add(new LineaPedido(id, ocurrencia,
                        linea.path("platoId").asText(), linea.path("cantidad").asInt(),
                        fecha, pagado, fechaPagado,
                        new BigDecimal(linea.path("precioUnitario").asText()), total));
                }
            }
        } catch (IOException | RuntimeException e) {
            throw new IllegalStateException("No se pudieron leer los pedidos: " + ruta, e);
        }
        return resultado;
    }
}
