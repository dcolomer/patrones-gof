package comportamiento.observer.improve;

import comportamiento.observer.improve.event.PropertyEvent;

public class FullObserver implements PropertyListener {

	private Float precio;
	private String descripcion;
	
	@Override
	public void onPropertyEvent(PropertyEvent event) {
		System.out.println(this.getClass());
		
		System.out.println("Atributo: " + event.getNombre());
		
		Class<?> clase = event.getClassValor();		
		if (clase.equals(Float.class)) {
			precio = (Float) event.getValor();		
			System.out.println("Valor: " + precio.floatValue());
		} else if (clase.equals(String.class)) {
			descripcion = (String) event.getValor();		
			System.out.println("Valor: " + descripcion);	
		}
		
		System.out.println();
	}
}
