create database DormDash;
use DormDash;

create table users (
	username varchar(40) not null,
	password varchar(40) not null,
	userID int not null,
	facebookID int,
	venmoID int,
	phoneNumber varchar(15),
	primary key(userID));

create table workers (
	username varchar(40) not null,
	password varchar(40) not null,
	workerID int not null,
	facebookID int,
	venmoID int,
	phoneNumber varchar(15),
	isWorking boolean,
	primary key(workerID));

create table menu (
	dayOfWeek varchar(10),
	foodItem varchar(50),
	primary key(dayOfWeek));

create table locations (
	buildingName varchar(40) not null,
	buildingCharge float,
	primary key(buildingName));

create table orders (
	userID int,
	workerID int,
	orderID int not null,
	foodOrder varchar(50),
	orderPickupLocation varchar(40),
	orderDropoffLocation varchar(40),
	pickupTime time,
	dropoffTime time,
	primary key(orderID),
	foreign key(userID) references users(userID),
	foreign key(workerID) references workers(workerID),
	foreign key(orderPickupLocation) references locations(buildingName),
	foreign key(orderDropoffLocation) references locations(buildingName));



