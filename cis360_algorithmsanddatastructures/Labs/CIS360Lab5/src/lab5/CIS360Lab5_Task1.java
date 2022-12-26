package lab5;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class CIS360Lab5_Task1 {
	static int recursiveCount = 0;
	public static int partition(int[] randomArray, int low, int high) {
		int i, j;
		int pivotItem = randomArray[low];
		//high = randomArray.length;
		
		j = low;
		//System.out.println(high);
		for(i = low + 1; i <= high; i++ ) {
			if(randomArray[i] <= pivotItem) {
				j = j + 1;
				int temp1 = randomArray[i];
				randomArray[i] = randomArray[j];
				randomArray[j] = temp1;
			}			
		}
		pivotItem = j;
		int temp2 = randomArray[low];
		randomArray[low] = randomArray[pivotItem];
		randomArray[pivotItem] = temp2;
		return pivotItem;
	}
	
	public static void quickSort(int[] tempArr, int min, int max) {
		int pivotPoint;
		//int recursiveCount = 0;
		if(min < max) {
			pivotPoint = partition(tempArr,min,max);
			quickSort(tempArr,min, pivotPoint - 1);
			quickSort(tempArr,pivotPoint + 1, max);
			//int outCount = recursiveCount++;
			//System.out.println(outCount);
		}
		recursiveCount++;
		//System.out.println("Total recursive calls = " + recursiveCount);
	}

	public static void main(String[] args) {
		int low = 0; 
		Scanner input = new Scanner(System.in); //Create a scanner object for entering desired values
		Random random = new Random();
		//Scanner keyInput = new Scanner(System.in); //Create a scanner object for entering a desired key value to be found
		System.out.println("Enter a desired array size: ");
		int size = input.nextInt();
		int high = size - 1;
		System.out.println("Creating array. Please wait.... \n");
		int[] mainArray = new int[high + 1]; //Creates an array basis the array size entered in line
		
		//fill the array with random numbers
		for(int i = low; i < high; i++) {
					mainArray[i] = random.nextInt(1000); 
					//the number in parenthesis basically generates a number between 0 to the numeral entered
		}
		
		//System.out.print(Arrays.toString(mainArray));
		System.out.println("\n");
		//System.out.print(Arrays.toString(quickSort(randomArray,low,high)));
		quickSort(mainArray,low,high);
		//System.out.println(Arrays.toString(mainArray));
		System.out.println("Total recursive calls(first element) = " + recursiveCount);
		input.close();

	}

}
