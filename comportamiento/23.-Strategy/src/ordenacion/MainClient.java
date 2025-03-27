package comportamiento.strategy.ordenacion;

public class MainClient {
	
	private static double[] list = { 
			71.8,9.2,33.4,88.6,4.9,51.32,70.11,72.72,21.29,67.01,
			42.22,55.44,94.00,57.43,54.18,45.77,89.39,37.32,25.39,
			12.02,7.19,56.23,53.66,81.91,92.03,36.34,62.32,40.99,
			22.12,77.33,24.45,34.00,50.43,91.92,97.08,10.11,3.01,
			69.61,5.00,68.10,6.09,73.55,31.43,100.00,82.44,59.33,
			41.91,76.48,61.47,32.34,16.02,44.38,29.55,90.06,8.66,
			35.34,75.06,87.87,27.88,18.12,15.45,20.20,95.00,96.01,
			85.99,79.56,13.77,47.05,11.02,58.55,43.66,78.78,26.00,
			39.32,86.05,14.99,74.45,52.55,98.01,1.11,64.11,84.32,
			99.09,80.58,28.59,66.44,2.88,19.01,60.99,38.98,46.77,
			30.54,65.43,23.20,83.66,93.03,48.43,49.47,17.87,63.49
	};
	
	private Contexto contexto;
	
	public MainClient() {
		contexto = new Contexto();
		contexto.setEstrategia(new EstrategiaBurbuja());
		executeAndShow();		
		// ***********************************************		
		contexto.setEstrategia(new EstrategiaQuickSort());
		executeAndShow();
	}
	
	public static void main(String[] args) {
		new MainClient();		
	}

	private void executeAndShow() {
		long tStart = System.nanoTime();
		contexto.ordenarLista(list);
		long tEnd = System.nanoTime();
		
		long dif = tEnd-tStart;		
		System.out.println("--------------------------------------");		
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i] + " ");
		}			
		System.out.println("\nTiempo de ordenacion: " + dif);		
	}
}
