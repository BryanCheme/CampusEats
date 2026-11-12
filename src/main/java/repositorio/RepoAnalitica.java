/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorio;

import modelo.LineaPedido;
import modelo.Pago;
import java.util.List;

/**
 *
 * @author PC-PR
 */

public interface RepoAnalitica {

    void guardarLineas(List<LineaPedido> lineas);

    void guardarPagos(List<Pago> pagos);

    List<LineaPedido> lineasEntre(String desde, String hasta);

    List<Pago> pagosEntre(String desde, String hasta);
}
