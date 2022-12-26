#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
//#include <cmath.h>


double random_number();   //Generate uniform random number
double snowfall(double x); //Integrand model for modeling the snowfall in Dartmouth, MA
double monteCarlo(double lower, double upper, int N);  //A general function to check the working of the MonteCarlo integration 
double error(double integral); //Calculate the error

int main(int argc, char **argv){
		
	srand(time(NULL)); //randomize seed //Remember to mention this before invoking a random variable
		
	double lower, upper;
	lower = 0.0;
	upper = 10.0;
	int N = atoi(argv[1]);  //Cognizant with the keyword argument argv to intake a user input //DON'T USE <conio.h> package !!!
	
	double estimate = monteCarlo(lower,upper,N); //This returns the approximate of the integrand passed for the Monte-Carlo approximation
	
	double ae = error(estimate); // Error output

	printf("%i  %.6f  %.6f\n",N,estimate, ae); //Simplify for .dat output

	return 0;
}

double random_number(){
	//Generate uniform random number
	
	return (double)rand()/((double)RAND_MAX+1);
}

double snowfall(double x){
	//Integrand to model the snowfall over Dartmouth, MA in Jan 2023
	
	return (1.0/2.4496028273129427) * exp(-(fabs((x-2) * (x-2)))/2); //For absolute value, use fabs() because it has a wider signature
}

double monteCarlo(double lower, double upper, int N){
	//General function to execute the Monte Carlo Approximation
	//Can be used for any integrand and any bound			
									
		double apx = 0.0;	//This will store the approximate of the integrand
		double sum = 0.0;	//This stores the V value.
			
		for(int i = 0; i<N; i++){	
			//Generate a random number within the limits of the integration

			double rand_num = random_number() * (upper - lower);
			double func_value = snowfall(rand_num);	 //Pass the requisite random number into the integrand i.e. the parent function
			sum+=func_value;	//Calculation of V
		}											        								      return apx = (upper - lower) * (sum/N);  //Returns the integral value
}

double error(double integral){
		//Function to calculate the absolute error i.e. the divergence from the expected value
		
		const double normal = 1.0;
		double absolute_error = fabs(integral - normal);
		return absolute_error;
}
