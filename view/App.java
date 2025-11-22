package view;
import control.AuthenticatorController;
import navigation.NavigationManager;
import view.forms.Auth.Login;
import view.forms.dashboard.Dahsboard;


// clase que gestiona las dependecias e inyecciones para trabajar bajo el mismo objecto
// y elemento que haya accedido al sistema

public class App {

	//llamar aqui capas que actuaran como conexion entre la BD y el programa
	
			//llamar aqui los elementos que actuaran como controlles Services (los que manejan la logica de validaciones
			// llaman a las capas y efectuan las operadores crud luego de validar que todo este en orden)
		
			//llamar aqui las vistas  y pasar los elementos a los cuales debe redirigir
			

	private AuthenticatorController authenticator = new AuthenticatorController();
	private Dahsboard dashboard = new  Dahsboard(null);
	private NavigationManager navigation = new NavigationManager(dashboard);

	
	//tomar en cuenta que si se trabajan con objectos diferenes las referencias e instancias tambien lo seran
	// lo que provocara entonces  fallos en la llamada de ciertos metodos, si ya manejan esa parte de otra manera
	//entonces hagan los cambios pertinentes de esta clase y ajustar a lo que tienen
	
	public void start() {

		dashboard.setChangeState(navigation);
		Login login = new Login(authenticator, navigation);
		login.setVisible(true);
	}
	public static void main(String[] args) {
	
		new App().start();
	}


}
