#!/bin/bash


for value in {1..30}
do
	#echo $value
	./montecarlo $((2**value))
done
