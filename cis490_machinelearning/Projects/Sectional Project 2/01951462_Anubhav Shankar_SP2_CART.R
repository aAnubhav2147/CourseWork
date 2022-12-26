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
  labs(x = "",
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
       title = "Boxplots for Numeric Variables") +
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
heart_tbl_corr


############################## CART MODEL ##########################################


Random.seed <- c('Mersenne-Twister', 490)
set.seed(490)

###################### Split data into training and testing set ############################

index <- sample(1:nrow(heart_clean), 0.75 * nrow(heart_clean), replace = FALSE)
heart_train <- heart_clean[index,]
heart_test <- heart_clean[-index,]


glimpse(heart_train)


heart_cart <- rpart(formula = Heart_Disease_Binary ~ ., heart_train, method = "class",
control = rpart.control(minbucket = 2, xval = 10))
prp(heart_cart,roundint = FALSE)
names(heart_lr)
class_param <- heart_cart$cptable # See the associated complexity parameters
class_param

train_error <- double(6)
test_error <- double(6)
cv_error <- double(6)

# The for loop below traverses the entire CP table to calculate the respective error rates
for(i in 1:nrow(class_param)){
alpha <- class_param[i, 'CP']
train <- table(heart_train$Heart_Disease_Binary, predict(prune(heart_cart, cp = alpha),
heart_train, type = "class"))
train_error[i] <- 1 - sum(diag(train))/sum(train)
cv_error[i] <- class_param[i, "xerror"] * class_param[i, 'rel error']
test <- table(heart_test$Heart_Disease_Binary, predict(prune(heart_cart, cp = alpha),
heart_test, type = "class"))
test_error[i] <- 1 - sum(diag(test))/sum(test)
}

train_error

test_error

cv_error

################### Plot the respective error rates to prune the tree effectively ################

matplot(class_param[,'nsplit'], cbind(train_error, cv_error, test_error), 
        pch=19, col=c("red", "black", "blue"), type="b", 
        ylab="Loss/error", xlab="Depth/# of Splits")

legend("right", c('Train', 'CV', 'Test') ,col=seq_len(3),cex=0.8,fill=c("red", "black", "blue"))

plotcp(heart_cart)

########### Prune the tree at an appropriate split and associated complexity parameter ############

heart_prune <- prune(heart_cart,cp = class_param[4, 'CP'])
prp(heart_prune)

################### Confusion matrix and accuracy calculations ##################################

heart_cfm_cart <- table(heart_test$Heart_Disease_Binary,predict(heart_prune,
type = "class", newdata = heart_test))
heart_cfm_cart

cart_accuracy <- sum(diag(heart_cfm_cart))/sum(heart_cfm_cart)
cart_accuracy

cart_train_accuracy <- 1-train_error[6]
cart_train_accuracy

cart_test_accuracy <- 1-test_error[6]
cart_test_accuracy

cart_cv_accuracy <- 1-cv_error[6]
cart_cv_accuracy

prune_train_acc <- 1-train_error[4]
prune_train_acc

prune_test_acc <- 1-test_error[4]
prune_test_acc

prune_cv_acc <- 1-cv_error[4]
prune_cv_acc


