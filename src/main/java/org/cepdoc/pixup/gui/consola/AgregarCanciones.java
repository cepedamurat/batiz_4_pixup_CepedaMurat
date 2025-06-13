package org.cepdoc.pixup.gui.consola;

import org.cepdoc.pixup.negocio.Ejecutable;
import org.cepdoc.pixup.util.ReadUtil;
import org.cepdoc.pixup.model.Cancion;
import org.cepdoc.pixup.model.Disco;
import org.cepdoc.pixup.repository.jdbc.impl.CancionJdbcImpl;
import org.cepdoc.pixup.repository.jdbc.impl.DiscoJdbcImpl;

public class AgregarCanciones implements Ejecutable {
    private static AgregarCanciones instance;
    private boolean flag;

    private AgregarCanciones() {}

    public static AgregarCanciones getInstance() {
        if (instance == null) {
            instance = new AgregarCanciones();
        }
        return instance;
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            System.out.println("Ingrese el titulo del disco al que desea agregar canciones:");
            String titulo = ReadUtil.read();
            Disco disco = DiscoJdbcImpl.getInstance().findByName(titulo);

            if (disco == null) {
                System.out.println("Disco no encontrado.");
                return;
            }

            boolean agregarMas = true;
            while (agregarMas) {
                System.out.println("Ingrese el título de la canción:");
                String nombreCancion = ReadUtil.read();

                System.out.println("Ingrese la duración de la canción (en segundos):");
                int duracion = ReadUtil.readInt();

                Cancion cancion = new Cancion();
                cancion.setTitulo(nombreCancion);
                cancion.setDuracion(duracion);
                cancion.setIdDisco(disco.getId());

                int idCancion = CancionJdbcImpl.getInstance().insert(cancion);

                if (idCancion > 0) {
                    System.out.println("Canción agregada exitosamente con ID: " + idCancion);
                } else {
                    System.out.println("No se pudo agregar la canción.");
                }

                System.out.println("¿Desea agregar otra canción? (s/n):");
                String respuesta = ReadUtil.read();
                agregarMas = respuesta.equalsIgnoreCase("s");
            }
        } catch (Exception e) {
            System.out.println("Error al agregar canciones: " + e.getMessage());
        }
    }
}