import java.util.*; 

public class AckerFunction {

    private static int spaces = 0;
    private static int numberOfInvocations = 0;

    // getter for data field "numberOfInvocations"
    public static int countOfInvocations(){
        return numberOfInvocations;
    }

    public static int acker(int m, int n){
    	printSpaces();
    	System.out.println("Enter method Acker: " + "m: " + m + " " + "n: " + n);
    	numberOfInvocations++;
        int result = 0;
        if (m < 0 || n < 0) {
        	System.out.println("Invalid Input! Entries can't be negative integers.");
        }       
        else if( m == 0) {
        	return n + 1;
        }
        else if(m > 0 && n == 0) {
        	spaces = spaces + 1;
        	result = acker(m-1,1);
        	spaces = spaces - 1;
        }
        else {
        	spaces = spaces + 1;
        	result = acker(m-1,acker(m,n-1));
        	spaces = spaces - 1;
        }        
        
        // spaces =- 5;
        printSpaces();
        System.out.println("Leave method acker: acker: (" + m +"," + n + ") = " + result);
        return result;
        
    }

    // indent the trace messages according to how "deep" the current recursive call is
    private static void printSpaces(){
        for (int i = 0; i < spaces; i++)
            System.out.print(" ");
    }

    public static void main(String[] args) {

        //TODO: read two nonnegtive intergers from stardard input and
        //      call the recursive method acker(int, int).
        //        Output the total number of method invocations.
    	
    	int m = 0;
    	int n = 0;
    	boolean valid = false;
    	//boolean quit = false;
    	//String quit = "q";
    	Scanner input = new Scanner(System.in);
    	System.out.println("Enter an operation: ");
    	String operation = input.next();
    	
    	
    	//quit = input.next();
    	/*
    	do {
    		if(operation == "q") {
    			System.out.println("Done!");
    			break;
    		}
    	}while(valid); */
    	
    	switch(operation = operation.toLowerCase()) {
    	case "a":
    		System.out.println("Input two integers seperated by a space: \n");
    		if(input.hasNextInt()) {
        		if(input.hasNextInt()) {
        			m = input.nextInt();
        			valid = true;
        		}
        		else {
        			System.out.println("Invalid input entered!");
        		}
        		if(input.hasNextInt()) {
        			n = input.nextInt();
        			valid = true;
        		}
        		else {
        			System.out.println("Invalid input entered!");
        		}    		
    	}
    		else {
    			System.out.println("Invalid input entered!");
    		}
    		
    		int output = acker(m , n);
    		System.out.println("Result: " + output + " " +"Total Number of Invocations: " + numberOfInvocations + "  " );
    		break;
    		
    	case "quit":
    		System.out.println("Press q again to exit");
    		if (input.hasNext("[A-Za-z]+")){
    			if(input.hasNext("q")) {
    				System.out.println("Done!");
    				break;
    			}
    		}
    		
    		default:
    			System.out.println("No input received. Terminating.....");
    	}
    	
    	input.close();
    		
    
    	
    	
    	/*
    	while(!quit) {
    		//System.out.println("Results: " + acker(m , n) + " " +"Total Number of Invocations: " + numberOfInvocations + "  " );
    		switch(operation) {
    		case "q":
    			System.out.println("Done");
        		break;
    		}
    		
    	} */
    	

    	
    	//String q = input.next();
    	
    	}
}
