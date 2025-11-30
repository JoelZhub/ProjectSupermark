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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import model.Producto;
import session.SessionContext;
import view.AplicationContext;
import view.components.AssetManager;
import view.components.Fonts;
import view.dashboard.Dahsboard;

public class EndConsumer extends JPanel {

	//este panel es para las facturas de clienes rapidos no registrados
	//compras rapidas
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -133827025529974867L;
	/**
	 * 
	 */
	private  final AplicationContext context;
	@SuppressWarnings("unused")
	private final Dahsboard dahsboard;
	
	private LocalDateTime ahora = LocalDateTime.now();

	private  DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	private String resultado = ahora.format(formato);
	
	private JComboBox<Producto> selectProducto;
	
	private JPanel panelTitulo, panelContenido, buttonPane, panelInformacionSuperMarket, panelAgregarProducto;
	
	private JScrollPane scrollPaneDescripcion,  scrollPanelValor, scrollPaneItbis ;
	
	private JButton btnAgregarProducto,  btnCancelar,  btnGuardar;
	
	
	@SuppressWarnings("unused")
	private	JList<Producto> listDescripcionProductos;
	
	@SuppressWarnings("rawtypes")
	private	JList listValorProducto ;
	
	@SuppressWarnings("rawtypes")
	private	JList listItbis;
	private JSpinner cantidad ;
	

	private JLabel lbTitulo, lbSubTitulo, lbCajeroUsuario, lbTotalArticulos, lbFecha,   lbDescripcion ,  itbisProducto,  lbLogo,  lbValor, lblNewLabel, lbRnc, lbFechaHoraActual,
	lbDireccion, lbComprobanteFiscal,  lbTipoConsumo, AgregarProducto, lbTienda, lblCantidad;
	
	public EndConsumer(AplicationContext context, Dahsboard dahsboard) {
		
		this.context = context;
		this.dahsboard = dahsboard;
		setBounds(100, 100, 798, 818);
		setLayout(null);
		setBackground(Color.WHITE);
		crearPanelTitulo();
		crearPanelInformacionSuperMarket();
		crearPanelAgregarProducto();
		crearPanelContenido();
		crearButtonPanel();
		crearLabels();   	
		
	}
	
	
	private void crearButtonPanel() {
		
		buttonPane = new JPanel();
		buttonPane.setBackground(Color.WHITE);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnGuardar = new JButton("Guardar");	
		buttonPane.add(btnGuardar);
	
		btnCancelar = new JButton("Cancelar");
		buttonPane.add(btnCancelar);
		
		add(buttonPane, BorderLayout.SOUTH);
	}
	
	private void crearPanelTitulo() {
		
		panelTitulo = new JPanel();
		panelTitulo.setBackground(Color.WHITE);
		panelTitulo.setBounds(0, 0, 784, 114);
		panelTitulo.setLayout(null);
		add(panelTitulo);	
	}
	
	
	
	
	private void crearPanelValorProducto() {
		
		scrollPanelValor = new JScrollPane();
		scrollPanelValor.setBounds(539, 31, 180, 162);
		
		
		listValorProducto = new JList<>();
		scrollPanelValor.setViewportView(listValorProducto);
		
	}
	
	private void  crearPanelItbis() {
			
		scrollPaneItbis = new JScrollPane();
		scrollPaneItbis.setBounds(316, 31, 180, 162);
	
		listItbis = new JList<>();
		scrollPaneItbis.setViewportView(listItbis);
	}
	private void crearPanelContenido() {
		
		panelContenido = new JPanel();
		panelContenido.setBackground(Color.WHITE);
		panelContenido.setBounds(0, 479, 774, 271);
		panelContenido.setLayout(null);
		
		crearPanelDescripcion();
		crearPanelValorProducto();
		crearPanelItbis();
		
		add(panelContenido);
		panelContenido.add(scrollPaneDescripcion);
		panelContenido.add(scrollPanelValor);
		panelContenido.add(scrollPaneItbis);
		
		
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
		cantidad.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(0), null, Integer.valueOf(1)));
		cantidad.setBounds(293, 44, 200, 20);
		panelAgregarProducto.add(cantidad);
		
		add(panelAgregarProducto);
		
	}
	
	private void llenarProductos() {
		
		for(Producto p: context.getProductoController().listarProductos()) {
			
			if(p.getActivo() == 1) {
				
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
		lbLogo.setIcon(AssetManager.icon("logo.png", 95,95));
		lbLogo.setBounds(10, 13, 185, 91);
		panelTitulo.add(lbLogo);
		
		lbDireccion = new JLabel("Ave. San Vicente de paul #114");
		lbDireccion.setFont(Fonts.custom);
		lbDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lbDireccion.setBounds(372, 80, 402, 24);
		panelTitulo.add(lbDireccion);
		
		lbTienda = new JLabel("TIENDA SAN VICENTE");
		lbTienda.setFont(Fonts.custom);
		lbTienda.setHorizontalAlignment(SwingConstants.RIGHT);
		lbTienda.setBounds(372, 55, 402, 24);
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
		if(SessionContext.get() != null) {
			lbCajeroUsuario.setText(SessionContext.get().getNombreUsuarioLogueado());
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
