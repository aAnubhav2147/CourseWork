cat("\f") # Clear Console
rm(list = ls()) # Clear the working environment

###################### Load required packages ###############################
library(MASS)
library(ISLR)
library(glmnet)
library(plotmo)
library(dplyr)
library(corrplot)
library(broom)
library(ggplot2)
library(elasticnet)
library(caret)
library(Hmisc)


############## Data Import & Preliminary EDA #################################
boston <- Boston
# View(boston)
contents(boston) # View the metadata
names(boston) # View the column names
str(boston) # View the structure of the data frame
sum(is.na(boston))
head(boston)

summary(boston)
par(mfrow = c(1,4))
boxplot(boston$crim, main = "crim")
boxplot(boston$zn, main = "zn")
boxplot(boston$rm, main = "rm")
boxplot(boston$black, main = "black")

par(mfrow = c(1,1))
corrplot(cor(boston))


################## Multiple Linear Regression ################################

set.seed(490) # Set a seed to ensure reproduceability across runs
index <- sample(1:nrow(boston),0.7*nrow(boston),replace = FALSE) # Create an index for subsetting the data
train <- boston[index,] # Create a training set with 70% of the data
test <- boston[-index,] # Create a testing set with the remaining 30% of the data

train_bkp1 <- train
train_bkp2 <- train
test_bkp1 <- test
test_bkp2 <- test


multiple_lm <- lm(medv~.,data = train)
summary(multiple_lm)
par(mfrow=c(2,2))
plot(multiple_lm)
glance(multiple_lm)
mlm_mse <- mean(residuals(multiple_lm)^2)
mlm_mse
mlm_rmse <- sqrt(mlm_mse)
mlm_rmse
mlm_rss <- sum(residuals(multiple_lm)^2)
mlm_rss
mlm_rse <- sqrt(mlm_rss/340)
mlm_rse

multiple_lm_2 <- lm(medv~chas+nox+rm+dis+rad+ptratio+black+lstat, data = train)
summary(multiple_lm_2)
par(mfrow=c(2,2))
plot(multiple_lm_2)
glance(multiple_lm_2)
mlm2_mse <- mean(residuals(multiple_lm_2)^2)
mlm2_mse
mlm2_rmse <- sqrt(mlm2_mse)
mlm2_rmse
mlm2_rss <- sum(residuals(multiple_lm_2)^2)
mlm2_rss
mlm2_rse <- sqrt(mlm2_rss/345)
mlm2_rse

multiple_lm_3 <- lm(medv~chas+nox+rm+dis+ptratio+black+lstat, data = train)
summary(multiple_lm_3)
par(mfrow=c(2,2))
plot(multiple_lm_3)
glance(multiple_lm_3)
mlm3_mse <- mean(residuals(multiple_lm_3)^2)
mlm3_mse
mlm3_rmse <- sqrt(mlm3_mse)
mlm3_rmse
mlm3_rss <- sum(residuals(multiple_lm_3)^2)
mlm3_rss
mlm3_rse <- sqrt(mlm3_rss/346)
mlm3_rse

# We will now include the following interactions -> rm*lstat, rm*rad, and lstat*rad to see if 
# a parsimonious model is possible. We will drop zn and black altogether owing to huge
# variances present internally within the variables.

multiple_lm_4 <- lm(medv~crim+chas+nox+rm+dis+rad+tax+ptratio+lstat+rm*lstat+rm*rad+lstat*rad, data = train)
summary(multiple_lm_4)
residuals <- data.frame('Residuals' = multiple_lm_4$residuals)
res_hist <- ggplot(residuals, aes(x=Residuals)) + geom_histogram(color='black', fill='red') + ggtitle('Histogram of Residuals')
res_hist
par(mfrow=c(2,2))
plot(multiple_lm_4)
glance(multiple_lm_4)
mlm4_mse <- mean(residuals(multiple_lm_4)^2)
mlm4_mse
mlm4_rmse <- sqrt(mlm4_mse)
mlm4_rmse
mlm4_rss <- sum(residuals(multiple_lm_4)^2)
mlm4_rss
mlm4_rse <- sqrt(mlm4_rss/341)
mlm4_rse

# As the fourth model is the better of the four models, we'll consider it as the parsimonious model and
# use it to predict the testing data set

fit_predict <- predict(multiple_lm_4,test)
summary(fit_predict)
fit_ssl <- sum((test$medv-fit_predict)^2)
sprintf("SSL/SSR/SSE: %f", fit_ssl)
fit_test_mse <- fit_ssl/nrow(test)
sprintf("MSE: %f", fit_test_mse)
fit_test_rmse <- sqrt(fit_test_mse)
sprintf("RMSE: %f", fit_test_rmse)

# Let us create a new column to store the predicted prices/property values in the testing data set
test$pred_price <- fit_predict
pred_plot <- test %>% ggplot(aes(medv,pred_price)) + geom_point(alpha = 0.75) + geom_smooth(method = "loess") + stat_smooth(aes(color = "black")) + xlab("Actual Property Value") + ylab("Predicted Property Value")
pred_plot

############################# PREPARATORY SETUP #####################################

grid <- 10 ^ seq(6, -3, length = 10)

# Independent/Action Variables
x <- model.matrix(medv~., boston)[,-1] #-1  is to remove the Intercept column which auto-creates
# upon running the model for the first time
head(x)

#Dependent/Response Variable
y <- boston$medv

############################### RIDGE REGRESSION ###################################

# Perform the first ridge regression with a random lambda function obtained from the grid
ridge_mod <- glmnet(scale(x), y, alpha = 0, lambda = grid, thresh = 1e-2, standardize = TRUE)
#### P.S. - I need help in understanding the output of the below two lines
coef(ridge_mod)
plot_glmnet(ridge_mod, xvar = "lambda", label = 4)

# plot_glmnet(ridge_mod, xvar = "lambda", label = 2) # Optional Step. Only minor changes observed by changing the value of the label argument

cv_ridge <- cv.glmnet(scale(x), y, alpha = 0, nfolds = 10)
cv_ridge
plot(cv_ridge)
best_lambda_ridge <- cv_ridge$lambda.1se
best_lambda_ridge
ridge_mod_final <- glmnet(scale(x), y, alpha = 0, lambda = best_lambda_ridge, thresh = 1e-2, standardize = TRUE)
predict(ridge_mod_final, type = "coefficients", s = best_lambda_ridge)
ridge_pred <- predict(ridge_mod_final, s=best_lambda_ridge, newx = scale(x))
sprintf("MSE: %f", mean((ridge_pred - y)^2))
sprintf("RMSE: %f", sqrt(mean((ridge_pred - y)^2)))


############################# LASSO REGRESSION ################################################

lasso_mod <- glmnet(scale(x), y, alpha = 1, lambda = grid, thresh = 1e-2, standardize = TRUE)
plot_glmnet(lasso_mod, xvar = "lambda", label = 4)
lasso_cv <- cv.glmnet(scale(x), y, alpha = 1, nfolds = 10)
lasso_cv
plot(lasso_cv)
best_lambda_lasso <- lasso_cv$lambda.1se
best_lambda_lasso
lasso_mod_final <- glmnet(scale(x), y, alpha = 0, lambda = best_lambda_lasso,thresh = 1e-2, standardize = TRUE)
predict(lasso_mod, type = "coefficients", s = best_lambda_lasso)
lasso_pred <- predict(lasso_mod_final,s = best_lambda_lasso, newx = scale(x))
sprintf("MSE: %f", mean((lasso_pred - y)^2))
sprintf("RMSE: %f", sqrt(mean((lasso_pred - y)^2)))