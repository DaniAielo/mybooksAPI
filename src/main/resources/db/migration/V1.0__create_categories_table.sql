create table if not exists categories(
    id serial,
    name varchar(50) not null,
    constraint pk_category primary key(id)
)

