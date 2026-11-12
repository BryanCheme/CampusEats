package com.mycompany.campuseats;

import java.util.List;
import repositorio.RepoAnaliticaEnMemoria;
import servicio.ServicioAnalitica;
import estadisticas.*;

public class CampusEats {

    public static void main(String[] args) {
        System.out.println("Sistema CampusEats");
        ServicioAnalitica servicio = new ServicioAnalitica(new RepoAnaliticaEnMemoria(),
                List.of(new MetricaPlatosTop(), new MetricaPedidosPorHora(), new MetricaTicketPromedio()));
        servicio.cargar();
        servicio.resumen("2026-10-19", "2026-10-30");
    }
}
