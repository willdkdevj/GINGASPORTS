create table players(
                       id bigint not null auto_increment,
                       name varchar(100) not null,
                       email varchar(100) not null,
                       alias varchar(20) not null,
                       birthdate date not null,
                       phone varchar(14) not null,
                       distance numeric not null,
                       scout numeric not null,
                       notification numeric not null,
                       pcd numeric not null,
                       sport varchar(50) not null,
                       modality varchar(50) not null,
                       position varchar(25) not null,
                       handedness varchar(20) not null,
                       wearabledevice numeric not null,
                       primary key(id)
);
