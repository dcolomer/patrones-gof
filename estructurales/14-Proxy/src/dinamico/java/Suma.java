package estructurales.proxy.dinamico.java;
/*
 * Interfaz que tiene que implementar el RealSubject
 */
public interface Suma {
	public int sumar(int a, int b);
	public int sumarN(int a, int... b);
}
