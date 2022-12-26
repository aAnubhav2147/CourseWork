#include <stdlib.h>
#include <stdio.h>
#include <mpi.h>
#include <math.h>
#include <time.h>
#include <unistd.h>
#include "secret_function.h"

int main(int argc, char **argv){

	double test = secret_function(512.,404.2319);
	printf("secret_function(512.,404.2319) = %lf\n",test);
	return 0;
}
