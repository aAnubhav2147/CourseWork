package lab1;
import java.util.Scanner;
//import java.lang.Math;
import java.util.Random;

public class CIS360L1_SS{
	// Lab Week 1
	//Create a method to execute Sequential Search
			public static int sequentialSearch(int[] tempArr, int key) {
				//Basis the pseudocode
				for(int i = 0; i < tempArr.length; i++) {
					if(tempArr[i] == key) {
						return i;
					}
				}
				return -1;
			}
			
	public static void main(String[] args) {
		//Sequential Search
		//int min = 0; 
		Scanner input = new Scanner(System.in); //Create a scanner object for entering desired values
		Random random = new Random();
		//Scanner keyInput = new Scanner(System.in); //Create a scanner object for entering a desired key value to be found
		System.out.println("Enter a desired array size: ");
		//int size = input.nextInt(random.nextInt()); //Enter a random array size
		int size = input.nextInt();
		System.out.println("Creating array. Please wait.... \n");
		int[] randomArray = new int[size]; //Creates an array basis the array size entered in line
		
		//fill the array with random numbers
		for(int i = 0; i < size; i++) {
			randomArray[i] = random.nextInt(1000); 
			//the number in parenthesis basically generates a number between 0 to the numeral entered
			//This increases the probability of finding the key value input
		}
		
		System.out.println("Enter a desired key value: ");
		//int inputKey = random.nextInt();
		int inputKey = input.nextInt();
		
		//Measure the run time
		System.out.println("\n" + "Sequential Search");
		long runTime;
		long startTime = System.nanoTime();
		System.out.println("The key is located at: " + sequentialSearch(randomArray,inputKey) + "\n");
		long endTime = System.nanoTime();
		runTime = endTime - startTime;
			
		System.out.println("Time for " + size + " is: " + runTime + " nanoseconds");
		input.close(); //Get rid of pesky resource/memory leak warnings
	}
}

		
		
		