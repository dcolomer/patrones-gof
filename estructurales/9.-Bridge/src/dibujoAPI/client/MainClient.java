package estructurales.bridge.dibujoAPI.client;

import estructurales.bridge.dibujoAPI.Figura;
import estructurales.bridge.dibujoAPI.FiguraCirculo;

/** "Client" */
public class MainClient {
	
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {		
		// Creamos tres circulos
		Figura c1 = new FiguraCirculo(200, 200, 22);
		Figura c2 = new FiguraCirculo(300, 300, 12);
		Figura c3 = new FiguraCirculo(50, 70, 24);
		
		// Aumentamos el radio del segundo circulo
		c1.redimensionar(3);
		
		// Los agregamos a un array de Figura
		Figura[] figuras = new Figura[] { c1, c2, c3 };
				
		// Los pintamos en la pantalla		
		for (Figura figura : figuras) {				
			figura.dibujar();				
		}
		
		// Esperamos a que el usuario cierre la ventana.
		// Podemos invocar al metodo esperar() sobre cualquier circulo; 
		// aqui lo hacemos sobre el primero
		c1.esperar();		
	}
	
}
