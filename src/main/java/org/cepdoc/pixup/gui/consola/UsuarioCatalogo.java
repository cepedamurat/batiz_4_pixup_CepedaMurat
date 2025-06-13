package org.cepdoc.pixup.gui.consola;

import org.cepdoc.pixup.gui.LecturaAccion;
import org.cepdoc.pixup.negocio.Ejecutable;

public class UsuarioCatalogo extends LecturaAccion
{

    public static UsuarioCatalogo usuarioCatalogo;
    
    private UsuarioCatalogo()
    {
    }

    public static UsuarioCatalogo getInstance( )
    {
        if(usuarioCatalogo==null)
        {
            usuarioCatalogo = new UsuarioCatalogo();
        }
        return usuarioCatalogo;
    }

    @Override
    public void despliegaMenu()
    {
        System.out.println("Seleccione una opción para Usuarios:");
        System.out.println("1.- Agregar Usuario");
        System.out.println("2.- Modificar Usuario");
        System.out.println("3.- Eliminar Usuario");
        System.out.println("4.- Listar Usuarios");
        System.out.println("5.- Salir");
    }

    @Override
    public int valorMinMenu()
    {
        return 1;
    }

    @Override
    public int valorMaxMenu()
    {
        return 5;
    }

    @Override
    public void procesaOpcion()
    {
        Ejecutable ejecutable = null;
        switch (opcion)
        {
            case 1:
                // Lógica para agregar usuario
                ejecutable = AgregarUsuario.getInstance();
                break;
            case 2:
                // Lógica para modificar usuario
                ejecutable = ModificarUsuario.getInstance();
                break;
            case 3:
                // Lógica para eliminar usuario
                ejecutable = EliminarUsuario.getInstance();
                break;
            case 4:
                // Lógica para listar usuarios
                ejecutable = ListarUsuarios.getInstance();
                break;
        }
        ejecutable.setFlag(true);
        ejecutable.run();
    }
}