#!/bin/bash

rm pb2.dat # Fail safe to remove pre-existing files
rm *.o1* # Remove pre-existing output files
rm *.e* # Remove pre-existing error files

echo "Solving Problem 2..."

mpicc -no-pie -fopenmp secret_function.o -Wall -std=c99 -o pb2 problem2.c -lm -O3

#mpirun -np 4 pb2 10 >> pb2.dat #DON'T USE mpirun for this problem on Stampede

#ibrun -np 4 pb2 10 >> pb2.dat #Run to figure out the walltime

ibrun -np 4 pb2 36765 >> pb2.dat #Generate output

echo "Problem 2 Solved!"

echo "Completed!"

