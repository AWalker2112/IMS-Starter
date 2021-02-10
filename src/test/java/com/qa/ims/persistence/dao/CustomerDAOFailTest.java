package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.DBUtils;

public class CustomerDAOFailTest {

	private final CustomerDAO DAO = new CustomerDAO();

	@Before
	public void setup() {
		DBUtils.connect("Fail");
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testReadAll() {
		List<Customer> expected = new ArrayList<>();
		expected.add(new Customer(1L, "jordan", "harrison"));
		expected.add(new Customer(2L, "alex", "walker"));
		expected.add(new Customer(3L, "joe", "fred"));
		expected.add(new Customer(4L, "james", "peters"));

		assertEquals(new ArrayList<>(), DAO.readAll());
	}

	@Test
	public void testCreate() {
		final Customer created = new Customer(5L, "chris", "perrins");

		assertNull(DAO.create(created));

	}

	@Test
	public void testReadLatest() {
		assertNull(DAO.readLatest());
	}

}
