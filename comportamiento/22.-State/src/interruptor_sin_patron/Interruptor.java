package comportamiento.state.interruptor_sin_patron;

/*
 * Objeto de contexto.
 */
public class Interruptor {

    private boolean encendido;
    
    public void conmutar() {    	
        if (!encendido) {
        	encendido = true;
        	System.out.println("encendido");
        } else {
        	encendido = false;
        	System.out.println("apagado");
        }    	
    }

}
