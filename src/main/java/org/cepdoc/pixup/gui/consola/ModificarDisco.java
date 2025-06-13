package org.cepdoc.pixup.gui.consola;

import org.cepdoc.pixup.gui.LecturaAccion;
import org.cepdoc.pixup.negocio.Ejecutable;

public class ModificarDisco extends LecturaAccion {
    private static ModificarDisco instance;
    private boolean flag;

    private ModificarDisco() {}

    public static ModificarDisco getInstance() {
        if (instance == null) {
            instance = new ModificarDisco();
        }
        return instance;
    }

    @Override
    public void despliegaMenu()
    {
        System.out.println("Seleccione la acción deseada:");
        System.out.println("1.- Agregar Canciones");
        System.out.println("2.- Modificar Datos");
        System.out.println("3.- Salir");
    }

    @Override
    public int valorMinMenu()
    {
        return 1;
    }

    @Override
    public int valorMaxMenu()
    {
        return 3;
    }

    @Override
    public void procesaOpcion()
    {
        Ejecutable ejecutable = null;
        switch (opcion)
        {
            case 1:
                // Lógica para agregar cancion
                ejecutable = AgregarCanciones.getInstance();
                break;
            case 2:
                break;
        }
        ejecutable.setFlag(true);
        ejecutable.run();
    }
}