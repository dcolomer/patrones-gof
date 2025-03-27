package estructurales.bridge.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** "ConcreteImplementor" */
class FileSystemPersistenceImplementor
		implements PersistenceImplementor {
				
	@Override
	public long saveObject(Object object) {
		long fileId = System.currentTimeMillis();
		
		// Crear un fichero cuyo nombre sera la marca de hora actual
		// EL DIRECTORIO persistence DEBE EXISTIR!! 
		File f = new File("c://persistence/"+Long.toString(fileId));
	
		// write file to Stream		
		writeObjectToFile(f, object);		
		
		return fileId;	
	}
	
	@Override
	public Object getObject(long objectId) {		
		File f = new File("c://persistence/"+Long.toString(objectId));		
		return readObjectFromFile(f);			
	}

	@Override
	public void deleteObject(long objectId) {
		File f = new File("c://persistence/"+Long.toString(objectId));		
		f.delete();				
	}
	
	/* METODOS PRIVADOS */
	
	/*
	 * Abrir el fichero recibido por parametro,
	 * leer el objeto en él almacenado y cerrar 
	 * el fichero. Finalmente devolver el objeto.
	 */
	private Object readObjectFromFile(File f) {
		try {
			ObjectInputStream in =
			    new ObjectInputStream(
			        new FileInputStream(f));
			
            Object obj = in.readObject();
            
            in.close();            
            return obj;
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Serializar el objeto y guardarlo en un fichero
	 */
	private void writeObjectToFile(File f, Object object) {		
		try {
			ObjectOutputStream out =
			    new ObjectOutputStream(
			        new FileOutputStream(f));
			out.writeObject(object);
            out.close();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

}
