package comportamiento.observer.java_support_delegacion;

import java.util.Observer;

public class MainClient {

  public static void main(String args[]) {

    // Crear el Subject y los Observers.
    ConcreteSubject subject = new ConcreteSubject("Caja de galletas", 1.29F);
    Observer nombreObs = new NombreObserver();
    Observer precioObs = new PrecioObserver();

    // Añadir los Observers a la lista de observers del Subject
    subject.addObserver(nombreObs);
    subject.addObserver(precioObs);

    // Hacer algunos cambios en el estado del Subject  
    // para ver cómo se comportan los Observers
    subject.setNombre("Bollos rellenos de chocolate");
    subject.setPrecio(2.57F);
    subject.setPrecio(1.99F);
    subject.setNombre("Tarta de queso");
  }

}



