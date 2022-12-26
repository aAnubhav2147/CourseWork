#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include <omp.h>  //Required for OpenMP compilation


double random_number();                                      //Generate a uniform random number
double func(double *x, int dim);                             //Function to store the integrand
double monteCarlo(double lower, double upper, int N); 	     //A function to approximate the MonteCarlo integration
double error(double integral); 				     //Calculate the error i.e. divergence from expected value

int main(int argc, char **argv){
	
	//double thread_count[4] = {0}; //Initialize all threads to 0
	
	double start,end, total_time; //For measuring the program's walltime
	start = omp_get_wtime();  //Start the OMP timer
	srand(time(NULL)); 	 //random seed 
			   	//Remember to mention this before invoking a random variable 
			       //Each thread will get its own unique seed

	double lower, upper;   //Integral bounds
	lower = -1.0;
	upper = 1.0;
	unsigned long long N = atoll(argv[1]);	//Cognizant with the keyword argument argv to intake a user input //DON'T USE <conio.h> package !!!
	
	omp_set_num_threads(4);	 //Initialize the number of threads to be allocated

	double estimate;
	double ae;
	
	#pragma omp parallel
	{
	
		estimate = monteCarlo(lower,upper,N); //Monte-Carlo Approximation

		ae = error(estimate); // Error output

		end = omp_get_wtime(); //Stop the OMP timer

		total_time = end - start;
	
		printf("thread %i  %llu  %.6f  %.6f\n  Total time taken -> %.2f s\n",omp_get_thread_num(),N,estimate, ae, total_time); //Simplify for .dat output
	}
	
	return 0;

}


double random_number(){
	  //Generate uniform random number

	  return (double)rand()/((double)RAND_MAX);
}



double func(double *x, int dim){
	//This function will be used to pass the integrand that we need to approximate
	
	double sum = 0.0;
	#pragma omp parallel for private(sum)
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
		double sum = 0.0;   //This stores the summation value
		const int dim = 10; //Indicates the number of dimensions i.e. the number of times the integrand needs to be integrated

		double x[dim]; //Creates "dim" dimensional array
		#pragma omp parallel for
		for(int i = 0; i<N; i++){ //Outer for-loop repeats the integration for N trials laid down in the shell script 
			//#pragma omp parallel for private(j)
			#pragma omp parallel for
			for(int j = 0; j < dim; j++){ //Inner for loop basically creates the "dim" dimensional box and integrates the function "dim" times
				//Generate a random number within the limits of the integration
				//The subtraction is a fail-safe to ensure bound adherence

				x[j] = (2 * random_number()) - 1; //This basically ensures the integration happens 'dim' times

				//printf("%f ", x[j]);  //For brute-force debugging

			}

		double func_value = func(x,dim);  //Pass the requisite random number into the integrand i.e. the parent function
		//printf("%f ", func_value); //For brute-force debugging
		sum+=func_value; //Calculation of the summation

	       	}

	       //printf("%f ",pow(2,dim));  //For brute-force debugging

	       return pow(2,dim) * (sum/N); //Returns the integral value //Here the V is for a 10-dimensional box so, 
	       				    //we need to have it's exponential nature captured

}


double error(double integral){

	const double value = 1024; //Expected value of convergence post integration
	double absolute_error = fabs(integral - value); //Calculate the divergence from the expected value
	return absolute_error;

}
