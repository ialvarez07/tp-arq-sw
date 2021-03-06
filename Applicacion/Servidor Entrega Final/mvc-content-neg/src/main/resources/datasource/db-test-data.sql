insert into T_CUSTOMER (NUMBER, NAME, USERNAME, DATE_OF_BIRTH, EMAIL, REWARDS_NEWSLETTER, MONTHLY_EMAIL_UPDATE, VERSION)
    values ('1000', 'Sam Dunlap', 'samd', '1979-11-15', 'samd@gmail.com', true, false, 0);
insert into T_CUSTOMER (NUMBER, NAME, USERNAME, DATE_OF_BIRTH, EMAIL, REWARDS_NEWSLETTER, MONTHLY_EMAIL_UPDATE, VERSION)
    values ('1001', 'Dollie R. Adams', 'dollie', '1974-09-23', 'dollie@hotmail.com', false, true, 0);
insert into T_CUSTOMER (NUMBER, NAME, USERNAME, DATE_OF_BIRTH, EMAIL, REWARDS_NEWSLETTER, MONTHLY_EMAIL_UPDATE, VERSION)
    values ('1002', 'Cornelia J. Andresen', 'cornelia', '1963-02-27', 'cornelia@yahoo.com', true, false, 0);
insert into T_CUSTOMER (NUMBER, NAME, USERNAME, DATE_OF_BIRTH, EMAIL, REWARDS_NEWSLETTER, MONTHLY_EMAIL_UPDATE, VERSION)
    values ('1003', 'Coral Villareal Betancourt', 'cvb', '1957-07-21', 'coralb@icerocket.com', false, true, 0);
insert into T_CUSTOMER (NUMBER, NAME, USERNAME, DATE_OF_BIRTH, EMAIL, REWARDS_NEWSLETTER, MONTHLY_EMAIL_UPDATE, VERSION)
    values ('1004', 'Jens I. Schmidt', 'schmidtj', '1976-04-23', 'schmidtj@netzero.com', true, false, 0);

insert into T_ACCOUNT (NUMBER, ACC_TYPE, CUST_ID, CREDIT_CARD, BALANCE, VERSION)
    values ('123456789', 'CREDIT', 0, '1234123412341234', 200.07, 0);
insert into T_ACCOUNT (NUMBER, ACC_TYPE, CUST_ID, CREDIT_CARD, BALANCE, VERSION)
    values ('123456001', 'CREDIT', 1, '1234123412340001', 489.24, 0);
insert into T_ACCOUNT (NUMBER, ACC_TYPE, CUST_ID, CREDIT_CARD, BALANCE, VERSION)
    values ('123456002', 'CREDIT', 2, '1234123412340002', 1265.84, 0);
insert into T_ACCOUNT (NUMBER, ACC_TYPE, CUST_ID, CREDIT_CARD, BALANCE, VERSION)
    values ('123456003', 'CREDIT', 3, '1234123412340003', 21.50, 0);
insert into T_ACCOUNT (NUMBER, ACC_TYPE, CUST_ID, CREDIT_CARD, BALANCE, VERSION)
    values ('123456004', 'CREDIT', 4, '1234123412340004', 804.47, 0);
    
insert into T_ACCOUNT (NUMBER, ACC_TYPE, CUST_ID, CREDIT_CARD, BALANCE, VERSION)
    values ('123456799', 'SAVINGS', 0, '', 6940.55, 0);
insert into T_ACCOUNT (NUMBER, ACC_TYPE, CUST_ID, CREDIT_CARD, BALANCE, VERSION)
    values ('123456788', 'CHECK', 0, '1010102020203030', 210.82, 0);
insert into T_ACCOUNT (NUMBER, ACC_TYPE, CUST_ID, CREDIT_CARD, BALANCE, VERSION)
    values ('123457001', 'CHECK', 1, '', 2169.15, 0);
insert into T_ACCOUNT (NUMBER, ACC_TYPE, CUST_ID, CREDIT_CARD, BALANCE, VERSION)
    values ('123457004', 'CHECK', 1, '', 1029.21, 0);
insert into T_ACCOUNT (NUMBER, ACC_TYPE, CUST_ID, CREDIT_CARD, BALANCE, VERSION)
    values ('123457002', 'SAVINGS', 2, '1234123412340202', 9371.92, 0);
insert into T_ACCOUNT (NUMBER, ACC_TYPE, CUST_ID, CREDIT_CARD, BALANCE, VERSION)
    values ('123457003', 'SAVINGS', 3, '1234123412340303', 2730.36, 0);

insert into T_ACCOUNT_TRANSACTION (ACCOUNT_ID, NAME, NUMBER, CODE, AMOUNT)
    values (0, 'Annabelle Lee', '987654321', '706050', 34.00);
insert into T_ACCOUNT_TRANSACTION (ACCOUNT_ID, NAME, NUMBER, CODE, AMOUNT)
    values (0, 'Smart IT Services', '998877661', '112233', 89.99);
    
insert into T_ACCOUNT_TRANSACTION (ACCOUNT_ID, NAME, NUMBER, CODE, AMOUNT)
    values (1, 'BJ Plumbing', '987654322', '706051', 278.00);
    
insert into T_ACCOUNT_TRANSACTION (ACCOUNT_ID, NAME, NUMBER, CODE, AMOUNT)
    values (2, 'Smart IT Services', '998877661', '112233', 69.99);
    
insert into T_ACCOUNT_TRANSACTION (ACCOUNT_ID, NAME, NUMBER, CODE, AMOUNT)
    values (3, 'Smart IT Services', '998877661', '112233', 59.99);
insert into T_ACCOUNT_TRANSACTION (ACCOUNT_ID, NAME, NUMBER, CODE, AMOUNT)
    values (3, 'BJ Plumbing', '987654322', '706051', 239.95);
insert into T_ACCOUNT_TRANSACTION (ACCOUNT_ID, NAME, NUMBER, CODE, AMOUNT)
    values (3, 'Teleservices Direct', '998877664', '445566', 34.80);
    
insert into T_ACCOUNT_TRANSACTION (ACCOUNT_ID, NAME, NUMBER, CODE, AMOUNT)
    values (4, 'Smart IT Services', '998877661', '112233', 69.99);
insert into T_ACCOUNT_TRANSACTION (ACCOUNT_ID, NAME, NUMBER, CODE, AMOUNT)
    values (4, 'Willis Supermart', '998877662', '122344', 69.99);