package estructurales.facade.subsistema;

public class FachadaSubsistema {
	private String nombre;
	private String apellidos;
	private String direc;
	private String ciudad;
	private String provincia;
	private TarjetaTipo tipo;
	private String numero;
	private String fechaCaducidad;

	private static final FachadaSubsistema instance = 
		new FachadaSubsistema();
	
	private FachadaSubsistema() {
		
	}
	
	public static FachadaSubsistema getInstance() {    
		return instance;
	}

	public boolean guardarDatosCliente() {
		Direccion direccion;
		Usuario usuario;
		Tarjeta tarjeta;

		boolean datosValidos = true;
		String megError = "";

		/*
		 * El código cliente no tiene constancia de ninguna 
		 * de las operaciones del subsistema.
		 */
		
		/*
		 * Validaciones
		 */
		usuario = new Usuario(nombre, apellidos);
		if (!usuario.esValido()) {
			datosValidos = false;
			megError = "Nombre o apellidos incorrectos";
		}

		direccion = new Direccion(direc, ciudad, provincia);
		if (!direccion.esValido()) {
			datosValidos = false;
			megError = "Direccion/Ciudad/Provincia incorrectas";
		}

		tarjeta = new Tarjeta(tipo, numero, fechaCaducidad);
		if (!tarjeta.esValido()) {
			datosValidos = false;
			megError = "Datos de la tarjeta incorrectos";
		}

		if (!datosValidos) {
			System.out.println(megError);
			return false;
		}

		/*
		 * Almacenamiento
		 */
		if (direccion.guardarDatos() && 
				usuario.guardarDatos() && tarjeta.guardarDatos()) 
		{
			return true;
		} else {
			return false;
		}

	}

	/*
	 * Setters invocados por el código cliente
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setDireccion(String direccion) {
		this.direc = direccion;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public void setTipo(TarjetaTipo tipo) {
		this.tipo = tipo;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
}
