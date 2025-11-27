package view;

import control.FacturaController;
import control.ProductoController;
import control.UserController;
import dao.FacturaDAO;
import dao.ProductoDAO;
import dao.UserDAO;
import navigation.NavigationManager;

public class AplicationContext {
	
	
	//esta clase es la puerta de enlace de todos las clases controladoras y dao
	//la cree para no tener instancias repetidas de multiples objectos 
	// no hice esto en app pq seria meter logica de backend en el front-end
	private final UserDAO userDao = new UserDAO();
	
	private final UserController userController = new UserController(userDao);

	private final FacturaDAO facturaDAO = new FacturaDAO();
	private final ProductoDAO productoDao = new ProductoDAO();
	
	private final FacturaController facturaController = new FacturaController(facturaDAO, productoDao );
	private final ProductoController productoController = new ProductoController(productoDao);
	
	
	private final  NavigationManager navigation = new NavigationManager();
	
	
	public UserController getUserController() {return userController;}
	public FacturaController  getFacturaController() {return facturaController;}
	public ProductoController getProductoController() {return productoController;}
	public NavigationManager getNavigation() {return navigation;}

}
