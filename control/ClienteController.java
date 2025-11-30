package control;

import java.util.List;

import dao.ClienteDAO;
import model.Cliente;

public class ClienteController {

	private ClienteDAO clientes;
	
	public ClienteController(ClienteDAO clientes) {
		
		this.clientes = clientes;
		
	}
	
	public boolean validarCampos(Cliente cliente) {
		if(cliente.getNombre() == null || cliente.getNombre().equals("")) return false;
		if(cliente.getIdentificacion() == null || cliente.getIdentificacion().equals("") || 
				cliente.getIdentificacion().length() < 6 || cliente.getIdentificacion().length() > 6  ) return false;
		if(cliente.getTelefono().equals("") || cliente.getTelefono() == null) return false;
		if(cliente.getClasificacion().equals("") || cliente.getClasificacion() == null ) return false;
		return true;
	}
	
	public boolean crearCliente(Cliente cliente) {	
		return clientes.insertar(cliente);
	}
	
	public boolean editarCliente(Cliente cliente) {
		
		return clientes.editar(cliente);
	}
	
	public boolean eliminarCLiente(int id) {
		return clientes.eliminar(id);
	}
	public Cliente buscarCliente(int id) {
		return clientes.buscar(id);
	}
	
	public List<Cliente> listarClientes(){
		return clientes.listar();
	}


	
}
