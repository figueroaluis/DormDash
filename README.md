----------DORM DASH----------
William Chen, Sam Miller, Luis Figueroa, Katy Uchiyama

DormDash is an on-demand food delivery system for Occidental College where students are able to order food from different food locations within the school and have them delivered to their location. 

Setup and Instructions:
For our project, we created an AWS server on which we ran a Virtual Machine with our MySQL database and Springboot server. We created our Android app with Android Studio version 3.4. Below is information for connecting to the server:


Crucial Info
IP address: ec2-3-14-49-112.us-east-2.compute.amazonaws.com
Port Number: 80

(optional) chmod 400 dormdash.pem

1. When in Terminal, navigate to folder with the pem key and input this command
ssh -i dormdash.pem ec2-user@ec2-34-209-125-83.us-west-2.compute.amazonaws.com

2. To run a jar, write java -jar "name of jar".jar


Within our zip file, most of our classes can be found under DormDash/app/src/main/java/com/figueroaluis/dormdash. More classes for our server is under DormDash/Server Info/Login_Register/src/main/java/MyServer. Our SQL file for table creation is under DormDash/Server Info/DormDash_db.sql and we called our database DormDash.
