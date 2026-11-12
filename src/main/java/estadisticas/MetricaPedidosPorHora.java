package estadisticas;

import java.util.*;
import java.util.stream.Collectors;
import modelo.LineaPedido;

public class MetricaPedidosPorHora implements Metrica {

    public String nombre() {
        return "Pedidos por hora";
    }

    public String calcular(List<LineaPedido> lineas) {
        Map<String, Set<String>> pedidos = new HashMap<>();
        for (LineaPedido l : lineas) {
            if (l.fechaPagado != null && l.fechaPagado.length() >= 13) {
                String hora = l.fechaPagado.substring(11, 13) + ":00";
                pedidos.computeIfAbsent(hora, k -> new HashSet<>()).add(l.idPedido);
            }
        }
        return pedidos.entrySet().stream().sorted(Comparator.<Map.Entry<String, Set<String>>>comparingInt(e -> e.getValue().size()).reversed().thenComparing(Map.Entry::getKey))
                .limit(3).map(e -> e.getKey() + " (" + e.getValue().size() + " pedidos)").collect(Collectors.joining(", "));
    }
}
