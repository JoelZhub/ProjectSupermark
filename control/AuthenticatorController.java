package control;

import java.util.Set;
import java.util.regex.Pattern;

import model.Permiso;
import model.PermissionsProvider;
import model.User;
import dao.UserDAO;
import session.UsersContext;

public class AuthenticatorController {

	private UserDAO userDAO;
	//clase que se encarga de la autentificacion de los datos ingresados
	//login -> registro de usuarios
	
	//crear aqui  atributo que contendra  referencia de la clase que mapea los usuarios de la BD
	//crear constructor para inyeccion por dependencia  y trabajar bajo la misma instancia de objecto
		

	//private  -> favor completar con la lista de usuarios (obtenida desde la base de datos) 
	
	private static  final  Pattern regexEmail = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
	//private static final int email_max_length = 254;
	private static final int  password_min_length = 6;
	
	public AuthenticatorController( ) {
		
	}
	

	/*
	 *   CREAR AQUI
	 * */
	
	public UsersContext authenticate(String email, String password) {
		
		//llamar al metodo del controlador de usuarios que busca en la lisata el usuario existente 
		// o en su defecto en la BD -> en caso de no existir favor crear arriba
		
	
		//cambiar este return por los valores del usuario encontrado
		//este lo cree para realizar pruebas de sistema -> favor recordar descomentar la linea de 
		//rol de la clase USERSCONTEXT  
		
		User usuario = userDAO.buscarPorEmail(email);

        if (usuario == null) return null;
        if (!usuario.getPassword().equals(password)) return null;

        Set<Permiso> permisos = PermissionsProvider.getPermissionsProvider(usuario.getRol());
		
		//usa ese elemento de permisos para cuando creen en el usuario
		//(reucerden que deben descomentar el construtor de la clase USUARIOSCONTEX)
		return new UsersContext(
            usuario.getId(),
			usuario.getRol(),
            usuario.getNombre(),
            permisos
        );
	}
	
	public boolean validateFieldsLogin(String email, String password) {
		
		if(email == null) return false;
		if(email.trim().isEmpty()) return false;
		if(!regexEmail.matcher(email).matches()) return false;
		
		if(password == null) return false;
		if(password.trim().isEmpty()) return false;
		if(password.trim().length() < password_min_length ) return false;
		
		return true;
	}
	
	public boolean validateFieldsRegister() {

		return true;
	}
		
}
