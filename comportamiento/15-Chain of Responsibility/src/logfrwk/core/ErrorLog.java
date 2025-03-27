package comportamiento.CoR.logfrwk.core;

import static comportamiento.CoR.logfrwk.core.CtesLog.*;

public class ErrorLog extends AbstractLog {

	public ErrorLog(Log sucesor) {
        super(sucesor);        
    }

    @Override
    public void generarMensaje(CtesLog nivel, LogConfig config) {
	    if (procedeMostrar(nivel, config.getNivelMin())) {
        	String msg = "[ERROR] " + config.getMsg();
        	config.setMsg(msg);
            escribir(config);
        } else {
            reenviar(nivel, config);
        }
    }
    
    private boolean procedeMostrar(CtesLog nivel, CtesLog nivelMin) {
    	return nivel == ERROR && 
        		nivel.ordinal() >= nivelMin.ordinal();
    }

}
