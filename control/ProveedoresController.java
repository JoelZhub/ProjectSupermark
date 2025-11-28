package control;

import java.util.List;

import dao.ProveedorDAO;
import model.Proveedor;

public class ProveedoresController {

	private ProveedorDAO proveedores;
	
	public ProveedoresController( ProveedorDAO proveedores) {
		
		this.proveedores = proveedores;
	
	}
	
	
	public List<Proveedor> listarProveedores(){
		
		return proveedores.listar();
	}
}
