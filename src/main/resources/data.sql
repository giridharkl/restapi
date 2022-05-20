/* Init dataset for H2 Database */
/* Continents */
insert into continent(id,name) values(1,'Asia');
insert into continent(id,name) values(2,'Africa');
insert into continent(id,name) values(3,'Antarctica');
insert into continent(id,name) values(4,'Australia');
insert into continent(id,name) values(5,'Europe');
insert into continent(id,name) values(6,'North America');
insert into continent(id,name) values(7,'South America');
alter sequence continent_seq restart with 8;
/* Countries */
insert into country(id,name) values(1,'USA');
insert into country(id,name) values(2,'Finland');
insert into country(id,name) values(3,'Germany');
alter sequence country_seq restart with 4;
/* Cities */
insert into city(id,name,continent_id,country_id) values(1,'Helsinki',5,2);
insert into city(id,name,continent_id,country_id) values(2,'Frakfurt',5,3);
insert into city(id,name,continent_id,country_id) values(3,'NewYork',6,1);
alter sequence city_seq restart with 4;
