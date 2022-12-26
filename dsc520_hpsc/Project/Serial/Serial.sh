#!/bin/bash

rm *.dat #Failsafe to remove pre-existing files

echo "Running Serial Code..."

Rscript rf_fp_serial.R >> Serial_Output.dat

echo "Completed!"

