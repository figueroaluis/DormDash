create database DormDash;
use DormDash;

create table users (
	username varchar(40) not null,
	password blob not null,
	facebookID int,
	venmoID int,
	phoneNumber varchar(15),
	is_registered_worker boolean,
	is_working boolean default false,
	primary key(username));

create table menu (
	date varchar(15),
	foodItem varchar(100),
	mealType varchar(20),
	primary key(foodItem, mealType));

create table locations (
	buildingName varchar(40) not null,
	buildingCharge float,
	primary key(buildingName));

create table orders (
	username varchar(40) not null,
	orderID int not null,
	foodOrder varchar(50),
	orderPickupLocation varchar(40),
	orderDropoffLocation varchar(40),
	pickupTime time,
	dropoffTime time,
	primary key(orderID),
	foreign key(username) references users(username),
	foreign key(orderPickupLocation) references locations(buildingName),
	foreign key(orderDropoffLocation) references locations(buildingName));



