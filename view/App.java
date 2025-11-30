package view;
import com.formdev.flatlaf.FlatLightLaf;

import view.Auth.Login;
public class App {
	public void start() {
		var context = new AplicationContext();
		var login = new Login(context);
		login.setVisible(true);
	}
	public static void main(String[] args) {
		FlatLightLaf.setup();
		new App().start();
	}	
}


/* nota de cambios realizados:
 * 
 *
 * los cambios que envie son de los modulos que estan funcionando perfectamente -> usuarios
 * > proveedores, ofertas y productos

favor provar dicho metodos y notificar de caulquier error

los otros modulos de factura, ventas, servicio al cliente los deje en  mi rama local hasta que termine de diseñarlos

hay unas cuantas cosas a nivel de diseño que se pueden arreglar pero me enfoque mas en la funcionalidad
ya a medida que vayamos terminando acomodo uno que otro colores y eso;

este es uno de los usuarios admin por si quieren probar el sistema no tenga que estar entrando a la BD:
cmendez@empresa.com
1234

 :D
*/