import java.text.DecimalFormat;

public class DecimalFormatDemo{
	public static void main(String[] args){
	DecimalFormat formatter1 = new DecimalFormat("0.0");
	DecimalFormat formatter2 = new DecimalFormat("00.00");
	DecimalFormat formatter3 = new DecimalFormat(".00");
	DecimalFormat formatter4 = new DecimalFormat("0.00%");

	System.out.println("0.0: " + formatter1.format(.8675309));
	System.out.println("00.00: " + formatter2.format(.8675309));
	System.out.println(".00: " + formatter3.format(.8675309));
	System.out.println("0.00%: " + formatter4.format(.8675309));
	System.out.println(".00: " + formatter3.format(8675309));
	}
}