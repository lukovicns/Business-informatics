
insert into country(name) values ('Srbija');
insert into country(name) values ('Rusija');
insert into country(name) values ('Italija');
insert into country(name) values ('Peru');

insert into currency(name, official_code, domicilna, country_id) values ('Dinar', 'DIN', true, 1);
insert into currency(name, official_code, domicilna, country_id) values ('Rublja', 'RUB', true, 2);
insert into currency(name, official_code, domicilna, country_id) values ('Lira', 'LIR', true, 3);
insert into currency(name, official_code, domicilna, country_id) values ('Sol', 'SOL', true, 4);

insert into bank(bank_id,bank_pib,bank_name,bank_address,bank_email,bank_web,bank_tel,bank_fax,bank_act,bank_swt,bank_tr_acc,country_id)
values(1,'993456789','Vojvodjanska banka','Bulevear fronta 20','vojvodjanska@gmail.com','www.vobanka.com','021/555-333','232-444',true,'23345678','333-000-000',1);

insert into bank(bank_id,bank_pib,bank_name,bank_address,bank_email,bank_web,bank_tel,bank_fax,bank_act,bank_swt,bank_tr_acc,country_id)
values(2,'123456789','Komercijalna banka','Bulvear nardonog fronta 20','komercijalna@mail.rs','www.komercijalna.com','024/555-131','444-444',true,'12345678','222-000-000',1);

insert into bank(bank_id,bank_pib,bank_name,bank_address,bank_email,bank_web,bank_tel,bank_fax,bank_act,bank_swt,bank_tr_acc,country_id)
values(3,'123456788','Erste banka','Bulvear nardonog fronta 100','erstelna@mail.rs','www.erste.com','024/525-111','123-123',true,'98456456','666-000-000',2);

insert into exchange_list(date, number_of_exchange_list, used_since, bank_bank_id) 
values('2017-10-15', 123, '2017-10-1', 1);

insert into exchange_list(date, number_of_exchange_list, used_since, bank_bank_id) 
values('2017-2-15', 222, '2014-10-1', 1);

insert into exchange_list(date, number_of_exchange_list, used_since, bank_bank_id) 
values('2017-1-2', 321, '2017-10-1', 2);

insert into currency_exchange(buy_rate, middle_rate, sell_rate, exchange_list_id, primary_currency_id, according_to_currency_id) 
values (321, 320, 319, 1, 1, 2);
insert into currency_exchange(buy_rate, middle_rate, sell_rate, exchange_list_id, primary_currency_id, according_to_currency_id) 
values (100.4, 102.4, 97.9, 2, 1, 3);
insert into currency_exchange(buy_rate, middle_rate, sell_rate, exchange_list_id, primary_currency_id, according_to_currency_id) 
values (50, 51, 52, 3, 3, 4);

insert into city(city_id, name, ptt_number, country_id) values(1, 'Beograd', '11000', 1);
insert into city(city_id, name, ptt_number, country_id) values(2, 'Novi sad', '21000', 1);
insert into city(city_id, name, ptt_number, country_id) values(3, 'Moskva', '31000', 2);
insert into city(city_id, name, ptt_number, country_id) values(4, 'Rim', '41000', 3);

insert into user (user_id, name, surname, email, password) values (1, 'Pera', 'Peric', 'p@p.com', 'passpass');
insert into client (client_id, name, surname, email, password, address, date_of_birth) values (1, 'Marko', 'Markovic', 'm@m.com', 'passpass', 'Bulevar Oslovodjenja 44', '18/07/1991');
insert into client (client_id, name, surname, email, password, address, date_of_birth) values (2, 'Jovan', 'Jovanovic', 'j@j.com', 'passpass', 'Bulevar Oslovodjenja 43', '04/02/1992');
insert into client (client_id, name, surname, email, password, address, date_of_birth) values (3, 'Goran', 'Goranovic', 'g@g.com', 'passpass', 'Bulevar Oslovodjenja 42', '06/12/1985');

insert into account(account_id,account_num,account_date,account_active,bank_id,client_id, currency_id) values(1,'333-111-333','2017-05-05',true,1,1, 1);
insert into account(account_id,account_num,account_date,account_active,bank_id,client_id, currency_id) values(2,'222-111-444','2017-05-06',true,3,2, 2);
insert into account(account_id,account_num,account_date,account_active,bank_id,client_id, currency_id) values(3,'666-111-333','2017-06-03',true,2,3, 3);

insert into analytical_statement(amount, approval_authorization_number, approval_model, currency_date, date_of_receipt, 
								 debit_authorization_number, error_type, model, originator, originator_account, purpose, recipient,
								 recipient_account, payment, urgently, currency_id,
								 place_of_acceptance_id) 
	   value (15000.00, '123456789', '97', '2017-05-09', '2017-05-09', '100018356', 'IZVRSEN_NALOG', '97',
	   		  'Pera Peric, Novi Sad', '333-111-333', 'Uplata poreza na promet proizvoda', 'Poreska uprava',
	   		  '222-714121843-73', false, false, 1, 2);
insert into analytical_statement(amount, approval_authorization_number, approval_model, currency_date, date_of_receipt, 
								 debit_authorization_number, error_type, model, originator, originator_account, purpose, recipient,
								 recipient_account, payment, urgently, currency_id,
								 place_of_acceptance_id) 
	   value (13500.00, '123456789', '97', '2017-05-16', '2017-05-16', '100018356', 'IZVRSEN_NALOG', '97',
	   		  'Igre na srecu, Beograd', '222-111-333', 'Dobitag na greb greb kartici', 'Ana Maric, Novi Sad',
	   		  '333-111-333', true, false, 1, 1);	   		  
insert into analytical_statement(amount, approval_authorization_number, approval_model, currency_date, date_of_receipt, 
								 debit_authorization_number, error_type, model, originator, originator_account, purpose, recipient,
								 recipient_account, payment, urgently, currency_id,
								 place_of_acceptance_id) 
	   value (1500.00, '123456789', '97', '2017-06-03', '2017-06-03', '100018356', 'IZVRSEN_NALOG', '97',
	   		  'Stefan Popic, Doze Djerdja, Novi Sad', '666-111-333', 'Uplata za overu semestra', 'Fakultet tehnickih nauka, Trg Dositeja Obradovica 1, Novi Sad',
	   		  '840-714121843-73', false, false, 3, 1);

insert into daily_account_status(id, date,account_account_id, previous_amount, transfer_expenses, number_of_changes, transfer_in_favor, current_amount,analytical_statement_analytical_statement_id)
values(1, '2017-05-09', 1, 50000, 15000, 1, 0, 35000,1);
insert into daily_account_status(id, date,account_account_id, previous_amount, transfer_expenses, number_of_changes, transfer_in_favor, current_amount,analytical_statement_analytical_statement_id)
values(2, '2017-05-16', 1, 35000, 0, 1, 13500, 48500,1);
insert into daily_account_status(id, date,account_account_id, previous_amount, transfer_expenses, number_of_changes, transfer_in_favor, current_amount,analytical_statement_analytical_statement_id)
values(3, '2017-06-03', 3, 0, 1500, 1, 0, -1500,2);