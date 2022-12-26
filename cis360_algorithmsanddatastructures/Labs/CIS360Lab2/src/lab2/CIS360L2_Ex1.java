package lab2;
import java.lang.Math;

public class CIS360L2_Ex1 {

	public static void main(String[] args) {
		//Overhead operators
		int i = 0;
		//int k = 10;
		//int n = 2^k;
		int n = 10;
		int j;
		int count = 0;
		
		for(i = 0; i<n; i++) {
			j = n;
			
			while(j>=1) {
				j = (int) Math.floor(j/2);
				count++; // count the number of times the while loop executes
			}			
		}
		System.out.println(count);
	}

}
