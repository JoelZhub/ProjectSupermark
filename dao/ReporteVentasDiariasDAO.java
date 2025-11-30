package dao;

import db.DBConnection;
import model.RegistroVentaDiaria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReporteVentasDiariasDAO {

    public List<RegistroVentaDiaria> obtenerVentasDiarias(String fechaDia) {
        List<RegistroVentaDiaria> lista = new ArrayList<>();

        String sql = """
            SELECT p.nombre,
                   p.precio,
                   SUM(fp.cantidad) AS cantidad,
                   (p.precio * SUM(fp.cantidad)) AS total
            FROM facturas f
            INNER JOIN factura_producto fp ON f.idFactura = fp.idFactura
            INNER JOIN productos p ON fp.idProducto = p.idProducto
            WHERE f.fecha LIKE ?
            GROUP BY p.idProducto, p.nombre, p.precio
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, fechaDia + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");

                lista.add(new RegistroVentaDiaria(nombre, precio, cantidad));
            }

        } catch (SQLException e) {
            System.out.println("Error obteniendo ventas diarias: " + e.getMessage());
        }

        return lista;
    }
}