DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS shoppingcarts;
DROP TABLE IF EXISTS shoppingcartitem;

CREATE TABLE shoppingcarts
(
  id SERIAL PRIMARY KEY
);

CREATE TABLE shoppingcartitem(
  id SERIAL PRIMARY KEY,
  shoppingcartid INT REFERENCES  shoppingcarts(id),
  productid INT,
  productcount INT
);


CREATE TABLE customers
(
id SERIAL PRIMARY KEY,
firstname varchar(40),
lastname varchar(40),
shoppingcartid INT REFERENCES shoppingcarts(id)
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
INSERT INTO users (username,password,role,customerid) VALUES ('batman','robin','USER','1');