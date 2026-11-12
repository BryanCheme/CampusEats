/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pipeline;

import modelo.LineaPedido;
import java.util.List;

/**
 *
 * @author PC-PR
 */
public interface Filtro {

    List<LineaPedido> aplicar(List<LineaPedido> lineas);
}
