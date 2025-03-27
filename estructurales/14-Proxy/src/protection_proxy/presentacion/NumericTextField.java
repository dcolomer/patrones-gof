package estructurales.proxy.protection_proxy.presentacion;

import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class NumericTextField extends JTextField{

	private static final long serialVersionUID = 1L;

	@Override
    protected Document createDefaultModel()
    {
        return new NumericDocument();
    }

    private static class NumericDocument extends PlainDocument
    {
		private static final long serialVersionUID = 1L;
		// Expresion regular con la que debe coincidir lo que teclee el usuario (sólo digitos)
        private final static Pattern DIGITS = Pattern.compile("\\d*");
        // No queremos el campo tenga mas de 3 digitos
        private final static int MAX_LEN_PERMITIDA = 3;
        
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
        {        	        	
        	// Solo admitir el texto si es un numero y no se excede el tamaño permitido
            if (str != null) {            	            	 
            	if (caracterEsValido(str) && longitudEsValida(str)) {
            		super.insertString(offs, str, a);
            	}
            }    
        }
        
        private boolean caracterEsValido(String str) {
        	return DIGITS.matcher(str).matches();
        }
        
        private boolean longitudEsValida(String str) {
        	int lenMaxima = getLength() + str.length();
        	return lenMaxima <= MAX_LEN_PERMITIDA ? true : false;
        }
    }
}
