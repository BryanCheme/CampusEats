package estadisticas;

import java.util.*;
import java.math.*;
import modelo.LineaPedido;

public class MetricaTicketPromedio implements Metrica {

    public String nombre() {
        return "Ticket promedio";
    }

    public String calcular(List<LineaPedido> lineas) {
        Map<String, BigDecimal> pedidos = new HashMap<>();
        for (LineaPedido l : lineas) {
            pedidos.putIfAbsent(l.idPedido, l.totalPedido);
        }
        if (pedidos.isEmpty()) {
            return "Sin pedidos";
        }
        BigDecimal suma = pedidos.values().stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        return suma.divide(BigDecimal.valueOf(pedidos.size()), 2, RoundingMode.HALF_UP).toPlainString() + " dolares por pedido";
    }
}
