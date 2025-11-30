package view.modules.sales;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import model.Producto;
import view.AplicationContext;
import view.components.Fonts;
import view.components.Messages;
import view.dashboard.Dahsboard;
import view.table.TableFactory;
import view.table.UniversalTableModel;
import view.table.schemas.TableSchema;
import view.table.schemas.VentasSchema;
import view.table.style.TableStyle;
import view.table.style.TableStyleConfigure;

public class SalesModule extends JPanel  implements ActionListener, MouseListener{

	//modulo de busqueda de productos disponibles para venta
	//asistente de ventas  que se encuentra en los pasillos de los suerpermercados
	
	@SuppressWarnings("unused")
	private final Dahsboard dahsboard;
	@SuppressWarnings("unused")
	private final AplicationContext context;
	private JPanel panelSearch, panelContenedorAccionesCrud;
	private JTextField textFieldSearch;
	private JLabel lbTotalRegister, lbTitulo;
	private JScrollPane scrollPaneVentas;
	private static final long serialVersionUID = 1L;
	
	private List<Producto> data;
	private JTable table ;

	private TableSchema<Producto> schema;
	public SalesModule(AplicationContext context, Dahsboard dahsboard ) {
		
		this.context = context;
		this.dahsboard = dahsboard;
		setLayout(null);
		setBackground(null);
		crearPanelViewPortVentas();
		crearPanelOperacionesCurd();
	}
	
	public void  crearPanelViewPortVentas() {
		
		data = context.getProductoController().listarProductos();
		schema = VentasSchema.create();
		table = TableFactory.createTable(data, schema);
		table.setBackground(TableStyleConfigure.COLOR_ROW_BG);
		
		table.setShowVerticalLines(true);
		table.setGridColor(new Color(0xE5E7EB));
		table.setBorder(BorderFactory.createEmptyBorder());
		table.setShowGrid(false);
		table.setIntercellSpacing(new Dimension(0, 0));
		
	    TableStyle.apply(table);
		table.setBounds(0, 0,  250, 253);
	
		scrollPaneVentas = new JScrollPane(table);
		scrollPaneVentas.setBounds(10, 107, 1027, 320);
		scrollPaneVentas.getViewport().setBorder(null);
		scrollPaneVentas.getViewport().setBackground(null);
		scrollPaneVentas.setOpaque(false);
		scrollPaneVentas.getViewport().setOpaque(false);
		UIManager.put("ScrollBar.showButtons", false);
		UIManager.put("ScrollBar.width", 12);
		
		add(scrollPaneVentas);
		
	}
	
//		
	public void crearPanelOperacionesCurd() {
	
		panelContenedorAccionesCrud = new JPanel();
		panelContenedorAccionesCrud.setLayout(null);
		panelContenedorAccionesCrud.setOpaque(true);
		panelContenedorAccionesCrud.setBackground(null);
		panelContenedorAccionesCrud.setBounds(10, 10, 1043, 36);
	
		crearPanelBuscar();
		panelContenedorAccionesCrud.add(panelSearch);
		add(panelContenedorAccionesCrud);
			
	
	}

	public void crearPanelBuscar() {
	
		panelSearch = new JPanel();
		panelSearch.setBounds(0, 0, 1040, 32);
		panelSearch.setBackground(null);
		panelSearch.setLayout(null);
	
		
		lbTitulo = new JLabel("Consultar productos disponibles para ventas: ");
		lbTitulo.setBounds(0, 0, 400, 32);
		lbTitulo.setFont(Fonts.custom);
		lbTitulo.setForeground(Color.BLACK);
		panelSearch.add(lbTitulo);
		
		lbTotalRegister = new JLabel("0");
		lbTotalRegister.setBounds(810, 0, 45, 32);
		lbTotalRegister.setFont(Fonts.custom);
		lbTotalRegister.setForeground(Color.BLACK);
		panelSearch.add(lbTotalRegister);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addActionListener(this);
		textFieldSearch.addMouseListener(this);
		textFieldSearch.setText("Search");
		textFieldSearch.setBackground(null);
		textFieldSearch.setBounds(840, 4, 190, 26);
		textFieldSearch.setColumns(10);
		panelSearch.add(textFieldSearch);
		add(panelSearch);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == textFieldSearch ) handleProductoSearch();
	}
	
	@SuppressWarnings({ "unchecked" })
	private void handleProductoSearch() {

	    String txt = textFieldSearch.getText().trim();

	    if (!txt.matches("[A-Za-z0-9 ]+")) {
	        new Messages(dahsboard, "Nombre inválido. No se permiten caracteres especiales.").messageError();
	        return;
	    }

	    if (txt.matches("\\d+")) {
	        int id = Integer.parseInt(txt);
	        var producto = context.getProductoController().buscar(id);

	        if (producto == null) {
	            new Messages(dahsboard, "Producto no encontrado").messageError();
	            return;
	        }

	        List<Producto> data = List.of(producto);
	        ((UniversalTableModel<Producto>) table.getModel()).setData(data);
	        lbTotalRegister.setText("1");
	        return;
	    }

	   
	    String search = normalize(txt);

	    List<Producto> productos = context.getProductoController().listarProductos();

	    List<Producto> filtrados = productos.stream()
	        .filter(p -> {

	            String nombre = normalize(p.getNombre());
	            String categoria = normalize(p.getCategoria().toString());

	            return nombre.contains(search) || categoria.contains(search);
	        })
	        .toList();

	    if (filtrados.isEmpty()) {
	        new Messages(dahsboard, "No hay coincidencias").messageAvisos();
	        return;
	    }

	    ((UniversalTableModel<Producto>) table.getModel()).setData(filtrados);
	    lbTotalRegister.setText(String.valueOf(filtrados.size()));
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
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == textFieldSearch) {
			textFieldSearch.setText("Search");
		}
		
	}
	
	private String normalize(String input) {
	    String normalized = java.text.Normalizer.normalize(input, java.text.Normalizer.Form.NFD);
	    return normalized.replaceAll("\\p{M}", "").toLowerCase();
	}

}
