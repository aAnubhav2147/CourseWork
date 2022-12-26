package lab5;

public class Calculator implements Process {

    //protected final String PROMPT = "In the Input line, please enter " +
    //                                "an age (the sentinel is ";
	
	protected final String PROMPT = "Please enter two number in the input line ";
    protected final int SENTINEL = -1;
    //protected int highestAge;
    int cnt = 1; //random count iterator to replace highestAge
    protected GUI gui; //We'll be using this to print stuff out rather than use java defaults
    
    SmallInt[] input = new SmallInt[2]; //Creating an array makes it easier to store random user input recorded by the SmallInt sub-class

    // Postcondition: This Calculator has been initialized.
    public Calculator() {           // default constructor
         gui = new GUI (this);
         gui.print(PROMPT + cnt + ": ");
    }

    // Postcondition: The input string s has been processed.
    public void processInput(String s) {
        //final String HIGHEST_MESSAGE = "\nThe highest age is ";
        gui.println(s);
        
        input[cnt - 1] = new SmallInt(s); //-1 to prevent StackOverflow error and also store user inputs
        
        if(cnt == 2) {
        	//add method
        	gui.println("\t" + input[0].getValue() + "\n + \n \t" + input[1].getValue() + "\n-----------\n" + "\t" + input[0].add(input[1]));
        	
        	gui.freeze( );
        	
        	//final String CLOSE_WINDOW_PROMPT = "\nThe execution of this project has " +
            //        "been completed.\nPlease close this window when you are ready.";
        	
        	gui.print("\n" + "The execution of this project has been completed." +"\n" + "Please close this window to terminate.");
        	
        } else {
        	cnt++; //count and wait for next user input
        	gui.print(PROMPT+cnt+": ");
        }
        
        /*
        int age = Integer.parseInt(s);
        if (age != SENTINEL) {  // not the sentinel
            if (age > highestAge) highestAge = age;
            gui.print(PROMPT + SENTINEL + "): ");

        } else {                // sentinel reached
            gui.println(HIGHEST_MESSAGE + highestAge + CLOSE_WINDOW_PROMPT);
            gui.freeze( );
        } */
        
        
    }

    public static void main(String argv[]) {
        Calculator calc = new Calculator();
    }

}

