package control;

import dao.CuentaPagarDAO;
import model.CuentaPagar;
import model.CuentaPagarPago;

import java.util.List;

public class CuentaPagarController {

    private CuentaPagarDAO dao;

    public CuentaPagarController() {
        dao = new CuentaPagarDAO();
    }

    public List<CuentaPagar> listarCuentas() {
        return dao.listar();
    }

    public CuentaPagar buscarCuenta(int id) {
        return dao.buscar(id);
    }

    public String registrarCuenta(CuentaPagar c) {
        return dao.insertar(c) ? "Cuenta por pagar registrada." : "Error registrando cuenta.";
    }

    public String registrarPago(int idCuenta, String fecha, double monto) {
        CuentaPagarPago pago = new CuentaPagarPago(idCuenta, fecha, monto);
        return dao.registrarPago(pago) ? "Pago registrado." : "Error registrando pago.";
    }

    public List<CuentaPagarPago> listarPagos(int idCuenta) {
        return dao.listarPagos(idCuenta);
    }
}