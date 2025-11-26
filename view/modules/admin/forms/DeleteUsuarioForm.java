package view.modules.admin.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import session.SessionContext;
import view.AplicationContext;
import view.components.BtnStyle;
import view.components.Fonts;
import view.components.Messages;
import view.dashboard.Dahsboard;

public class DeleteUsuarioForm extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	@SuppressWarnings("unused")
	private final Dahsboard dahsboard;
	@SuppressWarnings("unused")
	private final  AplicationContext context;
	private JTextField textField;
	private JButton btnCancelar, btnGuardar;
	private JPanel buttonPane;
	
	public static void main(String[] args) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public DeleteUsuarioForm(Dahsboard dahsboard, AplicationContext context) {
		
		this.dahsboard = dahsboard;
		this.context = context;

		setBounds(100, 100, 450, 216);
		setBackground(new Color(56, 56, 56));
		getContentPane().setLayout(new BorderLayout());
		setModal(true);
		setResizable(false);
		Point p = dahsboard.getLocationOnScreen();
		getContentPane().setBackground(new Color(56,56,56));
		setLocation(p.x, p.y);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\img\\iconApp.png"));
		contentPanel.setBackground(new Color(56, 56, 56));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
				
		JLabel lbEliminar = new JLabel("Eliminar Usuario");
		lbEliminar.setBounds(10, 22, 198, 20);
		lbEliminar.setFont(Fonts.bold);
		lbEliminar.setForeground(Color.WHITE);
		contentPanel.add(lbEliminar);
		
		JLabel lbId = new JLabel("Ingresar ID");
		lbId.setFont(Fonts.custom);
		lbId.setBounds(10, 80, 140, 12);
		lbId.setForeground(Color.WHITE);
		contentPanel.add(lbId);
		
		textField = new JTextField();
		textField.setBackground(null);
		textField.setBorder(BorderFactory.createLineBorder(Color.white, 2, true));
		textField.setBounds(139, 77, 267, 27);
		textField.setForeground(Color.WHITE);
		
		contentPanel.add(textField);
		textField.setColumns(10);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new GridLayout(1, 2, 10, 0));
			buttonPane.setBackground(new Color(56,56,56));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnGuardar = new JButton("Guardar");
				btnGuardar.setBorder(null);
				BtnStyle.primary(btnGuardar, new Color(102, 237, 142));
				btnGuardar.setFont(Fonts.custom);
				btnGuardar.setForeground(Color.WHITE);
				btnGuardar.setPreferredSize(new Dimension(60, 40));
				btnGuardar.addActionListener(e -> {
					
					int idUser;
					
						if(textField.getText().trim().matches("\\d+")) {
							idUser = Integer.parseInt(textField.getText().trim());
							
							if(idUser == SessionContext.get().getIdUsuarioLogueado()) {
								new Messages(dahsboard, "No puede eliminar su propia cuenta").messageError();
								return;
							}
							else {
							if(new Messages(dahsboard, "Esta por eliminar un usuario, desea continuar?").messageWarning()) {
					
								if(context.getUserController().eliminarUsuario(idUser)) {
									new Messages(dahsboard, "Usuario eliminado con extio").messageAlert();
									textField.setText("");
									return;
								}else {
									new Messages(dahsboard, "Usuario no encontrado").messageError();
									return;
								}
								
							} else {
								new Messages(dahsboard, "Accion cancelada").messageCancelaciones();
								return;
							}
							
						}
							
						}else {	
							new Messages(dahsboard, "Ingrese un ID valido").messageError();
							return;
						}
					
					
					
				});
				buttonPane.add(btnGuardar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setBorder(null);
				btnCancelar.setPreferredSize(new Dimension(40, 20));
				BtnStyle.primary(btnCancelar, new Color(204, 204, 204));
				btnCancelar.setFont(Fonts.custom);
				btnCancelar.setForeground(Color.WHITE);
				btnCancelar.addActionListener(e -> this.dispose());
				buttonPane.add(btnCancelar);
			}
		}
	}

}
