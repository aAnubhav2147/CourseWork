import java.util.Scanner;

public class FTC_ext{
	public static void main(String[] args){
	    Scanner input = new Scanner(System.in);
	    System.out.print("Enter a Fahrenheit Value and the day of the week: ");
	    int fahrenheit = input.nextInt();
	    //input.nextLine(); //cleans up newline at the end of user input
	    //System.out.print("Enter a day of the week along with the month day and year: ");
	    String day = input.next();
	    double celsius = (5D/9) * (fahrenheit - 32);
	    System.out.println(day + " " + "Celsius: " + celsius);
	    System.out.println(day + " " + "Fahrenheit: " + fahrenheit);
	}
}