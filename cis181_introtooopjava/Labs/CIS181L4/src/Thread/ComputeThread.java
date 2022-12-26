package Thread;
//import java.util.Scanner;

public class ComputeThread implements Runnable{
	ThreadFrame threadframe ;
	static public double result = 0;
	static public int i;
	String str1 = "";
	//Scanner input = new Scanner(System.in);
	public ComputeThread(ThreadFrame t) {
		threadframe = t;
	}
	public void run() {
		long s = 1;
		//System.out.println("Enter a number for factorial calculation: ");
		//int n = input.nextInt();
		for(i=1;i<=30;i++)
			//for(i=1;i<=n;i++) //Compute the factorial sum up to the number entered
		{
			//add code starting here
            if(str1 == "") {
            	str1 = i + "!";
            }else {
            	str1 = str1 + "+" + i + "!";
            }
            
            s *= i; //compute the factorial
            result += s; //continuously increment the result to store the successive factorial sums
            threadframe.output.setText(str1); //multi-thread and use the ThreadFrame listener
            threadframe.jprogressBar.setValue(i); //Show the progress bar

			//add code stopping here

			try {
				Thread.sleep((long)(500 + 500 * Math.random()));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
				System.out.println("Error in the first thread");
			}

		}
	}
}