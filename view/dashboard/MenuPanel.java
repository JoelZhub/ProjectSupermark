	package view.dashboard;
	
	import java.awt.Color;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.util.HashMap;
	import java.util.Map;
	import model.Modulo;
import model.OperationType;
import model.Permiso;
import model.SubModulo;
import session.SessionContext;

	import javax.swing.JButton;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.SwingUtilities;

	import view.AplicationContext;
import view.components.AssetManager;
	import view.components.BtnStyle;
	import view.components.Fonts;
	import view.components.Messages;
	import view.components.RoundePanel;
import view.formFactory.FormFactory;
	
	public class MenuPanel extends JPanel implements ActionListener {
		
		private static final long serialVersionUID = 1L;
		private final AplicationContext context;
	
		private Map<JButton, Modulo> mapearModulos; 
		
		private RoundePanel panelCrear;
		
		private JPanel panelBotones;
			
		@SuppressWarnings("unused")
		private String messageCrear = "";
		
		private JButton btnAgregar, btnVenta, btnFactura, btnCliente, btnUsuarios,  btnProductos, btnFinanzas, btnReportes;
		
		private JLabel lbTitulo, lbIconUsuarios,lbCliente,  lbIconCategorias, lbIconVentas, lbIconFacturas, lbLogo, lbCrearTitle,  lblNuevoProducto, lbIconFinanzas, lbIconReportes;
		
		
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
			
			btnFinanzas = new JButton("Finanzas");
			btnFinanzas.setBounds(87, 311, 158, 34);
			BtnStyle.second(btnFinanzas);
			btnFinanzas.setFont(Fonts.custom);
			btnFinanzas.addActionListener(e -> {
				context.getNavigation().goTo(Modulo.FINANZAS);
				verificarModuloUserMessagePersonalizado();
			});
			
			btnCliente = new JButton("Clientes");
			btnCliente.setBounds(87, 370, 158, 34);
			BtnStyle.second(btnCliente);
			btnCliente.setFont(Fonts.custom);
			btnCliente.addActionListener(e -> {
				context.getNavigation().goTo(Modulo.CLIENTE);
				verificarModuloUserMessagePersonalizado();
			});

			btnReportes = new JButton("Reportes");
			btnReportes.setBounds(87, 420, 158, 34);
			BtnStyle.second(btnReportes);
			btnReportes.setFont(Fonts.custom);
			btnReportes.addActionListener(e -> {
				context.getNavigation().goTo(Modulo.REPORTES);
				verificarModuloUserMessagePersonalizado();
			});
			
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
		                        lblNuevoProducto.setText("Solo lectura");
		                        break;
		                    case  CLIENTE:
		                    	  lblNuevoProducto.setText("Nuevo Cliente");
			                        break;
		                    	
							case FINANZAS:
								lblNuevoProducto.setText("Nuevo movimiento");
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
			panelBotones.setBounds(0, 205, 267, 460);
			panelBotones.setLayout(null);
		
			panelBotones.add(btnFactura);
			panelBotones.add(btnUsuarios);
			panelBotones.add(btnVenta);
			panelBotones.add(btnProductos);
			panelBotones.add(btnFinanzas);
			panelBotones.add(btnCliente);
			panelBotones.add(btnReportes);
			
			panelBotones.add(lbIconCategorias);
			panelBotones.add(lbCliente);
			panelBotones.add(lbIconVentas);
			panelBotones.add(lbIconFacturas);
			panelBotones.add(lbIconUsuarios);
			panelBotones.add(lbIconFinanzas);
			panelBotones.add(lbIconReportes);
			
		}
		

		private void mapearBotones() {
			mapearModulos =  new HashMap<>();
			mapearModulos.put(btnProductos, Modulo.PRODUCTOS);
			mapearModulos.put(btnVenta, Modulo.VENTAS);
			mapearModulos.put(btnFactura, Modulo.FACTURACION);
			mapearModulos.put(btnUsuarios, Modulo.ADMIN);
			mapearModulos.put(btnFinanzas, Modulo.FINANZAS);
			mapearModulos.put(btnCliente, Modulo.CLIENTE);
			mapearModulos.put(btnReportes, Modulo.REPORTES);
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

			lbIconFinanzas = new JLabel("");
			lbIconFinanzas.setIcon(AssetManager.icon("finanzas.png", 32, 32));
			lbIconFinanzas.setBounds(20, 311, 32, 32);
			
			lbCliente = new JLabel("");
			lbCliente.setIcon(AssetManager.icon("cliente.png", 32, 32));
			lbCliente.setBounds(20, 370, 32, 32);

			lbIconReportes = new JLabel("");
			lbIconReportes.setIcon(AssetManager.icon("reportes.png", 32, 32));
			lbIconReportes.setBounds(20, 420, 32, 32);
				
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
				if(context.getNavigation().getModuloActual() == Modulo.VENTAS) {
					new Messages(dahsboard, "Zona informativa. Edición no habilitada en este módulo").messageError();
					return;
				}else {
					
					if(context.getNavigation().getModuloActual() == Modulo.PRODUCTOS) {

						if(context.getProveedorController().listarProveedores().size() == 0) {
							new Messages(dahsboard, "Debe existir un proveedor antes de crear un nuevo producto").messageError();
							return;
							
						}else {
							new FormFactory(context).crearForm(context.getNavigation().getModuloActual(), SubModulo.PRODUCTO,  OperationType.CREATE, dahsboard).setVisible(true);
						}
						
					}else {
						new FormFactory(context).crearForm(context.getNavigation().getModuloActual(), null, OperationType.CREATE, dahsboard).setVisible(true);
					}
				}
			}
			
			
			
		}

		
		
		
		
	}
	
	
