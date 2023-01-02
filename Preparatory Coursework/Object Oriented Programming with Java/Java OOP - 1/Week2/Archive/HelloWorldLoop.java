/* public class HelloWorldLoop{
	public static void main(String[] args){
	    int counter = 0;
	    while (counter < 10){
	         System.out.println("Hello, World!");
	         counter++;
	    }
	}
} */

/* public class HelloWorldLoop{
	public static void main(String[] args){
		int lineNum = 1;
		while (lineNum <= 10){
			System.out.println("Hello, World!" + " " + lineNum);
			lineNum++;
		}
	}
} */

/* public class HelloWorldLoop{
	public static void main(String[] args){
		int lineNum = 10;
		while (lineNum > 0){
			System.out.println("Hello, World!" + " " + lineNum);
			lineNum--;
		}
	}
} */

/* public class HelloWorldLoop{
	public static void main(String[] args){
		int lineNum = 1;
		do{
			System.out.println("Hello, World!" + " " + lineNum);
			lineNum++;
		} while (lineNum <= 10);
	}
} */

/* public class HelloWorldLoop{
	public static void main(String[] args){
		int lineNum;
		for (lineNum = 1; lineNum <= 10; lineNum++) {
			System.out.println("Hello, World!" + " " + lineNum);
		}
		System.out.println("lineNum's value after the loop is: " + " " + lineNum);
	}
} */

public class HelloWorldLoop{
	public static void main(String[] args){
		int outerCounter = 1;
		while (outerCounter <= 5){
			int innerCounter = 1;
			while (innerCounter <= 10) {
				System.out.println("Hello, World!" + " " + innerCounter);
				innerCounter++;
			}
			outerCounter++;
		}
	}
}