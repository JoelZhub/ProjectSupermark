package view.modules.inventory.forms.products;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;

import model.Categoria;
import model.Detalles;
import model.Producto;
import model.Proveedor;
import view.AplicationContext;
import view.components.BtnStyle;
import view.components.Filtros;
import view.components.Fonts;
import view.components.Messages;
import view.components.NumericFilter;
import view.dashboard.Dahsboard;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import com.github.lgooddatepicker.components.DatePicker;


public class CrearProducto extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	@SuppressWarnings("unused")
	private final Dahsboard dahsboard;
	@SuppressWarnings("unused")
	private final AplicationContext context;
	private Producto producto;
	@SuppressWarnings("unused")
	private Detalles detalles;
	private JTextField textFieldProducto;
	private JTextField textFieldUnidad;
	private JTextField textFieldPrecio;
	private JComboBox<Categoria> textFieldCategoria;
	private JSpinner textFieldCantidad;
	private JButton btnCancelar, btnGuardar, btnContinuar, btnRegresar;
	private JPanel panelInformacionBasica ;
	private JPanel panelDetalleProducto;
	private JLabel lbMarca;
	private JLabel lbDetalleProducto;
	private JComboBox<Proveedor> textFieldProeveedor;
	private JLabel lbOrigen;
	private JComboBox<String> textFieldOrigen;
	private JLabel lbFecha;
	private JTextField textField;
	private JPanel buttonPane;
	public static void main(String[] args) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	//solo tiene el design de momento falta valdiar los campos y llamar el metood que se encarga de crear el producto de la clase producto controller
	// tambien falta crear  el elemento que se encarga de gestionar los proveedores es decir el controller, el de oferta  y detalles del producto
	
	public CrearProducto(Dahsboard dahsboard, AplicationContext context) {
		setModal(true);
		this.dahsboard = dahsboard;
		this.context = context;
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\img\\iconApp.png"));
		setBounds(100, 100, 800, 490);
		
		setBackground(new Color(56, 56, 56));
		getContentPane().setBackground(new Color(56, 56, 56));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(56, 56, 56));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);;
		
		panelDetalleProducto = new JPanel();
		panelDetalleProducto.setVisible(false);
		panelDetalleProducto.setBackground(null);
		panelDetalleProducto.setBounds(10, 10, 810, 393);
		contentPanel.add(panelDetalleProducto);
		panelDetalleProducto.setLayout(null);
		
		lbMarca = new JLabel("Marca");
		lbMarca.setForeground(Color.WHITE);
		lbMarca.setFont(Fonts.custom);
		lbMarca.setBounds(5, 104, 117, 12);
		panelDetalleProducto.add(lbMarca);
		
		lbDetalleProducto = new JLabel("Detalles Producto");
		lbDetalleProducto.setFont(Fonts.bold);
		lbDetalleProducto.setForeground(Color.WHITE);
		lbDetalleProducto.setBounds(10, 26, 237, 26);
		panelDetalleProducto.add(lbDetalleProducto);
		
		textFieldProeveedor = new JComboBox<Proveedor>();
		textFieldProeveedor.setForeground(Color.WHITE);
		textFieldProeveedor.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
		textFieldProeveedor.setBackground(new Color(56, 56, 56));
		textFieldProeveedor.setBounds(5, 248, 280, 26);
		panelDetalleProducto.add(textFieldProeveedor);
		
		JLabel lblProvedor = new JLabel("Proveedor");
		lblProvedor.setForeground(Color.WHITE);
		lblProvedor.setFont(Fonts.custom);
		lblProvedor.setBounds(5, 208, 98, 12);
		panelDetalleProducto.add(lblProvedor);
		
		lbOrigen = new JLabel("Origen");
		lbOrigen.setForeground(Color.WHITE);
		lbOrigen.setFont(Fonts.custom);
		lbOrigen.setBounds(340, 208, 98, 12);
		panelDetalleProducto.add(lbOrigen);
		
		textFieldOrigen = new JComboBox<String>();
		textFieldOrigen.addItem("Importado");
		textFieldOrigen.addItem("Nacional");
		textFieldOrigen.setForeground(Color.WHITE);
		textFieldOrigen.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
		textFieldOrigen.setBackground(new Color(56, 56, 56));
		textFieldOrigen.setBounds(340, 248, 280, 26);
		panelDetalleProducto.add(textFieldOrigen);
		
		lbFecha = new JLabel("Fecha");
		lbFecha.setFont(Fonts.custom);
		lbFecha.setForeground(Color.WHITE);
		lbFecha.setBounds(340, 104, 117, 12);
		panelDetalleProducto.add(lbFecha);
		
		DatePicker textFieldFecha = new DatePicker();
		textFieldFecha.setBounds(340, 148, 280, 26);
		panelDetalleProducto.add(textFieldFecha);
		
		textField = new JTextField();
		textField.setBounds(5, 148, 280, 26);
		panelDetalleProducto.add(textField);
		textField.setColumns(10);
		
		panelInformacionBasica = new JPanel();
		panelInformacionBasica.setBounds(10, 10, 754, 393);
		contentPanel.add(panelInformacionBasica);
		panelInformacionBasica.setBackground(null);
		panelInformacionBasica.setLayout(null);
		
		JLabel lbCrearProducto = new JLabel("Crear Producto");
		lbCrearProducto.setBounds(20, 38, 176, 26);
		lbCrearProducto.setForeground(Color.WHITE);
		lbCrearProducto.setFont(Fonts.bold);
		panelInformacionBasica.add(lbCrearProducto);
		
		JLabel lbNombreProducto = new JLabel("Producto");
		lbNombreProducto.setBounds(20, 106, 98, 12);
		lbNombreProducto.setForeground(Color.WHITE);
		lbNombreProducto.setFont(Fonts.custom);
		panelInformacionBasica.add(lbNombreProducto);
		
		textFieldProducto = new JTextField();
		textFieldProducto.setBounds(20, 136, 343, 26);
		
		textFieldProducto.setForeground(Color.WHITE);
		textFieldProducto.setBackground(null);
		textFieldProducto.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
		panelInformacionBasica.add(textFieldProducto);
		textFieldProducto.setColumns(10);
		
		JLabel lbUnidad = new JLabel("Unidad");
		lbUnidad.setBounds(20, 188, 98, 12);
		lbUnidad.setFont(Fonts.custom);
		lbUnidad.setForeground(Color.WHITE);
		panelInformacionBasica.add(lbUnidad);
		
		textFieldUnidad = new JTextField();
		textFieldUnidad.setBounds(20, 218, 343, 26);
		textFieldUnidad.setColumns(10);
		textFieldUnidad.addKeyListener( new KeyAdapter() {
			 @Override
			    public void keyReleased(KeyEvent e) {
				 		if(textFieldUnidad.getText().trim().matches("\\d+")) {
				 			textFieldUnidad.setText("");
				 		}
			    }
		});
		
		textFieldUnidad.setForeground(Color.WHITE);
		textFieldUnidad.setBackground(null);
		textFieldUnidad.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
		panelInformacionBasica.add(textFieldUnidad);
		
		JLabel lbCantidad = new JLabel("Cantidad");
		lbCantidad.setBounds(20, 272, 114, 12);
		lbCantidad.setFont(Fonts.custom);
		lbCantidad.setForeground(Color.WHITE);
		panelInformacionBasica.add(lbCantidad);
		
		textFieldCantidad = new JSpinner(new SpinnerNumberModel(10, 1, null, 1));
		textFieldCantidad.setBounds(20, 312, 343, 26);
		textFieldCantidad.setEditor(new JSpinner.NumberEditor(textFieldCantidad, "#"));
		Filtros.aplicarFiltroNumericoSpinner(textFieldCantidad);
		textFieldCantidad.setBackground(null);
		textFieldCantidad.setForeground(Color.white);
		textFieldCantidad.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
		panelInformacionBasica.add(textFieldCantidad);
		
		JLabel lbPrecio = new JLabel("Precio");
		lbPrecio.setBounds(376, 106, 98, 12);
		lbPrecio.setFont(Fonts.custom);
		lbPrecio.setForeground(Color.WHITE);
		panelInformacionBasica.add(lbPrecio);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setBounds(376, 136, 343, 26);
		Filtros.aplicarFiltroNumericoTextField(textFieldPrecio);
		textFieldPrecio.setColumns(10);
		textFieldPrecio.setForeground(Color.WHITE);
		textFieldPrecio.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
		textFieldPrecio.setBackground(null);
		panelInformacionBasica.add(textFieldPrecio);
		
		JLabel lbCategoria = new JLabel("Categoria");
		lbCategoria.setBounds(376, 188, 98, 12);
		lbCategoria.setForeground(Color.WHITE);
		lbCategoria.setFont(Fonts.custom);
		panelInformacionBasica.add(lbCategoria);
		
		textFieldCategoria = new JComboBox<>(Categoria.values());
		textFieldCategoria.setBounds(376, 218, 343, 26);
		textFieldCategoria.setSelectedItem(null);
		textFieldCategoria.setBackground(new Color(56,56,56));
		textFieldCategoria.setForeground(Color.WHITE);
		textFieldCategoria.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
		panelInformacionBasica.add(textFieldCategoria);
		{
			buttonPane = new JPanel();
			buttonPane.setBackground(new Color(56,56,56));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnGuardar = new JButton("Guardar");
				BtnStyle.primary(btnGuardar, Color.BLACK);
				btnGuardar.setFont(Fonts.custom);
				
				
				btnContinuar = new JButton("Continuar");
				btnContinuar.setFont(Fonts.custom);
				BtnStyle.primary(btnContinuar,Color.BLACK);
				btnContinuar.addActionListener(this);
				
				btnRegresar = new JButton("Regresar");
				btnRegresar.setFont(Fonts.custom);
				BtnStyle.primary(btnRegresar, Color.BLACK);
				btnRegresar.addActionListener(this);
				
				
				buttonPane.add(btnContinuar);
			
				getRootPane().setDefaultButton(btnGuardar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setFont(Fonts.custom);
				BtnStyle.primary(btnCancelar, Color.BLACK);
				btnCancelar.addActionListener(e -> this.dispose());
				buttonPane.add(btnCancelar);
			}
		}
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnContinuar) {
		
			double precio = 0;
			int cantidad = 0;
			if(!textFieldPrecio.getText().equals("")  && textFieldCantidad.getValue() !=  null ) {
				
				precio = Double.parseDouble(textFieldPrecio.getText());
				cantidad =  (int)textFieldCantidad.getValue();

				/*producto = /* new Producto(textFieldProducto.getText(), precio, (Categoria)textFieldCategoria.getSelectedItem(), 
						cantidad,  textFieldUnidad.getText())*/;
				
				if(context.getProductoController().validarProductoInformacionBase(producto)) {
					panelInformacionBasica.setVisible(false);
					panelDetalleProducto.setVisible(true);
					buttonPane.add(btnRegresar);
					buttonPane.remove(btnContinuar);
					buttonPane.add(btnGuardar);
					
				}else {
					
					new Messages(dahsboard, "Rellene los campos correctamente").messageError();
					return;
				}
				
			}else {	
				new Messages(dahsboard, "Ingrese una cantidad y precio validos").messageError();
				return;
				
			}
			
			
			
			
		}
		
		if(e.getSource() == btnRegresar) {
			
			panelInformacionBasica.setVisible(true);
			panelDetalleProducto.setVisible(false);
			buttonPane.remove(btnGuardar);
			buttonPane.remove(btnRegresar);
			buttonPane.add(btnContinuar);
			
		}
		
		if(e.getSource() == btnGuardar) {
			
			/*gestionar cuando se cree el controller de  detalles y proveedores*/
			
		}
		
	}
}
