cat("\f") # Clear Console
rm(list = ls()) # Clear the working environment

######################## Set the Working Directory ##############################################
setwd("C:/Users/anubh/Desktop/Course Requisites/Spring 2022/Machine Learning CIS490/Sectional Project 2")
getwd()


####################### Load required packages ###########################################
library(tidyverse)
library(ggplot2)
library(pROC)
library(rpart)
library(rpart.plot)
library(caret)
library(GGally)

#################### Import the dataset #######################################
heart <- read.csv(file.choose())

#################### Data Wrangling Steps ######################################
names(heart) # See the current fields and field names

heart_lr <- heart[,-1] # Remove the first column as it serves no purpose

names <- c("Age", # Create a vector containing the desired column names
           "Sex",
           "Chest_Pain_Type",
           "Resting_Blood_Pressure",
           "Serum_Cholesterol",
           "Fasting_Blood_Sugar",
           "Resting_ECG",
           "Max_Heart_Rate_Achieved",
           "Exercise_Induced_Angina",
           "ST_Depression_Exercise",
           "Peak_Exercise_ST_Segment",
           "Num_Major_Vessels_Flouro",
           "Thalassemia",
           "Diagnosis_Heart_Disease")

colnames(heart_lr) <- names # Change the existing column names

names(heart_lr) # Check to ensure that the changes have take place

head(heart_lr) # Check the first six records of the dataset

sum(is.na(heart_lr)) # Check the total number of NA's present

str(heart_lr) # Check the metadata of the dataset

attach(heart_lr) # Optional step -> doing this makes accessing the columns easier

############# Convert a few required categorical variables from integers to factors ###############
heart_lr$Sex <- factor(heart_lr$Sex)
heart_lr$Fasting_Blood_Sugar <- factor(heart_lr$Fasting_Blood_Sugar)
heart_lr$Resting_ECG <- factor(heart_lr$Resting_ECG)
heart_lr$Exercise_Induced_Angina <- factor(heart_lr$Exercise_Induced_Angina)
heart_lr$Peak_Exercise_ST_Segment <- factor(heart_lr$Peak_Exercise_ST_Segment)

glimpse(heart_lr) # a dplyr equivalent of str()

summary(heart_lr) # Get a basic data profile

# table(Num_Major_Vessels_Flouro)

# dplyr provides a lot of commands for data wrangling. One of the most powerful of them
# being mutate() which can be used to create new fields or edit the existing fields similar
# to the CASE statement on SQL.

heart_lr <- heart_lr %>% mutate(Chest_Pain_Type_Numeric = case_when(Chest_Pain_Type == "typical" ~ 1,
Chest_Pain_Type == "nontypical" ~ 2,
Chest_Pain_Type == "nonanginal" ~ 3,
Chest_Pain_Type == "asymptomatic" ~ 4
))
heart_lr$Chest_Pain_Type_Numeric <- factor(heart_lr$Chest_Pain_Type_Numeric)

heart_lr <- heart_lr %>% mutate(Resting_ECG_Results = case_when(Resting_ECG == 0 ~ "Normal",
Resting_ECG == 1 ~ "ST-T Wave Abnormality",
Resting_ECG == 2 ~ "Left Ventricle Hyperthropy"
))

heart_lr <- heart_lr %>% mutate(Heart_Disease_Binary = case_when(Diagnosis_Heart_Disease == "No" ~ 0,
Diagnosis_Heart_Disease == "Yes" ~ 1
))
heart_lr$Heart_Disease_Binary <- factor(heart_lr$Heart_Disease_Binary)

heart_lr <- heart_lr %>% mutate(Thalassemia_Levels = case_when(Thalassemia == "normal" ~ 3,
Thalassemia == "fixed" ~ 6,
Thalassemia == "reversable" ~ 7
))
heart_lr$Thalassemia_Levels <- factor(heart_lr$Thalassemia_Levels)

glimpse(heart_lr)

which(is.na(heart_lr),arr.ind = TRUE) # Figure out which rows have blanks or NA's present in them

heart_clean <- heart_lr %>% drop_na() %>% filter(Thalassemia != "?") # dplyr provides the piping
# capability that can be used to chain multiple commands together

# View(heart_clean)
glimpse(heart_clean)
# heart_clean <- heart_clean[,-c(3,13,14,16)]
heart_clean <- heart_clean[,-c(3,13,16)] # Subset out a few columns which we don't need
glimpse(heart_clean)

################################### EDL ###################################################\

heart_tbl <- heart_clean %>% select(Sex,Fasting_Blood_Sugar,Resting_ECG,
Exercise_Induced_Angina,Peak_Exercise_ST_Segment,
Thalassemia_Levels,Heart_Disease_Binary,Chest_Pain_Type_Numeric) %>%
gather(key = "key", value = "value", -Heart_Disease_Binary)

heart_tbl %>% ggplot(aes(value)) +
geom_bar(aes(x        = value,
fill     = Heart_Disease_Binary),
alpha    = .6,
position = "dodge",
color    = "black",
width    = .8
) +
labs(x = "",  # Create basic barplots to understand the prevalence and causes of heart attacks
y = "",
title = "Scaled Effect of Categorical Variables") +
theme(
axis.text.y  = element_blank(),
axis.ticks.y = element_blank()) +
facet_wrap(~ key, scales = "free", nrow = 4) +
scale_fill_manual(
values = c("#fde725ff", "#20a486ff"),
name   = "Heart\nDisease",
labels = c("No HD", "Yes HD"))

heart_tbl_cont <- heart_clean %>% select(Age, Resting_Blood_Pressure,
Serum_Cholesterol,
Max_Heart_Rate_Achieved,
ST_Depression_Exercise,
Num_Major_Vessels_Flouro,
Heart_Disease_Binary) %>%
gather(key = "key", value = "value", -Heart_Disease_Binary)

heart_tbl_cont %>% ggplot(aes(y = value)) +
geom_boxplot(aes(fill = Heart_Disease_Binary),
alpha  = .6,
fatten = .7) +
labs(x = "",
y = "",
title = "Boxplots for Numeric Variables") + # Create boxplots to see the outliers in the continuous fields
scale_fill_manual(
values = c("#fde725ff", "#20a486ff"),
name   = "Heart\nDisease",
labels = c("No HD", "Yes HD")) +
theme(
axis.text.x  = element_blank(),
axis.ticks.x = element_blank()) +
facet_wrap(~ key,
scales = "free",
ncol   = 2)

heart_tbl_corr <- heart_clean %>% ggcorr(high = "#20a486fe",
low = "#fde725ee",
label = TRUE,)
heart_tbl_corr <- heart_clean %>% ggcorr(high = "#20a486fe",
low = "#fde725ee",
label = TRUE,
hjust = 0.80,
size = 3,
label_size = 3,
nbreaks = 5) +
labs(title = "Correlation Matrix",
subtitle = "Pearson Method for pair-wise correlations")
heart_tbl_corr # The above lines plot the one -to-one correlation between the fields



######################## LOGISTIC REGRESSION ###########################################


Random.seed <- c('Mersenne-Twister', 490)
set.seed(490)

###################### Split data into training and testing set ############################

index <- sample(1:nrow(heart_clean), 0.75 * nrow(heart_clean), replace = FALSE)
heart_train <- heart_clean[index,]
heart_test <- heart_clean[-index,]


glimpse(heart_train)

logit_train <- glm(Heart_Disease_Binary ~ .,data = heart_train, family = "binomial")
summary(logit_train) # Logistic regression using training set

logit_full <- glm(Heart_Disease_Binary ~ .,data = heart_clean, family = "binomial")
summary(logit_full) # Logistic regression using the full data set


logLik(logit_train) # Log likelihood of training set

logLik(logit_full) # Log likelihood of the full data set

with(logit_full, pchisq(null.deviance - deviance, df.null - df.residual, lower.tail = FALSE)) # p-value of the full dataset

with(logit_train, pchisq(null.deviance - deviance, df.null - df.residual, lower.tail = FALSE)) # p-value of the training dataset


logit_train_parsimonious <- glm(Heart_Disease_Binary ~ Resting_Blood_Pressure +
Exercise_Induced_Angina +
Thalassemia_Levels +
Num_Major_Vessels_Flouro,data = heart_train, family = "binomial")

summary(logit_train_parsimonious) # parsimonious model using significant variables from the training set

logLik(logit_train_parsimonious) # Log likelihood of the parsimonious training set

logit_full_parsimonious <- glm(Heart_Disease_Binary ~ Sex + Resting_Blood_Pressure +
Chest_Pain_Type_Numeric +
Thalassemia_Levels +
Num_Major_Vessels_Flouro,data = heart_clean, family = "binomial")

summary(logit_full_parsimonious) # parsimonious model using significant variables from the full data set

logLik(logit_full) # Log likelihood of the parsimonious full data set

with(logit_full_parsimonious, pchisq(null.deviance - deviance, df.null - df.residual, lower.tail = FALSE)) # p-value of the full parsimonious dataset

with(logit_train_parsimonious, pchisq(null.deviance - deviance, df.null - df.residual, lower.tail = FALSE)) # p-value of the parsimonious training dataset


logit_none <- glm(Heart_Disease_Binary ~ 1, data = heart_clean, family = "binomial")
summary(logit_none) # Intercept model 
logLik(logit_none) # log-likelihood of the intercept model
with(logit_none, pchisq(null.deviance - deviance, df.null - df.residual, lower.tail = FALSE)) # p-value of the intercept model


exp(coef(logit_full_parsimonious))
confint(logit_full_parsimonious)

exp(coef(logit_train_parsimonious))
confint(logit_train_parsimonious)

################################ Predictions and Model fit metrics #################################

predictions_test <- predict(logit_train_parsimonious, heart_test, type = "response")
predictions_binary_test <- ifelse(predictions_test > 0.5, 1, 0)
cftable_test <- table(predictions_binary_test, heart_test$Heart_Disease_Binary)
cftable_test
accuracy_test <- sum(diag(cftable_test))/sum(cftable_test)
accuracy_test
sensitivity_test <- cftable_test[1]/(cftable_test[1] + cftable_test[2])
sensitivity_test
specificity_test <- cftable_test[4]/(cftable_test[3] + cftable_test[4])
specificity_test
ppv_test <- cftable_test[1]/(cftable_test[1] + cftable_test[3])
ppv_test
npv_test <- cftable_test[4]/(cftable_test[2] + cftable_test[4])
npv_test

predictions_train <- predict(logit_train_parsimonious, heart_train, type = "response")
predictions_binary_train <- ifelse(predictions_train > 0.5, 1, 0)
cftable_train <- table(predictions_binary_train, heart_train$Heart_Disease_Binary)
cftable_train
accuracy_train <- sum(diag(cftable_train))/sum(cftable_train)
accuracy_train
sensitivity_train <- cftable_train[1]/(cftable_train[1] + cftable_train[2])
sensitivity_train
specificity_train <- cftable_train[4]/(cftable_train[3] + cftable_train[4])
specificity_train
ppv_train <- cftable_train[1]/(cftable_train[1] + cftable_train[3])
ppv_train
npv_train <- cftable_train[4]/(cftable_train[2] + cftable_train[4])
npv_train


predictions_full <- predict(logit_full_parsimonious, heart_clean, type = "response")
predictions_binary_full <- ifelse(predictions_full > 0.5, 1, 0)
cftable_full <- table(predictions_binary_full, heart_clean$Heart_Disease_Binary)
cftable_full
accuracy_full <- sum(diag(cftable_full))/sum(cftable_full)
accuracy_full
sensitivity_full <- cftable_full[1]/(cftable_full[1] + cftable_full[2])
sensitivity_full
specificity_full <- cftable_full[4]/(cftable_full[3] + cftable_full[4])
specificity_full
ppv_full <- cftable_full[1]/(cftable_full[1] + cftable_full[3])
ppv_full
npv_full <- cftable_full[4]/(cftable_full[2] + cftable_full[4])
npv_full

predictions_test_full <- predict(logit_full_parsimonious, heart_test, type = "response")
predictions_binary_test_full <- ifelse(predictions_test_full > 0.5, 1, 0)
cftable_test_full <- table(predictions_binary_test_full, heart_test$Heart_Disease_Binary)
cftable_test_full
accuracy_test_full <- sum(diag(cftable_test_full))/sum(cftable_test_full)
accuracy_test_full
sensitivity_test_full <- cftable_test_full[1]/(cftable_test_full[1] + cftable_test_full[2])
sensitivity_test_full
specificity_test_full <- cftable_test_full[4]/(cftable_test_full[3] + cftable_test_full[4])
specificity_test_full
ppv_test_full <- cftable_test_full[1]/(cftable_test_full[1] + cftable_test_full[3])
ppv_test_full
npv_test_full <- cftable_test_full[4]/(cftable_test_full[2] + cftable_test_full[4])
npv_test_full

predictions_train_full <- predict(logit_full_parsimonious, heart_train, type = "response")
predictions_binary_train_full <- ifelse(predictions_train_full > 0.5, 1, 0)
cftable_train_full <- table(predictions_binary_train_full, heart_train$Heart_Disease_Binary)
cftable_train_full
accuracy_train_full <- sum(diag(cftable_train_full))/sum(cftable_train_full)
accuracy_train_full
sensitivity_train_full <- cftable_train_full[1]/(cftable_train_full[1] + cftable_train_full[2])
sensitivity_train_full
specificity_train_full <- cftable_train_full[4]/(cftable_train_full[3] + cftable_train_full[4])
specificity_train_full
ppv_train_full <- cftable_train_full[1]/(cftable_train_full[1] + cftable_train_full[3])
ppv_train_full
npv_train_full <- cftable_train_full[4]/(cftable_train_full[2] + cftable_train_full[4])
npv_train_full

################################ ROC & AUC #############################################################

roc_test <- roc(heart_test$Heart_Disease_Binary,predictions_test)
plot.roc(roc_test, col=par("fg"),plot=TRUE,print.auc = FALSE, legacy.axes = TRUE, asp =NA)
roc_test

plot.roc(smooth(roc_test), col="blue", add = TRUE,plot=TRUE,print.auc = TRUE, legacy.axes = TRUE, asp =NA)
legend("bottomright", legend=c("Empirical", "Smoothed"),
       col=c(par("fg"), "blue"), lwd=2)

roc_train <- roc(heart_train$Heart_Disease_Binary,predictions_train)
roc_train
plot.roc(roc_train, col="black",plot=TRUE,print.auc = FALSE, legacy.axes = TRUE, asp =NA)

plot.roc(smooth(roc_train), col="blue", add = TRUE,plot=TRUE,print.auc = TRUE, legacy.axes = TRUE, asp =NA)
legend("bottomright", legend=c("Empirical", "Smoothed"),
       col=c(par("fg"), "blue"), lwd=2)

roc_full <- roc(heart_clean$Heart_Disease_Binary,predictions_full)
roc_full
plot.roc(roc_full, col="black",plot=TRUE,print.auc = FALSE, legacy.axes = TRUE, asp =NA)

plot.roc(smooth(roc_full), col="blue", add = TRUE,plot=TRUE,print.auc = TRUE, legacy.axes = TRUE, asp =NA)
legend("bottomright", legend=c("Empirical", "Smoothed"),
       col=c(par("fg"), "blue"), lwd=2)

################################### S-Curve ############################################################

heart_scurve <- glm(Heart_Disease_Binary ~ Num_Major_Vessels_Flouro, data = heart_clean, family = "binomial")
plot(as.numeric(Heart_Disease_Binary) ~ Num_Major_Vessels_Flouro, data = heart_clean,
     col = "green", pch = "|", xlim = c(-1,3), ylim = c(0,1),
     main = "Using Logistic Regression for Classification") +
abline(h = 0, lty = 3) +
abline(h = 1, lty = 3) +
abline(h = 0.5, lty = 2) +
curve(predict(heart_scurve, data.frame(Num_Major_Vessels_Flouro = x), type = "response"),
        add = TRUE, lwd = 3, col = "dodgerblue") +
abline(v = -coef(heart_scurve)[1] / coef(heart_scurve)[2], lwd = 3)