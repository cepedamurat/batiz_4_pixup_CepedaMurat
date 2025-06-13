package org.cepdoc.pixup.gui.consola;

import java.util.List;
import org.cepdoc.pixup.model.Orden;
import org.cepdoc.pixup.negocio.Ejecutable;
import org.cepdoc.pixup.repository.jdbc.impl.OrdenJdbcImpl;

public class ListarOrdenes implements Ejecutable {
    private static ListarOrdenes instance;
    private boolean flag;

    private ListarOrdenes() {}

    public static ListarOrdenes getInstance() {
        if (instance == null) {
            instance = new ListarOrdenes();
        }
        return instance;
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        List<Orden> ordenes = OrdenJdbcImpl.getInstance().findAll();
        System.out.println("+--------------------------------------------------------------------------------------+");
        System.out.println("|                                 Lista de Órdenes                                     |");
        System.out.println("+--------------------------------------------------------------------------------------+");
        System.out.printf("| %-10s | %-15s | %-15s | %-15s | %-17s |\n", "ID", "Costo Total", "Cantidad Total", "Fecha", "ID Usuario");
        System.out.println("+--------------------------------------------------------------------------------------+");

        for (Orden orden : ordenes) {
            System.out.printf("| %-10d | %-15.2f | %-15.2f | %-15s | %-17d |\n",
                    orden.getIdOrden(),
                    orden.getCostoTotal(),
                    orden.getCantidadTotal(),
                    orden.getFecha(),
                    orden.getIdUsuario());
        }

        System.out.println("+--------------------------------------------------------------------------------------+");
    }
}