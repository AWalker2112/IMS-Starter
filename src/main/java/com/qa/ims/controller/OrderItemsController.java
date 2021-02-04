package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderItemsDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItems;
import com.qa.ims.utils.Utils;

public class OrderItemsController implements CrudController<OrderItems> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO = new OrderDAO();
	private OrderItemsDAO orderItemDAO = new OrderItemsDAO();;
	private Utils utils = new Utils();;

	public OrderItemsController(OrderItemsDAO orderItemsDAO, Utils utils) {
		super();
		this.orderItemDAO = orderItemsDAO;
		this.utils = utils;
	}

	public OrderItemsController(OrderDAO orderDAO, OrderItemsDAO orderItemDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.orderItemDAO = orderItemDAO;
		this.utils = utils;
	}

	@Override
	public List<OrderItems> readAll() {

		List<OrderItems> ordersItems = orderItemDAO.readAll();
		for (OrderItems orderItem : ordersItems) {
			LOGGER.info(orderItem);
		}
		return ordersItems;

	}

	/**
	 * Creates a OrderItem by taking in user input
	 */

	@Override
	public OrderItems create() {
		// TODO Auto-generated method stub

		Long fkOrderID = orderDAO.readID();

		LOGGER.info("Please enter a item ID");
		Long fkItemID = utils.getLong();

		LOGGER.info("Please enter a quantity");
		Long quantity = utils.getLong();

		OrderItems orderitem = orderItemDAO.create(new OrderItems(fkOrderID, fkItemID, quantity));

		LOGGER.info("OrderItem created");

		LOGGER.info("The total cost for this order is: £" + orderDAO.orderCost(fkOrderID));
		return orderitem;

	}

	/**
	 * Updates an existing OrderItem by taking in user input
	 */

	@Override
	public OrderItems update() {
		// TODO Auto-generated method stub
		
		LOGGER.info("Please enter a order ID");
		Long fkOrderID = utils.getLong();

		LOGGER.info("Please enter a item ID");
		Long fkItemID = utils.getLong();

		LOGGER.info("Please enter a quantity");
		Long quantity = utils.getLong();

		OrderItems orderitem = orderItemDAO.create(new OrderItems(fkOrderID, fkItemID, quantity));

		LOGGER.info("OrderItem created");

		LOGGER.info("The total cost for this order is: £" + orderDAO.orderCost(fkOrderID));
		return orderitem;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long orderID = utils.getLong();
		
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long itemsID = utils.getLong();
		
		return orderItemDAO.deleteOrderItem(orderID, itemsID);
	}

}
