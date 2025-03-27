package estructurales.proxy.dinamico.java.client;

import estructurales.proxy.dinamico.java.ProxyDinamico;
import estructurales.proxy.dinamico.java.Resta;
import estructurales.proxy.dinamico.java.RestaImpl;
import estructurales.proxy.dinamico.java.Suma;
import estructurales.proxy.dinamico.java.SumaImpl;

/*
 * Ejemplo de como se un proxy dinámico de Java puede sustituir a diferentes objetos.
 * Primero se muestra el caso de utilizar un servicio de manera normal, sin proxy.
 * A continuación vemos cómo se utiliza el proxy dinámico  para realizar cierto 
 * procesamiento antes, durante y después de la llamada al método del servicio Suma.
 * Finalmente vemos cómo el mismo proxy se puede utilizar para realizar cierto 
 * procesamiento antes, durante y después de la llamada al método del servicio Resta.
 */
public class MainClient {

	public static void main(String[] args) {		
		
		print("-->Uso normal del servicio ofrecido por una clase:");
				
		Suma objSuma = new SumaImpl();		
		int total = 0;		
		total = objSuma.sumarN(2 , 3,  4);
		print("La suma es " + total);
		
		// *****************************************************************
		
		print("\n-->Uso del proxy dinamico para acceder al servicio de Suma:");
		objSuma = (Suma) ProxyDinamico.newProxyInstance(new SumaImpl());			
		total = objSuma.sumarN(8 , 4, 2, 9);
		print("La suma es " + total);
		
		// ******************************************************************
		
		print("\n-->Uso del mismo tipo de proxy dinamico para acceder al servicio de Resta:");
		Resta objResta = (Resta) ProxyDinamico.newProxyInstance(new RestaImpl());							
		total = objResta.restar(2 , 2);
		print("La resta es " + total);
	}
		
	private static void print(String texto) {
		System.out.println(texto);
	}
			
}