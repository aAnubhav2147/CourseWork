package lab3;
import java.util.*;
//import java.lang.Math;

public class CIS360L3_TaskA {
	
	//Create a Binary Search algorithm to be used later
	/*
	public static int binarySearch(int[] tempArr, int low, int high, int key) {
		//instantiate mid
		int mid;
		if (high >= low) {
			//basis the pseudo code calculate the mid value
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
	
	*/	
		
		//int[] temp_arr = Arrays.copyOf(int_arr, arr_size);
		//Arrays.sort(temp_arr);
		//Random random = new Random();
		//int random_min = temp_arr[0];
		
		//Original Attempt using the binarySearch method created above.
		//Problem with the approach -> if n = 4, it's giving the output as the 5 smallest numbers. 
		//Unable to troubleshoot where the indexing mismatch lies
		
		/*

		for(int i = 0; i<arr_size; i++) {
			if (binarySearch(temp_arr,0,n,int_arr[i])>=-1) {
				System.out.print(int_arr[i] + " ");
			}
		}
		
		*/
	
	public static void printNSmallest(int[] int_arr,int arr_size,int m) {
		
		arr_size = int_arr.length;
		
		if(arr_size <= 2 || m >= arr_size) {
			//If array size is insufficient or if the provided m is out of index -> terminate
			System.out.println("Insufficient number of terms OR invalid entry for indexing!");
			return;
		}
		
		for(int i = m; i < arr_size; i++ ) {
			//Set minimum index to be later used for positional referencing and swapping
			int minIndex = 0;
			for(int j = 0; j < m; j++) {
				if(int_arr[j] > int_arr[minIndex]) {
					//Use this if condition to do the first comparison against the first element
					minIndex = j; //the new pointer position becomes the new min and forward reference
					int_arr[minIndex] = int_arr[j];
				}
			}
			//Swap if the the original pointer is pointing to a value less than an array element
			//Else the positional and index tracking won't be possible for the upcoming iterations
			if(int_arr[minIndex] > int_arr[i]) {
				int pos = int_arr[minIndex];
				int_arr[minIndex] = int_arr[i];
				pos = int_arr[i];
			}
			
		}
		
		for(int k = 0; k < m; k++) {
			System.out.print(int_arr[k] + " ");
		}
		
	}

	public static void main(String[] args) {
		/*Find m smallest numbers in an unsorted array of n numbers
		 * Example -> If m = 3, then print out the three smallest numbers in a list/array
		 */
		
		//int test_arr[] = {1,565,3,4,12,-6,77,11,1767};
		//int test_size = test_arr.length;
		
		Scanner input = new Scanner(System.in);
		Random random = new Random();
		
		int low = 0;
		System.out.println("Enter a desired array size: ");
	    int size = input.nextInt();
	    int high = size;
	    
	    System.out.println("Creating array. Please wait.... \n");
        int[] arr = new int[high]; //Creates an array basis the array size entered in line
		
		//fill the array with random numbers
		for(int i = low; i < high; i++) {
					arr[i] = random.nextInt(1000); 
					//the number in parenthesis basically generates a number between 0 to the numeral entered
					//This increases the probability of finding the key value input
		}
		System.out.print(Arrays.toString(arr));
		System.out.println("\n");
		
	    System.out.println("Enter desired m: ");
	    int m = input.nextInt();
	    
	    //Call the above created method
	    printNSmallest(arr, size, m);
	    input.close();
	}

}
