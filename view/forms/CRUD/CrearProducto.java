package view.forms.CRUD;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JButton;
import model.Categoria;
import model.Producto;
import model.Proveedor;
import utils.BtnStyle;
import utils.Fonts;
import utils.FrameDragger;
import utils.Messages;
import view.modules.products.productoServicio;

public class CrearProducto extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textFieldNombreP;
	private JButton Continuar, btnClose, btnGuardar,  btnRegresar  ;
	private JComboBox<Proveedor> comboBoxProevedor;
	private JTextField textFieldUnidadP;
	private DatePicker datePickerFecha;
	private JSpinner cantidadProductoSpinner;
	private JFormattedTextField formattedTextFieldPrecio, formattedTextFieldCodigo;
	private JTextField textFieldMarcaDp;
	private JComboBox<Categoria> comboBoxCategoria;
	private JPanel panelDetallesDelProducto,  panelDatosForms,  panelInformacionBase;
	private JComboBox<String> comboBoxOrigen;

	private productoServicio pS;
	
	private Map<Object, Runnable> acciones;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlatLightLaf.setup();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public CrearProducto() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		new FrameDragger(this);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\img\\iconApp.png"));
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 776, 470);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		
		pS = new productoServicio();
		
		acciones = new HashMap<>();
		

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelDatosForms = new JPanel();
		panelDatosForms.setBackground(new Color(255, 255, 255));
		panelDatosForms.setBounds(0, 0, 776, 481);
		contentPane.add(panelDatosForms);
		panelDatosForms.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Crear");
		lblNewLabel.setFont(Fonts.bold);
		lblNewLabel.setBounds(106, 23, 270, 25);
		lblNewLabel.setFont(Fonts.bold);
		panelDatosForms.add(lblNewLabel);
		
		JLabel lbLogo = new JLabel("");
		lbLogo.setIcon(new ImageIcon("resources\\img\\agregar-producto.png"));
		lbLogo.setBounds(26, 20, 84, 64);
		panelDatosForms.add(lbLogo);
		
		panelInformacionBase = new JPanel();
		panelInformacionBase.setBackground(new Color(255, 255, 255));
		panelInformacionBase.setBounds(10, 84, 700, 307);
		panelDatosForms.add(panelInformacionBase);
		panelInformacionBase.setLayout(null);
		
		JLabel lbCodigoProducto = new JLabel("Código ");
		lbCodigoProducto.setBounds(10, 61, 100, 14);
		lbCodigoProducto.setFont(Fonts.custom);
		panelInformacionBase.add(lbCodigoProducto);
		
		JLabel lbInformacionBase = new JLabel("Información  Base");
		lbInformacionBase.setFont(Fonts.custom);
		lbInformacionBase.setBounds(10, 23, 297, 15);
		panelInformacionBase.add(lbInformacionBase);
		
		formattedTextFieldCodigo = new JFormattedTextField();
		formattedTextFieldCodigo.setBounds(10, 95, 312, 25);
		panelInformacionBase.add(formattedTextFieldCodigo);
		
		JLabel lbNombreProducto = new JLabel("Nombre");
		lbNombreProducto.setBounds(10, 143, 100, 12);
		lbNombreProducto.setFont(Fonts.custom);
		panelInformacionBase.add(lbNombreProducto);
		
		textFieldNombreP = new JTextField();
		textFieldNombreP.setBounds(10, 177, 312, 25);
		panelInformacionBase.add(textFieldNombreP);
		textFieldNombreP.setColumns(10);
		
		JLabel lbUnidad = new JLabel("Unidad");
		lbUnidad.setFont(Fonts.custom);
		lbUnidad.setBounds(10, 223, 100, 12);
		panelInformacionBase.add(lbUnidad);
		
		textFieldUnidadP = new JTextField();
		textFieldUnidadP.setBounds(10, 257, 312, 25);
		panelInformacionBase.add(textFieldUnidadP);
		textFieldUnidadP.setColumns(10);
		
		JLabel lbCategoria = new JLabel("Categoría ");
		lbCategoria.setFont(Fonts.custom);
		lbCategoria.setBounds(354, 61, 100, 14);
		panelInformacionBase.add(lbCategoria);
		
		 comboBoxCategoria = new JComboBox<Categoria>();
		comboBoxCategoria.setBounds(354, 95, 312, 25);
		panelInformacionBase.add(comboBoxCategoria);
		
		JLabel lbPrecio = new JLabel("Precio");
		lbPrecio.setFont(Fonts.custom);
		lbPrecio.setBounds(354, 143, 100, 12);
		panelInformacionBase.add(lbPrecio);
		
		formattedTextFieldPrecio = new JFormattedTextField();
		formattedTextFieldPrecio.setBounds(354, 180, 312, 25);
		panelInformacionBase.add(formattedTextFieldPrecio);
		
		JLabel lbCantidadProducto = new JLabel("Cantidad ");
		lbCantidadProducto.setBounds(354, 223, 100, 12);
		lbCantidadProducto.setFont(Fonts.custom);
		panelInformacionBase.add(lbCantidadProducto);
		
		cantidadProductoSpinner = new JSpinner();
		cantidadProductoSpinner.setBounds(354, 257, 312, 25);
		panelInformacionBase.add(cantidadProductoSpinner);
	
	
		Continuar = new JButton("Continuar");
		BtnStyle.primary(Continuar, new Color(64, 128, 128));
		Continuar.setFont(Fonts.custom);
		Continuar.setBounds(10, 401, 163, 31);
		Continuar.addActionListener(this);
		panelDatosForms.add(Continuar);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(510, 340, 163, 31);
		btnGuardar.setFont(Fonts.custom);
		BtnStyle.primary(btnGuardar, new Color(64, 128, 128));
		btnGuardar.setVisible(false);
		panelDatosForms.add(btnGuardar);
		
		btnClose = new JButton("");
		BtnStyle.flat(btnClose);
		btnClose.addActionListener(this);
		btnClose.setIcon(new ImageIcon("resources\\img\\clos.png"));
		btnClose.setBounds(711, 10, 65, 30);
		panelDatosForms.add(btnClose);
		
		panelDetallesDelProducto = new JPanel();
		panelDetallesDelProducto.setBackground(new Color(255, 255, 255));
		panelDetallesDelProducto.setBounds(10, 84, 700, 222);
		panelDetallesDelProducto.setVisible(false);
		panelDatosForms.add(panelDetallesDelProducto);
		panelDetallesDelProducto.setLayout(null);
		
		JLabel lbDetalles = new JLabel("Detalles del producto");
		lbDetalles.setBounds(10, 23, 250, 30);
		lbDetalles.setFont(Fonts.custom);
		panelDetallesDelProducto.add(lbDetalles);
		
		JLabel lbMarca = new JLabel("Marca");
		lbMarca.setBounds(10, 61, 108, 12);
		lbMarca.setFont(Fonts.custom);
		panelDetallesDelProducto.add(lbMarca);
		
		textFieldMarcaDp = new JTextField();
		textFieldMarcaDp.setBounds(10, 95, 312, 25);
		panelDetallesDelProducto.add(textFieldMarcaDp);
		textFieldMarcaDp.setColumns(10);
		
		JLabel lbProductoO = new JLabel("Origen");
		lbProductoO.setBounds(10, 143, 145, 12);
		lbProductoO.setFont(Fonts.custom);
		panelDetallesDelProducto.add(lbProductoO);
		
		comboBoxOrigen = new JComboBox<>();
		comboBoxOrigen.setBounds(10, 177, 312, 25);
		panelDetallesDelProducto.add(comboBoxOrigen);
		
		JLabel lbProevedor = new JLabel("Proevedor");
		lbProevedor.setBounds(352, 61, 95, 12);
		lbProevedor.setFont(Fonts.custom);
		panelDetallesDelProducto.add(lbProevedor);
		
		comboBoxProevedor = new JComboBox<>();
		comboBoxProevedor.setBounds(354, 95, 312, 25);
		panelDetallesDelProducto.add(comboBoxProevedor);
		
		JLabel lbFecha = new JLabel("Fecha");
		lbFecha.setFont(Fonts.custom);
		lbFecha.setBounds(354, 143, 44, 12);
		panelDetallesDelProducto.add(lbFecha);
		
		datePickerFecha = new DatePicker();
		datePickerFecha.setBounds(354, 180, 312, 25);
		panelDetallesDelProducto.add(datePickerFecha);
		
		JLabel lblNuevoProducto = new JLabel("nuevo producto");
		lblNuevoProducto.setBounds(106, 46, 270, 25);
		lblNuevoProducto.setFont(Fonts.bold);
		panelDatosForms.add(lblNuevoProducto);
		
		btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(20, 340, 163, 31);
		btnRegresar.setVisible(false);
		btnRegresar.addActionListener(this);
		panelDatosForms.add(btnRegresar);
		
		acciones.put(Continuar, this::continueForm);
		acciones.put(btnRegresar, this::goBackForm);
		acciones.put(btnClose, this::closeWindows);
		acciones.put(btnGuardar, this::saveData);
			
	}
	
	public void saveData() {
		
		
	}

	public void  continueForm() {
		
	String precioText = formattedTextFieldPrecio.getText();
	Object cantidadText = cantidadProductoSpinner.getValue();
	
	if(precioText.isEmpty() || cantidadText == null) {	
		new Messages(this, "Ingrese un precio y cantidad validos").messageError();
		return;
	}
	
	
	double precio;
	int cantidad;

	try {
		
		precio = Double.parseDouble(precioText);
		cantidad = (int) cantidadText;
		
	}catch(NumberFormatException e) {
		new Messages(this, "Formato numerico invalido para cantidad o precio").messageError();
		return;
	}
	
	boolean exito =	pS.validarCreacionProducto(new Producto(formattedTextFieldCodigo.getText(), textFieldNombreP.getText(),
				precio, (Categoria)comboBoxCategoria.getSelectedItem(), cantidad, textFieldUnidadP.getText()), "continuar");
		
		if(exito) {
				panelInformacionBase.setVisible(false);
				panelDetallesDelProducto.setVisible(true);
				btnRegresar.setVisible(true);
				btnGuardar.setVisible(true);
				Continuar.setVisible(false);
				
		} else {
			new Messages(this, "Rellene los campos correctamente").messageError();
		}
		
	}
	
	public void goBackForm() {
		panelInformacionBase.setVisible(true);
		panelDetallesDelProducto.setVisible(false);
		btnRegresar.setVisible(false);
		btnGuardar.setVisible(false);
		Continuar.setVisible(true);
		
	}
	
	public void closeWindows() {
		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Runnable accion = acciones.get(e.getSource());
		if(accion != null ) accion.run();
	}
	
	
	
	public void llenarComboBox() {
		
		// despues de recibir  los proevedores   de la BD
		//favor llenar el la list correspondientes 
	}
}
