
create table prices(
                      id int primary key auto_increment,
                      length_curtain_base int not null,
                      price float(20.5) not null,
                      system_id int not null,
                      foreign key (system_id) references dict_system (id)


);

alter table dict_wave add column ratio_wave float(20.5) not null;

