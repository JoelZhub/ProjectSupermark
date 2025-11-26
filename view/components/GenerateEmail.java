package view.components;

import java.text.Normalizer;

public class GenerateEmail {

	 public static String generarEmail(String nombreCompleto) {
	        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
	            return "";
	        }

	        String[] partes = nombreCompleto.trim().split("\\s+");
	        if (partes.length < 2) {
	    
	            return quitarAcentos(partes[0].toLowerCase()) + "@empresa.com";
	        }

	        String primeraLetra = partes[0].substring(0, 1).toLowerCase();
	        String segundaPalabra = quitarAcentos(partes[1].toLowerCase());

	        return primeraLetra + segundaPalabra + "@empresa.com";
	    }
	 
	    private static String quitarAcentos(String input) {
	        String normalizado = Normalizer.normalize(input, Normalizer.Form.NFD);
	        return normalizado.replaceAll("\\p{M}", "");
	    }
}
