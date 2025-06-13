package org.cepdoc.pixup.gui.consola;

import org.cepdoc.pixup.gui.LecturaAccion;
import org.cepdoc.pixup.negocio.Ejecutable;


public class DiscoCatalogo extends LecturaAccion {

    public static DiscoCatalogo discoCatalogo;

    private DiscoCatalogo() {
    }

    public static DiscoCatalogo getInstance() {
        if (discoCatalogo == null) {
            discoCatalogo = new DiscoCatalogo();
        }
        return discoCatalogo;
    }

    @Override
    public void despliegaMenu() {
        System.out.println("Seleccione una opción para discos:");
        System.out.println("1.- Agregar disco");
        System.out.println("2.- Modificar disco");
        System.out.println("3.- Eliminar disco");
        System.out.println("4.- Lista discos");
        System.out.println("5.- Salir");
    }

    @Override
    public int valorMinMenu() {
        return 1;
    }

    @Override
    public int valorMaxMenu() {
        return 5;
    }

    @Override
    public void procesaOpcion() {
        Ejecutable ejecutable = null;
        switch (opcion) {
            case 1:
                // Lógica para agregar discos                
                ejecutable = AgregarDisco.getInstance();
                break;
            case 2:
                // Lógica para modificar disco
                ejecutable = ModificarDisco.getInstance();
                break;
            case 3:
                // Lógica para eliminar disco
                ejecutable = EliminarDisco.getInstance();
                break;
            case 4:
                // Lógica para lista discos
                ejecutable = ListarDiscos.getInstance();
                break;          
        }
        ejecutable.setFlag(true);
        ejecutable.run();
    }
}