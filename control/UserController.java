package control;

import java.util.Set;
import java.util.regex.Pattern;
import model.Permiso;
import model.PermissionsProvider;
import model.User;
import dao.UserDAO;
import session.UsersContext;

public class UserController {

	private UserDAO userDAO;
	private static  final  Pattern regexEmail = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
	@SuppressWarnings("unused")
	private static final  Pattern regexText = Pattern.compile(".*[^A-Za-zÁÉÍÓÚáéíóúÑñ].*");
	
	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	

	public UsersContext authenticate(String email, String password) {
			
		User usuario = userDAO.buscarPorEmail(email);

        if (usuario == null) return null;
        if (!usuario.getPassword().equals(password)) return null;

        Set<Permiso> permisos = PermissionsProvider.getPermissionsProvider(usuario.getRol());
		
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
		return true;
	}
	
	public boolean validateFieldsRegister(User u) {

		if(u.getNombre() == null) return false;
		if(u.getNombre().length() < 2) return false;
//		if(!regexText.matcher(u.getNombre()).matches()) return false;
		
		if(u.getRol() == null) return false;
		
		if(u.getEmail() == null) return false;
		if(u.getEmail().trim().isEmpty()) return false;
//		if(!regexEmail.matcher(u.getEmail()).matches()) return false;
		
		if(u.getPassword() == null) return false;
		if(u.getPassword().trim().isEmpty()) return false;	
		
		return true;
	}
	
	public boolean crearUsuario(User u) {
			if(userDAO.insertar(u)) {
				return true;
			}
			return false;
		}

	}
	

		

