package view.components;

public class GeneratePassword {
	
	public static String generateSecurePassword() {
	    int min = 8;
	    int max = 10;
	    int length = (int) (Math.random() * (max - min + 1)) + min;

	    String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String lower = "abcdefghijklmnopqrstuvwxyz";
	    String digits = "0123456789";
	    String symbols = "!@#$%&*?";
	    String pool = upper + lower + digits + symbols;

	    StringBuilder sb = new StringBuilder();

	    for (int i = 0; i < length; i++) {
	        int idx = (int) (Math.random() * pool.length());
	        sb.append(pool.charAt(idx));
	    }

	    return sb.toString();
	}
	
}
