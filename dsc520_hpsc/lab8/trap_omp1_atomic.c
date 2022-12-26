#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <omp.h>
//#include <conio.h>

double error(double integral){
	const double pi = 3.141592653589793;
	double absolute_error = fabs(integral - pi / 2. );
	return absolute_error;
}

double func(double x){
	return 1.0/(1.0 + x*x); //Integrand of the trapezoidal rule
	// return 1 		//ideally return 2 for [-1,1]
}

int main(int argc, char **argv){
	omp_set_num_threads(4);
	double x0 = -1;
	double x1 = 1;
	int N = atoi( argv[1] ); //convert input to integer; atoi is part of stdlib.h

	double h = 2.0/(N-1.0);

	double s = func(x0)+func(x1);

	#pragma omp parallel for private(s,h)
  	for(int i = 1;i<N;i++){
		#pragma omp atomic
		s+= 2.0*func(x0+i*h); //Calculate the integral
	}

	double ae = error((h/2)*s);

	printf("thread %i s=%f  h=%f\n  N=%i\n  Error=%1.15f\n", omp_get_thread_num(),s,h,N,ae);
	
	//printf("Answer = %f\n", (h/2)*s);
	
	//double ae = error((h/2)*s); 	// Error output

	//printf("Error = %e\n", ae);
	
	//printf("%i %1.15f\n",N, ae); 	//Simplify for .dat output
	
	return 0;
}



