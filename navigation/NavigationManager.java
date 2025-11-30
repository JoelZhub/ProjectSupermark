package navigation;
import model.Modulo;
import model.Rol;
import session.SessionContext;
import view.components.Messages;
import view.dashboard.Dahsboard;

public class NavigationManager {

	private Dahsboard dahsboard;
	private Modulo moduloActual;

	public void goTo(Modulo modulo) {
		if(!SessionContext.get().tienePermisos(modulo.getPermisoNecesarios())) {
			new Messages(dahsboard,"No tienes permisos para acceder a este modulo").messageError();
			return;
		}	
		moduloActual = modulo;
		modulo.setAplicationContext(dahsboard.getContext());
		modulo.setDahsboard(dahsboard);
		dahsboard.setPanelContent(modulo.construirPanel());	
	}
	
			
	public Modulo getModuloActual() {
		return moduloActual;
	}
	
	public void setCambioModuloActual(Rol rol) {
		if(SessionContext.get()  != null) {
			switch(SessionContext.get().getRolUsuarioLogueado()) {
			case ADMIN: 	
				moduloActual = Modulo.ADMIN;
				break;
			case VENDEDOR: 	
				moduloActual = Modulo.VENTAS;
				break;
			case CAJERO: 	
				moduloActual = Modulo.FACTURACION;
				break;
			case ENCARGADO_INVENTARIO: 	
				moduloActual = Modulo.PRODUCTOS;
				break;
			case SERVICIO_CLIENTE :
				moduloActual = Modulo.CLIENTE;
				break;
			case GERENTE_FINANZAS:
				moduloActual = Modulo.FINANZAS;
				break;
			default:
				moduloActual = null;
		
			}
		}	
	}
	
	public void setDahsboard(Dahsboard dahsboard) {
		this.dahsboard = dahsboard;
		
	}
		
}
