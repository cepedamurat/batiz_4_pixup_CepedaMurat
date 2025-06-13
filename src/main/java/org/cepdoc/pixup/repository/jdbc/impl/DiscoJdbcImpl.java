package org.cepdoc.pixup.repository.jdbc.impl;

import org.cepdoc.pixup.model.Disco;
import org.cepdoc.pixup.repository.jdbc.DiscoJdbc;
import org.cepdoc.pixup.repository.jdbc.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DiscoJdbcImpl extends Conexion<Disco> {
    private static DiscoJdbcImpl discoJdbc;

    private DiscoJdbcImpl() {}

    public static DiscoJdbcImpl getInstance() {
        if (discoJdbc == null) {
            discoJdbc = new DiscoJdbcImpl();
        }
        return discoJdbc;
    }

    public List<Disco> findAll() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Disco> discos = null;
        String query = "SELECT * FROM disco";

        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            discos = new ArrayList<>();
            while (resultSet.next()) {
                Disco disco = new Disco();
                disco.setId(resultSet.getInt("id_disco"));
                disco.setTitulo(resultSet.getString("titulo"));
                disco.setIdArtista(resultSet.getInt("id_artista"));
                disco.setIdGeneroMusical(resultSet.getInt("id_genero_musical"));
                disco.setIdDisquera(resultSet.getInt("id_disquera"));
                disco.setFechaLanzamiento(resultSet.getDate("fecha_lanzamiento"));
                disco.setPrecio(resultSet.getFloat("precio"));
                disco.setImagen(resultSet.getString("imagen"));
                disco.setExistencia(resultSet.getInt("existencia"));
                disco.setDescuento(resultSet.getFloat("descuento"));
                discos.add(disco);
            }
            resultSet.close();
            statement.close();
            closeConnection();
            return discos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Disco findById(int id) {
        Statement statement = null;
        ResultSet resultSet = null;
        Disco disco = null;
        String query = "SELECT * FROM disco WHERE id_disco = " + id;

        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                disco = new Disco();
                disco.setId(resultSet.getInt("id_disco"));
                disco.setTitulo(resultSet.getString("titulo"));
                disco.setIdArtista(resultSet.getInt("id_artista"));
                disco.setIdGeneroMusical(resultSet.getInt("id_genero_musical"));
                disco.setIdDisquera(resultSet.getInt("id_disquera"));
                disco.setFechaLanzamiento(resultSet.getDate("fecha_lanzamiento"));
                disco.setPrecio(resultSet.getFloat("precio"));
                disco.setImagen(resultSet.getString("imagen"));
                disco.setExistencia(resultSet.getInt("existencia"));
                disco.setDescuento(resultSet.getFloat("descuento"));
            }
            resultSet.close();
            statement.close();
            closeConnection();
            return disco;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Disco findByName(String nombre) {
        Statement statement = null;
        ResultSet resultSet = null;
        Disco disco = null;
        String query = "SELECT * FROM disco WHERE titulo = '" + nombre + "'";

        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                disco = new Disco();
                disco.setId(resultSet.getInt("id_disco"));
                disco.setTitulo(resultSet.getString("titulo"));
                disco.setIdArtista(resultSet.getInt("id_artista"));
                disco.setIdGeneroMusical(resultSet.getInt("id_genero_musical"));
                disco.setIdDisquera(resultSet.getInt("id_disquera"));
                disco.setFechaLanzamiento(resultSet.getDate("fecha_lanzamiento"));
                disco.setPrecio(resultSet.getFloat("precio"));
                disco.setImagen(resultSet.getString("imagen"));
                disco.setExistencia(resultSet.getInt("existencia"));
                disco.setDescuento(resultSet.getFloat("descuento"));
            }
            resultSet.close();
            statement.close();
            closeConnection();
            return disco;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insert(Disco disco) {
        Statement statement = null;
        String sql = "INSERT INTO disco (titulo, precio, existencia, descuento, fecha_lanzamiento, imagen, id_disquera, id_artista, id_genero_musical) VALUES ('" +
                disco.getTitulo() + "', " +
                disco.getPrecio() + ", " +
                disco.getExistencia() + ", " +
                disco.getDescuento() + ", '" +
                disco.getFechaLanzamiento() + "', '" +
                disco.getImagen() + "', " +
                disco.getIdDisquera() + ", " +
                disco.getIdArtista() + ", " +
                disco.getIdGeneroMusical() + ")";

        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM disco WHERE id_disco = ?";
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

    public static void main(String[] args) {
        DiscoJdbcImpl
                .getInstance()
                .findAll()
                .forEach(System.out::println);
    }
}
