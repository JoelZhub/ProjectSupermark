package dao;

import db.DBConnection;
import model.Factura;
import utils.Operaciones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO implements Operaciones<Factura> {

    @Override
    public boolean insertar(Factura f) {
        String sql = "INSERT INTO facturas (fecha, total, metodoPago, clienteId, empleadoId) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, f.getFecha());
            stmt.setDouble(2, f.getTotal());
            stmt.setString(3, f.getMetodoPago());
            stmt.setInt(4, f.getClienteId());
            stmt.setInt(5, f.getCajeroId());

            int rows = stmt.executeUpdate();
            if (rows == 0) return false;

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                f.setIdFactura(rs.getInt(1));
            }

        return true;

        } catch (SQLException e) {
            System.out.println("Error insertando factura: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean editar(Factura f) {
        String sql = "UPDATE facturas SET fecha=?, total=?, metodoPago=?, clienteId=?, empleadoId=? WHERE idFactura=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, f.getFecha());
            stmt.setDouble(2, f.getTotal());
            stmt.setString(3, f.getMetodoPago());
            stmt.setInt(4, f.getClienteId());
            stmt.setInt(5, f.getCajeroId());
            stmt.setInt(6, f.getIdFactura());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error editando factura: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql1 = "DELETE FROM productos_factura WHERE idFactura = ?";
        String sql2 = "DELETE FROM facturas WHERE idFactura = ?";

        try (Connection con = DBConnection.getConnection()) {

            PreparedStatement stmt1 = con.prepareStatement(sql1);
            stmt1.setInt(1, id);
            stmt1.executeUpdate();

            PreparedStatement stmt2 = con.prepareStatement(sql2);
            stmt2.setInt(1, id);
            return stmt2.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminando factura: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Factura buscar(int id) {
        String sql = "SELECT * FROM factura WHERE idFactura=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Factura f = new Factura(
                        rs.getInt("idFactura"),
                        rs.getString("fecha"),
                        rs.getString("metodoPago"),
                        rs.getInt("clienteId"),
                        rs.getInt("empleadoId")
                );
                f.setTotal(rs.getDouble("total"));
                return f;
            }

        } catch (SQLException e) {
            System.out.println("Error buscando factura: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Factura> listar() {
        String sql = "SELECT * FROM factura";
        List<Factura> lista = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Factura f = new Factura(
                        rs.getInt("idFactura"),
                        rs.getString("fecha"),
                        rs.getString("metodoPago"),
                        rs.getInt("clienteId"),
                        rs.getInt("empleadoId")
                );
                f.setTotal(rs.getDouble("total"));
                lista.add(f);
            }

        } catch (SQLException e) {
            System.out.println("Error listando facturas: " + e.getMessage());
        }

        return lista;
    }

    //productos_factura

    public boolean agregarProducto(int idFactura, String idProducto, int cantidad) {
        String sql = "INSERT INTO factura_producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idFactura);
            stmt.setString(2, idProducto);
            stmt.setInt(3, cantidad);

            boolean ok = stmt.executeUpdate() > 0;

            if (ok) actualizarTotal(idFactura);
            return ok;

        } catch (SQLException e) {
            System.out.println("Error agregando producto a factura: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarCantidad(int idFactura, String idProducto, int cantidad) {
        String sql = "UPDATE factura_producto SET cantidad=? WHERE idFactura=? AND idProducto=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, cantidad);
            stmt.setInt(2, idFactura);
            stmt.setString(3, idProducto);

            boolean ok = stmt.executeUpdate() > 0;

            if (ok) actualizarTotal(idFactura);
            return ok;

        } catch (SQLException e) {
            System.out.println("Error actualizando cantidad: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarProducto(int idFactura, String idProducto) {
        String sql = "DELETE FROM factura_producto WHERE idFactura=? AND idProducto=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idFactura);
            stmt.setString(2, idProducto);

            boolean ok = stmt.executeUpdate() > 0;

            if (ok) actualizarTotal(idFactura);
            return ok;

        } catch (SQLException e) {
            System.out.println("Error eliminando producto de factura: " + e.getMessage());
            return false;
        }
    }

    public List<String[]> listarProductos(String idFactura) {
        List<String[]> lista = new ArrayList<>();
        String sql = "SELECT idProducto, cantidad FROM factura_producto WHERE idFactura=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setString(1, idFactura);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                lista.add(new String[]{
                        rs.getString("idProducto"),
                        String.valueOf(rs.getInt("cantidad"))
                });
            }

        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
        return lista;
    }

    //total

    public double calcularTotal(int idFactura) {

        String sql = """
                SELECT fp.cantidad, p.precio
                FROM factura_producto fp
                JOIN productos p ON fp.idProducto = p.idProducto
                WHERE fp.idFactura = ?
                """;

        double total = 0;

        try (Connection con = DBConnection.getConnection();
            PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, idFactura);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int cantidad = rs.getInt("cantidad");
                double precio = rs.getDouble("precio");
                total += cantidad * precio;
            }

        } catch (SQLException e) {
            System.out.println("Error calculando total: " + e.getMessage());
        }

        return total;
    }

    public void actualizarTotal(int idFactura) {
        double total = calcularTotal(idFactura);

        String sql = "UPDATE facturas SET total=? WHERE idFactura=?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement st = con.prepareStatement(sql)) {

            st.setDouble(1, total);
            st.setInt(2, idFactura);

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error actualizando total: " + e.getMessage());
        }
    }
}