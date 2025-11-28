package view.modules.admin;

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
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import model.OperationType;
import model.User;
import view.AplicationContext;
import view.components.AssetManager;
import view.components.Fonts;
import view.components.Messages;
import view.components.RoundePanel;
import view.dashboard.Dahsboard;
import view.formFactory.FormFactory;
import view.table.TableFactory;
import view.table.UniversalTableModel;
import view.table.schemas.TableSchema;
import view.table.schemas.UsuariosSchema;
import view.table.style.TableStyle;
//import view.table.style.TableStyleConfigure;

public class DashboardAdmin extends JPanel implements ActionListener, MouseListener {

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

	private JButton btnEditarUsuario, btnEliminar, btnRefrescar;
	private JPanel panelSearch, panelContenedorAccionesCrud;
	private JTextField textFieldSearch;
	private JLabel lbTotalRegister ;
	private List<User> data;
	private TableSchema<User> schema;
	private JTable table;
	private AplicationContext context;
	private Dahsboard dahsboard;
	
	public DashboardAdmin (AplicationContext context, Dahsboard dahsboard) {
		
		setBackground(null);
		setLayout(null);
		this.context = context;
		this.dahsboard  = dahsboard;
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
	
		lbPanelUsuariosConteo = new JLabel(context.getUserController().listar().size() + "");
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
	
		lbPanelFacturacionConteo = new JLabel(context.getFacturaController().listarFacturas().size() + ""); //
		lbPanelFacturacionConteo.setForeground(Color.BLACK);
		lbPanelFacturacionConteo.setHorizontalAlignment(SwingConstants.CENTER);
		lbPanelFacturacionConteo.setFont(Fonts.custom);
		lbPanelFacturacionConteo.setBounds(104, 27, 94, 12);
		
	}
	
	//table correspondiente
	public void crearViewPort() {
		data = context.getUserController().listar();
		schema = UsuariosSchema.create();
		table = TableFactory.createTable(data, schema);
		table.setBackground(null);
		table.setOpaque(false);
	
		table.setShowVerticalLines(true);
		table.setGridColor(new Color(0xE5E7EB));
		table.setBorder(BorderFactory.createEmptyBorder());
		table.setShowGrid(false);
		table.setIntercellSpacing(new Dimension(0, 0));
////		
	    TableStyle.apply(table);
		table.setBounds(0, 0,  800, 253);
////		
		scrollPanel = new JScrollPane(table);
		scrollPanel.setBackground(null);
		scrollPanel.setBorder(null);
		scrollPanel.setBorder(null);
		scrollPanel.setOpaque(false);
		scrollPanel.getViewport().setBorder(null);
		scrollPanel.getViewport().setBackground(Color.WHITE);
		scrollPanel.getViewport().setOpaque(false);
		scrollPanel.setBounds(10, 161,850, 253);
////	
		UIManager.put("ScrollBar.showButtons", false);
		UIManager.put("ScrollBar.width", 12);
		add(scrollPanel);
	
		
	}
	
	
	//opciones crud
	
	
		@SuppressWarnings("unchecked")
		public void crearBtns() {
		
		btnEditarUsuario = new JButton();
		btnEditarUsuario.setText("Editar");
		btnEditarUsuario.setIcon(AssetManager.icon("editar.png", 18, 18));
		btnEditarUsuario.setBounds(0, 0, 120, 35);
		btnEditarUsuario.addActionListener(e -> {
			new FormFactory(context).
			crearForm(context.getNavigation().getModuloActual(), dahsboard, OperationType.EDIT).
			setVisible(true);
			
		});
		btnEditarUsuario.setFocusPainted(false);
		btnEditarUsuario.setIconTextGap(6);
		btnEditarUsuario.setBorder(BorderFactory.createLineBorder(new Color(88, 177, 237), 2, true));
		btnEditarUsuario.setForeground(new Color(88, 177, 237));
		btnEditarUsuario.setBackground(null);
		btnEditarUsuario.putClientProperty("JButton.arc", 8);
		
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
		btnEliminar.addActionListener(e -> {
			new FormFactory(context).
			crearForm(context.getNavigation().getModuloActual(), dahsboard, OperationType.DELETE).
			setVisible(true);
			
		});
		
		btnRefrescar = new JButton();
		btnRefrescar.setBackground(null);
		btnRefrescar.setBorder(BorderFactory.createLineBorder(new Color(140, 255, 179), 2, true));
		btnRefrescar.putClientProperty("JButton.arc",20);
		btnRefrescar.setFocusPainted(false);
		btnRefrescar.setIcon(AssetManager.icon("actualizar.png", 28, 28));
		btnRefrescar.setBounds(300, 0, 50, 35);
		btnRefrescar.setIconTextGap(6);
		btnRefrescar.setFont(Fonts.custom);
		btnRefrescar.addActionListener(e -> {
			  lbPanelUsuariosConteo.setText(context.getUserController().listar().size() + "");
			  lbPanelUsuariosConteo.revalidate();
			  lbPanelUsuariosConteo.repaint();
			  ((UniversalTableModel<User>) table.getModel()).setData(context.getUserController().listar());	
			

		});
		
	}
	public void crearPanelOperacionesCurd() {
		
		panelContenedorAccionesCrud = new JPanel();
		panelContenedorAccionesCrud.setLayout(null);
		panelContenedorAccionesCrud.setOpaque(true);
		panelContenedorAccionesCrud.setBackground(null);
		panelContenedorAccionesCrud.setBounds(10, 110, 1043, 36);
	
		crearPanelBuscar();
		crearBtns();
		panelContenedorAccionesCrud.add(btnEditarUsuario);
		panelContenedorAccionesCrud.add(btnEliminar);
		panelContenedorAccionesCrud.add(btnRefrescar);
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
		textFieldSearch.setText("Search");
		textFieldSearch.setBackground(null);
		textFieldSearch.setBounds(57, 4, 190, 26);
		textFieldSearch.setColumns(10);
		textFieldSearch.addActionListener(this);
		textFieldSearch.addMouseListener(this);
		panelSearch.add(textFieldSearch);
		
		
		add(panelSearch);
	}


	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource() == textFieldSearch) {
			int idUsuario;
			if(textFieldSearch.getText().trim().matches("\\d+")) {
				idUsuario =  Integer.parseInt(textFieldSearch.getText().trim());
				var user = context.getUserController().buscarUsuario(idUsuario);
				if(user != null) {
					List<User> newData = new ArrayList<>();
					newData.add(user);
					((UniversalTableModel<User>) table.getModel()).setData(newData);	
					lbTotalRegister.setText(newData.size() + "");
					lbTotalRegister.revalidate();
					lbTotalRegister.repaint();
				}else {
					new Messages(dahsboard, "Usuario no encontrado").messageError();
					return;
				}
				
			}else {
				new Messages(dahsboard, "Ingrese un ID valido").messageError();
				return;
			}
				
			
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
		if(e.getSource() ==textFieldSearch) {
			textFieldSearch.setText("");
		}	
	}


	@Override
	public void mouseExited(MouseEvent e) {
		
		if(e.getSource() ==textFieldSearch) {
			textFieldSearch.setText("Search");
			lbTotalRegister.setText("0");
			lbTotalRegister.revalidate();
			lbTotalRegister.repaint();
		}
		
	}

}
