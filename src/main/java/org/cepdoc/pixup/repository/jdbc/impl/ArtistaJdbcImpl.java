package org.cepdoc.pixup.repository.jdbc.impl;

import org.cepdoc.pixup.model.Artista;
import org.cepdoc.pixup.repository.jdbc.ArtistaJdbc;
import org.cepdoc.pixup.repository.jdbc.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistaJdbcImpl extends Conexion<Artista> implements ArtistaJdbc {
    private static ArtistaJdbc instance;

    private ArtistaJdbcImpl() {}

    public static ArtistaJdbc getInstance() {
        if (instance == null) {
            instance = new ArtistaJdbcImpl();
        }
        return instance;
    }

    @Override
    public List<Artista> findAll() {
        List<Artista> artistas = new ArrayList<>();
        String query = "SELECT id_artista, nombre FROM artista";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Artista artista = new Artista();
                artista.setId(rs.getInt("id_artista"));
                artista.setNombre(rs.getString("nombre"));
                artistas.add(artista);
            }
            rs.close();
            st.close();
            closeConnection();
            return artistas;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Artista findById(int id) {
        Artista artista = null;
        String query = "SELECT id_artista, nombre FROM artista WHERE id_artista = ?";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                artista = new Artista();
                artista.setId(rs.getInt("id_artista"));
                artista.setNombre(rs.getString("nombre"));
            }
            rs.close();
            ps.close();
            closeConnection();
            return artista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Artista findByName(String nombre) {
        Artista artista = null;
        String query = "SELECT id_artista, nombre FROM artista WHERE nombre = ?";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                artista = new Artista();
                artista.setId(rs.getInt("id_artista"));
                artista.setNombre(rs.getString("nombre"));
            }
            rs.close();
            ps.close();
            closeConnection();
            return artista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(Artista artista) {
        int idGenerado = 0;
        String query = "INSERT INTO artista (nombre) VALUES (?)";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return 0;
            }
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, artista.getNombre());
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
        String query = "DELETE FROM artista WHERE id_artista = ?";
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