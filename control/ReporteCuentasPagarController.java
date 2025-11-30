package control;

import dao.ReporteCuentasPagarDAO;
import model.ReporteCuentasPagar;
import model.RegistroCuentaPagar;

import java.util.List;

public class ReporteCuentasPagarController {

    private ReporteCuentasPagarDAO dao;

    public ReporteCuentasPagarController() {
        this.dao = new ReporteCuentasPagarDAO();
    }

    /**
     * Genera el reporte. estadoFiltro puede ser null o vacío para todas las cuentas.
     */
    public ReporteCuentasPagar generarReporte() {
        List<RegistroCuentaPagar> cuentas = dao.obtenerCuentas();
        return new ReporteCuentasPagar(cuentas);
    }
}