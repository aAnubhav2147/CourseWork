#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>
#include <omp.h>   //Required for OpenMP compilation
//#include <mpi.h>   //Include for MPI compilation

double random_number();                                      //Generate a uniform random number
double func(double *x, int dim);                             //Function to store the integrand
double monteCarlo(double lower, double upper, int N); 	     //A function to approximate the MonteCarlo integration
//double error(double integral); 		 	     	     //Calculate the error i.e. divergence from expected value

// Does a Strong Scaling Test and uses the Reduction Parallelization approach

int main(int argc, char **argv){

	const int d = 63;
	
	double thread_count[63] = {0}; //Initialize all threads to 0
	
	double start, end, total_time;
	
	//int rank,size;
	//MPI_Init(&argc, &argv); //initializes MPI
	//MPI_Comm_rank(MPI_COMM_WORLD, &rank); //get current MPI-process ID. 0,1,....
	//MPI_Comm_size(MPI_COMM_WORLD, &size); //get total number of processes

	//srand(time(NULL) * rank);  //random seed 
	start = omp_get_wtime();    //Start OMP timer	
	srand(time(NULL));	    //Remember to mention this before invoking a random variable 
			  	   //Each thread will get its own unique seed

	double lower, upper;
	lower = -0.5;
	upper = 0.5;
	//unsigned long long N = atoll(argv[1]);	//Cognizant with the keyword argument argv to intake a user input //DON'T USE <conio.h> package !!!
	unsigned long long N = pow(10,6);
	
	omp_set_num_threads(63);	 //Initialize number of threads to be allocated
	
	double estimate;
	//double ae;
	
	#pragma omp parallel
	{
		int id = omp_get_thread_num();

		estimate = monteCarlo(lower,upper,N); //Monte-Carlo Approximation

		thread_count[id] += estimate;

		//ae = error(estimate); // Error output
	
		end = omp_get_wtime(); //Stop OMP timer

		total_time = end - start;
		
		//printf("ID  N  Value  t");
		printf("%i  %llu  %.6f  %.2f\n",id,N,estimate,total_time); //Simplify for .dat output

	}
	
	double integral = 0.0;
	for(int i=0; i<d; i++){
		integral += thread_count[i];
	}

	//MPI_Finalize(); //programs should always perform a graceful shutdown
	
	return 0;
}


double random_number(){
	  //Generate uniform random number

	  return (double)rand()/((double)RAND_MAX);
}



double func(double *x, int dim){
	//This function will be used to pass the integrand that we need to approximate
	
	double value = 0.0;
	#pragma omp parallel for //private(value)
	for(int i=1; i<dim; i++){
		
		value += exp(-(((1-x[i]) * (1-x[i])) + 100 * ((x[i+1] - x[i]*x[i]) * (x[i+1] - x[i]*x[i]))));

		//We need to use the array *x here because we need to sum N different variables and then do
		//a N-dimensional integration i.e. integrate the function N times.
	}

	return value;
        
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
				//x[j] = (2 * random_number()) - 1; //This basically ensures the integration happens 'dim' times

				x[j] = random_number() - 1;
				//x[j] = 0; //Test case to check if the integrand is correct

				//printf("%f ", x[j]);  //For brute-force debugging
			}

			double func_value = func(x,dim);  //Pass the requisite random number into the integrand i.e. the parent function
			//printf("%f ", func_value); //For brute-force debugging
			sum+=func_value; //Calculation of the summation

	       	}

	       //printf("%f ",pow(2,dim)); //For brute-force debugging

	       return pow(2,dim) * (sum/N); //Returns the integral value //Here the V is for a 10-dimensional box so, 
	       				   //we need to have it's exponential nature captured

}


//double error(double integral){

//	const double value = 1024; //Expected value of convergence post integration
//	double absolute_error = fabs(integral - value); //Calculate the divergence from the expected value
//	return absolute_error;

//}
