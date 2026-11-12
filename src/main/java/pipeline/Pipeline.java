/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pipeline;

import modelo.LineaPedido;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC-PR
 */

public class Pipeline {

    private List<Filtro> filtros = new ArrayList<>();

    public void agregarFiltro(Filtro filtro) {
        filtros.add(filtro);
    }

    public List<LineaPedido> ejecutar(List<LineaPedido> lineasIniciales) {
        List<LineaPedido> datosActuales = lineasIniciales;

        for (Filtro filtro : filtros) {
            int tamanoAntes = datosActuales.size();
            datosActuales = filtro.aplicar(datosActuales);
            int descartados = tamanoAntes - datosActuales.size();

            String nombreFiltro = filtro.getClass().getSimpleName();
            System.out.println(nombreFiltro + " descarto: " + descartados + " lineas.");
        }

        return datosActuales;
    }
}
