package dao;

import db.DBConnection;
import model.RegistroInventario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReporteInventarioDAO {

    public List<RegistroInventario> obtenerInventario() {
        List<RegistroInventario> lista = new ArrayList<>();

        String sql = """
            SELECT nombre, precio, cantidad, unidad FROM productos
        """;

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                String unidad = rs.getString("unidad");

                lista.add(new RegistroInventario(nombre, unidad, precio, cantidad));
            }

        } catch (SQLException e) {
            System.out.println("Error obteniendo inventario: " + e.getMessage());
        }

        return lista;
    }
}