package view;

import control.ClienteController;
import control.CuentaCobrarController;
import control.CuentaPagarController;
import control.FacturaController;
import control.OfertasController;
import control.ProductoController;
import control.ProveedoresController;
import control.UserController;
import dao.ClienteDAO;
import dao.CuentaCobrarDAO;
import dao.CuentaPagarDAO;
import dao.FacturaDAO;
import dao.OfertaDAO;
import dao.ProductoDAO;
import dao.ProveedorDAO;
import dao.UserDAO;
import model.CuentaCobrar;
import model.CuentaPagar;
import navigation.NavigationManager;

public class AplicationContext {
	
	
	//esta clase es la puerta de enlace de todos las clases controladoras y dao
	//la cree para no tener instancias repetidas de multiples objectos 
	// no hice esto en app pq seria meter logica de backend en el front-end
	
	private final UserDAO userDao = new UserDAO();
	private final ProveedorDAO proveedorDAO  = new ProveedorDAO();
	private final UserController userController = new UserController(userDao);
	private final FacturaDAO facturaDAO = new FacturaDAO();
	private final ProductoDAO productoDao = new ProductoDAO();
	private final OfertaDAO ofertaDao  = new OfertaDAO();
	private final CuentaCobrarDAO cuentaCobrarDAO  = new CuentaCobrarDAO();
	private final CuentaPagarDAO cuentaPagarDAO  = new CuentaPagarDAO();
	private final ClienteDAO clienteDao = new ClienteDAO();
	
	private final FacturaController facturaController = new FacturaController(facturaDAO, productoDao );
	private final ProductoController productoController = new ProductoController(productoDao);
	private final ProveedoresController proveedoresController = new ProveedoresController(proveedorDAO);
	private final OfertasController ofertasController = new OfertasController(ofertaDao);
	private final NavigationManager navigation = new NavigationManager();
	private final CuentaCobrarController cobrarController = new CuentaCobrarController();
	private final CuentaPagarController pagarController = new CuentaPagarController();
	private final ClienteController clienteController = new ClienteController(clienteDao);

	private final ReporteCuentasCobrarController reporteCuentaCobrarController = new ReporteCuentasCobrarController();
	private final ReporteCuentasPagarController reporteCuentaPagarController = new ReporteCuentasPagarController();
	private final ReporteInventarioController reporteInventarioController = new ReporteInventarioController();
	private final ReporteVentasDiariasController reporteVentasDiariasController = new ReporteVentasDiariasController();
	
	
	public UserController getUserController() {return userController;}
	public ClienteController getClienteController() {return clienteController;}
	public FacturaController  getFacturaController() {return facturaController;}
	public ProductoController getProductoController() {return productoController;}
	public NavigationManager getNavigation() {return navigation;}
	public ProveedoresController getProveedorController() {return proveedoresController;}
	public OfertasController getOfertasController() {return ofertasController;}
	public CuentaCobrarController getCuentaCobrarController() {return cobrarController;}
	public CuentaPagarController getCuentaPagarController () {return pagarController;}

	public ReporteCuentasCobrarController getReportCXCController() {return reporteCuentaCobrarController;}
	public ReporteCuentasPagarController getReportCXPController() {return reporteCuentaPagarController;}
	public ReporteInventarioController getReportInvController() {return reporteInventarioController;}
	public ReporteVentasDiariasController getCReportVenDiaController () {return reporteVentasDiariasController;}
	
}
