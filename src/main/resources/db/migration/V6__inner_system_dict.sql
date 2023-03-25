
create table dict_system(
                      id int primary key auto_increment,
                      system_pl varchar(400) not null,
                      system_org varchar(400) not null
);

alter table longcurtain add column sizesystem_id int not null;
alter table longcurtain
    add foreign key (sizesystem_id) references dict_sizesystem (id);