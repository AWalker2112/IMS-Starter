package com.qa.ims.persistence.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOFailTest {

	private final OrderDAO orderDAO = new OrderDAO();

	@Before
	public void setup() {
		DBUtils.connect("Fail");
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void readAllTest() {

		assertEquals(new ArrayList<>(), orderDAO.readAll());

	}

	@Test
	public void readLatestTest() {

		assertNull( orderDAO.readLatest());

	}
	
	@Test
	public void readIDTest() {

		final long ID = orderDAO.readID();
		assertEquals(0l, ID);
	}
	
	@Test
	public void readAllFormatedSingleOrderTest() {
		final long userInputId = 2l;
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(2l, 3l, "joe", "fred", 4l, "icontroller", 99.99, 3l));

		assertEquals(new ArrayList<>(), orderDAO.readAllFormatedSingleOrder(userInputId));
	}
	
	@Test
	public void testCreateException() {
		final Order created = new Order(3l, 200L);

		assertEquals(null, orderDAO.create(created));

	}
	
	@Test
	public void deleteTestExcetion() {
		
		assertEquals(0, orderDAO.delete(2));
			
	}
	
	@Test
	public void deleteOrderItemsTest() {

		assertEquals(0, orderDAO.deleteOrderItems(2));
	}
	
	@Test
	public void orderCostTest() {

		assertEquals(0, orderDAO.orderCost(2l), 0);
	}
}
