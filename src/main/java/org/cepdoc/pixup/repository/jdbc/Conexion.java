package org.cepdoc.pixup.repository.jdbc;

import org.cepdoc.pixup.model.Catalogo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Conexion<T extends Catalogo>
{
    /**
     * @author gerdoc
     */

    public static String user = "root";
    public static String password = "n0m3l0";
    public static String db = "pixup";
    public static String server = "127.0.0.1";
    public static String dbport = "3307";
    protected Connection connection;

    public Conexion()
    {
    }

    public boolean testDriver()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            return true;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    private boolean loadConnection(String user, String password, String db, String server)
    {
        String url = null;
        if (user == null || password == null || db == null || server == null)
        {
            return false;
        }
        if ("".equals(user) || "".equals(password) || "".equals(db) || "".equals(server))
        {
            return false;
        }
        url = String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s", server, dbport, db, user, password);
        try
        {
            if (!testDriver( ) )
            {
                return false;
            }
            connection = DriverManager.getConnection(url);
            return connection != null;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean openConnection()
    {
        try
        {
            if( connection == null )
            {
                if( !loadConnection( user, password, db, server ) )
                {
                    return false;
                }
            }
            return connection.isClosed();
        }
        catch (SQLException e)
        {
            return false;
        }
    }
    public void closeConnection( )
    {
        try
        {
            if (connection == null)
            {
                return;
            }
            if (connection.isClosed())
            {
                return;
            }
            connection.close();
            connection = null;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

}