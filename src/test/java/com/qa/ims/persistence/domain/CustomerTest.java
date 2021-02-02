package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerTest {
	
	private Customer customer1 = new Customer("Alex", "Walker");
	private Customer customer2 = new Customer(3l, "James", "Leek");
	
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}

	
	@Test
	public void getIDTest() {
		
		assertEquals(3l,customer2.getId(),0);
		
	}
	
	@Test
	public void setIDTest() {
		
		customer2.setId(4l);
		assertEquals(4l,customer2.getId(),0);
		
	}
	
	@Test
	public void getFirstNameTest() {
		
		assertEquals("Alex",customer1.getFirstName());
		
	}
	
	@Test
	public void setFirstNameTest() {
		
		customer1.setFirstName("Ben");
		
		assertEquals("Ben",customer1.getFirstName());
		
	}
	
	@Test
	public void getSurnameTest() {
		
		assertEquals("Walker",customer1.getSurname());
		
	}
	
	@Test
	public void setSurnameTest() {
		
		customer1.setSurname("Smith");
		
		assertEquals("Smith",customer1.getSurname());
		
	}
	
	@Test
	public void toStringTest() {
		
		System.out.println(customer2.toString());
		assertEquals("id:3 first name:James surname:Leek", customer2.toString());
	}
	
}
