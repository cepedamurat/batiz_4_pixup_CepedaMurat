package org.cepdoc.pixup.repository.jdbc;

import org.cepdoc.pixup.model.Cancion;
import java.util.List;

public interface CancionJdbc {
    List<Cancion> findAll();
    Cancion findById(int id);
    Cancion findByTitulo(String titulo);
    int insert(Cancion cancion);
    boolean delete(int id);
}