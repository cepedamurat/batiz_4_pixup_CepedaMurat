package org.cepdoc.pixup.gui.consola;

import org.cepdoc.pixup.negocio.Ejecutable;
import org.cepdoc.pixup.util.ReadUtil;
import org.cepdoc.pixup.model.Artista;
import org.cepdoc.pixup.model.Disco;
import org.cepdoc.pixup.model.Disquera;
import org.cepdoc.pixup.model.GeneroMusical;
import org.cepdoc.pixup.repository.jdbc.impl.ArtistaJdbcImpl;
import org.cepdoc.pixup.repository.jdbc.impl.DiscoJdbcImpl;
import org.cepdoc.pixup.repository.jdbc.impl.DisqueraJdbcImpl;
import org.cepdoc.pixup.repository.jdbc.impl.GeneroMusicalJdbcImpl;

public class AgregarDisco implements Ejecutable {
    private static AgregarDisco instance;
    private boolean flag;

    private AgregarDisco() {}

    public static AgregarDisco getInstance() {
        if (instance == null) {
            instance = new AgregarDisco();
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
            // Solicitar artista
            System.out.println("Ingrese el nombre del artista:");
            String nombre_artista = ReadUtil.read();
            int id_artista = 0;

            // Valida si existe en la base de datos y si no existe, agrégalo
            Artista artista = ArtistaJdbcImpl.getInstance().findByName(nombre_artista);
            if (artista != null) {
                id_artista = artista.getId();
            } else {
                artista = new org.cepdoc.pixup.model.Artista();
                artista.setNombre(nombre_artista);
                id_artista = ArtistaJdbcImpl.getInstance().insert(artista);
            }

            // Solicitar disquera
            System.out.println("Ingrese el nombre de la disquera:");
            String nombre_disquera = ReadUtil.read();
            int id_disquera = 0;

            // Valida si existe en la base de datos y si no existe, agrégalo
            Disquera disquera = DisqueraJdbcImpl.getInstance().findByName(nombre_disquera);
            if (disquera != null) {
                id_disquera = disquera.getId();
            } else {
                disquera = new Disquera();
                disquera.setNombre(nombre_disquera);
                id_disquera = DisqueraJdbcImpl.getInstance().insert(disquera);
            }

            // Solicitar generomusical
            System.out.println("Ingrese el género musical:");
            String generoMusical = ReadUtil.read();
            int id_genero = 0;

            GeneroMusical genero = GeneroMusicalJdbcImpl.getInstance().findByName(generoMusical);
            if (genero == null) {
                genero = new GeneroMusical();
                genero.setNombre(generoMusical);
                id_genero = GeneroMusicalJdbcImpl.getInstance().insert(genero);
            }
            else {
                id_genero = genero.getId();
            }

            // Solicitar datos del disco
            System.out.println("Ingrese el título del disco:");
            String titulo = ReadUtil.read();

            // Solicitar precio
            System.out.println("Ingrese el precio del disco:");
            float precio = Float.parseFloat(ReadUtil.read());

            // Solicitar existencia
            System.out.println("Ingrese las existencia del disco:");
            int existencia = Integer.parseInt(ReadUtil.read());

            // Solicitar descuento
            System.out.println("Ingrese el descuento del disco:");
            float descuento = Float.parseFloat(ReadUtil.read());

            // Solicitar fecha de lanzamiento
            System.out.println("Ingrese la fecha de lanzamiento del disco (YYYY-MM-DD):");
            String fechaLanzamiento = ReadUtil.read();

            // Solicitar imagen
            System.out.println("Ingrese la liga de la imagen del disco:");
            String imagen = ReadUtil.read();

            // Crear el disco
            Disco disco = new Disco();
            disco.setIdArtista(id_artista);
            disco.setIdDisquera(id_disquera);
            disco.setIdGeneroMusical(id_genero);
            disco.setTitulo(titulo);
            disco.setPrecio(precio);
            disco.setExistencia(existencia);
            disco.setDescuento(descuento);
            try {
                java.sql.Date sqlFechaLanzamiento = java.sql.Date.valueOf(fechaLanzamiento);
                disco.setFechaLanzamiento(sqlFechaLanzamiento);
            } catch (IllegalArgumentException e) {
                System.out.println("Formato de fecha inválido. Use el formato YYYY-MM-DD.");
                disco.setFechaLanzamiento(null);
            }
            disco.setImagen(imagen);
            // Insertar el disco en la base de datos

            int idDisco = DiscoJdbcImpl.getInstance().insert(disco);
            
        } catch (Exception e) {
            System.out.println("Error al agregar disco: " + e.getMessage());
        }
    }
}