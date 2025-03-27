package estructurales.bridge.dibujoAPI;

/** "Abstraction" */
public abstract class Figura {
	
	/*
	 * Obtener la implementación mediante una factoría. 
	 */	
	protected static DibujoAPI dibujoAPI =
		Factoria.getInstance().create("plataforma");
		
	/*
	 * Metodos que pertenecen a la "Abstraction"
	 */
	
	// Multiplicar el radio de la circunferencia por
	// el portecentaje indicado por parametro
	public abstract void redimensionar(double pct);
	
	/*
	 * Metodos que se delegan en el "Implementor"
	 */
	
	// Dibujar un circulo
	public abstract void dibujar();
	
	// Codigo a ejecutar una vez realizado el dibujo,
	// normalmente mostrar un mensaje de finalizacion
	public abstract void esperar();	
			
}