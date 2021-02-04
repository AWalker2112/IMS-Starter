package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderItemsDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItems;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private OrderItemsDAO orditeDAO = new OrderItemsDAO();
	private Utils utils = new Utils();

	private OrderItemsController orderitems = new OrderItemsController(orditeDAO, utils);

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	@Override
	public List<Order> readAll() {

		while (true) {
			LOGGER.info("Please choose (All/Single/Return)\nALL: All items in all orders\nSINGLE: Specific order\nRETURN: Return to the menu before");
			String choice = utils.getString();
			

			if (choice.toLowerCase().equals("all")) {
				List<Order> orders = orderDAO.readAllFormated();
				for (Order order : orders) {
					LOGGER.info(order);
				}
				
				continue;
			} else if (choice.toLowerCase().equals("single")) {
				LOGGER.info("Please enter a order ID");
				Long fkOrderID = utils.getLong();
				List<Order> orders = orderDAO.readAllFormatedSingleOrder(fkOrderID);
				for (Order order : orders) {
					LOGGER.info(order);
					
				}
				LOGGER.info("The total cost for this order is: £" + orderDAO.orderCost(fkOrderID));
				
				continue;
			} else if (choice.toLowerCase().equals("return")) {
				List<Order> orders = orderDAO.readAllFormated();
				return orders;
			}
			
			else {
				LOGGER.info("Incorrect statement");
				continue;
			}

		}

	}

	@Override
	public Order create() {

		LOGGER.info("Please enter a customer ID");
		Long fkCustomerID = utils.getLong();
		Order order = orderDAO.create(new Order(fkCustomerID));

		LOGGER.info("Order created");

		while (true) {
			LOGGER.info("Would you like to add an item? (Y/N)");
			String yesNo = utils.getString();

			if (yesNo.equals("N")) {
				return order;
			} else if (yesNo.equals("Y")) {

				orderitems.create();

				continue;
			} else {
				LOGGER.info("Incorrect statement");
				continue;
			}
		}

	}

	@Override
	public Order update() {
		// TODO Auto-generated method stub

		while (true) {
			LOGGER.info("Would you like to add or remove an item? (Add/Remove/Return) \nADD: Add an item to a order \nREMOVE: Remove an item from a order \nRETURN: Return to the menu before");
			String addRemove = utils.getString();

			if (addRemove.toLowerCase().equals("return")) {
				return null;

			} else if (addRemove.toLowerCase().equals("add")) {

				orderitems.update();

				continue;
			} else if (addRemove.toLowerCase().equals("remove")) {

				orderitems.delete();
			} else {
				LOGGER.info("Incorrect statement");
				continue;
			}
		}

	}

	@Override
	public int delete() {

		LOGGER.info("Please enter the id of the order you would like to delete");
		Long orderID = utils.getLong();
		orderDAO.deleteOrderItems(orderID);
		return orderDAO.delete(orderID);
	}

}
