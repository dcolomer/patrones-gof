package comportamiento.CoR.basico.variante;


public abstract class Handler {
	protected Handler sucesor;
	
	public Handler getSucesor() {
		return sucesor;
	}
	
	public void setSucesor(Handler sucesor)	{
		this.sucesor = sucesor;
	}

	//public abstract void handleRequest(Request request);
	protected abstract boolean handleRequestImpl(Request request);
	
	// método final -> no se puede sobrescribir en las subclases
	public final void handleRequest(Request request) {
		boolean peticionDespachada = this.handleRequestImpl(request);
		if (sucesor != null && !peticionDespachada)
		{
			sucesor.handleRequest(request);
		}
	}
	
	/** Cuando necesitamos que una peticion llegue a 
	 * todos los manejadores */
	/*public final void handleRequest(Request request) {
		this.handleRequestImpl(request);
		if (sucesor != null)
		{
			sucesor.handleRequest(request);
		}
	}*/
		
}
