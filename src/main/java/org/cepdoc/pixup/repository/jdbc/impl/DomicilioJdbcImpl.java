package org.cepdoc.pixup.repository.jdbc.impl;

import org.cepdoc.pixup.model.Domicilio;
import org.cepdoc.pixup.repository.jdbc.Conexion;
import org.cepdoc.pixup.repository.jdbc.DomicilioJdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DomicilioJdbcImpl extends Conexion<Domicilio> implements DomicilioJdbc {
    private static DomicilioJdbc domicilioJdbc;

    private DomicilioJdbcImpl()
    {
    }

    public static DomicilioJdbc getInstance( )
    {
        if( domicilioJdbc == null )
        {
            domicilioJdbc = new DomicilioJdbcImpl( );
        }
        return domicilioJdbc;
    }

    @Override
    public List<Domicilio> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Domicilio>Domicilios = null;
        Domicilio Domicilio = null;
        String query = "SELECT * FROM domicilio";

        try
        {
            if( openConnection() )
            {
                System.out.println("Error en conexión");
                return null;
            }
            statement = connection.createStatement( );
            resultSet = statement.executeQuery( query );
            Domicilios = new ArrayList<>( );
            while( resultSet.next() )
            {
                Domicilio = new Domicilio();
                Domicilio.setId( resultSet.getInt( "id_domicilio" ) );
                Domicilio.setCalle( resultSet.getString( "calle" ) );
                Domicilio.setNum_exterior( resultSet.getInt( "numero_exterior" ) );
                Domicilio.setNum_interior( resultSet.getInt( "numero_interior" ) );
                Domicilio.setIdColonia( resultSet.getInt( "id_colonia" ) );
                Domicilio.setIdUsuario( resultSet.getInt( "id_usuario" ) );
                Domicilios.add( Domicilio );
            }
            resultSet.close();
            statement.close();
            closeConnection( );
            return Domicilios;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void main( String a[] )
    {
        DomicilioJdbcImpl
                .getInstance()
                .findAll()
                .stream()
                .forEach( System.out::println);
    }

    @Override
    public Domicilio findById(int id_domicilio) {
        Statement statement = null;
        ResultSet resultSet = null;
        Domicilio domicilio = null;
        String query = "SELECT * FROM domicilio WHERE id_domicilio = " + id_domicilio;

        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                domicilio = new Domicilio();
                domicilio.setId(resultSet.getInt("id_domicilio"));
                domicilio.setCalle(resultSet.getString("calle"));
                domicilio.setNum_exterior(resultSet.getInt("numero_exterior"));
                domicilio.setNum_interior(resultSet.getInt("numero_interior"));
                domicilio.setIdColonia(resultSet.getInt("id_colonia"));
                domicilio.setIdUsuario(resultSet.getInt("id_usuario"));
            }
            resultSet.close();
            statement.close();
            closeConnection();
            return domicilio;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        
    }

    @Override
    public int insert(Domicilio domicilio) {

        String query = "INSERT INTO domicilio (calle, numero_exterior, numero_interior, id_colonia, id_usuario) VALUES (?, ?, ?, ?, ?)";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return -1;
            }
            var ps = connection.prepareStatement(query);
            ps.setString(1, domicilio.getCalle());
            ps.setInt(2, domicilio.getNum_exterior());
            ps.setInt(3, domicilio.getNum_interior());
            ps.setInt(4, domicilio.getIdColonia());
            ps.setInt(5, domicilio.getIdUsuario());
            int rowsAffected = ps.executeUpdate();
            ps.close();
            closeConnection();
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean delete(int id_domicilio) {
        String query = "DELETE FROM domicilio WHERE id_domicilio = ?";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return false;
            }
            var ps = connection.prepareStatement(query);
            ps.setInt(1, id_domicilio);
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
