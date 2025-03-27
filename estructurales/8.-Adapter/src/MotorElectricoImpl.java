package estructurales.adapter;

public class MotorElectricoImpl implements MotorElectrico {

	private boolean conectado = false;

	public MotorElectricoImpl() {
		System.out.println("Creando instancia de motor electrico");
	}

	@Override
	public void conectar() {
		System.out.println("motor electrico conectado");
		this.conectado = true;
	}

	@Override
	public void activar() {
		if (!conectado) {
            System.out
            	.println("No se puede activar porque no esta conectado el motor electrico");
        } else {
            System.out.println("motor electrico activado");
        }
	}

	@Override
	public void aumentarVelocidad() {
		if (!conectado) {
            System.out.println("No se puede acelerar el motor electrico porque no esta conectado");
        } else {
            System.out.println("motor electrico acelerado");
        }
	}

	@Override
	public void detener() {
		if (!conectado) {
            System.out.println("No se puede detener motor electrico porque no esta conectado");
        } else {
            System.out.println("motor electrico detenido");
        }
	}

	@Override
	public void desconectar() {
		System.out.println("motor electrico desconectado");
        conectado = false;
	}

}
