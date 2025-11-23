package view.modules.products;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import control.ProductoController;
import model.Oferta;
import model.Producto;
import view.components.AssetManager;
import view.components.Fonts;
import view.table.TableFactory;
import view.table.schemas.OfertaSchema;
import view.table.schemas.ProductosSchema;
import view.table.schemas.TableSchema;
import view.table.style.TableStyle;
import view.table.style.TableStyleConfigure;

public class ProductsModule extends JPanel implements ActionListener {

	private JButton btnEditarProducto, btnEliminar, btnEditarOferta, 
	btnCrearOferta, btnEliminarOferta;
	private JPanel panelSearch, panelOperacionesOferta,  panelContenedorAccionesCrud;
	private JTextField textFieldSearch, textFieldSearchOferta;
	private JLabel lbTotalRegister, lbCantidadProductosStockBajo;
	
	private ProductoController productos;
	
	//private OfertasController ofertas;
	
	private JScrollPane scrollPaneProductos, scrollPaneAlertCantidad, scrollPaneOfertas ;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public  ProductsModule() {
		
		setLayout(null);
		setBackground(null);
		productos = new ProductoController();
		crearPanelOperacionesCurd();
		crearPanelViewPortProductos();
		crearPanelViewPortOfertas();
		crearPanelStockBajo();
		crearPanelOperacionesOferta();
		
	}
	
	
	public void crearPanelViewPortProductos() {
		
		
		List<Producto> data =  productos.listarProductos();
		
		TableSchema<Producto> schema = ProductosSchema.create();
		JTable table = TableFactory.createTable(data, schema);
		table.setBackground(TableStyleConfigure.COLOR_ROW_BG);
		
		table.setShowVerticalLines(true);
		table.setGridColor(new Color(0xE5E7EB));
		table.setBorder(BorderFactory.createEmptyBorder());
		table.setShowGrid(false);
		table.setIntercellSpacing(new Dimension(0, 0));
		
	    TableStyle.apply(table);
		table.setBounds(0, 0,  250, 253);

		scrollPaneProductos = new JScrollPane(table);
		scrollPaneProductos.setBounds(10, 107, 481, 320);
		scrollPaneProductos.getViewport().setBorder(null);
		scrollPaneProductos.getViewport().setBackground(null);
		scrollPaneProductos.setOpaque(false);
		scrollPaneProductos.getViewport().setOpaque(false);
		UIManager.put("ScrollBar.showButtons", false);
		UIManager.put("ScrollBar.width", 12);
		
		add(scrollPaneProductos);
		
	}
	
	public void crearPanelViewPortOfertas() {
		
		
		//List<Oferta> data =  ofertas.listarOfertas();
		
		
//		TableSchema<Oferta> schema = OfertaSchema.create();
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

			
		 scrollPaneOfertas = new JScrollPane(/*table*/);
		 scrollPaneOfertas.getViewport().setBorder(null);
		 scrollPaneOfertas.getViewport().setBackground(null);
		 scrollPaneOfertas.setOpaque(false);
		 scrollPaneOfertas.getViewport().setOpaque(false);
		 scrollPaneOfertas.setBounds(515, 107, 388, 207);
		 
		 	UIManager.put("ScrollBar.showButtons", false);
			UIManager.put("ScrollBar.width", 12);
			
		 add(scrollPaneOfertas);
		
	}
	
	public void crearPanelOperacionesOferta() {
		
		panelOperacionesOferta = new JPanel();
		panelOperacionesOferta.setOpaque(true);
		panelOperacionesOferta.setBackground(null);
		panelOperacionesOferta.setBounds(515, 71, 388, 34);
		panelOperacionesOferta.setLayout(null);
		
		btnCrearOferta = new JButton();
		btnCrearOferta.setBorder(BorderFactory.createLineBorder(new Color(73, 230, 115), 2, true));
		btnCrearOferta.addActionListener(this);
		btnCrearOferta.setIcon(AssetManager.icon("etiqueta.png", 24, 24));
		btnCrearOferta.setBounds(75, 0, 35, 32);
		btnCrearOferta.setBackground(null);
		btnCrearOferta.setOpaque(true);
		panelOperacionesOferta.add(btnCrearOferta);
		
		btnEliminarOferta = new JButton();
		btnEliminarOferta.setBackground(null);
		btnEliminarOferta.setOpaque(true);
		btnEliminarOferta.setBorder(BorderFactory.createLineBorder(new Color(255,120, 120), 2, true));
		btnEliminarOferta.addActionListener(this);
		btnEliminarOferta.setIcon(AssetManager.icon("borrar.png", 24, 24));
		btnEliminarOferta.setBounds(152, 0, 35, 32);
		panelOperacionesOferta.add(btnEliminarOferta);
		
		btnEditarOferta = new JButton();
		btnEditarOferta.setBackground(null);
		btnEditarOferta.setOpaque(true);
		btnEditarOferta.addActionListener(this);
		btnEditarOferta.setBorder(BorderFactory.createLineBorder(new Color(88, 177, 237), 2, true));
		btnEditarOferta.setIcon(AssetManager.icon("editar.png", 24, 24));
		btnEditarOferta.setBounds(0, 0, 35, 32);
		panelOperacionesOferta.add(btnEditarOferta);
		
		textFieldSearchOferta = new JTextField();
		textFieldSearchOferta.setText("Search");
		textFieldSearchOferta.setColumns(10);
		textFieldSearchOferta.setBounds(230, 6, 158, 24);
		textFieldSearchOferta.setBackground(null);
		panelOperacionesOferta.add(textFieldSearchOferta);
		
		add(panelOperacionesOferta);
	}
	
	public void crearPanelStockBajo() {
		
		//descomentar cuando se cree el metodo que obtiene productos con stock bajo
		
//		List<Producto> data =  productos.listarProductosStockBajo();
//		
//		TableSchema<Producto> schema = ProductosSchemaStockBajo.create();
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

		
		scrollPaneAlertCantidad = new JScrollPane();
		scrollPaneAlertCantidad.setBounds(939, 106, 141, 125);
		scrollPaneAlertCantidad.getViewport().setBorder(null);
		scrollPaneAlertCantidad.getViewport().setBackground(null);
		scrollPaneAlertCantidad.setOpaque(false);
		scrollPaneAlertCantidad.getViewport().setOpaque(false);
		add(scrollPaneAlertCantidad);
		
		lbCantidadProductosStockBajo = new JLabel("Stock bajo: ");
		lbCantidadProductosStockBajo.setBounds(939, 237, 141, 35);
		lbCantidadProductosStockBajo.setForeground(Color.BLACK);
		lbCantidadProductosStockBajo.setFont(Fonts.custom);
		add(lbCantidadProductosStockBajo);
		

	}
	
	
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
		panelContenedorAccionesCrud.setBounds(10, 10, 1043, 36);
	
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
