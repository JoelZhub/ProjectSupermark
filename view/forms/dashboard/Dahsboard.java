package view.forms.dashboard;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import view.components.RoundePanel;
import view.forms.Auth.Login;
import view.forms.CRUD.CrearProducto;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import utils.AssetManager;
import utils.BtnStyle;
import utils.Fonts;
import utils.FrameDragger;

public class Dahsboard implements ActionListener {

	private JFrame frame;
	
	//btns
	private JButton btnAgregar, btnDashboard, btnCategorias, btnVenta, 
	btnFactura, btnClose, btnEditarProducto, btnEliminar, btnBuscar, btnUsuarios;
	
	//recibir componente table ya llenada
	
	private JTable tableProductos;
	
	//recibir metodo crud que se encarga de crear el form de productos
	
	private CrearProducto crearProducto;
	
	private Map<Object,Runnable> acciones;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
					FlatLightLaf.setup();
					Dahsboard window = new Dahsboard();
					
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Dahsboard() {
		initialize();
	}

	
	private void initialize() {
		
		
		frame = new JFrame();
		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\img\\iconApp.png"));
	
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 1100, 600);
		
		new FrameDragger(frame);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		crearProducto = new CrearProducto();
		acciones = new HashMap<>();
		
		//panels
		JPanel panelFondo = new JPanel();
		panelFondo.setBackground(new Color(255, 255, 255));
		panelFondo.setBounds(0, 0, 1100, 610);
		frame.getContentPane().add(panelFondo);
		panelFondo.setLayout(null);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(255, 255, 255));
		panelMenu.setBounds(0, 0, 277, 600);
		panelFondo.add(panelMenu);
		panelMenu.setLayout(null);
		
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(new Color(255, 255, 255));
		panelBotones.setBounds(0, 192, 267, 408);
		panelMenu.add(panelBotones);
		panelBotones.setLayout(null);

		JPanel panelCrear = new RoundePanel(30);
		panelCrear.setBackground(new Color(255, 255, 255));
		panelCrear.setBounds(24, 104, 223, 78);
		panelMenu.add(panelCrear);
		panelCrear.setLayout(null);
		
		JPanel panelBanner = new RoundePanel(50);
		panelBanner.setBackground(new Color(255, 255, 255));
		panelBanner.setBounds(298, 55, 777, 129);
		panelFondo.add(panelBanner);
		panelBanner.setLayout(null);
		

		RoundePanel panelVentas = new RoundePanel(10);
		panelVentas.setBackground(new Color(255, 255, 255));
		panelVentas.setBounds(560, 202, 219, 82);
		panelFondo.add(panelVentas);
		panelVentas.setLayout(null);
		
		JPanel panelFVentas = new JPanel();
		panelFVentas.setBackground(new Color(238, 32, 73));
		panelFVentas.setBounds(0, -2, 94, 83);
		panelVentas.add(panelFVentas);
		panelFVentas.setLayout(null);
		

		JPanel panelProducto = new RoundePanel(10);
		panelProducto.setBackground(new Color(255, 255, 255));
		panelProducto.setBounds(310, 202, 219, 82);
		
		panelFondo.add(panelProducto);
		panelProducto.setLayout(null);
		
		JPanel panelFproducto = new JPanel();
		panelFproducto.setBackground(new Color(114, 148, 226));
		panelFproducto.setBounds(0, -2, 94, 83);
		panelProducto.add(panelFproducto);
		panelFproducto.setLayout(null);
		
		
		RoundePanel panelCategorias = new RoundePanel(10);
		panelCategorias.setBackground(new Color(255, 255, 255));
		panelCategorias.setBounds(810, 202, 219, 82);
		panelFondo.add(panelCategorias);
		panelCategorias.setLayout(null);
		
		JPanel panelfFacturacion = new JPanel();
		panelfFacturacion.setBackground(new Color(255, 128, 64));
		panelfFacturacion.setBounds(0, -2, 94, 83);
		panelCategorias.add(panelfFacturacion);
		panelfFacturacion.setLayout(null);
		
		JPanel panelContenido = new JPanel();
		panelContenido.setBackground(new Color(255, 255, 255));
		panelContenido.setBounds(300, 307, 790, 281);
		panelFondo.add(panelContenido);
		panelContenido.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 61, 779, 220);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.getViewport().setBackground(Color.WHITE);
		panelContenido.add(scrollPane);
		
		tableProductos = new JTable();
		tableProductos.setBackground(new Color(255, 255, 255));
		tableProductos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"IdProducto", "Producto", "Precio", "Categoria", "Cantidad", "Unidad", "Oferta", "Detalles"
			}
		));
		tableProductos.setOpaque(false);
		tableProductos.setBackground(null);
		JTableHeader header = tableProductos.getTableHeader();
		header.setFont(Fonts.custom);
		scrollPane.setViewportView(tableProductos);
		
		//labels
		JLabel lbTitulo = new JLabel("SuperMarket");
		lbTitulo.setBounds(92, 38, 175, 36);
		lbTitulo.setFont(Fonts.bold);
		panelMenu.add(lbTitulo);
				
		JLabel lbCrearTitle = new JLabel("Crear");
		lbCrearTitle.setBounds(10, 24, 86, 12);
		lbCrearTitle.setFont(Fonts.custom);
		panelCrear.add(lbCrearTitle);
		
		JLabel lblNuevoProducto = new JLabel("Nuevo Producto");
		lblNuevoProducto.setBounds(10, 46, 117, 12);
		lblNuevoProducto.setFont(Fonts.custom);
		panelCrear.add(lblNuevoProducto);
		
		JLabel lbIconCasa = new JLabel("");
	
		lbIconCasa.setIcon(AssetManager.icon("casa.png", 32, 32));
		lbIconCasa.setBounds(20, 29, 32, 32);
		panelBotones.add(lbIconCasa);
		
		JLabel lbIconCategorias = new JLabel("");
		lbIconCategorias.setIcon(AssetManager.icon("categorias.png", 32, 32));
		lbIconCategorias.setBounds(20, 104, 32, 32);
		panelBotones.add(lbIconCategorias);
		
		JLabel lbIconVentas = new JLabel("");
		lbIconVentas.setIcon(AssetManager.icon("ventas.png", 32, 32));
		lbIconVentas.setBounds(20, 173, 32, 32);
		panelBotones.add(lbIconVentas);
		
		JLabel lbIconFacturas = new JLabel("");
		lbIconFacturas.setIcon(AssetManager.icon("factura.png", 32, 32));
		lbIconFacturas.setBounds(20, 242, 32, 32);
		panelBotones.add(lbIconFacturas);
		
		JLabel lbLogo = new JLabel("");
		lbLogo.setIcon(AssetManager.icon("logo.png", 64, 64));
		lbLogo.setBounds(10, 17, 84, 64);
		panelMenu.add(lbLogo);
		
	
		JLabel lbSaludo1 = new JLabel("Bienvenido,");
		lbSaludo1.setBounds(34, 47, 221, 22);
		lbSaludo1.setFont(Fonts.bold);
		panelBanner.add(lbSaludo1);
		
		JLabel lbSaludo2 = new JLabel("Admin");
		lbSaludo2.setBounds(34, 73, 175, 21);
		lbSaludo2.setFont(Fonts.bold);
		panelBanner.add(lbSaludo2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(AssetManager.icon("admin1.png", 124, 124));
		lblNewLabel.setBounds(611, 10, 138, 107);
		panelBanner.add(lblNewLabel);
	
		JLabel lbProductoPanelImg = new JLabel("");
		lbProductoPanelImg.setIcon(AssetManager.icon("embalaje.png", 84, 73));
		lbProductoPanelImg.setBounds(6, 8, 84, 73);
		panelFproducto.add(lbProductoPanelImg);
		
		JLabel lbPanelProductos = new JLabel("Productos");
		lbPanelProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lbPanelProductos.setBounds(104, 46, 94, 12);
		lbPanelProductos.setFont(Fonts.custom);
		panelProducto.add(lbPanelProductos);
		
		JLabel lbPanelProductosConteo = new JLabel("0");
		lbPanelProductosConteo.setHorizontalAlignment(SwingConstants.CENTER);
		lbPanelProductosConteo.setBounds(104, 24, 94, 12);
		lbPanelProductosConteo.setFont(Fonts.custom);
		panelProducto.add(lbPanelProductosConteo);
		
		JLabel lbVentasPanelImg = new JLabel("");
		lbVentasPanelImg.setIcon(AssetManager.icon("bolsa-de-valores.png", 84, 73));
		lbVentasPanelImg.setBounds(6, 8, 84, 73);
		panelFVentas.add(lbVentasPanelImg);
		
		JLabel lbPanelVentas = new JLabel("Ventas");
		lbPanelVentas.setHorizontalAlignment(SwingConstants.CENTER);
		lbPanelVentas.setBounds(104, 48, 94, 12);
		lbPanelVentas.setFont(Fonts.custom);
		panelVentas.add(lbPanelVentas);
		
		JLabel lbPanelVentaConteo = new JLabel("0");
		lbPanelVentaConteo.setHorizontalAlignment(SwingConstants.CENTER);
		lbPanelVentaConteo.setBounds(104, 26, 94, 12);
		lbPanelVentaConteo.setFont(Fonts.custom);
		panelVentas.add(lbPanelVentaConteo);
		
		JLabel lbFacturasPanelImg = new JLabel("");
		lbFacturasPanelImg.setIcon(AssetManager.icon("banca-electronica.png", 84, 73));
		lbFacturasPanelImg.setBounds(6, 8, 84, 73);
		panelfFacturacion.add(lbFacturasPanelImg);
		
		JLabel lbPanelFacturacion = new JLabel("Facturación ");
		lbPanelFacturacion.setHorizontalAlignment(SwingConstants.CENTER);
		lbPanelFacturacion.setBounds(104, 49, 94, 12);
		lbPanelFacturacion.setFont(Fonts.custom);
		panelCategorias.add(lbPanelFacturacion);
		
		JLabel lbPanelFacturacionConteo = new JLabel("0");
		lbPanelFacturacionConteo.setHorizontalAlignment(SwingConstants.CENTER);
		lbPanelFacturacionConteo.setBounds(104, 27, 94, 12);
		lbPanelFacturacionConteo.setFont(Fonts.custom);
		panelCategorias.add(lbPanelFacturacionConteo);
		
		JLabel lbTituloProductos = new JLabel("Productos");
		lbTituloProductos.setBounds(0, 10, 196, 22);
		lbTituloProductos.setFont(Fonts.custom);
		panelContenido.add(lbTituloProductos);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(566, 0, 213, 40);
		panelContenido.add(panel);
		panel.setLayout(null);
		
		btnEditarProducto = new JButton("");
		btnEditarProducto.setIcon(AssetManager.icon("editar.png", 30, 30));
		btnEditarProducto.setBounds(0, 0, 30, 30);
		btnEditarProducto.addActionListener(this);
		BtnStyle.flat(btnEditarProducto);
		panel.add(btnEditarProducto);
		
		btnBuscar = new JButton("");
		btnBuscar.setIcon(AssetManager.icon("lupa.png", 30, 30));
		btnBuscar.setBounds(81, 0, 30, 30);
		btnBuscar.addActionListener(this);
		BtnStyle.flat(btnBuscar);
		panel.add(btnBuscar);
		
		btnEliminar = new JButton("");
		btnEliminar.setIcon(AssetManager.icon("eliminar.png", 30, 30));
		btnEliminar.setBounds(159, 0, 30, 30);
		BtnStyle.flat(btnEliminar);
		btnEliminar.addActionListener(this);
		panel.add(btnEliminar);
	
		LocalDateTime time  = LocalDateTime.now();
		String date = time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
		JLabel lbDate = new JLabel(date);
		lbDate.setBounds(301, 10, 228, 26);
		lbDate.setFont(Fonts.custom);
		panelFondo.add(lbDate);
		
		
		//separadores
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setForeground(new Color(0, 0, 0));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(280, 0, 2, 600);
		panelFondo.add(separator);
		
		//buttons
		btnAgregar = new JButton("");
		BtnStyle.flat(btnAgregar);
		btnAgregar.setBackground(new Color(255, 255, 255));
		btnAgregar.addActionListener(this);
		btnAgregar.setIcon(AssetManager.icon("agregar.png", 70,70));
		btnAgregar.setBounds(134, 4, 70, 70);
		
		panelCrear.add(btnAgregar);
		
		btnDashboard = new JButton("Dashboard");
		btnDashboard.setBounds(87, 29, 158, 34);
		BtnStyle.second(btnDashboard);
		btnDashboard.setFont(Fonts.custom);
		btnDashboard.addActionListener(this);
		panelBotones.add(btnDashboard);
		
		btnCategorias = new JButton("Categorias");
		btnCategorias.setBounds(87, 104, 158, 34);
		BtnStyle.second(btnCategorias);
		btnCategorias.setFont(Fonts.custom);
		btnCategorias.addActionListener(this);
		
		panelBotones.add(btnCategorias);
		
		btnVenta = new JButton("Ventas");
		btnVenta.setBounds(87, 173, 158, 34);
		BtnStyle.second(btnVenta);
		btnVenta.setFont(Fonts.custom);
		btnVenta.addActionListener(this);
		panelBotones.add(btnVenta);
		
		btnFactura = new JButton("Facturas");
		btnFactura.setBounds(87, 242, 158, 34);
		BtnStyle.second(btnFactura);
		btnFactura.setFont(Fonts.custom);
		btnFactura.addActionListener(this);
		panelBotones.add(btnFactura);
		
		btnClose = new JButton("");
		BtnStyle.flat(btnClose);
		btnClose.setIcon(new ImageIcon("resources\\img\\clos.png"));
		btnClose.setBounds(841, 15, 65, 30);
		btnClose.addActionListener(this);
		panelFondo.add(btnClose);
		
		btnUsuarios = new JButton("Usuarios");
		btnUsuarios.setFont(Fonts.custom);
		BtnStyle.second(btnUsuarios);
		btnUsuarios.setBounds(87, 302, 158, 34);
		panelBotones.add(btnUsuarios);
		
		acciones.put(btnAgregar, this::openCreate);
		acciones.put(btnClose, this::closeWindows);
		acciones.put(btnFactura, this::openBill);
		acciones.put(btnVenta, this::openSales);
		acciones.put(btnCategorias, this::openCategorys);
		
		
		JLabel lbIconUsuarios = new JLabel("");
		lbIconUsuarios.setIcon(AssetManager.icon("user.png",32,32));
		lbIconUsuarios.setBounds(20, 302, 32, 32);
		panelBotones.add(lbIconUsuarios);
		acciones.put(btnEditarProducto, this::openEditProduct);
		acciones.put(btnEliminar, this::openRemoveProduct);
		acciones.put(btnBuscar, this::openSearchProduct);
		
	}

	public void openRemoveProduct() {
		
	}
	public void openSearchProduct() {
		
	}
	public void openEditProduct() {
		
	}
	public void openCreate() {
		
		CrearProducto crearProducto = new CrearProducto();
		crearProducto.setVisible(true);
	}
	
	public void openCategorys() {
		
	}
	public void openSales() {
		
		
	}
	public void openBill() {
		
	}
	public void closeWindows() {
//		frame.dispose();
//		Login login = new Login();
//		login.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Runnable accion =  acciones.get(e.getSource());
		if(accion != null) accion.run();
	}
}
