package com.qa.ims.persistence.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.utils.DBUtils;

public class OrderItemsDAOFailTest {

	private final OrderItemsDAO Dao = new OrderItemsDAO();

	@Before
	public void setup() {
		DBUtils.connect("Fail");
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void readLatestTest() {

		assertNull(Dao.readLatest());
	}

	@Test
	public void deleteTest() {
		assertEquals(0, Dao.delete(1));
	}

	@Test
	public void deleteOrderItemTest() {

		assertEquals(0, Dao.deleteOrderItem(2l, 4l));
	}
}
