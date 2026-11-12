package modelo;

import java.math.BigDecimal;

public class LineaPedido {

    public final String idPedido;
    public final int ocurrenciaPedido;
    public final String platoNombre;
    public final int cantidad;
    public final String fechaHora;
    public final boolean pagado;
    public final String fechaPagado;
    public final BigDecimal precioUnitario;
    public final BigDecimal totalPedido;

    public LineaPedido(String idPedido, int ocurrenciaPedido, String platoNombre,
            int cantidad, String fechaHora, boolean pagado, String fechaPagado,
            BigDecimal precioUnitario, BigDecimal totalPedido) {
        this.idPedido = idPedido;
        this.ocurrenciaPedido = ocurrenciaPedido;
        this.platoNombre = platoNombre;
        this.cantidad = cantidad;
        this.fechaHora = fechaHora;
        this.pagado = pagado;
        this.fechaPagado = fechaPagado;
        this.precioUnitario = precioUnitario;
        this.totalPedido = totalPedido;
    }
}
