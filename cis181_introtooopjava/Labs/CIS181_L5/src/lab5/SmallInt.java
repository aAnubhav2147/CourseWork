package lab5;

import java.math.BigInteger;
import java.util.*;

/*Implements a new class SmallInt to do all the mathematical operations
 * asked for in the lab work 
 */

public class SmallInt {
	int k; //initialize an integer for carrying out the operations
	
	//Construct overloaded constuctors for checking data type before carrying out the operations
	
	//Constructor
	
	public SmallInt(int i) {
		super(); //use this as the GUI extends JFrame and also will be used by a no-args constructor in the superclass, Calculator.java
		this.k = i;
	}
	
	public SmallInt(String s) {
		this.k = setValue(s); //enables k to be a string value
	}
	
	//Getter to fetch k
	public int getInt() {
		return k;
	}
	
	public void setInt(int k) {
		this.k = k; //Basically sets k to itself
	}
	
	public static boolean checkNumeric(String str) {
		try {
            Double num = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
		
		return true;
	}
	
	public int setValue(String S) {
		int i;
		
		try {
			i = Integer.parseInt(S);
		}catch(Exception e) {
			if (checkNumeric(S)) {
				i = 0;
			} else {
				i = -1;
			}
		}
		
		if(i < Integer.MIN_VALUE || i > Integer.MAX_VALUE) {
			//Lab assignment condition
			i = 0;
		}
		
		return i;				
	}
	
	public int getValue() {
		return getInt();
	}
	
	public int add(SmallInt j) {
		int sum = j.getInt() + this.getInt();
		
		//Re-check the condition for sum as was done for setValue method
		if(sum < Integer.MIN_VALUE || sum > Integer.MAX_VALUE) {
			//Lab assignment condition
			sum = 0;
		}
		
		return sum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
