package org.cepdoc.pixup.repository.jdbc;

import org.cepdoc.pixup.model.Municipio;

import java.util.List;

public interface MunicipioJdbc {
    List<Municipio> findAll( );

    Municipio findById(int id);
    Municipio findByName(String nombre);
    int insert(Municipio municipio);
    boolean delete(int id);
}
