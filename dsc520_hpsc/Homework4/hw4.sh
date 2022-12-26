#!/bin/bash

rm pb1.dat # Fail safe to remove pre-existing files

echo "Compiling Test..."

mpicc -no-pie secret_function.o -Wall -std=c99 -o program program.c -lm -O3

	./program >> test.dat

echo "Test Compiled!"


echo "Solving Problem 1 parts (b) & (c)..."

mpicc -no-pie -fopenmp secret_function.o -Wall -std=c99 -o pb1 problem1.c -lm -O3

#for i in {1..2} # For loop to execute the program conditions and generate; here it is to generate the number of samples
#do

	mpirun -np 4 pb1 4 >> pb1.dat  #$((i)) >> pb1.dat # Generate output

#done

echo "(b) & (c) Solved!"


echo "Completed!"
