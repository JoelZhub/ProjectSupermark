package view.modules.inventory.forms.supplier;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Proveedor;
import view.AplicationContext;
import view.components.BtnStyle;
import view.components.Filtros;
import view.components.Fonts;
import view.components.Messages;
import view.dashboard.Dahsboard;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class CrearProveedor extends JDialog  implements ActionListener, MouseListener {

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
	private Proveedor proveedor;
	
	@SuppressWarnings("unused")
	private final AplicationContext context;
	private final Dahsboard dahsboard;
	
	public static void main(String[] args) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public CrearProveedor(AplicationContext context, Dahsboard dahsboard) {
		
		this.context = context;
		this.dahsboard = dahsboard;
		setBounds(100, 100, 698, 544);
		setBackground(new Color(56,56,56));
		setResizable(false);
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
			Filtros.aplicarFiltroSoloLetras(textFieldNombre);
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
			Filtros.aplicarFiltroTelefono(textFieldTelefono);
			textFieldTelefono.setColumns(10);
			textFieldTelefono.addMouseListener(this);
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
			Filtros.aplicarFiltroNumericoTextField(textFieldRnc);
			textFieldRnc.addMouseListener(this);
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
			Filtros.aplicarFiltroSoloLetras(textFieldCiudad);
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
			Filtros.aplicarFiltroSoloLetras(textFieldPais);
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
					
					proveedor = new Proveedor(
							textFieldRnc.getText(),
							textFieldNombre.getText(),
							textFieldTelefono.getText(),
							textFieldCorreo.getText(),
							textFieldCalle.getText(),
							textFieldCiudad.getText(),
							textFieldPais.getText()
							);
					
					if(context.getProveedorController().validarDatosProveedores(proveedor)) {
						
						boolean existe = context.getProveedorController().listarProveedores()
								.stream()
								.anyMatch(p -> p.getRncProveedor() == proveedor.getRncProveedor());
						
						if(existe) {
							new Messages(dahsboard, "Ya existe un producto con el RNX" + proveedor.getRncProveedor()).messageError();
							return;
						}else {
						
							if(context.getProveedorController().insertarProveedor(proveedor)) {
								new Messages(dahsboard, "Proveedor creado con exito").messageAlert();
								return;
								
							}else {
								new Messages(dahsboard, "Error al crear el proveedor").messageError();
								return;
							}
							
						}
					}else {
						
						new Messages(dahsboard, "Rellene los campos de manera correcta").messageError();
						return;
					}
					
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


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == textFieldRnc) {
			if(textFieldRnc.getText().length() < 9) {
				new Messages(dahsboard, "Ingrese un RNC valido de 9 digitos").messageError();
				return;
			}
			
		}
		if(e.getSource() == textFieldTelefono) {
			
		
		}
	}

}
