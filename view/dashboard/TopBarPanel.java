	package view.dashboard;
	
	import java.awt.Color;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.MouseEvent;
	import java.awt.event.MouseListener;
	import java.time.LocalDateTime;
	import java.time.format.DateTimeFormatter;
	import javax.swing.JButton;
	import javax.swing.JDialog;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.SwingConstants;
	import session.SessionContext;
import view.AplicationContext;
	import view.components.AssetManager;
	import view.components.BtnStyle;
	import view.components.Fonts;
	import view.Auth.Login;
	
	public class TopBarPanel extends JPanel implements  ActionListener, MouseListener{
	
		/**
		 * 
		 * 
		 * 
		 */
		
		private Dahsboard dashboard;
		private JLabel lbDate;
		private JDialog dialog;
		private boolean desplegar = true;
		private JButton btnLogout, /*btnDark, btnLight*/ btnSalir, btnCerrarSesion;
		private static final long serialVersionUID = 1L;
		@SuppressWarnings("unused")
		private final AplicationContext context;
		
		public TopBarPanel(Dahsboard dashboard, AplicationContext context) {
			
			setBackground(null);
			setLayout(null);
			this.context = context;
			this.dashboard = dashboard;
			crearPanel();
		}
		
		public void crearPanel() {
			
			LocalDateTime time  = LocalDateTime.now();
			String date = time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
			
			lbDate = new JLabel(date);
			lbDate.setBounds(10, 7, 190, 14);
			lbDate.setFont(Fonts.custom);
			add(lbDate);
			
			//descomentar cuando el login este funcionando y este todo eso funcionando
			
			String nombreUser = "admin";
			if(SessionContext.get() != null) {
				
				nombreUser = SessionContext.get().getNombreUsuarioLogueado();
			}
			btnLogout = new JButton(nombreUser);
			BtnStyle.flat(btnLogout);
			btnLogout.setHorizontalAlignment(SwingConstants.LEFT);
			btnLogout.setHorizontalTextPosition(SwingConstants.LEFT);
			btnLogout.setIcon(AssetManager.icon("angulo.png", 24, 24));
			btnLogout.setBackground(new Color(255, 255, 255));
			btnLogout.setBounds(910, 2, 183, 25);
			btnLogout.addActionListener(this);
			add(btnLogout);
			
//			btnDark = new JButton("");
//			BtnStyle.flat(btnDark);
//			btnDark.setIcon(AssetManager.icon("oscuro.png", 24, 24));
//			btnDark.setBounds(797, 2, 27, 27);
//			btnDark.addActionListener(this);
//			add(btnDark);
//			
//			btnLight = new JButton("");
//			BtnStyle.flat(btnLight);
//			btnLight.setIcon(AssetManager.icon("modo-claro.png", 24, 24));
//			btnLight.setBounds(853, 2, 27, 27);
//			btnLight.addActionListener(this);
//			add(btnLight);
		
			//panelHeader.setBounds(287, 10, 1103, 35);
			
			
		}
	
		@Override
		public void actionPerformed(ActionEvent e) {
			
//			if(e.getSource() == btnDark) {	
//				FlatDarkLaf.setup();
//				SwingUtilities.updateComponentTreeUI(dashboard);
//				dashboard.applyDarkMode();
//			}
//			if(e.getSource() == btnLight) {
//				FlatLightLaf.setup();
//				dashboard.applyLightMode();
////			}
			
			if(e.getSource() == btnLogout) {
				
				if(desplegar) {
					
					btnLogout.setIcon(AssetManager.icon("anguloAbajo.png", 24, 24));
					crearJDialog();
					desplegar = false;
				}else {
					
					btnLogout.setIcon(AssetManager.icon("angulo.png", 24, 24));
					
					if(dialog != null) {
						btnLogout.setIcon(AssetManager.icon("angulo.png", 24, 24));
						dialog.dispose();	
					}
					
					desplegar = true;
				}	
			}
			
			if(e.getSource() == btnCerrarSesion) {
				
				Login login = new Login(context);
				SessionContext.clear();
				login.setVisible(true);
				dashboard.dispose();
			}
			if(e.getSource() == btnSalir) {
				System.exit(0);
			}
			
			
		}
		
		public void crearJDialog() {
			
			dialog = new JDialog(dashboard);
			dialog.getContentPane().setBackground(Color.WHITE);
			dialog.setLayout(null);
			dialog.setBounds(1200, 95, 250, 100);
			dialog.setUndecorated(true);
			dialog.setResizable(false);
			
			//arreglar background cuando se active el modo dark o claro
			
			btnCerrarSesion = new JButton("Cerrar Sesion");
			BtnStyle.flat(btnCerrarSesion);
			btnCerrarSesion.setFont(Fonts.custom);
			btnCerrarSesion.setForeground(Color.BLACK);
			btnCerrarSesion.setBounds(0, 60, 250,  35);
			btnCerrarSesion.setIcon(AssetManager.icon("cerrar-sesion.png", 24, 24));	
			btnCerrarSesion.setIconTextGap(100);
			btnCerrarSesion.setHorizontalAlignment(SwingConstants.RIGHT); 
			btnCerrarSesion.setHorizontalTextPosition(SwingConstants.LEFT);
			btnCerrarSesion.addMouseListener(this);
			btnCerrarSesion.addActionListener(this);
			
			btnSalir = new JButton("Salir");
			BtnStyle.flat(btnSalir);
			btnSalir.setFont(Fonts.custom);
			btnSalir.setForeground(Color.BLACK);
			btnSalir.setBounds(0, 15, 255,  35); 
			btnSalir.setIcon(AssetManager.icon("apagar.png", 24, 24));	
			btnSalir.setIconTextGap(165);
			btnSalir.setHorizontalAlignment(SwingConstants.RIGHT); 
			btnSalir.setHorizontalTextPosition(SwingConstants.LEFT);
			btnSalir.addMouseListener(this);
			btnSalir.addActionListener(this);
			
			
			dialog.add(btnCerrarSesion);
			dialog.add(btnSalir);
			
			dialog.setVisible(true);
			
			
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
//			
//			if(e.getSource() == btnSalir) {
//					BtnStyle.primary(btnSalir, new Color(245, 73, 39));
//			}
//			if(e.getSource() == btnCerrarSesion) {
//				BtnStyle.primary(btnCerrarSesion, new Color(125, 182, 255));
//			}
//			
		}

		@Override
		public void mouseExited(MouseEvent e) {
	
//			if(e.getSource() == btnSalir) {
//				BtnStyle.flat(btnSalir);
//				btnSalir.setForeground(Color.BLACK);		
//		}
//		if(e.getSource() == btnCerrarSesion) {
//			BtnStyle.flat(btnCerrarSesion);
//			btnCerrarSesion.setForeground(Color.BLACK);
//		}
		}
		
		
		
	}
