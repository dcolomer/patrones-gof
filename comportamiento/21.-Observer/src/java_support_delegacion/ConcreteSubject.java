package comportamiento.observer.java_support_delegacion;

import java.util.*;

/**
 * Un subject al que observar. La diferencia con la anterior versión es que esta
 * clase ya extiende de otra, por lo que no podemos heredar de
 * java.util.Observable. La solución pasa por delegar, esto es, declarar un
 * atributo de tipo DelegatedObservable y pasarle a él las llamadas.
 */

class ParentClass {
}

public class ConcreteSubject extends ParentClass {

	private DelegatedObservable observable;
	
	private String nombre;
	private float precio;

	public ConcreteSubject(String nombre, float precio) {
		this.nombre = nombre;
		this.precio = precio;
		observable = new DelegatedObservable();
		System.out.println("ConcreteSubject creado: el nombre es " + nombre
				+ " y el precio es " + precio);
	}

	public String getNombre() {
		return nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void addObserver(Observer o) {
		observable.addObserver(o);
	  }
	  
	  public void deleteObserver(Observer o) {
		  observable.deleteObserver(o);
	  }
	  
	public void setNombre(String nombre) {
		this.nombre = nombre;
		observable.setChanged();
		observable.notifyObservers(nombre);
	}

	public void setPrecio(float precio) {
		this.precio = precio;
		observable.setChanged();
		observable.notifyObservers(new Float(precio));
	}

}
