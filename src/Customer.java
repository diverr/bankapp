
public class Customer {

	private String firstName;
	private String lastName;
	private String ssn;
	private Account account;

	Customer(String firstName, String lastName, String ssn, Account account) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.account = account;
	}
	
	@Override
	public String toString() {
		return "Información de Cliente\n" + 
				"Nombre: " + firstName + "\n" +
				"Apellidos: " + lastName + "\n" +
				"SSN: " + ssn + "\n" +
				"Cuenta: " + account + "\n";
				
				
	}
	
	public String basicInfo() {
		return "Nombre: " + firstName + 
				" Apellidos: " + lastName + 
				" SSN: " + ssn + 
				" Cuenta: " + account.getAccountNumber();
				
				
	}
	
	public Account getAccount() {
		return account;
	}
	
}
