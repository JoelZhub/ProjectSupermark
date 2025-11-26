package view.modules.admin.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Rol;
import model.User;
import session.SessionContext;
import view.AplicationContext;
import view.components.AssetManager;
import view.components.BtnStyle;
import view.components.Fonts;
import view.components.GenerateEmail;
import view.components.GeneratePassword;
import view.components.Messages;
import view.dashboard.Dahsboard;

public class EditUsuarioForm extends JDialog implements ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final Dahsboard dahsboard;
	private final  AplicationContext context;
	private boolean cambioIcon = true;
	private char passwordHidden;
	private JTextField textFieldNombre;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;
	private JButton btnCancelar, btnGuardar, btnLimpiar;
	private JPanel buttonPane;
	private JComboBox<Rol> selectRol;
	private JLabel lbIconPassword, lbCambiarPassword;
	private JTextField textFielID;
	
	public static void main(String[] args) {
		try {
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public EditUsuarioForm(Dahsboard dahsboard,AplicationContext context) {
		
		this.dahsboard = dahsboard;
		this.context = context;
		setBounds(100, 100, 695, 520);
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
	
		JLabel lbTitulo = new JLabel("Editar Usuario");
		lbTitulo.setFont(Fonts.bold);
		lbTitulo.setForeground(Color.WHITE);
		lbTitulo.setBounds(21, 39, 398, 28);
		contentPanel.add(lbTitulo);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBackground(null);
		textFieldNombre.setForeground(Color.WHITE);
		textFieldNombre.setFont(Fonts.custom);
		textFieldNombre.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));
		textFieldNombre.setBounds(209, 177, 349, 28);
		textFieldNombre.addKeyListener(new KeyAdapter() {
			 @Override
			    public void keyReleased(KeyEvent e) {
				 String input = textFieldNombre.getText().trim();
			     textFieldEmail.setText(GenerateEmail.generarEmail(input));
			    }
		});
		contentPanel.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lbNombre = new JLabel("Nombre");
		lbNombre.setFont(Fonts.custom);
		lbNombre.setForeground(Color.WHITE);
		lbNombre.setBounds(21, 180, 171, 21);
		contentPanel.add(lbNombre);
		
		JLabel lbEmail = new JLabel("Correo Electronico");
		lbEmail.setBounds(21, 245, 171, 21);
		lbEmail.setFont(Fonts.custom);
		lbEmail.setForeground(Color.WHITE);
		contentPanel.add(lbEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setEditable(false);
		textFieldEmail.setBackground(null);
		textFieldEmail.setFont(Fonts.custom);
		textFieldEmail.setForeground(Color.WHITE);
		textFieldEmail.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(209, 242, 349, 28);
		contentPanel.add(textFieldEmail);
		
		JLabel lbRol = new JLabel("Rol");
		lbRol.setBounds(21, 308, 171, 21);
		lbRol.setFont(Fonts.custom);
		lbRol.setForeground(Color.WHITE);
		contentPanel.add(lbRol);
		
		JLabel lbPassword = new JLabel("Password");
		lbPassword.setBounds(21, 371, 171, 21);
		lbPassword.setFont(Fonts.custom);
		lbPassword.setForeground(Color.WHITE);
		contentPanel.add(lbPassword);
		
		selectRol = new JComboBox<Rol>(Rol.values());
		selectRol.setBounds(209, 308, 349, 28);
		selectRol.setBackground(new Color(56,56,56));
		selectRol.setForeground(Color.white);
		selectRol.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));
		contentPanel.add(selectRol);
		
		JLabel lbIconUser = new JLabel("");
		lbIconUser.setHorizontalAlignment(SwingConstants.CENTER);
		lbIconUser.setIcon(AssetManager.icon("user-interface.png", 28, 28));
		lbIconUser.setBounds(584, 177, 44, 30);
		contentPanel.add(lbIconUser);
		
		JLabel lbIconEmail = new JLabel("");
		lbIconEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lbIconEmail.setIcon(AssetManager.icon("gmail.png", 28,28));
		lbIconEmail.setBounds(584, 242, 44, 30);
		contentPanel.add(lbIconEmail);
		
		JLabel lbIconRol = new JLabel("");
		lbIconRol.setIcon(AssetManager.icon("rol.png", 28, 28));
		lbIconRol.setHorizontalAlignment(SwingConstants.CENTER);
		lbIconRol.setBounds(584, 308, 44, 30);
		contentPanel.add(lbIconRol);
		

		
		passwordField = new JPasswordField();
		passwordField.setEditable(false);
		passwordField.setBackground(null);
		passwordField.setFont(Fonts.custom);
		passwordField.setForeground(Color.WHITE);
		passwordField.setBounds(209, 372, 349, 28);
		
		
		contentPanel.add(passwordField);
		
		textFielID = new JTextField();
		textFielID.setForeground(Color.WHITE);
		textFielID.setFont(null);
		textFielID.setColumns(10);
		textFielID.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));
		textFielID.setBackground((Color) null);
		textFielID.addActionListener(this);
		textFielID.setBounds(209, 114, 349, 28);
		contentPanel.add(textFielID);
		
		JLabel lbId = new JLabel("IDUsuario");
		lbId.setForeground(Color.WHITE);
		lbId.setFont(null);
		lbId.setBounds(21, 117, 171, 21);
		contentPanel.add(lbId);
		
		JLabel lbIconIDUser = new JLabel("");
		lbIconIDUser.setHorizontalAlignment(SwingConstants.CENTER);
		lbIconIDUser.setIcon(AssetManager.icon("user-interface.png", 28, 28));
		lbIconIDUser.setBounds(584, 112, 44, 30);
		contentPanel.add(lbIconIDUser);
		
		lbCambiarPassword = new JLabel("");
		lbCambiarPassword.setIcon(AssetManager.icon("cambiarContrasena.png", 28, 28));
		lbCambiarPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lbCambiarPassword.setBounds(627, 371, 44, 30);
		lbCambiarPassword.addMouseListener(this);
		contentPanel.add(lbCambiarPassword);
		
		lbIconPassword = new JLabel("");
		lbIconPassword.setIcon(AssetManager.icon("ojo.png", 28,28));
		lbIconPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lbIconPassword.setBounds(573, 371, 44, 30);
		lbIconPassword.addMouseListener(this);
		contentPanel.add(lbIconPassword);
		
			buttonPane = new JPanel();
			buttonPane.setBackground(new Color(56,56,56));
			buttonPane.setLayout (new GridLayout(1, 2, 10, 0));
			
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				btnGuardar = new JButton("Guardar");
				btnGuardar.setBorder(null);
				BtnStyle.primary(btnGuardar, new Color(102, 237, 142));
				btnGuardar.setFont(Fonts.custom);
				btnGuardar.setForeground(Color.WHITE);
				btnGuardar.setPreferredSize(new Dimension(200, 40));
				btnGuardar.setBounds(0, 10, 200, 30);
				btnGuardar.addActionListener(this);
				buttonPane.add(btnGuardar);
				getRootPane().setDefaultButton(btnGuardar);
				
				btnLimpiar = new JButton("Limpiar");
				btnLimpiar.setBorder(null);
				btnLimpiar.setPreferredSize(new Dimension(200, 40));
				BtnStyle.primary(btnLimpiar, new Color(176,176,176));
				btnLimpiar.setFont(Fonts.custom);
				btnLimpiar.setForeground(Color.WHITE);
				btnLimpiar.addActionListener(this);
				buttonPane.add(btnLimpiar);
				
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setBorder(null);
				btnCancelar.setPreferredSize(new Dimension(200, 40));
				BtnStyle.primary(btnCancelar, new Color(204, 204, 204));
				btnCancelar.setFont(Fonts.custom);
				btnCancelar.setForeground(Color.WHITE);
				btnCancelar.addActionListener(e -> this.dispose());
				buttonPane.add(btnCancelar);
			
			
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == textFielID) {
			
			int idUser;
			if(textFielID.getText().trim().matches("\\d+")) {
				idUser = Integer.parseInt(textFielID.getText().trim());
				var user = context.getUserController().buscarUsuario(idUser);
				
				if(user != null) {
					selectRol.setEnabled(true);
					textFielID.setText(user.getId() + "");
					textFielID.setEditable(false);
					textFieldNombre.setText(user.getNombre());
					textFieldEmail.setText(user.getEmail().trim());
					selectRol.setSelectedItem(user.getRol());
					
					if(user.getId() == SessionContext.get().getIdUsuarioLogueado()) {
						selectRol.setEnabled(false);
					}
			
					passwordField.setText(user.getPassword());
					
				}else {
					new Messages(dahsboard, "Usuario no encontrao").messageError();
					return;
				}
				
			}else {
				new Messages(dahsboard, "Ingrese un ID valido").messageError();
				return;
			}
			
		}
		if(e.getSource()  == btnLimpiar) 
		{
			textFielID.setText("");
			textFielID.setEditable(true);
			textFieldNombre.setText("");
			textFieldEmail.setText("");
			selectRol.setSelectedItem(null);
			passwordField.setText("");
		}
		
		if(e.getSource() == btnGuardar) {
			
			//este metodo todavia no esta en funcionamiento busca pero todavia no acciona el cambio
			String password = new String(passwordField.getPassword());
			
			var user = new User(textFieldNombre.getText(),
					textFieldEmail.getText(), password, (Rol) selectRol.getSelectedItem());
			
			if(context.getUserController().validateFieldsRegister(user)) {
				if(new Messages(dahsboard, "Esta por editar la informacion de este usuario, ¿Desea continuar?").messageWarning()) {
					if(context.getUserController().editarUser(user)) {
						new Messages(dahsboard, "Usuario editado con éxito").messageAlert();
						return;
					}else {
						new Messages(dahsboard, "Error al editar el usuario").messageError();
						return;
					}
					
				}else {
					new Messages(dahsboard, "Accion cancelada").messageCancelaciones();
					return;
				}
	
			}else {
				
				new Messages(dahsboard, "Datos incorrectos").messageError();
				return;
			}
		}
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getSource() == lbIconPassword) {
			
			if(cambioIcon) {
				lbIconPassword.setIcon(new ImageIcon("resources\\img\\ojo (1).png"));
				passwordHidden = passwordField.getEchoChar();
				passwordField.setEchoChar((char) 0);
				cambioIcon = false;
			}else {
				lbIconPassword.setIcon(new ImageIcon("resources\\img\\ojo.png"));
				passwordField.setEchoChar(passwordHidden);
				cambioIcon = true;
			}
			
		}
		if(e.getSource() == lbCambiarPassword) {
			passwordField.setText(GeneratePassword.generateSecurePassword());
		}
		
		
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
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
