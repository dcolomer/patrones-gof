package estructurales.adapter;

public class MotorCompeticion extends Motor {

	public MotorCompeticion(){
        System.out.println("Creando una instancia de motor competicion");
    }
	
	@Override
	public void encender() {
		System.out.println("motor competicion encendido");
	}

	@Override
	public void acelerar() {
		System.out.println("motor competicion acelerado");
	}

	@Override
	public void apagar() {
		System.out.println("motor competicion apagado");
	}

}
