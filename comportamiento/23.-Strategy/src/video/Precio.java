package comportamiento.strategy.video;

public abstract class Precio {

	private static Precio normal = new PrecioNormal();
	private static Precio antigua = new PrecioAntigua();
    private static Precio estreno = new PrecioEstreno();    
    
    public abstract double calcularImportePrestamo(int diasPrestamo);
    
    public int getPuntos(int diasPrestamo){
        return 1;
    }
    
    public static Precio getNormal() {
        return normal;
    }
    
    public static Precio getAntigua() {
        return antigua;
    }
    
    public static Precio getEstreno() {
        return estreno;
    }
    
}
