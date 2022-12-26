#/!bin/bash

echo "Initializing..."

rm *.dat # Fail safe to remove pre-existing files

echo "Pre-existing files removed!"

echo "Compiling..."
gcc -std=c99 -fopenmp -o game game.c -lm
gcc -std=c99 -fopenmp -o avg_game avg_game.c -lm
echo "Compiled!"

for x in {1..2} # Outer for loop to execute the desired number of iterations
do
	echo "Game = "$x

	for i in {1..4} # Inner for loop to execute the program conditions and generate
	do		 # multiple file output basis the number of iterations run
		#echo $i
		./game $((i)) >> game_$x.dat # Generates the 'x' number of files post 'x' iterations
		./avg_game $((2**i)) >> avg_game_$x.dat
	done
done

echo "Completed!"
