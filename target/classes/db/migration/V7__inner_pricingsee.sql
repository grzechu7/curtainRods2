
create table pricingsee(
                      id int primary key auto_increment,
                      pricingpl float(20.5) not null,
                      pricingorg float(20.5) null,
                      customer_id int not null,
                      longcurtain_id int not null,
                      timeprice Timestamp,
                      foreign key (customer_id) references customer (id),
                      foreign key (longcurtain_id) references longcurtain (id)


);

