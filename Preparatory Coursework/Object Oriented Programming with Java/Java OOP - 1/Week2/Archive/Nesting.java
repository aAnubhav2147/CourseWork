public class Nesting{
	public static void main(String[] args){
	    boolean x = true;
	    int count = 0;
	    while (x){
	       count += 4;
	       if(count % 5 == 0){
	         x = false;
	       }
	    }
	System.out.println(count);
	}
}