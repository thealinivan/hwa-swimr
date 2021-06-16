create schema if not exists testdb;
use testdb;

drop table if exists place;
drop table if exists club;

create table place
(
	`id` integer AUTO_INCREMENT PRIMARY KEY,
	`name` varchar(255),
	`postcode` varchar(255),
	`club_id` integer
);

create table club
(
	`id` integer AUTO_INCREMENT PRIMARY KEY,
	`name` varchar(255), make varchar(255)
);


