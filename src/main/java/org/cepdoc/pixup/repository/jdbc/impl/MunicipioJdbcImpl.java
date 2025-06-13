package org.cepdoc.pixup.repository.jdbc.impl;

import org.cepdoc.pixup.model.Municipio;
import org.cepdoc.pixup.repository.jdbc.Conexion;
import org.cepdoc.pixup.repository.jdbc.MunicipioJdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class MunicipioJdbcImpl extends Conexion<Municipio> implements MunicipioJdbc {
    private static MunicipioJdbc municipioJdbc;

    private MunicipioJdbcImpl() {}

    public static MunicipioJdbc getInstance() {
        if (municipioJdbc == null) {
            municipioJdbc = new MunicipioJdbcImpl();
        }
        return municipioJdbc;
    }

    @Override
    public List<Municipio> findAll() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Municipio> Municipios = null;
        Municipio municipio = null;
        String query = "SELECT * FROM municipio";

        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            Municipios = new ArrayList<>();
            while (resultSet.next()) {
                municipio = new Municipio();
                municipio.setId(resultSet.getInt("id_municipio"));
                municipio.setNombre(resultSet.getString("nombre"));
                municipio.setIdEstado(resultSet.getInt("id_estado"));
                Municipios.add(municipio);
            }
            resultSet.close();
            statement.close();
            closeConnection();
            return Municipios;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Municipio findById(int id) {
        Statement statement = null;
        ResultSet rs = null;
        Municipio municipio = null;
        String query = "SELECT * FROM municipio WHERE id_municipio = ?";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()) {
                municipio = new Municipio();
                municipio.setId(rs.getInt("id_municipio"));
                municipio.setNombre(rs.getString("nombre"));
                municipio.setIdEstado(rs.getInt("id_estado"));
            }
            rs.close();

            closeConnection();
            return municipio;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Municipio findByName(String nombre) {
        Statement statement = null;
        ResultSet rs = null;
        Municipio municipios = null;
        String query = "SELECT * FROM municipio WHERE nombre = '" + nombre + "'";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()) {
                municipios = new Municipio();
                municipios.setId(rs.getInt("id_municipio"));
                municipios.setNombre(rs.getString("nombre"));
                municipios.setIdEstado(rs.getInt("id_estado"));
            }
            rs.close();
            closeConnection();
            return municipios;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insert(Municipio municipio) {
        Statement statement = null;
        String sql = "INSERT INTO municipio (nombre, id_estado) VALUES ('" + 
                    municipio.getNombre() + "', " + municipio.getIdEstado() + 
                    ")";
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
        } catch (SQLException e) {
            return 0;
        }
        return 0;
    }

    public boolean delete(int id) {
        Statement statement = null;
        String sql = "DELETE FROM municipio WHERE id_municipio = " + id;
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return false;
            }
            statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(sql);
            statement.close();
            closeConnection();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        MunicipioJdbcImpl
                .getInstance()
                .findAll()
                .stream()
                .forEach(System.out::println);
    }
}
