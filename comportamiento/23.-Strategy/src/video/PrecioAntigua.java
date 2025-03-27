package comportamiento.strategy.video;

class PrecioAntigua extends Precio {
	
	@Override
	public double calcularImportePrestamo(int diasPrestamo){
        double resultado = 1.5;
        if (diasPrestamo > 3) {
        	resultado += (diasPrestamo - 3) * 1.5;
        }	
        return resultado;
    }
}
