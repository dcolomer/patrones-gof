package estructurales.proxy.dinamico.java;
/*
 * RealSubject: objeto que implementa la interfaz Resta y 
 * que será sustituido (proxeado)
 */
public class RestaImpl implements Resta {
	
	@Override
	public int restar(int a, int b) {
		return a - b;
	}
}
