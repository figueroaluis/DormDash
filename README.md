## DORM DASH
William Chen, Sam Miller, Luis Figueroa, Katy Uchiyama

DormDash is an on-demand food delivery system for Occidental College where students are able to order food from different food locations within the school and have them delivered to their location. 

### Setup and Instructions:
For our project, we created an AWS server on which we ran a Virtual Machine with our MySQL database and Springboot server. We created our Android app with Android Studio version 3.4. Below is information for connecting to the server:


#### Connecting to the Server
```IP address: 3.14.202.131```
```Port Number: 80```

When you need to connect to the server, let us know and we'll start it

(optional) ```chmod 400 dormdash.pem```

1. When in Terminal, navigate to folder with the pem key and input this command

```ssh -i dormdash_final.pem ec2-user@e3.14.202.131```

2. To run a jar, run the following command on the terminal:
```sudo java -jar "name of jar".jar```

Within our zip file, most of our classes can be found under DormDash/app/src/main/java/com/figueroaluis/dormdash. More classes for our server is under DormDash/Server Info/Login_Register/src/main/java/MyServer. Our SQL file for table creation is under DormDash/Server Info/DormDash_db.sql and we called our database DormDash.

### Project Info:
Initially we intended on webscraping the Marketplace and Coffee Cart websites to get menus for those tables. We created a working java file that scraped the websites, added the data to the database, and then pulled the data from the database to add to the website. We hoped this would work but we ran into a lot of issues along the way and ended up having to manually enter food data. It was difficult to account for every possible formatting of the website because there are different times and formatting used for each day.

### Future Features
Like any ambitious developer, we wanted to implement many more features than the ones we were able to get done.
Below, we list a couple of features that we have planned to complete in the near future:
* Confirm delivery
  * This is perhaps the next most important feature to include, since we want to keep track of what orders have keep completed.
* Finish android implementation of acceptOrder API call
  * We created the acceptOrder endpoint and currently have a list of orders with checkboxes on the Android side
* Venmo payment option
  * Option to tip the worker
* Maps to track location of Worker and Client
* In-app Messaging system
* Facebook Log in (for social media facilitations) 
