package org.cepdoc.pixup.repository.jdbc.impl;

import org.cepdoc.pixup.model.Pago;
import org.cepdoc.pixup.repository.jdbc.PagoJdbc;
import org.cepdoc.pixup.repository.jdbc.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagoJdbcImpl extends Conexion<Pago> implements PagoJdbc {
    private static PagoJdbc instance;

    public PagoJdbcImpl() {}

    public static PagoJdbc getInstance() {
        if (instance == null) {
            instance = new PagoJdbcImpl();
        }
        return instance;
    }

    @Override
    public List<Pago> findAll() {
        List<Pago> pagos = new ArrayList<>();
        String query = "SELECT id_pago, numero_tarjeta, fecha_pago, cantidad_total, id_orden FROM pago";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt("id_pago"));
                pago.setNumeroTarjeta(rs.getString("numero_tarjeta"));
                pago.setFechaPago(rs.getDate("fecha_pago"));
                pago.setCantidadTotal(rs.getFloat("cantidad_total"));
                pago.setIdOrden(rs.getInt("id_orden"));
                pagos.add(pago);
            }
            rs.close();
            st.close();
            closeConnection();
            return pagos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Pago findById(int id) {
        Pago pago = null;
        String query = "SELECT id_pago, numero_tarjeta, fecha_pago, cantidad_total, id_orden FROM pago WHERE id_pago = ?";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pago = new Pago();
                pago.setIdPago(rs.getInt("id_pago"));
                pago.setNumeroTarjeta(rs.getString("numero_tarjeta"));
                pago.setFechaPago(rs.getDate("fecha_pago"));
                pago.setCantidadTotal(rs.getFloat("cantidad_total"));
                pago.setIdOrden(rs.getInt("id_orden"));
            }
            rs.close();
            ps.close();
            closeConnection();
            return pago;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Pago> findByNumeroTarjeta(String numeroTarjeta) {
        List<Pago> pagos = new ArrayList<>();
        String query = "SELECT id_pago, numero_tarjeta, fecha_pago, cantidad_total, id_orden FROM pago WHERE numero_tarjeta = ?";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return null;
            }
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, numeroTarjeta);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt("id_pago"));
                pago.setNumeroTarjeta(rs.getString("numero_tarjeta"));
                pago.setFechaPago(rs.getDate("fecha_pago"));
                pago.setCantidadTotal(rs.getFloat("cantidad_total"));
                pago.setIdOrden(rs.getInt("id_orden"));
                pagos.add(pago);
            }
            rs.close();
            ps.close();
            closeConnection();
            return pagos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(Pago pago) {
        int idGenerado = 0;
        String query = "INSERT INTO pago (numero_tarjeta, fecha_pago, cantidad_total, id_orden) VALUES (?, ?, ?, ?)";
        try {
            if (openConnection()) {
                System.out.println("Error en conexión");
                return 0;
            }
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pago.getNumeroTarjeta());
            ps.setDate(2, new java.sql.Date(pago.getFechaPago().getTime()));
            ps.setFloat(3, pago.getCantidadTotal());
            ps.setInt(4, pago.getIdOrden());
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
}