	package view.dashboard;
	
	import java.awt.Color;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.util.HashMap;
	import java.util.Map;
	import model.Modulo;
	import model.Permiso;
	import model.Rol;
	import session.SessionContext;

	import javax.swing.JButton;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
import javax.swing.SwingUtilities;

	import view.AplicationContext;
	import view.FormFactory;
	import view.components.AssetManager;
	import view.components.BtnStyle;
	import view.components.Fonts;
import view.components.Messages;
import view.components.RoundePanel;
	
	public class MenuPanel extends JPanel implements ActionListener {
		
		private static final long serialVersionUID = 1L;
		private final AplicationContext context;
	
		private Map<JButton, Modulo> mapearModulos; 
		
		private RoundePanel panelCrear;
		
		private JPanel panelBotones;
			
		@SuppressWarnings("unused")
		private String messageCrear = "";
		
		private JButton btnAgregar, btnVenta, btnFactura, btnUsuarios,  btnProductos;
		
		private JLabel lbTitulo, lbIconUsuarios,  lbIconCategorias, lbIconVentas, lbIconFacturas, lbLogo, lbCrearTitle,  lblNuevoProducto;
		
		
		private Dahsboard dahsboard;
		
		public MenuPanel(AplicationContext context, Dahsboard dahsboard) {
	
			setLayout(null);
			setBackground(Color.WHITE);
			this.context = context;
			this.dahsboard = dahsboard;
			crearPanel();	
			
		
		}
		
		private void crearPanel() {
			
			crearLabels();
			crearBotonoes();
			mapearBotones();
			aplicarPermisos();
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
		
			btnProductos = new JButton("Productos");
			btnProductos.setBounds(87, 29, 158, 34);
			BtnStyle.second(btnProductos);
			btnProductos.setFont(Fonts.custom);			
			btnProductos.addActionListener(e -> {
				context.getNavigation().goTo(Modulo.PRODUCTOS);
				verificarModuloUserMessagePersonalizado();
				
			});
			
			
			btnVenta = new JButton("Ventas");
			btnVenta.setBounds(87, 104, 158, 34);
			BtnStyle.second(btnVenta);
			btnVenta.setFont(Fonts.custom);
			btnVenta.addActionListener(e ->  {
				context.getNavigation().goTo(Modulo.VENTAS);
				verificarModuloUserMessagePersonalizado();
			});
			
	
			btnFactura = new JButton("Facturas");
			btnFactura.setBounds(87, 173, 158, 34);
			BtnStyle.second(btnFactura);
			btnFactura.setFont(Fonts.custom);
			btnFactura.addActionListener(e -> {
				context.getNavigation().goTo(Modulo.FACTURACION);
				verificarModuloUserMessagePersonalizado();
			});
			
			btnUsuarios = new JButton("Usuarios");
			btnUsuarios.setFont(Fonts.custom);
			BtnStyle.second(btnUsuarios);
			btnUsuarios.addActionListener(e -> {
				context.getNavigation().goTo(Modulo.ADMIN);
				verificarModuloUserMessagePersonalizado();
			});
			
			btnUsuarios.setBounds(87, 242, 158, 34);
			
			
		}
		
		public void verificarModuloUserMessagePersonalizado() {
		    SwingUtilities.invokeLater(() -> {
		        if (context.getNavigation() == null) {
		            lblNuevoProducto.setText("No identificado");
		        } else {
		            Modulo m = context.getNavigation().getModuloActual();
		            if (m == null) {
		                lblNuevoProducto.setText("No identificado");
		            } else {
		                switch (m) {
		                    case ADMIN:
		                        lblNuevoProducto.setText("Nuevo usuario");
		                        break;
		                    case PRODUCTOS:
		                        lblNuevoProducto.setText("Nuevo producto");
		                        break;
		                    case FACTURACION:
		                        lblNuevoProducto.setText("Nueva factura");
		                        break;
		                    case VENTAS:
		                        lblNuevoProducto.setText("Inhabilitado");
		                        break;
		                    default:
		                        lblNuevoProducto.setText("No identificado");
		                        break;
		                }
		            }
		        }
		        
		        lblNuevoProducto.revalidate();
		        lblNuevoProducto.repaint();
		        dahsboard.revalidate();
		        dahsboard.repaint();
		        
		       
		    });
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
			
			panelBotones.add(lbIconCategorias);

			panelBotones.add(lbIconVentas);
			panelBotones.add(lbIconFacturas);
			panelBotones.add(lbIconUsuarios);
			
			
		}
		

		private void mapearBotones() {
			mapearModulos =  new HashMap<>();
			mapearModulos.put(btnProductos, Modulo.PRODUCTOS);
			mapearModulos.put(btnVenta, Modulo.VENTAS);
			mapearModulos.put(btnFactura, Modulo.FACTURACION);
			mapearModulos.put(btnUsuarios, Modulo.ADMIN);
			
		}
		
		private void aplicarPermisos() {
			
			mapearModulos.forEach((btn, modulo) ->{
				Permiso permisosNecesarios = modulo.getPermisoNecesarios();	
				boolean permisoT = true;
				if(SessionContext.get() != null) {
					permisoT = SessionContext.get().tienePermisos(permisosNecesarios);
				}
				
				btn.setEnabled(permisoT);
			});
				
		}
	
		private void  crearLabels() {
					

			lblNuevoProducto = new JLabel("");
			lblNuevoProducto.setForeground(Color.BLACK);
			lblNuevoProducto.setBounds(10, 46, 117, 12);
			lblNuevoProducto.setFont(Fonts.custom);
			lblNuevoProducto.setBackground(Color.WHITE);
			lbCrearTitle = new JLabel("Crear");
			lbCrearTitle.setForeground(Color.BLACK);
			lbCrearTitle.setBackground(Color.WHITE);
			lbCrearTitle.setBounds(10, 24, 86, 12);
			
			lbCrearTitle.setFont(Fonts.custom);
	
			lbIconCategorias = new JLabel("");
			lbIconCategorias.setIcon(AssetManager.icon("categorias.png", 32, 32));
			lbIconCategorias.setBounds(20, 29, 32, 32);
			
			lbIconVentas = new JLabel("");
			lbIconVentas.setIcon(AssetManager.icon("ventas.png", 32, 32));
			lbIconVentas.setBounds(20, 104, 32, 32);
			
			lbIconFacturas = new JLabel("");
			lbIconFacturas.setIcon(AssetManager.icon("factura.png", 32, 32));
			
			lbIconFacturas.setBounds(20, 173, 32, 32);
		
			lbIconUsuarios = new JLabel("");
			lbIconUsuarios.setIcon(AssetManager.icon("user.png",32,32));
			lbIconUsuarios.setBounds(20, 242, 32, 32);
			
				
		}
		
		private void crearPanelCreate() {
			
			panelCrear = new RoundePanel(30);
			panelCrear.setBackground(Color.white);
			panelCrear.setBounds(24, 104, 223, 78);
			panelCrear.setLayout(null);
			verificarModuloUserMessagePersonalizado();
			panelCrear.add(btnAgregar);
			panelCrear.add(lblNuevoProducto);
			panelCrear.add(lbCrearTitle);
		}
	
		
		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource() == btnAgregar) {
				if(SessionContext.get().getRolUsuarioLogueado() == Rol.VENDEDOR) {
					new Messages(dahsboard, "Acción no permitida para este perfil").messageError();
					return;
				}else {
					new FormFactory(context).crearForm(context.getNavigation().getModuloActual(), dahsboard).setVisible(true);;
				}
			}
			
			
			
		}

		
		
		
		
	}
	
	
