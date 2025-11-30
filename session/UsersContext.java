package session;

import java.util.Set;      
import model.Permiso;
import model.Rol;

public class UsersContext {

	private final int idUsuario;
	private final Rol rol;
	private final Set<Permiso> permisos; 
	private final String nombreUsuario; 
	
	public UsersContext(int idUsuario, Rol rol, String nombreUsuario, Set<Permiso> permisos) {
		
		this.idUsuario = idUsuario;
		this.rol = rol; 
		this.nombreUsuario = nombreUsuario;
		this.permisos = permisos;
		
	}
		
	public Rol getRolUsuarioLogueado(){
	
		 return rol;
	}
	
	public boolean tienePermisos(Permiso p) {
        return permisos != null && permisos.contains(p);
    }

    public int getIdUsuarioLogueado() {
        return idUsuario;
    }

    public Set<Permiso> getPermisosUser() {
        return permisos;
    }

    public String getNombreUsuarioLogueado() {
        return nombreUsuario;
    }

    //comente este metood porque estaba demas arriba esta getRolUsuarioLogueado que hace la misma funcion que este
//    public Rol getRol() {
//        return rol;
//    }
}
