package comportamiento.command.planificacion.client.receivers;

import java.util.Random;

/** Receiver **/
public class AgenteExterno {
	
	// Valor arbitrario que establecemos como inefectivo
	private static final int VALOR_INEFECTIVIDAD = 13;
	BaseQuimica baseQuimica;
	
	public AgenteExterno(BaseQuimica baseQuimica) {
		this.baseQuimica = baseQuimica;
	}
	
	public void inyectar() {
		System.out.println("******Inyectando agente externo a la base quimica...");
		baseQuimica.fusionar(this);
	}

	/*
	 * Retorna un valor entre 5000ms y 11000ms
	 */
	public long getSegundosCambiosTemperatura() {
		// nextInt(n) devuelve un valor entre 0 y n-1
		return 1000 * (new Random().nextInt(7) + 5);				
	}

	// Devuelve un float entre 1.0 y 3.0
	public float getValorEnfriamiento() { 
		// nextFloat() devuelve un valor entre 0.0 and 1.0
		return new Random().nextFloat() * 2.0F + 1.0F;
	}

	public boolean esEfectivo() {
		int numero = new Random().nextInt(50) + 1;
		
		if (numero == VALOR_INEFECTIVIDAD) {
			System.out.println("#### Estado agente externo: NO EFECTIVO!!!");
			return false;		
		}	
		System.out.println("#### Estado agente externo: EFECTIVO");
		return true;
	}
	
	public BaseQuimica getBaseQuimica() {
		return this.baseQuimica;
	}
}
