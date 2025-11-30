package view.components;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumericFilter extends DocumentFilter {

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {

        String newText = buildNewText(fb, offset, 0, string);

        if (isNumeric(newText)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {

        String newText = buildNewText(fb, offset, length, text);

        if (isNumeric(newText)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    private String buildNewText(FilterBypass fb, int offset, int length, String text)
            throws BadLocationException {

        String oldText = fb.getDocument().getText(0, fb.getDocument().getLength());
        StringBuilder sb = new StringBuilder(oldText);
        sb.replace(offset, offset + length, text == null ? "" : text);

        return sb.toString();
    }

   
    public boolean isNumeric(String value) {
        if (value == null || value.isEmpty()) return true;
        return value.matches("^\\d*(\\.\\d*)?$");
    }
}
