package comportamiento.CoR.solicitud_compra;

public class Peticion {

	private int ID;
	private String descripcion;
	private double importe;
	private String msgEstado;

	public Peticion(int id, String desc, double imp) {
		ID = id;
		descripcion = desc;
		importe = imp;
	}

	public double getImporte() {
		return importe;
	}
	
	public void setImporte(double importe) {
		this.importe = importe;
	}	

	public String getMsgEstado() {
		return msgEstado;
	}

	public void setMsgEstado(String msgEstado) {
		this.msgEstado = msgEstado;
	}

	@Override
	public String toString() {
		return msgEstado + "\n" + 
			"[ID=" + ID + ", descripcion=" + descripcion + ", importe=" + importe + "]" + "\n" +
			"**************************************************************************";
	}	
}
