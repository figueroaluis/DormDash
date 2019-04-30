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
	dayOfWeek varchar(10),
	foodItem varchar(50),
	primary key(dayOfWeek));

create table locations (
	buildingName varchar(40) not null,
	buildingCharge float,
	primary key(buildingName));

create table orders (
	username varchar(40) not null,
	orderID int not null auto_increment,
	foodOrder varchar(100),
	orderPickupLocation varchar(40),
	orderDropoffLocation varchar(40),
	pickupTime time,
	dropoffTime time,
	primary key(orderID),
	foreign key(username) references users(username),
	foreign key(orderPickupLocation) references locations(buildingName),
	foreign key(orderDropoffLocation) references locations(buildingName));

INSERT INTO locations VALUES ("Berkus Hall", 3.00),
("Braun Hall", 3.00),
("Norris Hall", 3.00),
("Stearns Hall", 3.00),
("Stewart-Cleland Hall", 3.00),
("Pauley Hall", 2.00),
("Bell-Young Hall", 2.00),
("Erdman Hall", 2.00),
("Wylie Hall", 2.00),
("Newcomb Hall", 2.00),
("Chilcott Hall", 1.00),
("Haines Hall", 1.00);





