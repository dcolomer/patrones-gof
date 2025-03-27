package estructurales.bridge.persistencia;

/** "RefinedAbstraction" */
public class PersistenceImp implements Persistence {

	PersistenceImplementor implementor;

	/*
	 * Por defecto utilizamos la implementacion HahMap
	 */
	public PersistenceImp() {
		implementor =
			PersistenceFactory.getInstance().create(PersistenceType.InMemory);
	}
	
	/*
	 * Este constructor permite elegir una de las 
	 * implementaciones de persistencia disponibles  
	 */
	public PersistenceImp(PersistenceType tipoPersitencia) {
		implementor =
			PersistenceFactory.getInstance().create(tipoPersitencia);
	}

	@Override
	public String persist(Object object) {
		return Long.toString(implementor.saveObject(object));
	}
	
	@Override
	public Object findById(String objectId) {
		return implementor.getObject(Long.parseLong(objectId));
	}
	
	@Override
	public void deleteById(String id) {
		implementor.deleteObject(Long.parseLong(id));
	}

}
