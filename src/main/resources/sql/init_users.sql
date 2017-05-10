DROP TABLE IF EXISTS customers,users,shoppingcarts,shoppingcarelements;


CREATE TABLE shoppingcarts (
  id SERIAL PRIMARY KEY
);


CREATE TABLE shoppingcarelements
(
  id SERIAL PRIMARY KEY,
  shoppingcartid INT,
  productid INT,
  productcount INT
);


CREATE TABLE customers
(
id SERIAL PRIMARY KEY,
firstname varchar(40),
lastname varchar(40),
email VARCHAR(255),
phonenumber VARCHAR(40),
billingaddress INT,
shippingaddress INT,
shoppingcartid INT
);



CREATE TABLE users
(
id SERIAL PRIMARY KEY,
username varchar(40),
password varchar(40),
role VARCHAR(40),
customerid INT REFERENCES customers(id)
);


INSERT INTO customers(firstname,lastname) VALUES ('bruce','wayne');
