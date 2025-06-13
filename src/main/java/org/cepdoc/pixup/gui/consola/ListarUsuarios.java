package org.cepdoc.pixup.gui.consola;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.cepdoc.pixup.gui.LecturaAccion;
import org.cepdoc.pixup.model.Usuario;
import org.cepdoc.pixup.negocio.Ejecutable;
import org.cepdoc.pixup.repository.jdbc.impl.UsuarioJdbcImpl;

public class ListarUsuarios implements Ejecutable {
    private static ListarUsuarios instance;
    private boolean flag;

    private ListarUsuarios() {}

    public static ListarUsuarios getInstance() {
        if (instance == null) {
            instance = new ListarUsuarios();
        }
        return instance;
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        List<Usuario> usuarios = UsuarioJdbcImpl.getInstance().findAll();
        System.out.println("+--------------------------------------------------------------------+");
        System.out.println("|                           Lista de Usuarios                        |");
        System.out.println("+--------------------------------------------------------------------+");
        System.out.printf("| %-10s | %-25s | %-25s |\n", "ID", "Nombre", "Email");
        System.out.println("+--------------------------------------------------------------------+");

        for (Usuario usuario : usuarios) {
            System.out.printf("| %-10d | %-25s | %-25s |\n", 
                      usuario.getIdUsuario(), 
                      usuario.getNombreUsuario() + " " + usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno(), 
                      usuario.getEmail());
        }

        System.out.println("+--------------------------------------------------------------------+");
    }
}