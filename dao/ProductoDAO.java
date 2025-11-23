package dao;

import db.DBConnection;
import model.Categoria;
import model.Producto;
import utils.Operaciones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO implements Operaciones<Producto> {

    @Override
    public boolean insertar(Producto p) {
        if (p == null) return false;

        if (buscar(p.getCodigo()) != null) {
            return false;
        }

        String sql = "INSERT INTO productos (idProducto, nombre, precio, categoria, cantidad, unidad) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, p.getCodigo());
            stmt.setString(2, p.getNombre());
            stmt.setDouble(3, p.getPrecio());
            stmt.setString(4, p.getCategoria().name());
            stmt.setInt(5, p.getCantida());
            stmt.setString(6, p.getUnidad());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error insertar producto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean editar(Producto p) {
        if (p == null) return false;

        String sql = "UPDATE productos SET nombre=?, precio=?, categoria=?, cantidad=?, unidad=? WHERE idProducto=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, p.getNombre());
            stmt.setDouble(2, p.getPrecio());
            stmt.setString(3, p.getCategoria().name());
            stmt.setInt(4, p.getCantida());
            stmt.setString(5, p.getUnidad());
            stmt.setInt(6, p.getCodigo());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error editar producto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM productos WHERE idProducto = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminar producto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Producto buscar(int id) {
        String sql = "SELECT * FROM productos WHERE idProducto = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Producto(
                        rs.getInt("idProducto"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        Categoria.valueOf(rs.getString("categoria")),
                        rs.getInt("cantidad"),
                        rs.getString("unidad")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error buscar producto: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Producto> listar() {
        String sql = "SELECT * FROM productos";
        List<Producto> lista = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Producto p = new Producto(
                        rs.getInt("idProducto"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        Categoria.valueOf(rs.getString("categoria")),
                        rs.getInt("cantidad"),
                        rs.getString("unidad")
                );

                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }

        return lista;
    }
}