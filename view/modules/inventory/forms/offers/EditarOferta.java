package view.modules.inventory.forms.offers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JRadioButton;

public class EditarOferta extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldOferta;
	private JTextField textFieldMonto;

	
	public static void main(String[] args) {
		try {
			EditarOferta dialog = new EditarOferta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public EditarOferta() {
		setBounds(100, 100, 620, 390);
		setBackground(new Color(56,56,56));
		getContentPane().setBackground(new Color(56,56,56));
		contentPanel.setBackground(new Color(56,56,56));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbEditarOferta = new JLabel("Editar Oferta");
			lbEditarOferta.setBounds(21, 23, 163, 32);
			contentPanel.add(lbEditarOferta);
		}
		{
			JLabel lbOferta = new JLabel("Oferta");
			lbOferta.setBounds(21, 78, 126, 18);
			contentPanel.add(lbOferta);
		}
		{
			textFieldOferta = new JTextField();
			textFieldOferta.setBounds(21, 120, 254, 26);
			contentPanel.add(textFieldOferta);
			textFieldOferta.setColumns(10);
		}
		{
			JLabel lbMonto = new JLabel("Monto");
			lbMonto.setBounds(21, 166, 126, 18);
			contentPanel.add(lbMonto);
		}
		{
			textFieldMonto = new JTextField();
			textFieldMonto.setColumns(10);
			textFieldMonto.setBounds(21, 208, 254, 26);
			contentPanel.add(textFieldMonto);
		}
		{
			JLabel lbFechaInicio = new JLabel("Fecha Inicio");
			lbFechaInicio.setBounds(312, 78, 126, 18);
			contentPanel.add(lbFechaInicio);
		}
		
		DatePicker dateFechaInicio = new DatePicker();
		dateFechaInicio.setBounds(312, 120, 230, 26);
		contentPanel.add(dateFechaInicio);
		
		DatePicker dateFechaFin = new DatePicker();
		dateFechaFin.setBounds(312, 208, 230, 26);
		contentPanel.add(dateFechaFin);
		
		JLabel lbFechaFin = new JLabel("Fecha Fin");
		lbFechaFin.setBounds(312, 166, 126, 18);
		contentPanel.add(lbFechaFin);
		
		JLabel lbEstado = new JLabel("Estado");
		lbEstado.setBounds(21, 254, 126, 12);
		contentPanel.add(lbEstado);
		
		JRadioButton btnRActivaOferta = new JRadioButton("Activa");
		btnRActivaOferta.setBounds(21, 285, 102, 20);
		contentPanel.add(btnRActivaOferta);
		
		JRadioButton btnRInactivaOferta = new JRadioButton("Inactiva");
		btnRInactivaOferta.setBounds(141, 286, 102, 20);
		contentPanel.add(btnRInactivaOferta);
		{
			JPanel buttonPane = new JPanel();
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
