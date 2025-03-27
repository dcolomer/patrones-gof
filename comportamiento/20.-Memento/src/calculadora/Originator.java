package comportamiento.memento.calculadora;

/**
 * Originator Interface
 */
public interface Originator {

	// Create Memento 
	public MementoToCareTaker backupLastCalculation();
	
	// setMemento
	public void restorePreviousCalculation(MementoToCareTaker memento);
	
	// Actual Services Provided by the originator 
	public int getCalculationResult();
	public void setFirstNumber(int firstNumber);
	public void setSecondNumber(int secondNumber);
}
