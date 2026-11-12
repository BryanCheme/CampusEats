/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores;
import modelo.Pago;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author PC-PR
 */



public class PasarelaCsvAdapter implements FuenteDatos<Pago> {

    private Path ruta;

    public PasarelaCsvAdapter(Path ruta) {
        this.ruta = ruta;
    }

    @Override
    public List<Pago> leer() {
        List<Pago> lista = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(ruta);
            for (int i = 1; i < lineas.size(); i++) {
                String linea = lineas.get(i);
                if (linea.trim().isEmpty()) {
                    continue;
                }

                // Usamos -1 para conservar columnas vacias al final de la linea
                String[] partes = linea.split(";", -1);
                if (partes.length >= 6) {
                    String ref = partes[0].trim();
                    double monto = Double.parseDouble(partes[1].trim().replace(",", "."));
                    String estadoTx = partes[2].trim();
                    String codAutorizacion = partes[3].trim();
                    String motivoRechazo = partes[4].trim();
                    String fechaHora = partes[5].trim();

                    lista.add(new Pago(ref, monto, estadoTx, codAutorizacion, motivoRechazo, fechaHora));
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("Error leyendo archivo: " + ruta, e);
        }
        return lista;
    }
} 
