cat("\f") # Clear Console
rm(list = ls()) # Clear the working environment

######################## Load the Required Packages #############################################

library(titanic)
library(dplyr)
library(tidyverse)
library(caTools)
library(caret)
library(AER)
library(arm)
library(Amelia)
library(modeest)
library(mice)
library(MASS)
library(cowplot)
library(rmarkdown)
library(ggplot2)
library(pROC)
library(rpart)
library(rpart.plot)
library(GGally)
library(randomForest)
library(e1071)
library(ROCR)

###################################### Load the Data ####################################################

train <- titanic_train # The Training Set
test <- titanic_test # The Testing Set
glimpse(train) # Analyze the metadata
glimpse(test)


colSums(is.na(train) | train == " ") # Check for NA's and blank values
colSums(is.na(test) | test == " ")


train <- train %>% mutate(Source = "Train") # Introduce an identifier column
test <- test %>% mutate(Source = "Test")
names(train)
names(test)
test$Survived <- NA # Add the target variable in the Test set as it is not there by default
names(test)
full <- rbind(train,test) # Combine the train and test sets for a few feature engineering steps
names(full)
glimpse(full)
# View(full)
# table(full$Survived,full$Source)
# table(full$Survived)
# 549 + 342
# table(test$Survived)

full$Title<-sapply(full$Name,function(x) strsplit(x,'[.,]')[[1]][2]) # Strip the title away from the name
full$Title<-gsub(' ','',full$Title) # Remove extra spaces
aggregate(Age~Title,full,median) # Just a random pivot to decide titles based on Age. Done in lines 58-60
full$Title[full$Title %in% c('Capt', 'Don', 'Major','Jonkheer')] <- 'Sir' 
full$Title[full$Title %in% c('Dona','the Countess' )] <- 'Lady'
full$Title[full$Title %in% c('Mlle','Mme','Ms')] <- 'Miss'

# age_fit <- rpart(Age[!is.na(Age)]~Pclass+Title+Sex+SibSp+Parch+Fare,data=full[!is.na(full$Age),],method='anova')
missmap(train,col=c('yellow','black'),main='Titanic Train Data',legend=F) # See which rows have missing values

## NOTE: DON'T EVER USE MISSMAP IN THE FUTURE. IT IS AS USELESS AS IT COMES. NO INSIGHTS GATHERED WHATSOEVER ##

full$FamilySize<-full$Parch+full$SibSp+1 # Create a variable for Family Size. The +1 is because some children were with
# nannies whose Parch = 0
full$Mother<-0 # Create a mother identifier
full$Mother[full$Sex=='female' & full$Parch>0 & full$Age>18 & full$Title!='Miss']<-1
full$Child<-0 # Create a child identifier
full$Child[full$Parch>0 & full$Age<=12]<-1

Surname<-sapply(full$Name,function(x) strsplit(x,'[.,]')[[1]][1]) # Strip out the surname
FamilyId<-paste0(full$FamilySize,Surname) # Create a Family Identifier basis the size
full$FamilyId<-factor(FamilyId)
Family<-data.frame(table(FamilyId))
SmallFamily<-Family$FamilyId[Family$Freq<=2]
FamilyId[FamilyId %in% SmallFamily]<-'Small'
full$FamilyId2<-factor(FamilyId)

full$Deck<-sapply(full$Cabin, function(x) strsplit(x,NULL)[[1]][1]) #Ascertain the Deck occupied from Cabin details
full$Deck[is.na(full$Deck)]<-'N/A' # Fill the missing values

full$CabinNum<-sapply(full$Cabin,function(x) strsplit(x,'[A-Z]')[[1]][2]) #Ascertain the Cabin Number occupied from Cabin details
full$num<-as.numeric(full$CabinNum) # Fill the missing values
num<-full$num[!is.na(full$num)]
Pos<-kmeans(num,3)
full$CabinPos<-'N/A'
full$CabinPos[!is.na(full$num)]<-Pos$cluster
full$CabinPos<-factor(full$CabinPos)
full$num<-NULL

full$Embarked[is.na(full$Embarked)]<-'S' # Fill the blank Embarkation points with Southampton value

full$Fare[is.na(full$Fare)]<-median(full$Fare,na.rm=T) # Fill missing Fare values with the median fare

######### A novel technique to fill the missing Age values using the Decision Trees ###########################

age_fit<-rpart(Age[!is.na(Age)]~Pclass+Title+Sex+SibSp+Parch+Fare,data=full[!is.na(full$Age),],method='anova')
full$Age[is.na(full$Age)]<-predict(age_fit,full[is.na(full$Age),])

full<-transform(full,
                Pclass=factor(Pclass),
                Sex=factor(Sex),
                Embarked=factor(Embarked),
                Title=factor(Title),
                Mother=factor(Mother),
                Child=factor(Child),
                FamilyId2=factor(FamilyId2),
                Deck=factor(Deck)
) # Change the required columns to factors

Random.seed <- c('Mersenne-Twister', 490) # Set the random seed
set.seed(490)

train<-full[full$Source=='Train',]
test<-full[full$Source=='Test',]
train$Survived<-factor(train$Survived) # Turn the target variable into a factor


train_copy <- train # Create a copy of the training set
test_copy <- test # Create a copy of the test set

names(train_copy)
train_copy <- train_copy[,-c(9,11,13:22)] # Drop some redundant columns which didn't yield any statistical significance in previous tries
names(train_copy)
colSums(is.na(train_copy))
names(test_copy)
test_copy <- test_copy[,-c(9,11,13:22)]
names(test_copy)
test_copy <- test_copy[,-c(1,4)]

# full_copy <- full
# names(full)
# names(full_copy)
# full_copy <- full_copy[,-c(9,11,13:22)]
# names(full_copy)
# full_copy <- full_copy[,-c(1,4)]

glimpse(train_copy)
sapply(train_copy, function(x) length(unique(x)))
train_copy <- train_copy[,-c(1,4)]
names(train_copy)

### As the testing set provided by Kaggle does not have the target variable,
### a psuedo-random target variable is generated for the purpose of the project

## The step in line 151, however, will introduce bias to the testing set

test_copy <- test_copy %>% mutate(Survived = case_when(Sex == "male" ~ 0,
                                                       Pclass != "1" ~ 0,
                                                       Sex == "female" ~ 1))
test_copy$Survived <- as.factor(test_copy$Survived) # Turn the target variable into a factor

# full_copy <- full_copy %>% mutate(Survived = case_when((Survived != "0" || Survived != "1")&Sex == "male" ~ 0,
#                                                        (Survived != "0" || Survived != "1") & Pclass != "1" ~ 0,
#                                                        (Survived != "0" || Survived != "1") & Sex == "female" ~ 1))

# full_copy$Survived <- as.factor(full_copy$Survived)
# sum(is.na(full_copy$Survived))


colSums(is.na(train_copy))

colSums(is.na(test_copy))

# colSums(is.na(full_copy))

########################### LOGISTIC REGRESSION ####################################################

titanic_glm <- glm(Survived ~ Sex, data = train_copy, family = 'binomial')
summary(titanic_glm)

# Test for accuracy
predict_sex_survived <- predict(titanic_glm,newdata = test_copy,type = 'response') 
# Since Survived can only be either 1 or 0, write if statement to round up of down the response
predict_sex_survived <- ifelse(predict_sex_survived>0.5,1,0)
error_1 <- mean(predict_sex_survived!=test_copy$Survived)
accuracy_1 <- 1-error_1
accuracy_1

prob_survival <- data.frame(prob_survival = titanic_glm$fitted.values,Survived = train_copy$Survived, Sex = train_copy$Sex)

ggplot(prob_survival, aes(fill = Survived, y = prob_survival, x = Sex)) + 
  geom_bar(position = "fill", stat = "identity") + 
  labs(x= "Survival Status", y = "Probability of Survival", title = "Survival chances by Gender") + 
  scale_y_continuous(labels = scales::percent_format()) + 
  scale_fill_discrete(labels = c("Died","Survived"))


logistic_complete <- glm(Survived~., data = train_copy, family = "binomial")
summary(logistic_complete)
logistic_stepwise <- logistic_complete %>% stepAIC(direction = "both", trace = FALSE)
summary(logistic_stepwise)
AIC(logistic_complete,logistic_stepwise)


predict_logistic_test <- predict(logistic_stepwise,test_copy,type = "response")

predict_logistic_1 <- ifelse(predict_logistic_test>0.5,1,0)
cftable_test <- table(predict_logistic_1,test_copy$Survived)
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
error_test <- mean(predict_logistic_1!=test_copy$Survived)
acc_test <- 1 - error_test
acc_test

predict_logistic_train <- predict(logistic_stepwise,train_copy,type = "response")
predict_logistic_2 <- ifelse(predict_logistic_train>0.5,1,0)
cftable_train <- table(predict_logistic_2,train_copy$Survived)
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
error_train <- mean(predict_logistic_2!=train_copy$Survived)
acc_train <- 1 - error_train
acc_train


roc_test <- roc(test_copy$Survived,predict_logistic_test)
plot.roc(roc_test, col=par("fg"),plot=TRUE,print.auc = FALSE, legacy.axes = TRUE, asp =NA)
roc_test
plot.roc(smooth(roc_test), col="blue", add = TRUE,plot=TRUE,print.auc = TRUE, legacy.axes = TRUE, asp =NA)
legend("bottomright", legend=c("Empirical", "Test"),
col=c(par("fg"), "blue"), lwd=2)

roc_train <- roc(train_copy$Survived,predict_logistic_train)
plot.roc(roc_train, col=par("fg"),plot=TRUE,print.auc = FALSE, legacy.axes = TRUE, asp =NA)
roc_test
plot.roc(smooth(roc_train), col="green", add = TRUE,plot=TRUE,print.auc = TRUE, legacy.axes = TRUE, asp =NA)
legend("bottomright", legend=c("Empirical", "Train"),
       col=c(par("fg"), "green"), lwd=2)

## The lines below attempted to plot both the ROC & AUC curves in the same curves

# pred <- prediction(predict_logistic_test,test_copy$Survived)
# perf <- performance(pred,"tpr","fpr")
# par(mar=c(5,5,2,2),xaxs = "i",yaxs = "i",cex.axis=1.3,cex.lab=1.4)
# plot(perf,col="black",lty=3, lwd=3)
# auc <- performance(pred,"auc")
# auc
# abline(0,1,col="grey")

##################################### S-CURVE #############################################################
predicted_data <- data.frame(probability_of_survival = logistic_stepwise$fitted.value,Survived=train_copy$Survived,Sex=train_copy$Sex)
# predicted_data <- predicted_data[order(predicted.data$probability.of.survival,decreasing = FALSE),]
ggplot(data=predicted_data,aes(x=probability_of_survival,y=as.numeric(Survived)-1,col=Sex))+
geom_point(alpha=0.8,shape=1,stroke=1)+
xlab('Predicted probability of surviving titanic')+
ylab('Survived')+
stat_smooth(method="glm", method.args=list(family="binomial"), se=FALSE,fullrange = TRUE)+
theme(legend.position = 'bottom',plot.title = element_text(hjust = 0.5))+
ggtitle('Survival by Gender')


################################### CART ###########################################################

titanic_cart <- rpart(formula = Survived ~., train_copy, method = "class",
control = rpart.control(minsplit = 9, minbucket = 3,xval = 10))
prp(titanic_cart,roundint = F)
cart_param <- titanic_cart$cptable
cart_param
nrow(cart_param)

train_error <- double(4)
test_error <- double(4)
cv_error <- double(4)

for(i in 1:nrow(cart_param)){
alpha <- cart_param[i, 'CP']
train_temp <- table(train_copy$Survived, predict(prune(titanic_cart, cp = alpha),
train_copy,type = "class"))
train_error[i] <- 1 - sum(diag(train_temp))/sum(train_temp)
cv_error[i] <- cart_param[i, "xerror"] * cart_param[i, "rel error"]
test_temp <- table(test_copy$Survived,predict(prune(titanic_cart,cp = alpha),
test_copy,type = "class"))
test_error[i] <- 1 - sum(diag(test_temp))/sum(test_temp)
}

train_error
test_error
cv_error

matplot(cart_param[,"nsplit"],cbind(train_error,cv_error,test_error),
pch = 19, col = c("red","green","blue"), type = "b",
ylab = "Loss/Error", xlab = "Depth of Split")

legend("right", c('Train', 'CV', 'Test') ,col=seq_len(3),cex=0.8,fill=c("red", "green", "blue"))

plotcp(titanic_cart)


titanic_prune <- prune(titanic_cart,cp = cart_param[4, 'CP'])
prp(titanic_prune)

titanic_cfm_cart <- table(test_copy$Survived,predict(titanic_prune, type = "class", newdata = test_copy))
titanic_cfm_cart

cart_accuracy <- sum(diag(titanic_cfm_cart))/sum(titanic_cfm_cart)
cart_accuracy

cart_train_accuracy <- 1-train_error[4]
cart_train_accuracy
cart_test_accuracy <- 1-test_error[4]
cart_test_accuracy
cart_cv_accuracy <- 1-cv_error[4]
cart_cv_accuracy

prune_train_acc <- 1-train_error[4]
prune_train_acc
prune_test_acc <- 1-test_error[4]
prune_test_acc
prune_cv_acc <- 1-cv_error[4]
prune_cv_acc






###################################################################################################
control <- trainControl(method = "repeatedcv", number = 10, repeats = 10)

logistic1 <- train(Survived ~ FamilySize+CabinPos+Deck+Pclass+Sex+Age+SibSp+Parch+Fare+Embarked+Title+Mother+Child, data = train, method = "glm", trControl = control, family = binomial(link = "logit"))

tree1 <- train(Survived ~ FamilySize+CabinPos+Deck+Pclass+Sex+Age+SibSp+Parch+Fare+Embarked+Title+Mother+Child, data = train, method = "rpart", trControl = control)

logistic_bayesian <- train(Survived ~ FamilySize+CabinPos+Deck+Pclass+Sex+Age+SibSp+Parch+Fare+Embarked+Title+Mother+Child, data = train, method = "bayesglm", trControl = control, family = binomial(link = "logit"))

acc_logistic1 <- paste0(round(max(logistic1$results$Accuracy),3)*100,'%')
cat('prediction accucary with logisitc regreesion is ',acc_logistic1,'.\n')

acc_tree1 <- paste0(round(max(tree1$results$Accuracy),3)*100,'%')
cat('prediction accucary with decision tree model is ',acc_tree1,'.\n')

acc_bayesian <- paste0(round(max(logistic_bayesian$results$Accuracy),3)*100,'%')
cat('prediction accucary with bayesian logistic regression is ',acc_bayesian,'.\n')

randomforest <- train(Survived ~ FamilySize+CabinPos+Deck+Pclass+Sex+Age+SibSp+Parch+Fare+Embarked+Title+Mother+Child, data = train, method = "rf", trControl = control)

grid <- expand.grid(size = 1, decay = 0.01)
neuralnet <- train(Survived ~ FamilySize+CabinPos+Deck+Pclass+Sex+Age+SibSp+Parch+Fare+Embarked+Title+Mother+Child, data = train, method = "nnet", trControl = control,maxit = 1000, trace = FALSE, tuneGrid = grid )

acc_rf <- paste0(round(max(randomforest$results$Accuracy),3)*100,'%')
cat('prediction accucary of random forest is ',acc_rf,'.\n')

acc_nnet <- paste0(round(max(neuralnet$results$Accuracy),3)*100,'%')
cat('prediction accucary of neural net is ',acc_nnet,'.\n')


# test$Survived <- predict(logistic1,test)
# kaggle <- test[,1:2]
# setwd("C:/Users/anubh/Desktop/Course Requisites/Spring 2022/Machine Learning CIS490/Final Project")
# write.csv(kaggle,"kaggle_log1.csv",row.names = FALSE)
# 
# test$Survived <- predict(tree1,test)
# kaggle <- test[,1:2]
# write.csv(kaggle,"kaggle_tree.csv",row.names = FALSE)
# 
# test$Survived <- predict(logistic_bayesian,test)
# kaggle <- test[,1:2]
# write.csv(kaggle,"kaggle_baylog.csv",row.names = FALSE)
# 
# test$Survived <- predict(randomforest,test)
# kaggle <- test[,1:2]
# write.csv(kaggle,"kaggle_randomforest.csv",row.names = FALSE)
# 
# test$Survived <- predict(neuralnet,test)
# kaggle <- test[,1:2]
# write.csv(kaggle,"kaggle_neuralnet.csv",row.names = FALSE)
