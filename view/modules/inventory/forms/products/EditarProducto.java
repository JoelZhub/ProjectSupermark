package view.modules.inventory.forms.products;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

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
import view.components.Filtros;
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
	private JTextField textFieldNombre;
	private JTextField textFieldUnidad;
	private JTextField textFieldPrecio;
	private JComboBox<Categoria> selectCategoria;
	private JComboBox<Proveedor> selectProveedor;
	private JSpinner textFieldCantidad ;
	private JRadioButton btnEstadoActivo, btnEstadoSinStock, btnEstadoBloqueado;
	private JButton btnGuardar, btnCancelar, btnLimpiar, btnContinuar, btnRegresar;
	private Producto producto;
	@SuppressWarnings("unused")
	private Detalles detalles;
	private Proveedor proveedor;
	private JPanel buttonPane, panelDatosProductos;
	private JPanel panelDetallesProducto;
	private JLabel lbMarca;
	private JTextField textFieldMarca;
	private JComboBox<String> textFieldOrigen;
	private JComboBox<Producto> SelectProducto;
	private DatePicker fechaAgregado;
	private int idDetalle;
	
	private int idProducto;

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
		
		JLabel lbSeleccionar = new JLabel("Seleccionar producto");
		lbSeleccionar.setForeground(Color.WHITE);
		lbSeleccionar.setFont(Fonts.custom);
		lbSeleccionar.setBounds(22, 24, 321, 20);
		panelDatosProductos.add(lbSeleccionar);
		
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
		Filtros.aplicarFiltroSoloLetras(textFieldUnidad);
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
		Filtros.aplicarFiltroNumericoTextField(textFieldPrecio);
		textFieldPrecio.setBounds(377, 162, 346, 26);
		panelDatosProductos.add(textFieldPrecio);
		
		JLabel lbCategoria = new JLabel("Categoria");
		lbCategoria.setForeground(Color.WHITE);
		lbCategoria.setBounds(23, 216, 170, 20);
		panelDatosProductos.add(lbCategoria);
		
		selectCategoria = new JComboBox<>(Categoria.values());
		selectCategoria.setSelectedItem(null);
		selectCategoria.setBounds(23, 258, 700, 26);
		panelDatosProductos.add(selectCategoria);
		
		
		JLabel lbCantidad = new JLabel("Cantidad");
		lbCantidad.setFont(Fonts.custom);
		lbCantidad.setForeground(Color.WHITE);
		lbCantidad.setBounds(23, 293, 170, 20);
		panelDatosProductos.add(lbCantidad);
		
		textFieldCantidad = new JSpinner();
		Filtros.aplicarFiltroNumericoSpinner(textFieldCantidad);
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
		
		SelectProducto = new JComboBox<>();
		
		for(Producto po: context.getProductoController().listarProductos()) {
				if(po.getActivo() == 1) {
					SelectProducto.addItem(po);
				}
		}
		
		SelectProducto.setSelectedItem(null);
		SelectProducto.addActionListener(this);
		SelectProducto.setBounds(23, 68, 320, 26);
		panelDatosProductos.add(SelectProducto);
		
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
		
		for(Proveedor por: context.getProveedorController().listarProveedores()) {
			if(por.getActivo() == 1) {
				selectProveedor.addItem(por);
			}
		}
		
		selectProveedor.setSelectedItem(null);
		selectProveedor.setBounds(23, 180, 320, 26);
		panelDetallesProducto.add(selectProveedor);
		
		JLabel lbFecha = new JLabel("Fecha");
		lbFecha.setFont(Fonts.custom);
		lbFecha.setForeground(Color.WHITE);
		lbFecha.setBounds(363, 137, 183, 12);
		panelDetallesProducto.add(lbFecha);
		
		textFieldOrigen = new JComboBox<>();
		textFieldOrigen.setSelectedItem(null);
		textFieldOrigen.addItem("Importado");
		textFieldOrigen.addItem("Nacional");
		textFieldOrigen.setBounds(363, 76, 320, 26);
		panelDetallesProducto.add(textFieldOrigen);
		
		fechaAgregado = new DatePicker();
		fechaAgregado.setBounds(363, 180, 320, 26);
		panelDetallesProducto.add(fechaAgregado);
		
		
		{
			buttonPane = new JPanel();
			buttonPane.setBackground(new Color(56,56,56));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnGuardar = new JButton("Guardar");
				btnGuardar.setFont(Fonts.custom);
				btnGuardar.addActionListener(this);
				BtnStyle.primary(btnGuardar, Color.BLACK);
					
				btnLimpiar = new JButton("Limpiar");
				btnLimpiar.setFont(Fonts.custom);
				btnLimpiar.addActionListener(this);
				BtnStyle.primary(btnLimpiar, Color.BLACK);
				buttonPane.add(btnLimpiar);
				
				btnContinuar = new JButton("Continuar");
				btnContinuar.setFont(Fonts.custom);
				BtnStyle.primary(btnContinuar, Color.BLACK);
				btnContinuar.addActionListener(this);
				buttonPane.add(btnContinuar);
	
				btnRegresar = new JButton("Regresar");
				btnRegresar.setFont(Fonts.custom);
				BtnStyle.primary(btnRegresar, Color.BLACK);
				btnRegresar.addActionListener(this);
				
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setFont(Fonts.custom);
				BtnStyle.primary(btnCancelar, Color.BLACK);
				btnCancelar.addActionListener( e -> this.dispose());
				buttonPane.add(btnCancelar);
			}
		}
	}

	

	 
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == SelectProducto){
				
			producto = (Producto) SelectProducto.getSelectedItem();
			if (producto.getDetalles() == null) {
			    new Messages(dahsboard, "Este producto no tiene detalles registrados").messageError();
			    return;
			}
			idDetalle = producto.getDetalles().getIdDetalleProducto();
			idProducto = producto.getCodigo();
			textFieldNombre.setText(producto.getNombre());
			textFieldUnidad.setText(producto.getUnidad());
			textFieldPrecio.setText(producto.getPrecio() + "");
			selectCategoria.setSelectedItem(producto.getCategoria());
			textFieldCantidad.setValue(producto.getCantida());	
			btnEstadoBloqueado.setSelected(producto.getActivo() == 0);
			if(producto.getActivo() == 1) {
				boolean cantidad = producto.getCantida() > 0;
				btnEstadoActivo.setSelected(cantidad);
				btnEstadoSinStock.setSelected(!cantidad);
			}
			
			LocalDate f1 = Instant.ofEpochMilli(producto.getDetalles().getFechaAgregado().getTime())
			        .atZone(ZoneId.systemDefault())
			        .toLocalDate();
			
			fechaAgregado.setDate(f1);
			proveedor = context.getProveedorController().buscarProveedor(producto.getDetalles().getIdProveedor());
			selectProveedor.setSelectedItem(proveedor);
			textFieldOrigen.setSelectedItem(producto.getDetalles().getOrigen());
			textFieldMarca.setText(producto.getDetalles().getMarca());
			
			
		}
		
		if(e.getSource() == btnLimpiar) {

			textFieldNombre.setText("");
			textFieldUnidad.setText("");
			textFieldPrecio.setText("");
			selectCategoria.setSelectedItem(null);
			textFieldCantidad.setValue(10);	
			btnEstadoBloqueado.setSelected(false);
			btnEstadoActivo.setSelected(false);
			btnEstadoSinStock.setSelected(false);
			
			selectProveedor.setSelectedItem(null);
			textFieldMarca.setText("");
			textFieldOrigen.setSelectedItem(null);
		}
		
		if(e.getSource() == btnContinuar) {
		
			if(SelectProducto.getSelectedItem() == null) {
				new Messages(dahsboard, "Seleccione un producto valido").messageError();
				return;
			}
			else {
			Double precio = 0.0;
			
			
			if(textFieldPrecio.getText().trim() != null) {
				
				precio = Double.parseDouble(textFieldPrecio.getText());
			}
			int cantidad = (int)  textFieldCantidad.getValue();
			int activo = 0;
			
			if(btnEstadoActivo.isSelected()) {
				activo = 1;
			}
			if(btnEstadoBloqueado.isSelected()) {
				activo = 0;
			}
			if(btnEstadoSinStock.isSelected()) {
				if(cantidad <= 0) {
					new Messages(dahsboard, "Este producto tiene una cantidad mayor a 0 por lo que no puede marcarse sin stock").messageError();
					return;
				}else {
					activo = 0;
				}
			}
			
			producto = new Producto(
					idProducto,
					textFieldNombre.getText(),
					precio,
					(Categoria) selectCategoria.getSelectedItem(),
					cantidad,
					textFieldUnidad.getText(),
					activo
		
				);
			
			if(context.getProductoController().validarProductoInformacionBase(producto)) {
				
				buttonPane.add(btnGuardar);
				buttonPane.add(btnRegresar);
				buttonPane.remove(btnContinuar);
				buttonPane.remove(btnCancelar);
				buttonPane.add(btnCancelar);
				panelDetallesProducto.setVisible(true);
				panelDatosProductos.setVisible(false);
				
			}else {
				new Messages(dahsboard, "Rellene todos los campos correctamente").messageError();
				return;
			}
			
		}
			
		}
		
		if(e.getSource() == btnRegresar) {
			buttonPane.remove(btnRegresar);
			buttonPane.remove(btnGuardar);
			buttonPane.add(btnContinuar);
			panelDetallesProducto.setVisible(false);
			panelDatosProductos.setVisible(true);
			
		}
		
		if(e.getSource() == btnGuardar) {
	
			var po = (Proveedor) selectProveedor.getSelectedItem();
			String origen = (String) textFieldOrigen.getSelectedItem();
			
			LocalDate ld = fechaAgregado.getDate();
			Date sqlDate = Date.valueOf(ld);
	
			detalles = new Detalles(
					idDetalle,
					producto.getCodigo(),
					textFieldMarca.getText(),
					po,
					origen,
					sqlDate
				);
//			
			
			if(context.getProductoController().validarProductoDetalles(detalles)) {
				producto.setDetalles(detalles);
				if(new Messages(dahsboard, "Esta por modificar la información de este producto, ¿Desea continuar?").messageWarning()) {
					if(context.getProductoController().editar(producto)) {
						new Messages(dahsboard, "Producto editado correctamente").messageAlert();
						return;
						
					}else {
						new Messages(dahsboard, "Error al editar la informacion del producto").messageError();
						return;
					}
					
				}else {
					new Messages(dahsboard, "Accion cancelada").messageCancelaciones();
					return;
					
				}
			
			}else {
				new Messages(dahsboard, "Complete todos los campos correctamente").messageError();
				return;
			}
			
		}
	}
}
