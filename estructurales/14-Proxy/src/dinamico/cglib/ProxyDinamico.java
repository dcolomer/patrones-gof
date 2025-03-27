package estructurales.proxy.dinamico.cglib;
import java.lang.reflect.*;

import javax.naming.OperationNotSupportedException;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/*
 * Proxy.
 * Esta clase implementa la interfaz MethodInterceptor de CGLIB, 
 * lo cual hace que deba implementar el método intercept(). Este
 * método es similar al método invoke() de la interfaz 
 * java.lang.reflect.InovationHandler.
 * 
 * monitoriza las llamadas efectuadas a los métodos 
 * del objeto real.
 * Podemos realizar alguna tarea antes y despues de la ejecucion 
 * del método invocado, incluso variar los argumentos enviados al 
 * método o modificar el resultado de su ejecucion para que retorne 
 * un valor alterado. 
 */
public class ProxyDinamico implements MethodInterceptor {
	
	public Object intercept(Object realSubject, Method met,
			Object[] args, MethodProxy methodProxy)
                  throws Throwable
    {
		String metodo = met.getName();
		print("Entrando en el metodo " + metodo);
		
		// Tenemos control para decidir qué métodos de un objeto se van a monitorizar
		// en este caso exclusivamente los metodos sumarN() y restar() pero no suma()
		if (metodo.equals("sumarN") ||
				metodo.equals("restar"))
		{			
			// Tenemos acceso a los parametros
			//System.out.println("parametro: " + args[0]);
			mostrarParametros(args);
			// Llamamos al RealSubject
			Object resultado = methodProxy.invokeSuper(realSubject, args);
			
			print("resultado = " + resultado);		
			print("Saliendo del metodo " + metodo);
	
			// Devolvemos el resultado retornado por el RealSubject aunque
			// podriamos alterarlo.
			return resultado;
		} else
			throw new OperationNotSupportedException("Metodo no soportado por el proxy");
				
    }	

	private void mostrarParametros(Object[] args) {
		print("parametro num. 1: " + args[0]);
		if (args.length == 2 && args[1].getClass().getSimpleName().equals("int[]")) 
		{			
			int[] segundoParam = (int[]) args[1];
			for (int i = 0; i < segundoParam.length; i++) {
				print("parametro num. " + (i+2) + ": " + segundoParam[i]);
			}
		} else {
			print("parametro num. 2: " + args[1]);
		}			
	}

	/*
	 * Método de conveniencia que proporciona un proxy para la clase suministrada 
	 * por parámetro. Notad que se utiliza la clase Enhancer de CGLIB, a la cual:
	 * 
	 * -Mediante el método setSuperClass() se le establece la clase del RealSubject 
	 * cuyos métodos se tienen que interceptar.
	 * 
	 * -Con el método setCallBack() se establece una instancia del objeto que 
	 * queremos que realice el trabajado de monitorización sobre el RealSubject, 
	 * esto es, nuestro proxy.
	 */
	public static Object createProxy(Class<?> claseRealSubject) {
		Enhancer e = new Enhancer();
		e.setSuperclass(claseRealSubject);
		try {
			e.setCallback(ProxyDinamico.class.newInstance());
		} catch (InstantiationException ex1) {			
			ex1.printStackTrace();
		} catch (IllegalAccessException ex1) {			
			ex1.printStackTrace();
		}					
		return e.create();
	}
	
	
	private static void print(String texto) {
		System.out.println(texto);
	}
}
