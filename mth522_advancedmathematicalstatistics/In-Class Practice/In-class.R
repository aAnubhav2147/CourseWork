library("dplyr")
library("car")

setwd("C:/Users/anubh/Desktop/Course Requisites/Fall 2022/MTH 522 - Advanced Mathematical Stats/In-Class Practice")
getwd()

auto <- read.csv("auto-mpg.csv")
auto$horsepower <- as.integer(auto$horsepower)
sum(is.na(horsepower))
auto_imp <- auto[,-(8:9)]
glimpse(auto_imp)


set.seed(123)
model <- lm(mpg ~ .,data = auto_imp)
summary(model)

ncvTest(model)

myspread <- spreadLevelPlot(model)
myspread$PowerTransformation
pt <- myspread$PowerTransformation

y <- auto_imp$mpg^(pt)
model_improve <- lm(y~.,data = auto_imp)
summary(model_improve)
ncvTest(model_improve)

ks.test(sd(model_improve$residuals),model_improve$residuals)
