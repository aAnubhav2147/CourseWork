import java.util.Scanner;

public class Calculator_test{
	public static void main(String[] args){
	     final double inv_value = 0;			
	     Scanner input = new Scanner(System.in);
	     System.out.print("List of operations: add,subtract,multiply,divide,alphabetize \n");
	     //input.nextLine();
	     System.out.print("Enter an operation: \n");
         String operation = input.next();
	     int num1 = 0;
	     int num2 = 0;
	     boolean isValid1 = false;
	     boolean isValid2 = false;
	     double d1 = 0.0;
	     double d2 = 0.0;
	     boolean isValid3 = false;
	     boolean isValid4 = false;
	     String x = String.valueOf(d1);
	     String y = String.valueOf(d2);	

	     switch(operation = operation.toLowerCase()){

	     	case "add":
	     	while (isValid1 == false || isValid2 == false){
	     		System.out.print("Enter two integers: \n");
	     	    if(input.hasNextInt()){
	     		   num1 = input.nextInt();
	     		   isValid1 = true;     		   
	     	       num2 = input.nextInt();
	     	       isValid2 = true;	     	       	
	               int output1 = num1 + num2;
	               System.out.print("Answer: " + output1);
	               break;
	     	}
	     	else {
	     		  System.out.println("Invalid input entered. Terminating");
	     		  break;
	     	}	  
            //input.close();
	     	} break;


	     	case "subtract":
	     	System.out.print("Enter two integers: \n");
	     	if (input.hasNextInt()){
	     		 num1 = input.nextInt();
	     	     num2 = input.nextInt();	     	       	
	             int output2 = num1 - num2;
	             System.out.print("Answer: " + output2);
	             break;
	     	}
	     	else {
	     		  System.out.println("Invalid input entered. Terminating");
	     		  break;
	     	}	     

	     	case "multiply":
	     	System.out.print("Enter two doubles: \n");
	     	if (input.hasNextDouble()){
	     		 d1 = input.nextDouble();
	     	     d2 = input.nextDouble();	     	
	     	     double output3 = d1 * d2;
	     	     System.out.printf("Answer: " + "%n %.2f", output3);
	     	     break;
	     	}
	     	else {
	     		  System.out.println("Invalid input entered. Terminating");
	     		  break;
	     	}	     
	     	

	     	case "divide":     
	     	System.out.print("Enter two doubles: \n");	     	
	        if(input.hasNextDouble()){
	     		d1 = input.nextDouble();
	     	    d2 = input.nextDouble();
	     	    if (d2 != inv_value){
	     	    	double output4 = d1/d2;
	     	        System.out.printf("Answer: " + "%n %.2f", output4);
	     	        break;
	     	    }
	     	    else System.out.println("Invalid input entered. Terminating"); break;     
	     	}    
            else if(!input.hasNextDouble()) {
            	      x = input.next();
            	      d1 = input.nextInt();
            	      y = input.next();
            	      d2 = input.nextInt();
                      System.out.println("Invalid input entered. Terminating");
	     	          break;
            }
            break;
      	     	       
	     	case "alphabetize":
	     	System.out.print("Enter two words: \n");
	     	if (input.hasNext()){
	     		 String s1 = input.next();
	     	     String s2 = input.next();
	     	     if (s1.compareToIgnoreCase(s2) > 0) {
	     	     	  System.out.println("Answer: " + s1 + " comes after " + s2 + " alphabetically.");
	     	     	  break;
	     	     	}	  
	     	     else if (s1.compareToIgnoreCase(s2) < 0){
	     	     	       System.out.println("Answer: " + s1 + " comes before " + s2 + " alphabetically.");
	     	     	       break;
	     	     	    }   
	     	     else {
	     	     	System.out.println("Answer: Chicken or Egg.");
	     	        break;
	     	    }    
	     	}
	     	else {
                	 System.out.println("Invalid input entered. Terminating");
	     		     break;
            }
            
	     	default:
	     	System.out.println("Invalid input entered. Terminating");
	     	return;

	    }
	    //input.nextLine();
	    System.out.println(" ");


	}
}