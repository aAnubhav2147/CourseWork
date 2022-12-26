package danger;

import java.util.Scanner;

public class Test {
	 public static void main(String[] args) {
	        //Machine input = new Machine();
	        //String safeGoods[] = {"pant","hat","cap","jacket","laptop","shirt","book"};
		 
		        boolean wentToCatch = false; //use a boolean operator to wrap the try-catch inside a do-while loop
	        
	            Scanner sc=new Scanner(System.in); //Initiate scanner class for taking user input
	            
	            System.out.println("Please input items/testcases:");
	            String input=sc.nextLine();
	            Goods g=new Goods(); //initiate a new Goods class instance 
	            g.setName(input);
	            Machine m=new Machine(); //initiate a new Machine class instance
	            do {
	            	try {
		            	//for(int i = 0; i < goods.length; i++) {
	            		m.checkBag(g); //check the items
		            	wentToCatch = false; //item is safe
		            	System.out.println(g.getName() + " Passed!");
		            }catch(DangerException d) {
		            	/*for(int i = 0; i < goods.length; i++) {
		            		wentToCatch = true;
			            	input = goods[i];
		            	}*/
		            	//wentToCatch = true;
		            	//input = goods[];
		            	wentToCatch = true; //item is not safe
		            	d.toShow();
		            	System.out.println(g.getName() + " Dangerous!");
		            	break;		            	
		            }
	            } while(wentToCatch == true); //assume that the item is not safe
	              	        
	    }

}
