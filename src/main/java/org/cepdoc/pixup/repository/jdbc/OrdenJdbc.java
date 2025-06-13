package org.cepdoc.pixup.repository.jdbc;

import org.cepdoc.pixup.model.Orden;
import java.util.List;

public interface OrdenJdbc {
    List<Orden> findAll();
    Orden findById(int id);
    int insert(Orden orden);
    boolean delete(int id);
    
}