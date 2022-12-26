package lab9;
import java.util.*;

public class NQueens {
	static int count = 0;
	static int solCount = 0;
	static int n = 8;
	static int[] col = new int[n];
		
	//Attempt 1 - Pseudocode verbatim
public static boolean promising(int i) {
		int k;
		boolean change = true;
		
		//k = 1;
		//do {
			for(k = 0; k < i && change; k++) {
				if(col[i] == col[k] || Math.abs(col[i] - col[k]) == i - k) {
					change = false;
				}
			}
		//}while(change);
		
		return change;		
	}
	
public static void queens(int i) {
		int j;
		count++;
		
		if(promising(i)) {
			if(i == n-1) {
				System.out.println(Arrays.toString(col));
				solCount++;
				System.out.println("Solution # " + solCount);
			}
			else {
				for(j = 0; j < n; j++) {
					col[i + 1] = j;
					queens(i + 1);
					//count++;
				}
				
			}
		}
	}
	
	/*Attempt 2 : Using Geeks for Geeks
	public static boolean promising(int[][] board,int row, int col) {
		int i;
		int j;
		boolean change = true;	
	} */


public static void main(String[] args) {
		//int n = 4;
		queens(-1);
		System.out.println("Total recursive calls: " + count);
	}

}
