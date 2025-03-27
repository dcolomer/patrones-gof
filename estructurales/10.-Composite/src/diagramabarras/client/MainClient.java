package estructurales.composite.diagramabarras.client;

import estructurales.composite.diagramabarras.Ventana;

public class MainClient {
	
	/*
	 * Arrays con los datos a mostrar en el diagrama.
	 * En una version mas sofisticada se tendrían que
	 * obtener de una fuente externa (fichero, base 
	 * de datos, etc)
	 */
	private static String[] elementosX = new String[] {	
		"Juan", "Teresa", "Alfredo", "Maria", "Luis", 
		"Rafael", "Ismael", "Sonia", "Tomas", "Rosa", 
		"Kim", "Ramon", "Marc", "Laura", "Pilar", 
		"David", "Martin", "Xavi", "Helena", "Dario"
	};
			
	private static float[] elementosY = new float[] { 
		5.5f, 8.0f, 4.0f, 9.0f, 6.25f, 3.5f, 6.0f, 
		10.0f, 7.5f, 5.0f, 6.0f, 2.5f, 4.0f, 5.0f, 
		8.0f, 5.5f, 7.0f, 1.0f, 3.5f, 6.5f	
	};
	
	private static String[] elementosDeRefenciaY = new String[] { 
		"  0", "  1", "  2", "  3", "  4", "  5", "  6", "  7", "  8", "  9", " 10"	
	};
	
	public static void main(String ...args) {
		new Ventana(elementosX, elementosY, elementosDeRefenciaY);		
	}
}
