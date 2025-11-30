package dao;

import db.DBConnection;
import model.CompraProveedor;
import utils.Operaciones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraProveedorDAO implements Operaciones<CompraProveedor> {

    @Override
    public boolean insertar(CompraProveedor c) {
        String sql = "INSERT INTO compra_proveedor (idProducto, idProveedor, cantidad, fecha, precio) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, c.getIdProducto());
            stmt.setInt(2, c.getIdProveedor());
            stmt.setInt(3, c.getCantidad());
            stmt.setString(4, c.getFecha());
            stmt.setDouble(5, c.getPrecio());

            int rows = stmt.executeUpdate();
            if (rows == 0) return false;

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                c.setIdCompra(rs.getInt(1));
            }

            // opcional: crear automáticamente la cuenta por pagar por esta compra
            crearCuentaPorPagar(con, c);

            return true;
        } catch (SQLException e) {
            System.out.println("Error insertando compra: " + e.getMessage());
            return false;
        }
    }

    private void crearCuentaPorPagar(Connection con, CompraProveedor c) {
        String sql = "INSERT INTO cuentas_pagar (idCompra, idProveedor, monto_total, monto_pendiente, estado) VALUES (?, ?, ?, ?, ?)";
        double monto = c.getCantidad() * c.getPrecio();
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, c.getIdCompra());
            st.setInt(2, c.getIdProveedor());
            st.setDouble(3, monto);
            st.setDouble(4, monto);
            st.setString(5, "PENDIENTE");
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error creando cuenta por pagar al insertar compra: " + e.getMessage());
        }
    }

    @Override
    public boolean editar(CompraProveedor c) {
        String sql = "UPDATE compra_proveedor SET idProducto=?, idProveedor=?, cantidad=?, fecha=?, precio=? WHERE idCompra=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, c.getIdProducto());
            stmt.setInt(2, c.getIdProveedor());
            stmt.setInt(3, c.getCantidad());
            stmt.setString(4, c.getFecha());
            stmt.setDouble(5, c.getPrecio());
            stmt.setInt(6, c.getIdCompra());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error editando compra: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM compra_proveedor WHERE idCompra = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error eliminando compra: " + e.getMessage());
            return false;
        }
    }

    @Override
    public CompraProveedor buscar(int id) {
        String sql = "SELECT * FROM compra_proveedor WHERE idCompra = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CompraProveedor(
                        rs.getInt("idCompra"),
                        rs.getInt("idProducto"),
                        rs.getInt("idProveedor"),
                        rs.getInt("cantidad"),
                        rs.getString("fecha"),
                        rs.getDouble("precio")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error buscando compra: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<CompraProveedor> listar() {
        String sql = "SELECT * FROM compra_proveedor";
        List<CompraProveedor> lista = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new CompraProveedor(
                        rs.getInt("idCompra"),
                        rs.getInt("idProducto"),
                        rs.getInt("idProveedor"),
                        rs.getInt("cantidad"),
                        rs.getString("fecha"),
                        rs.getDouble("precio")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error listando compras: " + e.getMessage());
        }
        return lista;
    }
}