#!/bin/bash

rm *.dat # Failsafe to remove pre-existing files

echo "Executing Parallel..."

Rscript rf_fp_parallel.R >> Parallel_Output.dat

echo "Completed!"
