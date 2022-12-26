#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
//#include <cmath.h>
//#include <conio.h>


double random_number();                                      //Generate a uniform random number
double func(double *x, int dim);                             //Function to store the integrand i.e. the indicator function
double monteCarlo(double lower, double upper, int N); 	     //A function to approximate the MonteCarlo integration
double error(double integral); 				     //Calculate the error

int main(int argc, char **argv){
	
	srand(time(NULL)); //randomize seed //Remember to mention this before invoking a random variable

	
	double lower, upper;
	lower = -1.0;
	upper = 1.0;
	unsigned long long N = atoll(argv[1]);	//Cognizant with the keyword argument argv to intake a user input //DON'T USE <conio.h> package !!!
	//double dim = 10;
	
	 									                                                                                        
	double estimate = monteCarlo(lower,upper,N); //Monte-Carlo Approximation
	//printf("%i   %.4f\n",N,estimate);


	double ae = error(estimate); // Error output


	printf("%llu  %.6f  %.6f\n",N,estimate, ae); 	//Simplify for .dat output
	
	return 0;
}


double random_number(){
	  //Generate uniform random number

	  return (double)rand()/((double)RAND_MAX);
}



double func(double *x, int dim){
	//This function will be used to pass the integrand that we need to approximate
	
	double sum = 0.0;

	for(int i=0; i<dim; i++){
		
		sum += x[i]; //Here, sum = x1 + x2 + ..... + xn

		//We need to use the array *x here because we need to sum N different variables and then do
		//a N-dimensional integration i.e. integrate the function N times.
	}

	return sum + 1.0;
        
}


double monteCarlo(double lower, double upper, int N){
	        //General function to execute the Monte Carlo Approximation
		//Can be used for any integrand and any bound
		
		//double apx = 0.0;  //This will store the approximate of the integrand
		double sum = 0.0;  //This stores the summation value
		const int dim = 10;
		double x[dim];
		
		for(int i = 0; i<N; i++){
			for(int j = 0; j < dim; j++){
				//Generate a random number within the limits of the integration
				//x[dim] = random_number() - 1;	//The subtraction is a fail-safe to ensure bound adherence
					
				x[j] = (2 * random_number()) - 1; //This basically ensures the integration happens 'dim' times

				//printf("%f ", x[j]);
			}

			double func_value = func(x,dim);  //Pass the requisite random number into the integrand i.e. the parent function
			//printf("%f ", func_value);
			sum+=func_value; //Calculation of the summation
	       }

	       //printf("%f ",pow(2,dim));

	       return pow(2,dim) * (sum/N); //Returns the integral value //Here the V is for a 10-dimensional box so, 
	       				   //we need to have it's exponential nature captured

}


double error(double integral){
	//Function to calculate the absolute error i.e. the divergence from the expected value
	
	const double value = 1024;
	double absolute_error = fabs(integral - value);
	return absolute_error;

}
