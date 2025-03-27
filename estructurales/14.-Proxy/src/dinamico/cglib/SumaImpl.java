package estructurales.proxy.dinamico.cglib;
/*
 * RealSubject: objeto que será sustituido (proxeado)
 */
public class SumaImpl {
	
	public int sumarN(int a, int... b) {
		
		int tmp = 0;
		for (int i=0; i<b.length; i++) {		
			tmp += b[i];
		}
		return a + tmp;
	}
	
	public int sumar(int a, int b) {		
		return a + b;
	}
}
