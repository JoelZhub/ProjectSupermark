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

import model.CuentaPagar;
import view.AplicationContext;
import view.components.BtnStyle;
import view.components.Filtros;
import view.components.Fonts;
import view.components.Messages;
import view.dashboard.Dahsboard;

public class CrearCuentaPagar extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldIdCompra;
    private JTextField textFieldIdProveedor;
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

    public CrearCuentaPagar(AplicationContext context, Dahsboard dahsboard) {
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
            JLabel lbCrear = new JLabel("Crear Cuenta por Pagar");
            lbCrear.setBounds(20, 22, 265, 44);
            lbCrear.setForeground(Color.WHITE);
            lbCrear.setFont(Fonts.bold);
            contentPanel.add(lbCrear);
        }
        {
            JLabel lbIdCompra = new JLabel("ID Compra");
            lbIdCompra.setFont(Fonts.custom);
            lbIdCompra.setForeground(Color.WHITE);
            lbIdCompra.setBounds(20, 97, 157, 23);
            contentPanel.add(lbIdCompra);
        }
        {
            textFieldIdCompra = new JTextField();
            textFieldIdCompra.setBounds(20, 127, 301, 26);
            contentPanel.add(textFieldIdCompra);
            textFieldIdCompra.setColumns(10);
            Filtros.aplicarFiltroNumericoTextField(textFieldIdCompra);
        }
        {
            JLabel lbIdProveedor = new JLabel("ID Proveedor");
            lbIdProveedor.setBounds(350, 97, 157, 23);
            lbIdProveedor.setForeground(Color.WHITE);
            lbIdProveedor.setFont(Fonts.custom);
            contentPanel.add(lbIdProveedor);
        }
        {
            textFieldIdProveedor = new JTextField();
            textFieldIdProveedor.setBounds(350, 127, 294, 26);
            textFieldIdProveedor.setFont(Fonts.custom);
            contentPanel.add(textFieldIdProveedor);
            Filtros.aplicarFiltroNumericoTextField(textFieldIdProveedor);
            textFieldIdProveedor.setColumns(10);
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
            if (textFieldIdCompra.getText().trim().isEmpty() || !textFieldIdCompra.getText().trim().matches("\\d+")) {
                new Messages(dahsboard, "Ingrese un ID de compra válido").messageError();
                return;
            }
            if (textFieldIdProveedor.getText().trim().isEmpty() || !textFieldIdProveedor.getText().trim().matches("\\d+")) {
                new Messages(dahsboard, "Ingrese un ID de proveedor válido").messageError();
                return;
            }
            if (textFieldMonto.getText().trim().isEmpty()) {
                new Messages(dahsboard, "Ingrese el monto").messageError();
                return;
            }
            try {
                int idCompra = Integer.parseInt(textFieldIdCompra.getText().trim());
                int idProveedor = Integer.parseInt(textFieldIdProveedor.getText().trim());
                double monto = Double.parseDouble(textFieldMonto.getText().trim());
                if (monto <= 0) {
                    new Messages(dahsboard, "El monto debe ser mayor que cero").messageError();
                    return;
                }
                CuentaPagar cp = new CuentaPagar(idCompra, idProveedor, monto, monto, "PENDIENTE");
                boolean ok = false;
                try {
                    String res = context.getCuentaPagarController().registrarCuenta(cp);
                    new Messages(dahsboard, res).messageAlert();
                    ok = true;
                } catch (Exception ex) {
                    new Messages(dahsboard, "Error al crear la cuenta").messageError();
                }
                if (ok) limpiarCampos();
                if (ok) this.dispose();
            } catch (NumberFormatException ex) {
                new Messages(dahsboard, "Valores numéricos inválidos").messageError();
            }
        }
    }

    private void limpiarCampos() {
        textFieldIdCompra.setText("");
        textFieldIdProveedor.setText("");
        textFieldMonto.setText("");
    }
}