package estructurales.proxy.dinamico.java;
import java.lang.reflect.*;

import javax.naming.OperationNotSupportedException;

/*
 * Proxy.
 * Esta clase monitoriza las llamadas efectuadas a los métodos 
 * del objeto real.
 * Podemos realizar alguna tarea antes y despues de la ejecucion 
 * del método invocado, incluso variar los argumentos enviados al 
 * método o modificar el resultado de su ejecucion para que retorne 
 * un valor alterado. 
 */
public class ProxyDinamico implements InvocationHandler {
	
	private Object realSubject;

	public ProxyDinamico(Object realSubject) {
		this.realSubject = realSubject;
	}

	/*
	 * Lo queremos hacer cada vez que se llame a algún método de alguna clase "RealSubject". 
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable 
	{
		String metodo = method.getName();
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
			Object resultado = method.invoke(realSubject, args);
			
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
	 * Método de conveniencia para hacer que sea más fácil para las
	 * clases cliente obtener un objeto Proxy de Java
	 */
	public static Object newProxyInstance(Object realSubject) {
		
		Class<?> interfaz = realSubject.getClass().getInterfaces()[0]; 
		
		return Proxy.newProxyInstance(
			interfaz.getClassLoader(),
			new Class[] { interfaz }, 
			new ProxyDinamico(realSubject)
		);		
	}
	
	private static void print(String texto) {
		System.out.println(texto);
	}
}
