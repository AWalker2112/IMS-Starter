package com.qa.ims.persistence.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderTest {
	
	private Order order1 = new Order (9l);
	private Order order2 = new Order (5l, 2l);
	private Order order3 = new Order (7l, 2l, "Alex", "Walker", 2l, "Iphone", 99.99, 99l);
	private Order order4 = new Order (9l,9l);
	
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Order.class).verify();
	}
	
	@Test
	public void toStringTest() {
		

		assertEquals("Order ID = 7, Customer ID = 2, First Name = Alex, Surname = Walker, Item ID = 2, Item Name = Iphone, Item Value = 99.99, Quantity = 99" , order3.toString());
		
		
	}
	
	@Test
	public void getOrderIDTest() {
		
		assertEquals(9l,order4.getOrderID(),0);
		
	}
	
	@Test
	public void setOrderIDTest() {
		
		order1.setOrderID(7l);
		assertEquals(7l,order1.getOrderID(),0);
	}
	
	@Test
	public void getFkCustomerIDTest() {
		assertEquals(2l,order2.getFkCustomerID(),0);
	}
	
	@Test
	public void setFkCustomerIDTest() {
		
		order2.setFkCustomerID(88l);
		assertEquals(88l,order2.getFkCustomerID(),0);
	}

}
