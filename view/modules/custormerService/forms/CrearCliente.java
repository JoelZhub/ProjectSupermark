package view.modules.custormerService.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class CrearCliente extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	@SuppressWarnings("unused")
	private final AplicationContext context;
	@SuppressWarnings("unused")
	private final Dahsboard dahsboard;
	private JTextField textFieldIdentificacion;
	private JTextField textFieldNombre;
	private JTextField textFieldTelefono;
	private JComboBox<String> selectTipoCliente;
	
	public static void main(String[] args) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	public CrearCliente(AplicationContext context, Dahsboard dahsboard) {
		
		this.context = context;
		this.dahsboard = dahsboard;
		setResizable(false);
		setBackground(new Color(56,56,56));
		setBounds(100, 100, 598, 372);
		getContentPane().setBackground(new Color(56,56,56));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(56,56,56));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbCrear = new JLabel("Crear cliente");
			lbCrear.setBounds(10, 20, 298, 21);
			lbCrear.setForeground(Color.WHITE);
			lbCrear.setFont(Fonts.bold);
			contentPanel.add(lbCrear);
		}
		{
			textFieldIdentificacion = new JTextField();
			Filtros.aplicarFiltroNumericoTextField(textFieldIdentificacion);
			textFieldIdentificacion.setBounds(160, 85, 306, 26);
			contentPanel.add(textFieldIdentificacion);
			textFieldIdentificacion.setColumns(10);
		}
		{
			JLabel lbIdentificacion = new JLabel("Identificación ");
			lbIdentificacion.setBounds(10, 87, 140, 21);
			lbIdentificacion.setForeground(Color.WHITE);
			lbIdentificacion.setFont(Fonts.custom);
			contentPanel.add(lbIdentificacion);
		}
		{
			textFieldNombre = new JTextField();
			Filtros.aplicarFiltroSoloLetras(textFieldNombre);
			textFieldNombre.setColumns(10);
			textFieldNombre.setBounds(160, 137, 306, 26);
			contentPanel.add(textFieldNombre);
		}
		{
			JLabel lbNombre = new JLabel("Nombre");
			lbNombre.setForeground(Color.WHITE);
			lbNombre.setFont(Fonts.custom);
			lbNombre.setBounds(10, 139, 140, 21);
			contentPanel.add(lbNombre);
		}
		{
			JLabel lbClasificacion = new JLabel("Clasificación ");
			lbClasificacion.setForeground(Color.WHITE);
			lbClasificacion.setBounds(10, 186, 140, 21);
			lbClasificacion.setFont(Fonts.custom);
			contentPanel.add(lbClasificacion);
		}
		{
			selectTipoCliente = new JComboBox<>();
			selectTipoCliente.addItem("MAYORITARIO");
			selectTipoCliente.addItem("MINOTIRARIO");
			selectTipoCliente.setSelectedItem(null);
			
			selectTipoCliente.setBounds(160, 186, 306, 26);
			contentPanel.add(selectTipoCliente);
		}
		{
			JLabel lblTelfono = new JLabel("Teléfono ");
			lblTelfono.setBounds(10, 233, 140, 21);
			lblTelfono.setFont(Fonts.custom);
			lblTelfono.setForeground(Color.WHITE);
			contentPanel.add(lblTelfono);
		}
		{
			textFieldTelefono = new JTextField();
			Filtros.aplicarFiltroTelefono(textFieldTelefono);
			textFieldTelefono.setColumns(10);
			textFieldTelefono.setBounds(160, 234, 306, 26);
			contentPanel.add(textFieldTelefono);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnGuardar = new JButton("Guardar");
				BtnStyle.primary(btnGuardar, Color.BLACK);
				btnGuardar.addActionListener(e -> {
					
					String clasificacion = (String)  selectTipoCliente.getSelectedItem();
					
					Cliente cl = new Cliente(
							textFieldIdentificacion.getText(),
							textFieldNombre.getText(),
							clasificacion,
							textFieldTelefono.getText()
							);
					
					if(context.getClienteController().validarCampos(cl)) {
						if(context.getClienteController().crearCliente(cl)) {
							new Messages(dahsboard, "Cliente creado exitosamente").messageAlert();
							limpiarCampos();
							return;
							
							
						}else {
							new Messages(dahsboard, "Error al crear el cliente").messageError();
							return;
						}
					}else {
						new Messages(dahsboard, "Rellene los campos correctamente").messageError();
						return;
					}
					
					
				});
				btnGuardar.setFont(Fonts.custom);
				buttonPane.add(btnGuardar);
				
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				BtnStyle.primary(btnCancelar, Color.BLACK);
				btnCancelar.setFont(Fonts.custom);
				btnCancelar.addActionListener( e-> this.dispose());
				buttonPane.add(btnCancelar);
			}
		}
	}



	private void limpiarCampos() {
		textFieldTelefono.setText("");
		selectTipoCliente.setSelectedItem(null);
		textFieldNombre.setText("");
		textFieldIdentificacion.setText("");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
