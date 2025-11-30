package dao;

import db.DBConnection;
import model.RegistroCuentaCobrar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReporteCuentasCobrarDAO {

    /**
     * Si estadoFiltro es null o vacío, devuelve todas las cuentas.
     * Si no, filtra por cc.estado = estadoFiltro.
     */
    public List<RegistroCuentaCobrar> obtenerCuentas() {
        List<RegistroCuentaCobrar> lista = new ArrayList<>();

        String sql = """
            SELECT cc.id,
                   c.nombre AS cliente,
                   cc.monto_total,
                   cc.monto_pendiente,
                   cc.estado
            FROM cuentas_cobrar cc
            INNER JOIN clientes c ON cc.idCliente = c.identificacion
            WHERE cc.estado = 'Pendiente';
            """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String cliente = rs.getString("cliente");
                double montoTotal = rs.getDouble("monto_total");
                double montoPendiente = rs.getDouble("monto_pendiente");
                String estado = rs.getString("estado");

                lista.add(new RegistroCuentaCobrar(id, cliente, montoTotal, montoPendiente, estado));
            }

        } catch (SQLException e) {
            System.out.println("Error obteniendo cuentas por cobrar: " + e.getMessage());
        }

        return lista;
    }
}