package com.qa.ims.persistence.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderItemsTest {
	
	
	private OrderItems orderItem1 = new OrderItems (1l , 1l);
	private OrderItems orderItem2 = new OrderItems (2l , 2l, 2l);
	private OrderItems orderItem3 = new OrderItems (3l , 3l, 3l , 3l);
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(OrderItems.class).verify();
	}
	
	@Test
	public void toStringTest() {
		
		assertEquals("orderItemsID=3, fkOrderID=3, fkItemID=3, quantity=3", orderItem3.toString());
	}
	
	@Test
	public void getOrderItemsIDTest() {
		assertEquals(3l,orderItem3.getOrderItemsID(),0);
	}
	
	@Test
	public void setOrderItemsIDTest() {
		orderItem3.setOrderItemsID(6l);
		assertEquals(6l,orderItem3.getOrderItemsID(),0);
		
	}
	
	@Test
	public void getFkOrderIDTest() {
		
		assertEquals(2l,orderItem2.getFkOrderID(),0);
	}
	@Test
	public void setFkOrderIDTest() {
		orderItem3.setFkOrderID(9l);
		System.out.println(orderItem3.getFkOrderID());
		assertEquals(9l,orderItem3.getFkOrderID(),0);
	}
	
	@Test
	public void getFkItemIDTest() {
		
		
		assertEquals(1l,orderItem1.getFkItemID(),0);
	}
	@Test
	public void setFkItemIDTest() {
		orderItem1.setFkItemID(55l);
		assertEquals(55l,orderItem1.getFkItemID(),0);
	}
	@Test
	public void getQuantityTest() {
		assertEquals(1l,orderItem1.getQuantity(),0);
	}
	@Test
	public void setQuantityTest() {
		orderItem1.setQuantity(205l);
		assertEquals(205l,orderItem1.getQuantity(),0);
	}
	
	

}
