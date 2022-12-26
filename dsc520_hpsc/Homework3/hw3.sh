#!/bin/bash

echo "Initializing..."

#rm *.dat # Fail safe to remove pre-existing files

rm mc3.dat
rm mc3_omp.dat

echo "Pre-existing files removed!"

echo "Compiling MonteCarlo Serial Code..."

gcc -std=c99 -o mc3 MonteCarlo3.c -lm -O3 # The -O3 tag is for optimized compilation and quicker run times

#for x in {1..5} # Outer for loop to execute the desired number of iterations
#do
	#echo "Running Monte Carlo iteration x = "$x

for i in {2..10} # Inner for loop to execute the program conditions and generate
do		 # file output basis the number of iterations run
		
	#./mc3 $((10**i)) >> mc2_$x.dat # Generates the 'x' number of files post 'x' iterations
	./mc3 $((10**i)) >> mc3.dat
done

#done
echo "MonteCarlo Serial Code compiled!"


echo "Parallelizing Monte Carlo Serial Code..."

gcc -std=c99 -fopenmp -o mc3_omp MonteCarlo3OMP.c -lm -O3

for i in {2..8}
do               
	./mc3_omp $((10**i)) >> mc3_omp.dat
done

echo "Parallelization Complete!"

#conda deactivate
#echo "Conda Deactivated!"

#echo "Solving Problem 3..."

#mpicc -o mc3_omp2 MonteCarlo3OMP2.c -lm -O3

#for i in {2..7}
#do
# 	./mc3_omp2 $((10**i)) >> mc3_omp2.dat
#done

#echo "Problem 3 solved!"


#echo "Calculating Program Time..."

#gcc -std=c99 -o mc2_time MonteCarlo2_time.c -lm -O3

#for t in {2..10}
#do
#	\time -f "Program: %C\nTotal time: %E\nUser Mode (s) %U\nKernel Mode (s) %S\nCPU: %P" ./mc2_time $((10**t)) &>> mc2_time.dat
#done

#conda activate
#echo "Conda Reactivated!"

echo "Completed!"
