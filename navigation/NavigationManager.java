package navigation;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Modulo;
import session.SessionContext;
import session.UsersContext;
import view.components.Messages;
import view.forms.dashboard.Dahsboard;

//clase que recibe los permisos y 
//habilita los modulos por los cuales el usuario puede manejar

public class NavigationManager {
	
	private JPanel panelContenido;
	private final JFrame dashboard;
	private Modulo moduloActual;
	//private final Rol rolActual;
	
	public NavigationManager(JPanel panelContenido, JFrame dashboard) {
		this.panelContenido = panelContenido;
		this.dashboard = dashboard;
		//rolActual = SessionContext.get().getRolUsuarioLogueado();
	}

	
	//segun el modulo al cual quiera ir se redirige
	
	public void goTo(Modulo modulo) {
		
		if(!SessionContext.get().tienePermisos(modulo.getPermisoNecesarios())) {
			new Messages(dashboard,"No tienes permisos para acceder a este modulo").messageError();
			return;
		}	

		//remover elementos mostrados, agregar el componente y refrescar el dashboard
		moduloActual = modulo;
		panelContenido.removeAll();
		panelContenido.add(modulo.construirPanel());
		panelContenido.revalidate();
		panelContenido.repaint();
			
	}
	
	public Modulo getModuloActual() {
		return moduloActual;
	}
	
	public void navigationToDashboard(UsersContext user) {
		SessionContext.set(user);
		Dahsboard dash  = new Dahsboard(this);
		dash.setVisible(true);
	}
	
	public void setPanelContenido(JPanel panelContenido) {
		this.panelContenido = panelContenido;
	}
	
	public JFrame getDashboard() {
		return dashboard;
	}
	
	
}
