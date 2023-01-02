import java.util.Random;

public class Die{
	
	public static final int SIDES = 6;
	private int faceValue;
	private Random rand;

	public Die(){
		faceValue = 1;
		rand = new Random();
	}

	public int roll(){
	     faceValue = rand.nextInt(SIDES) + 1;
	     return faceValue;

	     /* We could also use the Math.random method
	        low + (int) (Math.random() * ((high - low) + 1))
	        */

	}

	public int getFaceValue(){
	    return faceValue;
	}

	public String toString(){
	    return "Die with face value: " + faceValue;
	}

	public static void main(String[] args){
	    Die die1 = new Die();

	    System.out.println(die1.toString());
	    System.out.println(die1.roll());
	    System.out.println(die1.roll());
	}
}