#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
//#include <cmath.h>
//#include <conio.h>


double random_number();                                      //Generate a uniform random number
double func(double x);                                       //Test function to store the integrand for the trapezoidal rule
double monteCarloGeneral(double lower, double upper, int N); //A general function to check the working of the MonteCarlo integration
double error(double integral); 				     //Calculate the error

int main(int argc, char **argv){
	
	srand(time(NULL)); //randomize seed //Remember to mention this before invoking a random variable

	
	double lower, upper;
	lower = -1.0;
	upper = 1.0;
	int N = atoi(argv[1]);	//Cognizant with the keyword argument argv to intake a user input //DON'T USE <conio.h> package !!!
	
	 									                                                                                        
	double estimate = monteCarloGeneral(lower,upper,N); //To check the trapezoidal rule
	//printf("%i   %.4f\n",N,estimate);


	double ae = error(estimate); // Error output	//Unlike lab4 we only need to pass the integral approximation
							// here because the monteCarloGeneral() function is already
							// calculating the integrand and incrementing the requisite V


	printf("%i  %.6f  %.6f\n",N,estimate, ae); 	//Simplify for .dat output
	
	return 0;
}


double random_number(){
	  //Generate uniform random number

	  return (double)rand()/((double)RAND_MAX+1);
}



double func(double x){
	//This function will be used to pass the integrand that we need to approximate
		
        return 1.0/(1.0 + x*x); //Integrand
}

double monteCarloGeneral(double lower, double upper, int N){
	        //General function to execute the Monte Carlo Approximation
		//Can be used for any integrand and any bound
		//For the moment passing the trapezoidal rule only
		
		double apx = 0.0;  //This will store the approximate of the integrand
		double sum = 0.0;  //This stores the V value.
				   //For special case of the trapezoidal rule, V = 2
		
			for(int i = 0; i<N; i++){
				//Generate a random number within the limits of the integration
				double rand_num = random_number() - 1; //The -1 is a fail-safe to ensure bound adherence
				//printf("%.4f\n", rand_num);         //This print statement is here to check whether
								     // the random number generated is within
								    // the defined bounds. For trapezoidal it'll be [-1,1]
								   // For snowfall it'll be [0,10]
			
			
				double func_value = func(rand_num);  //Pass the requisite random number into the integrand i.e. the parent function
				sum+=func_value; //Calculation of V
	       }

	       return apx = (upper - lower) * (sum/N); //Returns the integral value
}


double error(double integral){
	//Function to calculate the absolute error i.e. the divergence from the expected value
	
	const double pi = 3.141592653589793;
	double absolute_error = fabs(integral - pi / 2. );
	return absolute_error;
}
