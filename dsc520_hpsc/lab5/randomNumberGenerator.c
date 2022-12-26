#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>

double randGenerator(double lower, double upper, double N){
	
	double rand_num;

	
	rand_num = (double)rand()/((double)RAND_MAX+1);
	

	return rand_num;
}

int main(int argc, char **argv){

	srand(time(NULL)); //setting the seed

	double lower, upper, N;

	lower = 0;
	upper = 1;
	N = atoi(argv[1]);
	
	//FILE *fp;

	
	double ans;

	for(int i = 0; i < N; i++){

		ans = randGenerator(lower, upper, N);
	
		//return ans;
		printf("%1f\n", ans);

	}

	return ans;

	//return 0;
}


