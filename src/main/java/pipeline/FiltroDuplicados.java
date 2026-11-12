package pipeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.LineaPedido;

public class FiltroDuplicados implements Filtro {

    @Override
    public List<LineaPedido> aplicar(List<LineaPedido> lineas) {
        Map<String, Integer> primeraOcurrencia = new HashMap<>();
        List<LineaPedido> resultado = new ArrayList<>();
        for (LineaPedido linea : lineas) {
            primeraOcurrencia.putIfAbsent(linea.idPedido, linea.ocurrenciaPedido);
            if (primeraOcurrencia.get(linea.idPedido) == linea.ocurrenciaPedido) {
                resultado.add(linea);
            }
        }
        return resultado;
    }
}
