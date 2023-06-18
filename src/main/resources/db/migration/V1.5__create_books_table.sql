create table if not exists books(
    id serial,
    title varchar(150) not null,
    category_id integer not null,
    author_id integer not null,
    constraint pk_book primary key (id),
    constraint fk_book_category foreign key (category_id) references categories(id),
    constraint fk_book_author foreign key (author_id) references authors(id)
)

