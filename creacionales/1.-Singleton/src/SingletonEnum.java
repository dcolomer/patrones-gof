package creacionales.singleton;

import java.util.Arrays;

public enum SingletonEnum {
	instance;
	private final String[] bebidas = { "cerveza", "agua" };
	
	private SingletonEnum() {
		System.out.println("Singleton(): inicializando la instancia");
	}
    
    public void imprimirBebidas() {
        System.out.println(Arrays.toString(bebidas));
    }
    
    public void hacerAlgo()	{
		System.out.println("hacer algo(): el SingletonEnum hace algo!");
	}
}
