package lab4;
import java.util.*;

public class CIS360Lab4_Task1 {
	public static int[] getMinMax(int[] tempArr,int n) {
		//The time complexity of the below algorithm will be (3n/2) given that
		//it is comparing two elements from the array at the same time and continuously updating the min and max
		//Concurrently, a single element will be compared three times even though the array is being divided every time
		int[] minmax = new int[2];
		int min = 0;
		int max = 0;
		int size = tempArr.length;
		//int i;
		
		for(int i = 0; i < size; i = i + 2) {
			//Compensate for odd number of elements
			if(i + 1 < size) {
				if(tempArr[i] >= tempArr[i + 1]) {
					//The OR condition is for extra-padding to prevent out-of-bounds exception
					if(tempArr[i] > max ||  i == 0) {
						max = tempArr[i];
					}
					if(tempArr[i+1] < min || i == 0) {
						 min = tempArr[i + 1];
					}
				}
				else {
					//The OR condition is for extra-padding to prevent out-of-bounds exception
					if(tempArr[i] < tempArr[i + 1] || i == 0) {
						if(tempArr[i + 1] > max) {
							 max = tempArr[i + 1];
						}
						if(tempArr[i] < min || i == 0) {
							 min = tempArr[i];
						}
					}
				}	

			}
					}
		minmax[0] = min;
		minmax[1] = max;
		return minmax;
	}

	public static void main(String[] args) { 
		Scanner input = new Scanner(System.in); //Create a scanner object for entering desired values
		Random random = new Random();
		
		int low = 0;
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
		
		//Arrays.sort(randomArray); //Sort the array for Ternary Search execution
		System.out.print(Arrays.toString(randomArray));
		System.out.println("\n");
		System.out.print(Arrays.toString(getMinMax(randomArray,size)));
		input.close();
	}

}
