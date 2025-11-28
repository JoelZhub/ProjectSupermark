package view.modules.inventory.forms.supplier;

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
import model.Proveedor;
import view.components.BtnStyle;
import view.components.Fonts;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class EliminarProveedor extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	
	public static void main(String[] args) {
		try {
			EliminarProveedor dialog = new EliminarProveedor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EliminarProveedor() {
		setBounds(100, 100, 450, 236);
		setBackground(new Color(56,56,56));
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\img\\iconApp.png"));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(56,56,56));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbEliminar = new JLabel("Bloquear Proveedor");
			lbEliminar.setBounds(10, 34, 175, 12);
			lbEliminar.setFont(Fonts.bold);
			lbEliminar.setForeground(Color.WHITE);
			contentPanel.add(lbEliminar);
		}
		{
			JLabel lbProveedor = new JLabel("Proveedor");
			lbProveedor.setForeground(Color.WHITE);
			lbProveedor.setFont(Fonts.custom);
			lbProveedor.setBounds(10, 98, 95, 18);
			contentPanel.add(lbProveedor);
		}
		{
			JComboBox<Proveedor> comboBox = new JComboBox<>();
			comboBox.setSelectedItem(null);
			comboBox.setBounds(125, 94, 276, 26);
			contentPanel.add(comboBox);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(56,56,56));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnGuardar = new JButton("Guardar");
				btnGuardar.setFont(Fonts.custom);
				btnGuardar.addActionListener(this);
				BtnStyle.primary(btnGuardar, Color.BLACK);
				buttonPane.add(btnGuardar);
				
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setFont(Fonts.custom);
				btnCancelar.addActionListener(e -> this.dispose());
				BtnStyle.primary(btnCancelar, Color.BLACK);
				buttonPane.add(btnCancelar);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
