package comportamiento.CoR.logfrwk.wrapper;

/*
 * Logger es una clase envoltorio que facilita el uso del 
 * framework de log a las clases cliente, ocultando los
 * detalles de implementacion y proporcionando una serie
 * de métodos fáciles de utilizar.
 * 
 * Por cada clase cliente que quiera usar una configuracion 
 * distinta de log, se tiene que crear una instancia de Logger.
 * A su vez, con cada ejecucion del constructor de logger se 
 * crea una instancia del framework de log.
 *  
 * Notad que su constructor se encarga de crear la cadena
 * objetos receptores de peticiones de log.
 */

import static comportamiento.CoR.logfrwk.core.CtesLog.*;

import comportamiento.CoR.logfrwk.core.*;

public class Logger {		
	
	// Referencia a la instancia del framework 	
	private Log log;
	
	// Nombre de la clase que llama. Util para mostrarlo 
	// junto a cada mensaje de log
	private String llamante;
	
	// Configuracion de log asociada a esta clase cliente
	private LogConfig config; 
		
	// Constructor
	public Logger(Class<?> claseLlamante) {
			
		/*
		 * Creacion de la cadena de manejadores de log.
		 * 
		 * Notad que al ultimo de la cadena le decimos 
		 * que su sucesor es null para marcar el final.
		 * Ordenamos la cadena según nos interesa: el nivel
		 * menor es TRAZA y el mayor FATAL
		 */		
		log = 
			new TrazaLog(
				new DebugLog(
					new InfoLog(
						new AvisoLog(
							new ErrorLog(
								new FatalLog(null))))));		 		
		
		llamante = claseLlamante.getSimpleName();
		config = new LogConfig(); // Configuracion de Log predeterminada	
	}
	
	// ******************* NIVEL DE LOG *********************
	
	public void traza(String msg) {
		config.setMsg("[" + llamante + "] : " + msg);
		log.generarMensaje(TRAZA, config);
	}
	
	public void debug(String msg) {
		config.setMsg("[" + llamante + "] : " + msg);
		log.generarMensaje(DEBUG, config);
	}
	
	public void info(String msg) {
		config.setMsg("[" + llamante + "] : " + msg);
		log.generarMensaje(INFO, config);
	}
	
	public void aviso(String msg) {
		config.setMsg("[" + llamante + "] : " + msg);
		log.generarMensaje(AVISO, config);
	}
	
	public void error(String msg) {
		config.setMsg("[" + llamante + "] : " + msg);
		log.generarMensaje(ERROR, config);
	}
	
	public void fatal(String msg) {
		config.setMsg("[" + llamante + "] : " + msg);
		log.generarMensaje(FATAL, config);
	}

	// ****************** CONFIGURACION FINA *******************
	
	public void setMinNivel(CtesLog minNivel) {		
		config.setNivelMin(minNivel);
	}
	
	public void setSalida(CtesLog salida) {
		config.setSalida(salida);		
	}
	
	public void activar() {
		config.setLogActivo(true);
	}
	
	public void desactivar() {
		config.setLogActivo(false);
	}
	
	// ****************** CONFIGURACION GRUESA *******************
	
	public void configurar(CtesLog minNivel, CtesLog salida) {		
		setMinNivel(minNivel);
		setSalida(salida);		
	}
		
}
