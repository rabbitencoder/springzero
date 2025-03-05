-- Insert sample data into the USERDETAILS table
INSERT INTO userdetails (id, user_name, first_name, last_name, email_address, role, ssn) VALUES
    (101, 'r', 'rahul', 'singh', 'r@example.com', 'admin', 'ssn01');
INSERT INTO userdetails (id, user_name, first_name, last_name, email_address, role, ssn) VALUES
    (102, 'a', 'rahul', 'singh', 'a@example.com', 'admin', 'ssn02');
INSERT INTO userdetails (id, user_name, first_name, last_name, email_address, role, ssn) VALUES
    (103, 'h', 'rahul', 'singh', 'h@example.com', 'admin', 'ssn03');

insert into orders (order_Id, order_Description, user_Id) values (201, 'sugar', 101);
insert into orders (order_Id, order_Description, user_Id) values (202, 'oil', 101);
insert into orders (order_Id, order_Description, user_Id) values (203, 'sugar', 102);
insert into orders (order_Id, order_Description, user_Id) values (204, 'sugar', 103);