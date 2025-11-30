package control;

import java.util.List;
import java.util.regex.Pattern;

import dao.ProveedorDAO;
import model.Proveedor;

public class ProveedoresController {

	private ProveedorDAO proveedores;
	private static  final  Pattern regexEmail = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
	@SuppressWarnings("unused")
	private static final Pattern regexText = Pattern.compile("[A-Za-zÁÉÍÓÚáéíóúÑñ ]+");
	
	public ProveedoresController( ProveedorDAO proveedores) {
		
		this.proveedores = proveedores;
	
	}
	
	
	public List<Proveedor> listarProveedores(){
		return proveedores.listar();
	}
	
	public boolean insertarProveedor(Proveedor p) {
		return proveedores.insertar(p);
	}
	
	public boolean desactivarProveedor(int id) {
		return proveedores.eliminar(id);
	}
	
	public boolean editarInformacionProveedor(Proveedor p) {
		
		return proveedores.editar(p);
	}
	
	public Proveedor buscarProveedor(int id) {
		
		return proveedores.buscar(id);
	}
	
	public boolean validarDatosProveedores(Proveedor p) {
		
		if(!regexText.matcher(p.getNombre().trim()).matches()) return false;
		if(p.getNombre() == null || p.getNombre().length() < 2) return false;
		
		if(p.getRncProveedor().length() < 9 || p.getRncProveedor().length() > 9 ) return false;
		if(regexText.matcher(p.getTelefono()).matches()) return false;
		
		if(p.getCalle() == null || p.getCalle().length() < 3) return false;
		if(p.getCiudad() == null || p.getCiudad().length() < 3) return false;
		
		if(!regexText.matcher(p.getPais().trim()).matches()) return false;
		if(p.getPais() == null || p.getPais().length() < 2) return false;
		
		if(p.getActivo() > 2) return false;
		if(!regexEmail.matcher(p.getCorreo()).matches()) return false;
		if(p.getCorreo() == null )return false;
		
		return true;
	}
}
