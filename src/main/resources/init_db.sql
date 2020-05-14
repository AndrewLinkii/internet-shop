CREATE SCHEMA `internet-shop` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `internet-shop`.`products` (
`product_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(225) NOT NULL,
`price` DECIMAL (11) NOT NULL,
PRIMARY KEY (`product_id`));

INSERT INTO products  (name , price)
VALUES  ('Phone', 224.6),
        ('Book', 76.7),
        ('LapTop', 666.6),
        ('Wallet', 346.6);
