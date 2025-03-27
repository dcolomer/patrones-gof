package estructurales.bridge.persistencia;

/*
 * Factoría implementada como Singleton.
 * Crea un objeto de alguna de las subclases de "Implementor"
 */
class PersistenceFactory {

	static final PersistenceFactory instance = 
		new PersistenceFactory();
	
	static PersistenceFactory getInstance() {
		return instance;
	}
	
	private PersistenceFactory() {
		 		
	}
	
	/*
	 * Crea un objeto de alguna de las subclases de "Implementor".
	 * Notad que no se cachea el tipo de implementor, ya que 
	 * interesa poder invocar a create() en cualquier otro momento
	 * en tiempo de ejecucion para que cree otro tipo de implementor
	 */
	PersistenceImplementor create(PersistenceType tipoPersistencia) {		
		if (tipoPersistencia.equals(PersistenceType.InMemory)) {
			return new InMemoryPersistenceImplementor();
		} else if (tipoPersistencia.equals(PersistenceType.FileSystem)) {
			return new FileSystemPersistenceImplementor();
		} else if (tipoPersistencia.equals(PersistenceType.DataBase)) {
			return new DataBasePersistenceImplementor();
		} else {
			throw new IllegalArgumentException("El tipo de persistencia indicado es erroneo: "+tipoPersistencia);
		}		
	}
	
}