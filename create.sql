create table car (car_id bigint not null, car_address varchar(255), car_brand varchar(255) not null, car_color varchar(255), car_description varchar(255), car_fuel varchar(255), car_km integer, car_manufacturer varchar(255), car_model varchar(255) not null, car_origin varchar(255), car_reference bigint not null, car_year integer, user_id bigint not null, primary key (car_id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table user (user_id bigint not null, user_email varchar(255) not null, user_password varchar(255) not null, user_phone float not null, user_username varchar(255) not null, primary key (user_id)) engine=InnoDB;
alter table car add constraint FKja1j4mm4rqlv6cnhgp1qqgtuj foreign key (user_id) references user (user_id);
create table car (car_id bigint not null, car_address varchar(255), car_brand varchar(255) not null, car_color varchar(255), car_description varchar(255), car_fuel varchar(255), car_km integer, car_manufacturer varchar(255), car_model varchar(255) not null, car_origin varchar(255), car_reference varchar(255), car_year integer, user_id bigint not null, primary key (car_id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table user (user_id bigint not null, user_email varchar(255) not null, user_password varchar(255) not null, user_phone float not null, user_username varchar(255) not null, primary key (user_id)) engine=InnoDB;
alter table car add constraint FKja1j4mm4rqlv6cnhgp1qqgtuj foreign key (user_id) references user (user_id);
create table car (car_id bigint not null, car_address varchar(255), car_brand varchar(255) not null, car_color varchar(255), car_description varchar(255), car_fuel varchar(255), car_km integer, car_manufacturer varchar(255), car_model varchar(255) not null, car_origin varchar(255), car_reference varchar(255), car_year integer, user_id bigint not null, primary key (car_id)) engine=InnoDB;
create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table user (user_id bigint not null, user_email varchar(255) not null, user_password varchar(255) not null, user_phone float not null, user_username varchar(255) not null, primary key (user_id)) engine=InnoDB;
alter table car add constraint FKja1j4mm4rqlv6cnhgp1qqgtuj foreign key (user_id) references user (user_id);