package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderItemsDAO;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItems;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private Utils utils = new Utils();;

	@Mock
	private OrderDAO orderDAO;

	@Mock
	private OrderItemsDAO orderItemDAO;

	@InjectMocks
	private OrderController controller;

	@Test
	public void readAllTest() {

		final String All = "ALL", SINGLE = "SINGLE", RETURN = "RETURN", MISTAKE = "AJS654";
		final Long orderID = 2l;
		List<Order> orderRead = new ArrayList<>();
		orderRead.add(new Order(1l, 1l, "Alex", "Walker", 3l, "TestItem", 123.33, 3l));
		orderRead.add(new Order(2l, 2l, "John", "Smith", 4l, "TestItem", 987.65, 9l));

		List<Order> orderReadSingle = new ArrayList<>();
		orderReadSingle.add(new Order(2l, 2l, "John", "Smith", 4l, "TestItem", 987.65, 9l));
		orderReadSingle.add(new Order(2l, 2l, "John", "Smith", 4l, "TestItem2", 50.66, 2l));

		Mockito.when(utils.getLong()).thenReturn(orderID);
		Mockito.when(utils.getString()).thenReturn(All, MISTAKE, SINGLE, RETURN);
		Mockito.when(orderDAO.readAll()).thenReturn(orderRead);
		Mockito.when(orderDAO.readAllFormatedSingleOrder(orderID)).thenReturn(orderReadSingle);

		assertEquals(orderRead, controller.readAll());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(utils, Mockito.times(4)).getString();
		Mockito.verify(orderDAO, Mockito.times(2)).readAll();
		Mockito.verify(orderDAO, Mockito.times(1)).readAllFormatedSingleOrder(orderID);

	}

	@Test
	public void createTest() {

		final Long orderID = 2l, itemID = 3l, quantity = 3l;
		final Order createdOrder = new Order(2l);
		final Order createdOrderItemExit = new Order(1l, 1l, null, null, null, null, null, null);
	
		final String Y = "Y", N = "N", MISTAKE = "AJS654";

		Mockito.when(utils.getLong()).thenReturn(orderID, itemID, quantity, itemID, quantity);
		Mockito.when(orderDAO.create(createdOrder)).thenReturn(createdOrderItemExit);
		Mockito.when(utils.getString()).thenReturn(MISTAKE, Y, N);

		assertEquals(createdOrderItemExit, controller.create());

		Mockito.verify(utils, Mockito.times(5)).getLong();
		Mockito.verify(utils, Mockito.times(3)).getString();
		Mockito.verify(orderDAO, Mockito.times(1)).create(createdOrder);

	}

	@Test
	public void testCreateItem() {

		final Long FKOrderID = 1l, FKItemID = 1l, QUANTITY = 9L;
		final OrderItems orderitem = new OrderItems(FKOrderID, FKItemID, QUANTITY);

		Mockito.when(orderDAO.readID()).thenReturn(FKOrderID);

		Mockito.when(utils.getLong()).thenReturn(FKItemID, QUANTITY);
		Mockito.when(orderItemDAO.create(orderitem)).thenReturn(orderitem);

		assertEquals(orderitem, controller.createItem());

		Mockito.verify(orderDAO, Mockito.times(1)).readID();
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(orderItemDAO, Mockito.times(1)).create(orderitem);

	}

	@Test
	public void updateTest() {

		final String ADD = "ADD", REMOVE = "REMOVE", RETURN = "RETURN", MISTAKE = "AJS654";
		final Long orderID = 1l, itemID = 1l, quantity = 1l;
		Mockito.when(utils.getString()).thenReturn(MISTAKE, ADD, REMOVE, RETURN); // ADD, REMOVE,
		Mockito.when(utils.getLong()).thenReturn(orderID, itemID, quantity, orderID, itemID);

		assertEquals(null, controller.update());
		Mockito.verify(utils, Mockito.times(5)).getLong();
		Mockito.verify(utils, Mockito.times(4)).getString();

	}

	@Test
	public void updateItemTest() {

		final Long FKOrderID = 1l, FKItemID = 1l, QUANTITY = 9L;
		final OrderItems orderitem = new OrderItems(FKOrderID, FKItemID, QUANTITY);

		Mockito.when(utils.getLong()).thenReturn(FKOrderID, FKItemID, QUANTITY);
		Mockito.when(orderItemDAO.create(orderitem)).thenReturn(orderitem);

		assertEquals(orderitem, controller.updateItem());

		Mockito.verify(utils, Mockito.times(3)).getLong();
		Mockito.verify(orderItemDAO, Mockito.times(1)).create(orderitem);
	}

	@Test
	public void deleteTest() {
		final long orderID = 1L;

		Mockito.when(utils.getLong()).thenReturn(orderID, orderID);

		Mockito.when(orderDAO.deleteOrderItems(orderID)).thenReturn(1);

		System.out.println(controller.delete());

		assertEquals(0, controller.delete());

		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(orderDAO, Mockito.times(2)).deleteOrderItems(orderID);
	}

	@Test
	public void deleteItemTest() {

		final long orderID = 1L, itemID = 2l;

		Mockito.when(utils.getLong()).thenReturn(orderID, itemID);
		Mockito.when(orderItemDAO.deleteOrderItem(orderID, itemID)).thenReturn(1);

		assertEquals(1, this.controller.deleteItem());

		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(orderItemDAO, Mockito.times(1)).deleteOrderItem(orderID, itemID);

	}

}
