package creacionales.builder.citas;

public class FaltaInfoException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String MENSAJE = 
		"La cita no se puede crear porque falta informacion";
	
	public static final int FECHA_INI_REQUERIDA = 1;
	public static final int FECHA_FIN_REQUERIDA = 2;
	public static final int DESCRIPCION_REQUERIDA = 4;
	public static final int ASISTENTES_REQUERIDOS = 8;
	public static final int LOCALIZACION_REQUERIDA = 16;
	
	private int faltaInfo;

	public FaltaInfoException(int elementosRequeridos) {
		super(MENSAJE);
		faltaInfo = elementosRequeridos;
	}

	public int getFaltaInfo() {
		return faltaInfo;
	}
}
