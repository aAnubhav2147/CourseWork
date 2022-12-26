#!/bin/bash

echo "Initializing..."

rm mc3_omp2_*.dat # Fail safe to remove pre-existing files

echo "Pre-existing files removed!"

echo "Solving Problem 3..."


echo "Solving with Reduction Approach & Strong Scaling..."

#mpicc -fopenmp -o mc3_omp2 MonteCarlo3OMP2.c -lm -O3
gcc -fopenmp -o mc3_omp_reduction MonteCarlo3OMP_reduction.c -lm -O3

#for i in {2..7}
#do
 	#./mc3_omp2 $((10**i)) >> mc3_omp2.dat
	./mc3_omp_reduction >> mc3_omp2_reduction.dat
#done

echo "Reduction Approach Complete!"


echo "Solving with Critical Approach & Strong Scaling..."

gcc -fopenmp -o mc3_omp_critical MonteCarlo3OMP_critical.c -lm -O3
  ./mc3_omp_critical >> mc3_omp2_critical.dat

echo "Critical Approach Complete!"


echo "Solving with Critical Approach & Weak Scaling..."
gcc -fopenmp -o mc3_omp_critical_ws MonteCarlo3OMP_critical_ws.c -lm -O3
  ./mc3_omp_critical_ws >> mc3_omp2_critical_ws.dat

echo "Critical Approach with Weak Scaling Solved!"


echo "Solving Part d..."

gcc -fopenmp -o mc3_omp2_pd MonteCarlo3OMP_critical_partd.c -lm -O3

for i in {1..15}
do
        ./mc3_omp2_pd $((4**i)) >> mc3_omp2_partd.dat
done

echo "Part d Solved!"


echo "Problem 3 solved!"


echo "Completed!"
