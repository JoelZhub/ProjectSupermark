package control;

import dao.CuentaCobrarDAO;
import model.CuentaCobrar;
import model.CuentaCobrarPago;

import java.util.List;

public class CuentaCobrarController {

    private CuentaCobrarDAO dao;

    public CuentaCobrarController() {
        dao = new CuentaCobrarDAO();
    }

    public List<CuentaCobrar> listarCuentas() {
        return dao.listar();
    }

    public CuentaCobrar buscarCuenta(int id) {
        return dao.buscar(id);
    }

    public String registrarCuenta(CuentaCobrar c) {
        return dao.insertar(c) ? "Cuenta por cobrar registrada." : "Error registrando cuenta.";
    }

    public String registrarPago(int idCuenta, String fecha, double monto) {
        CuentaCobrarPago pago = new CuentaCobrarPago(idCuenta, fecha, monto);
        return dao.registrarPago(pago) ? "Pago registrado." : "Error registrando pago.";
    }

    public java.util.List<CuentaCobrarPago> listarPagos(int idCuenta) {
        return dao.listarPagos(idCuenta);
    }
}