/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package estadisticas;
import modelo.LineaPedido;
import java.util.List;
/**
 *
 * @author PC-PR
 */


public interface Metrica {
    String nombre();
    String calcular(List<LineaPedido> lineasLimpias);
}