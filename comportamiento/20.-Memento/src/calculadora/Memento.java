package comportamiento.memento.calculadora;

/**
 * Memento Object Implementation
 * 
 * Note that this object implements both interfaces
 */
public class Memento implements MementoToCareTaker, MementoToOriginator {

	private int firstNumber;
	private int secondNumber;

	public Memento(int firstNumber, int secondNumber) {
		this.firstNumber = firstNumber;
		this.secondNumber = secondNumber;
	}

	@Override
	public int getFirstNumber() {
		return firstNumber;
	}

	@Override
	public int getSecondNumber() {
		return secondNumber;
	}
}
