package comportamiento.strategy.video;

class PrecioNormal extends Precio {
	
	@Override
	public double calcularImportePrestamo(int diasPrestamo) {
        double resultado = 2;
        if (diasPrestamo > 2) {
        	resultado += (diasPrestamo - 2) * 1.5;
        }	
        return resultado;
    }
}
