package session;

import java.util.Set;      
import model.Permiso;

//clase de estado global para manejar el estado del usuario y obtener permisos 
// que tiene el mismo

//nota: esto es para manejar el estado global del usuario  (como una localStorage)
//y asi no tener que recorrer la list de usuarios
//o en su defecto tener que estar consultado a la BD

public class UsersContext {

	private final int idUsuario;
	//private final Rol rol;
	//private final Set<Permiso> permisos; // si crean un elemento que contenga los persios reemplazar este 
	private final String nombreUsuario; 
	
	public UsersContext(int idUsuario, /*Rol rol,*/  String nombreUsuario, Set<Permiso> permisos) {
		
		this.idUsuario = idUsuario;
		//this.rol = rol;   -> comentado porque no se ha recibido el enum de roles, descomentar cuando se reciba el enum correspondiente
		this.nombreUsuario = nombreUsuario;
		//this.permisos = permisos;
		
	}
	
	//constructor de pruebas favor eliminar cuando creen lo solicitado en cada clase -> en caso de que quieran que funcione de esta manera
	//si no es el caso y ya tienen una funcionalidad creada favor colocar la en esta clase y hacer los cambios pertienentes
	
	
	public  UsersContext(int idUsuario, String nombreUsuario) {
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
	}
	
	//descomentar las lineas de permiso en caso de usar esta clase  -> tomar encuenta que si lo hacen
	// la clase que gestiona y recibe la lista de usuarios ya debe estar creada para ser utilizada
	
	public boolean tienePermisos(Permiso p) {
		return false; //  permisos.contains(p); 
	}
	
	public int getIdUsuarioLogueado() {
		return idUsuario;
	}
	
	public Set<Permiso> getPermisosUser() {
		return null; // permisos;
	}
	
	public String getNombreUsuarioLogueado() {
		return nombreUsuario;
	}
	
}
