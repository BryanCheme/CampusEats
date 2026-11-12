/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author PC-PR
 */
public class Pago {

    public String referencia;
    public double monto;
    public String estado;
    public String codAutorizacion;
    public String motivoRechazo;
    public String fechaHora;

    public Pago(String referencia, double monto, String estado, String codAutorizacion, String motivoRechazo, String fechaHora) {
        this.referencia = referencia;
        this.monto = monto;
        this.estado = estado;
        this.codAutorizacion = codAutorizacion;
        this.motivoRechazo = motivoRechazo;
        this.fechaHora = fechaHora;
    }
}
