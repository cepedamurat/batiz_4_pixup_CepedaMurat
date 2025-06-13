package org.cepdoc.pixup.repository.jdbc;

import org.cepdoc.pixup.model.Disquera;
import java.util.List;

public interface DisqueraJdbc {
    List<Disquera> findAll();
    Disquera findById(int id);
    Disquera findByName(String nombre);
    int insert(Disquera disquera);
    boolean delete(int id);
}