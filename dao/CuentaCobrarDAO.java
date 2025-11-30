package dao;

import db.DBConnection;
import model.CuentaCobrar;
import model.CuentaCobrarPago;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaCobrarDAO {

    public boolean insertar(CuentaCobrar c) {
        String sql = "INSERT INTO cuentas_cobrar (idCliente, idFactura, monto_total, monto_pendiente, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, c.getIdCliente());
            pst.setInt(2, c.getIdFactura());
            pst.setDouble(3, c.getMontoTotal());
            pst.setDouble(4, c.getMontoPendiente());
            pst.setString(5, c.getEstado());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error insertando cuenta por cobrar: " + e.getMessage());
            return false;
        }
    }

    public boolean editar(CuentaCobrar c) {
        String sql = "UPDATE cuentas_cobrar SET idCliente=?, idFactura=?, monto_total=?, monto_pendiente=?, estado=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, c.getIdCliente());
            pst.setInt(2, c.getIdFactura());
            pst.setDouble(3, c.getMontoTotal());
            pst.setDouble(4, c.getMontoPendiente());
            pst.setString(5, c.getEstado());
            pst.setInt(6, c.getId());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error editando cuenta por cobrar: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM cuentas_cobrar WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error eliminando cuenta por cobrar: " + e.getMessage());
            return false;
        }
    }

    public CuentaCobrar buscar(int id) {
        String sql = "SELECT * FROM cuentas_cobrar WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new CuentaCobrar(
                        rs.getInt("id"),
                        rs.getString("idCliente"),
                        rs.getInt("idFactura"),
                        rs.getDouble("monto_total"),
                        rs.getDouble("monto_pendiente"),
                        rs.getString("estado")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error buscando cuenta por cobrar: " + e.getMessage());
        }
        return null;
    }

    public List<CuentaCobrar> listar() {
        String sql = "SELECT * FROM cuentas_cobrar";
        List<CuentaCobrar> lista = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new CuentaCobrar(
                        rs.getInt("id"),
                        rs.getString("idCliente"),
                        rs.getInt("idFactura"),
                        rs.getDouble("monto_total"),
                        rs.getDouble("monto_pendiente"),
                        rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error listando cuentas por cobrar: " + e.getMessage());
        }
        return lista;
    }

    public boolean registrarPago(CuentaCobrarPago pago) {
        String sqlPago = "INSERT INTO cuentas_cobrar_pagos (idCuenta, fecha, monto) VALUES (?, ?, ?)";
        String sqlUpdate = "UPDATE cuentas_cobrar SET monto_pendiente = ?, estado = ? WHERE id = ?";
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            try (PreparedStatement pst = con.prepareStatement(sqlPago)) {
                pst.setInt(1, pago.getIdCuenta());
                pst.setString(2, pago.getFecha());
                pst.setDouble(3, pago.getMonto());
                pst.executeUpdate();
            }

            CuentaCobrar cuenta = buscar(pago.getIdCuenta());
            if (cuenta == null) {
                con.rollback();
                return false;
            }

            double nuevoPendiente = cuenta.getMontoPendiente() - pago.getMonto();
            String nuevoEstado = nuevoPendiente <= 0 ? "COBRADA" : cuenta.getEstado();

            try (PreparedStatement pst2 = con.prepareStatement(sqlUpdate)) {
                pst2.setDouble(1, Math.max(0, nuevoPendiente));
                pst2.setString(2, nuevoEstado);
                pst2.setInt(3, pago.getIdCuenta());
                pst2.executeUpdate();
            }

            con.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("Error registrando pago cuenta por cobrar: " + e.getMessage());
            try { if (con != null) con.rollback(); } catch (SQLException ignored) {}
            return false;
        } finally {
            try { if (con != null) con.setAutoCommit(true); } catch (SQLException ignored) {}
        }
    }

    public List<CuentaCobrarPago> listarPagos(int idCuenta) {
        String sql = "SELECT * FROM cuentas_cobrar_pagos WHERE idCuenta = ?";
        List<CuentaCobrarPago> lista = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, idCuenta);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                lista.add(new CuentaCobrarPago(
                        rs.getInt("idPago"),
                        rs.getInt("idCuenta"),
                        rs.getString("fecha"),
                        rs.getDouble("monto")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error listando pagos cuentas por cobrar: " + e.getMessage());
        }
        return lista;
    }
}