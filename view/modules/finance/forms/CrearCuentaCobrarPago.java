package view.modules.finance.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import view.AplicationContext;
import view.components.BtnStyle;
import view.components.Filtros;
import view.components.Fonts;
import view.components.Messages;
import view.dashboard.Dahsboard;

public class CrearCuentaCobrarPago extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldIdCuenta;
    private JTextField textFieldFecha;
    private JTextField textFieldMonto;
    @SuppressWarnings("unused")
    private JButton btnGuardar, btnCancelar;
    private final AplicationContext context;
    @SuppressWarnings("unused")
    private final Dahsboard dahsboard;

    public static void main(String[] args) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CrearCuentaCobrarPago(AplicationContext context, Dahsboard dahsboard) {
        this.context = context;
        this.dahsboard = dahsboard;
        setBounds(100, 100, 730, 449);
        setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\img\\iconApp.png"));
        setBackground(new Color(56,56,56));
        getContentPane().setBackground(new Color(56,56,56));
        contentPanel.setBackground(new Color(56,56,56));
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        contentPanel.setLayout(null);
        {
            JLabel lbCrear = new JLabel("Registrar Pago - Cuenta por Cobrar");
            lbCrear.setBounds(20, 22, 400, 44);
            lbCrear.setForeground(Color.WHITE);
            lbCrear.setFont(Fonts.bold);
            contentPanel.add(lbCrear);
        }
        {
            JLabel lbIdCuenta = new JLabel("ID Cuenta");
            lbIdCuenta.setFont(Fonts.custom);
            lbIdCuenta.setForeground(Color.WHITE);
            lbIdCuenta.setBounds(20, 97, 157, 23);
            contentPanel.add(lbIdCuenta);
        }
        {
            textFieldIdCuenta = new JTextField();
            textFieldIdCuenta.setBounds(20, 127, 301, 26);
            contentPanel.add(textFieldIdCuenta);
            textFieldIdCuenta.setColumns(10);
            Filtros.aplicarFiltroNumericoTextField(textFieldIdCuenta);
        }
        {
            JLabel lbFecha = new JLabel("Fecha (dd/MM/yyyy)");
            lbFecha.setBounds(350, 97, 200, 23);
            lbFecha.setForeground(Color.WHITE);
            lbFecha.setFont(Fonts.custom);
            contentPanel.add(lbFecha);
        }
        {
            textFieldFecha = new JTextField();
            textFieldFecha.setBounds(350, 127, 294, 26);
            textFieldFecha.setFont(Fonts.custom);
            contentPanel.add(textFieldFecha);
            textFieldFecha.setColumns(10);
        }
        {
            JLabel lbMonto = new JLabel("Monto");
            lbMonto.setFont(Fonts.custom);
            lbMonto.setForeground(Color.WHITE);
            lbMonto.setBounds(20, 182, 157, 23);
            contentPanel.add(lbMonto);
        }
        {
            textFieldMonto = new JTextField();
            Filtros.aplicarFiltroNumericoTextField(textFieldMonto);
            textFieldMonto.setColumns(10);
            textFieldMonto.setBounds(20, 212, 627, 26);
            contentPanel.add(textFieldMonto);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBackground(Color.WHITE);
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                btnGuardar = new JButton("Guardar");
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
        if (e.getSource() == btnGuardar) {
            if (textFieldIdCuenta.getText().trim().isEmpty() || !textFieldIdCuenta.getText().trim().matches("\\d+")) {
                new Messages(dahsboard, "Ingrese un ID de cuenta válido").messageError();
                return;
            }
            if (textFieldFecha.getText().trim().isEmpty()) {
                new Messages(dahsboard, "Ingrese la fecha del pago").messageError();
                return;
            }
            if (textFieldMonto.getText().trim().isEmpty()) {
                new Messages(dahsboard, "Ingrese el monto").messageError();
                return;
            }
            try {
                int idCuenta = Integer.parseInt(textFieldIdCuenta.getText().trim());
                String fecha = textFieldFecha.getText().trim();
                double monto = Double.parseDouble(textFieldMonto.getText().trim());
                if (monto <= 0) {
                    new Messages(dahsboard, "El monto debe ser mayor que cero").messageError();
                    return;
                }
                boolean ok = false;
                try {
                    String res = context.getCuentaCobrarController().registrarPago(idCuenta, fecha, monto);
                    new Messages(dahsboard, res).messageAlert();
                    ok = true;
                } catch (Exception ex) {
                    new Messages(dahsboard, "Error al registrar el pago").messageError();
                }
                if (ok) limpiarCampos();
                if (ok) this.dispose();
            } catch (NumberFormatException ex) {
                new Messages(dahsboard, "Valores numéricos inválidos").messageError();
            }
        }
    }

    private void limpiarCampos() {
        textFieldIdCuenta.setText("");
        textFieldFecha.setText("");
        textFieldMonto.setText("");
    }
}