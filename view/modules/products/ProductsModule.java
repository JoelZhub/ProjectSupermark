package view.modules.products;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import model.OperationType;
import model.Producto;
import view.AplicationContext;
import view.FormFactory;
import view.components.AssetManager;
import view.components.Fonts;
import view.components.Messages;
import view.dashboard.Dahsboard;
import view.table.TableFactory;
import view.table.UniversalTableModel;
import view.table.schemas.ProductosSchema;
import view.table.schemas.ProductosSchemaStockBajo;
import view.table.schemas.TableSchema;
import view.table.style.TableStyle;
import view.table.style.TableStyleConfigure;

public class ProductsModule extends JPanel implements ActionListener, MouseListener {

	private JButton btnEditarProducto, btnEliminar, btnRefrescar, btnEditarOfPr, 
	btnCrearOfPr, btnEliminarOfPr;
	private JPanel panelSearch, panelOperacionesOferta,  panelContenedorAccionesCrud;
	private JTextField textFieldSearch, textFieldSearchOfPr;
	private JLabel lbTotalRegister, lbCantidadProductosStockBajo;
	private JScrollPane scrollPaneProductos, scrollPaneAlertCantidad, scrollPaneOfertas ;
	
	private final Dahsboard dahsboard;
	private final AplicationContext context;
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private List<Producto> data, productosStockBajo;
	private TableSchema<Producto> schema;
	private JTable table, tableProductosStock, tableOfertas, tableProveedores;
	//	private int elementActivo = 0;
	
	public  ProductsModule(Dahsboard dahsboard, AplicationContext context) {
		
		setLayout(null);
		setBackground(null);
		this.dahsboard = dahsboard;
		this.context = context;
		crearPanelOperacionesCurd();
		crearPanelViewPortProductos();
		crearPanelViewPortOfertas();
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
		 scrollPaneOfertas.setBounds(515, 107, 500, 207);
		 
		 	UIManager.put("ScrollBar.showButtons", false);
			UIManager.put("ScrollBar.width", 12);
			
		 add(scrollPaneOfertas);
		
	}
	
	public void crearPanelOperacionesOfPr() {
		
		panelOperacionesOferta = new JPanel();
		panelOperacionesOferta.setOpaque(true);
		panelOperacionesOferta.setBackground(null);
		panelOperacionesOferta.setBounds(515, 71, 388, 34);
		panelOperacionesOferta.setLayout(null);
		
		btnCrearOfPr = new JButton();
		btnCrearOfPr.setBorder(BorderFactory.createLineBorder(new Color(73, 230, 115), 2, true));
		btnCrearOfPr.addActionListener(this);
		btnCrearOfPr.setIcon(AssetManager.icon("etiqueta.png", 24, 24));
		btnCrearOfPr.setBounds(75, 0, 35, 32);
		btnCrearOfPr.setBackground(null);
		btnCrearOfPr.setOpaque(true);
		panelOperacionesOferta.add(btnCrearOfPr);
		
		btnEliminarOfPr = new JButton();
		btnEliminarOfPr.setBackground(null);
		btnEliminarOfPr.setOpaque(true);
		btnEliminarOfPr.setBorder(BorderFactory.createLineBorder(new Color(255,120, 120), 2, true));
		btnEliminarOfPr.addActionListener(this);
		btnEliminarOfPr.setIcon(AssetManager.icon("borrar.png", 24, 24));
		btnEliminarOfPr.setBounds(152, 0, 35, 32);
		panelOperacionesOferta.add(btnEliminarOfPr);
		
		btnEditarOfPr = new JButton();
		btnEditarOfPr.setBackground(null);
		btnEditarOfPr.setOpaque(true);
		btnEditarOfPr.addActionListener(this);
		btnEditarOfPr.setBorder(BorderFactory.createLineBorder(new Color(88, 177, 237), 2, true));
		btnEditarOfPr.setIcon(AssetManager.icon("editar.png", 24, 24));
		btnEditarOfPr.setBounds(0, 0, 35, 32);
		panelOperacionesOferta.add(btnEditarOfPr);
		
		textFieldSearchOfPr = new JTextField();
		textFieldSearchOfPr.setText("Search");
		textFieldSearchOfPr.setColumns(10);
		textFieldSearchOfPr.addMouseListener(this);
		textFieldSearchOfPr.addActionListener(this);
		textFieldSearchOfPr.setBounds(230, 6, 158, 24);
		textFieldSearchOfPr.setBackground(null);
		panelOperacionesOferta.add(textFieldSearchOfPr);
		
		add(panelOperacionesOferta);
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
			new FormFactory(context).crearForm(context.getNavigation().getModuloActual(), dahsboard, OperationType.EDIT).setVisible(true);
			
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
			new FormFactory(context).crearForm(context.getNavigation().getModuloActual(), dahsboard, OperationType.DELETE).setVisible(true);
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
		textFieldSearch.addActionListener(this);
		textFieldSearch.addMouseListener(this);
		textFieldSearch.setText("Search");
		textFieldSearch.setBackground(null);
		textFieldSearch.setBounds(57, 4, 190, 26);
		textFieldSearch.setColumns(10);
		panelSearch.add(textFieldSearch);
		
		add(panelSearch);
	}


	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == textFieldSearch) {
			int idProducto;
			if(textFieldSearch.getText().trim().matches("\\d+")) {
				idProducto =  Integer.parseInt(textFieldSearch.getText().trim());
				var producto = context.getProductoController().buscar(idProducto);
				
				if(producto != null) {
					List<Producto> newData = new ArrayList<>();
					newData.add(producto);
					((UniversalTableModel<Producto>) table.getModel()).setData(newData);	
					lbTotalRegister.setText(newData.size() + "");
					lbTotalRegister.revalidate();
					lbTotalRegister.repaint();
					
					
				}else {
					new Messages(dahsboard, "Producto no encontrado").messageError();
					return;
				}
				
			}else {
				new Messages(dahsboard, "Ingrese un ID valido").messageError();
				return;
			}
				
			
		}
		if(e.getSource() == btnRefrescar) {
			
			((UniversalTableModel<Producto>) table.getModel()).setData(context.getProductoController().listarProductos());			
		}
		
		if(e.getSource() == textFieldSearchOfPr) {
			
			//funcionara cuando se cree el DAO que traera las ofertas activas de la BD  
			// y los proveedores
			
			
			
			
		}
		
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
			lbTotalRegister.setText("0");
			lbTotalRegister.revalidate();
			lbTotalRegister.repaint();
		}
		
	}
	
	
}
