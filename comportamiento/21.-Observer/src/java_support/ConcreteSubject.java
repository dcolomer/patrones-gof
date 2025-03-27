package comportamiento.observer.java_support;

import java.util.*;

/**
 * Un subject al que observar
 */
public class ConcreteSubject extends Observable {
  
  private String nombre;
  private float precio;

  public ConcreteSubject(String nombre, float precio) {
    this.nombre = nombre;
    this.precio = precio;
    System.out.println("ConcreteSubject creado: el nombre es " + 
    		nombre + " y el precio es " + precio);
  }

  public String getNombre() {
    return nombre;
  }
  
  public float getPrecio() {
    return precio;
  }
  
  public void setNombre(String nombre) {
    this.nombre = nombre;
    setChanged();
    notifyObservers(nombre);
  }
  
  public void setPrecio(float precio) {
    this.precio = precio;
    setChanged();
    notifyObservers(new Float(precio));
  }
  
}
  
