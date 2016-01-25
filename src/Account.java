
public class Account {
	private double balance = 0;
	private double interest = 0.02;
	private int accountNumber;
	private static int numberOfAccounts = 1000000;
	
	Account() {
		accountNumber = numberOfAccounts++;
	}
	
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getInterest() {
		return interest * 100;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public void withdraw(double amount) {
		if(amount + 5 > balance) {
			System.out.println("No tienes fondos suficientes");
			return;
		}
		
		balance -= amount + 5;
		
		checkInterest(0);
		
		System.out.println("Has hecho un reintegro de " + amount + " euros con un recargo de 5 euros");
		System.out.println("Tienes un balande de " + balance);
	}
	
	public void deposit(double amount) {
		if(amount <= 0) {
			System.out.println("Tienes que hacer un ingreso positivo");
			return;
		}
		
		checkInterest(amount);
		
		amount = amount + amount * interest;
		balance += amount;
		
		System.out.println("Has hecho un depósito de 5 euros con un ratio de interés de " + (interest * 100) + "%" );
		System.out.println("Tienes un balance de " + balance);
	}
	
	protected void checkInterest(double amount) {
		if(balance + amount > 10000) {
			interest = 0.05;
		} else {
			interest = 0.02;
		}
	}
	
}
