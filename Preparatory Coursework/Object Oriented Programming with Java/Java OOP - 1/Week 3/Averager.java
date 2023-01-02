public class Averager{
	public static void main(String[] args){
	    double[] weekHighs = {80,70,75,69,72,74,90};
        double highSum = 0;
        for (double dayHigh : weekHighs){
        	highSum = highSum + dayHigh;
        }
	    double averageHighs = highSum/weekHighs.length;	    
	    System.out.printf("Average is: " + "% .3f", averageHighs);
	    System.out.println();                       
	}
}