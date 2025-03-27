package comportamiento.strategy.video;

public class MainClient {

	private static final int[] diasPrestamo = {
		1, 2, 3, 4, 5, 6, 7
	};
	
	public static void main(String[] args) {
	
		// Creamos un cliente
		Cliente cliente = new Cliente("Daniel Colomer");
		cliente.persist();
		
		// ***********************************************************************
				
		// Creamos una pelicula de tipo normal
		Pelicula peli = Pelicula.crearNormal("La vida de Tomas");
		/* 
		 * Ahora cambiamos su estado para indicar que está desfasada.
		 * Esto tiene implicaciones serias, ya que cambia la manera
		 * en que se calcula su precio de alquiler.
		 */
		peli.setAntigua();
		// La grabamos
		peli.persist();

		// Creamos un soporte fisico para esa pelicula
		BluRay bluRay = new BluRay("BR1","11111-X", peli);
		bluRay.persist();
		
		// Creamos un alquiler de 4 dias para ese soporte fisico
		Alquiler alquiler = new Alquiler("alq1", bluRay, diasPrestamo[3]);
		alquiler.persist();
		
		// Creamos un enlace entre el cliente y el alquiler
		cliente.nuevoAlquiler(alquiler);
		
		// ************************************************************************
		
		// Creamos una pelicula
		Pelicula peli2 = Pelicula.crearEstreno("El señor de los anillos. Parte 1");		
		peli2.persist();

		// Creamos un soporte fisico para esa pelicula
		Dvd dvd = new Dvd("DV1","21212-Z", peli2);
		dvd.persist();
		
		// Creamos un alquiler de 2 dias para ese soporte fisico
		Alquiler alquiler2 = new Alquiler("alq2", dvd, diasPrestamo[1]);
		alquiler2.persist();
		
		// Creamos un enlace entre el cliente y el alquiler
		cliente.nuevoAlquiler(alquiler2);
		
		// ************************************************************************
		
		// Mostrar por pantalla lo que se le debe al video club 
		String estadoCuentas = cliente.mostrarEstadoCuentas();
		System.out.println(estadoCuentas);
		
		mostrarObjetosPersistidos();
	}

	private static void mostrarObjetosPersistidos() {
		say("Mostrando informacion sobre los objetos de dominio previamente grabados");
		say("_______________________________________________________________________");
		
		/*say(Pelicula.get("La vida de Tomas"));
		say(Pelicula.get("El señor de los anillos. Parte 1"));
		
		say(BluRay.get("BR1"));
		say(Dvd.get("DV1"));
		
		say(Alquiler.get("alq1"));
		say(Alquiler.get("alq2"));*/
		
		say(Cliente.get("Daniel Colomer"));
	}

	private static void say(Object target) {
		System.out.println(target);		
	}
}

