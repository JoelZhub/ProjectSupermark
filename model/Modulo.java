package model;

import javax.swing.JPanel;

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
		case ADMIN : return new DashboardAdmin();
		case FACTURACION : return new BillingModule();
		default : return new JPanel();
		}
		
	}
	
}
