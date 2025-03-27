package comportamiento.CoR.logfrwk.core;

import static comportamiento.CoR.logfrwk.core.CtesLog.*;

public class LogConfig {

	private CtesLog nivelMin = TRAZA;    
	private CtesLog salida = PANTALLA;    
	private boolean logActivo = true;
    
	private String msg;
    
	public CtesLog getNivelMin() { return nivelMin;}
	public CtesLog getSalida() { return salida;	}
	public boolean isLogActivo() { return logActivo; }
	
	public String getMsg() {return msg; }
	
	public void setNivelMin(CtesLog nivelMin) {
		this.nivelMin = nivelMin;
	}
	
	public void setSalida(CtesLog salida) {
		this.salida = salida;
	}
	
	public void setLogActivo(boolean log_activo) {
		this.logActivo = log_activo;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
		
}
