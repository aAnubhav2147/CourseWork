package lab5;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class CIS360Lab5_Task2 {
	static int recursiveCount = 0;
	public static int partition(int[] randomArray, int low, int high) {
		int i, j;
		int pivotItem = randomArray[low];
		
		
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
	
	public static int medianPiv(int[] testArr,int findx, int lindx) {
		int elem1 = testArr[findx];
		//int elemen = testArr[lindx-1];
		int elemen = testArr[testArr.length-1];
		
		//int midelement = (findx + lindx)/2;
		int midelement = (lindx)/2;
		
		int[] sortArr = {testArr[findx],testArr[midelement],testArr[lindx]};
		Arrays.sort(sortArr);
		
		int midValue = sortArr[1];
		int temp = testArr[findx];
		testArr[findx] = midValue;
		if(midValue == testArr[findx]) {
			//swap(testArr,temp,lindx);
			testArr[findx] = temp;
		}else if(midValue == testArr[midelement]) {
			//swap(testArr,temp,midelement);
			testArr[midelement] = temp;
		}
		return partition(testArr,findx,lindx);		
		
	}
	
	
	public static int medianMethod(int arr[], int low, int high) {
		int mid = (low + high)/2;
		int pivotIndex = arr[mid];
		if (low >= high)
			return arr[low];

		if (low < high) {

			pivotIndex = medianPiv(arr, low, high);

			partition(arr, low, high);

		}
		
		return pivotIndex;
	}
	
	public static void medianQuickSort(int[] tempArr, int min, int max) {
		int pivotPoint;
		//int median = medianMethod(tempArr,min,max);
		//int recursiveCount = 0;
		if(min < max) {
			pivotPoint = medianMethod(tempArr,min,max);
			medianQuickSort(tempArr,min, pivotPoint - 1);
			medianQuickSort(tempArr,pivotPoint + 1, max);
			//int outCount = recursiveCount++;
			//System.out.println(outCount);
		}
		recursiveCount++;
	}
	
	/*
	public static void swap(int[] testArr, int idx1, int idx2) {
		int tmp1 = testArr[idx1];
		testArr[idx1] = testArr[idx2];
		testArr[idx2] = tmp1;
	} 
	
	public static int medianMethod(int[] randArray,int findx, int lindx) {
		int center = (findx + lindx)/2 ;
		
		if(randArray[findx] > randArray[center]) {
			swap(randArray,findx,center);
		}
		
		else if(randArray[findx] > randArray[lindx]) {
			swap(randArray,findx,lindx);
		}
		
		else if(randArray[center] > randArray[lindx]) {
			swap(randArray,center,lindx);
		}
		
		swap(randArray,center,lindx-1);
		return randArray[lindx-1];
		
	} */

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
		medianQuickSort(mainArray,low,high);
		//System.out.println(Arrays.toString(mainArray));
		System.out.println("Total recursive calls(median) = " + recursiveCount);
		
		input.close();

	}

}
