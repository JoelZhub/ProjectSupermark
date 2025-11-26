package view;
import view.Auth.Login;
public class App {
	public void start() {
		var context = new AplicationContext();
		var login = new Login(context);
		login.setVisible(true);
	}
	public static void main(String[] args) {
		new App().start();
	}	
}
