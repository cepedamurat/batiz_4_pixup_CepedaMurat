package org.cepdoc.pixup.gui.consola;

import org.cepdoc.pixup.model.Usuario;
import org.cepdoc.pixup.negocio.Ejecutable;
import org.cepdoc.pixup.repository.jdbc.impl.UsuarioJdbcImpl;
import org.cepdoc.pixup.util.ReadUtil;

public class EliminarUsuario implements Ejecutable {
    private static EliminarUsuario instance;
    private boolean flag;

    private EliminarUsuario() {}

    public static EliminarUsuario getInstance() {
        if (instance == null) {
            instance = new EliminarUsuario();
        }
        return instance;
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        
        System.out.println("Escribe el nombre del usuario que deseas eliminar:");
        String nombre = ReadUtil.read();
        
        Usuario eliminar = UsuarioJdbcImpl.getInstance().findByName(nombre);
        System.out.println("Seguro que deseas eliminar al usuario: " + eliminar.getNombreUsuario() + " " + eliminar.getApellidoPaterno() + "? (S/N)");
        String confirmacion = ReadUtil.read();
        if (confirmacion.equalsIgnoreCase("S")) {
            UsuarioJdbcImpl.getInstance().delete(eliminar.getIdUsuario());
            System.out.println("Usuario eliminado correctamente.");
        } else {
            System.out.println("Eliminación cancelada.");
        }
        
    }
}