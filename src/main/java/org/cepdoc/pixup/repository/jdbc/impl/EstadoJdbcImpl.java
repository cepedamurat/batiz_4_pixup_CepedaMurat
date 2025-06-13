package org.cepdoc.pixup.repository.jdbc.impl;

import org.cepdoc.pixup.repository.jdbc.Conexion;
import org.cepdoc.pixup.repository.jdbc.EstadoJdbc;
import org.cepdoc.pixup.model.Estado;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EstadoJdbcImpl extends Conexion<Estado> implements EstadoJdbc
{
private static  EstadoJdbc estadoJdbc;

    public EstadoJdbcImpl()
    {
    }

    public static EstadoJdbc getInstance()
    {
        if( estadoJdbc == null )
        {
            estadoJdbc = new EstadoJdbcImpl();
        }
        return estadoJdbc;
    }

    @Override
    public List<Estado> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Estado> list = null;
        Estado estado = null;
        String sql ="Select * from estado";

        try
        {
            if( openConnection() )
            {
                System.out.println("Error en conexion");
                return null;
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery( sql );
            if( resultSet == null )
            {
                return null;
            }
            list =  new java.util.ArrayList<Estado>( );
            while( resultSet.next( ) )
            {
                estado = new Estado();
                estado.setId( resultSet.getInt( "ID_ESTADO" ) );
                estado.setNombre( resultSet.getString( "NOMBRE_ESTADO" ) );
                list.add( estado );
            }
            resultSet.close( );
            closeConnection( );
            return list;
        }
        catch (SQLException e)
        {
            return null;
        }
    }

    @Override
    public Estado findById(int id) {
        Statement statement = null;
        ResultSet resultSet = null;
        Estado estado = null;
        String sql ="Select * from estado where id_estado = " + id;
        try {
            if (openConnection()) {
                System.out.println("Error en conexion");
                return null;
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet == null) {
                return null;
            }
            else {
                estado = new Estado();
                if (resultSet.next()) {
                    estado.setId(resultSet.getInt("ID_ESTADO"));
                    estado.setNombre(resultSet.getString("NOMBRE_ESTADO"));
                    resultSet.close();
                    closeConnection();
                    return estado;
                }
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    @Override
    public Estado findByName(String nombre) {
        Statement statement = null;
        ResultSet resultSet = null;
        Estado estado = null;
        String sql = "Select * from estado where nombre_estado = '" + nombre + "'";

        try {
            if (openConnection()) {
                System.out.println("Error en conexion");
                return null;
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet == null) {
                return null;
            } else {
                estado = new Estado();
                if (resultSet.next()) {
                    estado.setId(resultSet.getInt("ID_ESTADO"));
                    estado.setNombre(resultSet.getString("NOMBRE_ESTADO"));
                    resultSet.close();
                    closeConnection();
                    return estado;
                }
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    @Override
    public int insert(Estado estado) {
        Statement statement = null;
        String sql = "INSERT INTO estado (nombre_estado) VALUES ('" + estado.getNombre() + "')";
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
            closeConnection();
        } catch (SQLException e) {
            return 0;
        }
        return 0;
    }

    @Override
    public boolean update(Estado estado) {
        // Implementación pendiente
        return false;
    }

    @Override
    public boolean delete(int id) {
        // Implementación pendiente
        return false;
    }

    public static void main(String[] a) {
        EstadoJdbcImpl.getInstance().findAll().forEach(System.out::println);
    }

}