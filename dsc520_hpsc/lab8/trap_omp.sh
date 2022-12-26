#!/bin/bash

rm trap_*.dat

gcc -std=c99 -fopenmp -o trap_omp1 trap_omp1.c
gcc -std=c99 -fopenmp -o trap_omp1_atomic trap_omp1_atomic.c
gcc -std=c99 -fopenmp -o trap_omp2 trap_omp2.c
gcc -std=c99 -fopenmp -o trap_omp1_time trap_omp1_time.c
gcc -std=c99 -fopenmp -o trap_omp1_atomic_time trap_omp1_atomic_time.c
gcc -std=c99 -fopenmp -o trap_omp2_time trap_omp2_time.c

for value in {1..30}
do
	#echo $value
	./trap_omp1 $((2**value)) >> trap_omp1.dat
	./trap_omp1_atomic $((2**value)) >> trap_omp1_atomic.dat
	./trap_omp2 $((2**value)) >> trap_omp2.dat
done

for t in {1..30}
do
	\time -f "Program: %C\nTotal time: %E\nUser Mode (s) %U\nKernel Mode (s) %S\nCPU: %P" ./trap_omp1_time $((2**t)) &>> trap_omp1_time.dat
	\time -f "Program: %C\nTotal time: %E\nUser Mode (s) %U\nKernel Mode (s) %S\nCPU: %P" ./trap_omp1_atomic_time $((2**t)) &>> trap_omp1_atomic_time.dat
	\time -f "Program: %C\nTotal time: %E\nUser Mode (s) %U\nKernel Mode (s) %S\nCPU: %P" ./trap_omp2_time $((2**t)) &>> trap_omp2_time.dat
done
