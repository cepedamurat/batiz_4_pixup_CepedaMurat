package org.cepdoc.pixup.repository.jdbc;

import org.cepdoc.pixup.model.TipoDomicilio;

import java.util.List;

public interface TipoDomicilioJdbc {
    List<TipoDomicilio> findAll( );

}
