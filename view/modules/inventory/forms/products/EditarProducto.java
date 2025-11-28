package view.modules.inventory.forms.products;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.Categoria;
import model.Detalles;
import model.Producto;
import model.Proveedor;
import view.AplicationContext;
import view.components.BtnStyle;
import view.components.Fonts;
import view.components.Messages;
import view.dashboard.Dahsboard;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.SpinnerNumberModel;
import com.github.lgooddatepicker.components.DatePicker;

public class EditarProducto extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final Dahsboard dahsboard;
	private final AplicationContext context;
	private JTextField textFieldId;
	private JTextField textFieldNombre;
	private JTextField textFieldUnidad;
	private DatePicker datePicker;
	private JTextField textFieldPrecio;
	private JComboBox<Categoria> selectCategoria;
	private JComboBox<Proveedor> selectProveedor;
	private JSpinner textFieldCantidad ;
	private JRadioButton btnEstadoActivo, btnEstadoSinStock, btnEstadoBloqueado;
	private JButton btnGuardar, btnCancelar, btnLimpiar, btnContinuar, btnRegresar;
	private Producto producto;
	@SuppressWarnings("unused")
	private Detalles detalles;
	private JPanel buttonPane, panelDatosProductos;
	private JPanel panelDetallesProducto;
	private JLabel lbMarca;
	private JTextField textFieldMarca;
	private JComboBox<String> textFieldOrigen;

	public static void main(String[] args) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EditarProducto(AplicationContext context, Dahsboard dahsboard ) {
		
		this.context = context;
		this.dahsboard = dahsboard;
		setBounds(100, 100, 770, 519);
		setBackground(new Color(56,56,56));
		getContentPane().setBackground(new Color(56,56,56));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(56,56,56));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lbEditarProducto = new JLabel("Editar Producto");
		lbEditarProducto.setForeground(Color.WHITE);
		lbEditarProducto.setFont(Fonts.bold);
		lbEditarProducto.setBounds(22, 37, 278, 21);
		contentPanel.add(lbEditarProducto);
		
		panelDatosProductos = new JPanel();
		panelDatosProductos.setBounds(10, 79, 736, 372);
		panelDatosProductos.setBackground(null);
		panelDatosProductos.setLayout(null);
		contentPanel.add(panelDatosProductos);

		textFieldId = new JTextField();
		textFieldId.setBounds(23, 68, 320, 26);
		textFieldId.addActionListener(this);
		panelDatosProductos.add(textFieldId);
		textFieldId.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Introducir el id del producto");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(Fonts.custom);
		lblNewLabel.setBounds(22, 24, 321, 20);
		panelDatosProductos.add(lblNewLabel);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(377, 68, 346, 26);
		panelDatosProductos.add(textFieldNombre);
		
		JLabel lbNombre = new JLabel("Nombre");
		lbNombre.setForeground(Color.WHITE);
		lbNombre.setFont(Fonts.custom);
		lbNombre.setBounds(377, 24, 170, 20);
		panelDatosProductos.add(lbNombre);
		
		JLabel lbUnidad = new JLabel("Unidad");
		lbUnidad.setForeground(Color.WHITE);
		lbUnidad.setFont(Fonts.custom);
		lbUnidad.setBounds(23, 118, 170, 20);
		panelDatosProductos.add(lbUnidad);
		
		textFieldUnidad = new JTextField();
		textFieldUnidad.setColumns(10);
		textFieldUnidad.setBounds(23, 162, 320, 26);
		panelDatosProductos.add(textFieldUnidad);
		
		JLabel lbPrecio = new JLabel("Precio");
		lbPrecio.setForeground(Color.WHITE);
		lbPrecio.setFont(Fonts.custom);
		lbPrecio.setBounds(377, 118, 170, 20);
		panelDatosProductos.add(lbPrecio);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setColumns(10);
		textFieldPrecio.setBounds(377, 162, 346, 26);
		panelDatosProductos.add(textFieldPrecio);
		
		JLabel lbCategoria = new JLabel("Categoria");
		lbCategoria.setForeground(Color.WHITE);
		lbCategoria.setBounds(23, 216, 170, 20);
		panelDatosProductos.add(lbCategoria);
		
		selectCategoria = new JComboBox<>(Categoria.values());
		selectCategoria.setSelectedItem(null);
		selectCategoria.setBounds(23, 257, 700, 26);
		panelDatosProductos.add(selectCategoria);
		
		
		JLabel lbCantidad = new JLabel("Cantidad");
		lbCantidad.setFont(Fonts.custom);
		lbCantidad.setForeground(Color.WHITE);
		lbCantidad.setBounds(23, 293, 170, 20);
		panelDatosProductos.add(lbCantidad);
		
		textFieldCantidad = new JSpinner();
		textFieldCantidad.setModel(new SpinnerNumberModel(Integer.valueOf(10), Integer.valueOf(1), null, Integer.valueOf(1)));
		textFieldCantidad.setBounds(22, 330, 346, 26);
		panelDatosProductos.add(textFieldCantidad);
		
		JLabel Estado = new JLabel("Estado");
		Estado.setForeground(Color.WHITE);
		Estado.setFont(Fonts.custom);
		Estado.setBounds(391, 293, 170, 20);
		panelDatosProductos.add(Estado);
		
		btnEstadoActivo = new JRadioButton("Disponible");
		btnEstadoActivo.setBackground(null);
		btnEstadoActivo.setForeground(Color.WHITE);
		btnEstadoActivo.setFont(Fonts.custom);
		btnEstadoActivo.setBounds(392, 319, 102, 20);
		panelDatosProductos.add(btnEstadoActivo);
		
		btnEstadoSinStock = new JRadioButton("Sin stock");
		btnEstadoSinStock.setBounds(392, 340, 102, 20);
		btnEstadoSinStock.setBackground(null);
		btnEstadoSinStock.setForeground(Color.WHITE);
		btnEstadoSinStock.setFont(Fonts.custom);
		panelDatosProductos.add(btnEstadoSinStock);
		
		btnEstadoBloqueado = new JRadioButton("Bloqueado");
		btnEstadoBloqueado.setBounds(516, 320, 102, 20);
		btnEstadoBloqueado.setBackground(null);
		btnEstadoBloqueado.setForeground(Color.WHITE);
		btnEstadoBloqueado.setFont(Fonts.custom);
		panelDatosProductos.add(btnEstadoBloqueado);
		
		ButtonGroup group = new ButtonGroup();
		group.add(btnEstadoActivo);
		group.add(btnEstadoSinStock);
		group.add(btnEstadoBloqueado);
		
		panelDetallesProducto = new JPanel();
		panelDetallesProducto.setBounds(22, 89, 724, 255);
		panelDetallesProducto.setVisible(false);
		panelDetallesProducto.setBackground(null);
		contentPanel.add(panelDetallesProducto);
		panelDetallesProducto.setLayout(null);
		
		lbMarca = new JLabel("Marca ");
		lbMarca.setForeground(Color.WHITE);
		lbMarca.setFont(Fonts.custom);
		lbMarca.setBounds(23, 32, 183, 12);
		panelDetallesProducto.add(lbMarca);
		
		textFieldMarca = new JTextField();
		textFieldMarca.setBounds(23, 76, 320, 26);
		panelDetallesProducto.add(textFieldMarca);
		textFieldMarca.setColumns(10);
		
		JLabel lbOrigen = new JLabel("Origen");
		lbOrigen.setForeground(Color.WHITE);
		lbOrigen.setFont(Fonts.custom);
		lbOrigen.setBounds(363, 32, 183, 12);
		panelDetallesProducto.add(lbOrigen);
		
		JLabel lbProveedor = new JLabel("Proveedor");
		lbProveedor.setForeground(Color.WHITE);
		lbProveedor.setFont(Fonts.custom);
		lbProveedor.setBounds(23, 137, 183, 12);
		panelDetallesProducto.add(lbProveedor);
		
		selectProveedor = new JComboBox<>();
		selectProveedor.setSelectedItem(null);
		selectProveedor.setBounds(23, 180, 320, 26);
		panelDetallesProducto.add(selectProveedor);
		
		JLabel lbFecha = new JLabel("Fecha");
		lbFecha.setFont(Fonts.custom);
		lbFecha.setForeground(Color.WHITE);
		lbFecha.setBounds(363, 137, 183, 12);
		panelDetallesProducto.add(lbFecha);
		
		datePicker = new DatePicker();
		datePicker.setBounds(366, 180, 317, 26);
		panelDetallesProducto.add(datePicker);
		
		textFieldOrigen = new JComboBox<>();
		textFieldOrigen.setSelectedItem(null);
		textFieldOrigen.addItem("Importado");
		textFieldOrigen.addItem("Nacional");
		textFieldOrigen.setBounds(363, 76, 320, 26);
		panelDetallesProducto.add(textFieldOrigen);
		
		
		{
			buttonPane = new JPanel();
			buttonPane.setBackground(new Color(56,56,56));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnGuardar = new JButton("Guardar");
				btnGuardar.addActionListener(this);
				BtnStyle.primary(btnGuardar, Color.BLACK);
				buttonPane.add(btnGuardar);
				
				btnLimpiar = new JButton("Limpiar");
				btnLimpiar.addActionListener(this);
				BtnStyle.primary(btnLimpiar, Color.BLACK);
				buttonPane.add(btnLimpiar);
			
				btnContinuar = new JButton("Continuar");
				BtnStyle.primary(btnContinuar, Color.BLACK);
				btnContinuar.setEnabled(false);
				btnContinuar.addActionListener(this);
				buttonPane.add(btnContinuar);
	
				btnRegresar = new JButton("Regresar");
				BtnStyle.primary(btnRegresar, Color.BLACK);
				btnRegresar.addActionListener(this);
				
			}
			{
				btnCancelar = new JButton("Cancelar");
				BtnStyle.primary(btnCancelar, Color.BLACK);
				btnCancelar.addActionListener( e -> this.dispose());
				buttonPane.add(btnCancelar);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == textFieldId) {
			int idProducto;
			if(textFieldId.getText().trim().matches("\\d+")) {	
				idProducto = Integer.parseInt(textFieldId.getText().trim());
				producto = context.getProductoController().buscar(idProducto);
				
				if(producto == null) {
					new Messages(dahsboard, "Usuario no encontrado").messageError();
					return;
					
				}else {
					
					textFieldId.setText(producto.getCodigo() + "");
					textFieldId.setEditable(false);
					textFieldNombre.setText(producto.getNombre());
					textFieldUnidad.setText(producto.getUnidad());
					textFieldPrecio.setText(producto.getPrecio() + "");
					selectCategoria.setSelectedItem(producto.getCategoria());
					textFieldCantidad.setValue(producto.getCantida());	
					btnEstadoBloqueado.setSelected(producto.getActivo() == 0);
					
					if(producto.getActivo() == 1) {
						boolean cantidad = producto.getCantida() > 10;
						btnEstadoActivo.setSelected(cantidad);
						btnEstadoSinStock.setSelected(!cantidad);
					}
					
					btnContinuar.setEnabled(true);
					//completar los campos de detalle productos cuando se cree la clase correspondiente 
					//y este la table en la BD
					
					/*
					 * textFieldMarca
					 * datePicker
					 * selectProveedor
					 * textFieldOrigen
					 * 
					 * rellenar cuando se cree los elementos correspondientes 
					 * detalles = new Detalles();
					 * */
					
					
				}
		
			}else {
				new Messages(dahsboard, "Ingrese un id valido").messageError();
				return;
			}
		}
		
		if(e.getSource() == btnGuardar) {
			if(textFieldId.getText().equals("") || textFieldId.getText() == null) {
				new Messages(dahsboard, "No se ha buscado el producto a editar").messageError();
				return;
			}
			else {
				
			}
			
		}
		
		if(e.getSource() == btnLimpiar) {

			textFieldId.setText("");
			textFieldId.setEditable(true);
			textFieldNombre.setText("");
			textFieldUnidad.setText("");
			textFieldPrecio.setText("");
			selectCategoria.setSelectedItem(null);
			textFieldCantidad.setValue(10);	
			btnEstadoBloqueado.setSelected(false);
			btnEstadoActivo.setSelected(false);
			btnEstadoSinStock.setSelected(false);
		}
		
		if(e.getSource() == btnContinuar) {
			if(context.getProductoController().validarProductoInformacionBase(producto)) {
				buttonPane.add(btnRegresar);
				buttonPane.remove(btnContinuar);
				panelDetallesProducto.setVisible(true);
				panelDatosProductos.setVisible(false);
				
			}else {
				new Messages(dahsboard, "Rellene todos los campos correctamente").messageError();
				return;
			}
			
		}
		
		if(e.getSource() == btnRegresar) {
			buttonPane.remove(btnRegresar);
			buttonPane.add(btnContinuar);
			panelDetallesProducto.setVisible(false);
			panelDatosProductos.setVisible(true);
			
		}
		
		if(e.getSource() == btnGuardar) {
			
			/*elementos comentados hasta que se creen los metodos correspondientes
			 * 
			 * 
			 * if(context.getDetalleController().validarDetallesProducto(detalles)){
			 * 		 context.getProductoController().editar(producto);
			 * 		 context.getDetallesController().editar(detalles);
			 * 		
			 * }else{ 
			 *	 new Messages(dahsboard, "Rellene los detalles del producto correctamente").messageError();
			 * 	return;
			 * }
			 * */
			
		}
	}
}
