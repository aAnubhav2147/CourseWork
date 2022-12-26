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

	//The next if-statement implements the ring topology
	//the last process ID is size-1, so the ring topology is: 0->1, 1->2,...,size-1->0
	//rank 0 starts the chain of events by passing to rank 1

	if(rank==0){
		//only the process with rank ID = 0 will be in this block of code
	
		//send data to process 1
		MPI_Send(&send_number, 1, MPI_DOUBLE, 1, 0, MPI_COMM_WORLD);

		//receive data from process size-1
		MPI_Recv(&rec_number, 1, MPI_DOUBLE, size-1, 0 , MPI_COMM_WORLD, &status);
	}

	else if(rank==size-1){
		//receive data from process rank-1 (it "left" neighbor)
		MPI_Recv(&rec_number, 1, MPI_DOUBLE, rank-1, 0, MPI_COMM_WORLD, &status);

		//send data to its "right neighbor", rank 0
		MPI_Send(&send_number,1, MPI_DOUBLE, 0,0,MPI_COMM_WORLD);
	}

	else {
		//receive data from process rank-1 ("left neighbor")
		MPI_Recv(&rec_number, 1, MPI_DOUBLE, rank-1,0,MPI_COMM_WORLD, &status);

		//send data to its "right neighbor" (rank + 1)
		MPI_Send(&send_number, 1, MPI_DOUBLE, rank + 1, 0, MPI_COMM_WORLD);
	}

	printf("Process %i send %1.3e and received %1.3e\n", rank, send_number, rec_number);

	MPI_Finalize(); //for graceful shutdown
	return 0;
}
