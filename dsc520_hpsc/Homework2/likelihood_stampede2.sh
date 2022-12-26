#!/bin/bash

rm lkl_time_stampede2.dat

#Calculate runtime in Stampede2

echo "Calculating Program Time..."

gcc -std=c99 -o lkl_time_stampede Likelihood_time.c -lm -O3 # The -O3 is for optimized and quicker compilation and run time

for t in {2..7}
do
	\time -f "Program: %C\nTotal time: %E\nUser Mode (s) %U\nKernel Mode (s) %S\nCPU: %P" ./lkl_time_stampede $((10**t)) &>> lkl_time_stampede2.dat
done

echo "Completed!"
