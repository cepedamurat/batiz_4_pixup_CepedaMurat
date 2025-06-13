package org.cepdoc.pixup.repository.jdbc;


import org.cepdoc.pixup.model.Estado;

import java.util.List;

public interface EstadoJdbc
{
        List<Estado> findAll( );

        Estado findById(int id);
        Estado findByName(String nombre);
        int insert(Estado estado);
        boolean update(Estado estado);
        boolean delete(int id);
}

