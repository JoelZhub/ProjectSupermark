package dao;

import db.DBConnection;
import model.CuentaPagar;
import model.CuentaPagarPago;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaPagarDAO {

    public boolean insertar(CuentaPagar c) {
        String sql = "INSERT INTO cuentas_pagar (idCompra, idProveedor, monto_total, monto_pendiente, estado) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, c.getIdCompra());
            stmt.setInt(2, c.getIdProveedor());
            stmt.setDouble(3, c.getMontoTotal());
            stmt.setDouble(4, c.getMontoPendiente());
            stmt.setString(5, c.getEstado());

            int rows = stmt.executeUpdate();
            if (rows == 0) return false;

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                // opcional: set id en modelo si se expone setter
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Error insertando cuenta por pagar: " + e.getMessage());
            return false;
        }
    }

    public boolean editar(CuentaPagar c) {
        String sql = "UPDATE cuentas_pagar SET idCompra=?, idProveedor=?, monto_total=?, monto_pendiente=?, estado=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, c.getIdCompra());
            stmt.setInt(2, c.getIdProveedor());
            stmt.setDouble(3, c.getMontoTotal());
            stmt.setDouble(4, c.getMontoPendiente());
            stmt.setString(5, c.getEstado());
            stmt.setInt(6, c.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error editando cuenta por pagar: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM cuentas_pagar WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error eliminando cuenta por pagar: " + e.getMessage());
            return false;
        }
    }

    public CuentaPagar buscar(int id) {
        String sql = "SELECT * FROM cuentas_pagar WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CuentaPagar(
                        rs.getInt("id"),
                        rs.getInt("idCompra"),
                        rs.getInt("idProveedor"),
                        rs.getDouble("monto_total"),
                        rs.getDouble("monto_pendiente"),
                        rs.getString("estado")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error buscando cuenta por pagar: " + e.getMessage());
        }
        return null;
    }

    public List<CuentaPagar> listar() {
        String sql = "SELECT * FROM cuentas_pagar";
        List<CuentaPagar> lista = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new CuentaPagar(
                        rs.getInt("id"),
                        rs.getInt("idCompra"),
                        rs.getInt("idProveedor"),
                        rs.getDouble("monto_total"),
                        rs.getDouble("monto_pendiente"),
                        rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error listando cuentas por pagar: " + e.getMessage());
        }
        return lista;
    }

    public boolean registrarPago(CuentaPagarPago pago) {
        String sqlPago = "INSERT INTO cuentas_pagar_pagos (idCuenta, fecha, monto) VALUES (?, ?, ?)";
        String sqlUpdate = "UPDATE cuentas_pagar SET monto_pendiente = ?, estado = ? WHERE id = ?";
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

            CuentaPagar cuenta = buscar(pago.getIdCuenta());
            if (cuenta == null) {
                con.rollback();
                return false;
            }

            double nuevoPendiente = cuenta.getMontoPendiente() - pago.getMonto();
            String nuevoEstado = nuevoPendiente <= 0 ? "PAGADA" : cuenta.getEstado();

            try (PreparedStatement pst2 = con.prepareStatement(sqlUpdate)) {
                pst2.setDouble(1, Math.max(0, nuevoPendiente));
                pst2.setString(2, nuevoEstado);
                pst2.setInt(3, pago.getIdCuenta());
                pst2.executeUpdate();
            }

            con.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("Error registrando pago cuenta por pagar: " + e.getMessage());
            try { if (con != null) con.rollback(); } catch (SQLException ignored) {}
            return false;
        } finally {
            try { if (con != null) con.setAutoCommit(true); } catch (SQLException ignored) {}
        }
    }

    public List<CuentaPagarPago> listarPagos(int idCuenta) {
        String sql = "SELECT * FROM cuentas_pagar_pagos WHERE idCuenta = ?";
        List<CuentaPagarPago> lista = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, idCuenta);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                lista.add(new CuentaPagarPago(
                        rs.getInt("idPago"),
                        rs.getInt("idCuenta"),
                        rs.getString("fecha"),
                        rs.getDouble("monto")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error listando pagos cuentas por pagar: " + e.getMessage());
        }
        return lista;
    }
}