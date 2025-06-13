package org.cepdoc.pixup.repository.jdbc.impl;

import org.cepdoc.pixup.model.Cancion;
import org.cepdoc.pixup.repository.jdbc.CancionJdbc;
import org.cepdoc.pixup.repository.jdbc.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CancionJdbcImpl extends Conexion<Cancion> implements CancionJdbc {
    private static CancionJdbc instance;

    private CancionJdbcImpl() {}

    public static CancionJdbc getInstance() {
        if (instance == null) {
            instance = new CancionJdbcImpl();
        }
        return instance;
    }

    @Override
    public List<Cancion> findAll() {
        List<Cancion> canciones = new ArrayList<>();
        String query = "SELECT * FROM cancion";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Cancion cancion = new Cancion();
                cancion.setIdCancion(rs.getInt("id_cancion"));
                cancion.setTitulo(rs.getString("titulo"));
                cancion.setDuracion(rs.getInt("duracion"));
                cancion.setIdDisco(rs.getInt("id_disco"));
                canciones.add(cancion);
            }
            rs.close();
            st.close();
            closeConnection();
            return canciones;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Cancion findById(int id) {
        Cancion cancion = null;
        String query = "SELECT * FROM cancion WHERE id_cancion = ?";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cancion = new Cancion();
                cancion.setIdCancion(rs.getInt("id_cancion"));
                cancion.setTitulo(rs.getString("titulo"));
                cancion.setDuracion(rs.getInt("duracion"));
                cancion.setIdDisco(rs.getInt("id_disco"));
            }
            rs.close();
            ps.close();
            closeConnection();
            return cancion;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Cancion findByTitulo(String titulo) {
        Cancion cancion = null;
        String query = "SELECT * FROM cancion WHERE titulo = '?'";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,  titulo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cancion.setIdCancion(rs.getInt("id_cancion"));
                cancion.setTitulo(rs.getString("titulo"));
                cancion.setDuracion(rs.getInt("duracion"));
                cancion.setIdDisco(rs.getInt("id_disco"));
            }
            rs.close();
            ps.close();
            closeConnection();
            return cancion;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(Cancion cancion) {
        int idGenerado = 0;
        String query = "INSERT INTO cancion (titulo, duracion, id_disco) VALUES (?, ?, ?)";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return 0;
            }
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cancion.getTitulo());
            ps.setInt(2, cancion.getDuracion());
            ps.setInt(3, cancion.getIdDisco());
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
        String query = "DELETE FROM cancion WHERE id_cancion = ?";
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