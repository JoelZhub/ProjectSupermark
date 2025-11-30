package view.modules.custormerService.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Cliente;
import view.AplicationContext;
import view.components.BtnStyle;
import view.components.Filtros;
import view.components.Fonts;
import view.components.Messages;
import view.dashboard.Dahsboard;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class EditarCliente extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final AplicationContext context;
	@SuppressWarnings("unused")
	private final Dahsboard dahsboard;
	private JTextField textFieldNombre;
	private JTextField textFieldTelefono;
	private JComboBox<Cliente> selectCliente;
	private JComboBox<String> selectClasificacion;
	private JRadioButton btnEstadoA, btnEstadoD;
	private Cliente cliente;
	private JTextField textFieldIdentificacion;
	
	public static void main(String[] args) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EditarCliente(AplicationContext context,  Dahsboard dahsboard ) {
		
		this.context = context;
		this.dahsboard = dahsboard;
		setBounds(100, 100, 654, 438);
		setResizable(false);
		setBackground(new Color(56,56,56));
		getContentPane().setBackground(new Color(56,56,56));
		contentPanel.setBackground(new Color(56,56,56));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbEditar = new JLabel("Editar Cliente");
			lbEditar.setFont(Fonts.bold);
			lbEditar.setForeground(Color.WHITE);
			lbEditar.setBounds(10, 25, 556, 37);
			contentPanel.add(lbEditar);
		}
		{
			textFieldNombre = new JTextField();
			Filtros.aplicarFiltroSoloLetras(textFieldNombre);
			textFieldNombre.setBounds(10, 205, 260, 26);
			contentPanel.add(textFieldNombre);
			textFieldNombre.setColumns(10);
		}
		{
			selectCliente = new JComboBox<>();
			llenarComboBox();
			selectCliente.setBounds(10, 121, 260, 26);
			selectCliente.setSelectedItem(null);
			selectCliente.addActionListener(this);
			contentPanel.add(selectCliente);
		}
		{
			JLabel lbSelectCLiente = new JLabel("Seleccionar un cliente");
			lbSelectCLiente.setFont(Fonts.custom);
			lbSelectCLiente.setForeground(Color.WHITE);
			lbSelectCLiente.setBounds(10, 84, 260, 18);
			contentPanel.add(lbSelectCLiente);
		}
		{
			JLabel lbNombre = new JLabel("Nombre");
			lbNombre.setFont(Fonts.custom);
			lbNombre.setForeground(Color.WHITE);
			lbNombre.setBounds(10, 165, 194, 23);
			contentPanel.add(lbNombre);
		}
		{
			textFieldTelefono = new JTextField();
			textFieldTelefono.setColumns(10);
			Filtros.aplicarFiltroTelefono(textFieldTelefono);
			textFieldTelefono.setBounds(345, 208, 260, 26);
			contentPanel.add(textFieldTelefono);
		}
		{
			JLabel lbTelefono = new JLabel("Telefono");
			lbTelefono.setFont(Fonts.custom);
			lbTelefono.setForeground(Color.WHITE);
			lbTelefono.setBounds(345, 168, 194, 23);
			contentPanel.add(lbTelefono);
		}
		{
			JLabel lbClasificacion = new JLabel("Clasificación ");
			lbClasificacion.setBounds(10, 256, 194, 23);
			lbClasificacion.setFont(Fonts.custom);
			lbClasificacion.setForeground(Color.WHITE);
			contentPanel.add(lbClasificacion);
		}
		{
			selectClasificacion = new JComboBox<>();
			selectClasificacion.addItem("MAYORITARIO");
			selectClasificacion.addItem("MINOTIRARIO");
			selectClasificacion.setSelectedItem(null);
			selectClasificacion.setBounds(10, 300, 260, 26);
			contentPanel.add(selectClasificacion);
		}
		{
			JLabel lblEstado = new JLabel("Estado");
			lblEstado.setBounds(345, 264, 194, 23);
			lblEstado.setForeground(Color.WHITE);
			lblEstado.setFont(Fonts.custom);
			contentPanel.add(lblEstado);
		}
		
		btnEstadoA = new JRadioButton("Activo");
		btnEstadoA.setFont(Fonts.custom);
		btnEstadoA.setBackground(null);
		btnEstadoA.setBounds(345, 306, 102, 20);
		contentPanel.add(btnEstadoA);
		
		btnEstadoD = new JRadioButton("Bloqueado");
		btnEstadoD.setFont(Fonts.custom);
		btnEstadoD.setBackground(null);
		btnEstadoD.setBounds(469, 306, 102, 20);
		contentPanel.add(btnEstadoD);
		ButtonGroup group = new ButtonGroup();
		group.add(btnEstadoA);
		group.add(btnEstadoD);
		
		
		{
			textFieldIdentificacion = new JTextField();
			textFieldIdentificacion.setColumns(10);
			textFieldIdentificacion.setBounds(345, 121, 260, 26);
			contentPanel.add(textFieldIdentificacion);
		}
		{
			JLabel lbIdentificacion = new JLabel("Identificación ");
			lbIdentificacion.setForeground(Color.WHITE);
			lbIdentificacion.setFont(null);
			lbIdentificacion.setBounds(345, 81, 194, 23);
			contentPanel.add(lbIdentificacion);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnGuardar = new JButton("Guardar");
				btnGuardar.setFont(Fonts.custom);
				btnGuardar.addActionListener(e-> {
					
					String clasificacion = (String) selectClasificacion.getSelectedItem();
			
					int activo = 0;
					
					if(!btnEstadoA.isSelected()  && !btnEstadoD.isSelected()) {
						new Messages(dahsboard, "Debe seleccionar un estado valido").messageError();
						return;
					}
					if(btnEstadoA.isSelected()) {
						activo=1;
					}
					if(btnEstadoD.isSelected()) {
						activo = 0;
					}
					
//					
					cliente = new Cliente(
							textFieldIdentificacion.getText(),
							textFieldNombre.getText(),
							clasificacion, 
							textFieldTelefono.getText(),
							activo
							);
					
					if(selectCliente.getSelectedItem() != null) {
					if(context.getClienteController().validarCampos(cliente)) {
						
						if(new Messages(dahsboard, "Esta por editar la información de este elemento, ¿Desea continuar?").messageWarning()) {
							
							if(context.getClienteController().editarCliente(cliente)) {
								new Messages(dahsboard, "Cliente editado con exito").messageAlert();
								return;
								
							}else {
								new Messages(dahsboard, "Error al editar la información del cliente").messageError();
								return;
								
							}
							
						}else {
							
							new Messages(dahsboard, "Accion cancelada").messageCancelaciones();
							return;
						}
						
					}else {
						
						new Messages(dahsboard, "Rellene los campos correctamente").messageError();
						return;
					}
				}else {
					
					new Messages(dahsboard, "Seleccione un cliente valido").messageError();
					return;
				}
					
					
				});
				BtnStyle.primary(btnGuardar, new Color(0,0,0));
				buttonPane.add(btnGuardar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setFont(Fonts.custom);
				btnCancelar.addActionListener(e -> this.dispose());
				BtnStyle.primary(btnCancelar, new Color(0,0,0));
				buttonPane.add(btnCancelar);
			}
		}
	}
	
	private void llenarComboBox() {
		for(Cliente c : context.getClienteController().listarClientes()) {
				selectCliente.addItem(c);
		}
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == selectCliente) {
			cliente = (Cliente) selectCliente.getSelectedItem();
			
			textFieldNombre.setText(cliente.getNombre());
			textFieldIdentificacion.setText(cliente.getIdentificacion());
			selectClasificacion.setSelectedItem(cliente.getClasificacion());
			
		
			
			textFieldTelefono.setText(cliente.getTelefono());
			btnEstadoA.setSelected( cliente.getActivo() == 1);
			btnEstadoD.setSelected(cliente.getActivo() == 0);
			
	
		}
		
	}
}
