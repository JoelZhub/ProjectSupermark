package view.components;


import java.util.Random;

public class RNCUtils {

    private static final String RNC_CONSUMIDOR_FINAL = "999999999";
    private static final int RNC_LENGTH = 9;
    private static final Random random = new Random();

 
    public static String generarRNCConsumidorFinal() {
        return RNC_CONSUMIDOR_FINAL;
    }

    
    public static String generarRNCFical() {
        StringBuilder rnc = new StringBuilder();
        for (int i = 0; i < RNC_LENGTH; i++) {
            rnc.append(random.nextInt(10)); 
        }
        return rnc.toString();
    }

    
    public static boolean esRNCValido(String rnc) {
        return rnc != null && rnc.matches("\\d{9}");
    }
}
