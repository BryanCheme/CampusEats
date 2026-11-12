/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author PC-PR
 */
public class Plato {

    public int idPlato;
    public String nombre;
    public String categoria;
    public double precioActual;
    public String estado;

    public Plato(int idPlato, String nombre, String categoria, double precioActual, String estado) {
        this.idPlato = idPlato;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precioActual = precioActual;
        this.estado = estado;
    }
}
