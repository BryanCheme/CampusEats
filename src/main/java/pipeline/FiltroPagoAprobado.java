package pipeline;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.LineaPedido;
import modelo.Pago;

public class FiltroPagoAprobado implements Filtro {

    private final Map<String, List<Pago>> pagosPorPedido = new HashMap<>();

    public FiltroPagoAprobado(List<Pago> pagos) {
        for (Pago pago : pagos) {
            pagosPorPedido.computeIfAbsent(pago.referencia, clave -> new ArrayList<>()).add(pago);
        }
    }

    @Override
    public List<LineaPedido> aplicar(List<LineaPedido> lineas) {
        List<LineaPedido> resultado = new ArrayList<>();
        for (LineaPedido linea : lineas) {
            boolean valido = false;
            for (Pago pago : pagosPorPedido.getOrDefault(linea.idPedido, List.of())) {
                if ("APROBADA".equalsIgnoreCase(pago.estado)
                        && BigDecimal.valueOf(pago.monto).compareTo(linea.totalPedido) == 0) {
                    valido = true;
                    break;
                }
            }
            if (valido) {
                resultado.add(linea);
            }
        }
        return resultado;
    }
}
