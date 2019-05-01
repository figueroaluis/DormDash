create database DormDash;
use DormDash;

create table users (
	username varchar(40) not null,
	password varchar(512) not null,
	facebookID int,
	venmoID int,
	phoneNumber varchar(15),
	is_registered_worker boolean,
	is_working boolean default false,
	primary key(username));

create table menu (
	dayOfWeek varchar(10),
	foodItem varchar(50),
	primary key(dayOfWeek));

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
	foreign key(workerID) references workers(workerID),
	foreign key(orderPickupLocation) references locations(buildingName),
	foreign key(orderDropoffLocation) references locations(buildingName));



