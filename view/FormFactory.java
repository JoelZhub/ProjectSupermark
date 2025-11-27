package view;

import javax.swing.JDialog;

import model.Modulo;
import model.OperationType;
import view.dashboard.Dahsboard;
import view.modules.admin.forms.CrEdUsuariosForm;
import view.modules.admin.forms.DeleteUsuarioForm;
import view.modules.admin.forms.EditUsuarioForm;
import view.modules.billing.forms.CrearFactura;
import view.modules.products.forms.CrearProducto;
import view.modules.products.forms.DeleteProducto;
import view.modules.products.forms.EditarProducto;

public class FormFactory {

	private final AplicationContext context;
	
	public FormFactory(AplicationContext context) {
		
		this.context = context;
	}
	public JDialog crearForm(Modulo modulo, Dahsboard dahsboard, OperationType op ) {
		
		switch(modulo) {
		case ADMIN:
			return switch(op) {
			case CREATE -> new  CrEdUsuariosForm(dahsboard, context);
			case EDIT -> new EditUsuarioForm(dahsboard, context);
			case DELETE -> new DeleteUsuarioForm(dahsboard, context);
			default -> new JDialog();
			};
			
		case FACTURACION: return new CrearFactura();
		case PRODUCTOS:
			return  switch(op) {		
			case CREATE -> new CrearProducto(dahsboard, context);
			case EDIT -> new EditarProducto(context, dahsboard);
			case DELETE -> new DeleteProducto(dahsboard, context);
			default -> new JDialog();
		
			};
		default:
			return new JDialog();
		
		}	
	}
}
