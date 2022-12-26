package lab3;
import java.util.Scanner;

public class CIS360L3_TaskB {
	//Create a recursive function for calculating the Fibonacci Sequence
	public static int fibSeqRec(int n) {
		if ((n == 0) || (n == 1)) {
			return n;
		}
		else if(n > 1) {
			return fibSeqRec(n-1) + fibSeqRec(n-2);
		}
		else {
			return n;
			//System.out.println(n + " is not a valid input!");
		}
	}

	public static void main(String[] args) {
		//Implement Fibonacci Sequence Recursive
		Scanner input = new Scanner(System.in);
		System.out.println("Enter n for calculation");
		int n = input.nextInt();
		long runTime;
		long startTime = System.currentTimeMillis();
		System.out.println(fibSeqRec(n));
		long endTime = System.currentTimeMillis();
		runTime = endTime - startTime;
		System.out.println("Runtime for: " + n + " is " + runTime + " miliseconds");
		//int calc = (int) runTime/(2^(n/2));
		//System.out.println(calc);
		input.close();
	}

}
