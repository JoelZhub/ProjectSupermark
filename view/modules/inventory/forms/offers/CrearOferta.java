package view.modules.inventory.forms.offers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import com.github.lgooddatepicker.components.DatePicker;

import model.Producto;

public class CrearOferta extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombre;
	private JTextField textFieldMonto;

	
	public static void main(String[] args) {
		try {
			CrearOferta dialog = new CrearOferta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public CrearOferta() {
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
			JLabel lbCrearOferta = new JLabel("Crear Oferta");
			lbCrearOferta.setBounds(20, 43, 175, 23);
			contentPanel.add(lbCrearOferta);
		}
		{
			JLabel lbNombreOferta = new JLabel("Nombre Oferta");
			lbNombreOferta.setBounds(20, 97, 157, 23);
			contentPanel.add(lbNombreOferta);
		}
		{
			textFieldNombre = new JTextField();
			textFieldNombre.setBounds(20, 151, 301, 26);
			contentPanel.add(textFieldNombre);
			textFieldNombre.setColumns(10);
		}
		{
			JLabel lbProducto = new JLabel("Producto");
			lbProducto.setBounds(350, 97, 157, 23);
			contentPanel.add(lbProducto);
		}
		{
			JComboBox<Producto> comboBox = new JComboBox<>();
			comboBox.setBounds(350, 151, 294, 26);
			contentPanel.add(comboBox);
		}
		{
			JLabel lbMonto = new JLabel("Monto");
			lbMonto.setBounds(20, 197, 157, 23);
			contentPanel.add(lbMonto);
		}
		{
			textFieldMonto = new JTextField();
			textFieldMonto.setColumns(10);
			textFieldMonto.setBounds(20, 243, 627, 26);
			contentPanel.add(textFieldMonto);
		}
		{
			JLabel lbFechaInicio = new JLabel("Fecha Inicio");
			lbFechaInicio.setBounds(20, 293, 157, 23);
			contentPanel.add(lbFechaInicio);
		}
		
		DatePicker dateFechaInicio = new DatePicker();
		dateFechaInicio.setBounds(20, 335, 286, 26);
		contentPanel.add(dateFechaInicio);
		
		DatePicker dateFechaFin = new DatePicker();
		dateFechaFin.setBounds(350, 335, 286, 26);
		contentPanel.add(dateFechaFin);
		
		JLabel lbFechaInicio = new JLabel("Fecha Inicio");
		lbFechaInicio.setBounds(350, 293, 157, 23);
		contentPanel.add(lbFechaInicio);
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
