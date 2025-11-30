package view.modules.report.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.ReporteCuentasCobrarController;
import control.ReporteVentasDiariasController;

import javax.swing.JLabel;
import javax.swing.JTextField;

import model.ReporteCuentasCobrar;
import model.ReporteVentasDiarias;
import view.AplicationContext;
import view.components.BtnStyle;
import view.components.Filtros;
import view.components.Fonts;
import view.components.Messages;
import view.dashboard.Dahsboard;

public class CrearReporteCXC extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    @SuppressWarnings("unused")
    private JButton btnGuardar, btnCancelar;
    private final AplicationContext context;
    @SuppressWarnings("unused")
    private final Dahsboard dahsboard;
    private ReporteCuentasCobrarController reporteCuentaCobrarController;

    public static void main(String[] args) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CrearReporteCXC(AplicationContext context, Dahsboard dahsboard) {
        this.context = context;
        this.dahsboard = dahsboard;
        this.reporteCuentaCobrarController = context.getReportCXCController();

        setModal(true);
        setResizable(false);
        setBounds(100, 100, 730, 449);
        Point p = dahsboard.getLocationOnScreen();
        setLocation(p.x + 40, p.y + 40);
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\img\\iconApp.png"));
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(56,56,56));

        contentPanel.setBackground(new Color(56,56,56));
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        contentPanel.setLayout(null);
        {
            JLabel lbCrear = new JLabel("Crear Reporte Cuentas Por Cobrar");
            lbCrear.setBounds(20, 22, 300, 44);
            lbCrear.setForeground(Color.WHITE);
            lbCrear.setFont(Fonts.bold);
            contentPanel.add(lbCrear);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(Color.WHITE);
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                btnGuardar = new JButton("Crear");
                btnGuardar.setForeground(Color.WHITE);
                btnGuardar.setFont(Fonts.custom);
                btnGuardar.addActionListener(this);
                BtnStyle.primary(btnGuardar, new Color(0, 0, 0));
                buttonPane.add(btnGuardar);
            }
            {
                btnCancelar = new JButton("Cancelar");
                btnCancelar.setFont(Fonts.custom);
                btnCancelar.setForeground(Color.WHITE);
                btnCancelar.addActionListener(e -> this.dispose());
                BtnStyle.primary(btnCancelar, new Color(0, 0, 0));
                buttonPane.add(btnCancelar);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object src = e.getSource();
        if (src instanceof JButton && "Crear".equals(((JButton) src).getText())) {
            try { 
                String fecha = LocalDate.now().toString();
                String RUTA = "reportes/reporte_cuentas_cobrar"  + fecha + ".pdf";
                ReporteCuentasCobrar reporte = reporteCuentaCobrarController.generarReporte();
                boolean ok = false;
                try {
                    reporte.exportarPDF(RUTA);
                    new Messages(dahsboard, "PDF generado correctamente.").messageAlert();
                    ok = true;
                } catch (Exception ex) {
                    new Messages(dahsboard, "Error al crear el reporte: " + ex.getMessage()).messageError();
                }
                if (ok) this.dispose();
            } catch (NumberFormatException ex) {
                new Messages(dahsboard, "Valores de fecha").messageError();
            }
        }
    }
}