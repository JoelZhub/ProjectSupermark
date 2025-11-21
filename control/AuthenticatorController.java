package control;

import java.util.regex.Pattern;

public class AuthenticatorController {

	//clase que se encarga de la autentificacion de los datos ingresados
	//login -> registro de usuarios
	
	//crear aqui  atributo que contendra  referencia de la clase que mapea los usuarios de la BD
	//crear constructor para inyeccion por dependencia  y trabajar bajo la misma instancia de objecto
		
	private static  final  Pattern regexEmail = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
	private static final int email_max_length = 254;
	private static final int  password_min_length = 6;
	
	public AuthenticatorController( /*colocar aqui la refencia del elemento que recibe
	 a los usuarios de la bd y se encarga de crearlos*/  ) {
		
	}
	
	private boolean validarCamposLogin(String email, String password) {
		
		if(email == null) return false;
		if(email.trim().isEmpty()) return false;
		if(!regexEmail.matcher(email).matches()) return false;
		
		if(password == null) return false;
		if(password.trim().isEmpty()) return false;
		if(password.trim().length() < password_min_length ) return false;
		
		return true;
	}
	
	
	
	
		
	
}
