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

import view.components.BtnStyle;
import view.components.Fonts;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class CrearProveedor extends JDialog  implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane;
	private JTextField textFieldNombre;
	private JTextField textFieldCorreo;
	private JTextField textFieldTelefono;
	private JTextField textFieldCalle;
	private JTextField textFieldRnc;
	private JTextField textFieldCiudad;
	private JTextField textFieldPais;

	public static void main(String[] args) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public CrearProveedor() {
		setBounds(100, 100, 698, 544);
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
			lbProveedor.setForeground(Color.WHITE);
			lbProveedor.setFont(Fonts.bold);
			lbProveedor.setBounds(10, 35, 338, 21);
			contentPanel.add(lbProveedor);
		}
		{
			JLabel lbNombre = new JLabel("Nombre");
			lbNombre.setBounds(356, 104, 99, 13);
			lbNombre.setForeground(Color.WHITE);
			lbNombre.setFont(Fonts.custom);
			contentPanel.add(lbNombre);
		}
		{
			textFieldNombre = new JTextField();
			textFieldNombre.setBounds(356, 143, 290, 26);
			contentPanel.add(textFieldNombre);
			textFieldNombre.setColumns(10);
		}
		{
			textFieldCorreo = new JTextField();
			textFieldCorreo.setColumns(10);
			textFieldCorreo.setBounds(10, 237, 290, 26);
			contentPanel.add(textFieldCorreo);
		}
		{
			JLabel lbCorreo = new JLabel("Correo");
			lbCorreo.setBounds(10, 198, 99, 13);
			lbCorreo.setForeground(Color.WHITE);
			lbCorreo.setFont(Fonts.custom);
			contentPanel.add(lbCorreo);
		}
		{
			textFieldTelefono = new JTextField();
			textFieldTelefono.setColumns(10);
			textFieldTelefono.setBounds(356, 237, 290, 26);
			contentPanel.add(textFieldTelefono);
		}
		{
			JLabel lbTelefono = new JLabel("Teléfono ");
			lbTelefono.setBounds(356, 198, 99, 13);
			lbTelefono.setFont(Fonts.custom);
			lbTelefono.setForeground(Color.WHITE);
			contentPanel.add(lbTelefono);
		}
		{
			textFieldCalle = new JTextField();
			textFieldCalle.setColumns(10);
			textFieldCalle.setBounds(10, 330, 290, 26);
			contentPanel.add(textFieldCalle);
		}
		{
			JLabel lbCalle = new JLabel("Calle");
			lbCalle.setBounds(10, 291, 99, 13);
			lbCalle.setForeground(Color.WHITE);
			lbCalle.setFont(Fonts.custom);
			contentPanel.add(lbCalle);
		}
		{
			textFieldRnc = new JTextField();
			textFieldRnc.setColumns(10);
			textFieldRnc.setBounds(10, 143, 290, 26);
			contentPanel.add(textFieldRnc);
		}
		{
			JLabel lbRnc = new JLabel("Rnc");
			lbRnc.setBounds(10, 104, 99, 13);
			lbRnc.setForeground(Color.WHITE);
			lbRnc.setFont(Fonts.custom);
			contentPanel.add(lbRnc);
		}
		{
			textFieldCiudad = new JTextField();
			textFieldCiudad.setColumns(10);
			textFieldCiudad.setBounds(356, 330, 290, 26);
			contentPanel.add(textFieldCiudad);
		}
		{
			JLabel lblCiudad = new JLabel("Ciudad");
			lblCiudad.setBounds(356, 291, 99, 13);
			lblCiudad.setFont(Fonts.custom);
			lblCiudad.setForeground(Color.WHITE);
			contentPanel.add(lblCiudad);
		}
		{
			textFieldPais = new JTextField();
			textFieldPais.setColumns(10);
			textFieldPais.setBounds(10, 414, 290, 26);
			contentPanel.add(textFieldPais);
		}
		{
			JLabel lbPais = new JLabel("Pais");
			lbPais.setBounds(10, 375, 99, 13);
			lbPais.setForeground(Color.WHITE);
			lbPais.setFont(Fonts.custom);
			contentPanel.add(lbPais);
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnGuardar = new JButton("Guardar");
				BtnStyle.primary(btnGuardar, new Color(56,56,56));
				btnGuardar.addActionListener(e -> {
					
					
					
				});
				btnGuardar.setFont(Fonts.custom);
				buttonPane.add(btnGuardar);
				
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setFont(Fonts.custom);
				btnCancelar.addActionListener(e -> this.dispose());
				BtnStyle.primary(btnCancelar, new Color(56,56,56));
				buttonPane.add(btnCancelar);
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
