import java.util.Scanner;

public class FTC_pf{
	public static void main(String[] args){
	    Scanner input = new Scanner(System.in);
	    System.out.print("Enter a Fahrenheit Value: ");
	    int fahrenheit = input.nextInt();
	    //input.nextLine(); //cleans up newline at the end of user input
	    System.out.print("Enter a day of the week: ");
	    String day = input.next();
	    System.out.print("Enter your preferred Celsius label (Celsius,Centigrade, or C): ");
	    String cText = input.next();
	    double celsius = (5D/9) * (fahrenheit - 32);
	    System.out.printf("%s Fahrenheit: %d\n",day,fahrenheit);
	    System.out.printf("%s %-10s: %.1f\n",day,cText,celsius);
	}
}