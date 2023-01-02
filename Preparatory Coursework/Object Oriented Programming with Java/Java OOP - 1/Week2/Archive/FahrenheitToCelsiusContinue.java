import java.util.Scanner;

public class FahrenheitToCelsiusContinue{
	public static void main(String[] args){

	    final int MIN_TEMP = -200;
	    final int MAX_TEMP = 200;

	    Scanner input = new Scanner(System.in);

	    double totalFahrenheit = 0;
	    int validFahrenheits = 0;
	    int fahrenheit;

	    for(int i = 1; i <= 5; i++){

	       System.out.print("Enter a Fahrenheit value: ");
	       fahrenheit = input.nextInt();
	       if ((fahrenheit >= MIN_TEMP) && (fahrenheit <= MAX_TEMP)){
	            totalFahrenheit = totalFahrenheit + fahrenheit;
	            validFahrenheits++;
	       }
	       else {
	             System.out.println("Invalid Value");
	             continue;
	       }
	    }
	    if (validFahrenheits > 0){
	        System.out.println("Average Fahrenheit Temperature: " + totalFahrenheit/validFahrenheits);
	    }
	}
}