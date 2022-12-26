cat("\f") # Clear Console
rm(list = ls()) # Clear the working environment

######################## Load the Required Packages #############################################

library(titanic)
library(dplyr)
library(caret)
library(rpart)
library(ggplot2)
library(randomForest)
library(doParallel)
library(foreach)

###################################### Load the Data ####################################################

train <- titanic_train # The Training Set
test <- titanic_test # The Testing Set
glimpse(train) # Analyze the metadata
glimpse(test)


########################################### EDL & FEATURE ENGINEERING #################################################

colSums(is.na(train) | train == " ") # Check for NA's and blank values
colSums(is.na(test) | test == " ")

train <- train %>% mutate(Source = "Train") # Introduce an identifier column
test <- test %>% mutate(Source = "Test")
names(train)
names(test)
test$Survived <- rbinom(n=nrow(test),size = 1,prob = 0.05) # Add the target variable in the Test set as it is not there by default
names(test)
full <- rbind(train,test) # Combine the train and test sets for a few feature engineering steps
names(full)
glimpse(full)


full$Title<-sapply(full$Name,function(x) strsplit(x,'[.,]')[[1]][2]) # Strip the title away from the name
full$Title<-gsub(' ','',full$Title) # Remove extra spaces
aggregate(Age~Title,full,median) # Just a random pivot to decide titles based on Age. Done in lines 58-60
full$Title[full$Title %in% c('Capt', 'Don', 'Major','Jonkheer')] <- 'Sir' 
full$Title[full$Title %in% c('Dona','the Countess' )] <- 'Lady'
full$Title[full$Title %in% c('Mlle','Mme','Ms')] <- 'Miss'


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
                Survived=factor(Survived),
                Pclass=factor(Pclass),
                Sex=factor(Sex),
                Embarked=factor(Embarked),
                Title=factor(Title),
                Mother=factor(Mother),
                Child=factor(Child),
                FamilyId2=factor(FamilyId2),
                Deck=factor(Deck)
) # Change the required columns to factors

set.seed(123)
train_copy<-full[full$Source=='Train',]
test_copy<-full[full$Source=='Test',]



names(train_copy)
train_copy <- train_copy[,-c(9,11,13:22)] # Drop some redundant columns which didn't yield any statistical significance in previous tries
names(train_copy)
colSums(is.na(train_copy))
names(test_copy)
test_copy <- test_copy[,-c(9,11,13:22)]
names(test_copy)
test_copy <- test_copy[,-c(1,4)]
test_copy$Survived <- rbinom(n=nrow(test_copy),size = 1,prob = 0.05) #Fail-safe to ensure that the Test set still has random 0-1 binary variables
test_copy$Survived <- as.factor(test_copy$Survived) # Convert to factor for binary classification

glimpse(train_copy)
sapply(train_copy, function(x) length(unique(x))) # Check the number of unique values in each column
train_copy <- train_copy[,-c(1,4)]
names(train_copy)


colSums(is.na(train_copy))

colSums(is.na(test_copy))


###################################### RANDOM FOREST w/t PARALLELIZATION ##########################################

for(i in 2:16){ # Conducts a Strong Scaling Test
  
  registerDoParallel(cores = i)
  getDoParWorkers()
  
  start <- Sys.time()
  
  rf <- foreach(ntree=rep(200, 5), .combine=randomForest::combine,
                .multicombine=TRUE, .packages='randomForest') %dopar% {
                  randomForest(Survived~.,data = train_copy,ntree=1000,mtry=8,importance=T,proximity=T)              
                } 
  
  end <- Sys.time()
  
  total_time <- print(end-start)
  
  closeAllConnections() # Ensures that the program exits after every run otherwise can lead to problems with successive runs
}


#rf <- randomForest(Survived~.,data = train_copy,ntree=1000,mtry=seq(from = 2, to = ncol(train_copy) - 1, by = 1),importance=T,proximity=T)

varImpPlot(rf)
rf

hist(treesize(rf),
     main = "No. of Nodes for the Trees",
     col = "green")

p1 <- predict(rf, train_copy)
confusionMatrix(p1, train_copy$Survived)
p2 <- predict(rf,test_copy)
confusionMatrix(p2, test_copy$Survived)

hist(treesize(rf),
     main = "No. of Nodes for the Trees",
     col = "green")
