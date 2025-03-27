package comportamiento.CoR.basico;

public abstract class Handler {
	protected Handler sucesor;
	
	public Handler getSucesor() {
		return sucesor;
	}
	
	public void setSucesor(Handler sucesor)	{
		this.sucesor = sucesor;
	}

	public abstract void handleRequest(Request request);
}
