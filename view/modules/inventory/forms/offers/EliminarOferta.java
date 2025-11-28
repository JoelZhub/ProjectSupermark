package view.modules.inventory.forms.offers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Oferta;

import javax.swing.JLabel;
import javax.swing.JComboBox;

public class EliminarOferta extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	
	public static void main(String[] args) {
		try {
			EliminarOferta dialog = new EliminarOferta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public EliminarOferta() {
		setBounds(100, 100, 450, 253);
		setBackground(new Color(56,56,56));
		getContentPane().setBackground(new Color(56,56,56));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(56,56,56));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbEliminarOferta = new JLabel("Desactivar Oferta");
			lbEliminarOferta.setBounds(10, 24, 186, 21);
			contentPanel.add(lbEliminarOferta);
		}
		{
			JLabel lbOferta = new JLabel("Oferta");
			lbOferta.setBounds(10, 92, 97, 12);
			contentPanel.add(lbOferta);
		}
		{
			JComboBox<Oferta> selectOferta = new JComboBox<>();
			selectOferta.setBounds(10, 129, 404, 26);
			contentPanel.add(selectOferta);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(56,56,56));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnGuardar = new JButton("Guardar");
				btnGuardar.setActionCommand("OK");
				buttonPane.add(btnGuardar);
				getRootPane().setDefaultButton(btnGuardar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}

}
