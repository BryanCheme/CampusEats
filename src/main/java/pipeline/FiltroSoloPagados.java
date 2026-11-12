/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pipeline;

import modelo.LineaPedido;

import java.util.List;

/**
 *
 * @author PC-PR
 */

public class FiltroSoloPagados implements Filtro {

    @Override
    public List<LineaPedido> aplicar(List<LineaPedido> lineas) {
        return lineas.stream().filter(l -> l.pagado).toList();
    }
}
