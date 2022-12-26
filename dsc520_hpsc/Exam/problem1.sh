#!/bin/bash

echo "Initializing..."

echo "Removing existing files"
rm *.dat # Remove files to prevent overwriting
echo "Existing files removed!"

echo "Compiling Program..."
gcc -std=c99 -o pennies pennies.c -lm #Compile Program

echo "Solving Problem1..."

for value in {1..4} # For loop for N = 4
do
	#echo $value
	./pennies $((value)) >> pb1f4.dat
done

for value in {1..10} # For loop for N = 10
do
	#echo $value
	./pennies $((value)) >> pb1f10.dat
done

for value in {1..30}
do
	#echo $value
	./pennies $((value)) >> pb1f30.dat
done

echo "Problem 1 Solved!"
