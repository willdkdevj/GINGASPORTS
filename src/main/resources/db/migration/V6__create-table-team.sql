create table teams(
                       id bigint not null auto_increment,
                       name VARCHAR(255) not null ,
                       emblem VARCHAR(255) not null ,
                       ranking NUMERIC,
                       classification NUMERIC,
                       active tinyint not null,
                       primary key(id)
);
