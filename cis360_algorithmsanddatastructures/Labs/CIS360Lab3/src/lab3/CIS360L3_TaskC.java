package lab3;
import java.util.Scanner;

public class CIS360L3_TaskC {
	//Create an iterative function for calculating the Fibonacci Sequence
	public static int fibSeqIter(int n) {
		int[] fib = new int[n + 2];
		int i = 0;
		
		fib[0] = 0;
		fib[1] = 1;
		
		for(i = 2; i <= n; i++) {
			fib[i] = fib[i-1] + fib[i-2];
		}
		
		return fib[n];
	}

	public static void main(String[] args) {
		//Implement Fibonacci Sequence Iterative
		Scanner input = new Scanner(System.in);
		System.out.println("Enter n for calculation");
		int n = input.nextInt();
		long runTime;
		long startTime = System.nanoTime();
		System.out.println(fibSeqIter(n));
		long endTime = System.nanoTime();
		runTime = endTime - startTime;
		System.out.println("Runtime for: " + n + " is " + runTime + " nanoseconds");
		input.close();
	}

}
