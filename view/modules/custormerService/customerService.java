package view.modules.custormerService;

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

import model.Cliente;
import model.OperationType;
import view.AplicationContext;
import view.components.AssetManager;
import view.components.Filtros;
import view.components.Fonts;
import view.components.Messages;
import view.dashboard.Dahsboard;
import view.formFactory.FormFactory;
import view.table.TableFactory;
import view.table.UniversalTableModel;
import view.table.schemas.ClienteSchema;

import view.table.schemas.TableSchema;
import view.table.style.TableStyle;
import view.table.style.TableStyleConfigure;

public class customerService extends JPanel implements ActionListener, MouseListener {

	
	private JButton btnEditarProducto, btnEliminar, btnRefrescar;
	
	private JPanel panelSearch,  panelContenedorAccionesCrud;
	
	private JScrollPane scrollPaneClientes;
	
	private JLabel lbTotalRegister;
	
	@SuppressWarnings("unused")
	private final AplicationContext context;
	@SuppressWarnings("unused")
	private final Dahsboard dahsboard;
	
	private JTextField textFieldSearch;

	private TableSchema<Cliente> schema;
	private JTable tableCliente;
	
	private List<Cliente> clientesData;
	
	
	private static final long serialVersionUID = 1L;
	
	public  customerService( AplicationContext context, Dahsboard dahsboard) {
		
		this.context = context;
		this.dahsboard = dahsboard;
		setLayout(null);
		setBackground(null);
		crearPanelOperacionesCurd();
		crearPanelViewPortProductos();
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
			new FormFactory(context).crearForm(context.getNavigation().getModuloActual(),null, OperationType.EDIT, dahsboard).setVisible(true);
			
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
			new FormFactory(context).crearForm(context.getNavigation().getModuloActual(), null ,OperationType.DELETE,  dahsboard).setVisible(true);
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

	
	
	public void crearPanelViewPortProductos() {
		
		clientesData=  context.getClienteController().listarClientes();
		schema = ClienteSchema.create();
		tableCliente = TableFactory.createTable(clientesData, schema);
		tableCliente.setBackground(TableStyleConfigure.COLOR_ROW_BG);
		
		tableCliente.setShowVerticalLines(true);
		tableCliente.setGridColor(new Color(0xE5E7EB));
		tableCliente.setBorder(BorderFactory.createEmptyBorder());
		tableCliente.setShowGrid(false);
		tableCliente.setIntercellSpacing(new Dimension(0, 0));
		
	    TableStyle.apply(tableCliente);
	    tableCliente.setBounds(0, 0,  1000, 253);

	    scrollPaneClientes = new JScrollPane(tableCliente);
	    scrollPaneClientes.setBounds(10, 107, 1000, 320);
	    scrollPaneClientes.getViewport().setBorder(null);
	    scrollPaneClientes.getViewport().setBackground(null);
		scrollPaneClientes.setOpaque(false);
		scrollPaneClientes.getViewport().setOpaque(false);
		UIManager.put("ScrollBar.showButtons", false);
		UIManager.put("ScrollBar.width", 12);
		
		add(scrollPaneClientes);
		
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	private void handleRefresh() {
	    ((UniversalTableModel<Cliente>) tableCliente.getModel())
	        .setData(context.getClienteController().listarClientes());
	
	}
	
	@SuppressWarnings("unchecked")
	private void handleProductoSearch() {
		
		 String txt = textFieldSearch.getText().trim();
		    if (!txt.matches("\\d+")) {
		        new Messages(dahsboard, "Ingrese un ID válido").messageError();
		        return;
		    }

		    int id = Integer.parseInt(txt);
		    var cliente = context.getClienteController().buscarCliente(id);

		    if (cliente == null) {
		        new Messages(dahsboard, "Cliente no encontrado").messageError();
		        return;
		    }

		    List<Cliente> data = List.of(cliente);

		    ((UniversalTableModel<Cliente>) tableCliente.getModel()).setData(data);
		    lbTotalRegister.setText(String.valueOf(data.size()));
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnRefrescar) handleRefresh();
		if(e.getSource() == textFieldSearch) handleProductoSearch();
		
	}
}
