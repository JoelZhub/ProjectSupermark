package model;

import javax.swing.JPanel;

import view.AplicationContext;
import view.dashboard.Dahsboard;
import view.modules.admin.DashboardAdmin;
import view.modules.billing.BillingModule;
import view.modules.custormerService.customerService;
import view.modules.finance.FinanceModule;
import view.modules.inventory.InventoryModule;
import view.modules.sales.SalesModule;
import view.modules.report.ReportModule;

public enum Modulo {

	PRODUCTOS(Permiso.PRODUCTOS_VER), 
	VENTAS(Permiso.VENTAS_VER),
	ADMIN(Permiso.USUARIOS_VER),
	FACTURACION(Permiso.FACTURACION_VER),
	FINANZAS(Permiso.FINANZAS_VER),
	CLIENTE(Permiso.SERVICIO_CLIENTE);
	REPORTES(Permiso.REPORTES_VER);
	
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
		
		case PRODUCTOS : return new InventoryModule(dahsboard, context);
		case VENTAS : return new SalesModule(context, dahsboard);
		case ADMIN : return new DashboardAdmin(context, dahsboard);
		case FACTURACION : return new BillingModule(context, dahsboard);
		case FINANZAS : return new FinanceModule(dahsboard, context);
		case CLIENTE: return new customerService(context, dahsboard);
		case REPORTES : return new ReportModule(dahsboard, context);
		default : return new JPanel();
		}
		
	}
	
}
