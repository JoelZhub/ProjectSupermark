package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Pattern;

public class ValidacionFecha {
    //patron
    private static final Pattern TIMESTAMP_REGEX = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$");

    //valida que el string tenga formato "yyyy-MM-dd HH:mm:ss" y que represente una fecha y hora valida
    public static boolean isValidTimestamp(String fecha) {
        if (fecha == null) return false;

        fecha = fecha.trim().replaceAll("\\s+", " ");

        if (!TIMESTAMP_REGEX.matcher(fecha).matches()) return false;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        sdf.setLenient(false);

        try {
            sdf.parse(fecha);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}