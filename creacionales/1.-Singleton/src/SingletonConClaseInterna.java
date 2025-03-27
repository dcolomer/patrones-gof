package creacionales.singleton;

import java.io.Serializable;

public class SingletonConClaseInterna implements Serializable {

	private static final long serialVersionUID = 1L;

	// Prevenir la instanciacion desde el exterior de la clase
	private SingletonConClaseInterna() {
		System.out
		.println("SingletonInterna(): inicializando la instancia");
	}

	/*
	 * La clase SingletonHolder se carga en la primera ejecucion de
	 * SingletonInterna.getInstance(), pero no antes
	 */
	private static class SingletonHolder {
		private static final SingletonConClaseInterna instancia = 
			new SingletonConClaseInterna();
	}

	/*
	 * Metodo estatico publico que retorna la instancia. Notad que lo que se
	 * devuelve es el atributo estatico de la clase estatica
	 */
	public static SingletonConClaseInterna getInstance() {
		return SingletonHolder.instancia;
	}
}
