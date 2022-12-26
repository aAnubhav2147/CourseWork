#!/bin/bash

rm likelihood.dat

gcc -std=c99 -o lkl Likelihood.c -lm -O3

echo "Compiling Likelihood..."

for j in {2..9}
do
	#echo $j
	./lkl $((10**j)) >> likelihood.dat
done
echo "Likelihood compiled!"

echo "Calculating Program Time..."

gcc -std=c99 -o lkl_time Likelihood_time.c -lm -O3

for t in {2..9}
do
		\time -f "Program: %C\nTotal time: %E\nUser Mode (s) %U\nKernel Mode (s) %S\nCPU: %P" ./lkl_time $((10**t)) &>> lkl_time.dat
done

echo "Completed!"

