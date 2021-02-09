package com.qa.ims.persistence.dao;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {

	private final OrderDAO orderDAO = new OrderDAO();

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testUpdate() {

		final Order updated = new Order(3l, 2L);
		assertEquals(null, orderDAO.update(updated));

	}

	@Test
	public void readAllTest() {

		List<Order> expected = new ArrayList<>();
		expected.add(new Order(2l, 3l, "joe", "fred", 4l, "icontroller", 99.99, 3l));

		assertEquals(expected, orderDAO.readAll());

	}

	@Test
	public void readLatestTest() {

		assertEquals(new Order(2l, 3l, null, null, null, null, null, null), orderDAO.readLatest());

	}

	@Test
	public void readIDTest() {

		final long ID = orderDAO.readID();
		assertEquals(2l, ID);
	}

	@Test
	public void readAllFormatedSingleOrderTest() {
		final long userInputId = 2l;
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(2l, 3l, "joe", "fred", 4l, "icontroller", 99.99, 3l));

		assertEquals(expected, orderDAO.readAllFormatedSingleOrder(userInputId));
	}
	
	
	
	
	@Test
	public void updateTest() {
		final Order updated = new Order(3l, 2L);
		assertEquals(null, orderDAO.update(updated));

	}

	@Test
	public void testCreate() {
		final Order created = new Order(3l, 2L);

		assertEquals(created, orderDAO.create(created));

	}
	
	@Test
	public void testCreateException() {
		final Order created = new Order(3l, 200L);

		assertEquals(null, orderDAO.create(created));

	}

	@Test
	public void deleteTest() {
		assertEquals(1, orderDAO.delete(1));
	}
	
	@Test
	public void deleteTestExcetion() {
		
		assertEquals(0, orderDAO.delete(2));
	}

	@Test
	public void deleteOrderItemsTest() {

		assertEquals(1, orderDAO.deleteOrderItems(2));
	}
	
	

	@Test
	public void orderCostTest() {

		assertEquals(299.97, orderDAO.orderCost(2l), 0);
	}
	
	
	@Test
	public void readTest() {
		

		assertEquals(null, orderDAO.read(1l));
	}
	

}
