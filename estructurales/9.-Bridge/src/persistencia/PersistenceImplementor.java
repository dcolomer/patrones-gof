package estructurales.bridge.persistencia;

/** "Implementor" **/
interface PersistenceImplementor {

	public long saveObject(Object object);
	
	public Object getObject(long objectId);
	
	public void deleteObject(long objectId);	
	
}