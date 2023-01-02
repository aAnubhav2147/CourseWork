import java.util.Scanner;

public class Branching{
	public static void main(String[] args){
	    final int MIN_PARK_TEMP = 70;
	    final int MAX_PARK_TEMP =90;

	    Scanner input = new Scanner(System.in);
	    System.out.print("Enter a Fahrenheit value: ");
	    int fahrenheit = input.nextInt();
	    System.out.print("Enter a day of the week: ");
	    String day = input.next();
	    double celsius = (5D/9) * (fahrenheit - 32);

	    System.out.println(day + "Fahrenheit: " + fahrenheit);
	    System.out.printf("%s %.1f \n", day + "Celsius:", celsius);

	    if ((fahrenheit >= 85) && (fahrenheit <= 95)){
	       System.out.println("Go to the swimming pool");
	    }

	    else if ((fahrenheit >= 70) && (fahrenheit < 85)){
	            System.out.println("Go to the park");
	    }

	    else {
               System.out.println("Stay at home");

	    }
	    System.out.println("Bye");
	}
}