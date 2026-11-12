/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adaptadores;
import java.util.List;
/**
 *
 * @author PC-PR
 */

public interface FuenteDatos<T> {
    List<T> leer();
}