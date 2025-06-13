package org.cepdoc.pixup.repository.jdbc.impl;

import org.cepdoc.pixup.model.Orden;
import org.cepdoc.pixup.repository.jdbc.Conexion;
import org.cepdoc.pixup.repository.jdbc.OrdenJdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdenJdbcImpl extends Conexion<Orden> implements OrdenJdbc {
    private static OrdenJdbc instance;

    private OrdenJdbcImpl() {}

    public static OrdenJdbc getInstance() {
        if (instance == null) {
            instance = new OrdenJdbcImpl();
        }
        return instance;
    }

    @Override
    public List<Orden> findAll() {
        List<Orden> ordenes = new ArrayList<>();
        String query = "SELECT id_orden, costo_total, fecha, cantidad_total, id_usuario FROM orden";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Orden orden = new Orden();
                orden.setIdOrden(rs.getInt("id_orden"));
                orden.setCostoTotal(rs.getFloat("costo_total"));
                orden.setFecha(rs.getDate("fecha"));
                orden.setCantidadTotal(rs.getFloat("cantidad_total"));
                orden.setIdUsuario(rs.getInt("id_usuario"));
                ordenes.add(orden);
            }
            rs.close();
            st.close();
            closeConnection();
            return ordenes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Orden findById(int id) {
        Orden orden = null;
        String query = "SELECT id_orden, costo_total, fecha, cantidad_total, id_usuario FROM orden WHERE id_orden = ?";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                orden = new Orden();
                orden.setIdOrden(rs.getInt("id_orden"));
                orden.setCostoTotal(rs.getFloat("costo_total"));
                orden.setFecha(rs.getDate("fecha"));
                orden.setCantidadTotal(rs.getFloat("cantidad_total"));
                orden.setIdUsuario(rs.getInt("id_usuario"));
            }
            rs.close();
            ps.close();
            closeConnection();
            return orden;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(Orden orden) {
        int idGenerado = 0;
        String query = "INSERT INTO orden (costo_total, fecha, cantidad_total, id_usuario) VALUES (?, ?, ?, ?)";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return 0;
            }
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setFloat(1, orden.getCostoTotal());
            ps.setDate(2, new java.sql.Date(orden.getFecha().getTime()));
            ps.setFloat(3, orden.getCantidadTotal());
            ps.setInt(4, orden.getIdUsuario());
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
        String query = "DELETE FROM orden WHERE id_orden = ?";
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