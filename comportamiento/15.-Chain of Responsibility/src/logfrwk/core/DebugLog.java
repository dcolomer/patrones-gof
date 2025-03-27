package comportamiento.CoR.logfrwk.core;

import static comportamiento.CoR.logfrwk.core.CtesLog.*;

public class DebugLog extends AbstractLog {

	public DebugLog(Log sucesor) {
        super(sucesor);        
    }

    @Override
    public void generarMensaje(CtesLog nivel, LogConfig config) {
	    if (procedeMostrar(nivel, config.getNivelMin())) {
        	String msg = "[DEBUG] " + config.getMsg();
        	config.setMsg(msg);
            escribir(config);
        } else {
            reenviar(nivel, config);
        }
    }
    
    private boolean procedeMostrar(CtesLog nivel, CtesLog nivelMin) {
    	return nivel == DEBUG && 
        		nivel.ordinal() >= nivelMin.ordinal();
    }

}
