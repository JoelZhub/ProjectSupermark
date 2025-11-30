package view.modules.inventory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import model.Oferta;
import model.OperationType;
import model.Producto;
import model.Proveedor;
import model.SubModulo;
import view.AplicationContext;
import view.components.AssetManager;
import view.components.Filtros;
import view.components.Fonts;
import view.components.Messages;
import view.dashboard.Dahsboard;
import view.formFactory.FormFactory;
import view.table.TableFactory;
import view.table.UniversalTableModel;
import view.table.schemas.OfertaSchema;
import view.table.schemas.ProductosSchema;
import view.table.schemas.ProductosSchemaStockBajo;
import view.table.schemas.ProveedorSchema;
import view.table.schemas.TableSchema;
import view.table.style.TableStyle;
import view.table.style.TableStyleConfigure;

public class InventoryModule extends JPanel implements ActionListener, MouseListener {

	private JButton btnEditarProducto, btnEliminar, btnRefrescar, btnEditarOfPr, 
	btnCrearOfPr, btnEliminarOfPr, btnProveedores, btnOfertas;
	
	private JPanel panelSearch, panelOperacionesOfPr,  panelContenedorAccionesCrud;
	
	
	private JTextField textFieldSearch, textFieldSearchOfPr;
	private JLabel lbTotalRegister, lbCantidadProductosStockBajo;
	
	private JScrollPane scrollPaneProductos, scrollPaneAlertCantidad, scrollPaneOfertasProveedores ;
	private int vistOfPr = 1;
	
	private final Dahsboard dahsboard;
	private final AplicationContext context;
	
	private static final long serialVersionUID = 1L;
	
	
	@SuppressWarnings("unused")
	private List<Producto> data, productosStockBajo;
	private List<Proveedor> proveedores;
	private List<Oferta> ofertas;
	
	private TableSchema<Producto> schema;
	private JTable table, tableProductosStock, tableOfertas, tableProveedores;
	
	public  InventoryModule(Dahsboard dahsboard, AplicationContext context) {
		
		setLayout(null);
		setBackground(null);
		this.dahsboard = dahsboard;
		this.context = context;
		crearPanelOperacionesCurd();
		crearPanelViewPortProductos();
		crearPanelViewPortOfertasProveedores();
		crearPanelStockBajo();
		crearPanelOperacionesOfPr();
		
	}
	
	public void crearPanelViewPortProductos() {
		
		data =  context.getProductoController().listarProductos();
		schema = ProductosSchema.create();
		table = TableFactory.createTable(data, schema);
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
	
	public void crearPanelViewPortOfertasProveedores() {
		
		proveedores =  context.getProveedorController().listarProveedores();
		TableSchema<Proveedor> schema = ProveedorSchema.create();
		tableProveedores = TableFactory.createTable(proveedores, schema);
		tableProveedores.setBackground(TableStyleConfigure.COLOR_ROW_BG);
//		
		tableProveedores.setShowVerticalLines(true);
		tableProveedores.setGridColor(new Color(0xE5E7EB));
		tableProveedores.setBorder(BorderFactory.createEmptyBorder());
		tableProveedores.setShowGrid(false);
		tableProveedores.setIntercellSpacing(new Dimension(0, 0));
		
//		
	    TableStyle.apply(tableProveedores);
	    tableProveedores.setBounds(0, 0,  250, 253);
	    
	    ofertas = context.getOfertasController().listarOfertas();
	    TableSchema<Oferta> schemaOferta = OfertaSchema.create();
	    tableOfertas = TableFactory.createTable(ofertas, schemaOferta);

	    tableOfertas.setShowVerticalLines(true);
	    tableOfertas.setGridColor(new Color(0xE5E7EB));
	    tableOfertas.setBorder(BorderFactory.createEmptyBorder());
	    tableOfertas.setShowGrid(false);
	    tableOfertas.setIntercellSpacing(new Dimension(0, 0));
	    
	    TableStyle.apply(tableOfertas);
	    tableOfertas.setBounds(0, 0,  250, 253);
	    
		 scrollPaneOfertasProveedores = new JScrollPane(tableProveedores);
		 scrollPaneOfertasProveedores.getViewport().setBorder(null);
		 scrollPaneOfertasProveedores.getViewport().setBackground(null);
		 scrollPaneOfertasProveedores.setOpaque(false);
		 scrollPaneOfertasProveedores.getViewport().setOpaque(false);
		 scrollPaneOfertasProveedores.setBounds(515, 107, 500, 207);
		 
		UIManager.put("ScrollBar.showButtons", false);
		UIManager.put("ScrollBar.width", 12);
			
		 add(scrollPaneOfertasProveedores);
		
	}
	
	public void crearPanelOperacionesOfPr() {
		
		panelOperacionesOfPr = new JPanel();
		panelOperacionesOfPr.setOpaque(true);
		panelOperacionesOfPr.setBackground(null);
		panelOperacionesOfPr.setBounds(515, 71, 388, 34);
		panelOperacionesOfPr.setLayout(null);
		
		btnCrearOfPr = new JButton();
		btnCrearOfPr.setBorder(BorderFactory.createLineBorder(new Color(73, 230, 115), 2, true));
		btnCrearOfPr.addActionListener(this);
		btnCrearOfPr.setIcon(AssetManager.icon("etiqueta.png", 24, 24));
		btnCrearOfPr.setBounds(75, 0, 35, 32);
		btnCrearOfPr.setBackground(null);
		btnCrearOfPr.setOpaque(true);
		panelOperacionesOfPr.add(btnCrearOfPr);
		
		btnEliminarOfPr = new JButton();
		btnEliminarOfPr.setBackground(null);
		btnEliminarOfPr.setOpaque(true);
		btnEliminarOfPr.setBorder(BorderFactory.createLineBorder(new Color(255,120, 120), 2, true));
		btnEliminarOfPr.addActionListener(this);
		btnEliminarOfPr.setIcon(AssetManager.icon("borrar.png", 24, 24));
		btnEliminarOfPr.setBounds(152, 0, 35, 32);
		panelOperacionesOfPr.add(btnEliminarOfPr);
		
		btnEditarOfPr = new JButton();
		btnEditarOfPr.setBackground(null);
		btnEditarOfPr.setOpaque(true);
		btnEditarOfPr.addActionListener(this);
		btnEditarOfPr.setBorder(BorderFactory.createLineBorder(new Color(88, 177, 237), 2, true));
		btnEditarOfPr.setIcon(AssetManager.icon("editar.png", 24, 24));
		btnEditarOfPr.setBounds(0, 0, 35, 32);
		panelOperacionesOfPr.add(btnEditarOfPr);
		
		textFieldSearchOfPr = new JTextField();
		textFieldSearchOfPr.setText("Search");
		textFieldSearchOfPr.setColumns(10);
		textFieldSearchOfPr.addMouseListener(this);
		textFieldSearchOfPr.addActionListener(this);
		textFieldSearchOfPr.setBounds(230, 6, 158, 24);
		textFieldSearchOfPr.setBackground(null);
		panelOperacionesOfPr.add(textFieldSearchOfPr);
		
		add(panelOperacionesOfPr);
	}
	
	public void crearPanelStockBajo() {
			
		productosStockBajo =  context.getProductoController().listarProductosStockBajo();
		TableSchema<Producto> schema = ProductosSchemaStockBajo.create();
		tableProductosStock = TableFactory.createTable(productosStockBajo, schema);
		tableProductosStock.setBackground(TableStyleConfigure.COLOR_ROW_BG);

		tableProductosStock.setShowVerticalLines(true);
		tableProductosStock.setGridColor(new Color(0xE5E7EB));
		tableProductosStock.setBorder(BorderFactory.createEmptyBorder());
		tableProductosStock.setShowGrid(false);
		tableProductosStock.setIntercellSpacing(new Dimension(0, 0));
		
	    TableStyle.apply(tableProductosStock);
	    tableProductosStock.setBounds(0, 0,  500, 120);

		scrollPaneAlertCantidad = new JScrollPane(tableProductosStock);
		scrollPaneAlertCantidad.setBounds(515, 340, 500, 120);
		scrollPaneAlertCantidad.getViewport().setBorder(null);
		scrollPaneAlertCantidad.getViewport().setBackground(null);
		scrollPaneAlertCantidad.setOpaque(false);
		scrollPaneAlertCantidad.getViewport().setOpaque(false);
		add(scrollPaneAlertCantidad);
		
		
		lbCantidadProductosStockBajo = new JLabel("Stock bajo: "  + context.getProductoController().listarProductosStockBajo().size());
		lbCantidadProductosStockBajo.setBounds(515, 310, 141, 35);
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
		btnEditarProducto.addActionListener(e -> {
			new FormFactory(context).crearForm(context.getNavigation().getModuloActual(), SubModulo.PRODUCTO, OperationType.EDIT, dahsboard).setVisible(true);
			
		});
		
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
		btnEliminar.addActionListener(e -> {
			new FormFactory(context).crearForm(context.getNavigation().getModuloActual(), SubModulo.PRODUCTO,OperationType.DELETE,  dahsboard).setVisible(true);
		});
		btnEliminar.setFont(Fonts.custom);
		btnEliminar.addActionListener(this);
		
		btnRefrescar = new JButton();
		btnRefrescar.setBackground(null);
		btnRefrescar.setBorder(BorderFactory.createLineBorder(new Color(140, 255, 179), 2, true));
		btnRefrescar.putClientProperty("JButton.arc",20);
		btnRefrescar.setFocusPainted(false);
		btnRefrescar.setIcon(AssetManager.icon("actualizar.png", 28, 28));
		btnRefrescar.setBounds(300, 0, 50, 35);
		btnRefrescar.setIconTextGap(6);
		btnRefrescar.setFont(Fonts.custom);
		btnRefrescar.addActionListener(this);
		
		btnProveedores = new JButton();
		btnProveedores.setBackground(null);
		btnProveedores.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 2, true));
		btnProveedores.setFocusPainted(false);
		btnProveedores.setIcon(AssetManager.icon("proveedor.png", 28, 28));
		btnProveedores.setBounds(360, 0, 50, 35);
		btnProveedores.setIconTextGap(6);
		btnProveedores.setFont(Fonts.custom);
		btnProveedores.addActionListener(this);
			
		btnOfertas = new JButton();
		btnOfertas.setBackground(null);
		btnOfertas.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 2, true));
		btnOfertas.setFocusPainted(false);
		btnOfertas.setIcon(AssetManager.icon("oferta.png", 28, 28));
		btnOfertas.setBounds(430, 0, 50, 35);
		btnOfertas.setIconTextGap(6);
		btnOfertas.setFont(Fonts.custom);
		btnOfertas.addActionListener(this);
		
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
		panelContenedorAccionesCrud.add(btnRefrescar);
		panelContenedorAccionesCrud.add(btnEliminar);
		panelContenedorAccionesCrud.add(btnProveedores);
		panelContenedorAccionesCrud.add(btnOfertas);
		panelContenedorAccionesCrud.add(panelSearch);
		add(panelContenedorAccionesCrud);
			
	
	}

	public void crearPanelBuscar() {
	
		panelSearch = new JPanel();
		panelSearch.setBounds(760, 0, 246, 32);
		panelSearch.setBackground(null);
		panelSearch.setLayout(null);
		
		lbTotalRegister = new JLabel("0");
		lbTotalRegister.setBounds(0, 0, 45, 32);
		lbTotalRegister.setFont(Fonts.custom);
		lbTotalRegister.setForeground(Color.BLACK);
		panelSearch.add(lbTotalRegister);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addActionListener(this);
		textFieldSearch.addMouseListener(this);
		textFieldSearch.setText("Search");
		Filtros.aplicarFiltroNumericoTextField(textFieldSearch);
		textFieldSearch.setBackground(null);
		textFieldSearch.setBounds(57, 4, 190, 26);
		textFieldSearch.setColumns(10);
		panelSearch.add(textFieldSearch);
		
		add(panelSearch);
	}


	@Override
	public void actionPerformed(ActionEvent e) {

	    Object src = e.getSource();

	    if (src == textFieldSearch) handleProductoSearch();
	    else if (src == textFieldSearchOfPr) handleProveedorOfertaSearch();
	    else if (src == btnRefrescar) handleRefresh();
	    else if (src == btnCrearOfPr) handleCrud(OperationType.CREATE);
	    else if (src == btnEliminarOfPr) handleCrud(OperationType.DELETE);
	    else if (src == btnEditarOfPr) handleCrud(OperationType.EDIT);
	    else if (src == btnOfertas) switchToOfertas();
	    else if (src == btnProveedores) switchToProveedores();
	}


	///operaciones utilitarias metodos de llamados
	///
	///
	
	@SuppressWarnings({ "unchecked", "unused" })
	private void handleProductoSearch() {
		
		 String txt = textFieldSearch.getText().trim();
		    if (!txt.matches("\\d+")) {
		        new Messages(dahsboard, "Ingrese un ID válido").messageError();
		        return;
		    }

		    int id = Integer.parseInt(txt);
		    var producto = context.getProductoController().buscar(id);

		    if (producto == null) {
		        new Messages(dahsboard, "Producto no encontrado").messageError();
		        return;
		    }

		    List<Producto> data = List.of(producto);

		    ((UniversalTableModel<Producto>) table.getModel()).setData(data);
		    lbTotalRegister.setText(String.valueOf(data.size()));
		
	}
	
	
	//buscar ofertas o proveedores segun table activa
	
	@SuppressWarnings("unused")
	private void  handleProveedorOfertaSearch() {	
	String txt = textFieldSearchOfPr.getText().trim();
		    if (!txt.matches("\\d+")) {
		        new Messages(dahsboard, "ID no válido").messageError();
		        return;
		    }
		    int id = Integer.parseInt(txt);

		    if (vistOfPr == 1) handleProveedorSearch(id);
		    else handleOfertaSearch(id);
	}

	//
	@SuppressWarnings("unchecked")
	private void handleProveedorSearch(int id) {
	    var proveedor = context.getProveedorController().buscarProveedor(id);

	    if (proveedor == null) {
	        new Messages(dahsboard, "Proveedor no encontrado").messageError();
	        return;
	    }

	    ((UniversalTableModel<Proveedor>) tableProveedores.getModel())
	        .setData(List.of(proveedor));
	}

	@SuppressWarnings("unchecked")
	private void handleOfertaSearch(int id) {
	    var oferta = context.getOfertasController().buscarOferta(id);

	    if (oferta == null) {
	        new Messages(dahsboard, "Oferta no encontrada").messageError();
	        return;
	    }

	    ((UniversalTableModel<Oferta>) tableOfertas.getModel())
	        .setData(List.of(oferta));
	}


	//refrescar tables
	@SuppressWarnings({ "unchecked", "unused" })
	private void handleRefresh() {
	    ((UniversalTableModel<Producto>) table.getModel())
	        .setData(context.getProductoController().listarProductos());

	    ((UniversalTableModel<Proveedor>) tableProveedores.getModel())
	        .setData(context.getProveedorController().listarProveedores());
	    
		((UniversalTableModel<Oferta>) tableOfertas.getModel())
		.setData(context.getOfertasController().listarOfertas());
		
	    ((UniversalTableModel<Producto>) tableProductosStock.getModel())
        .setData(context.getProductoController().listarProductos());
		
		
	}

	
	//cambio de vista proveedores-ofertas
	
	private void switchToOfertas() {
	    if (vistOfPr == 1) {
	        scrollPaneOfertasProveedores.setViewportView(tableOfertas);
	        vistOfPr = 0;
	    }
	}

	private void switchToProveedores() {
	    if (vistOfPr == 0) {
	        scrollPaneOfertasProveedores.setViewportView(tableProveedores);
	        vistOfPr = 1;
	    }
	}
	
	
	//operaciones crud proveedores -> ofertas
	
	private void handleCrud(OperationType op) {

	    SubModulo target = (vistOfPr == 1)
	            ? SubModulo.PROVEEDOR
	          : SubModulo.OFERTA;

	    new FormFactory(context)
	            .crearForm(
	                context.getNavigation().getModuloActual(),
	                target,
	                op,
	                dahsboard
	            )
	            .setVisible(true);
	}

	
	//eventos mouse
	
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
		if(e.getSource() == textFieldSearch) {
			textFieldSearch.setText("");
		}
		if(e.getSource() == textFieldSearchOfPr) {
			textFieldSearchOfPr.setText("");
		}
	
	}


	@Override
	public void mouseExited(MouseEvent e) {
		
		if(e.getSource() == textFieldSearch) {
			textFieldSearch.setText("Search");
			lbTotalRegister.setText("0");
			lbTotalRegister.revalidate();
			lbTotalRegister.repaint();
		}
		
		if(e.getSource() == textFieldSearchOfPr) {
			
			textFieldSearchOfPr.setText("Search");
		}
		
	}
	
	
}
