package org.cepdoc.pixup.repository.jdbc;

import org.cepdoc.pixup.model.GeneroMusical;
import java.util.List;

public interface GeneroMusicalJdbc {
    List<GeneroMusical> findAll();
    GeneroMusical findById(int id);
    GeneroMusical findByName(String nombre);
    int insert(GeneroMusical generoMusical);
    boolean delete(int id);
}