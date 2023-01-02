import java.util.Scanner;

public class FahrenheitToCelsius1{
	public static void main(String[] args){
	    Scanner input = new Scanner(System.in);
	    System.out.print("Enter a Fahrenheit Value: ");
	    int fahrenheit = input.nextInt();
	    System.out.print("Enter a day of the week: ");
	    String day = input.next();
	    double celsius = (5D/9) * (fahrenheit - 32);
	    System.out.println(day + " " + "Celsius: " + celsius);
	    System.out.println(day + " " + "Fahrenheit: " + fahrenheit);
	}
}