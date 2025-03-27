package comportamiento.state.interruptor;

/*
 * Objeto de contexto.
 * El comportamiento del interruptor al encenderse y al apagarse
 * se delega en la correspondiente subclase "estado", por lo que 
 * aqui queda un código limpio y sencillo
 */
public class Interruptor {

    private Estado estado;
    
    public Interruptor(Estado estado) {
    	this.estado = estado;
    }
    
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public void conmutar() {    	
        estado.conmutar(this);
    }

}
