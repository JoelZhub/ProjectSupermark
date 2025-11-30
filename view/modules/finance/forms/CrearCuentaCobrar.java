package view.modules.finance.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.CuentaCobrar;
import view.AplicationContext;
import view.components.BtnStyle;
import view.components.Filtros;
import view.components.Fonts;
import view.components.Messages;
import view.dashboard.Dahsboard;

public class CrearCuentaCobrar extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldIdCliente;
    private JTextField textFieldIdFactura;
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

    public CrearCuentaCobrar(AplicationContext context, Dahsboard dahsboard) {
        this.context = context;
        this.dahsboard = dahsboard;
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
            JLabel lbCrear = new JLabel("Crear Cuenta por Cobrar");
            lbCrear.setBounds(20, 22, 300, 44);
            lbCrear.setForeground(Color.WHITE);
            lbCrear.setFont(Fonts.bold);
            contentPanel.add(lbCrear);
        }
        {
            JLabel lbIdCliente = new JLabel("ID Cliente / Identificador");
            lbIdCliente.setFont(Fonts.custom);
            lbIdCliente.setForeground(Color.WHITE);
            lbIdCliente.setBounds(20, 97, 200, 23);
            contentPanel.add(lbIdCliente);
        }
        {
            textFieldIdCliente = new JTextField();
            textFieldIdCliente.setBounds(20, 127, 301, 26);
            contentPanel.add(textFieldIdCliente);
            textFieldIdCliente.setColumns(10);
        }
        {
            JLabel lbIdFactura = new JLabel("ID Factura / Venta");
            lbIdFactura.setBounds(350, 97, 157, 23);
            lbIdFactura.setForeground(Color.WHITE);
            lbIdFactura.setFont(Fonts.custom);
            contentPanel.add(lbIdFactura);
        }
        {
            textFieldIdFactura = new JTextField();
            textFieldIdFactura.setBounds(350, 127, 294, 26);
            textFieldIdFactura.setFont(Fonts.custom);
            contentPanel.add(textFieldIdFactura);
            Filtros.aplicarFiltroNumericoTextField(textFieldIdFactura);
            textFieldIdFactura.setColumns(10);
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
        Object src = e.getSource();
        if (src instanceof JButton && "Guardar".equals(((JButton) src).getText())) {
            if (textFieldIdCliente.getText().trim().isEmpty()) {
                new Messages(dahsboard, "Ingrese identificador del cliente").messageError();
                return;
            }
            if (textFieldIdFactura.getText().trim().isEmpty() || !textFieldIdFactura.getText().trim().matches("\\d+")) {
                new Messages(dahsboard, "Ingrese un ID de factura/venta válido").messageError();
                return;
            }
            if (textFieldMonto.getText().trim().isEmpty()) {
                new Messages(dahsboard, "Ingrese el monto").messageError();
                return;
            }
            try {
                String idCliente = textFieldIdCliente.getText().trim();
                int idFactura = Integer.parseInt(textFieldIdFactura.getText().trim());
                double monto = Double.parseDouble(textFieldMonto.getText().trim());
                if (monto <= 0) {
                    new Messages(dahsboard, "El monto debe ser mayor que cero").messageError();
                    return;
                }
                CuentaCobrar cc = new CuentaCobrar(idCliente, idFactura, monto, monto, "PENDIENTE");
                boolean ok = false;
                try {
                    String res = context.getCuentaCobrarController().registrarCuenta(cc);
                    new Messages(dahsboard, res).messageAlert();
                    ok = true;
                } catch (Exception ex) {
                    new Messages(dahsboard, "Error al crear la cuenta: " + ex.getMessage()).messageError();
                }
                if (ok) limpiarCampos();
                if (ok) this.dispose();
            } catch (NumberFormatException ex) {
                new Messages(dahsboard, "Valores numéricos inválidos").messageError();
            }
        }
    }

    private void limpiarCampos() {
        textFieldIdCliente.setText("");
        textFieldIdFactura.setText("");
        textFieldMonto.setText("");
    }
}