package org.cepdoc.pixup.repository.jdbc;

import org.cepdoc.pixup.model.Colonia;

import java.util.List;

public interface ColoniaJdbc {
    List<Colonia> findAll( );

    Colonia findById(int id);
    Colonia findByName(String nombre);
    int insert(Colonia colonia);
    boolean delete(int id);
}
