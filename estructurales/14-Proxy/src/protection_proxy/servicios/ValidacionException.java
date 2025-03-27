package estructurales.proxy.protection_proxy.servicios;

public class ValidacionException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String defaultMsg = "Error de validacion";
	
	public ValidacionException() {
		super(defaultMsg);
	}

	public ValidacionException(String msg) {
		super(msg);		
	}

	public ValidacionException(Throwable excep) {
		super(excep);		
	}

	public ValidacionException(String msg, Throwable excep) {
		super(msg, excep);		
	}

}
