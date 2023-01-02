package loops;

public class Loops {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sum = 0;
		int sumOdd = 0;
		int n = 1;
		
		while(n<=100) {
			sum = sum + n;
			n = n + 1;
		}
		System.out.print("Sum of numbers from 1 to 100 = " + sum + "\n");
		
		for(int x = 1; x<=100; x = x + 2){
			sumOdd = sumOdd + x;
		}
		System.out.print("Sum of odd numbers from 1 to 100 = " + sumOdd + "\n");		
		System.out.println("For loop is a compact version of while loop");
	}		
}
