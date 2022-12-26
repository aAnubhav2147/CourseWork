#include <stdio.h>
#include <mpi.h> //include mpi library header

int main(int argc, char **argv){
	int rank, size;

	MPI_Init(&argc, &argv); //initializes MPI
	MPI_Comm_rank(MPI_COMM_WORLD, &rank); //get current MPI-process ID. 0,1,....
	MPI_Comm_size(MPI_COMM_WORLD, &size); //get total number of processes
	printf("Hello World from process %d of %d\n", rank,size);
	MPI_Finalize(); //programs shoul always perform a graceful shutdown
	return 0;
}

