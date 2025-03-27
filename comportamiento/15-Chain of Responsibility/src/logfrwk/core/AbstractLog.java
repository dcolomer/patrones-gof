package comportamiento.CoR.logfrwk.core;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

abstract class AbstractLog implements Log {

	private static final String ficheroSalida = 
    		AbstractLog.class.getPackage().getName() + ".log.txt";
	
	protected Log sucesor;    
	
    // Constructor, sólo invocable por las subclases    
    protected AbstractLog(Log sucesor) {
        this.sucesor = sucesor;        
    }
    
	/** 
	 * @see Log#generarMensaje(CtesLog, LogConfig)
	 */
	@Override
	public abstract void generarMensaje(CtesLog nivel, LogConfig config);
    
    /*
     * Implementación de conveniencia para las subclases. 
     * Les permite pasarse en cadena la configuracion actual de log 
     */
    protected void reenviar(CtesLog nivel, LogConfig config) {
        if (sucesor != null) {        	
            sucesor.generarMensaje(nivel, config);
        }    
    }
    
    /*
     * Implementación de conveniencia para las subclases que
     * escribe el mensaje en la salida configurada
     */
    protected void escribir(LogConfig config) {
    	String msg = config.getMsg();
		if (config.isLogActivo()) {																				
			msg = "[" + getCadenaFecha() + "]" + msg; 			
			switch (config.getSalida()) {
				case PANTALLA:
					System.out.println(msg);
					break;
				case FICHERO:
					escribirEnFichero(msg);
					break;
				case PANTALLA_Y_FICHERO: 
					System.out.println(msg);
					escribirEnFichero(msg);
					break;
				default:
					throw new RuntimeException("Opcion de medio de salida de log no valida: " + config.getSalida());
			}    		
		}
	}	
	
	// ************* METODOS PRIVADOS *********************
	
	/*
	 * Método que se utiliza cuando se quiere utilizar un fichero 
	 * en disco para almacenar los mensajes de log
	 */
	private static synchronized void escribirEnFichero(String msg) {
		PrintWriter pw = null;
		final boolean anadirAlFichero = true;
		
		try {
			pw = new PrintWriter(
					new FileWriter(ficheroSalida, anadirAlFichero));
			pw.println(msg);
		} catch (IOException e) {			
			e.printStackTrace();
		} finally {
			if (pw != null) {				
				pw.close();				
			}
		}
	}

	/*
	 * A partir de la fecha/hora actual construye una cadena formateada 
	 * que aparecerá al principio de cada línea de log 
	 */
	private String getCadenaFecha() {
    	
    	SimpleDateFormat sdf = 
				new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");	
    	
		return sdf.format(Calendar.getInstance().getTime());		
	}
		    
}