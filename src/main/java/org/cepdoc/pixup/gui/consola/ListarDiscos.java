package org.cepdoc.pixup.gui.consola;

import java.util.List;

import org.cepdoc.pixup.model.Artista;
import org.cepdoc.pixup.model.Disco;
import org.cepdoc.pixup.model.GeneroMusical;
import org.cepdoc.pixup.model.Usuario;
import org.cepdoc.pixup.negocio.Ejecutable;
import org.cepdoc.pixup.repository.jdbc.impl.ArtistaJdbcImpl;
import org.cepdoc.pixup.repository.jdbc.impl.DiscoJdbcImpl;
import org.cepdoc.pixup.repository.jdbc.impl.GeneroMusicalJdbcImpl;
import org.cepdoc.pixup.repository.jdbc.impl.UsuarioJdbcImpl;

public class ListarDiscos implements Ejecutable {
    private static ListarDiscos instance;
    private boolean flag;

    private ListarDiscos() {}

    public static ListarDiscos getInstance() {
        if (instance == null) {
            instance = new ListarDiscos();
        }
        return instance;
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        List<Disco> discos = DiscoJdbcImpl.getInstance().findAll();
        System.out.println("+------------------------------------------------------------------------------------------------+");
        System.out.println("|                                          Lista de Discos                                       |");
        System.out.println("+------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-10s | %-25s | %-25s | %-25s |\n", "ID", "Titulo", "Artista", "Género Musical");
        System.out.println("+------------------------------------------------------------------------------------------------+");

        for (Disco disco : discos) {

            Artista artista = ArtistaJdbcImpl.getInstance().findById(disco.getIdArtista());
            String nombre_artista = "";
            if (artista != null) {
                nombre_artista = artista.getNombre();
            } else {
                nombre_artista = "Desconocido";
            }

            GeneroMusical generoMusical = GeneroMusicalJdbcImpl.getInstance().findById(disco.getIdGeneroMusical());
            String nombre_genero = "";
            if (generoMusical != null) {
                nombre_genero = generoMusical.getNombre();
            } else {
                nombre_genero = "Desconocido";
            }

            System.out.printf("| %-10d | %-25s | %-25s | %-25s |\n", 
                        disco.getId(), 
                        disco.getTitulo(), 
                        nombre_artista,
                        nombre_genero);
        }

        System.out.println("+------------------------------------------------------------------------------------------------+");
    }
}