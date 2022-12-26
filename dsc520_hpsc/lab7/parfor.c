#include <omp.h>
#include <stdio.h>
#include <math.h>

int main(){
	int N = 128;
	//int N = 1000000;
	float a[N];
	float b[N];

for (int j=0; j<N; j++){
	b[j] = j + 1;
}

#pragma omp parallel
{
	//float a[N]; //DUMBASS MISTAKE!!
	for (int i = 0; i < N; i++){
		printf("Thread %d working on component %d\n", omp_get_thread_num(),i);
		a[i] = sqrt(b[i]);
	}
}

for (int j = 0; j < N; j++){
	printf("square root of %f is = %f\n", b[j], a[j]);
}

return 0;
}
