package view.forms.Auth;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.components.BtnStyle;
import view.components.Fonts;
import view.components.FrameDragger;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class login extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JPasswordField passwordField;
	private JButton btnClose, btnIngresar;
	private JLabel lbIconPassword;
	private boolean cambioIconPassword = true;;
	private char passwordHidden;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
						
					login frame = new login();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public login() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		new FrameDragger(this);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\img\\iconApp.png"));
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 800, 401);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelDatosLogin = new JPanel();
		panelDatosLogin.setBackground(new Color(255, 255, 255));
		panelDatosLogin.setBounds(0, 0, 443, 400);
		contentPane.add(panelDatosLogin);
		panelDatosLogin.setLayout(null);
		
		//
		btnIngresar = new JButton("Ingresar");
		BtnStyle.primary(btnIngresar, new Color(64, 128, 128));
		btnIngresar.setBounds(20, 312, 151, 32);
		btnIngresar.setFont(Fonts.custom);
		btnIngresar.addActionListener(this);
		panelDatosLogin.add(btnIngresar);
				
		JLabel lbTitulo = new JLabel("SuperMarket");
		lbTitulo.setBounds(92, 33, 181, 30);
		lbTitulo.setFont(Fonts.bold);
		panelDatosLogin.add(lbTitulo);
		
		JLabel lbLogo = new JLabel("");
		lbLogo.setIcon(new ImageIcon("resources\\img\\logo.png"));
		lbLogo.setBounds(20, 10, 84, 64);
		panelDatosLogin.add(lbLogo);
		
		JLabel lbNameUser = new JLabel("Nombre ");
		lbNameUser.setBounds(21, 119, 190, 22);
		lbNameUser.setFont(Fonts.custom);
		panelDatosLogin.add(lbNameUser);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(20, 166, 371, 31);
		panelDatosLogin.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel passwordUser = new JLabel("Contraseña");
		passwordUser.setBounds(21, 216, 190, 22);
		passwordUser.setFont(Fonts.custom);
		panelDatosLogin.add(passwordUser);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(20, 260, 371, 31);
		
		panelDatosLogin.add(passwordField);
		
		JLabel lbIconUser = new JLabel("");
		lbIconUser.setBounds(401, 166, 32, 31);
		lbIconUser.setIcon(new ImageIcon("resources\\img\\user-interface.png"));
		panelDatosLogin.add(lbIconUser);
		
		lbIconPassword = new JLabel("");
		lbIconPassword.setBounds(401, 260, 32, 31);
		lbIconPassword.addMouseListener(this);
		lbIconPassword.setIcon(new ImageIcon("resources\\img\\ojo.png"));
		panelDatosLogin.add(lbIconPassword);
		

		btnClose = new JButton("");
		btnClose.setBounds(734, 8, 65, 30);
		contentPane.add(btnClose);
		BtnStyle.flat(btnClose);
		btnClose.addActionListener(this);
		btnClose.setIcon(new ImageIcon("resources\\img\\clos.png"));
		
		JLabel bannerLogin = new JLabel("");
		bannerLogin.setIcon(new ImageIcon("resources\\img\\fondoLogin.png"));
		bannerLogin.setBounds(442, -1, 357, 401);
		contentPane.add(bannerLogin);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(445, 0, 1, 400);
		contentPane.add(separator);
		
	

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnClose) {
			System.exit(0);
		}
		if(e.getSource() == btnIngresar) {
			
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == lbIconPassword) {
			
			if(cambioIconPassword) {
				lbIconPassword.setIcon(new ImageIcon("resources\\img\\ojo (1).png"));
				passwordHidden = passwordField.getEchoChar();
				passwordField.setEchoChar((char) 0);
				cambioIconPassword = false;
			}else {
				lbIconPassword.setIcon(new ImageIcon("resources\\img\\ojo.png"));
				passwordField.setEchoChar(passwordHidden);
				cambioIconPassword = true;
			}
			
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
