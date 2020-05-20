CREATE SCHEMA `internet-shop` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE products
(
    product_id BIGINT(11)   NOT NULL AUTO_INCREMENT,
    name      VARCHAR(225) NOT NULL,
    price     DECIMAL(11)  NOT NULL,
    PRIMARY KEY (product_id)
);
INSERT INTO products  (name , price)
VALUES  ('Phone', 224.6),
        ('Book', 76.7),
        ('LapTop', 666.6),
        ('Wallet', 346.6);

CREATE TABLE users
(
    user_id  BIGINT(11)   NOT NULL AUTO_INCREMENT,
    login    VARCHAR(256) NOT NULL,
    name     VARCHAR(256) NOT NULL,
    password VARCHAR(256) NOT NULL,
    salt     VARBINARY(300) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE roles
(
    role_id   BIGINT(11)   NOT NULL AUTO_INCREMENT,
    role_name VARCHAR(256) NOT NULL,
    PRIMARY KEY (role_id)
);

INSERT INTO roles (role_name) VALUES ('ADMIN');
INSERT INTO roles (role_name) VALUES ('USER');


CREATE TABLE users_role
(
    user_id BIGINT(11) NOT NULL,
    role_id BIGINT(11) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (role_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE carts
(
    cart_id BIGINT(11) NOT NULL AUTO_INCREMENT,
    user_id BIGINT(11) NOT NULL,
    PRIMARY KEY (cart_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE carts_products
(
    cart_id    BIGINT(11) NOT NULL,
    product_id BIGINT(11) NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES carts (cart_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (product_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE orders
(
    order_id BIGINT(11) NOT NULL AUTO_INCREMENT,
    user_id BIGINT(11) NOT NULL,
    PRIMARY KEY (order_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE order_products
(
    order_id   BIGINT(11) NOT NULL,
    product_id BIGINT(11) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (order_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (product_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
