#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
//#include <cmath.h>
//#include <conio.h>


double random_number();                                      //Generate a uniform random number
double likelihood(double x1, double x2);		     //Integrand for the likelihood estimator
double monteCarlo(double lower, double upper, int N);        //A general function to check the working of the MonteCarlo integration
double log_value(double l); 				     //Calculate the log of the integral

int main(int argc, char **argv){
	
	srand(time(NULL)); //randomize seed //Remember to mention this before invoking a random variable

	
	double lower, upper;
	lower = -5.0;
	upper = 5.0;
	unsigned long long N = atoll(argv[1]);	//Cognizant with the keyword argument argv to intake a user input //DON'T USE <conio.h> package !!!
	
	 									                                                                                        
	double integral = monteCarlo(lower,upper,N); //Output
	//printf("%i   %.4f\n",N,estimate);


	double ae = log_value(integral); // Log of the integral


	printf("%llu  %.6f  %.6f\n",N,integral,ae); 	//Simplify for .dat output
	
	return 0;
}


double random_number(){
	  //Generate uniform random number

	  return (double)rand()/((double)RAND_MAX+1);
}


double likelihood(double x1, double x2){
	//Integrand for the likelihood estimator	
	return exp(-((1-x1)*(1-x1)) - 100*((x2 - x1*x1) * (x2 - x1*x1)));
}

double monteCarlo(double lower, double upper, int N){
	        //General function to execute the Monte Carlo Approximation
		//Can be used for any integrand and any bound
		
		double apx = 0.0;  //This will store the approximate of the integrand
		double sum = 0.0;  //This stores the summation value.
		
			for(int i = 0; i<N; i++){
				for(int j = 0; j < 10; j++){
					//Generate a random number within the limits of the integration
					double rand_num = random_number() - 1;
			       		double rand_num2 = random_number() - 1;	//The subtracted value is for bound aherence
					double func_value = likelihood(rand_num,rand_num2);  //Pass the requisite random number into the integrand i.e. the parent function
					sum+=func_value; //Calculation of the summation
				}
	       		}

	       return apx = (upper - lower) * (sum/N); //Returns the integral value
}


double log_value(double l){
	//Function to calculate the log value of the integral
	
	//const double tv = 1.0;
	double lv = log(l);
	return lv;
}

