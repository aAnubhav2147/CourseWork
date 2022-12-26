#include <stdio.h>
#include <math.h>
#include <stdlib.h>

double bankbalance(int N);

int main(int argc, char **argv){
		
	int N = atoi(argv[1]);

	//for(int i = 0; i<N; i++){

	double closing = bankbalance(N); //Update statement

	printf("Closing Statement for Day %i  %.2f\n" , N, closing);

	//}

	return 0;
}

double bankbalance(int N){

	double start = 0.0; //Starting value

	double day1 = start + 0.01; //Day 1 balance

	double end = 0.0;

	for (int i = 0; i<N; i++){
		
		end += 2 * day1; //Deposit money
	}

	return end;
}
	
