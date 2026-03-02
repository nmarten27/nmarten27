#########################################################################
########################Final Project####################################
#########################################################################

#Please write down your code in the file and submit the file in the submission box. 

#########################################################################
####################Section one: Vector##################################
#########################################################################

#Following is the salary data of the top 10 highest-paid NBA players from 2023 to 2024.

#1. Create a vector to store the salary data.

salary_data <- c(45640084, 45640084, 46741590, 47607350, 47607350, 51915615, 47607350, 45640084, 45640084, 46407433)


#2. Assign the following names to the salary vector. 

names(salary_data) <- c("Paul George", "Damian Lillard", "Bradley Beal", "LeBron James", "Nikola Jokic", "Stephen Curry", "Joel Embiid", "Giannis Antetokounmpo","Kawhi Leonard", "Kevin Durant") 


#3. Who has the highest salary for the season of 2023-24. 

max(names(salary_data))

#4. Sort the Salary in descending order. 

sort(salary_data, decreasing = T)

#5. Calculate the mean and median of the salary.

mean(salary_data)
median(salary_data)

#6. Return the Salary of Kevin Durant and LeBron James. (Please use the names to retrieve data from the vector)

salary_data[c("Kevin Durant", "LeBron James")]

#7. Return the names of players who have a salary of more than 47 million. 

names(salary_data[salary_data > 47000000])

#########################################################################
####################Section Two: Matrix##################################
#########################################################################
#Following is the free throws data of 10 players from the 2015-2016 season to the 2023-2024 season.

#Instructions for this section:
  #You have only been supplied vectors. You will need to create the matrices yourself.
  #Seasons are labeled based on the first year in the season
  #E.g. the 2012-2013 season is presented as simply 2012

#Notes and Corrections to the data:
  #Joel Embiid: 2015 - DID NOT PLAY
  #Kevin Durant: 2019  - DID NOT PLAY
  #Kawhi Leonard: 2022 _ DID NOT PLAY

#Free throw attempts represent the number of times a player has the opportunity to take a free throw during a game.
#Free throw percentage is often calculated as the ratio of successful free throws 
#to the total number of free throw attempts, expressed as a percentage. 


#Seasons
Seasons <- c("2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023")

#Players
Player <- c("Stephen Curry", "LeBron James", "Nikola Jokic", "Joel Embiid", "Bradley Beal", "Kevin Durant", "Paul George", "Damian Lillard", "Giannis Antetokounmpo", "Kawhi Leonard")

#Free Throws
StephenCurry_FT <- c(363, 325, 278, 263, 26, 362, 275, 257, 299)
LeBronJames_FT <- c(359, 358, 388, 278, 264, 178, 254, 251, 303)
NikolaJokic_FT <- c(154, 188, 266, 289, 246, 342, 379, 341, 358)
JoelEmbiid_FT <- c(0, 191, 359, 522, 351, 471, 654, 661, 399)
BradleyBeal_FT <- c(135, 282, 292, 362, 385, 408, 169, 192, 109)
KevinDurant_FT <- c(447, 336, 359, 448, 0, 210, 372, 307, 362)
PaulGeorge_FT <- c(454, 336, 338, 453, 190, 198, 127, 256, 264)
DamianLillard_FT <- c(414, 488, 493, 468, 460, 449, 159, 510, 473)
GiannisAntetokounmpo_FT <- c(296, 471, 487, 500, 398, 398, 553, 498, 514)
KawhiLeonard_FT <- c(292, 469, 31, 364, 356, 261, 0, 243, 253)

#Free Throw Attempts
StephenCurry_FTA <- c(400, 362, 302, 287, 26, 395, 298, 281, 324)
LeBronJames_FTA <- c(491, 531, 531, 418, 381, 255, 336, 327, 404)
NikolaJokic_FTA <- c(190, 228, 313, 352, 301, 394, 468, 415, 438)
JoelEmbiid_FTA <- c(0, 244, 467, 649, 435, 548, 803, 771, 452)
BradleyBeal_FTA <- c(176, 342, 369, 448, 457, 459, 203, 228, 134)
KevinDurant_FTA <- c(498, 384, 404, 506, 0, 238, 409, 334, 423)
PaulGeorge_FTA <- c(528, 374, 411, 540, 217, 228, 148, 294, 291)
DamianLillard_FTA <- c(464, 545, 538, 513, 518, 484, 181, 558, 514)
GiannisAntetokounmpo_FTA <- c(409, 612, 641, 686, 629, 581, 766, 772, 782)
KawhiLeonard_FTA <- c(334, 533, 38, 426, 402, 295, 0, 279, 286)
  
#1.Create two matrices for Free Throws and Free Throws Attempts. 
#After creating the matrices, please remove the vectors from the environment.

free_throws <- rbind(StephenCurry_FT, LeBronJames_FT, NikolaJokic_FT, JoelEmbiid_FT, BradleyBeal_FT, KevinDurant_FT, PaulGeorge_FT, DamianLillard_FT, GiannisAntetokounmpo_FT, KawhiLeonard_FT)
free_throw_attempts <- rbind(StephenCurry_FTA, LeBronJames_FTA, NikolaJokic_FTA, JoelEmbiid_FTA, BradleyBeal_FTA, KevinDurant_FTA, PaulGeorge_FTA, DamianLillard_FTA, GiannisAntetokounmpo_FTA, KawhiLeonard_FTA)
rm(StephenCurry_FT, LeBronJames_FT, NikolaJokic_FT, JoelEmbiid_FT, BradleyBeal_FT, KevinDurant_FT, PaulGeorge_FT, DamianLillard_FT, GiannisAntetokounmpo_FT, KawhiLeonard_FT, StephenCurry_FTA, LeBronJames_FTA, NikolaJokic_FTA, JoelEmbiid_FTA, BradleyBeal_FTA, KevinDurant_FTA, PaulGeorge_FTA, DamianLillard_FTA, GiannisAntetokounmpo_FTA, KawhiLeonard_FTA)

#2.Please assign "Seasons" and "Player" as column and row names for the matrices.

colnames(free_throws) <- Seasons
rownames(free_throws) <- Player
colnames(free_throw_attempts) <- Seasons
rownames(free_throw_attempts) <- Player

#3.Calculate the Free throw percentage for each player and season. 
#Put the results into a matrix.
#Free throw percentage is often calculated as the ratio of successful free throws 
#to the total number of free throw attempts, expressed as a percentage.

#!Please multiply the results by 100, then round the results to two decimals. 

free_throw_percentage <- round((free_throws / free_throw_attempts) * 100, 2)

#4. If we can "NaN" in the matrix, please replace "NaN" with 0. 

free_throw_percentage[is.nan(free_throw_percentage)] <- 0

#5. Please return the Free Throw percentage of "Giannis Antetokounmpo" in the season of 2017-2018.
#(Please use the names to retrieve data from the vector)
#The return result should include the name and year.

free_throw_percentage["Giannis Antetokounmpo", "2017"]

#6.*Please calculate the Free Throw Percentage for each player of the 9 years in total. 
#Please create a vector to save the results. 
#Hint1: You cannot sum the percentage together directly. 
#You need to calculate the total Free Throws and Free Throws Attempts for each players of the 9 years.
#Hint2: You can use rowSums() to sum up the number in each matrix row. 

total_free_throws <- rowSums(free_throws)
total_free_throw_attempts <- rowSums(free_throw_attempts)
total_free_throw_attempt_percentage <- round((total_free_throws / total_free_throw_attempts) * 100, 2)

#7. Return the names of players who have the highest Free Throw Percentage over the last 8 years.

Player[total_free_throw_attempt_percentage == max(total_free_throw_attempt_percentage)]

#########################################################################
###########Section Three: Data Frame and Clustering Analysis#############
#########################################################################
#You are a Data Scientist working for a laundry-pickup services start-up "WeWashYouSleep". 
#This is a relatively small company, and they cannot compete with the big players in the major cities. 
#The company’s strategy is to build a vast network in the smaller cities. 
#"WeWashYouSleep" already had a strong presence in 150 locations.

#You need to solve the following question:
#Identify which of the two sales regions is performing better (outperforms the other in 2 of the 3 metrics):
#a. Average "Revenue per city" 
#b. Average "marketing spend per city" 
#c. Average "ROMI per city" (Revenue/Marketing spend)


#1.Please load the .csv file "Store Information".

store_information <- read.csv(file.choose())

#2.Calculate a new variable of "Return on Marketing Investment" (Revenue/Marketing spend).
#Return on Marketing Investment (ROMI) is a metric used to measure the financial return on a marketing campaign 
#or initiative relative to the cost of the marketing effort.
#The ratio indicates how much revenue can be gained for every dollar spent on marketing. 
#Please round the results to 2 decimals.

store_information$ROMI <- round(store_information$Revenue / store_information$Marketing.Spend, 2)

#3.Please calculate average revenue, average marketing spend, and average return on marketing investment for EACH REGION.
#Hint: You can simply take the average based on Region. 
#For 'Return on Marketing Investment,' you do NOT need to sum the revenue and marketing spend beforehand.

region_1_data <- store_information[store_information$Sales.Region == "Region 1", ]
region_2_data <- store_information[store_information$Sales.Region == "Region 2", ]
average_revenue_1 <- mean(region_1_data$Revenue)
average_marketing_spend_1 <- mean(region_1_data$Marketing.Spend)
average_ROMI_1 <- mean(region_1_data$ROMI)
average_revenue_2 <- mean(region_2_data$Revenue)
average_marketing_spend_2 <- mean(region_2_data$Marketing.Spend)
average_ROMI_2 <- mean(region_2_data$ROMI)

#4.Identify which of the two sales regions is performing better (outperforms the other in 2 of the 3 metrics):

higher_revenue <- if(average_revenue_1 > average_revenue_2) {
  "Region 1"
} else {
  "Region 2"
}
higher_marketing_spend <- if(average_marketing_spend_1 > average_marketing_spend_2) {
  "Region 1"
} else {
  "Region 2"
}
higher_ROMI <- if(average_ROMI_1 > average_ROMI_2) {
  "Region 1"
} else {
  "Region 2"
}

region_results <- c(higher_revenue, higher_marketing_spend, higher_ROMI)
print(region_results)

################# Clustering - Store Information   ######################

#Please run a cluster analysis based on "Marketing Spend" and "Revenue".
#5. pick the variable needed

cluster_data <- store_information[, c("Marketing.Spend", "Revenue")]

#6. Elbow Method
#Using the elbow method to find the optimal number of clusters

set.seed(123)
sumofsquare <- vector()
for(i in 1:10) {
  sumofsquare[i] <- sum(kmeans(cluster_data, centers = i)$withinss)
}
sum(kmeans(cluster_data,1)$withinss)
sum(kmeans(cluster_data,2)$withinss)
sum(kmeans(cluster_data,3)$withinss)
sum(kmeans(cluster_data,4)$withinss)

plot(x = 1:10,
     y = sumofsquare,
     type = "b",
     main = 'Elbow Plot', 
     xlab = 'Number of Clusters',
     ylab = 'Sum of Square'
)

#7. Run the clustering analysis

cluster_results <- kmeans(cluster_data, 3)

#8. print the results
#Please print two plot: one colored by clusters and one colored by region. 

plot(x = cluster_data$Marketing.Spend,
     y = cluster_data$Revenue,
     col = cluster_results$cluster,
     main = "Colored by Clusters",
     xlab = "Marketing Spend",
     ylab = "Revenue"
)

plot(x = cluster_data$Marketing.Spend,
     y = cluster_data$Revenue,
     col = as.factor(store_information$Sales.Region),
     main = "Colored by Sales Region",
     xlab = "Marketing Spend",
     ylab = "Revenue"
)

#9. Please write your interpretation of the results below:

# In the colored by clusters plot, the red cluster has a much higher revenue potential
# and the green group has much lower revenue potential, despite some having a high marketing spend
# the black group seems to be the most consistent of the 3 with few outliers.
# In the group by region plot, there seems to be an even variability of the two regions.
# Though, the red group seems to have the most marketing spend, on average, while
# it seems that the black group has more average revenue.


#########################################################################
####################Section Four: Data Preprocessing#####################
#########################################################################
#The dataset records information of 1000 employees. 

#1.Load the “Employee Data.csv” as a data frame 

employee_data <- read.csv(file.choose())

#2.Data Preparation/Missing Values:
#a.Print all the rows including missing values.

print(employee_data)

#b.Salary: Please convert salary as numerical data by removing the symbols ("$" and ",") in the data.

employee_data$salary <- as.numeric(gsub("[$,]", "", employee_data$salary))

#c.Title: If there are missing values in the Title, please remove the row and reset the index.

employee_data$title[employee_data$title == ""] <- NA
employee_data <- employee_data[!is.na(employee_data$title), ]
row.names(employee_data) <- NULL

#d.Salary: Please replace the missing values of salary with the median of the same Title.

median(employee_data$salary, na.rm = T)
meanbygroup <- ave((employee_data$salary), employee_data$title, FUN = function(x) median(x, na.rm = T))
employee_data$salary <- ifelse(is.na(employee_data$salary), meanbygroup, employee_data$salary)

#e.State: Please replace the missing values of State based on the cities. 
#Please use ifelse() and ave(). 

employee_data$state[employee_data$state == ""] <- NA
employee_data$state <- ifelse(is.na(employee_data$state), ave(employee_data$city, employee_data$city, FUN = function(group) {
  city <- group[1]
  ifelse(city == "Ogden" | city == "Salt Lake City" | city == "Provo", "UT", "NV")}), employee_data$state)

#3.Complete the following tasks: 
#a.Check the last 20 rows of the data frame. 

for (i in 0:19) {
  print(employee_data[997 - i, ])
}

#b.Check the structure of the data frame. 

str(employee_data)

#c.Use the summary command to check how many employees are in each City. 

summary(as.factor(employee_data$city))

#4.Complete the following filtering tasks:    
#a.Print all the employees from “Provo”.

print(employee_data[employee_data$city == "Provo", ])

#b.Print all the employees who have salary larger than or equal to $40,000 and less than or equal to $60,000. 

print(employee_data[employee_data$salary >= 40000 & employee_data$salary <= 60000,])

#5.a.Calculate the average Salary for each Title (using aggregate ())

?aggregate
average_salary_by_title <- aggregate(employee_data$salary, by = list(employee_data$title), FUN = mean)


#b.Calculate the median Salary for each City (using aggregate ())

median_salary_by_city <- aggregate(employee_data$salary, by = list(employee_data$city), FUN = median)

#########################################################################
####################Section Five: Sentiment Analysis#####################
#########################################################################
#1.Please go to "https://www.rottentomatoes.com/" 
#2.Pick a movie you like and go to the movie's webpage. (Example provides in the pdf file)
#3.Select either "All Audience" or "Verified Audience" 
#4.Please copy your url below: 

url = paste0("https://www.rottentomatoes.com/m/sinners_2025/reviews?type=user")

#5. Get more than 50 reviews from the movie you picked. 
#(If your selected movie has fewer than 50 reviews, choose a different movie.)
#Remove the spaces before and after the reviews. 
#Hint: You can save the webpage and read from the saved webpage. 

library(rvest)
library(tm)
library(wordcloud)
library(tidyverse)
library(tidytext)
library(textdata)

movie_reviews <- file.choose() %>%
  read_html() %>%
  html_nodes(css = "p.audience-reviews__review.js-review-text") %>%
  html_text
  movie_reviews <- gsub(pattern = "^\\s+|\\s+$|\\s{2,}", replacement = "", movie_reviews)
  movie_reviews <- gsub(pattern = "\n", replacement = "", movie_reviews)
print(movie_reviews)

#6.Conduct a Sentiment Analysis on the collected reviews.

movie_reviews <- data.frame(movie_reviews)
token <- unnest_tokens(movie_reviews, output = word, input = movie_reviews)
bing_lexicon <- get_sentiments("bing")
sentiment_analysis <- inner_join(token, bing_lexicon, by = "word")
sentiment_score <- count(sentiment_analysis, word, sentiment)

#7.Create a Word Cloud based on the results of the sentiment analysis (see the example in the .pdf).

colors <- c("red", "darkgreen")
names(colors) <- c("negative", "positive")
wordcloud(sentiment_score$word, sentiment_score$n,
          min.freq = 1, 
          scale = c(3, 0.5),
          max.words = 100,
          colors = colors[sentiment_score$sentiment],
          ordered.colors = T)

#########################################################################
####################Section Six: Web Scraping############################
#########################################################################
#1.Please visit the ESPN site to scrape the overall regular season data for a player you like. 
#https://www.espn.com/
#The detail is shown in the .pdf.
#Please select your favorite sport and player
#Please select the "stat" for the player you picked

#Note: If there are multiple tables, please select the “Regular Season Totals”. 
#PLEASE remove the “career” row in the final table. 
#Please assign seasons as the names of rows. 

#Note: A tibble is a modern version of a data frame in R, introduced by the tidyverse package. 
#Tibble is part of the tidyverse ecosystem and is designed to be a more user-friendly and consistent 
#alternative to the traditional data frame.

url2 <- paste0("https://www.espn.com/nba/player/stats/_/id/4432573/paolo-banchero")

paolo_stats <- url2 %>%
  read_html() %>%
  html_nodes("table") %>%
  .[[4]] %>%
  html_table(header = T)

seasons <- c("2022-2023", "2023-2024", "2024-2025")

table <- paolo_stats %>%
  filter(FG != "1531-3440")

table$Season <- seasons

paolo_stats <- paolo_stats %>%
  rownames(seasons)

table <- table %>%
  column_to_rownames("Season")

#2.Go to #https://open-meteo.com/
#Select Air Quality API
#Please use their api service to get the pm10 and pm2.5 ratio for La Crosse during the last two weeks. 

library(jsonlite)
library(httr)

#a.Call API through Endpoint 

base_url <- "https://air-quality-api.open-meteo.com/v1/air-quality"
word_url <- "?latitude=43.8014&longitude=-91.2396&hourly=pm2_5,pm10&past_days=14"
full_url <- paste0(base_url, word_url)

api_response <- httr::GET(full_url)

#b.Get the response in JSON 

api_response$status_code
api_response$content
api_content <- rawToChar(api_response$content)
Json_content <- fromJSON(api_content)

#c.Create the data frame with time, pm10, and pm2.5. (Please see example in the .pdf)

air_data <- data.frame(Json_content$hourly$time, Json_content$hourly$pm10, Json_content$hourly$pm2_5)





employee_data <- read.csv(file.choose())

print(employee_data)

employee_data$salary <- as.numeric(gsub("[$,]", "", employee_data$salary))

employee_data$title[employee_data$title == ""] <- NA
employee_data <- employee_data[!is.na(employee_data$title), ]
row.names(employee_data) <- NULL

median(employee_data$salary, na.rm = T)
meanbygroup <- ave((employee_data$salary), employee_data$title, FUN = function(x) median(x, na.rm = T))
employee_data$salary <- ifelse(is.na(employee_data$salary), meanbygroup, employee_data$salary)

employee_data$state[employee_data$state == ""] <- NA
employee_data$state <- ifelse(is.na(employee_data$state), ave(employee_data$city, employee_data$city, FUN = function(group) {
  city <- group[1]
  ifelse(city == "Ogden" | city == "Salt Lake City" | city == "Provo", "UT", "NV")}), employee_data$state)





library(jsonlite)
library(httr)

base_url <- "https://air-quality-api.open-meteo.com/v1/air-quality"
word_url <- "?latitude=43.8014&longitude=-91.2396&hourly=pm2_5,pm10&past_days=14"
full_url <- paste0(base_url, word_url)

api_response <- httr::GET(full_url)

api_response$status_code
api_response$content
api_content <- rawToChar(api_response$content)
Json_content <- fromJSON(api_content)

air_data <- data.frame(Json_content$hourly$time, Json_content$hourly$pm10, Json_content$hourly$pm2_5)
