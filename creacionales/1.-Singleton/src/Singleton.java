package creacionales.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class Singleton implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Singleton instance = new Singleton();

	private Singleton() {
		System.out.println("Singleton(): inicializando la instancia");
	}

	public static Singleton getInstance() {    
		return instance;
	}

	public void hacerAlgo()	{
		System.out.println("hacer algo(): el Singleton hace algo!");
	}


    // Este metodo es invocado immediatamente despues de la deserializacion
    // Hacemos que retorne la instancia almacenada en el atributo estatico
	// y la nueva instancia resultado de la deserializacion es desechada 
	// para el recolector de basura
    private Object readResolve() throws ObjectStreamException {
        return instance;
    }
}

