#!/bin/bash
MYPID=125
echo "PID IS" $MYPID
ps -p $MYPID -o comm

while [ "1" == "1" ]
do
	x=`date +%s`
	y=`ps -p $MYPID -o %cpu | tail -n 1`
	sleep 1
	echo $x $y
	./saveInput $y
done
