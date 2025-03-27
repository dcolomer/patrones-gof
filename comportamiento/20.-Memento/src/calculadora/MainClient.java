package comportamiento.memento.calculadora;

public class MainClient {

	public static void main(String[] args) {
		
		// program starts 
		Originator calculator = new OriginatorImp();
		
		// assume user enters two numbers
		calculator.setFirstNumber(10);
		calculator.setSecondNumber(100);
		
		// find result
		System.out.println(calculator.getCalculationResult());
		
		// Store result of this calculation in case of error
		MementoToCareTaker memento = calculator.backupLastCalculation();
		
		// user enters a number
		calculator.setFirstNumber(17);
		
		// user enters a wrong second number and calculates result
		calculator.setSecondNumber(-290);
		
		// calculate result
		System.out.println(calculator.getCalculationResult());
		
		// user hits CTRL + Z to undo last operation and see last result
		calculator.restorePreviousCalculation(memento);
		
		// result restored 
		System.out.println(calculator.getCalculationResult());

	}

}
