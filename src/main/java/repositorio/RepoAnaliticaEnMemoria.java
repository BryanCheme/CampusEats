package repositorio;

import java.util.ArrayList;
import java.util.List;
import modelo.LineaPedido;
import modelo.Pago;

public class RepoAnaliticaEnMemoria implements RepoAnalitica {

    private List<LineaPedido> lineasAlmacenadas = new ArrayList<>();
    private List<Pago> pagosAlmacenados = new ArrayList<>();

    @Override
    public void guardarLineas(List<LineaPedido> lineas) {
        lineasAlmacenadas = new ArrayList<>(lineas);
    }

    @Override
    public void guardarPagos(List<Pago> pagos) {
        pagosAlmacenados = new ArrayList<>(pagos);
    }

    @Override
    public List<LineaPedido> lineasEntre(String desde, String hasta) {
        return lineasAlmacenadas.stream()
                .filter(l -> l.fechaHora != null && l.fechaHora.length() >= 10)
                .filter(l -> l.fechaHora.substring(0, 10).compareTo(desde) >= 0)
                .filter(l -> l.fechaHora.substring(0, 10).compareTo(hasta) <= 0)
                .toList();
    }

    @Override
    public List<Pago> pagosEntre(String desde, String hasta) {
        return pagosAlmacenados.stream()
                .filter(p -> fechaIsoPago(p.fechaHora).compareTo(desde) >= 0)
                .filter(p -> fechaIsoPago(p.fechaHora).compareTo(hasta) <= 0)
                .toList();
    }

    private String fechaIsoPago(String fecha) {
        if (fecha == null || fecha.length() < 10) {
            return "";
        }
        String[] partes = fecha.substring(0, 10).split("/");
        if (partes.length == 3) {
            return partes[2] + "-" + partes[1] + "-" + partes[0];
        }
        return fecha.substring(0, 10);
    }
}
