package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import view.components.Fonts;
import view.components.FrameDragger;
import view.components.RoundePanel;
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
import java.time.LocalDateTime;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class dashboard implements ActionListener {

	private JFrame dashboardProductos;
	private JButton btnAgregar, btnDashboard, btnCategorias, btnVenta, btnFactura, btnClose;
	private JTable tableProductos;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				        if ("Nimbus".equals(info.getName())) {
				            javax.swing.UIManager.setLookAndFeel(info.getClassName());
				            break;
				        }
				    }
					dashboard window = new dashboard();
					window.dashboardProductos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public dashboard() {
		initialize();
	}

	
	private void initialize() {
		
		dashboardProductos = new JFrame();
		
		UIManager.put("Button.contentAreaFilled", false);
		UIManager.put("Button.borderPainted", false);
		UIManager.put("Button.focusPainted", false);
		UIManager.put("Table.background", Color.WHITE);
		UIManager.put("Table.scrollPaneBorder", null);
		UIManager.put("ScrollPane.background", Color.WHITE);
		UIManager.put("ScrollPane.viewportBorder", null);
		
		dashboardProductos.setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\img\\iconApp.png"));
		dashboardProductos.setResizable(false);
		dashboardProductos.setUndecorated(true);
		dashboardProductos.setBounds(100, 100, 1100, 600);
		new FrameDragger(dashboardProductos);
		dashboardProductos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dashboardProductos.getContentPane().setLayout(null);
		dashboardProductos.setLocationRelativeTo(null);
		
		//panels
		JPanel panelFondo = new JPanel();
		panelFondo.setBackground(new Color(255, 255, 255));
		panelFondo.setBounds(0, 0, 1100, 610);
		dashboardProductos.getContentPane().add(panelFondo);
		panelFondo.setLayout(null);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(255, 255, 255));
		panelMenu.setBounds(0, 0, 277, 600);
		panelFondo.add(panelMenu);
		panelMenu.setLayout(null);
		
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(new Color(255, 255, 255));
		panelBotones.setBounds(0, 214, 267, 376);
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
		lbIconCasa.setIcon(new ImageIcon("resources\\img\\casa.png"));
		lbIconCasa.setBounds(20, 41, 44, 34);
		panelBotones.add(lbIconCasa);
		
		JLabel lbIconCategorias = new JLabel("");
		lbIconCategorias.setIcon(new ImageIcon("resources\\img\\categorias.png"));
		lbIconCategorias.setBounds(20, 116, 44, 34);
		panelBotones.add(lbIconCategorias);
		
		JLabel lbIconVentas = new JLabel("");
		lbIconVentas.setIcon(new ImageIcon("resources\\img\\ventas.png"));
		lbIconVentas.setBounds(20, 185, 44, 34);
		panelBotones.add(lbIconVentas);
		
		JLabel lbIconFacturas = new JLabel("");
		lbIconFacturas.setIcon(new ImageIcon("resources\\img\\factura.png"));
		lbIconFacturas.setBounds(20, 254, 44, 34);
		panelBotones.add(lbIconFacturas);
		
		JLabel lbLogo = new JLabel("");
		lbLogo.setIcon(new ImageIcon("resources\\img\\logo.png"));
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
		lblNewLabel.setBounds(653, 0, 124, 129);
		panelBanner.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("resources\\img\\admin1.png"));
	
		
		JLabel lbProductoPanelImg = new JLabel("");
		lbProductoPanelImg.setIcon(new ImageIcon("resources\\img\\embalaje.png"));
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
		lbVentasPanelImg.setIcon(new ImageIcon("resources\\img\\bolsa-de-valores.png"));
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
		lbFacturasPanelImg.setIcon(new ImageIcon("resources\\img\\banca-electronica.png"));
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
		
		JButton btnEditarProducto = new JButton("");
		btnEditarProducto.setIcon(new ImageIcon("resources\\img\\editar.png"));
		btnEditarProducto.setBounds(0, 0, 51, 40);
		panel.add(btnEditarProducto);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon("resources\\img\\lupa.png"));
		btnBuscar.setBounds(81, 0, 51, 40);
		panel.add(btnBuscar);
		
		JButton btnEliminar = new JButton("");
		btnEliminar.setIcon(new ImageIcon("resources\\img\\eliminar.png"));
		btnEliminar.setBounds(159, 0, 51, 40);
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
		btnAgregar.setBackground(new Color(255, 255, 255));
		btnAgregar.setIcon(new ImageIcon("resources//img//agregar.png"));
		btnAgregar.setBounds(129, 4, 84, 67);
		panelCrear.add(btnAgregar);
		
		
		btnDashboard = new JButton("Dashboard");
		btnDashboard.setBounds(87, 41, 158, 34);
		btnDashboard.setFont(Fonts.custom);
		panelBotones.add(btnDashboard);
		
		btnCategorias = new JButton("Categorias");
		btnCategorias.setBounds(87, 116, 158, 34);
		btnCategorias.setFont(Fonts.custom);
		panelBotones.add(btnCategorias);
		
		btnVenta = new JButton("Ventas");
		btnVenta.setBounds(87, 185, 158, 34);
		btnVenta.setFont(Fonts.custom);
		panelBotones.add(btnVenta);
		
		btnFactura = new JButton("Facturas");
		btnFactura.setBounds(87, 254, 158, 34);
		btnFactura.setFont(Fonts.custom);
		panelBotones.add(btnFactura);
		
		btnClose = new JButton("");
		btnClose.setIcon(new ImageIcon("resources\\img\\clos.png"));
		btnClose.setBounds(1030, 5, 65, 30);
		btnClose.addActionListener(this);
		panelFondo.add(btnClose);
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnClose) {
			System.exit(0);
		}
	}
}
