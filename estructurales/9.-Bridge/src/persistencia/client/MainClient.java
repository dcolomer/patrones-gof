package estructurales.bridge.persistencia.client;

import java.io.PrintStream;

import estructurales.bridge.persistencia.Persistence;
import estructurales.bridge.persistencia.PersistenceImp;
import estructurales.bridge.persistencia.PersistenceType;

public class MainClient {
	
	private static PrintStream o = System.out;
	
	// Referencia que permite acceder a las operaciones 
	// definidas por la "Abstraction"
	private Persistence persistenceAPI;

	public static void main(String[] args) {
		new MainClient();
	}
	
	/*
	 * Constructor
	 */
	public MainClient() {
		// Si invocamos al constructor por defecto
		// se utiliza la implementacion HashMap
		persistenceAPI = new PersistenceImp();		
		test(PersistenceType.InMemory);

		// Cambiamos dinámicamente de implementacion
		persistenceAPI = new PersistenceImp(PersistenceType.FileSystem);		
		test(PersistenceType.FileSystem);
		
		// Volvemos a cambiar de implementacion
		persistenceAPI = new PersistenceImp(PersistenceType.DataBase);		
		test(PersistenceType.DataBase);
	}
	
	/*
	 * Operaciones CRUD para probar los 
	 * diferentes sistemas de persistencia
	 */
	private void test(PersistenceType tipoPersistencia) {
		
		o.println("**************************************************");
		o.println("Probando la persistencia "+tipoPersistencia);
		o.println("**************************************************");
		
		// Creamos un alumno y lo mostramos por pantalla
		Alumno alumno1 = new Alumno(100, "Juan");
		o.println("Alumno creado: ID = "+ alumno1.getId() 
				+ " Nombre = " + alumno1.getNombre());
				
		// Lo grabamos
		String id = persistenceAPI.persist(alumno1);
		o.println("Alumno grabado.");
		
		// Eliminamos la referencia
		alumno1 = null;
				
		// Lo recuperamos y lo mostramos por pantalla
		alumno1 = (Alumno) persistenceAPI.findById(id);
		System.out.println("Alumno recuperado: ID = "+ 
				alumno1.getId() + " Nombre = " + alumno1.getNombre());
		
		// Lo modificamos y lo mostramos por pantalla
		alumno1.setNombre("Eduardo");
		System.out.println("Alumno modificado: ID = "+ 
				alumno1.getId() + " Nombre = " + alumno1.getNombre());
		
		/*
		 * Lo volvemos a grabar
		 */
		//persistenceAPI.deleteById(id);
		id = persistenceAPI.persist(alumno1);
		o.println("Alumno grabado de nuevo.");
	}	

}
