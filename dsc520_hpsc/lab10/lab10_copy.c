#include <stdio.h>
#include <mpi.h>

int main(int argc, char **argv){

	int rank,size;
	MPI_Init(&argc,&argv); //Initialize MPI
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);  
	MPI_Comm_size(MPI_COMM_WORLD, &size);

	//Rank and size are defined on a global scope for it to work in MPI
	//the above two lines need to be present post initialization
	
	double send_number = 9.0;
	double rec_number;
	MPI_Status status;

	double process_local_max = 9;
	double process_global_max = -100;
	MPI_Reduce(&process_local_max, &process_global_max, 1, MPI_DOUBLE, MPI_MAX, 0 , MPI_COMM_WORLD);
	printf("Process ID %i, x = %f, max x = %f\n", rank, process_local_max, process_global_max);

	MPI_Finalize(); //for graceful shutdown
	return 0;
}
