package estructurales.adapter.client;

import estructurales.adapter.Motor;
import estructurales.adapter.MotorCompeticion;
import estructurales.adapter.MotorElectricoAdapter;
import estructurales.adapter.MotorFuncional;

public class MainClient {

	public static void main(String[] args) {		
		Motor motor = new MotorFuncional();
		motor.encender();
		motor.acelerar();
		motor.apagar();
		
		motor = new MotorCompeticion();
		motor.encender();
		motor.acelerar();
		motor.apagar();
		
		motor = new MotorElectricoAdapter();
		motor.encender();
		motor.acelerar();
		motor.apagar();
	}

}
