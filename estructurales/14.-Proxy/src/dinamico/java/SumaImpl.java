package estructurales.proxy.dinamico.java;
/*
 * RealSubject: objeto que implementa la interfaz Suma y 
 * que será sustituido (proxeado)
 */
public class SumaImpl implements Suma {
	@Override
	public int sumarN(int a, int... b) {
		
		int tmp = 0;
		for (int i=0; i<b.length; i++) {		
			tmp += b[i];
		}
		return a + tmp;
	}

	@Override
	public int sumar(int a, int b) {		
		return a + b;
	}
}
