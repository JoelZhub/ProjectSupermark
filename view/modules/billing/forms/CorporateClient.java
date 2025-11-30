package view.modules.billing.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

import model.Producto;
import session.SessionContext;
import view.AplicationContext;
import view.components.AssetManager;
import view.components.Fonts;
import view.dashboard.Dahsboard;

public class CorporateClient extends JPanel {

	private static final long serialVersionUID = -133827025529974867L;

	private final AplicationContext context;
	private final Dahsboard dahsboard;

	private LocalDateTime ahora = LocalDateTime.now();
	private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	private String resultado = ahora.format(formato);

	private JComboBox<Producto> selectProducto;
	private JComboBox<String> selectCliente;

	private JPanel panelTitulo, panelContenido, buttonPane, panelInformacionSuperMarket, panelAgregarProducto;
	private JPanel panelAgregarCliente, panelInfoCliente;

	private JScrollPane scrollPaneDescripcion, scrollPanelValor, scrollPaneItbis;

	private JButton btnAgregarProducto, btnAgregarCliente, btnCancelar, btnGuardar;

	private JList<Producto> listDescripcionProductos;
	private JList listValorProducto;
	private JList listItbis;
	private JSpinner cantidad;

	private JLabel lbTitulo, lbSubTitulo, lbCajeroUsuario, lbTotalArticulos, lbFecha, lbDescripcion, itbisProducto;
	private JLabel lbLogo, lbValor, lblNewLabel, lbRnc, lbFechaHoraActual, lbDireccion;
	private JLabel lbComprobanteFiscal, lbTipoConsumo, AgregarProducto, lbTienda, lblCantidad;

	// Labels cliente
	private JLabel lbClienteTitulo, lbClienteNombre, lbClienteTelefono, lbClienteIdent;

	public CorporateClient(AplicationContext context, Dahsboard dahsboard) {

		this.context = context;
		this.dahsboard = dahsboard;

//		setBounds(100, 100, 1050, 818);   
		setLayout(null);
		setBackground(Color.WHITE);

		crearPanelTitulo();
		crearPanelInformacionSuperMarket();
		crearPanelAgregarProducto();
		crearPanelAgregarCliente();
		
		crearPanelContenido();
		crearButtonPanel();
		crearLabels();
	}

	private void crearButtonPanel() {

		buttonPane = new JPanel();
		buttonPane.setBackground(Color.WHITE);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPane.setBounds(0, 750, 1040, 60);

		btnGuardar = new JButton("Guardar");
		buttonPane.add(btnGuardar);

		btnCancelar = new JButton("Cancelar");
		buttonPane.add(btnCancelar);

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
		scrollPanelValor.setBounds(539, 31, 180, 162);

		listValorProducto = new JList<>();
		scrollPanelValor.setViewportView(listValorProducto);
	}

	private void crearPanelItbis() {

		scrollPaneItbis = new JScrollPane();
		scrollPaneItbis.setBounds(316, 31, 180, 162);

		listItbis = new JList<>();
		scrollPaneItbis.setViewportView(listItbis);
	}

	private void crearPanelContenido() {

		panelContenido = new JPanel();
		panelContenido.setBackground(Color.WHITE);
		panelContenido.setBounds(0, 479, 1090, 271);
		panelContenido.setLayout(null);

		crearPanelDescripcion();
		crearPanelValorProducto();
		crearPanelItbis();
		crearPanelInfoCliente();
		panelContenido.add(scrollPaneDescripcion);
		panelContenido.add(scrollPanelValor);
		panelContenido.add(scrollPaneItbis);
		panelContenido.add(panelInfoCliente);
		add(panelContenido);
	}

	private void crearPanelDescripcion() {

		scrollPaneDescripcion = new JScrollPane();
		scrollPaneDescripcion.setBounds(10, 31, 288, 162);

		listDescripcionProductos = new JList<>();
		scrollPaneDescripcion.setViewportView(listDescripcionProductos);
	}

	private void crearPanelAgregarProducto() {

		panelAgregarProducto = new JPanel();
		panelAgregarProducto.setBackground(Color.WHITE);
		panelAgregarProducto.setBounds(10, 310, 509, 115);
		panelAgregarProducto.setLayout(null);

		selectProducto = new JComboBox<>();
		llenarProductos();
		selectProducto.setSelectedItem(null);
		selectProducto.setBounds(10, 44, 261, 21);
		panelAgregarProducto.add(selectProducto);

		btnAgregarProducto = new JButton("Agregar");
		btnAgregarProducto.setFont(Fonts.custom);
		btnAgregarProducto.setBounds(10, 77, 105, 28);
		panelAgregarProducto.add(btnAgregarProducto);

		cantidad = new JSpinner();
		cantidad.setModel(new SpinnerNumberModel(1, 0, null, 1));
		cantidad.setBounds(293, 44, 200, 20);
		panelAgregarProducto.add(cantidad);

		add(panelAgregarProducto);
	}

	private void crearPanelAgregarCliente() {

		panelAgregarCliente = new JPanel();
		panelAgregarCliente.setBackground(Color.WHITE);
		panelAgregarCliente.setBounds(530, 310, 250, 115);
		panelAgregarCliente.setLayout(null);

		JLabel lbCliente = new JLabel("Agregar Cliente");
		lbCliente.setFont(Fonts.custom);
		lbCliente.setBounds(10, 10, 200, 21);
		panelAgregarCliente.add(lbCliente);

		selectCliente = new JComboBox<>();
		selectCliente.setSelectedItem(null);
		selectCliente.setBounds(10, 44, 200, 21);
		panelAgregarCliente.add(selectCliente);

		btnAgregarCliente = new JButton("Agregar");
		btnAgregarCliente.setFont(Fonts.custom);
		btnAgregarCliente.setBounds(10, 77, 105, 28);
		panelAgregarCliente.add(btnAgregarCliente);

		add(panelAgregarCliente);

		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setBounds(515, 310, 10, 115);
		add(separator);
	}

	private void llenarClientes() {
		
		//cuando se reciban los cambios del controller y dao de clientes efectuar los cambios
	}
	
	private void crearPanelInfoCliente() {

		panelInfoCliente = new JPanel();
		panelInfoCliente.setBackground(Color.WHITE);
		panelInfoCliente.setLayout(null);
		panelInfoCliente.setBounds(750, 0, 240, 150);

		lbClienteTitulo = new JLabel("CLIENTE");
		lbClienteTitulo.setFont(Fonts.bold);
		lbClienteTitulo.setBounds(10, 0, 200, 20);
		panelInfoCliente.add(lbClienteTitulo);

		lbClienteNombre = new JLabel("Nombre: Juan Pérez");
		lbClienteNombre.setFont(Fonts.custom);
		lbClienteNombre.setBounds(10, 30, 220, 20);
		panelInfoCliente.add(lbClienteNombre);

		lbClienteTelefono = new JLabel("Tel: 809-555-0000");
		lbClienteTelefono.setFont(Fonts.custom);
		lbClienteTelefono.setBounds(10, 60, 220, 20);
		panelInfoCliente.add(lbClienteTelefono);

		lbClienteIdent = new JLabel("ID: 402-1234567-8");
		lbClienteIdent.setFont(Fonts.custom);
		lbClienteIdent.setBounds(10, 90, 220, 20);
		panelInfoCliente.add(lbClienteIdent);

		
	}

	private void llenarProductos() {
		for (Producto p : context.getProductoController().listarProductos()) {
			if (p.getActivo() == 1) {
				selectProducto.addItem(p);
			}
		}
	}

	private void crearPanelInformacionSuperMarket() {

		panelInformacionSuperMarket = new JPanel();
		panelInformacionSuperMarket.setBackground(Color.WHITE);
		panelInformacionSuperMarket.setBounds(0, 114, 1040, 186);
		panelInformacionSuperMarket.setLayout(null);
		add(panelInformacionSuperMarket);
	}

	private void crearLabels() {

		lbTitulo = new JLabel("SuperMarket");
		lbTitulo.setFont(Fonts.bold);
		lbTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lbTitulo.setBounds(187, 30, 508, 38);
		panelTitulo.add(lbTitulo);

		lbSubTitulo = new JLabel("¡Expertos en vender barato!");
		lbSubTitulo.setFont(Fonts.custom);
		lbSubTitulo.setBounds(187, 65, 345, 24);
		panelTitulo.add(lbSubTitulo);

		lbLogo = new JLabel("");
		lbLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lbLogo.setIcon(AssetManager.icon("logo.png", 95, 95));
		lbLogo.setBounds(10, 13, 185, 91);
		panelTitulo.add(lbLogo);

		lbDireccion = new JLabel("Ave. San Vicente de paul #114");
		lbDireccion.setFont(Fonts.custom);
		lbDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lbDireccion.setBounds(560, 80, 402, 24);
		panelTitulo.add(lbDireccion);

		lbTienda = new JLabel("TIENDA SAN VICENTE");
		lbTienda.setFont(Fonts.custom);
		lbTienda.setHorizontalAlignment(SwingConstants.RIGHT);
		lbTienda.setBounds(560, 55, 402, 24);
		panelTitulo.add(lbTienda);

		lblNewLabel = new JLabel("Supermarket S.A");
		lblNewLabel.setFont(Fonts.custom);
		lblNewLabel.setBounds(10, 10, 154, 23);
		panelInformacionSuperMarket.add(lblNewLabel);

		lbRnc = new JLabel("RNC : 366323846");
		lbRnc.setFont(Fonts.custom);
		lbRnc.setBounds(10, 47, 125, 23);
		panelInformacionSuperMarket.add(lbRnc);

		lbFechaHoraActual = new JLabel("");
		lbFechaHoraActual.setBounds(10, 80, 319, 23);
		panelInformacionSuperMarket.add(lbFechaHoraActual);

		lbComprobanteFiscal = new JLabel("");
		lbComprobanteFiscal.setBounds(10, 112, 319, 23);
		panelInformacionSuperMarket.add(lbComprobanteFiscal);

		lbTipoConsumo = new JLabel("FACTURA CORPORATIVA");
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

		lbDescripcion = new JLabel("DESCRIPCIÓN ");
		lbDescripcion.setFont(Fonts.custom);
		lbDescripcion.setBounds(10, 0, 180, 21);
		panelContenido.add(lbDescripcion);

		itbisProducto = new JLabel("ITBIS");
		itbisProducto.setFont(Fonts.custom);
		itbisProducto.setHorizontalAlignment(SwingConstants.LEFT);
		itbisProducto.setBounds(317, 4, 180, 21);
		panelContenido.add(itbisProducto);

		lbValor = new JLabel("VALOR");
		lbValor.setFont(Fonts.custom);
		lbValor.setHorizontalAlignment(SwingConstants.LEFT);
		lbValor.setBounds(539, 0, 180, 21);
		panelContenido.add(lbValor);

		lbCajeroUsuario = new JLabel("");
		if (SessionContext.get() != null) {
			lbCajeroUsuario.setText( "Le Antendio: " + SessionContext.get().getNombreUsuarioLogueado() + " Cajero" +  SessionContext.get().getIdUsuarioLogueado() );
		}
		lbCajeroUsuario.setBounds(10, 225, 191, 15);
		panelContenido.add(lbCajeroUsuario);

		lbTotalArticulos = new JLabel("0");
		lbTotalArticulos.setBounds(10, 250, 191, 21);
		panelContenido.add(lbTotalArticulos);

		lbFecha = new JLabel(resultado);
		lbFecha.setBounds(10, 203, 191, 15);
		panelContenido.add(lbFecha);
	}
}
