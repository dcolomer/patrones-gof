package comportamiento.observer.improve;

import comportamiento.observer.improve.event.PropertyEvent;

public class PrecioObserver implements PropertyListener {

	private Float precio;
	
	@Override
	public void onPropertyEvent(PropertyEvent event) {
		System.out.println(this.getClass());
		System.out.println("Atributo: " + event.getNombre());		
		precio = (Float) event.getValor();		
		System.out.println("Valor: " + precio.floatValue());
		System.out.println();
	}
}
