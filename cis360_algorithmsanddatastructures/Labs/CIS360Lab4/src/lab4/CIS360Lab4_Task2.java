package lab4;
import java.util.*;

public class CIS360Lab4_Task2 {
	public static int trinarySearch(int[] tempArr, int low, int high, int target) {
		//declare two middle term variables
		int mid1 = 0;
		int mid2 = 0;
		
		if(low <= high) {
			mid1 = low +  (high - low)/3;
			mid2 = high - (high - low)/3;
		}
		else {
			return -1;
		}
		
		//base cases
		if(target == tempArr[mid1]) {
			return mid1;
		}
		else if(target == tempArr[mid2]) {
			return mid2;
		}
		
		//cases for checking the mid-points recursively
		if(target < tempArr[mid1]) {
			high = mid1 - 1;
			return trinarySearch(tempArr,low,mid1 - 1,target);
		}
		else if(target > tempArr[mid2]) {
			low = mid2 + 1;
			return trinarySearch(tempArr,mid2 + 1,high,target);
		}
		else if(target > tempArr[mid1] && target < tempArr[mid2]) {
			low = mid1 + 1;
			high = mid2 - 1;
			return trinarySearch(tempArr,mid1 + 1,mid2 - 1,target);
		}
		
		return -1;
	}

	public static void main(String[] args) {
		// Lab week 4
		//Ternary Search
		
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
					randomArray[i] = random.nextInt(100); 
					//the number in parenthesis basically generates a number between 0 to the numeral entered
					//This increases the probability of finding the key value input
		}
		
		Arrays.sort(randomArray); //Sort the array for Ternary Search execution
		System.out.print(Arrays.toString(randomArray));
		System.out.println("\n");
		System.out.println("Enter a desired target value: ");
		int inputKey = input.nextInt();
		System.out.println("The key is located at: " + trinarySearch(randomArray,low, high,inputKey) + "\n");
		input.close();
	}

}
