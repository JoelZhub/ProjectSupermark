package model;

import javax.swing.JPanel;

import view.AplicationContext;
import view.dashboard.Dahsboard;
import view.modules.admin.DashboardAdmin;
import view.modules.billing.BillingModule;
//import view.modules.inventory.InventoryModule;
import view.modules.products.ProductsModule;
import view.modules.sales.SalesModule;

public enum Modulo {

	PRODUCTOS(Permiso.PRODUCTOS_VER), 
	VENTAS(Permiso.VENTAS_VER),
	ADMIN(Permiso.USUARIOS_VER),
	FACTURACION(Permiso.FACTURACION_VER);
	
	private AplicationContext context;
	private Dahsboard dahsboard;
	
	public void setAplicationContext(AplicationContext context) {
		
		this.context = context;
	}
	public void setDahsboard(Dahsboard dahsboard) {
		
		this.dahsboard = dahsboard;
	}
	private final Permiso permisosNecesarios;

	Modulo(Permiso permisosNecesarios) {
		this.permisosNecesarios = permisosNecesarios;
	}
	
	public Permiso getPermisoNecesarios() {
		return permisosNecesarios;
	}
	
	public JPanel construirPanel() {
		
		switch(this) {
		
		case PRODUCTOS : return new ProductsModule();
		case VENTAS : return new SalesModule();
		case ADMIN : return new DashboardAdmin(context, dahsboard);
		case FACTURACION : return new BillingModule();
		default : return new JPanel();
		}
		
	}
	
}
