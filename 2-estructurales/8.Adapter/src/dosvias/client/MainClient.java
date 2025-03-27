package estructurales.adapter.dosvias.client;

import estructurales.adapter.dosvias.IMotorElectrico;
import estructurales.adapter.dosvias.IMotorGasolina;
import estructurales.adapter.dosvias.MotorAdapter;

public class MainClient {

	public static void main(String[] args) {
		
		IMotorGasolina motorFuncional = MotorAdapter.crear("gasolinaF");
		motorFuncional.encender();
		motorFuncional.acelerar();
		motorFuncional.apagar();
		
		IMotorGasolina motorCompeticion = MotorAdapter.crear("gasolinaC");
		motorCompeticion.encender();
		motorCompeticion.acelerar();
		motorCompeticion.apagar();
		
		IMotorElectrico motorElectrico = MotorAdapter.crear("electrico");
		motorElectrico.conectar();
		motorElectrico.activar();
		motorElectrico.aumentarVelocidad();
		motorElectrico.detener();
		motorElectrico.desconectar();
		
		/*
		 * Peligroso, no se recomienta utilizar una 
		 * referencia del tipo MotorAdapter: la llamada 
		 * a conectar() fallará porque hemos creado un 
		 * motor de gasolina y conectar() es un metodo 
		 * de los motores electricos.
		 
		MotorAdapter todo = MotorAdapter.crear("gasolinaF");
		todo.encender();
		todo.conectar();*/
	}

}
