package comportamiento.observer.improve;

import comportamiento.observer.improve.event.PropertyEvent;

public class DescripcionObserver implements PropertyListener {

	private String descripcion;
	
	@Override
	public void onPropertyEvent(PropertyEvent event) {
		System.out.println(this.getClass());
		System.out.println("Atributo: " + event.getNombre());		
		descripcion = (String) event.getValor();		
		System.out.println("Valor: " + descripcion);
		System.out.println();
	}
}
