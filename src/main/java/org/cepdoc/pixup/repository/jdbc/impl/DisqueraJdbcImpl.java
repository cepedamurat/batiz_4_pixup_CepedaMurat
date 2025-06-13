package org.cepdoc.pixup.repository.jdbc.impl;

import org.cepdoc.pixup.model.Disquera;
import org.cepdoc.pixup.repository.jdbc.DisqueraJdbc;
import org.cepdoc.pixup.repository.jdbc.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisqueraJdbcImpl extends Conexion<Disquera> implements DisqueraJdbc {
    private static DisqueraJdbc instance;

    private DisqueraJdbcImpl() {}

    public static DisqueraJdbc getInstance() {
        if (instance == null) {
            instance = new DisqueraJdbcImpl();
        }
        return instance;
    }

    @Override
    public List<Disquera> findAll() {
        List<Disquera> disqueras = new ArrayList<>();
        String query = "SELECT id_disquera, nombre FROM disquera";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Disquera disquera = new Disquera();
                disquera.setId(rs.getInt("id_disquera"));
                disquera.setNombre(rs.getString("nombre"));
                disqueras.add(disquera);
            }
            rs.close();
            st.close();
            closeConnection();
            return disqueras;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Disquera findById(int id) {
        Disquera disquera = null;
        String query = "SELECT id_disquera, nombre FROM disquera WHERE id_disquera = ?";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                disquera = new Disquera();
                disquera.setId(rs.getInt("id_disquera"));
                disquera.setNombre(rs.getString("nombre"));
            }
            rs.close();
            ps.close();
            closeConnection();
            return disquera;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Disquera findByName(String nombre) {
        Disquera disquera = null;
        String query = "SELECT id_disquera, nombre FROM disquera WHERE nombre = ?";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                disquera = new Disquera();
                disquera.setId(rs.getInt("id_disquera"));
                disquera.setNombre(rs.getString("nombre"));
            }
            rs.close();
            ps.close();
            closeConnection();
            return disquera;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(Disquera disquera) {
        int idGenerado = 0;
        String query = "INSERT INTO disquera (nombre) VALUES (?)";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return 0;
            }
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, disquera.getNombre());
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
        String query = "DELETE FROM disquera WHERE id_disquera = ?";
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