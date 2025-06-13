package org.cepdoc.pixup.repository.jdbc.impl;

import org.cepdoc.pixup.model.TipoDomicilio;
import org.cepdoc.pixup.repository.jdbc.Conexion;
import org.cepdoc.pixup.repository.jdbc.TipoDomicilioJdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TipoDomicilioJdbcImpl extends Conexion<TipoDomicilio> implements TipoDomicilioJdbc {
    private static TipoDomicilioJdbc tipoDomicilioJdbc;

    private TipoDomicilioJdbcImpl()
    {
    }

    public static TipoDomicilioJdbc getInstance( )
    {
        if( tipoDomicilioJdbc == null )
        {
            tipoDomicilioJdbc = new TipoDomicilioJdbcImpl( );
        }
        return tipoDomicilioJdbc;
    }

    @Override
    public List<TipoDomicilio> findAll()
    {
        Statement statement = null;
        ResultSet resultSet = null;
        List<TipoDomicilio>TipoDomicilios = null;
        TipoDomicilio TipoDomicilio = null;
        String query = "SELECT * FROM TBL_Tipo_Domicilio";

        try
        {
            if( openConnection() )
            {
                System.out.println("Error en conexión");
                return null;
            }
            statement = connection.createStatement( );
            resultSet = statement.executeQuery( query );
            TipoDomicilios = new ArrayList<>( );
            while( resultSet.next() )
            {
                TipoDomicilio = new TipoDomicilio();
                TipoDomicilio.setId( resultSet.getInt( 1 ) );
                TipoDomicilio.setNombre( resultSet.getString( 2 ) );
                TipoDomicilios.add( TipoDomicilio );
            }
            resultSet.close();
            statement.close();
            closeConnection( );
            return TipoDomicilios;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void main( String a[] )
    {
        TipoDomicilioJdbcImpl
                .getInstance()
                .findAll()
                .stream()
                .forEach( System.out::println);
    }

}
