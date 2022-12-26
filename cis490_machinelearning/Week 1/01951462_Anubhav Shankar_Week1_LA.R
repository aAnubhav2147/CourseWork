cat("\f") # Clear the console prior to running the script

# Set the working directory
setwd("C:/Users/anubh/Desktop/Course Requisites/Spring 2022/Machine Learning CIS490/Week 1")
getwd()

# Load the data and do some exploration
iris_data <- data.frame(iris)
head(iris) # Get a glimpse of the first 5 rows
tail(iris) # Get a glimpse of the last 5 rows
attach(iris) # Keeps the data frame attached to the console work space. Makes it easier to use fields without needing the $ sign
table(Species) # Pivot the data frame basis one or multiple fields
names(iris) # Returns all the field names comprising the data frame
View(iris) # View the data frame
summary(iris) # View the summary stats of the data

library("e1071") # Call an installed package

# Some basic plots
hist(Petal.Length,xlab = "Sepal Length",ylab = "Frequency",main = 'Sepal Length Frequency',col = "blue") # Histogram

plot(Petal.Width,Sepal.Width,col = "black",xlab = "Petal Width", ylab = "Sepal Width") # Simple Scatter Plot
abline(lm(Petal.Width~Sepal.Width,col = "blue",lwd = 2)) # Introduces a regression line

plot(Species,Sepal.Width,col = "black") # Box Plot to identify outliers

