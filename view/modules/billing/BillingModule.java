package view.modules.billing;

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

import model.Factura;
import model.Rol;
import model.User;
import session.SessionContext;
import view.AplicationContext;
import view.components.AssetManager;
import view.components.Fonts;
import view.components.Messages;
import view.dashboard.Dahsboard;
import view.table.TableFactory;
import view.table.UniversalTableModel;
import view.table.schemas.FacturasSchema;
import view.table.schemas.TableSchema;
import view.table.style.TableStyle;
import view.table.style.TableStyleConfigure;

public class BillingModule extends JPanel implements ActionListener, MouseListener{

	/**
	 * 
	 */
	private JButton btnEliminar, btnRefrescar;
	private JPanel panelSearch, panelContenedorAccionesCrud;
	private JTextField textFieldSearch;
	private JLabel lbTotalRegister;
	private JScrollPane scrollPaneFacturas;
	private static final long serialVersionUID = 1L;
	private final AplicationContext context;
	@SuppressWarnings("unused")
	private final Dahsboard dahsboard;
	private List<Factura> data;
	private TableSchema<Factura> schema ;
	private JTable table;
	
	public BillingModule(AplicationContext context, Dahsboard dahsboard) {
		this.context = context;
		this.dahsboard = dahsboard;
		setLayout(null);
		setBackground(null);
		crearPanelViewPortFactura();
		crearPanelOperacionesCurd();
	}
	
	public void  crearPanelViewPortFactura() {
		
		data =  new ArrayList<>();
		
		if(SessionContext.get().getRolUsuarioLogueado() == Rol.ADMIN) {
			
			data = context.getFacturaController().listarFacturas();
		}else {
		
			 for(Factura f : context.getFacturaController().listarFacturas()) {
		
				 if(f.getCajeroId() == SessionContext.get().getIdUsuarioLogueado()) {
					 data.add(f);
				 }
			 }
		
		}
		
		FacturasSchema.setContext(context);
		schema = FacturasSchema.create();
		table = TableFactory.createTable(data, schema);
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
	
	@SuppressWarnings({ "unchecked", "unchecked" })
	public void crearBtns() {
		

		//deje solo el btn de refrescar
		
		//puesto que  un cajero como tal no tiene permsio de editar o eliminar facturas en un supermercado real
		// el que se encarga de eso es otro usuario y por cuestiones de tiempo no lo agregare, entonces para evitar
		//que el profe nos vaya a reclamar por esa parte mejor quite esos btn
		
		btnRefrescar = new JButton();
		btnRefrescar.setBackground(null);
		btnRefrescar.setBorder(BorderFactory.createLineBorder(new Color(140, 255, 179), 2, true));
		btnRefrescar.putClientProperty("JButton.arc",20);
		btnRefrescar.setFocusPainted(false);
		btnRefrescar.setIcon(AssetManager.icon("actualizar.png", 28, 28));
		btnRefrescar.setBounds(20, 0, 50, 35);
		btnRefrescar.setIconTextGap(6);
		btnRefrescar.setFont(Fonts.custom);
		btnRefrescar.addActionListener(e -> {
			List<Factura> newData = new ArrayList<>();
			
			if(SessionContext.get().getRolUsuarioLogueado() == Rol.ADMIN) {
				
				newData = context.getFacturaController().listarFacturas();
			}else {
			
				 for(Factura f : context.getFacturaController().listarFacturas()) {
			
					 if(f.getCajeroId() == SessionContext.get().getIdUsuarioLogueado()) {
						 newData.add(f);
					 }
				 }
			
			}
			  ((UniversalTableModel<Factura>) table.getModel()).setData(newData);	
			

		});
		
	}
		
	public void crearPanelOperacionesCurd() {
	
		panelContenedorAccionesCrud = new JPanel();
		panelContenedorAccionesCrud.setLayout(null);
		panelContenedorAccionesCrud.setOpaque(true);
		panelContenedorAccionesCrud.setBackground(null);
		panelContenedorAccionesCrud.setBounds(10, 10, 1043, 36);
	
		crearPanelBuscar();
		crearBtns();
		//btnRefrescar
		
		panelContenedorAccionesCrud.add(btnRefrescar);
//		panelContenedorAccionesCrud.add(btnEliminar);
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
		textFieldSearch.addActionListener(this);
		textFieldSearch.setBackground(null);
		textFieldSearch.addMouseListener(this);
		textFieldSearch.setBounds(57, 4, 190, 26);
		textFieldSearch.setColumns(10);
		panelSearch.add(textFieldSearch);
		
		add(panelSearch);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
			
			if(e.getSource() == textFieldSearch) {
				int id;
				if(textFieldSearch.getText().trim().matches("\\d+")) {
					id =  Integer.parseInt(textFieldSearch.getText().trim());
					var f = context.getFacturaController().buscar(id) ;
					if(f != null) {
						List<Factura> newData = new ArrayList<>();
						newData.add(f);
						((UniversalTableModel<Factura>) table.getModel()).setData(newData);	
						lbTotalRegister.setText(newData.size() + "");
						lbTotalRegister.revalidate();
						lbTotalRegister.repaint();
					}else {
						new Messages(dahsboard, "Factura no encontrada").messageError();
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
