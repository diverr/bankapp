
public class Checking extends Account{
	private static String accountType = "Checking";
	
	Checking(double initialDeposit) {
		super();
		this.setBalance(initialDeposit);
		checkInterest(0);
	}
	
	@Override
	public String toString() {
		return "Tipo de cuenta: " + accountType + " Cuenta\n" +
				"Número de cuenta: " + this.getAccountNumber() + "\n" + 
				"Balance: " + this.getBalance()+ "\n" + 
				"Interés: " + this.getInterest() + "%\n";
	}
}
