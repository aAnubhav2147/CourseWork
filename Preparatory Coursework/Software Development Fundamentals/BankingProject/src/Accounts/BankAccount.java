package Accounts;

import customer.Customer;

public class BankAccount {
	double balance;
	//Customer whose bank account this is
	Customer customer;
	
	//number of accounts being made. Shared across all instances
	static int counter = 10000;
	//Instance variable of account number
	int accountNumber;
	
	BankAccount(String name, int age){
		balance = 0;
		customer = new Customer(name,age);
		counter++;
		accountNumber = counter; //first bank account created will have account number = 10000
	}
	
	public static void main(String[] args) {
		BankAccount a1 = new BankAccount("x",35);
		BankAccount a2 = new BankAccount("Anubhav",28);
		System.out.println("Person: " + a1.customer.getName() + " " + "Account Number: " + a1.accountNumber);
		System.out.println("Person: " + a2.customer.getName() + " " + "Account Number: " + a2.accountNumber);
	}

}
