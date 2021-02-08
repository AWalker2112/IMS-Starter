package com.qa.ims.persistence.dao;

import static org.junit.Assert.*;


import org.junit.Before;

import org.junit.Test;

import com.qa.ims.persistence.domain.OrderItems;
import com.qa.ims.utils.DBUtils;

public class OrderItemsDAOTest {

	private final OrderItemsDAO Dao = new OrderItemsDAO();

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void readAllTest() {
		assertEquals(null, Dao.readAll());
	}

	@Test
	public void readTest() {

		final long ID = 1l;

		assertEquals(new OrderItems(ID, 2l, 4l, 3l), Dao.read(ID));

	}
	
	@Test
	public void readTestException() {

		final long ID = -1l;

		assertEquals(null, Dao.read(ID));

	}

	@Test
	public void readLatestTest() {

		assertEquals(new OrderItems(1l, 2l, 4l, 3l), Dao.readLatest());
	}

	@Test
	public void createTest() {
		final OrderItems createdExpected = new OrderItems(2l, 2l, 4l, 3l);
		final OrderItems created = new OrderItems(2l, 4l, 3l);
		assertEquals(createdExpected, Dao.create(created));
	}
	
	@Test
	public void createTestException() {
		
		final OrderItems created = new OrderItems(2l, 500l, 3l);
		assertEquals(null, Dao.create(created));
	}

	@Test
	public void updateTest() {
		final OrderItems updatedExpected = new OrderItems(1l, 2l, 4l, 6l);
		final OrderItems updated = new OrderItems(1l, 2l, 4l, 6l);

		assertEquals(updatedExpected, Dao.update(updated));
	}
	
	@Test
	public void updateTestException() {
		
		final OrderItems updated = new OrderItems(1l, 500l, 4l, 6l);

		assertEquals(null, Dao.update(updated));
	}

	@Test
	public void deleteTest() {
		assertEquals(1, Dao.delete(1));
	}
	

	@Test
	public void deleteOrderItemTest() {

		assertEquals(1, Dao.deleteOrderItem(2l, 4l));
	}

}
