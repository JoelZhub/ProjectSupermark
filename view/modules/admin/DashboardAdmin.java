package view.modules.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;


import view.components.AssetManager;
import view.components.Fonts;
import view.components.RoundePanel;
import view.table.TableFactory;
import view.table.schemas.TableSchema;
import view.table.style.TableStyle;
import view.table.style.TableStyleConfigure;

public class DashboardAdmin extends JPanel {

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
	
	public DashboardAdmin () {
		setBackground(null);
		//panelMenuBtnCrud.setBounds(330, 125, 1040, 400);
		setLayout(null);
		crearCards();
		crearViewPort();
		
	}
	
	
	
	
	public void crearCards() {
		
		crearLabels();
		
		//usuarios
		panelUsuarios = new RoundePanel(10);
		panelUsuarios.setLayout(null);
		panelUsuarios.setBackground(Color.WHITE);
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
		panelVentas.setBackground(Color.WHITE);
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
		panelCategorias.setBackground(Color.WHITE);
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
	
	
	
	
	public void crearLabels() {
		
		lbUsuarioPanel = new JLabel("");
		lbUsuarioPanel.setIcon(AssetManager.icon("user-interface.png", 64, 64));
		lbUsuarioPanel.setBounds(12, 10, 64, 64);
	
		lbPanelUsuarios = new JLabel("Usuarios");
		lbPanelUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lbPanelUsuarios.setFont(null);
		lbPanelUsuarios.setBounds(104, 46, 94, 12);
		
		//lbPanelUsuariosConteo = new JLabel(usuarios.size() + "");
		lbPanelUsuariosConteo = new JLabel("0");
		lbPanelUsuariosConteo.setHorizontalAlignment(SwingConstants.CENTER);
		lbPanelUsuariosConteo.setFont(null);
		lbPanelUsuariosConteo.setBounds(104, 24, 94, 12);
		
		
		lbVentasPanelImg = new JLabel("");
		lbVentasPanelImg.setIcon(AssetManager.icon("bolsa-de-valores.png", 64,64));
		lbVentasPanelImg.setBounds(12, 10, 64, 64);
			
		lbPanelVentas = new JLabel("Ventas");
		lbPanelVentas.setHorizontalAlignment(SwingConstants.CENTER);
		lbPanelVentas.setFont(null);
		lbPanelVentas.setBounds(104, 48, 94, 12);
		
		//lbPanelVentaConteo = new JLabel(ventas.size() + "");
		lbPanelVentaConteo = new JLabel("0");
		lbPanelVentaConteo.setHorizontalAlignment(SwingConstants.CENTER);
		lbPanelVentaConteo.setFont(null);
		lbPanelVentaConteo.setBounds(104, 26, 94, 12);
	
		lbFacturasPanelImg = new JLabel("");
		lbFacturasPanelImg.setIcon(AssetManager.icon("banca-electronica.png", 64, 64));
		lbFacturasPanelImg.setBounds(12, 10, 84, 73);
		
		lbPanelFacturacion = new JLabel("Facturación ");
		lbPanelFacturacion.setHorizontalAlignment(SwingConstants.CENTER);
		lbPanelFacturacion.setFont(null);
		lbPanelFacturacion.setBounds(104, 49, 94, 12);
	
		//lbPanelFacturacionConteo = new JLabel(facturas.size() + ""); //
		lbPanelFacturacionConteo = new JLabel("0");
		lbPanelFacturacionConteo.setHorizontalAlignment(SwingConstants.CENTER);
		lbPanelFacturacionConteo.setFont(null);
		lbPanelFacturacionConteo.setBounds(104, 27, 94, 12);
		
	}
	
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
	public void creartTable() {
		
		
	}

}
