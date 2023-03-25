create table customer
(
    id int primary key auto_increment,
    place varchar(250) not null,
    email varchar(100) not null,
    phone varchar(100)

);

create table longcurtain
(
    id int primary key auto_increment,
    room varchar(250) not null,
    length_curtain_rod int not null,
    left_wall int not null,
    right_wall int not null,
    customer_id int null,
    foreign key (customer_id) references customer (id)
);
