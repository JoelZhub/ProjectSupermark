package view.components;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import view.dashboard.Dahsboard;

public class ValidateDate {

	private static Dahsboard dahsboard;
	@SuppressWarnings("static-access")
	public ValidateDate(Dahsboard dahsboard) {
		this.dahsboard = dahsboard;
		
	}
	   public static boolean validarFechaInicio(LocalDate fechaInicio) {
	        LocalDate hoy = LocalDate.now();

	        if (fechaInicio == null) return false;

	        if (fechaInicio.isBefore(hoy)) {
	          new Messages(dahsboard,"La fecha de inicio no puede ser menor que el día actual.").messageError();
	          return false;
	        }
	        return true;
	    }

	    public static boolean validarFechaFin(LocalDate fechaInicio, LocalDate fechaFin) {
	        if (fechaInicio == null || fechaFin == null) return false;

	        if (fechaFin.isBefore(fechaInicio)) {
	            new Messages(dahsboard,"La fecha fin no puede ser menor que la fecha de inicio.").messageError();
	            return false;
	        }

	        return true;
	    }
	    
	    
	    public static LocalDate convertir(Date date) {
	        if (date == null) return null;
	        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    }
}
