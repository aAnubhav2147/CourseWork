#include <stdio.h>
int main(){
	int i = 5;
	printf("Value of i = %d\n", i);
	printf("Address of i = %p\n", &i);
	printf("Value of i = %d\n", *(&i));
	return 0;
}
