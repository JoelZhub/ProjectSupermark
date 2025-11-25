package view.modules.admin.forms;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.Rol;
import model.User;
import view.AplicationContext;
import view.components.AssetManager;
import view.components.BtnStyle;
import view.components.Fonts;
import view.components.Messages;
import view.dashboard.Dahsboard;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class CrearUsuariosForm extends JDialog implements MouseListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	@SuppressWarnings("unused")
	private Dahsboard dahsboard;
	private JTextField textFieldNombre;
	private JTextField textFieldEmail;
	private boolean cambioIcon = true;
	private JLabel lbIconPassword;
	private JPasswordField passwordField;
	private char passwordHidden;
	
	@SuppressWarnings("unused")
	private final  AplicationContext context;
	
	public static void main(String[] args) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public CrearUsuariosForm(Dahsboard dahsboard, AplicationContext context) {
		setBounds(100, 100, 695, 413);
		setUndecorated(true);
		this.context = context;
		getContentPane().setLayout(new BorderLayout());
		setModal(true);
		Point p = dahsboard.getLocationOnScreen();
		setLocation(p.x, p.y);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\img\\iconApp.png"));
		contentPanel.setBackground(new Color(56, 56, 56));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.dahsboard = dahsboard;
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lbTitulo = new JLabel("Crear Usuario");
		lbTitulo.setFont(Fonts.bold);
		lbTitulo.setForeground(Color.WHITE);
		lbTitulo.setBounds(21, 39, 398, 28);
		contentPanel.add(lbTitulo);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBackground(null);
		textFieldNombre.setForeground(Color.WHITE);
		textFieldNombre.setFont(Fonts.custom);
		textFieldNombre.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));
		textFieldNombre.setBounds(209, 108, 349, 28);
		textFieldNombre.addKeyListener( new KeyAdapter() {
			
			 @Override
			    public void keyReleased(KeyEvent e) {
				 textFieldEmail.setText(textFieldNombre.getText() + "@empresa.com");
				 String input = textFieldNombre.getText();
				 	if(input.isEmpty()) {
				 		passwordField.setText("");
				 		 textFieldEmail.setText("");
				 	}else {
				 		passwordField.setText(generateSecurePassword());
				 	}
			    }
		});
		contentPanel.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lbNombre = new JLabel("Nombre");
		lbNombre.setFont(Fonts.custom);
		lbNombre.setForeground(Color.WHITE);
		lbNombre.setBounds(21, 111, 171, 21);
		contentPanel.add(lbNombre);
		
		JLabel lbEmail = new JLabel("Correo Electronico");
		lbEmail.setBounds(21, 176, 171, 21);
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
		textFieldEmail.setBounds(209, 173, 349, 28);
		contentPanel.add(textFieldEmail);
		
		JLabel lbRol = new JLabel("Rol");
		lbRol.setBounds(21, 239, 171, 21);
		lbRol.setFont(Fonts.custom);
		lbRol.setForeground(Color.WHITE);
		contentPanel.add(lbRol);
		
		JLabel lbPassword = new JLabel("Password");
		lbPassword.setBounds(21, 302, 171, 21);
		lbPassword.setFont(Fonts.custom);
		lbPassword.setForeground(Color.WHITE);
		contentPanel.add(lbPassword);
		
		JComboBox<Rol> selectRol = new JComboBox<Rol>(Rol.values());
		selectRol.setBounds(209, 239, 349, 28);
		selectRol.setBackground(new Color(56,56,56));
		selectRol.setForeground(Color.white);
		selectRol.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));
		contentPanel.add(selectRol);
		
		JLabel lbIconUser = new JLabel("");
		lbIconUser.setHorizontalAlignment(SwingConstants.CENTER);
		lbIconUser.setIcon(AssetManager.icon("user-interface.png", 28, 28));
		lbIconUser.setBounds(584, 108, 44, 30);
		contentPanel.add(lbIconUser);
		
		JLabel lbIconEmail = new JLabel("");
		lbIconEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lbIconEmail.setIcon(AssetManager.icon("gmail.png", 28,28));
		lbIconEmail.setBounds(584, 173, 44, 30);
		contentPanel.add(lbIconEmail);
		
		JLabel lbIconRol = new JLabel("");
		lbIconRol.setIcon(AssetManager.icon("rol.png", 28, 28));
		lbIconRol.setHorizontalAlignment(SwingConstants.CENTER);
		lbIconRol.setBounds(584, 239, 44, 30);
		contentPanel.add(lbIconRol);
		
		lbIconPassword = new JLabel("");
		lbIconPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lbIconPassword.setIcon(AssetManager.icon("ojo.png", 28,28));
		lbIconPassword.addMouseListener(this);
	
		lbIconPassword.setBounds(584, 299, 44, 30);
		contentPanel.add(lbIconPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(null);
		passwordField.setFont(Fonts.custom);
		passwordField.setForeground(Color.WHITE);
		passwordField.setEditable(false);
		passwordField.setBounds(209, 303, 349, 28);
		
		
		
		
		contentPanel.add(passwordField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(56,56,56));
			buttonPane.setLayout (new GridLayout(1, 2, 10, 0));
			
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnGuardar = new JButton("Guardar");
				btnGuardar.setBorder(null);
				
				BtnStyle.primary(btnGuardar, new Color(102, 237, 142));
				btnGuardar.setFont(Fonts.custom);
				btnGuardar.setForeground(Color.WHITE);
				btnGuardar.setPreferredSize(new Dimension(200, 40));
			
				btnGuardar.setBounds(0, 10, 200, 30);
				btnGuardar.addActionListener(e -> {
					
					String password = new String(passwordField.getPassword());
					var user = new User(textFieldNombre.getText(), 
							textFieldEmail.getText(), 
							password,
							(Rol) selectRol.getSelectedItem());
					
					boolean dataV = context.getUserController().validateFieldsRegister(user);
				
					if(dataV) {
						if(context.getUserController().crearUsuario(user)) {
							new Messages(dahsboard, "Usuario creado exitosamente").messageAlert();
							textFieldNombre.setText("");
							passwordField.setText("");
							textFieldEmail.setText("");
							return;
						}else {
							new Messages(dahsboard, "Error al crear el usuario").messageError();
							return;
						}
						
					}else {
						new Messages(dahsboard, "Datos incorrectos").messageError();
						return;
					}
					
					
					
				});
				buttonPane.add(btnGuardar);
				getRootPane().setDefaultButton(btnGuardar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setBorder(null);
				btnCancelar.setPreferredSize(new Dimension(200, 40));
				BtnStyle.primary(btnCancelar, new Color(204, 204, 204));
				btnCancelar.setFont(Fonts.custom);
				btnCancelar.setForeground(Color.WHITE);
				btnCancelar.addActionListener(e -> this.dispose());
				buttonPane.add(btnCancelar);
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
		
		
	}

	private String generateSecurePassword() {
	    int min = 8;
	    int max = 10;
	    int length = (int) (Math.random() * (max - min + 1)) + min;

	    String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String lower = "abcdefghijklmnopqrstuvwxyz";
	    String digits = "0123456789";
	    String symbols = "!@#$%&*?";
	    String pool = upper + lower + digits + symbols;

	    StringBuilder sb = new StringBuilder();

	    for (int i = 0; i < length; i++) {
	        int idx = (int) (Math.random() * pool.length());
	        sb.append(pool.charAt(idx));
	    }

	    return sb.toString();
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
