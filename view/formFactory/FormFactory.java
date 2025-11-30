package view.formFactory;

import javax.swing.JDialog;

import model.Modulo;
import model.OperationType;
import model.SubModulo;
import view.AplicationContext;
import view.dashboard.Dahsboard;
import view.modules.admin.forms.CrEdUsuariosForm;
import view.modules.admin.forms.DeleteUsuarioForm;
import view.modules.admin.forms.EditUsuarioForm;
import view.modules.billing.forms.CrearFactura;
import view.modules.inventory.forms.offers.CrearOferta;
import view.modules.inventory.forms.offers.EditarOferta;
import view.modules.inventory.forms.offers.EliminarOferta;
import view.modules.inventory.forms.products.*;
import view.modules.inventory.forms.supplier.CrearProveedor;
import view.modules.inventory.forms.supplier.EditarProveedor;
import view.modules.inventory.forms.supplier.EliminarProveedor;
import view.modules.finance.forms.CrearCuentaCobrar;
import view.modules.finance.forms.CrearCuentaPagar;
import view.modules.finance.forms.CrearCuentaPagarPago;
import view.modules.finance.forms.CrearCuentaCobrarPago;

public class FormFactory {

	private final AplicationContext context;
	
	public FormFactory(AplicationContext context) {
		
		this.context = context;
	}
	
	
	public JDialog crearForm(Modulo modulo, SubModulo subModulo, OperationType op, Dahsboard dahsboard) {

	    return switch(modulo) {

	        case ADMIN -> switch(op) {
	            case CREATE -> new CrEdUsuariosForm(dahsboard, context);
	            case EDIT -> new EditUsuarioForm(dahsboard, context);
	            case DELETE -> new DeleteUsuarioForm(dahsboard, context);
	            default -> new JDialog();
	        };

	        case PRODUCTOS -> switch(subModulo) {

	            case PRODUCTO -> switch(op) {
	                case CREATE -> new CrearProducto(dahsboard, context);
	                case EDIT -> new EditarProducto(context, dahsboard);
	                case DELETE -> new DeleteProducto(dahsboard, context);
	                default -> new JDialog();
	            };

	            case PROVEEDOR -> switch(op) {
	                case CREATE -> new CrearProveedor(context, dahsboard);
	                case EDIT -> new EditarProveedor(context, dahsboard);
	                case DELETE -> new EliminarProveedor(context, dahsboard);
	                default -> new JDialog();
	            };

	            case OFERTA -> switch(op) {
	                case CREATE -> new CrearOferta(context, dahsboard);
	                case EDIT -> new EditarOferta(context, dahsboard);
	                case DELETE -> new EliminarOferta(context, dahsboard);
	                default -> new JDialog();
	            };

	            default -> new JDialog();
	        };

			case FINANZAS -> switch(subModulo) {
	            case CUENTA_COBRAR -> switch(op) {
	                case CREATE -> new CrearCuentaCobrar(context, dahsboard);
	                /*case EDIT -> new EditarProducto(context, dahsboard);
	                case DELETE -> new DeleteProducto(dahsboard, context);*/
	                default -> new JDialog();
	            };

				case CUENTA_PAGAR -> switch(op) {
	                case CREATE -> new CrearCuentaPagar(context, dahsboard);
	                /*case EDIT -> new EditarProducto(context, dahsboard);
	                case DELETE -> new DeleteProducto(dahsboard, context);*/
	                default -> new JDialog();
	            };

				case PAGO_CXP -> switch(op) {
	                case CREATE -> new CrearCuentaPagarPago(context, dahsboard);
	                /*case EDIT -> new EditarProducto(context, dahsboard);
	                case DELETE -> new DeleteProducto(dahsboard, context);*/
	                default -> new JDialog();
	            };

				case PAGO_CXC -> switch(op) {
	                case CREATE -> new CrearCuentaCobrarPago(context, dahsboard);
	                /*case EDIT -> new EditarProducto(context, dahsboard);
	                case DELETE -> new DeleteProducto(dahsboard, context);*/
	                default -> new JDialog();
	            };

	            default -> new JDialog();
	        };

	        case FACTURACION -> new CrearFactura();

	        default -> new JDialog();
	    };
	}

	
}
