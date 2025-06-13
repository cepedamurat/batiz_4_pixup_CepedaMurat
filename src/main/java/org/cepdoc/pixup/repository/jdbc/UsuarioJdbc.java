package org.cepdoc.pixup.repository.jdbc;

import org.cepdoc.pixup.model.Usuario;
import java.util.List;

public interface UsuarioJdbc {
    List<Usuario> findAll();
    Usuario findById(int id);
    Usuario findByName(String nombre);
    int insert(Usuario usuario);
    boolean delete(int id);
}