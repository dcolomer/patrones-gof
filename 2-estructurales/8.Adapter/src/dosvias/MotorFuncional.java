package estructurales.adapter.dosvias;

public class MotorFuncional implements IMotorGasolina {

	public MotorFuncional(){
        System.out.println("Creando una instancia de motor funcional");
    }
	
	@Override
	public void encender() {
		System.out.println("motor funcional encendido");
	}

	@Override
	public void acelerar() {
		System.out.println("motor funcional acelerado");
	}

	@Override
	public void apagar() {
		System.out.println("motor funcional apagado");
	}

}
