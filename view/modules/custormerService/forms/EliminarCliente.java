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
import view.components.Fonts;
import view.components.Messages;
import view.dashboard.Dahsboard;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class EliminarCliente extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final AplicationContext context;
	@SuppressWarnings("unused")
	private final Dahsboard dahsboard;
	private JComboBox<Cliente> selectCliente;
	
	public static void main(String[] args) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public EliminarCliente(AplicationContext context,  Dahsboard dahsboard) {
		this.context = context;
		this.dahsboard = dahsboard;
		setResizable(false);
		setBackground(new Color(56,56,56));
		setBounds(100, 100, 450, 246);
		getContentPane().setBackground(new Color(56,56,56));
		contentPanel.setBackground(new Color(56,56,56));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbDesactivar = new JLabel("Desactivar cliente");
			lbDesactivar.setFont(Fonts.bold);
			lbDesactivar.setForeground(Color.WHITE);
			lbDesactivar.setBounds(10, 10, 383, 32);
			contentPanel.add(lbDesactivar);
		}
		{
			selectCliente = new JComboBox<>();
			llenarComboBox();
			selectCliente.setSelectedItem(null);
			selectCliente.setBounds(10, 124, 383, 26);
			contentPanel.add(selectCliente);
		}
		{
			JLabel lbNombre = new JLabel("Seleccionar cliente");
			lbNombre.setBounds(10, 82, 383, 32);
			lbNombre.setFont(Fonts.custom);
			lbNombre.setForeground(Color.WHITE);
			contentPanel.add(lbNombre);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnGuardar = new JButton("Guardar");
				btnGuardar.addActionListener( e-> {
					
					if(selectCliente.getSelectedItem() != null) {
						
						Cliente c = (Cliente) selectCliente.getSelectedItem();
						
						int id = Integer.parseInt(c.getIdentificacion());
						
						if(new Messages(dahsboard, "Esta por desactivar este cliente, ¿Seguro que desea continuar?").messageWarning()) {
						if(context.getClienteController().eliminarCLiente(id)) {
							
							new Messages(dahsboard, "Cliente desactivado con extio").messageAlert();
							return;
							
						}else {
							
							new Messages(dahsboard, "Error al desactivar el cliente").messageError();
							return;
						}
					}else {
						new Messages(dahsboard, "Accion cancelada").messageCancelaciones();
						return;
						
					}
					}else {
						new Messages(dahsboard, "Seleccione un cliente valido").messageError();
						return;
					}
					
					
				});
				BtnStyle.primary(btnGuardar, new Color(0,0,0));
				btnGuardar.setFont(Fonts.custom);
				buttonPane.add(btnGuardar);
				
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(e -> this.dispose());
				BtnStyle.primary(btnCancelar, new Color(0,0,0));
				btnCancelar.setFont(Fonts.custom);
				buttonPane.add(btnCancelar);
			}
		}
	}

	
	private void llenarComboBox() {
		
		for(Cliente c : context.getClienteController().listarClientes()) {
			if(c.getActivo() == 1) {
				selectCliente.addItem(c);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
