package comportamiento.observer.java_support_delegacion;

import java.util.Observable;

/**
 * Una subclase de Observable que permite delegacion.
 * 
 * No podemos utilizar directamente en ConcreteSubject un atributo de tipo
 * java.util.Observable, ya que los métodos clearChanged() y setChanged() no
 * tienen visibilidad pública en Observable.
 */
public class DelegatedObservable extends Observable {

	public void clearChanged() {
		super.clearChanged();
	}

	public void setChanged() {
		super.setChanged();
	}

}
