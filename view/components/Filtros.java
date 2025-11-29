package view.components;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

public class Filtros {

	//aqui se invocan los filtros creados si quieren pueden crear uno y aqui implementan el metodo en caso de
	//requerirlo
	
	  public static void aplicarFiltroNumericoSpinner(JSpinner spinner) {
	        JFormattedTextField txt = ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField();
	        ((AbstractDocument) txt.getDocument()).setDocumentFilter(new NumericFilter());
	    }

	    public static void aplicarFiltroNumericoTextField(JTextField txt) {
	        ((AbstractDocument) txt.getDocument()).setDocumentFilter(new NumericFilter());
	    }
	    
	    public static void aplicarFiltroSoloLetras(JTextField txt) {
	        ((AbstractDocument) txt.getDocument()).setDocumentFilter(new LettersFilter());
	    }
	    
	    public static void aplicarFiltroTelefono(JTextField txt) {
	        ((AbstractDocument) txt.getDocument()).setDocumentFilter(new PhoneFilter());
	    }

}

