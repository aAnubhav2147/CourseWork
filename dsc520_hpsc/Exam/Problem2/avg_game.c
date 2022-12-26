#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <omp.h>
#include<time.h>

int dice();

int main(int argc, char **argv){

	srand(time(NULL)); //Set a random seed

	int N = atoi( argv[1] );

	int player_roll = dice(); //Roll the dice

	int winnings = 0; //Store the winning count

	double integral_thread[4] = {0}; //Initialize all threads to 0

	double x = 0;  //Update the ID
	for(int j=0; j<4;j++){
		x = x + integral_thread[j];
	}

	#pragma omp parallel for shared(player_roll, winnings)
	
		for(int i = 1; i<N; i++){

			int id = omp_get_thread_num();

			//int winnings = 0;

			if(id <= player_roll){
				winnings++;
				printf("Player %i, rolls %i, wins money\n", id, player_roll);
			} else {
				winnings--;
				printf("Player %i, rolls %i, lost money\n", id, player_roll);
			}

			//printf("Team Winnings = %i" , winnings);
		}
	

	printf("Avg. Team Winnings = %i\n" , winnings/N);

}

int dice(){
	
	int roll = 0; //Store the rolled value

	int sides = 6; //A regular six-sided dice
	
	for (int i = 0; i < sides; i++){
		
		roll = (rand() % sides) + 1; //simulate a dice roll
	}

	return roll;
}

