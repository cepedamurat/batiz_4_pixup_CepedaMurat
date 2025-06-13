package org.cepdoc.pixup.repository.jdbc;

import org.cepdoc.pixup.model.Disco;
import java.util.List;

public interface DiscoJdbc {
    List<Disco> findAll();
    Disco findById(int id);
    Disco findByName(String nombre);
    int insert(Disco disco);
    boolean delete(int id);
}