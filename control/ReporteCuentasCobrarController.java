package control;

import dao.ReporteCuentasCobrarDAO;
import model.ReporteCuentasCobrar;
import model.RegistroCuentaCobrar;

import java.util.List;

public class ReporteCuentasCobrarController {

    private ReporteCuentasCobrarDAO dao;

    public ReporteCuentasCobrarController() {
        this.dao = new ReporteCuentasCobrarDAO();
    }

    public ReporteCuentasCobrar generarReporte() {
        List<RegistroCuentaCobrar> cuentas = dao.obtenerCuentas();
        return new ReporteCuentasCobrar(cuentas);
    }
}