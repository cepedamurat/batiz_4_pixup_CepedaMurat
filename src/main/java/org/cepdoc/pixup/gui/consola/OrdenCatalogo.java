package org.cepdoc.pixup.gui.consola;

import org.cepdoc.pixup.gui.LecturaAccion;
import org.cepdoc.pixup.negocio.Ejecutable;


import java.util.Scanner;

public class OrdenCatalogo extends LecturaAccion {
    private static OrdenCatalogo ordenCatalogo;
    private OrdenCatalogo() {
    }
    public static OrdenCatalogo getInstance() {
        if (ordenCatalogo == null) {
            ordenCatalogo = new OrdenCatalogo();
        }
        return ordenCatalogo;
    }

    @Override
    public void despliegaMenu() {
        System.out.println("Seleccione una opción para órdenes:");
        System.out.println("1.- Agregar órdenes");
        System.out.println("2.- Listar ordenes");
        System.out.println("3.- Eliminar ordenes");
        System.out.println("4.- Salir");
    }

    @Override
    public int valorMinMenu() {
        return 1;
    }

    @Override
    public int valorMaxMenu() {
        return 4;
    }

    @Override
    public void procesaOpcion() {
        Ejecutable ejecutable = null;
        System.out.println("Opcion: " + opcion);
        switch (opcion) {
            case 1:
                // agregar ordenes
                ejecutable = AgregarOrdenes.getInstance();
                break;
            case 2:
                // listar ordenes
                ejecutable = ListarOrdenes.getInstance();
                break;
            case 3:
                //eliminar ordenes
                ejecutable = EliminarOrdenes.getInstance();
                break;
            
            
        }

        ejecutable.setFlag(flag);
        ejecutable.run();
    }
}