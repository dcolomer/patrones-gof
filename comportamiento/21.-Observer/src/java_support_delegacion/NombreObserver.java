package comportamiento.observer.java_support_delegacion;

import java.util.*;

/**
 * Un observador de cambios en el atributo 'nombre' del ConcreteSubject
 */
public class NombreObserver implements Observer {
  
  private String nombre; 

  public NombreObserver() {
    nombre = null;
    System.out.println("NombreObserver creado: el nombre es " + nombre);
  }

  @Override
  public void update(Observable obj, Object arg) {
    if (arg instanceof String) {
      nombre = (String) arg;
      System.out.println("NombreObserver: nombre ha cambiado a " + nombre);
    }
    else {
      // irrelevante para este observer
    }
  }  
}
  
