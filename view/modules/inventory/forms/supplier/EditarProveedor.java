package view.modules.inventory.forms.supplier;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Proveedor;
import view.AplicationContext;
import view.components.BtnStyle;
import view.components.Filtros;
import view.components.Fonts;
import view.components.Messages;
import view.dashboard.Dahsboard;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class EditarProveedor extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JPanel buttonPane;
	private JTextField textFieldNombre;
	private JTextField textFieldTelefono;
	private JTextField textFieldRnc;
	private JTextField textFieldCorreo;
	private JTextField textFieldCalle;
	private JTextField textFieldCiudad;
	private JTextField textFieldPais;
	private JRadioButton btnEstadoD , btnEstadoA;
	private JComboBox<Proveedor> seleccionarProveedor;
	@SuppressWarnings("unused")
	private final AplicationContext context;
	@SuppressWarnings("unused")
	private final Dahsboard dahsboard;
	private JButton btnGuardar;
	private Proveedor p;
	private int id;
	public static void main(String[] args) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public EditarProveedor(AplicationContext context, Dahsboard dahsboard) {
		
		this.context = context;
		this.dahsboard = dahsboard;
		setBounds(100, 100, 994, 491);
		setResizable(false);
		setBackground(new Color(56,56,56));
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\img\\iconApp.png"));
		getContentPane().setBackground(new Color(56,56,56));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(new Color(56,56,56));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbProveedor = new JLabel("Editar proveedor");
			lbProveedor.setFont(Fonts.bold);
			lbProveedor.setForeground(Color.WHITE);
			lbProveedor.setBounds(10, 35, 338, 21);
			contentPanel.add(lbProveedor);
		}
		{
			JLabel lbNombre = new JLabel("Nombre");
			lbNombre.setBounds(669, 104, 99, 13);
			lbNombre.setFont(Fonts.custom);
			lbNombre.setForeground(Color.WHITE);
			contentPanel.add(lbNombre);
		}
		{
			textFieldNombre = new JTextField();
			Filtros.aplicarFiltroSoloLetras(textFieldNombre);
			textFieldNombre.setBounds(669, 143, 290, 26);
			contentPanel.add(textFieldNombre);
			textFieldNombre.setColumns(10);
		}
		{
			textFieldTelefono = new JTextField();
			//Filtros.aplicarFiltroTelefono(textFieldTelefono);
			textFieldTelefono.setColumns(10);
			textFieldTelefono.setBounds(10, 230, 290, 26);
			contentPanel.add(textFieldTelefono);
		}
		{
			JLabel lbTelefono = new JLabel("Teléfono ");
			lbTelefono.setBounds(10, 191, 99, 13);
			lbTelefono.setFont(Fonts.custom);
			lbTelefono.setForeground(Color.WHITE);
			contentPanel.add(lbTelefono);
		}
		{
			textFieldRnc = new JTextField();
			Filtros.aplicarFiltroNumericoTextField(textFieldRnc);
			textFieldRnc.setColumns(10);
			textFieldRnc.setBounds(356, 143, 290, 26);
			contentPanel.add(textFieldRnc);
		}
		{
			JLabel lbRnc = new JLabel("Rnc");
			lbRnc.setBounds(356, 104, 99, 13);
			lbRnc.setFont(Fonts.custom);
			lbRnc.setForeground(Color.WHITE);
			contentPanel.add(lbRnc);
		}
		{
			textFieldCorreo = new JTextField();
			textFieldCorreo.setColumns(10);
			textFieldCorreo.setBounds(356, 230, 290, 26);
			contentPanel.add(textFieldCorreo);
		}
		{
			JLabel lbCorreo = new JLabel("Correo");
			lbCorreo.setBounds(356, 191, 99, 13);
			lbCorreo.setFont(Fonts.custom);
			lbCorreo.setForeground(Color.WHITE);
			contentPanel.add(lbCorreo);
		}
		{
			JLabel proveedorSeleccionado = new JLabel("Seleccione el proveedor a editar");
			proveedorSeleccionado.setBounds(10, 104, 290, 13);
			proveedorSeleccionado.setFont(Fonts.custom);
			proveedorSeleccionado.setForeground(Color.WHITE);
			contentPanel.add(proveedorSeleccionado);
			
		}
		{
			seleccionarProveedor = new JComboBox<>();
			for(Proveedor p : context.getProveedorController().listarProveedores() ) {
				seleccionarProveedor.addItem(p);
			}
			seleccionarProveedor.setSelectedItem(null);
			seleccionarProveedor.addActionListener(this);
			seleccionarProveedor.setBounds(10, 143, 290, 26);
			contentPanel.add(seleccionarProveedor);
		}
		{
			textFieldCalle = new JTextField();
			textFieldCalle.setBounds(669, 230, 290, 26);
			contentPanel.add(textFieldCalle);
		}
		{
			JLabel lbCalle = new JLabel("Calle");
			lbCalle.setBounds(669, 191, 99, 13);
			lbCalle.setFont(Fonts.custom);
			lbCalle.setForeground(Color.WHITE);
			contentPanel.add(lbCalle);
		}
		{
			textFieldCiudad = new JTextField();
			Filtros.aplicarFiltroSoloLetras(textFieldCiudad);
			textFieldCiudad.setBounds(10, 327, 290, 26);
			contentPanel.add(textFieldCiudad);
		}
		{
			JLabel lbCiudad = new JLabel("Ciudad");
			lbCiudad.setBounds(10, 288, 99, 13);
			lbCiudad.setFont(Fonts.custom);
			lbCiudad.setForeground(Color.WHITE);
			contentPanel.add(lbCiudad);
		}
		{
			textFieldPais = new JTextField();
			Filtros.aplicarFiltroSoloLetras(textFieldPais);
			textFieldPais.setColumns(10);
			textFieldPais.setBounds(356, 327, 290, 26);
			contentPanel.add(textFieldPais);
		}
		{
			JLabel lbPais = new JLabel("País ");
			lbPais.setBounds(356, 288, 99, 13);
			lbPais.setFont(Fonts.custom);
			lbPais.setForeground(Color.WHITE);
			contentPanel.add(lbPais);
		}
		{
			JLabel lbEstado = new JLabel("Estado");
			lbEstado.setBounds(669, 288, 99, 12);
			lbEstado.setFont(Fonts.custom);
			lbEstado.setForeground(Color.WHITE);
			contentPanel.add(lbEstado);
		}
		{
			btnEstadoA = new JRadioButton("Activó ");
			btnEstadoA.setBackground(null);
			btnEstadoA.setForeground(Color.WHITE);
			btnEstadoA.setFont(Fonts.custom);
			btnEstadoA.setBounds(669, 329, 102, 20);
			contentPanel.add(btnEstadoA);
		}
		{
			btnEstadoD = new JRadioButton("Desactivado");
			btnEstadoD.setBounds(789, 329, 150, 20);
			btnEstadoD.setBackground(null);
			btnEstadoD.setForeground(Color.WHITE);
			btnEstadoD.setFont(Fonts.custom);
			contentPanel.add(btnEstadoD);
			
			ButtonGroup group = new ButtonGroup();
			group.add(btnEstadoA);
			group.add(btnEstadoD);
			
		}
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
			
				btnGuardar = new JButton("Guardar");
				BtnStyle.primary(btnGuardar, new Color(56,56,56));
				btnGuardar.setFont(Fonts.custom);
				btnGuardar.addActionListener(this);
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
	
		if(e.getSource() == seleccionarProveedor) {
			
			p = (Proveedor) seleccionarProveedor.getSelectedItem();
			id = p.getIdProveedor();
			textFieldNombre.setText(p.getNombre());
			textFieldTelefono.setText(p.getTelefono());
			textFieldRnc.setText(p.getRncProveedor());
			textFieldCorreo.setText(p.getCorreo());
			textFieldCalle.setText(p.getCalle());
			textFieldCiudad.setText(p.getCiudad());
			textFieldPais.setText(p.getPais());
			
			if(p.getActivo() == 1) {
				btnEstadoA.setSelected(true);
			}else {
				btnEstadoD.setSelected(true);
			}
			
		}
		if(e.getSource() == btnGuardar) {
			
			int activo = 0;
			
			if(btnEstadoA.isSelected()) activo = 1;
			if(btnEstadoD.isSelected()) activo = 0;
		
			var proveedor = new Proveedor(
					id,
					textFieldRnc.getText(),
					textFieldNombre.getText(),
					textFieldTelefono.getText(),
					textFieldCorreo.getText(),
					textFieldCalle.getText(),
					textFieldCiudad.getText(),
					textFieldPais.getText(),
					activo	
				);
			
			System.out.println("" + id);
			
			if(context.getProveedorController().validarDatosProveedores(proveedor)) {
				if(new Messages(dahsboard, "Esta por alterar la información de este elemento, ¿Desea continuar?").messageWarning()) {
					
					if(context.getProveedorController().editarInformacionProveedor(proveedor)) {
						
						new Messages(dahsboard, "Proveedor editado con exito").messageAlert();
						textFieldRnc.setText("");
						textFieldNombre.setText("");
						textFieldTelefono.setText("");
						textFieldCorreo.setText("");
						textFieldCalle.setText("");
						textFieldCiudad.setText("");
						textFieldPais.setText("");
						btnEstadoA.setSelected(false);
						btnEstadoD.setSelected(false);
						return;
						
					}else {
						new Messages(dahsboard, "Error al editar la información de este usuario intente de nuevo").messageError();
						return;
					}
					
				}
				
				
				
				
			}else {
				
				new Messages(dahsboard, "Rellene los campos correctamente").messageError();
				return;
			}
			
			
		}
		
	}

}
