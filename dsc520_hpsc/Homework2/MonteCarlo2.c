#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
//#include <cmath.h>
//#include <conio.h>


double random_number();                                      //Generate a uniform random number
double indicator(double x,double y);                         //Function to store the integrand i.e. the indicator function
//double likelihood(double x1, double x2);		     //Integrand for the likelihood estimator
double monteCarlo(double lower, double upper, int N); 	     //A function to approximate the MonteCarlo integration
double error(double integral); 				     //Calculate the error

int main(int argc, char **argv){
	
	srand(time(NULL)); //randomize seed //Remember to mention this before invoking a random variable

	
	double lower, upper;
	lower = -1.0;
	upper = 1.0;
	unsigned long long N = atoll(argv[1]);	//Cognizant with the keyword argument argv to intake a user input //DON'T USE <conio.h> package !!!
	
	 									                                                                                        
	double estimate = monteCarlo(lower,upper,N); //Monte-Carlo Approximation
	//printf("%i   %.4f\n",N,estimate);


	double ae = error(estimate); // Error output


	printf("%llu  %.6f  %.6f\n",N,estimate, ae); 	//Simplify for .dat output
	
	return 0;
}


double random_number(){
	  //Generate uniform random number

	  return (double)rand()/((double)RAND_MAX+1);
}



double indicator(double x, double y){
	//This function will be used to pass the integrand that we need to approximate
		
        double dist = x*x + y*y; //Calculate the distance from the origin. In our case, the origin is at the centre of the 2-D plane

	if(dist <= 1){
		//printf("The point lies on the disc \n");
		return 1;
	}
	else{
		//printf("The point lies outside the disc \n");
		return 0;
	}
}

//double likelihood(double x1, double x2){
	//Integrand for the likelihood estimator	
//	return exp(-((1-x1)*(1-x1)) - 100*((x2 - x1*x1) * (x2 - x1*x1)));
//}

double monteCarlo(double lower, double upper, int N){
	        //General function to execute the Monte Carlo Approximation
		//Can be used for any integrand and any bound
		
		double apx = 0.0;  //This will store the approximate of the integrand
		double sum = 0.0;  //This stores the summation value
		
			for(int i = 0; i<N; i++){
				for(int j = 0; j < 2; j++){
				//Generate a random number within the limits of the integration
				double rand_num = random_number() - 1;
			       	double rand_num2 = random_number() - 1;	//The subtraction is a fail-safe to ensure bound adherence
				double func_value = indicator(rand_num,rand_num2);  //Pass the requisite random number into the integrand i.e. the parent function
				sum+=func_value; //Calculation of the summation
			}
	       }

	       return apx = (upper - lower) * (sum/N); //Returns the integral value
}


double error(double integral){
	//Function to calculate the absolute error i.e. the divergence from the expected value
	
	const double pi = 3.141592653589793;
	double absolute_error = fabs(integral - pi);
	return absolute_error;
}
