package view.modules.billing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import control.FacturaController;
import model.Factura;
import model.User;
import view.components.AssetManager;
import view.components.Fonts;
import view.table.TableFactory;
import view.table.UniversalTableModel;
import view.table.schemas.FacturasSchema;
import view.table.schemas.TableSchema;
import view.table.style.TableStyle;
import view.table.style.TableStyleConfigure;

public class BillingModule extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private JButton btnEditarProducto, btnEliminar, btnRefrescar;
	private JPanel panelSearch, panelContenedorAccionesCrud;
	private JTextField textFieldSearch;
	private JLabel lbTotalRegister;
	private JScrollPane scrollPaneFacturas;
	private static final long serialVersionUID = 1L;

	private FacturaController facturas;
	
	public BillingModule() {
		
		setLayout(null);
		setBackground(null);
//		facturas = new FacturaController();
		crearPanelViewPortVentas();
		crearPanelOperacionesCurd();
	}
	
	public void  crearPanelViewPortVentas() {
		
		List<Factura> data =  facturas.listarFacturas();
		
		TableSchema<Factura> schema = FacturasSchema.create();
		JTable table = TableFactory.createTable(data, schema);
		table.setBackground(TableStyleConfigure.COLOR_ROW_BG);
		
		table.setShowVerticalLines(true);
		table.setGridColor(new Color(0xE5E7EB));
		table.setBorder(BorderFactory.createEmptyBorder());
		table.setShowGrid(false);
		table.setIntercellSpacing(new Dimension(0, 0));
		
	    TableStyle.apply(table);
		table.setBounds(0, 0,  250, 253);

		
		scrollPaneFacturas = new JScrollPane(table);
		scrollPaneFacturas.setBounds(10, 107, 1027, 320);

		scrollPaneFacturas.getViewport().setBorder(null);
		scrollPaneFacturas.getViewport().setBackground(null);
		scrollPaneFacturas.setOpaque(false);
		scrollPaneFacturas.getViewport().setOpaque(false);
		UIManager.put("ScrollBar.showButtons", false);
		UIManager.put("ScrollBar.width", 12);
		
		add(scrollPaneFacturas);
		
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
		
		btnRefrescar = new JButton();
		btnRefrescar.setBackground(null);
		btnRefrescar.setBorder(BorderFactory.createLineBorder(new Color(140, 255, 179), 2, true));
		btnRefrescar.putClientProperty("JButton.arc",20);
		btnRefrescar.setFocusPainted(false);
		btnRefrescar.setIcon(AssetManager.icon("actualizar.png", 28, 28));
		btnRefrescar.setBounds(300, 0, 50, 35);
		btnRefrescar.setIconTextGap(6);
		btnRefrescar.setFont(Fonts.custom);
//		btnRefrescar.addActionListener(e -> {
//			List<User> newData = context.getUserController().listar();
//			  lbPanelUsuariosConteo.setText(context.getUserController().listar().size() + "");
//			  lbPanelUsuariosConteo.revalidate();
//			  lbPanelUsuariosConteo.repaint();
//			  ((UniversalTableModel<User>) table.getModel()).setData(newData);	
//			
//
//		});
		
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
