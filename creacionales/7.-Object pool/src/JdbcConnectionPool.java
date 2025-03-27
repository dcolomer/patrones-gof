package creacionales.objectpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * SINGLETON
 * 
 * SubClase de ObjectPool especializada en objetos Connection de java.sql
 * Es un Singleton que utiliza la técnica de la clase estática para la 
 * obtención de la instancia Singleton.
 */
public class JdbcConnectionPool extends ObjectPool<Connection> {
	
	// Limites de la caché
	private static final short MIN_CACHEADAS = 0, MAX_CACHEADAS = 20;
	
	// atributos para almacenar los diferentes valores de la conexión
	private String dsn, usr, pwd;
	
	/*
	 * Atributos estáticos necesarios para la clase interna
	 */
	private static DatosConexion dc;
	private static short conexionesCacheadas;
	private static int expirationTime;	

	/*
	 * Constructor privado
	 */
	private JdbcConnectionPool(DatosConexion dc, short conexionesCacheadas, int expirationTime) {
		super(expirationTime);
		if (conexionesCacheadas<MIN_CACHEADAS || 
			conexionesCacheadas>MAX_CACHEADAS) {
			throw new IllegalArgumentException("El valor para la conexiones que " +
					"se deben cachear no esta en el intervalo permitido");
		}
		
		try {
			Class.forName(dc.driver).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.dsn = dc.dsn;
		this.usr = dc.usr;
		this.pwd = dc.pwd;
		
		/*
		 * Si el cliente ha indicado que el el pool se debe
		 * iniciar con conexiones cacheadas.
		 * Hay que advertir que esta comprobación está a nivel 
		 * de subclase en lugar de estar en la superclase.
		 * Esto es asi porque la llamada a crear() es abstracta
		 * en la subclase y en consecuencia se llamaría a crear()
		 * de la subclase cuando aun no estaria construido.
		 */
		if (conexionesCacheadas>MIN_CACHEADAS) {
			for (int i=1; i<=conexionesCacheadas; i++) {				
				disponibles.put(crear(), ahora());
			}
		}
	}

	/*
	 * La clase SingletonHolder se carga en la primera ejecucion de
	 * JdbcConnectionPool.getInstance(), pero no antes.
	 */	
	private static class SingletonHolder {		
		private static ObjectPool<Connection> pool_ = 
			new JdbcConnectionPool(dc, conexionesCacheadas, expirationTime);						
	}
	
	/*
	 * Metodo estatico publico que retona la instancia. Notad que lo que se
	 * devuelve es el atributo estatico de la clase estatica SingletonHolder.
	 * Esto asegura que dos threads no pueden obtener sendas instancias del Singleton.
	 */
	public static ObjectPool<Connection> getInstance(DatosConexion dc_, 
			short conexionesCacheadas_, int expirationTime_) 
	{				
		// Asignamos los parámetros a los atributos estáticos de JdbcConnectionPool
		// para que los tenga disponibles la clase SingletonHolder
		dc = dc_;
		conexionesCacheadas = conexionesCacheadas_;
		expirationTime = expirationTime_; 
		return SingletonHolder.pool_;				
	}
	
	/*
	 * El pool obtiene una conexion del Entorno del recurso
	 */
	@Override
	protected Connection crear() {
		try {
			return (DriverManager.getConnection(dsn, usr, pwd));
		} catch (SQLException e) {
			e.printStackTrace();
			return (null);
		}
	}

	@Override
	protected void expirar(Connection cnn) {
		try {
			cnn.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validar(Connection cnn) {
		try {
			return (!cnn.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
			return (false);
		}
	}		
}
