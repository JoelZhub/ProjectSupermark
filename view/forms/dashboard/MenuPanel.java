	package view.forms.dashboard;
	
	import java.awt.Color;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.util.HashMap;
	import java.util.Map;
	import model.Modulo;
	import model.Permiso;
import navigation.NavigationManager;
import session.SessionContext;

	import javax.swing.JButton;
	import javax.swing.JLabel;
	import javax.swing.JPanel;		
	import view.components.AssetManager;
	import view.components.BtnStyle;
	import view.components.Fonts;
	import view.components.RoundePanel;
	
	public class MenuPanel extends JPanel implements ActionListener {
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	
		private Map<JButton, Modulo> mapearModulos; 
		
		private RoundePanel panelCrear;
		
		private JPanel panelBotones;
		
		@SuppressWarnings("unused")
		private String messageCrear = "";
		
		private JButton btnAgregar, btnDashboard, btnVenta, btnFactura, btnUsuarios,  btnProductos;
		
		private JLabel lbTitulo, lbIconCasa, lbIconUsuarios,  lbIconCategorias, lbIconVentas, lbIconFacturas, lbLogo, lbCrearTitle,  lblNuevoProducto;
		
		private NavigationManager navigation;
		
		public MenuPanel(NavigationManager navigation) {
	
			
			//panelMenu.setBounds(0, 0, 277, 700);
			setLayout(null);
			setBackground(Color.WHITE);
			this.setNavigation(navigation);
			crearPanel();		
			
		}
		
		private void crearPanel() {
			
			crearLabels();
			crearBotonoes();
//			mapearBotones();
//			aplicarPermisos();
			crearPanelBotones();
			crearPanelCreate();
			
			lbTitulo = new JLabel("SuperMarket");
			lbTitulo.setBounds(92, 38, 175, 36);
			lbTitulo.setFont(Fonts.bold);
			lbTitulo.setForeground(Color.black);
			add(lbTitulo);
							
			lbLogo = new JLabel("");
			lbLogo.setIcon(AssetManager.icon("logo.png", 64, 64));
			lbLogo.setBounds(10, 17, 84, 64);
			add(lbLogo);
			add(panelCrear);
			add(panelBotones);
		}
		
		private void crearBotonoes() {
			
			btnAgregar = new JButton("");
			BtnStyle.flat(btnAgregar);
			btnAgregar.setBackground(new Color(255, 255, 255));
			btnAgregar.addActionListener(this);
			btnAgregar.setIcon(AssetManager.icon("agregar.png", 70,70));
			btnAgregar.setBounds(134, 4, 70, 70);
			
			btnDashboard = new JButton("Dashboard");
			btnDashboard.setBounds(87, 29, 158, 34);
			BtnStyle.second(btnDashboard);
			btnDashboard.setFont(Fonts.custom);
			btnDashboard.addActionListener(this);
			
		
			btnProductos = new JButton("Productos");
			btnProductos.setBounds(87, 104, 158, 34);
			BtnStyle.second(btnProductos);
			btnProductos.setFont(Fonts.custom);
			
			//btnProductos.addActionListener(e -> navigation.goTo(Modulo.PRODUCTOS));
			
			btnVenta = new JButton("Ventas");
			btnVenta.setBounds(87, 173, 158, 34);
			BtnStyle.second(btnVenta);
			btnVenta.setFont(Fonts.custom);
			//btnVenta.addActionListener(e -> navigation.goTo(Modulo.VENTAS));
		
			btnFactura = new JButton("Facturas");
			btnFactura.setBounds(87, 242, 158, 34);
			BtnStyle.second(btnFactura);
			btnFactura.setFont(Fonts.custom);
			//btnFactura.addActionListener(e -> navigation.goTo(Modulo.FACTURACION));
			
			btnUsuarios = new JButton("Usuarios");
			btnUsuarios.setFont(Fonts.custom);
			BtnStyle.second(btnUsuarios);
			//btnUsuarios.addActionListener(e -> navigation.goTo(Modulo.ADMIN));
			btnUsuarios.setBounds(87, 302, 158, 34);
			
				
		}
		
		public String verificarRolUserMessagePersonalizado() {
			
			/*
			 * switch(SessionContext.get().getRolUsuarioLogueado()){
			 * 
			 * cambiar esto por los elementos ROL verdaderos 
			 *  Case ADMIN  -> "Nuevo Usuario";
			 *  Case VENDEDOR -> "Nueva Venta";
			 *  Case CAJERO -> "Nueva Factura";
			 *  Case ENCARGADORINVENTARIO -> "Nuevo Producto";
			 *  default :  return "No identificado";
			 * }
			 * 
			 * */
			
			return null;
		}
		
		
		private void crearPanelBotones() {
			
			panelBotones = new JPanel();
			panelBotones.setBackground(Color.white);
			panelBotones.setBounds(0, 205, 267, 408);
			panelBotones.setLayout(null);
		
			panelBotones.add(btnFactura);
			panelBotones.add(btnUsuarios);
			panelBotones.add(btnVenta);
			panelBotones.add(btnProductos);
			panelBotones.add(btnDashboard);
			
			panelBotones.add(lbIconCategorias);
			panelBotones.add(lbIconCasa);
			panelBotones.add(lbIconVentas);
			panelBotones.add(lbIconFacturas);
			panelBotones.add(lbIconUsuarios);
			
			
		}
		
	
		@SuppressWarnings("unused")
		private void mapearBotones() {
			
			mapearModulos =  new HashMap<>();
			mapearModulos.put(btnProductos, Modulo.PRODUCTOS);
			mapearModulos.put(btnVenta, Modulo.VENTAS);
			mapearModulos.put(btnFactura, Modulo.FACTURACION);
			mapearModulos.put(btnUsuarios, Modulo.ADMIN);
			
		}
		
		@SuppressWarnings("unused")
		private void aplicarPermisos() {
			
			mapearModulos.forEach((btn, modulo) ->{
				
				Permiso permisosNecesarios = modulo.getPermisoNecesarios();
				
				boolean permisoT =  SessionContext.get().tienePermisos(permisosNecesarios);
				
				btnProductos.setEnabled(permisoT);
				btnVenta.setEnabled(permisoT);
				btnFactura.setEnabled(permisoT);
				btnUsuarios.setEnabled(permisoT);
				
			});
				
		}
		
		
		
		private void  crearLabels() {
		
			
			//lblNuevoProducto = new JLabel(verificarRolUserMessagePersonalizado(););
		
			lblNuevoProducto = new JLabel("Nuevo Producto");
			lblNuevoProducto.setForeground(Color.BLACK);
			lblNuevoProducto.setBounds(10, 46, 117, 12);
			lblNuevoProducto.setFont(Fonts.custom);
			lblNuevoProducto.setBackground(Color.WHITE);
			lbCrearTitle = new JLabel("Crear");
			lbCrearTitle.setForeground(Color.BLACK);
			lbCrearTitle.setBackground(Color.WHITE);
			lbCrearTitle.setBounds(10, 24, 86, 12);
			
			lbCrearTitle.setFont(Fonts.custom);
	
			lbIconCasa = new JLabel("");
			lbIconCasa.setIcon(AssetManager.icon("casa.png", 32, 32));
			lbIconCasa.setBounds(20, 29, 32, 32);
		
			lbIconCategorias = new JLabel("");
			lbIconCategorias.setIcon(AssetManager.icon("categorias.png", 32, 32));
			lbIconCategorias.setBounds(20, 104, 32, 32);
			
			lbIconVentas = new JLabel("");
			lbIconVentas.setIcon(AssetManager.icon("ventas.png", 32, 32));
			lbIconVentas.setBounds(20, 173, 32, 32);
			
			lbIconFacturas = new JLabel("");
			lbIconFacturas.setIcon(AssetManager.icon("factura.png", 32, 32));
			lbIconFacturas.setBounds(20, 242, 32, 32);
		
			lbIconUsuarios = new JLabel("");
			lbIconUsuarios.setIcon(AssetManager.icon("user.png",32,32));
			lbIconUsuarios.setBounds(20, 302, 32, 32);
			
				
		}
		
		private void crearPanelCreate() {
			
			panelCrear = new RoundePanel(30);
			panelCrear.setBackground(Color.white);
			panelCrear.setBounds(24, 104, 223, 78);
			panelCrear.setLayout(null);
			
			panelCrear.add(btnAgregar);
			panelCrear.add(lblNuevoProducto);
			panelCrear.add(lbCrearTitle);
		}
	
	
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}

		public NavigationManager getNavigation() {
			return navigation;
		}

		public void setNavigation(NavigationManager navigation) {
			this.navigation = navigation;
		}
		
		
		
	}
	
	
