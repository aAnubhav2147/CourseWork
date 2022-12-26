#!/bin/bash

for value in {1..30}
do
	#echo $value
	./trap $((2**value))
done
