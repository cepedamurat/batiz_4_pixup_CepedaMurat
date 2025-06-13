package org.cepdoc.pixup.gui.consola;

import org.cepdoc.pixup.negocio.Ejecutable;

public class ModificarUsuario implements Ejecutable {
    private static ModificarUsuario instance;
    private boolean flag;

    private ModificarUsuario() {}

    public static ModificarUsuario getInstance() {
        if (instance == null) {
            instance = new ModificarUsuario();
        }
        return instance;
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        System.out.println("Modificar usuario...");
        // Lógica para modificar usuario aquí
    }
}