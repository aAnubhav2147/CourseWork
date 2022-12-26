wd <- "C:/Users/anubh/Desktop/Course Requisites/Spring 2022/Machine Learning CIS490/Week 2"
setwd(wd)
cars <- read.csv("auto-mpg.csv")
View(cars)
set.seed(123)
index <- sample(1:nrow(cars),0.8*nrow(cars),replace = FALSE)
cars_train <- cars[index,]
View(cars_train)
cars_test <- cars[-index,]
View(cars_train)
linear_model <- lm(mpg~weight, data = cars_train)
summary(linear_model)
plot(linear_model)
mse <- mean(residuals(linear_model)^2)
mse
rmse <- sqrt(mse)
linear_model_pred <- predict(linear_model,cars_test)
ssl <- sum((cars_test$mpg-linear_model_pred)^2)
summary(linear_model_pred)
plot(mpg~weight, data = cars_test)
abline(linear_model)
View(cars_train)
multi_lm <- lm(mpg~cylinders+displacement+weight+acceleration+model.year,data = cars_train)
summary(multi_lm)
