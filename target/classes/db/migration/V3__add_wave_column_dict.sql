alter table longcurtain add column wave int not null;


create table dict_moveto(
                      id int primary key auto_increment,
                      description_pl varchar(400) not null,
                      description_org varchar(400) not null
);
