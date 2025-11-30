package control;

import dao.ReporteVentasDiariasDAO;
import model.ReporteVentasDiarias;
import model.RegistroVentaDiaria;

import java.util.List;

public class ReporteVentasDiariasController {

    private ReporteVentasDiariasDAO dao;

    public ReporteVentasDiariasController() {
        this.dao = new ReporteVentasDiariasDAO();
    }

    public ReporteVentasDiarias generarReporte(String fechaDia) {
        List<RegistroVentaDiaria> ventas = dao.obtenerVentasDiarias(fechaDia);
        return new ReporteVentasDiarias(ventas, fechaDia);
    }
}