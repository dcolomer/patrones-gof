package comportamiento.memento.calculadora;

/**
 * Originator Implementation
 */
public class OriginatorImp implements Originator {

	private int firstNumber;
	private int secondNumber;

	@Override
	public MementoToCareTaker backupLastCalculation() {
		// create a memento object used for restoring two numbers
		return new Memento(firstNumber,secondNumber);
	}

	@Override
	public int getCalculationResult() {
		// result is adding two numbers
		return firstNumber + secondNumber;
	}

	@Override
	public void restorePreviousCalculation(MementoToCareTaker memento) {
		this.firstNumber = ((MementoToOriginator)memento).getFirstNumber();
		this.secondNumber = ((MementoToOriginator)memento).getSecondNumber();
	}

	@Override
	public void setFirstNumber(int firstNumber) {
		this.firstNumber =  firstNumber;
	}

	@Override
	public void setSecondNumber(int secondNumber) {
		this.secondNumber = secondNumber;
	}
}
