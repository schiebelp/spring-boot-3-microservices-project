CREATE TABLE inventory
(
    id       SERIAL PRIMARY KEY,
    sku_code varchar(255),
    quantity int,
    location varchar(255)
);