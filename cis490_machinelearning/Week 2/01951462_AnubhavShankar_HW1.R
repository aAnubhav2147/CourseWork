# Clear workspace and environment
rm(list=ls())
cat("\f")


# Load a required packages
library("MASS")
library(moments)


# Generate a pre-set covariance matrix
S <- matrix(c(4,3,2,3,9,5,2,5,36),3,3)
row_names <- c("x1","x2","x3")
col_names <- c("x1","x2","x3")
rownames(S) <- row_names
colnames(S) <- col_names

# Create a vector containing the means
m1 <- c(2,4,6)

# Set the required seed
set.seed(490)

# x1 <- rnorm(10,2)
# x2 <- rnorm(10,4)
# x3 <- rnorm(10,6)
# cov(x1,x2,x3)

x <- mvrnorm(10,m1,S) # A randomly generated set

mean(x) # Calculate the Expected Value
median(x) # Calculate the median

skewness(x) # Calculate the skewness of the random variables
kurtosis(x) # Calculate the kurtosis of the random variables

cov(x) # Generate the covariance matrix of the random variables
cor(x) # Generate the correlation matrix of the random variables