package view.forms.Auth;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.components.BtnStyle;
import view.components.Fonts;
import view.components.FrameDragger;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class RegisterUsers extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JPasswordField passwordField;
	private JButton btnClose,  btnCrearUsuario;
	private JTextField textFieldCorreo;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterUsers frame = new RegisterUsers();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public RegisterUsers() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		new FrameDragger(this);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\img\\iconApp.png"));
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 662, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelDatosForms = new JPanel();
		panelDatosForms.setBackground(new Color(255, 255, 255));
		panelDatosForms.setBounds(0, 0, 354, 440);
		contentPane.add(panelDatosForms);
		panelDatosForms.setLayout(null);
		
		JLabel lbTituloFormCrearUsuarios = new JLabel("Crear ");
		lbTituloFormCrearUsuarios.setBounds(106, 36, 113, 18);
		lbTituloFormCrearUsuarios.setFont(Fonts.bold);
		panelDatosForms.add(lbTituloFormCrearUsuarios);
		
		JLabel lbLogo = new JLabel("");
		lbLogo.setIcon(new ImageIcon("resources\\img\\logo.png"));
		lbLogo.setBounds(22, 22, 84, 64);
		panelDatosForms.add(lbLogo);
		
		JLabel lbNombre = new JLabel("Nombre de usuario");
		lbNombre.setBounds(10, 115, 158, 12);
		lbNombre.setFont(Fonts.custom);
		panelDatosForms.add(lbNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(10, 142, 311, 25);
		panelDatosForms.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel NivelAcceso = new JLabel("Nivel de acceso");
		NivelAcceso.setBounds(10, 253, 158, 12);
		NivelAcceso.setFont(Fonts.custom);
		panelDatosForms.add(NivelAcceso);
		
		JComboBox comboBoxAcceso = new JComboBox();
		comboBoxAcceso.setBounds(10, 282, 311, 25);
		panelDatosForms.add(comboBoxAcceso);
		
		JLabel passdword = new JLabel("Password generada");
		passdword.setBounds(10, 328, 158, 15);
		passdword.setFont(Fonts.custom);
		panelDatosForms.add(passdword);
		
		passwordField = new JPasswordField();
		passwordField.setEditable(false);
		passwordField.setBounds(10, 355, 311, 25);
		panelDatosForms.add(passwordField);
		
		btnCrearUsuario = new JButton("Crear usuario");
		btnCrearUsuario.setForeground(new Color(255, 255, 255));
		btnCrearUsuario.setFont(Fonts.custom);
		btnCrearUsuario.setBackground(new Color(54, 90, 120));
		btnCrearUsuario.setBounds(10, 398, 158, 32);
		panelDatosForms.add(btnCrearUsuario);
		
		JLabel lblNuevoUsuario = new JLabel("nuevo usuario");
		lblNuevoUsuario.setFont(null);
		lblNuevoUsuario.setBounds(106, 56, 144, 18);
		lblNuevoUsuario.setFont(Fonts.bold);
		panelDatosForms.add(lblNuevoUsuario);
		
		textFieldCorreo = new JTextField();
		textFieldCorreo.setColumns(10);
		textFieldCorreo.setBounds(10, 209, 311, 25);
		panelDatosForms.add(textFieldCorreo);
		
		JLabel lbCorreo = new JLabel("Nombre de usuario");
		lbCorreo.setFont(null);
		lbCorreo.setBounds(10, 182, 158, 12);
		panelDatosForms.add(lbCorreo);
		
		JLabel bannerLogin = new JLabel("");
		bannerLogin.setHorizontalAlignment(SwingConstants.CENTER);
		bannerLogin.setIcon(new ImageIcon("resources\\img\\fondoRegister.png"));
		bannerLogin.setBounds(364, 0, 298, 440);
		contentPane.add(bannerLogin);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBackground(new Color(0, 0, 0));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(355, 0, 1, 440);
		contentPane.add(separator);
		
		btnClose = new JButton("");
		BtnStyle.flat(btnClose);
		btnClose.addActionListener(this);
		btnClose.setIcon(new ImageIcon("resources\\img\\clos.png"));
		btnClose.setBounds(597, 5, 65, 30);
		contentPane.add(btnClose);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnClose ) {
			this.dispose();
		}
		
	}
}
