package view.modules.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;


import view.components.AssetManager;
import view.components.Fonts;
import view.components.RoundePanel;
import view.table.TableFactory;
import view.table.schemas.TableSchema;
import view.table.style.TableStyle;
import view.table.style.TableStyleConfigure;

public class DashboardAdmin extends JPanel implements ActionListener {

	/**
	 * 
	 * 
	 */
	
	private RoundePanel panelUsuarios, panelCategorias, panelVentas;
	private JPanel panelFUsuario,  panelfFacturacion,  panelFVentas;
	
	private JScrollPane scrollPanel;
	private static final long serialVersionUID = 1L;
	
	private JLabel lbUsuarioPanel, lbVentasPanelImg, lbPanelVentas, lbPanelFacturacionConteo,  lbFacturasPanelImg, lbPanelFacturacion,
	lbPanelVentaConteo, lbPanelUsuarios, lbPanelUsuariosConteo;
	
	
	private JButton btnEditarProducto, btnEliminar;
	private JPanel panelSearch, panelContenedorAccionesCrud;
	private JTextField textFieldSearch;
	private JLabel lbTotalRegister ;
	
	public DashboardAdmin () {
		
		setBackground(null);
		//panelMenuBtnCrud.setBounds(330, 125, 1040, 400);
		setLayout(null);
		crearCards();
		crearViewPort();
		crearPanelOperacionesCurd();
		
	}
	
	
//cards
	public void crearCards() {
		
		crearLabels();
		
		//usuarios
		panelUsuarios = new RoundePanel(10);
		panelUsuarios.setLayout(null);
		panelUsuarios.setBackground(null);
		panelUsuarios.setBounds(0, 10, 218, 81);
		add(panelUsuarios);
		
		panelFUsuario = new JPanel();
		panelFUsuario.setLayout(null);
		panelFUsuario.setBackground(new Color(114, 148, 226));
		panelFUsuario.setBounds(0, -2, 94, 83);
		panelUsuarios.add(panelFUsuario);
		panelFUsuario.add(lbUsuarioPanel);
		panelUsuarios.add(lbPanelUsuarios);
		panelUsuarios.add(lbPanelUsuariosConteo);
		
		//ventas
		
		panelVentas = new RoundePanel(10);
		panelVentas.setLayout(null);
		panelVentas.setBackground(null);
		panelVentas.setBounds(320, 10, 218, 81);
		add(panelVentas);
		
		panelFVentas = new JPanel();
		panelFVentas.setLayout(null);
		panelFVentas.setBackground(new Color(238, 32, 73));
		panelFVentas.setBounds(0, -2, 94, 83);
		panelVentas.add(panelFVentas);
		panelVentas.add(lbPanelVentaConteo);
		panelVentas.add(lbPanelVentas);
		panelFVentas.add(lbVentasPanelImg);
	
		//facturacion
		
		panelCategorias = new RoundePanel(10);
		panelCategorias.setLayout(null);
		panelCategorias.setBackground(null);
		panelCategorias.setBounds(650, 10, 218, 81);
		add(panelCategorias);
		
		panelfFacturacion = new JPanel();
		panelfFacturacion.setLayout(null);
		panelfFacturacion.setBackground(new Color(255, 128, 64));
		panelfFacturacion.setBounds(0, -2, 94, 83);
		panelCategorias.add(panelfFacturacion);
	
		panelfFacturacion.add(lbFacturasPanelImg);
		panelCategorias.add(lbPanelFacturacion);
		panelCategorias.add(lbPanelFacturacionConteo);
		
		
		
		
	}
	
	
	
	//labels
	public void crearLabels() {
		
		lbUsuarioPanel = new JLabel("");
		lbUsuarioPanel.setIcon(AssetManager.icon("user-interface.png", 64, 64));
		lbUsuarioPanel.setBounds(12, 10, 64, 64);
	
		lbPanelUsuarios = new JLabel("Usuarios");
		lbPanelUsuarios.setForeground(Color.BLACK);
		lbPanelUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lbPanelUsuarios.setFont(Fonts.custom);
		lbPanelUsuarios.setBounds(104, 46, 94, 12);
		
		//lbPanelUsuariosConteo = new JLabel(usuarios.size() + "");
		lbPanelUsuariosConteo = new JLabel("0");
		lbPanelUsuariosConteo.setForeground(Color.BLACK);
		lbPanelUsuariosConteo.setHorizontalAlignment(SwingConstants.CENTER);
		lbPanelUsuariosConteo.setFont(Fonts.custom);
		lbPanelUsuariosConteo.setBounds(104, 24, 94, 12);
		
		//ventas
		lbVentasPanelImg = new JLabel("");
		lbVentasPanelImg.setIcon(AssetManager.icon("bolsa-de-valores.png", 64,64));
		lbVentasPanelImg.setBounds(12, 10, 64, 64);
			
		lbPanelVentas = new JLabel("Ventas");
		lbPanelVentas.setForeground(Color.BLACK);
		lbPanelVentas.setHorizontalAlignment(SwingConstants.CENTER);
		lbPanelVentas.setFont(Fonts.custom);
		lbPanelVentas.setBounds(104, 48, 94, 12);
		
		//lbPanelVentaConteo = new JLabel(ventas.size() + "");
		lbPanelVentaConteo = new JLabel("0");
		lbPanelVentaConteo.setForeground(Color.BLACK);
		lbPanelVentaConteo.setHorizontalAlignment(SwingConstants.CENTER);
		lbPanelVentaConteo.setFont(Fonts.custom);
		lbPanelVentaConteo.setBounds(104, 26, 94, 12);
	
		//facturas
		lbFacturasPanelImg = new JLabel("");
		lbFacturasPanelImg.setIcon(AssetManager.icon("banca-electronica.png", 64, 64));
		lbFacturasPanelImg.setBounds(12, 10, 84, 73);
		
		lbPanelFacturacion = new JLabel("Facturación ");
		lbPanelFacturacion.setHorizontalAlignment(SwingConstants.CENTER);
		lbPanelFacturacion.setFont(Fonts.custom);
		lbPanelFacturacion.setForeground(Color.BLACK);
		lbPanelFacturacion.setBounds(104, 49, 94, 12);
	
		//lbPanelFacturacionConteo = new JLabel(facturas.size() + ""); //
		lbPanelFacturacionConteo = new JLabel("0");
		lbPanelFacturacionConteo.setForeground(Color.BLACK);
		lbPanelFacturacionConteo.setHorizontalAlignment(SwingConstants.CENTER);
		lbPanelFacturacionConteo.setFont(Fonts.custom);
		lbPanelFacturacionConteo.setBounds(104, 27, 94, 12);
		
	}
	
	//table correspondiente
	public void crearViewPort() {
		
		///obtener lista de usuarios
		//
		// aqui solo va el de usuarios 
		
		//List<Usuarios> data = new ArrayList<>();
	
//		TableSchema<Usuarios> schema = UsuariosSchema.create();
//		JTable table = TableFactory.createTable(data, schema);
//		table.setBackground(TableStyleConfigure.COLOR_ROW_BG);
//		
//		table.setShowVerticalLines(true);
//		table.setGridColor(new Color(0xE5E7EB));
//		table.setBorder(BorderFactory.createEmptyBorder());
//		table.setShowGrid(false);
//		table.setIntercellSpacing(new Dimension(0, 0));
//		
//	    TableStyle.apply(table);
//		table.setBounds(0, 0,  250, 253);
//		
//		scrollPanel = new JScrollPane(table);
//		scrollPanel.setBackground(null);
//		scrollPanel.setBorder(null);
//		scrollPanel.setBorder(null);
//		scrollPanel.setOpaque(false);
//		scrollPanel.getViewport().setBorder(null);
//		scrollPanel.getViewport().setBackground(Color.WHITE);
//		scrollPanel.getViewport().setOpaque(false);
//		scrollPanel.setBounds(10, 161,250, 253);
//	
//		UIManager.put("ScrollBar.showButtons", false);
//		UIManager.put("ScrollBar.width", 12);
//		add(scrollPanel);
	
		
	}
	
	
	//opciones crud
	
	
		public void crearBtns() {
		
		
		btnEditarProducto = new JButton();
		btnEditarProducto.setText("Editar");
		btnEditarProducto.setIcon(AssetManager.icon("editar.png", 18, 18));
		btnEditarProducto.setBounds(0, 0, 120, 35);
		btnEditarProducto.addActionListener(this);
		btnEditarProducto.setFocusPainted(false);
		btnEditarProducto.setIconTextGap(6);
		btnEditarProducto.setBorder(BorderFactory.createLineBorder(new Color(88, 177, 237), 2, true));
		btnEditarProducto.setForeground(new Color(88, 177, 237));
		btnEditarProducto.setBackground(null);
		btnEditarProducto.putClientProperty("JButton.arc", 8);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(null);
		btnEliminar.setForeground(new Color(255, 90, 90));
		btnEliminar.setBorder(BorderFactory.createLineBorder(new Color(255,120, 120), 2, true));
		btnEliminar.putClientProperty("JButton.arc",20);
		btnEliminar.setFocusPainted(false);
		btnEliminar.setIcon(AssetManager.icon("borrar.png", 18, 18));
		btnEliminar.setBounds(150, 0, 120, 35);
		btnEliminar.setIconTextGap(6);
		btnEliminar.setFont(Fonts.custom);
		btnEliminar.addActionListener(this);
		
	}
	public void crearPanelOperacionesCurd() {
		
		panelContenedorAccionesCrud = new JPanel();
		panelContenedorAccionesCrud.setLayout(null);
		panelContenedorAccionesCrud.setOpaque(true);
		panelContenedorAccionesCrud.setBackground(null);
		panelContenedorAccionesCrud.setBounds(10, 110, 1043, 36);
	
		crearPanelBuscar();
		crearBtns();
		panelContenedorAccionesCrud.add(btnEditarProducto);
		panelContenedorAccionesCrud.add(btnEliminar);
		
		panelContenedorAccionesCrud.add(panelSearch);
		
		add(panelContenedorAccionesCrud);
			
		
	}
	
	public void crearPanelBuscar() {
		
		panelSearch = new JPanel();
		panelSearch.setBounds(760, 0, 246, 32);
		panelSearch.setBackground(null);
		panelSearch.setLayout(null);
		
		//lbTotalRegister = new JLabel(usuarios.size() + "");
		lbTotalRegister = new JLabel("0");
		lbTotalRegister.setBounds(0, 0, 45, 32);
		lbTotalRegister.setFont(Fonts.custom);
		lbTotalRegister.setForeground(Color.BLACK);
		panelSearch.add(lbTotalRegister);
		
		textFieldSearch = new JTextField();
		textFieldSearch.setText("Search");
		textFieldSearch.setBackground(null);
		textFieldSearch.setBounds(57, 4, 190, 26);
		textFieldSearch.setColumns(10);
		panelSearch.add(textFieldSearch);
		
		add(panelSearch);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
