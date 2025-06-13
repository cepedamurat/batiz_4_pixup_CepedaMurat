package org.cepdoc.pixup.repository.jdbc.impl;

import org.cepdoc.pixup.model.Colonia;
import org.cepdoc.pixup.model.Municipio;
import org.cepdoc.pixup.repository.jdbc.ColoniaJdbc;
import org.cepdoc.pixup.repository.jdbc.Conexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ColoniaJdbcImpl extends Conexion<Colonia> implements ColoniaJdbc {
    private static ColoniaJdbc coloniaJdbc;

    private ColoniaJdbcImpl()
    {
    }

    public static ColoniaJdbc getInstance( )
    {
        if( coloniaJdbc == null )
        {
            coloniaJdbc = new ColoniaJdbcImpl( );
        }
        return coloniaJdbc;
    }

    @Override
    public List<Colonia> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Colonia>Colonias = null;
        Colonia Colonia = null;
        String query = "SELECT * FROM colonia";

        try
        {
            if( openConnection() )
            {
                System.out.println("Error en conexión");
                return null;
            }
            statement = connection.createStatement( );
            resultSet = statement.executeQuery( query );
            Colonias = new ArrayList<>( );
            while( resultSet.next() )
            {
                Colonia = new Colonia();
                Colonia.setId( resultSet.getInt( 1 ) );
                Colonia.setNombre( resultSet.getString( 2 ) );
                Colonias.add( Colonia );
            }
            resultSet.close();
            statement.close();
            closeConnection( );
            return Colonias;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Colonia findById(int id) {
        Statement statement = null;
        ResultSet resultSet = null;
        Colonia colonia = null;
        String query = "SELECT * FROM colonia WHERE id_colonia = " + id;

        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                colonia = new Colonia();
                colonia.setId(resultSet.getInt("id_colonia"));
                colonia.setNombre(resultSet.getString("nombre"));
                colonia.setIdMunicipio(resultSet.getInt("id_municipio"));
            }
            resultSet.close();
            statement.close();
            closeConnection();
            return colonia;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Colonia findByName(String nombre) {
        Statement statement = null;
        ResultSet rs = null;
        Colonia colonia = null;
        String query = "SELECT * FROM colonia WHERE nombre = '" + nombre + "'";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()) {
                colonia = new Colonia();
                colonia.setId(rs.getInt("id_colonia"));
                colonia.setNombre(rs.getString("nombre"));
                colonia.setIdMunicipio(rs.getInt("id_municipio"));
            }
            rs.close();
            closeConnection();
            return colonia;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insert(Colonia colonia) {
        Statement statement = null;
        String sql = "INSERT INTO colonia (nombre, cp, id_municipio) VALUES ('" + 
                    colonia.getNombre() + "', '" + colonia.getCp() + "', " + colonia.getIdMunicipio() + 
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

    @Override
    public boolean delete(int id) {
        Statement statement = null;
        String sql = "DELETE FROM colonia WHERE id_colonia = " + id;
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return false;
            }
            statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(sql);
            closeConnection();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main( String a[] )
    {
        ColoniaJdbcImpl
                .getInstance()
                .findAll()
                .stream()
                .forEach( System.out::println);
    }

}
