drop schema `ims`;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `customers` (
    `customer_id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) NOT NULL,
    `surname` VARCHAR(40) NOT NULL,
    PRIMARY KEY (`customer_id`)
);

CREATE TABLE IF NOT EXISTS `items` (
    `item_id` INT(11) NOT NULL AUTO_INCREMENT,
    `item_name` VARCHAR(40) NOT NULL,
    `item_value` DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (`item_id`)
);

CREATE TABLE IF NOT EXISTS `orders` (
    `order_id` INT(11) NOT NULL AUTO_INCREMENT,
    
    `fk_customer_id` INT(11) NOT NULL,
    PRIMARY KEY (`order_id`),
    FOREIGN KEY (`fk_customer_id`) REFERENCES `customers`(`customer_id`)
);


CREATE TABLE IF NOT EXISTS `orders_items` (
    `order_items_id` INT(11) NOT NULL AUTO_INCREMENT,
    `fk_order_id` INT(11) NOT NULL,
    `fk_item_id` INT(11) NOT NULL,
    `quantity` INT NOT NULL,
    PRIMARY KEY (`order_items_id`),
    FOREIGN KEY (`fk_order_id`) REFERENCES `orders`(`order_id`),
    FOREIGN KEY (`fk_item_id`) REFERENCES `items`(`item_id`)
);