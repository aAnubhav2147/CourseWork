//Character conversion

#include <stdio.h>
#include <stdlib.h>
int main(int argc, char **argv){
	printf("argc = %d\n", argc);
	printf("argv[0] = %s\n", argv[0]);
	printf("argv[1] = %s\n", argv[1]);
	double x = atof(argv[1]); //code here to convert input to a double
	printf("x = %f\n", x);

	//code to write
	if(x<2){
		printf("Low CPU usage!\n");
	} else if (x>10){
		printf("High CPU usage!\n");
	} else {
		printf("Moderate CPU usage!\n");
	}
	return 0;
}
