insert into country (country_id, name) values (1, 'Serbia');
insert into country (country_id, name) values (2, 'Germany');

insert into city (city_id, country_id, name, ptt_number) values (1, 1, 'Novi Sad', '21000');
insert into city (city_id, country_id, name, ptt_number) values (2, 1, 'Beograd', '11000');

insert into user (user_id, name, surname, email, password) values (1, 'Pera', 'Peric', 'p@p.com', 'passpass');
insert into client (client_id, name, surname, email, password, address, date_of_birth) values (1, 'Marko', 'Markovic', 'm@m.com', 'passpass', 'Bulevar Oslovodjenja 44', '18/07/1991');
