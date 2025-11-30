package view.modules.billing.forms;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import model.Cliente;
import model.Producto;
import session.SessionContext;
import view.AplicationContext;
import view.components.AssetManager;
import view.components.Filtros;
import view.components.Fonts;
import view.components.Messages;
import view.components.RNCUtils;
import view.dashboard.Dahsboard;

public class CorporateClient extends JPanel implements ActionListener {

	private static final long serialVersionUID = -133827025529974867L;

	private List<Producto> productosFactura = new ArrayList<>();

	private Producto p;
	private final AplicationContext context;
	private final Dahsboard dahsboard;
	private JComboBox<Cliente> selectCliente;
	
	private String idCliente;
		
	private LocalDateTime ahora = LocalDateTime.now();
	private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	private String resultado = ahora.format(formato);

	private double total;
	private double subTotal;

	private JComboBox<Producto> selectProducto;
	
	private JComboBox<String> metoodPago;
	private JPanel panelTitulo,panelInfoCliente, panelAgregarCliente,  panelContenido, buttonPane, panelInformacionSuperMarket, panelAgregarProducto;

	private JScrollPane scrollPaneDescripcion, scrollPanelValor, scrollPaneItbis;

	private JButton btnAgregarProducto, btnAgregarCliente,  btnCancelar, btnGuardar;

	private JList<String> listDescripcionProductos;
	private JList<String> listValorProducto;
	private JList<String> listItbis;
	private JSpinner cantidad;

	private JLabel lbTitulo, lbSubTitulo, lbClienteTitulo,  lbClienteNombre, 
	lbMetodoPago, lbSubtTotal, lbTotal, lbCajeroUsuario, lbTotalArticulos, lbFecha, lbDescripcion,
			itbisProducto, lbLogo, lbValor, lblNewLabel, lbRnc, lbClienteTelefono,  lbNc, lbDireccion, lbTc,
			lbTipoConsumo, AgregarProducto, lbTienda, lblCantidad;

	public CorporateClient(AplicationContext context, Dahsboard dahsboard) {

		this.context = context;
		this.dahsboard = dahsboard;
		setBounds(100, 100, 1048, 818);
		setLayout(null);
		setBackground(Color.WHITE);
		crearPanelTitulo();
		crearPanelInformacionSuperMarket();
		crearPanelAgregarProducto();
		crearPanelContenido();
		crearButtonPanel();
		crearPanelAgregarCliente();
		crearLabels();
		
		
	}
		
	private void crearPanelAgregarCliente() {

		
		crearPanelInfoCliente();
		panelAgregarCliente = new JPanel();
		panelAgregarCliente.setBackground(Color.WHITE);
		panelAgregarCliente.setBounds(740, 310, 250, 115);
		panelAgregarCliente.setLayout(null);

		JLabel lbCliente = new JLabel("Agregar Cliente");
		lbCliente.setFont(Fonts.custom);
		lbCliente.setBounds(10, 10, 200, 21);
		panelAgregarCliente.add(lbCliente);

		selectCliente = new JComboBox<>();
		llenarClientes();
		selectCliente.setSelectedItem(null);
		selectCliente.addActionListener(this);
		selectCliente.setBounds(10, 44, 200, 21);
		panelAgregarCliente.add(selectCliente);

		btnAgregarCliente = new JButton("Agregar");
		btnAgregarCliente.setFont(Fonts.custom);
		btnAgregarCliente.addActionListener(this);
		btnAgregarCliente.setBounds(10, 77, 105, 28);
		panelAgregarCliente.add(btnAgregarCliente);

		panelAgregarCliente.add(panelInfoCliente);
		add(panelAgregarCliente);

		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setBounds(515, 310, 10, 115);
		add(separator);
	}


	private void llenarClientes() {
		
		for(Cliente c : context.getClienteController().listarClientes()) {
			
			if(c.getActivo() == 1) {
				
				selectCliente.addItem(c);
			}
			
		}
		
		
	}
	
	private void crearPanelInfoCliente() {

		panelInfoCliente = new JPanel();
		panelInfoCliente.setBackground(Color.WHITE);
		panelInfoCliente.setLayout(null);
		panelInfoCliente.setBounds(690, 0, 240, 150);

		lbClienteTitulo = new JLabel("CLIENTE");
		lbClienteTitulo.setFont(Fonts.bold);
		lbClienteTitulo.setBounds(10, 0, 200, 20);
		panelInfoCliente.add(lbClienteTitulo);

		lbClienteNombre = new JLabel("");
		lbClienteNombre.setFont(Fonts.custom);
		lbClienteNombre.setBounds(10, 30, 220, 20);
		panelInfoCliente.add(lbClienteNombre);

		lbClienteTelefono = new JLabel(" ");
		lbClienteTelefono.setFont(Fonts.custom);
		lbClienteTelefono.setBounds(10, 60, 220, 20);
		panelInfoCliente.add(lbClienteTelefono);

		
		
	}

	private void crearButtonPanel() {

		buttonPane = new JPanel();
		buttonPane.setBackground(Color.WHITE);
		buttonPane.setBounds(320, 750, 1040, 60);

		btnGuardar = new JButton("Guardar");
		buttonPane.add(btnGuardar);

		btnCancelar = new JButton("Cancelar");
		buttonPane.add(btnCancelar);

		btnGuardar.addActionListener(this);
		btnCancelar.addActionListener(this);

		add(buttonPane);
	}

	private void crearPanelTitulo() {

		panelTitulo = new JPanel();
		panelTitulo.setBackground(Color.WHITE);
		panelTitulo.setBounds(0, 0, 1040, 114);
		panelTitulo.setLayout(null);
		add(panelTitulo);
	}

	private void crearPanelValorProducto() {

		scrollPanelValor = new JScrollPane();
		scrollPanelValor.setBounds(800, 31, 180, 162);
		listValorProducto = new JList<>(new DefaultListModel<>());
		scrollPanelValor.setViewportView(listValorProducto);
	}

	private void crearPanelItbis() {

		scrollPaneItbis = new JScrollPane();
		scrollPaneItbis.setBounds(500, 31, 180, 162);

		listItbis = new JList<>(new DefaultListModel<>());
		scrollPaneItbis.setViewportView(listItbis);
	}

	private void crearPanelContenido() {

		panelContenido = new JPanel();
		panelContenido.setBackground(Color.WHITE);
		panelContenido.setBounds(0, 460, 1040, 271);
		panelContenido.setLayout(null);

		crearPanelDescripcion();
		crearPanelValorProducto();
		crearPanelItbis();		
		panelContenido.add(scrollPaneDescripcion);
		panelContenido.add(scrollPanelValor);
		panelContenido.add(scrollPaneItbis);
		add(panelContenido);
	}

	private void crearPanelDescripcion() {

		scrollPaneDescripcion = new JScrollPane();
		scrollPaneDescripcion.setBounds(20, 31, 288, 162);

		listDescripcionProductos = new JList<>(new DefaultListModel<>());

		scrollPaneDescripcion.setViewportView(listDescripcionProductos);
	}

	private void crearPanelAgregarProducto() {

		panelAgregarProducto = new JPanel();
		panelAgregarProducto.setBackground(Color.WHITE);
		panelAgregarProducto.setBounds(10, 310, 659, 115);
		panelAgregarProducto.setLayout(null);

		selectProducto = new JComboBox<>();
		llenarProductos();
		selectProducto.setSelectedItem(null);
		selectProducto.setBounds(10, 44, 261, 21);
		panelAgregarProducto.add(selectProducto);

		btnAgregarProducto = new JButton("Agregar");
		btnAgregarProducto.setFont(Fonts.custom);
		btnAgregarProducto.addActionListener(this);
		btnAgregarProducto.setBounds(10, 77, 105, 28);
		panelAgregarProducto.add(btnAgregarProducto);

		cantidad = new JSpinner();
		Filtros.aplicarFiltroNumericoSpinner(cantidad);
		cantidad.setModel(new SpinnerNumberModel(1, 1, null, 1));
		cantidad.setBounds(293, 44, 200, 20);
		
		panelAgregarProducto.add(cantidad);
		
		metoodPago = new JComboBox<>();
		metoodPago.addItem("EFECTIVO");
		metoodPago.addItem("TARJETA");
		metoodPago.setBounds(540, 44, 100, 20);
		
		panelAgregarProducto.add(metoodPago);
		

		add(panelAgregarProducto);
	}

	private void llenarProductos() {

		for (Producto p : context.getProductoController().listarProductos()) {

			if (p.getActivo() == 1 && p.getCantida() != 0) {
				selectProducto.addItem(p);
			}
		}
	}

	private void crearPanelInformacionSuperMarket() {

		panelInformacionSuperMarket = new JPanel();
		panelInformacionSuperMarket.setBackground(Color.WHITE);
		panelInformacionSuperMarket.setBounds(0, 114, 784, 186);
		panelInformacionSuperMarket.setLayout(null);
		add(panelInformacionSuperMarket);
	}

	private void crearLabels() {

		lbTitulo = new JLabel("SuperMarket");
		lbTitulo.setFont(Fonts.bold);
		lbTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lbTitulo.setBounds(187, 20, 508, 38);
		panelTitulo.add(lbTitulo);

		lbSubTitulo = new JLabel("¡Expertos en vender barato!");
		lbSubTitulo.setFont(Fonts.custom);
		lbSubTitulo.setBounds(187, 60, 345, 24);
		panelTitulo.add(lbSubTitulo);
		lbSubTitulo.setHorizontalAlignment(SwingConstants.LEFT);

		lbLogo = new JLabel("");
		lbLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lbLogo.setIcon(AssetManager.icon("logo.png", 95, 95));
		lbLogo.setBounds(10, 13, 185, 91);
		panelTitulo.add(lbLogo);

		lbDireccion = new JLabel("Ave. San Vicente de paul #114");
		lbDireccion.setFont(Fonts.custom);
		lbDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lbDireccion.setBounds(650, 80, 402, 24);
		panelTitulo.add(lbDireccion);

		lbTienda = new JLabel("TIENDA SAN VICENTE");
		lbTienda.setFont(Fonts.custom);
		lbTienda.setHorizontalAlignment(SwingConstants.RIGHT);
		lbTienda.setBounds(630, 55, 402, 24);
		panelTitulo.add(lbTienda);

		lblNewLabel = new JLabel("Supermarket S.A");
		lblNewLabel.setFont(Fonts.custom);
		lblNewLabel.setBounds(10, 10, 154, 23);
		panelInformacionSuperMarket.add(lblNewLabel);

		lbRnc = new JLabel(RNCUtils.generarRNCFical());
		lbRnc.setFont(Fonts.custom);
		lbRnc.setBounds(10, 47, 125, 23);
		panelInformacionSuperMarket.add(lbRnc);

		lbNc = new JLabel("");
		lbNc.setBounds(10, 80, 319, 23);
		panelInformacionSuperMarket.add(lbNc);

		lbTc = new JLabel("");
		lbTc.setBounds(10, 112, 319, 23);
		panelInformacionSuperMarket.add(lbTc);

		lbTipoConsumo = new JLabel("FACTURA DE CONSUMO ELECTRÓNICA ");
		lbTipoConsumo.setFont(Fonts.bold);
		lbTipoConsumo.setHorizontalAlignment(SwingConstants.LEFT);
		lbTipoConsumo.setBounds(10, 145, 692, 23);
		panelInformacionSuperMarket.add(lbTipoConsumo);

		AgregarProducto = new JLabel("Agregar Producto");
		AgregarProducto.setFont(Fonts.custom);
		AgregarProducto.setBounds(10, 10, 200, 21);
		panelAgregarProducto.add(AgregarProducto);

		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(Fonts.custom);
		lblCantidad.setBounds(293, 10, 158, 21);
		panelAgregarProducto.add(lblCantidad);
		
		lbMetodoPago = new JLabel("Metodo de pago");
		lbMetodoPago.setFont(Fonts.custom);
		lbMetodoPago.setBounds(540, 10, 158, 21);
		panelAgregarProducto.add(lbMetodoPago);
		

		lbDescripcion = new JLabel("DESCRIPCIÓN ");
		lbDescripcion.setFont(Fonts.custom);
		lbDescripcion.setBounds(20, 0, 180, 21);
		panelContenido.add(lbDescripcion);

		itbisProducto = new JLabel("ITBIS");
		itbisProducto.setFont(Fonts.custom);
		itbisProducto.setHorizontalAlignment(SwingConstants.LEFT);
		itbisProducto.setBounds(500, 4, 180, 21);
		panelContenido.add(itbisProducto);

		lbValor = new JLabel("VALOR");
		lbValor.setFont(Fonts.custom);
		lbValor.setHorizontalAlignment(SwingConstants.LEFT);
		lbValor.setBounds(800, 0, 180, 21);
		panelContenido.add(lbValor);

		lbCajeroUsuario = new JLabel("");
		if (SessionContext.get() != null) {
			lbCajeroUsuario.setText("LE ATENDIO  EL CAJERO "
					+ SessionContext.get().getIdUsuarioLogueado() + " : "
					+ SessionContext.get().getNombreUsuarioLogueado());
		}
		lbCajeroUsuario.setBounds(10, 225, 450, 15);
		panelContenido.add(lbCajeroUsuario);

		lbTotalArticulos = new JLabel("# ARTICULOS = 0 ");
		lbTotalArticulos.setBounds(10, 250, 191, 21);
		panelContenido.add(lbTotalArticulos);

		lbSubtTotal = new JLabel("SUBTOTAL = 0.00");
		lbSubtTotal.setFont(Fonts.custom);
		lbSubtTotal.setBounds(220, 250, 200, 21);
		panelContenido.add(lbSubtTotal);

		lbTotal = new JLabel("TOTAL = 0.00");
		lbTotal.setFont(Fonts.bold);
		lbTotal.setBounds(440, 250, 200, 21);
		panelContenido.add(lbTotal);

		lbFecha = new JLabel(resultado);
		lbFecha.setBounds(10, 203, 191, 15);
		panelContenido.add(lbFecha);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnAgregarCliente) {
			
			if(selectCliente.getSelectedItem() != null) {
				Cliente c =(Cliente) selectCliente.getSelectedItem();
				
				for(Cliente l : context.getClienteController().listarClientes()) {
					
					if(l.getNombre().equals(c.getNombre())) {
						idCliente = l.getIdentificacion();
						break;
					}
				}
				
				
				lbTc.setText(c.getTelefono());
				lbTc.setFont(Fonts.custom);
				lbNc.setText(c.getNombre());
				lbNc.setFont(Fonts.custom);
				lbNc.revalidate();
				lbNc.repaint();
				
				lbTc.revalidate();
				lbTc.repaint();
				
			}
		}
		

		if (e.getSource() == btnGuardar) {
			if (productosFactura.isEmpty()) {
				new Messages(dahsboard, "La factura está en blanco, agregue productos").messageError();
				return;
			}
			if(metoodPago.getSelectedItem() != null) {
				String metodoPago = (String) metoodPago.getSelectedItem();
				int cajeroId = SessionContext.get().getIdUsuarioLogueado();
				int idC = 0;
				
				if(idCliente != null) {
					
					idC = Integer.parseInt(idCliente);
				}
				
				String resultado = context.getFacturaController().agregarFactura(metodoPago, idC, cajeroId);
				
				if(resultado.startsWith("Error")) {
					new Messages(dahsboard, resultado).messageError();
					return;
					
				}

				int idFactura = context.getFacturaController().listarFacturas().size();
				
				if(idFactura == 0) {
					new Messages(dahsboard, "Error al crear la factura principal").messageError();
					return;
				}
				
				boolean todosAgregados = true;
				
				int cantidad= 0;
				for (Producto producto : productosFactura) {
					
					String  idProducto = String.valueOf(producto.getCodigo());
					cantidad = producto.getCantida();
					
					String resultadoProducto = context.getFacturaController().agregarProducto(idFactura, idProducto, cantidad);
					if (resultadoProducto.startsWith("Error")) {
						todosAgregados = false;
						new Messages(dahsboard,"Error al agregar producto " + producto.getNombre() + ": " + resultadoProducto).messageError();
						
					}
					
				}

				Producto pD = context.getProductoController().buscar(p.getCodigo());
				pD.setCantidad(pD.getCantida() - cantidad);
				
				if(!context.getProductoController().editarStock(pD)) {
					new Messages(dahsboard, "Error actualizando el stock del producto de la factura");
				}
			
				
				if(todosAgregados) {
					
					new Messages(dahsboard, "Factura N°  " + idFactura + " guardada correctamentew").messageAlert();
				}else {
					new Messages(dahsboard, "Factura creada, pero hubo errores al agregar algunos productos. Revisar consola.").messageError();
				}	
			}else {
				
				new Messages(dahsboard, "Seleccione un metodo de pago valido").messageError();
				return;
			}

			return;
		}

		if (e.getSource() == btnCancelar) {
			int idx = listDescripcionProductos.getSelectedIndex();
			if (idx >= 0 && idx < productosFactura.size()) {
				productosFactura.remove(idx);
				DefaultListModel<String> modeloDescripcion = (DefaultListModel<String>) listDescripcionProductos.getModel();
				DefaultListModel<String> modeloValor = (DefaultListModel<String>) listValorProducto.getModel();
				DefaultListModel<String> modeloItbis = (DefaultListModel<String>) listItbis.getModel();
				modeloDescripcion.remove(idx);
				modeloValor.remove(idx);
				modeloItbis.remove(idx);
				recalcularTotales();
				lbTotalArticulos.setText("# ARTICULOS =  " + modeloDescripcion.getSize());
			}
			return;
		}

		if (e.getSource() == btnAgregarProducto) {

			if (selectProducto.getSelectedItem() == null) {
				new Messages(dahsboard, "Seleccione un producto válido").messageError();
				return;
			}

			p = (Producto) selectProducto.getSelectedItem();
			int cantidadAgregar = (Integer) cantidad.getValue();

			Producto existente = null;
			for (Producto prod : productosFactura) {
				if (prod.equals(p)) {
					existente = prod;
					break;
				}
			}

			int stockDisponible = p.getCantida();
			int cantidadActualFactura = (existente == null ? 0 : existente.getCantida());

			if (cantidadAgregar + cantidadActualFactura > stockDisponible) {
				new Messages(dahsboard, "La cantidad total supera el stock disponible").messageError();
				return;
			}

			DefaultListModel<String> modeloDescripcion = (DefaultListModel<String>) listDescripcionProductos.getModel();
			DefaultListModel<String> modeloValor = (DefaultListModel<String>) listValorProducto.getModel();
			DefaultListModel<String> modeloItbis = (DefaultListModel<String>) listItbis.getModel();

			boolean encontrado = false;

			for (int i = 0; i < productosFactura.size(); i++) {
				Producto prod = productosFactura.get(i);

				if (prod.equals(p)) {

					int nuevaCantidad = prod.getCantida() + cantidadAgregar;
					prod.setCantidad(nuevaCantidad);

					modeloDescripcion.set(i, prod.getNombre() + " - Cantidad: " + nuevaCantidad);
					modeloValor.set(i, String.format("%.2f", prod.getPrecio() * nuevaCantidad));
					modeloItbis.set(i, calcularItbis(prod, nuevaCantidad));

					recalcularTotales();
					new Messages(dahsboard, "Cantidad actualizada correctamente").messageAvisos();
					encontrado = true;
					break;
				}
			}

			if (!encontrado) {

				Producto pFactura = new Producto(p.getCodigo(), p.getNombre(), p.getPrecio(), p.getCategoria(),
						cantidadAgregar, p.getUnidad(), p.getActivo());

				productosFactura.add(pFactura);

				DefaultListModel<String> modeloDesc = (DefaultListModel<String>) listDescripcionProductos.getModel();
				DefaultListModel<String> modeloVal = (DefaultListModel<String>) listValorProducto.getModel();
				DefaultListModel<String> modeloItb = (DefaultListModel<String>) listItbis.getModel();

				modeloDesc.addElement(pFactura.getNombre() + " - Cantidad: " + cantidadAgregar);
				modeloVal.addElement(String.format("%.2f", pFactura.getPrecio() * cantidadAgregar));
				modeloItb.addElement(calcularItbis(pFactura, cantidadAgregar));

				recalcularTotales();
			}

			selectProducto.setSelectedItem(null);
			cantidad.setValue(1);
			DefaultListModel<String> modeloDescripcionActual = (DefaultListModel<String>) listDescripcionProductos.getModel();
			lbTotalArticulos.setText("# ARTICULOS =  " + modeloDescripcionActual.getSize());
		}
	}

	private void recalcularTotales() {

		subTotal = 0;
		total = 0;

		for (Producto prod : productosFactura) {
			double subtotalItem = prod.getPrecio() * prod.getCantida();
			double itbisItem = calcularItbisDouble(prod, prod.getCantida());
			subTotal += subtotalItem;
			total += subtotalItem + itbisItem;
		}

		lbSubtTotal.setText("SUBTOTAL = " + String.format("%.2f", subTotal));
		lbTotal.setText("TOTAL = " + String.format("%.2f", total));
	}

	private double calcularItbisDouble(Producto p, int cantidad) {

		switch (p.getCategoria()) {
			case COMESTIBLES:
			case VEGETALES_FRUTAS:
			case PANES:
				return 0.0;
			default:
				return p.getPrecio() * cantidad * 0.18;
		}
	}

	private String calcularItbis(Producto p, int cantidad) {

		switch (p.getCategoria()) {
			case COMESTIBLES:
			case VEGETALES_FRUTAS:
			case PANES:
				return "0.00";
			default:
				double itbis = p.getPrecio() * cantidad * 0.18;
				return String.format("%.2f", itbis);
		}
	}
}
