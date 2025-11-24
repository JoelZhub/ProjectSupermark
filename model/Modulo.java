package model;

import javax.swing.JPanel;

import view.modules.admin.DashboardAdmin;
import view.modules.billing.BillingModule;
//import view.modules.inventory.InventoryModule;
import view.modules.products.ProductsModule;
import view.modules.sales.SalesModule;
//import view.modules.users.UsersModule;

//enum que contiene los modulos existente -> 
//aplicado en la  clase que se encarga de navegar  entre modulos
//

public enum Modulo {
	
	//modulos disponibles de momento -> si se requieren mas se debe construir el panel correspondiente , agregar el permiso
	// y luego agregar aqui
	
	PRODUCTOS(Permiso.PRODUCTOS_VER), 
	
	VENTAS(Permiso.VENTAS_VER),
//	INVENTARIO(Permiso.INVENTARIO_VER), -> dejar comentada este apartado por favor
	ADMIN(Permiso.USUARIOS_VER),
	FACTURACION(Permiso.FACTURACION_VER);
	
	private final Permiso permisosNecesarios;

	//se debe recibir el permiso -> permiso validado segun el tipo de usuario 
	
	Modulo(Permiso permisosNecesarios) {
		this.permisosNecesarios = permisosNecesarios;
	}
	
	public Permiso getPermisoNecesarios() {
		return permisosNecesarios;
	}
	
	//elemento que returna  el panel segun el modulo que se requiere
	public JPanel construirPanel() {
		
		switch(this) {
		
		case PRODUCTOS : return new ProductsModule();
		case VENTAS : return new SalesModule();
//		case INVENTARIO : return new InventoryModule();
		case ADMIN : return new DashboardAdmin();
		case FACTURACION : return new BillingModule();
		default : return new JPanel();
		}
		
	}
	
}
