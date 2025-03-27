package comportamiento.strategy.video;

class PrecioEstreno extends Precio {
	
	@Override
	public double calcularImportePrestamo(int diasPrestamo) {
        return diasPrestamo * 3;
    }
	
	@Override
	public int getPuntos(int diasPrestamo) {
        return (diasPrestamo > 1) ? 1 : 2;
    }

}
