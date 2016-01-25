import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	// Instance variables
	Scanner keyboard = new Scanner(System.in);
	Bank bank = new Bank();
	boolean exit;
	
	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.runMenu();
	}
	
	public void runMenu() {
		printHeader();
		while(!exit) {
			printMenu();
			int choice = getInput();
			performAction(choice);
		}
	}

	private void performAction(int choice) {
		switch(choice) {
			case 0:
				System.out.println("Gracias por usar nuestra aplicación");
				System.exit(0);
				break;
			case 1:
				createAnAccount();
				break;
			case 2:
				makeADeposit();
				break;
			case 3:
				makeAWithdrawal();
				break;
			case 4:
				listBalance();
				break;
			default:
				System.out.println("Ha ocurrido un error");
		}
	}

	private void listBalance() {
		int account = selectAccount();
		if(account >= 0) {
			System.out.println(bank.getCustomer(account).getAccount());
		} else {
			System.out.println("Cuenta seleccionada no válida");
		}
	}

	private void makeAWithdrawal() {
		int account = selectAccount();
		if(account >= 0) {
			System.out.print("¿Cuanto quieres retirar?: ");
			double amount = 0;
			try {
				amount = Double.parseDouble(keyboard.nextLine());
			} catch(NumberFormatException e) {
				amount = 0;
			}
			bank.getCustomer(account).getAccount().withdraw(amount);
		}
	}

	private void makeADeposit() {
		int account = selectAccount();
		if(account >= 0) {
			System.out.print("¿Cuanto quieres ingresar?: ");
			double amount = 0;
			try {
				amount = Double.parseDouble(keyboard.nextLine());
			} catch(NumberFormatException e) {
				amount = 0;
			}
			bank.getCustomer(account).getAccount().deposit(amount);
		}
	}

	private int selectAccount() {
		ArrayList<Customer> customers = bank.getCustomers();
		if(customers.size() <= 0) {
			System.out.println("No hay clientes en el banco");
			return -1;
		}
		System.out.println("Selecciona cuenta:");
		for(int i = 0; i < customers.size(); i++) {
			System.out.println((i+1) + ") " + customers.get(i).basicInfo());
		}
		int account = 0;
		System.out.print("Por favor seleccione cuenta:");
		try {
			account = Integer.parseInt(keyboard.nextLine()) -1;
		} catch(NumberFormatException e) {
			account = -1;
		}
		
		if(account < 0 || account > customers.size()) {
			account = -1;
		}
		
		return account;
		
	}

	private void createAnAccount() {
		String firstName, lastName, ssn, accountType = "";
		double initialDeposit = 0;
		boolean valid = false;
		
		while(!valid) {
			System.out.print("Seleccione tipo de cuenta (checking/savings): ");
			accountType = keyboard.nextLine();
			if(accountType.equalsIgnoreCase("checking") || accountType.equalsIgnoreCase("savings")) {
				valid = true;
			} else {
				System.out.println("Tipo de cuenta erroneo");
			}
		}
		
		System.out.print("Por favor introduce tu nombre: ");
		firstName = keyboard.nextLine();
		System.out.print("Por favor introduce tu apellido: ");
		lastName = keyboard.nextLine();
		System.out.print("Por favor introduce tu número de la seguridad social: ");
		ssn = keyboard.nextLine();
		
		valid = false;
		
		while(!valid) {
			
			System.out.print("Por favor introduce un depósito inicial:");
			try {
				initialDeposit = Double.parseDouble(keyboard.nextLine());
			} catch(NumberFormatException e) {
				System.out.println("La cantidad debe ser numérica");
			}
			
			if(accountType.equalsIgnoreCase("checking")) {
				
				if(initialDeposit < 100) {
					System.out.println("Las cuentas tipo checking requieren un mínimo de 100 euros");
				} else {
					valid = true;
				}
				
			} else if(accountType.equalsIgnoreCase("saving")) {
				
				if(initialDeposit < 50) {
					System.out.println("Las cuentas tipo saving requieren un mínimo de 50 euros");
				} else {
					valid = true;
				}
				
			}
			
		}
		
		// creamos la cuenta
		Account account;
		if(accountType.equalsIgnoreCase("checking")) {
			account = new Checking(initialDeposit);
		} else {
			account = new Savings(initialDeposit);
		}
		
		Customer customer = new Customer(firstName, lastName, ssn, account);
		bank.addCustomer(customer);
		
	}

	private int getInput() {
		int choice = -1;
		
		do {
			System.out.print("Seleccione opción: ");
			
			try {
				
				choice = Integer.parseInt(keyboard.nextLine());
				
			} catch(NumberFormatException e) {
				
				System.out.println("Selección erronea. Por favor sólo números.");
				
			}
			
			if(choice < 0 || choice > 4) {
				System.out.println("Número fuera de rango. Seleccione de nuevo.");
			}
			
		} while(choice < 0 || choice > 4);
		
		return choice;
	
	}

	private void printMenu() {
		System.out.println("Realiza selección");
		System.out.println("1) Crear nueva cuenta");
		System.out.println("2) Realizar un ingreso");
		System.out.println("3) Realizar un reintegro");
		System.out.println("4) Mostrar balance");
		System.out.println("0) Salir");
	}

	private void printHeader() {
		System.out.println("+--------------------------------+");
		System.out.println("|        Bienvenido a la app     |");
		System.out.println("|               BankApp          |");
		System.out.println("+--------------------------------+");
		
	}
}
