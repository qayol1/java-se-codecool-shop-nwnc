DROP TABLE IF EXISTS supplier, productCategory, product;

CREATE TABLE product
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(100),
  description TEXT,
  defaultPrice FLOAT ,
  currencyString VARCHAR(100),
  productCategory VARCHAR(100),
  supplier VARCHAR(100)
);

CREATE TABLE supplier
(
  id SERIAL PRIMARY KEY ,
  name VARCHAR(40),
  description TEXT
);

CREATE TABLE productCategory
(
  id SERIAL PRIMARY KEY,
  name VARCHAR(40),
  department VARCHAR(40),
  description TEXT
);
