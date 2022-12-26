package lab1;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class CIS360L1_Binary {
	// Lab Week 1
	//Create a method to execute Binary Search
	public static int binarySearch(int[] tempArr, int low, int high, int key) {
		//instantiate mid
		int mid;
		if (high >= low) {
			//basis the psuedocode calculate the mid value
			mid = (high + low)/2;
			if(key == tempArr[mid]) {
				return mid;
			}
			else if(key < tempArr[mid]) {
				high = mid - 1;
				return binarySearch(tempArr,low,mid-1,key);
			}
			else {
				low = mid + 1;
				return binarySearch(tempArr,mid + 1,high,key);
			}
		} 
		return -1;		
	}
	

	public static void main(String[] args) {
		//Binary Search
		int low = 0; 
		Scanner input = new Scanner(System.in); //Create a scanner object for entering desired values
		Random random = new Random();
		//Scanner keyInput = new Scanner(System.in); //Create a scanner object for entering a desired key value to be found
		System.out.println("Enter a desired array size: ");
		int size = input.nextInt();
		int high = size;
		System.out.println("Creating array. Please wait.... \n");
		int[] randomArray = new int[high]; //Creates an array basis the array size entered in line
		
		//fill the array with random numbers
		for(int i = low; i < high; i++) {
					randomArray[i] = random.nextInt(1000); 
					//the number in parenthesis basically generates a number between 0 to the numeral entered
					//This increases the probability of finding the key value input
		}
		Arrays.sort(randomArray); //Sort the array for Binary Search execution
		System.out.println("Enter a desired key value: ");
		int inputKey = input.nextInt();
					
		//Measure the run time
		System.out.println("\n" +"Binary Search");
		long runTime;
		long startTime = System.nanoTime();
		System.out.println("The key is located at: " + binarySearch(randomArray,low, high,inputKey) + "\n");
		long endTime = System.nanoTime();
		runTime = endTime - startTime;
		
		System.out.println("Time for " + size + " is: " + runTime + " nanoseconds");
		input.close();

	}

}
