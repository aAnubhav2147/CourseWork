package kSmall;

import java.util.*;

public class KSmallTester {

    public final static int SIZE_OF_ARRAY = 10;
    public final static int LOW = 1;
    public final static String PROMPT = "Please  enter an integer k, 1<=k<=" +
        SIZE_OF_ARRAY + ", or 'R' to refill the array: ";

    public static void printArray(int[] array) {

        System.out.println("");
        System.out.print("array = [");
        for (int i=0; i < SIZE_OF_ARRAY-1; i++)
            System.out.print(""+ array[i]+" | ");
        System.out.println(""+ array[SIZE_OF_ARRAY-1]+"]");
        System.out.println("-------------------------------------------------"
                         + "-------------------");
    }

    public static void randFillArray(int[] array) {
        Random random = new Random();

        for (int i=0; i < SIZE_OF_ARRAY; i++)
            array[i] = random.nextInt(100);
    }

    public static void main(String argv[]) {
    	int k = 1, kthSmallest = 0;
        int[] array = new int[SIZE_OF_ARRAY + 1];
        int[] arrayTmp = new int[SIZE_OF_ARRAY + 1];
        
        
        
        String userInput = null;
        Scanner input = new Scanner(System.in);
        
        do {
        	System.out.println(PROMPT);
        	userInput = input.nextLine();
        	
        	if(userInput.equalsIgnoreCase("R")) {
        		randFillArray(array);
                printArray(array);
        	}
        	
        	else if(userInput.equalsIgnoreCase("Q")) {
        		System.out.println("Done!");
        		break;
        	}
        	
        	else if(!userInput.equalsIgnoreCase("Q") || !userInput.equalsIgnoreCase("R")) {
        		try {
        			if(Integer.parseInt(userInput)<LOW || Integer.parseInt(userInput)>SIZE_OF_ARRAY) { //work within constraints
        				throw new Exception(); //Introduce an exception class for the catch operations
        		}
        	
        			else {
                		int ksmallest = Integer.parseInt(userInput); // parses ksmallest
                		//Create a deep copy of array to be used for further processing
                		for (int i = 0; i < SIZE_OF_ARRAY;i++) {
                			arrayTmp[i] = array[i];
                		}
                		int kthSmall = KthSmallest.kSmall(ksmallest, arrayTmp, 0, SIZE_OF_ARRAY - 1);
                		System.out.println("Kth Smallest Elemnt : " + kthSmall); 
                		// prints out kth smallest element
                		// prints out quicksort 
                		System.out.print("Array post sort: ");
                		for (int i = 0; i < SIZE_OF_ARRAY;i++) {
                			System.out.print(arrayTmp[i]+ " ");
                			//NOTE: WHile the sorting is happening as it should the above line prints out the array in a random order
                			//So, for 1<=k<=4 the sorting may seem incorrect but with every successive entry of k you'll get the correct output
                		}
                		System.out.println(" ");
                	        	}
                }
                		// catches invalid format
                		catch(NumberFormatException e) {
                			System.out.println("Invalid input. Retry!!!!");
                			continue;
                		}
                		catch(Exception e) {
                			System.out.println("Invalid input. Retry!!!");
                			continue;
                		}
                	} 	
        	
        } while(userInput != null);
        
        /*
        boolean check = true; //Introduce a boolean condition for a do-while loop
        String operation = null;
        
        do {
        	Scanner input = new Scanner(System.in);
        	int k = 1, kthSmallest = 0;
            int[] array = new int[SIZE_OF_ARRAY];
            int[] arrayTmp = new int[SIZE_OF_ARRAY];
            
            randFillArray(array);
            printArray(array);
            
            //Get user input
            System.out.println("Enter a k value: ");
            
            try {
            	 operation = input.nextLine();
            	
            	if(operation.toLowerCase().trim().equals("q")) {
            		System.out.println("Done!");
            		break;
            	}
            	else if(Integer.parseInt(operation) > 0) {
            		int temp = Integer.parseInt(operation);
            		for (int i = 0; i < SIZE_OF_ARRAY;i++) {
            			arrayTmp[i] = array[i];
            		}
            		int kthSmall = KthSmallest.kSmall(temp, arrayTmp, 0, SIZE_OF_ARRAY);
            		System.out.println("k smallest of array = " + kthSmall);
            	}else if(Integer.parseInt(operation) < 0) {
            		System.out.println("Invalid Input!");
            	}
            	
            } catch(Exception e) {
            	System.out.println("Retry!");
            }                	
        } while(check); */
        
        //input.Close();
        
    	//System.out.println("Enter an operation: ");
    	//String operation = input.next();

        
        
        //Attempt 1 - with Switch statement - problem: checking for negative inputs proved challenging
        /*
        switch(operation = operation.toLowerCase()) {
    	case "k":
    		System.out.println("Input k: \n");
    		if(input.hasNextInt() || input.hasNext("[A-Za-z]+")){
    			if(input.hasNext("q")) {
    				System.out.println("Done!");
    				break;
    			}
    			else if(input.hasNextInt() >= 0) {
    				
    			}
    		}

        // ToDo: Read input k and print out the k-th smallest item of the array.

        // Note that your program should allow finding k-th smallest item from an array
        // continuously (i.e., more than once) , refilling the array, and exiting the
        // program when typing in "Q" or "q".

    } */                
}
}
