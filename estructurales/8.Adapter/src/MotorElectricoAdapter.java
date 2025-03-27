package estructurales.adapter;

public class MotorElectricoAdapter extends Motor {

	MotorElectrico motorE;
	
	public MotorElectricoAdapter() {
		motorE = new MotorElectricoImpl();
		System.out.println("Creando una instancia del adaptador del motor electrico");
	}
	
	@Override
	public void encender() {		
		motorE.conectar();
		motorE.activar();
	}

	@Override
	public void acelerar() {
		motorE.aumentarVelocidad();		
	}

	@Override
	public void apagar() {
		motorE.detener();
		motorE.desconectar();
	}

}
 