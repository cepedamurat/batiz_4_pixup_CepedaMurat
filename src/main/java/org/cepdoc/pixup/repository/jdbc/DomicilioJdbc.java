package org.cepdoc.pixup.repository.jdbc;

import org.cepdoc.pixup.model.Domicilio;

import java.util.List;

public interface DomicilioJdbc {
    List<Domicilio> findAll( );

    Domicilio findById(int id);
    int insert(Domicilio domicilio);
    boolean delete(int id);
}
