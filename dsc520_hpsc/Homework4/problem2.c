#include <stdlib.h>
#include <stdio.h>
#include <mpi.h>
#include <omp.h>
#include <math.h>
#include <time.h>
#include <unistd.h>
#include "secret_function.h"

void random_number(double lower, double upper, int dim, double *x);  //Generate a uniform random number

int main(int argc, char **argv){

	double start, end, total_time;

	unsigned long long N = atoll(argv[1]); //No. of samples //User Input

	int rank,size;
	// rank = unique process ID for this process (0,1,2,...)
	// size = total number of processes
	
	MPI_Init(&argc, &argv); //Connects all MPI processes
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	MPI_Comm_size(MPI_COMM_WORLD, &size);
	//MPI_Status status;
	
	srand(time(NULL) * rank); //Random seed. The multiplication ensures that each MPI process gets an unique seed

	start = omp_get_wtime();
	
	const int dim = 2;
	double x[dim]; //array of random numbers

	double local_min = RAND_MAX;

	for(int i=0; i<N; i++){

		random_number(-512.,512.,dim,x);

		double f = secret_function(x[0],x[1]); //Output of secret function

 		if(f<=local_min){
			local_min = f; //Swap the value of with the local minimum
			printf("Process %i , sample %llu, num1 = %lf\n", rank,N,x[0]); 
			printf("Process %i , sample %llu, num2 = %lf\n", rank,N,x[1]);
			printf("Process %i of %i local min = %lf \n", rank,size,f);
		} else {
			printf("Still searching for minimum value...\n");
		}

	}

	//printf("Process %i ,Local min = %lf\n", rank,local_min); //Brute-Force debugging
	
	end = omp_get_wtime();

	total_time = end - start;

	printf("Total time taken = %.2lfs\n", total_time);


	MPI_Barrier(MPI_COMM_WORLD); //To prevent a stray thread from reaching the reduction region before computing f


	//MPI Reduction Region
	
	double rr_start, rr_end, rr_total_time;

	rr_start = omp_get_wtime();
	
	double process_global_min;

	MPI_Reduce(&local_min, &process_global_min, 1, MPI_DOUBLE, MPI_MIN, 0 , MPI_COMM_WORLD); //Get the swapped minimum value to be the Global Minimum

	printf("Process %i , sample %llu, Global Min = %lf\n", rank, N, process_global_min);

	rr_end = omp_get_wtime();

	rr_total_time = rr_end - rr_start;

	printf("Total Reduction time = %.2lfs\n", rr_total_time);

	MPI_Finalize(); //Terminate MPI processes and do a graceful exit

	return 0;
}

void random_number(double lower, double upper, int dim, double *x){
	//Generate uniform random number
	
		for(int i = 0; i<dim; i++){

			double tmp = (double)rand()/((double)RAND_MAX+1);
		
			x[i] = (upper - lower) * tmp + lower;
		}
}
