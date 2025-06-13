package org.cepdoc.pixup.repository.jdbc.impl;

import org.cepdoc.pixup.model.Usuario;
import org.cepdoc.pixup.repository.jdbc.Conexion;
import org.cepdoc.pixup.repository.jdbc.UsuarioJdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioJdbcImpl extends Conexion<Usuario> implements UsuarioJdbc {
    private static UsuarioJdbc usuarioJdbc;

    private UsuarioJdbcImpl() {}

    public static UsuarioJdbc getInstance() {
        if (usuarioJdbc == null) {
            usuarioJdbc = new UsuarioJdbcImpl();
        }
        return usuarioJdbc;
    }

    @Override
    public List<Usuario> findAll() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuario";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(resultSet.getInt("id_usuario"));
                usuario.setNombreUsuario(resultSet.getString("nombre_usuario"));
                usuario.setApellidoPaterno(resultSet.getString("apellido_paterno"));
                usuario.setApellidoMaterno(resultSet.getString("apellido_materno"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setPassword(resultSet.getString("password"));
                usuario.setRfc(resultSet.getString("rfc"));
                usuarios.add(usuario);
            }
            resultSet.close();
            statement.close();
            closeConnection();
            return usuarios;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Usuario findById(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario usuario = null;
        String query = "SELECT * FROM usuario WHERE id_usuario = ?";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setApellidoPaterno(rs.getString("apellido_paterno"));
                usuario.setApellidoMaterno(rs.getString("apellido_materno"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
                usuario.setRfc(rs.getString("rfc"));
            }
            rs.close();
            ps.close();
            closeConnection();
            return usuario;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Usuario findByName(String nombre) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario usuario = null;
        String query = "SELECT * FROM usuario WHERE nombre_usuario = ?";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            ps = connection.prepareStatement(query);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setApellidoPaterno(rs.getString("apellido_paterno"));
                usuario.setApellidoMaterno(rs.getString("apellido_materno"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
                usuario.setRfc(rs.getString("rfc"));
            }
            rs.close();
            ps.close();
            closeConnection();
            return usuario;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(Usuario usuario) {
        Statement statement = null;
        String sql = "INSERT INTO usuario (nombre_usuario, apellido_paterno, apellido_materno, email, password, rfc) VALUES ('"
                + usuario.getNombreUsuario() + "', '"
                + usuario.getApellidoPaterno() + "', '"
                + usuario.getApellidoMaterno() + "', '"
                + usuario.getEmail() + "', '"
                + usuario.getPassword() + "', '"
                + usuario.getRfc() + "')";
        try {
            if (openConnection()) {
                System.out.println("Error en conexion");
                return 0;
            }
            statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    generatedKeys.close();
                    closeConnection();
                    return generatedId;
                }
            }
            statement.close();
            closeConnection();
            return 0;
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM usuario WHERE id_usuario = ?";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return false;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            ps.close();
            closeConnection();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}