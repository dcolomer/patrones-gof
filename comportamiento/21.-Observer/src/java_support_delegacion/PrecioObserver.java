package comportamiento.observer.java_support_delegacion;

import java.util.*;

/**
 * Un observador de cambios en el atributo 'precio' del ConcreteSubject
 */
public class PrecioObserver implements Observer {
  
  private float precio; 

  public PrecioObserver() {
    precio = 0;
    System.out.println("PrecioObserver creado: el precio es " + precio);
  }

  @Override
  public void update(Observable obj, Object arg) {
    if (arg instanceof Float) {
      precio = ((Float)arg).floatValue();   
      System.out.println("PrecioObserver: precio ha cambiado a " + precio);
    }
    else {
    	// irrelevante para este observer
    }
  }
  
}
  
