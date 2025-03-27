package comportamiento.CoR.basico;

public class ConcreteHandlerCero extends Handler {
	private String nomClase = this.getClass().getSimpleName();
	
	public void handleRequest(Request request) 	{
		
		// Si la peticion puede ser procesada por esta clase
		if (request.getValor() == 0) { 
			System.out.println("Valores cero son manejados por " 
					+ nomClase + ":");
			System.out.println("\t" + nomClase + ".HandleRequest : " 
					+ request.getValor());
		} else { // Si no, la pasamos al siguiente manejador
			getSucesor().handleRequest(request);
		}
	}
 }