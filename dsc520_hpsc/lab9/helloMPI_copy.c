#include <stdio.h>
#include <stdlib.h>
#include <mpi.h> //include mpi library header
#include <time.h>
#include <math.h>

//will sample any dim-dimensional box of size [a,b]^dim
//the random point is stored in the array x

void sample_rand(const double a, const double b, const int dim, double *x){
	for(int i=0; i<dim;i++){
		double tmp = ((double) rand())/((double) RAND_MAX);
		x[i] = (b-a)*tmp + a;
	}
}

double f(double *x){
	double x1 = x[0];
	double x2 = x[1];
	return exp(-(1-x1) * (1-x1) -100.*(x2-x1*x1)*(x2-x1*x1));
}

int main(int argc, char **argv){
	int rank, size;

	MPI_Init(&argc, &argv); //initializes MPI
	MPI_Comm_rank(MPI_COMM_WORLD, &rank); //get current MPI-process ID. 0,1,....
	MPI_Comm_size(MPI_COMM_WORLD, &size); //get total number of processes
	//printf("Hello World from process %d of %d\n", rank,size);
	
	long N = atol(argv[1]);	
	srand(time(NULL)*rank); //each MPI process has unique thread

	const int dim = 2; //dimension of function to optimize (x1,x2)
	double x[dim];

	//search for the function's maximum
	double max = -1;
	for(long int i=1; i<=N; i++){
		sample_rand(-5,5,dim,x);
		double f_i = f(x);
		if(f_i > max){
			max = f_i;
		}
	}
	
	printf("local max = %1.5e\n",max);

	MPI_Finalize(); //programs should always perform a graceful shutdown
	return 0;
}

