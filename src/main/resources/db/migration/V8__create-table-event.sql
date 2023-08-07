create table events(
                       id bigint not null auto_increment,
                       event_name varchar(100) not null,
                       address varchar(255) not null,
                       description_rules varchar(255),
                       date_game date not null,
                       hour_game varchar(6) not null,
                       duration varchar(6),
                       sport varchar(50) not null,
                       modality varchar(50) not null,
                       vacancies integer not null,
                       month_value double,
                       single_value double,
                       active tinyint not null,
                       primary key(id)
);
