cat("\f") # Clear Console
rm(list = ls()) # Remove any previously saved variables
setwd("C:/Users/anubh/Desktop/Course Requisites/Fall 2022/MTH 522 - Advanced Mathematical Stats") # Set working directory
getwd()

############### Import required packages ####################

library(UsingR)
library(dplyr)
library(ggplot2)
library(ggpubr)
library(rmarkdown)
library(knitr)

fason <- father.son # Load the data and save it into an object
glimpse(fason) # Get a quick overview of the data
str(fason) # Same as glimpse()
head(fason) # Preliminary exploration


# fason$color = "black"
# str(fason)
# fason$color[mean(fason$fheight)] = "red"
# fason$color[mean(fason$sheight)] = "blue"

plot(fason$fheight, fason$sheight, xlab = "Father's Height(in.)", ylab = "Son's Height(in.)", 
     pch = 20) + title("Height Comparison") # Create a simple Scatter Plot 



model_fit <- lm(fason$sheight ~ fason$fheight) # Create a simple Linear Regression Model
summary(model_fit) # Get the model statistics


################## Plot a revised scatter plot using ggplot2 ###############################

ggplot(fason,aes(x = fheight, y = sheight)) + # This line initiates the plotting function
geom_smooth(method = "lm", color = "red", size = 2) + # This line creates the regression line
geom_point() + # This line generates the points of the scatter plot
points(mean(fason$fheight),mean(fason$sheight)) + # This line basically sets the coordinates
geom_vline(xintercept = mean(fason$fheight),color = "green") + # Adds a vertical line passing through the mean of father's height
geom_hline(yintercept = mean(fason$sheight),color = "green") + # Adds a vertical line passing through the mean of son's height
geom_abline(slope = model_fit$coefficients[2],intercept = model_fit$coefficients[1],color = "blue",size = 1) + # This line creates the SD line by getting the intercept and slope from the regression model
xlab("Father's Height") + # Adds a x-axis label
ylab("Son's Height") + # Adds a y-axis label
title("Height Comparison") # Adds a title to the plot
