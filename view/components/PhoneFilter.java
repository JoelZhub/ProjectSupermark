package view.components;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class PhoneFilter extends DocumentFilter {

	//estas clases junto con letters filter, numeric filter actuan como filtro para los diversos campos de los formularios

    private static final int MAX_DIGITS = 15; 

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {

        if (isValid(fb, string)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {

        if (isValid(fb, text)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    private boolean isValid(FilterBypass fb, String text) throws BadLocationException {
        if (text == null) return false;

        String current = fb.getDocument().getText(0, fb.getDocument().getLength());
        String future = current + text;

        if (!future.startsWith("+")) return false;

        String digitsOnly = future.substring(1);

        if (!digitsOnly.matches("\\d*")) return false;

        return digitsOnly.length() <= MAX_DIGITS;
    }
}
