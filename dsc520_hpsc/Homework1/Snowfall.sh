#!/bin/bash


for value in {1..30}
do
	#echo $value
	./sf $((2**value))
done
