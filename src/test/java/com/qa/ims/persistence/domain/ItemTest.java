package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ItemTest {
	
	private Item item1 = new Item("PS4", 399.99);
	private Item item2 = new Item(4l,"Apple Watch", 799.99);
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Item.class).verify();
	}

	
	@Test
	public void getItemIDTest() {
		
		assertEquals(4l,item2.getItemID(),0);
		
	}
	
	@Test
	public void setItemIDTest() {
		
		item2.setItemID(5l);
		assertEquals(5l,item2.getItemID(),0);
		
	}
	
	@Test
	public void getItemNameTest() {
		
		assertEquals("PS4",item1.getItemName());
		
	}
	
	@Test
	public void setItemNameTest() {
		
		item1.setItemName("Whiteboard");
		
		assertEquals("Whiteboard",item1.getItemName());
		
	}
	
	@Test
	public void getValueTest() {
		
		assertEquals(399.99,item1.getValue(),0);
		
	}
	
	@Test
	public void setValueTest() {
		
		item1.setValue(4.50);
		
		assertEquals(4.50,item1.getValue(),0);
		
	}
	
	@Test
	public void toStringTest() {
		
		System.out.println(item2.toString());
		assertEquals("itemID:4, itemName:Apple Watch, value:799.99", item2.toString());
	}
	
	

}
