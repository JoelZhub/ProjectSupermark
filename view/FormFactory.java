package view;

import javax.swing.JDialog;

import model.Modulo;
import view.dashboard.Dahsboard;
import view.modules.admin.forms.CrearUsuariosForm;

public class FormFactory {

	private final AplicationContext context;
	
	public FormFactory(AplicationContext context) {
		
		this.context = context;
	}
	public JDialog crearForm(Modulo modulo, Dahsboard dahsboard) {
		
		switch(modulo) {
		case ADMIN: return new CrearUsuariosForm(dahsboard, context);
		
		default:
			break;
		
		}
	
		
		return null;
		
		
	}
}
