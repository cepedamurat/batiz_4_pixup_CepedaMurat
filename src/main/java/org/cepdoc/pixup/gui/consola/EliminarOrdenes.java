package org.cepdoc.pixup.gui.consola;

import org.cepdoc.pixup.model.Orden;
import org.cepdoc.pixup.negocio.Ejecutable;
import org.cepdoc.pixup.repository.jdbc.impl.OrdenJdbcImpl;
import org.cepdoc.pixup.util.ReadUtil;

public class EliminarOrdenes implements Ejecutable {
    private static EliminarOrdenes instance;
    private boolean flag;

    private EliminarOrdenes() {}

    public static EliminarOrdenes getInstance() {
        if (instance == null) {
            instance = new EliminarOrdenes();
        }
        return instance;
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        
        System.out.println("Escribe el ID de la orden que deseas eliminar:");
        int idOrden = Integer.parseInt(ReadUtil.read());

        Orden orden = OrdenJdbcImpl.getInstance().findById(idOrden);
        if (orden == null) {
            System.out.println("Orden no encontrada. Por favor, verifica el ID e intenta nuevamente.");
            return;
        }
        System.out.println("Seguro que deseas eliminar la orden con ID: " + orden.getIdOrden() + "? (S/N)");
        String confirmacion = ReadUtil.read();
        if (confirmacion.equalsIgnoreCase("S")) {
            OrdenJdbcImpl.getInstance().delete(idOrden);
            System.out.println("Orden eliminada correctamente.");
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }
}