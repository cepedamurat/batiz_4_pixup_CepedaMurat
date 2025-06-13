package org.cepdoc.pixup.gui.consola;

import org.cepdoc.pixup.model.Disco;
import org.cepdoc.pixup.negocio.Ejecutable;
import org.cepdoc.pixup.repository.jdbc.impl.DiscoJdbcImpl;
import org.cepdoc.pixup.util.ReadUtil;

public class EliminarDisco implements Ejecutable {
    private static EliminarDisco instance;
    private boolean flag;

    private EliminarDisco() {}

    public static EliminarDisco getInstance() {
        if (instance == null) {
            instance = new EliminarDisco();
        }
        return instance;
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        
        System.out.println("Escribe el titulo del disco que deseas eliminar:");
        String titulo = ReadUtil.read();

        Disco eliminar = DiscoJdbcImpl.getInstance().findByName(titulo);
        if (eliminar == null) {
            System.out.println("Disco no encontrado. Por favor, verifica el título e intenta nuevamente.");
            return;
        }
        System.out.println("Seguro que deseas eliminar el disco: " + eliminar.getTitulo() + "? (S/N)");
        String confirmacion = ReadUtil.read();
        if (confirmacion.equalsIgnoreCase("S")) {
            DiscoJdbcImpl.getInstance().delete(eliminar.getId());
            System.out.println("Disco eliminado correctamente.");
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }
}