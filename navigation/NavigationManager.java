package navigation;

import javax.swing.JFrame;
import model.Modulo;
import session.SessionContext;
import utils.Messages;

//clase que recibe los permisos y 
//habilita los modulos por los cuales el usuario puede manejar

public class NavigationManager {
	
	private final JFrame dashboard;
		
	public NavigationManager(JFrame dashboard) {
		this.dashboard = dashboard;
	}

	//segun el modulo al cual quiera ir se redirige
	
	public void goTo(Modulo modulo) {
		
		if(!SessionContext.get().tienePermisos(modulo.getPermisoNecesarios())) {
			new Messages(dashboard,"No tienes permisos para acceder a este modulo").messageError();
			return;
		}	
		
		//remover elementos mostrados, agregar el componente y refrescar el dashboard
		dashboard.removeAll();
		dashboard.add(modulo.construirPanel());
		dashboard.revalidate();
		dashboard.repaint();
		
		
	}
		
	public JFrame getDashboard() {
		return dashboard;
	}
	
}
