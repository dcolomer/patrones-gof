package estructurales.bridge.persistencia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import estructurales.bridge.persistencia.client.Alumno;

/** "ConcreteImplementor" **/
public class InMemoryPersistenceImplementor 
			implements PersistenceImplementor {

	// Estructura para almacenar los alumnos
	private static Map<Integer, Alumno> alumnosBD;
	
	/*
	 * Inicializador estatico para crear las estructuras
	 * de datos y algunos registros de ejemplo
	 */
	static {
		
		// Incializamos la base de datos en memoria
		alumnosBD = new HashMap<Integer, Alumno>();		
		
		// Creamos un Alumno		 
		Alumno alumno=new Alumno(1000, "Miguel");
						
		// Creamos la primera entrada en la base de datos en memoria		 		
		alumnosBD.put(alumno.getId(), alumno);
		
		// Creamos otro Alumno		
		alumno=new Alumno(1001, "Raquel");	
		alumnosBD.put(alumno.getId(), alumno);		
	}
	
	/*
	 * Grabar un Alumno, independientemente de si
	 * existe o no
	 */
	@Override
	public long saveObject(Object object) {
		Alumno alumno = (Alumno) object;
		// Comprobar si existe, en cuyo caso lo borramos
		deleteObject(alumno.getId());						
		// Siempre se crea de nuevo
		crearAlumno(alumno);
		
		// debug
		System.out.println("Listando alumnos...");
		for (Alumno a : listarAlumnos()) {
			System.out.println(a);
		}
		
		return alumno.getId();
	}

	/*
	 * Recuperar el alumno cuyo ID coincide con el parametro 	 
	 */
	@Override
	public Object getObject(long objectId) {
		int intObjectId;
		int IDtope = Integer.MAX_VALUE;
		if (objectId>IDtope) {
			throw new IllegalArgumentException("El ID no puede exceder de "+IDtope);
		} else {
			intObjectId = (int)objectId;	
		}
		if (alumnoExiste(intObjectId)) {
			return alumnosBD.get(intObjectId);							
		}
		return null;
	}

	/*
	 * Borrar el Alumno cuyo ID coincide con el parametro
	 */
	@Override
	public void deleteObject(long objectId) {
		int intObjectId;
		int IDtope = Integer.MAX_VALUE;
		if (objectId>IDtope) {
			throw new IllegalArgumentException("El ID no puede exceder de "+IDtope);
		} else {
			intObjectId = (int)objectId;	
		}
		// Si existe borramos el alumno
		if (alumnoExiste(intObjectId)) {
			System.out.println("borrado alumno - id = " + intObjectId);
			alumnosBD.remove(intObjectId);			
		}	
	}
	
	/*
	 * Metodo privado que añade una nueva entrada en
	 * la base de datos
	 */
	private void crearAlumno(Alumno alumno) {	
		System.out.println("persistiendo Alumno - nombre = " + alumno.getNombre());
		alumnosBD.put(alumno.getId(), alumno);
	}

	/*
	 * Comprobar si el nombre de un Alumno se
	 * encuentra en el Map
	 */
	private boolean alumnoExiste(int objectId) {
		return alumnosBD.containsKey(objectId);
	}
	
	/*
	 * Devolver la lista de alumnos
	 */
	private List<Alumno> listarAlumnos() {
		List<Alumno> alumnos=new ArrayList<Alumno>();
		for (Entry<Integer, Alumno> e: alumnosBD.entrySet()) {
			Alumno Alumno=e.getValue();			
			alumnos.add(Alumno);
		}
		Collections.sort(alumnos);
		return alumnos;
	}
	
}
