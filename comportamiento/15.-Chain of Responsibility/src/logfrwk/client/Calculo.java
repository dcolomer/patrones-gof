package comportamiento.CoR.logfrwk.client;

import comportamiento.CoR.logfrwk.wrapper.Logger;

import static comportamiento.CoR.logfrwk.core.CtesLog.*;

public class Calculo {
	
	private static final Logger log = new Logger(Calculo.class);	
	
	public Calculo() {
		log.configurar(TRAZA, FICHERO);		
	}
	
	public double sumar(int sumando1, int sumando2) {
		
		log.traza("Primer argumento pasado al metodo sumar(): " + sumando1);
		log.traza("Segundo argumento pasado al metodo sumar(): " + sumando2);
						
		long totalSuma = sumando1 + sumando2;
		if ((sumando1 > 0 && sumando2 > Integer.MAX_VALUE - sumando1) || 
				(sumando1 < 0 && sumando2 < Integer.MIN_VALUE - sumando1)) 
		{
			log.error("Se ha producido desbordamiento al realizar la suma! Resultado: " + totalSuma);
			
			log.info("Haciendo un cast a long para evitar el desbordamiento...");
			totalSuma = (long) sumando1 + sumando2;
	    }
		
		log.traza("La suma es: " + totalSuma);
		
		return totalSuma;
	}
	
	/*
	 * Los ordenadores no pueden representar todos los numeros reales.
	 * La logica de punto flotante es particularmente mala para
	 * los calculos monetarios, ya que es imposible representar 0.1
	 * o cualquier potencia negativa de 10 (1/10, 1/100, etc...) 
	 * exactamente como un fraccion de longitud fija.
	 * Para calculos monetarios o similares es mejor utilizar int,
	 * long, o la clase BigDecimal. Podriamos hacer lo siguiente:
	 * 
	 * String strMinuendo = String.valueOf(minuendo);
	 * String strSustraendo = String.valueOf(sustraendo);
	 * // Lo mejor es usar String's para el constructor
	 * BigDecimal BDminuendo = new BigDecimal(strMinuendo);
	 * BigDecimal BDsustraendo = new BigDecimal(strSustraendo);
	 *
	 * return BDminuendo.subtract(BDsustraendo).doubleValue(); 
	 */
	public double restar(double minuendo, double sustraendo) {
		
		log.traza("Primer argumento pasado al metodo restar(): " + minuendo);
		log.traza("Segundo argumento pasado al metodo restar(): " + sustraendo);
		
		double totalResta = minuendo - sustraendo;
		
		log.debug("Evitar el uso de float y double donde " +
			"se requiera precision: " + totalResta);
		
		return totalResta;		
	}
}
