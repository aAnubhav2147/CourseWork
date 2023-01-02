package primes;

import java.util.*;

public class Primes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a positive number: ");
		int x = input.nextInt();
		int factor = 2;
		
		while(x%factor != 0) {
			factor++;
		}
		if(factor == x) {
			System.out.println("the number is prime");
		}
		else {
			System.out.println("the number is not prime and the factor is " + factor);
		}

	}

}
