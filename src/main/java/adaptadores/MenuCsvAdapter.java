/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adaptadores;

import modelo.Plato;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC-PR
 */
public class MenuCsvAdapter implements FuenteDatos<Plato> {

    private Path ruta;

    public MenuCsvAdapter(Path ruta) {
        this.ruta = ruta;
    }

    @Override
    public List<Plato> leer() {
        List<Plato> lista = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(ruta);
            for (int i = 1; i < lineas.size(); i++) {
                String linea = lineas.get(i);
                if (linea.trim().isEmpty()) {
                    continue;
                }
                String[] partes = linea.split(",");
                int idPlato = Integer.parseInt(partes[0].trim());
                String nombre = partes[1].trim();
                String categoria = partes[2].trim();
                double precio = Double.parseDouble(partes[3].trim().replace(",", "."));
                String estado = partes[4].trim();

                lista.add(new Plato(idPlato, nombre, categoria, precio, estado));
            }
        } catch (Exception e) {
            throw new IllegalStateException("Error leyendo archivo: " + ruta, e);
        }
        return lista;
    }
}
