package dao;

import db.DBConnection;
import model.RegistroCuentaPagar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReporteCuentasPagarDAO {

    /**
     * Si estadoFiltro es null o vacío, devuelve todas las cuentas.
     * Si no, filtra por cp.estado = estadoFiltro.
     */
    public List<RegistroCuentaPagar> obtenerCuentas() {
        List<RegistroCuentaPagar> lista = new ArrayList<>();

        String sql = """
            SELECT cp.id,
                   prov.nombre AS proveedor,
                   cp.monto_total,
                   cp.monto_pendiente,
                   cp.estado
            FROM cuentas_pagar cp
            INNER JOIN proveedores prov ON cp.idProveedor = prov.idProveedor
            WHERE cp.estado = 'Pendiente';
            """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String proveedor = rs.getString("proveedor");
                double montoTotal = rs.getDouble("monto_total");
                double montoPendiente = rs.getDouble("monto_pendiente");
                String estado = rs.getString("estado");

                lista.add(new RegistroCuentaPagar(id, proveedor, montoTotal, montoPendiente, estado));
            }

        } catch (SQLException e) {
            System.out.println("Error obteniendo cuentas por pagar: " + e.getMessage());
        }

        return lista;
    }
}