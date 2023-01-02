public class EqualityTest{
	public static void main(String[] args){
	    String x = "park";
	    String y = new String("Japan");

	    if (x == y) System.out.println("x and y are aliases");

	    if (x != y) System.out.println("x and y are not aliases");

	    if (x.equals(y)){
	        System.out.println("x and y have the same contents");
	        System.out.println("x: " + x);
	        System.out.println("y: " + y);

	    }

	    if (x.compareTo(y) > 0){
	    	System.out.println("x is greater than y");
	    	System.out.println("x: " + x);
	    	System.out.println("y: " + y);
	    }	
	}
}