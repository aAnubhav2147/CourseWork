cat("\f")
rm(list=ls())
setwd("C:/Users/anubh/Desktop/Course Requisites/Fall 2022/MTH 522 - Advanced Mathematical Stats/Homeworks/Project 1")
getwd()

library("tidyverse")
library("dplyr")
library("tibble")

tmp <- read.csv("globalterrorismdb_0718dist.csv")
#names(tmp)
glimpse(tmp)

terror <- tmp %>% select(where(is.integer))
glimpse(terror)
terror <- terror %>% select(-(contains("INT_") | contains("mode") | contains("ransom") | contains("host") | contains("natlty") | contains("subtype")))
terror <- terror %>% rename(year = iyear, casualties = nkill)
terror$casualties[is.na(terror$casualties)] <- median(terror$casualties,na.rm = T)
sum(is.na(terror$casualties))
terror$severity <- " "
terror$severity <- ifelse(terror$casualties >= 10, "Major", terror$severity)
terror$severity <- ifelse(terror$casualties < 3, "Minor", terror$severity)
terror$severity <- ifelse(terror$casualties >= 3 & terror$casualties < 10, "Small", terror$severity)
table(terror$severity)

table(terror$year,terror$severity)
terror_tb <- table(terror$year,terror$severity)
barplot(terror_tb,beside = T, legend.text = F)

terror_tbl <- as.data.frame(terror_tb)
glimpse(terror_tbl)
terror_df <- terror_tbl %>% rename(year = Var1, severity = Var2, nAttacks = Freq)
glimpse(terror_df)
sapply(terror_df,class)
#x <- sapply(terror_df,is.factor)
#terror_df[,x] <- apply(terror_df[,x],2,as.integer)
sapply(terror_df,class)
sum(is.na(terror_df$year))
sum(is.na(terror_df$nAttacks))
plot(terror_df$year,terror_df$nAttacks,col=terror_df$severity,pch=20,xlab="Year",ylab="Number of Attacks")
ggplot(terror_df,aes(year,nAttacks,group=severity,fill=factor(severity))) + geom_point(aes(color=severity)) + scale_color_manual(values = c("red","blue","purple")) + xlab("Year") + ylab("Number of Attacks")
set.seed(123)
terror_model <- lm(nAttacks~year,data = terror_df)
summary(terror_model)
ggplot(terror_df,aes(year,nAttacks,group=severity,fill=factor(severity))) + geom_point(aes(color=severity)) + geom_smooth(method = "lm") + scale_color_manual(values = c("red","blue","purple")) + xlab("Year") + ylab("Number of Attacks") + labs(title = "Terror Attacks Linear Regression Plot") + theme(plot.title = element_text(hjust=0.5, size=20, face='bold'))




