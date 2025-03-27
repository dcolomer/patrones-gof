package comportamiento.observer.reports;

import java.util.Vector;

/**
 * Subject encargado de notificar a los observadores
 * un cambio en su estado.
 * Esta clase trabaja en conjunto con ReportManagerGUI,
 * ya que los cambios en su atributo 'departamento' es 
 * lo que realmente interesa a los observadores.
 */
public class ReportManager implements Publicador {

	private Vector<Subscriptor> observersList;
	
	public ReportManager() {		
		observersList = new Vector<Subscriptor>();
	}

	@Override
	public void notificarSubscriptores() {
		// Notificar a todos los subscriptores
		for (int i = 0; i < observersList.size(); i++) {
			Subscriptor observer = observersList.get(i);
			observer.refrescarInforme(this);
		}		
	}

	@Override
	public void registrar(Subscriptor obs) {		
		observersList.add(obs);		
	}

	@Override
	public void desRegistrar(Subscriptor obs) {
		observersList.remove(obs);
	}
}
