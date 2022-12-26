#!/bin/bash

rm mc2_*.dat # Fail safe to remove pre-existing files

echo "Compiling MonteCarlo 2..."

gcc -std=c99 -o mc2 MonteCarlo2.c -lm -O3 # The -O3 tag is for optimized compilation and quicker run times

for x in {1..5} # Outer for loop to execute the desired number of iterations
do
	echo "Running Monte Carlo iteration x = "$x

	for i in {2..10} # Inner for loop to execute the program conditions and generate
	do		 # multiple file output basis the number of iterations run
		#echo $i
		./mc2 $((10**i)) >> mc2_$x.dat # Generates the 'x' number of files post 'x' iterations
	done

done
echo "MonteCarlo 2 compiled!"

echo "Calculating Program Time..."

gcc -std=c99 -o mc2_time MonteCarlo2_time.c -lm -O3

for t in {2..10}
do
	\time -f "Program: %C\nTotal time: %E\nUser Mode (s) %U\nKernel Mode (s) %S\nCPU: %P" ./mc2_time $((10**t)) &>> mc2_time.dat
done

echo "Completed!"
