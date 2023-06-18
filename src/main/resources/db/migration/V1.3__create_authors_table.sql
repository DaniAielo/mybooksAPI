create table if not exists authors(
    id serial,
    name varchar(100) not null,
    gender char(1) not null,
    constraint pk_author primary key(id)
)

