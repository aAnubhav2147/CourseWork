package cp;
import java.io.Console;
//import java.util.*;
import java.io.PrintWriter;


public class ChangePassword {
	static boolean validateLogin(String username, String password) {
	    return true;
	  }
	  
	static boolean resetPassword(String username, String password) {
		return true;
	  }

	public static void main(String[] args) {
		Console cons = System.console();
	    
	    if (cons == null) {
	    	System.err.println("No console available.");
	      	System.exit(1);
	    }
	    
	    PrintWriter consOutput = cons.writer();
        String username = cons.readLine("Username:");
        System.out.println("Username: " + username);
        consOutput.println("Update your password:");

        boolean foundError = false;
        do { 
        	//introduce a new regex pattern in accordance with lab requirements
            String regex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{6,15}$";
            //The above regex instantiates a pattern which needs at least 6 character elements in the password and maximum 15 elements
            
            /* Create a String array containing all the test cases
            String[] testPasswords = {
            		"123",
            		"1234",
            		"123456a",
            		"789016",
            		"abcdef",
            		"123456A"
            		//cons.readPassword()
            };
            */
    
   	      foundError = false; //A standard boolean checker for looping the while condition
   	      
   	    // In the following section, please fill out code to meet requirements of the lab description
   	   String pwd = new String(cons.readPassword("Password:"));
   	   System.out.println("Password: " + pwd); //echo the password entry onto the screen
   	   //Ensure the user input is in accordance with the regex conditions
        	   if(pwd.matches(regex)) {
        		   foundError = false;
        		   System.out.println("Valid Pattern for password!");
        	   }
        	   else {
        		   
        		   System.out.println("Invalid Pattern!");
        	   }
        	   
       String pwd2 = new String(cons.readPassword("Verify password:"));
       System.out.println("Confirmed Password: " + pwd2); //echo the password entry onto the screen
       //Ensure the user input is in accordance with the regex conditions
        	   if(pwd2.matches(regex)) {
        		   foundError = false;
        		   System.out.println("Valid Pattern for password!");
        	   }
        	   else {
        		   
        		   System.out.println("Invalid Pattern!");
        	   }
       
       //Do one final check for all the required conditions.
       if(pwd.matches(pwd2) && pwd2.matches(regex) &&  pwd.matches(regex)) {
            	   System.out.println("Passwords are valid and they match!");
            	   break;
               }
       else {
    	           foundError = true;
            	   System.out.println("Passwords are either invalid or do not match. Retry!!");
            	   //break;
               }
      
            // If we make it this far, we have passed all of the tests.
            
          } while (foundError);
	}

}
