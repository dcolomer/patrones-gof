package comportamiento.observer.reports;

/**
 * Interfaz a implementar por una clase que quiera 
 * publicar notificaciones para objetos subscriptores  
 */
public interface Publicador {
	public void notificarSubscriptores();
	public void registrar(Subscriptor subscriptor);
	public void desRegistrar(Subscriptor subscriptor);
}
