package estructurales.adapter.dosvias;

public class MotorAdapter implements IMotorGasolina, IMotorElectrico {

	private IMotorGasolina motorG;
	private IMotorElectrico motorE;
	
	private MotorAdapter(IMotorGasolina motorG_) {
		motorG = motorG_;
		System.out.println("Creando una instancia del adaptador para el motor de gasolina");
	}
	
	private MotorAdapter(IMotorElectrico motorE_) {
		motorE = motorE_;
		System.out.println("Creando una instancia del adaptador para el motor electrico");
	}
	
	public static MotorAdapter crear(String tipo) {
		if (tipo.equals("gasolinaF")) {
			return new MotorAdapter(new MotorFuncional());			
		} else if (tipo.equals("gasolinaC")) {
			return new MotorAdapter(new MotorCompeticion());
		} else if (tipo.equals("electrico")) {
			return new MotorAdapter(new MotorElectricoImpl());	
		} else {
			throw new RuntimeException("El tipo de motor especificado no existe");
		}
	}
	
	/* 
	 * Metodos de MotorGasolina
	 */
	
	@Override
	public void encender() {		
		motorG.encender();
	}

	@Override
	public void acelerar() {
		motorG.acelerar();		
	}

	@Override
	public void apagar() {
		motorG.apagar();
	}

	/*
	 * Metodos de motor electrico
	 */
	@Override
	public void conectar() {
		motorE.conectar();		
	}

	@Override
	public void activar() {
		motorE.activar();		
	}

	@Override
	public void aumentarVelocidad() {
		motorE.aumentarVelocidad();		
	}

	@Override
	public void detener() {
		motorE.detener();		
	}

	@Override
	public void desconectar() {
		motorE.desconectar();		
	}

}
 