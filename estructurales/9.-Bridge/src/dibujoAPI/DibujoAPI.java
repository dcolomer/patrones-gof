package estructurales.bridge.dibujoAPI;

/** "Implementor" */
interface DibujoAPI {
		
	static final int FRAME_WIDTH = 450;
	static final int FRAME_HEIGHT = 450;
			
	public void dibujarCirculo(int x, int y, int radio);	
	public void esperar();
}
