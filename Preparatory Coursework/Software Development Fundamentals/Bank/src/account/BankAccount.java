package account;

public class BankAccount {
	double balance;
	String owner;
	
	//Deposit
	public void deposit(double amount) {
		balance += amount;
		
	}
	
	//Withdraw
	public void withdraw(double amount) {
		balance -= amount;
		
	}
	

}
