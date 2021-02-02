DROP TABLE IF EXISTS `customers`;

CREATE TABLE IF NOT EXISTS `customers` (
    `customer_id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`customer_id`)
);

DROP TABLE IF EXISTS `items`;

CREATE TABLE IF NOT EXISTS `items` (
    `item_id` INT(11) NOT NULL AUTO_INCREMENT,
    `item_name` VARCHAR(40) DEFAULT NULL,
    `item_value` DECIMAL(10,2) DEFAULT NULL,
    PRIMARY KEY (`item_id`)
);