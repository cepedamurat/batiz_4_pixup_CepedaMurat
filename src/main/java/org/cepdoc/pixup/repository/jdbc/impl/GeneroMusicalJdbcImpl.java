package org.cepdoc.pixup.repository.jdbc.impl;

import org.cepdoc.pixup.model.GeneroMusical;
import org.cepdoc.pixup.repository.jdbc.GeneroMusicalJdbc;
import org.cepdoc.pixup.repository.jdbc.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneroMusicalJdbcImpl extends Conexion<GeneroMusical> implements GeneroMusicalJdbc {
    private static GeneroMusicalJdbc instance;

    private GeneroMusicalJdbcImpl() {}

    public static GeneroMusicalJdbc getInstance() {
        if (instance == null) {
            instance = new GeneroMusicalJdbcImpl();
        }
        return instance;
    }

    @Override
    public List<GeneroMusical> findAll() {
        List<GeneroMusical> generos = new ArrayList<>();
        String query = "SELECT * FROM genero_musical";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                GeneroMusical genero = new GeneroMusical();
                genero.setId(rs.getInt("id_genero_musical"));
                genero.setNombre(rs.getString("genero_musical"));
                generos.add(genero);
            }
            rs.close();
            st.close();
            closeConnection();
            return generos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GeneroMusical findById(int id) {
        GeneroMusical genero = null;
        String query = "SELECT * FROM genero_musical WHERE id_genero_musical = ?";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                genero = new GeneroMusical();
                genero.setId(rs.getInt("id_genero_musical"));
                genero.setNombre(rs.getString("genero_musical"));
            }
            rs.close();
            ps.close();
            closeConnection();
            return genero;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GeneroMusical findByName(String nombre) {
        GeneroMusical genero = null;
        String query = "SELECT * FROM genero_musical WHERE genero_musical = ?";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                genero = new GeneroMusical();
                genero.setId(rs.getInt("id_genero_musical"));
                genero.setNombre(rs.getString("genero_musical"));
            }
            rs.close();
            ps.close();
            closeConnection();
            return genero;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(GeneroMusical generoMusical) {
        int idGenerado = 0;
        String query = "INSERT INTO genero_musical (genero_musical) VALUES (?)";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return 0;
            }
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, generoMusical.getNombre());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }
            rs.close();
            ps.close();
            closeConnection();
            return idGenerado;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM genero_musical WHERE id_genero_musical = ?";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return false;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            ps.close();
            closeConnection();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}