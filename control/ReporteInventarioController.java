package control;

import dao.ReporteInventarioDAO;
import model.ReporteInventario;
import model.RegistroInventario;

import java.util.List;

public class ReporteInventarioController {

    private ReporteInventarioDAO dao;

    public ReporteInventarioController() {
        this.dao = new ReporteInventarioDAO();
    }

    public ReporteInventario generarReporte() {
        List<RegistroInventario> ventas = dao.obtenerInventario();
        return new ReporteInventario(ventas);
    }
}