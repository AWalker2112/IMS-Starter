package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderItemsDAO;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItems;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private OrderItemsDAO orditeDAO = new OrderItemsDAO();
	private Utils utils = new Utils();

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	public OrderController(OrderItemsDAO orditeDAO, Utils utils) {
		super();
		this.orditeDAO = orditeDAO;
		this.utils = utils;
	}

	public OrderController(OrderDAO orderDAO, OrderItemsDAO orditeDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.orditeDAO = orditeDAO;
		this.utils = utils;
	}

	String costString = "The total cost for this order is: ";
	String incorrectString = "Incorrect statement";

	@Override
	public List<Order> readAll() {

		while (true) {
			LOGGER.info(
					"Please choose (All/Single/Return)\nALL: All items in all orders\nSINGLE: Specific order\nRETURN: Return to the menu before");
			String choice = utils.getString();

			if (choice.equalsIgnoreCase("all")) {
				List<Order> orders = orderDAO.readAll();
				for (Order order : orders) {
					LOGGER.info(order);
				}

			} else if (choice.equalsIgnoreCase("single")) {
				LOGGER.info("Please enter a order ID");
				Long fkOrderID = utils.getLong();
				List<Order> orders = orderDAO.readAllFormatedSingleOrder(fkOrderID);
				for (Order order : orders) {
					LOGGER.info(order);

				}
				LOGGER.info(costString + orderDAO.orderCost(fkOrderID));

			} else if (choice.equalsIgnoreCase("return")) {

				return orderDAO.readAll();
			}

			else {
				LOGGER.info(incorrectString);

			}

		}

	}

	/**
	 * Creates an order by taking in user input
	 */

	@Override
	public Order create() {

		LOGGER.info("Please enter a customer ID");
		Long fkCustomerID = utils.getLong();
		Order order = orderDAO.create(new Order(fkCustomerID));
		createItem();

		LOGGER.info("Order created");

		while (true) {
			LOGGER.info("Would you like to add an item? (Y/N)");
			String yesNo = utils.getString();

			if (yesNo.equalsIgnoreCase("N")) {
				return order;
			} else if (yesNo.equalsIgnoreCase("Y")) {

				createItem();

				continue;
			} else {
				LOGGER.info(incorrectString);
				continue;
			}
		}

	}

	/**
	 * Creates a item into an order by taking in user input
	 */

	public OrderItems createItem() {

		Long fkOrderID = orderDAO.readID();

		LOGGER.info("Please enter a item ID");
		Long fkItemID = utils.getLong();

		LOGGER.info("Please enter a quantity");
		Long quantity = utils.getLong();

		OrderItems orderitem = orditeDAO.create(new OrderItems(fkOrderID, fkItemID, quantity));

		LOGGER.info("Item added");

		LOGGER.info(costString + orderDAO.orderCost(fkOrderID));
		return orderitem;

	}

	/**
	 * Creates a updates an order either adding or removing an item by taking in
	 * user input
	 */

	@Override
	public Order update() {
		

		while (true) {
			LOGGER.info(
					"Would you like to add or remove an item? (Add/Remove/Return) \nADD: Add an item to a order \nREMOVE: Remove an item from a order \nRETURN: Return to the menu before");
			String addRemove = utils.getString();

			if (addRemove.equalsIgnoreCase("return")) {
				return null;

			} else if (addRemove.equalsIgnoreCase("add")) {

				updateItem();

				
			} else if (addRemove.equalsIgnoreCase("remove")) {

				deleteItem();
			} else {
				LOGGER.info(incorrectString);
				
			}
		}

	}

	/**
	 * Updates an existing Item by taking in user input
	 */

	public OrderItems updateItem() {

		LOGGER.info("Please enter a order ID");
		Long fkOrderID = utils.getLong();

		LOGGER.info("Please enter a item ID");
		Long fkItemID = utils.getLong();

		LOGGER.info("Please enter a quantity");
		Long quantity = utils.getLong();

		OrderItems orderitem = orditeDAO.create(new OrderItems(fkOrderID, fkItemID, quantity));

		LOGGER.info("Item and quantity has been added to a order");

		LOGGER.info(costString + orderDAO.orderCost(fkOrderID));
		return orderitem;
	}

	@Override
	public int delete() {

		LOGGER.info("Please enter the id of the order you would like to delete");
		Long orderID = utils.getLong();
		orderDAO.deleteOrderItems(orderID);
		return orderDAO.delete(orderID);
	}

	/**
	 * Deletes an existing item by the id of the item in the order
	 * 
	 * @return
	 */

	public int deleteItem() {

		LOGGER.info("Please enter the id of the order");
		Long orderID = utils.getLong();

		LOGGER.info("Please enter the id of the item you would like to delete");
		Long itemsID = utils.getLong();

		return orditeDAO.deleteOrderItem(orderID, itemsID);
	}

}
