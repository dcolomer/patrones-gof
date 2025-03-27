package comportamiento.strategy.ordenacion;

public class EstrategiaBurbuja implements Estrategia {
	public void ordenar(double[] lista) {
		int newLowest = 0;            // index of first comparison
	    int newHighest = lista.length-1;  // index of last comparison
	  
	    while (newLowest < newHighest) {
	        int highest = newHighest;
	        int lowest  = newLowest;
	        newLowest = lista.length;    // start higher than any legal index
	        for (int i=lowest; i<highest; i++) {
	            if (lista[i] > lista[i+1]) {
	               // exchange elements
	               double temp = lista[i];  
	               lista[i] = lista[i+1];  
	               lista[i+1] = temp;
	               if (i<newLowest) {
	                   newLowest = i-1;
	                   if (newLowest < 0) {
	                       newLowest = 0;
	                   }
	               } else if (i>newHighest) {
	                   newHighest = i+1;
	               }
	            }
	        }
	    }
	}
}
