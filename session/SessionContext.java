package session;
//clase que maneja los estados del usuario logueado

public class SessionContext {
	private static UsersContext current;
	public static void set(UsersContext userContext) {
		current = userContext;
	}	
	public static UsersContext get() {
		return current;
	}
	public static void clear() {
		current = null;
	}
}
