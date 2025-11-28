package view.modules.inventory.forms.supplier;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class EditarProveedor extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JPanel buttonPane;
	private JTextField textFieldNombre;
	private JTextField textFieldTelefono;
	private JTextField textFieldCorreo;
	private JTextField textFieldDireccion;
	private JTextField textFieldId;
	
	public static void main(String[] args) {
		try {
			EditarProveedor dialog = new EditarProveedor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public EditarProveedor() {
		
		setBounds(100, 100, 698, 452);
		setBackground(new Color(56,56,56));
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\img\\iconApp.png"));
		getContentPane().setBackground(new Color(56,56,56));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(56,56,56));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbProveedor = new JLabel("Crear Proveedor");
			lbProveedor.setBounds(10, 35, 338, 21);
			contentPanel.add(lbProveedor);
		}
		{
			JLabel lbNombre = new JLabel("Nombre");
			lbNombre.setBounds(10, 197, 99, 13);
			contentPanel.add(lbNombre);
		}
		{
			textFieldNombre = new JTextField();
			textFieldNombre.setBounds(10, 236, 290, 26);
			contentPanel.add(textFieldNombre);
			textFieldNombre.setColumns(10);
		}
		{
			textFieldTelefono = new JTextField();
			textFieldTelefono.setColumns(10);
			textFieldTelefono.setBounds(10, 329, 290, 26);
			contentPanel.add(textFieldTelefono);
		}
		{
			JLabel lbTelefono = new JLabel("Teléfono ");
			lbTelefono.setBounds(10, 290, 99, 13);
			contentPanel.add(lbTelefono);
		}
		{
			textFieldCorreo = new JTextField();
			textFieldCorreo.setColumns(10);
			textFieldCorreo.setBounds(356, 143, 290, 26);
			contentPanel.add(textFieldCorreo);
		}
		{
			JLabel lbCorreo = new JLabel("Correo");
			lbCorreo.setBounds(356, 104, 99, 13);
			contentPanel.add(lbCorreo);
		}
		{
			textFieldDireccion = new JTextField();
			textFieldDireccion.setColumns(10);
			textFieldDireccion.setBounds(356, 236, 290, 26);
			contentPanel.add(textFieldDireccion);
		}
		{
			JLabel lbDireccion = new JLabel("Dirección ");
			lbDireccion.setBounds(356, 197, 99, 13);
			contentPanel.add(lbDireccion);
		}
		{
			textFieldId = new JTextField();
			textFieldId.setColumns(10);
			textFieldId.setBounds(10, 143, 290, 26);
			contentPanel.add(textFieldId);
		}
		{
			JLabel IdProveedor = new JLabel("Imgrese el Id del Proveedor");
			IdProveedor.setBounds(10, 104, 290, 13);
			contentPanel.add(IdProveedor);
		}
		{
			buttonPane = new JPanel();
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
