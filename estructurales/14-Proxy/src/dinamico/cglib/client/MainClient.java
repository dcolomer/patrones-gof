package estructurales.proxy.dinamico.cglib.client;

import estructurales.proxy.dinamico.cglib.*;

/*
 * Ejemplo de uso de proxy dinámico mediante la librería CGLIB. Con
 * CGLIB nuestros RealSubjects no tienen obligatoriamente que implementar
 * interfaz alguna. 
 * Notad que son necesarias las librerias asm.jar y cglib-x.x.x.jar.
 */
public class MainClient {

	public static void main(String[] args) {		
		
		print("-->Uso normal del servicio ofrecido por una clase:");
				
		SumaImpl objSuma = new SumaImpl();		
		int total = 0;		
		total = objSuma.sumarN(2 , 3,  4);
		print("La suma es " + total);
		
		// *****************************************************************
		
		print("\n-->Uso del proxy dinamico para acceder al servicio de Suma:");
		objSuma = (SumaImpl) ProxyDinamico.createProxy(SumaImpl.class);			
		total = objSuma.sumarN(8 , 4, 2, 9);
		print("La suma es " + total);
		
		// ******************************************************************
		
		print("\n-->Uso del mismo tipo de proxy dinamico para acceder al servicio de Resta:");
		RestaImpl objResta = (RestaImpl) ProxyDinamico.createProxy(RestaImpl.class);							
		total = objResta.restar(2 , 2);
		print("La resta es " + total);
	}
		
	private static void print(String texto) {
		System.out.println(texto);
	}
			
}