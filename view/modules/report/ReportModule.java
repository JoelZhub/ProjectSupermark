package view.modules.report;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import control.ReporteCuentasCobrarController;
import control.ReporteCuentasPagarController;
import control.ReporteInventarioController;
import control.ReporteVentasDiariasController;

import model.ReporteCuentasCobrar;
import model.ReporteCuentasPagar;
import model.ReporteInventario;
import model.ReporteVentasDiarias;
import model.CuentaCobrar;
import model.CuentaPagar;
import model.OperationType;
import model.SubModulo;

import view.AplicationContext;
import view.components.AssetManager;
import view.components.Filtros;
import view.components.Fonts;
import view.components.Messages;
import view.components.BtnStyle;
import view.dashboard.Dahsboard;

import view.formFactory.FormFactory;
import view.modules.report.forms.CrearReporteVentas;
import view.table.TableFactory;
import view.table.UniversalTableModel;
import view.table.schemas.CuentasCobrarSchema;
import view.table.schemas.CuentasPagarSchema;
import view.table.schemas.PagosCuentasCobrarSchema;
import view.table.schemas.PagosCuentasPagarSchema;
import view.table.schemas.TableSchema;
import view.table.style.TableStyle;
import view.table.style.TableStyleConfigure;

public class ReportModule extends JPanel implements ActionListener {
    private final AplicationContext context;
    private final Dahsboard dahsboard;

    private JPanel panelContenedorAccionesCrud;
    private JButton btnreporteCXP, btnreporteCxC, btnreporteInv, btnreporteVenDia;

    private ReporteCuentasCobrarController reporteCuentaCobrarController;
    private ReporteCuentasPagarController reporteCuentaPagarController;
    private ReporteInventarioController reporteInventarioController;
    private ReporteVentasDiariasController reporteVentasDiariasController;

    public ReportModule(Dahsboard dahsboard, AplicationContext context) {

        setLayout(null);
		setBackground(null);
        this.context = context;
        this.dahsboard = dahsboard;

        this.reporteCuentaCobrarController = context.getReportCXCController();
        this.reporteCuentaPagarController = context.getReportCXPController();
        this.reporteInventarioController = context.getReportInvController();
        this.reporteVentasDiariasController = context.getCReportVenDiaController();

        crearPanelOperacionesOfPr();
    }

    public void crearPanelOperacionesOfPr() {
		panelContenedorAccionesCrud = new JPanel();
        panelContenedorAccionesCrud.setLayout(null);
        panelContenedorAccionesCrud.setOpaque(true);
        panelContenedorAccionesCrud.setBackground(null);
        panelContenedorAccionesCrud.setBounds(10, 10, 1043, 300);

        crearBtns();
        panelContenedorAccionesCrud.add(btnreporteCXP);
        panelContenedorAccionesCrud.add(btnreporteCxC);
        panelContenedorAccionesCrud.add(btnreporteInv);
        panelContenedorAccionesCrud.add(btnreporteVenDia);

        add(panelContenedorAccionesCrud);
	}

    public void crearBtns() {
        btnreporteCXP = new JButton("Reporte Cuentas Por Pagar");
        btnreporteCXP.setIcon(AssetManager.icon("descargar.png", 18, 18));
        btnreporteCXP.setBounds(0, 30, 200, 35);
        btnreporteCXP.setFocusPainted(false);
        btnreporteCXP.setBorder(BorderFactory.createLineBorder(new Color(217, 217, 217), 2, true));
        btnreporteCXP.addActionListener(this);

        btnreporteCxC = new JButton("Reporte Cuentas Por Cobrar");
        btnreporteCxC.setIcon(AssetManager.icon("descargar.png", 18, 18));
        btnreporteCxC.setBounds(0, 80, 200, 35);
        btnreporteCxC.setBorder(BorderFactory.createLineBorder(new Color(217, 217, 217), 2, true));
        btnreporteCxC.addActionListener(this);

        btnreporteInv = new JButton("Reporte Inventario");
        btnreporteInv.setBounds(0, 130, 200, 35);
        btnreporteInv.setIcon(AssetManager.icon("descargar.png", 18, 18));
        btnreporteInv.setBorder(BorderFactory.createLineBorder(new Color(217, 217, 217), 2, true));
        btnreporteInv.addActionListener(this);

        btnreporteVenDia = new JButton("Reporte Ventas Por Dia");
        btnreporteVenDia.setIcon(AssetManager.icon("descargar.png", 18, 18));
        btnreporteVenDia.setBounds(0, 180, 200, 35);
        btnreporteVenDia.setBorder(BorderFactory.createLineBorder(new Color(217, 217, 217), 2, true));
        btnreporteVenDia.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == btnreporteCXP) generarReporteCXP();
        else if (src == btnreporteCxC) generarReporteCXC();
        else if (src == btnreporteInv) generarReporteInv();
        else if (src == btnreporteVenDia) generarReporteVenDia();
    }

    private void generarReporteCXP() {
       new FormFactory(context).crearForm(context.getNavigation().getModuloActual(), SubModulo.REPORTE_CXP, OperationType.CREATE, dahsboard).setVisible(true);
    }

    private void generarReporteCXC() {
        new FormFactory(context).crearForm(context.getNavigation().getModuloActual(), SubModulo.REPORTE_CXC, OperationType.CREATE, dahsboard).setVisible(true);
    }

    private void generarReporteInv() {
       new FormFactory(context).crearForm(context.getNavigation().getModuloActual(), SubModulo.REPORTE_INV, OperationType.CREATE, dahsboard).setVisible(true);
    }

    private void generarReporteVenDia() {
        new FormFactory(context).crearForm(context.getNavigation().getModuloActual(), SubModulo.REPORTE_VEN, OperationType.CREATE, dahsboard).setVisible(true);
    }

}