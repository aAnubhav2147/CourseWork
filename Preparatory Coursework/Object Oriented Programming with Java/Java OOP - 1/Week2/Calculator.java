import java.util.Scanner;

public class Calculator{
	public static void main(String[] args){

		 Scanner input = new Scanner(System.in);

	     final double inv_value = 0;
	     int num1 = 0;
	     int num2 = 0;
	     boolean check = false;
	     boolean isValid1 = false;
	     boolean isValid2 = false;
	     double d1 = 0.0;
	     double d2 = 0.0;
	     boolean isValid3 = false;
	     boolean isValid4 = false;
	     String s1 = " ";
	     String s2 = " ";
	     
	     
	     System.out.println("List of operations: add subtract multiply divide alphabetize");
	     System.out.println("Enter an operation: ");
	     String operation = input.next();
	     
	     switch(operation = operation.toLowerCase()){

	     	case "add":
	     	System.out.print("Enter two integers: \n");
	     	if(input.hasNextInt()){
	     		if(input.hasNextInt()){                
	     		   num1 = input.nextInt();
	     		   check = true;
	     		   }
	     		else{
	     			System.out.println("Invalid input entered. Terminating...");
	     		    break;
	     		}     
	     	    if(input.hasNextInt()){
	     		   num2 = input.nextInt();
	     		   check = true;
	     		   }
	     		else{
	     			System.out.println("Invalid input entered. Terminating...");
	     		    break;
	     		}
	     	         
	     	}
	     	else{
	     			System.out.println("Invalid input entered. Terminating...");
	     		    break;
	     		}
	     	int output1 = num1 + num2;
	        System.out.println("Answer: " + output1); 
	        break;      	         		          	       		     	
	     	
	     	case "subtract":
	     	System.out.print("Enter two integers: \n");
	     	if(input.hasNextInt()){
	     		if(input.hasNextInt()){                
	     		   num1 = input.nextInt();
	     		   check = true;
	     		   }
	     		else{
	     			System.out.println("Invalid input entered. Terminating...");
	     		    break;
	     		}     
	     	    if(input.hasNextInt()){
	     		   num2 = input.nextInt();
	     		   check = true;
	     		   }
	     		else{
	     			System.out.println("Invalid input entered. Terminating...");
	     		    break;
	     		}
	     	         
	     	}
	     	else{
	     			System.out.println("Invalid input entered. Terminating...");
	     		    break;
	     		}
	     	int output2 = num1 - num2;
	        System.out.println("Answer: " + output2);
	        break;      	         		          
	     	
	     	   
	     	case "multiply":
	     	System.out.print("Enter two doubles: \n");
	     	if (input.hasNextDouble()){
	     		    if(input.hasNextDouble()){
	     				d1 = input.nextDouble();
	     				check = true;
	     			    }
	     			else {
                         System.out.println("Invalid input entered. Terminating...");
	     	             break;	     	     
	     	        }
	     	        if(input.hasNextDouble()){
	     				d2 = input.nextDouble();
	     				check = true;
	     			    }
	     			else {
                         System.out.println("Invalid input entered. Terminating...");
	     	             break;	     	     
	     	        }
	     	}        
	     	else{
	     			System.out.println("Invalid input entered. Terminating...");
	     		    break;
	     		}
	     	double output3 =  d1 * d2;
	     	System.out.printf("Answer: " + "%.2f", output3);
	     	System.out.println();
	     	break;
	     		        	     		 
	     	case "divide":	     
	     	System.out.print("Enter two doubles: \n");	     	
	     	if (input.hasNextDouble()){
	     			if(input.hasNextDouble()){
	     				d1 = input.nextDouble();
	     				check = true;
	     			    }
	     			else {
                         System.out.println("Invalid input entered. Terminating...");
	     	             break;	     	     
	     	        }
	     	        if(input.hasNextDouble()){
	     				d2 = input.nextDouble();
	     				check = true;
	     			    }
	     			else {
                         System.out.println("Invalid input entered. Terminating...");
	     	             break;	     	     
	     	        }        
	     	        if(d2 != inv_value){ 	   
	     	     	double output4 = (d1/d2);
	     	     	System.out.printf("Answer: " + "%.2f", output4);
	     	     	System.out.println();
	     	        break;
	     		    }
	     		    else {
                         System.out.println("Invalid input entered. Terminating...");
	     	             break;	     	     
	     	        }
            }
            else {
                         System.out.println("Invalid input entered. Terminating...");
	     	             break;	     	     
	     	}


	     	  	       
	     	case "alphabetize":
	     	System.out.print("Enter two words: \n");
	     	if (input.hasNext("[A-Za-z]+")){
	     		    if(input.hasNext("[A-Za-z]+")){
	     				s1 = input.next();
	     				check = true;
	     			    }
	     			else {
                         System.out.println("Invalid input entered. Terminating...");
	     	             break;	     	     
	     	        }
	     	        if(input.hasNext("[A-Za-z]+")){
	     				s2 = input.next();
	     				check = true;
	     			    }
	     			else {
                         System.out.println("Invalid input entered. Terminating...");
	     	             break;	     	     
	     	        }        

	     	    if (s1.compareToIgnoreCase(s2) > 0) {
	     	     	  System.out.println("Answer: " + s2 + " comes before " + s1 + " alphabetically.");
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
                	 System.out.println("Invalid input entered. Terminating...");
	     		     break;
            }
            
	     	default:
	     	System.out.println("Invalid input entered. Terminating...");
	     	break;

	    }

	    //System.out.println(" ");
	}
}	     