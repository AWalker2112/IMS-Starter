package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;




import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTest {
	
	private final ItemDAO DAO = new ItemDAO();
	
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		final Item created = new Item(5L, "ipad", 599.99);
		assertEquals(created, DAO.create(created));
		
		
	}
	
	@Test
	public void testCreateException() {
		final Item created = new Item(5L, "ipad", 100000000599.99);
		
		assertEquals(null, DAO.create(created));
		
		
	}

	
	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L, "iphone", 399.99));
		expected.add(new Item(2L, "ipad", 799.99));
		expected.add(new Item(3L, "imac", 1399.99));
		expected.add(new Item(4L, "icontroller", 99.99));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Item(4L, "icontroller", 99.99), DAO.readLatest());
	}
	
	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Item(ID, "iphone", 399.99), DAO.read(ID));
	}

	@Test
	public void testReadException() {
		final long ID = 100L;
		assertEquals(null, DAO.read(ID));
	}
	
	@Test
	public void testUpdate() {
		final Item updated = new Item(1L, "mug", 50.99);
		assertEquals(updated, DAO.update(updated));

	}
	
	@Test
	public void testUpdateException() {
		final Item updated = new Item(1L, "mug", 100000000050.99);
		assertEquals(null, DAO.update(updated));

	}
	
	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
	
	@Test
	public void testDeleteException() {
		assertEquals(0, DAO.delete(4));
	}
	
}
