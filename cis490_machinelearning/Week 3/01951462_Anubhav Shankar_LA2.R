# Set the requisite Working directory

wd <- "C:/Users/anubh/Desktop/Course Requisites/Spring 2022/Machine Learning CIS490/Week 2"
setwd(wd)

# Import the data

cars <- read.csv(file.choose())
View(cars)

# Set the required Seed and subset the data

set.seed(490)
random_seed <- c("Mersenne-Twister", 490)
index <- sample(1:nrow(cars),0.5*nrow(cars), replace = FALSE)

cars_train <- cars[index,]
cars_test <- cars[-index,]

############################ Simple Linear Regression #######################################

## Training data modeling

lm1 <- lm(mpg~weight, data = cars_train)
summary(lm1)
mse1 <- mean(residuals(lm1)^2)
mse1
rmse1 <- sqrt(mse1)
rmse1
rss <- sum(residuals(lm1)^2)
rss
rse <- sqrt(rss/197)
rse
lm1_predict <- predict(lm1, cars_test)
summary(lm1_predict)
lm1_test_ssl <- sum((cars_test$mpg - lm1_predict)^2)
sprintf("SSL/SSR/SSE: %f", lm1_test_ssl)
lm1_test_mse <- lm1_test_ssl/nrow(cars_test)
sprintf("MSE: %f", lm1_test_mse)
lm1_test_rmse <- sqrt(lm1_test_mse)
sprintf("RMSE: %f", lm1_test_rmse)
plot(mpg~weight, cars_test)
abline(lm1)

## Full data modeling

lm1_fd <- lm(mpg~weight, data = cars)
summary(lm1_fd)
mse1_fd <- mean(residuals(lm1_fd)^2)
mse1_fd
rmse1_fd <- sqrt(mse1_fd)
rmse1_fd
rss_fd <- sum(residuals(lm1_fd)^2)
rss_fd
rse_fd <- sqrt(rss_fd/197)
rse_fd

############################### Multiple Linear Regression ##########################################

## Training data modeling

lm2 <- lm(mpg~cylinders + displacement + weight + acceleration + model.year, data = cars_train)
summary(lm2)
lm2_mse <- mean(residuals(lm2)^2)
lm2_mse
lm2_rmse <- sqrt(lm2_mse)
lm2_rss <- sum(residuals(lm2)^2)
lm2_rss
lm2_rse <- sqrt(lm2_rss/193)
lm2_rse
lm2_predict <- predict(lm2,cars_test)
summary(lm2_predict)
lm2_test_ssl <- sum((cars_test$mpg - lm2_predict)^2)
sprintf("SSL/SSR/SSE: %f", lm2_test_ssl)
lm2_test_mse <- lm2_test_ssl/nrow(cars_test)
sprintf("MSE: %f", lm2_test_mse)
lm2_test_rmse <- sqrt(lm2_test_mse)
sprintf("RMSE: %f", lm2_test_rmse)
scatter.smooth(cars_test$weight,cars_test$mpg,main = "weight ~ mpg")

## Full data modeling

lm2_fd <- lm(mpg~cylinders + displacement + weight + acceleration + model.year, data = cars)
summary(lm2_fd)
lm2_fd_mse <- mean(residuals(lm2_fd)^2)
lm2_fd_mse
lm2_fd_rmse <- sqrt(lm2_fd_mse)
lm2_fd_rss <- sum(residuals(lm2_fd)^2)
lm2_fd_rss
lm2_fd_rse <- sqrt(lm2_fd_rss/392)
lm2_fd_rse

###################################### Parsimonious Model #######################################

## Training data modeling

lm3 <- lm(mpg~cylinders + model.year, data = cars_train)
summary(lm3)
lm3_mse <- mean(residuals(lm3)^2)
lm3_mse
lm3_rmse <- sqrt(lm3_mse)
lm3_rmse
lm3_rss <- sum(residuals(lm3)^2)
lm3_rss
lm3_rse <- sqrt(lm3_rss/196)
lm3_rse
lm3_predict <- predict(lm3,cars_test)
summary(lm3_predict)
lm3_test_ssl <- sum((cars_test$mpg - lm3_predict)^2)
sprintf("SSL/SSR/SSE: %f", lm3_test_ssl)
lm3_test_mse <- lm3_test_ssl/nrow(cars_test)
sprintf("MSE: %f", lm3_test_mse)
lm3_test_rmse <- sqrt(lm3_test_mse)
sprintf("RMSE: %f", lm3_test_rmse)

## Full data modeling

lm3_fd <- lm(mpg~cylinders + model.year, data = cars)
summary(lm3_fd)
lm3_fd_mse <- mean(residuals(lm3_fd)^2)
lm3_fd_mse
lm3_fd_rmse <- sqrt(lm3_fd_mse)
lm3_fd_rmse
lm3_fd_rss <- sum(residuals(lm3_fd)^2)
lm3_fd_rss
lm3_fd_rse <- sqrt(lm3_fd_rss/395)
lm3_fd_rse