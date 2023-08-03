create table cadastros(
    id bigint not null auto_increment,
    first_name varchar(255),
    last_name varchar(255),
    age int,
    country varchar(255),
    primary key(id)
);