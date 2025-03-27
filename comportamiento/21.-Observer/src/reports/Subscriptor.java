package comportamiento.observer.reports;

/**
 * Interfaz que tienen que implementar aquellas clases interesadas 
 * en recibir notificaciones de cambio sobre un objeto observable
 *
 */
public interface Subscriptor {
	  public void refrescarInforme(Publicador subject);
}
