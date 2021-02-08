INSERT INTO `customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');

INSERT INTO `items` (`item_name`, `item_value`) VALUES ('iphone', '399.99');


INSERT INTO `customers` (`first_name`, `surname`) VALUES ('alex', 'walker');
INSERT INTO `customers` (`first_name`, `surname`) VALUES ('joe', 'fred');
INSERT INTO `orders` ( `fk_customer_id`) VALUES ('2');
INSERT INTO `orders` ( `fk_customer_id`) VALUES ('3');

INSERT INTO `customers` (`first_name`, `surname`) VALUES ('james', 'peters');
INSERT INTO `items` (`item_name`, `item_value`) VALUES ('ipad', '799.99');
INSERT INTO `items` (`item_name`, `item_value`) VALUES ('imac', '1399.99');
INSERT INTO `items` (`item_name`, `item_value`) VALUES ('icontroller', '99.99');
INSERT INTO `orders_items` ( `fk_order_id`,`fk_item_id`,`quantity`) VALUES ('2','4','3');
