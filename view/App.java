package view;
import control.AuthenticatorController;
import navigation.NavigationManager;
import view.forms.Auth.Login;
import view.forms.dashboard.Dahsboard;


// clase que gestiona las dependecias e inyecciones para trabajar bajo el mismo objecto
// y elemento que haya accedido al sistema

public class App {
	
	private AuthenticatorController authenticator = new AuthenticatorController();
	private Dahsboard dashboard = new  Dahsboard(null);
	private NavigationManager navigation = new NavigationManager(null, dashboard);
	
	public void start() {
		dashboard.setChangeState(navigation);
		navigation.setPanelContenido(dashboard.getPanelContenido());
		Login login = new Login(authenticator, navigation);
		login.setVisible(true);
	}
	public static void main(String[] args) {
		new App().start();
	}

	public AuthenticatorController getAuthenticatorController() {
		return  authenticator;
	}

}
