package org.cepdoc.pixup.repository.jdbc;

import org.cepdoc.pixup.model.Artista;
import java.util.List;

public interface ArtistaJdbc {
    List<Artista> findAll();
    Artista findById(int id);
    Artista findByName(String nombre);
    int insert(Artista artista);
    boolean delete(int id);
}