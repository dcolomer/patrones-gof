package comportamiento.CoR.logfrwk.core;

public interface Log {

	/**
	 * Método abstracto a implementar en cada subclase.
	 * Toda subclase comprobará si el nivel de log recibido por parámetro
	 * es el adecuado para ella, en cuyo caso imprimirá el mensaje. En
	 * caso contrario llamará al método reenviar() para pasar la peticion 
	 * a la siguiente clase de la cadena
	 */
	public abstract void generarMensaje(CtesLog nivel, LogConfig config);

}