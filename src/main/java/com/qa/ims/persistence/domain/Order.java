package com.qa.ims.persistence.domain;

public class Order {

	private Long orderID;
	private Long fkCustomerID;

	//For all order details
	
	private String firstName;
	private String surname;
	private Long fkItemID;
	private String itemName;
	private Double value;
	private Long quantity;

	public Order(Long fkCustomerID) {

		this.fkCustomerID = fkCustomerID;
	}

	public Order(Long orderID, Long fkCustomerID) {

		this.orderID = orderID;
		this.fkCustomerID = fkCustomerID;
	}
	
	

	public Order(Long orderID, Long fkCustomerID, String firstName, String surname, Long fkItemID, String itemName,
			Double value, Long quantity) {
		super();
		this.orderID = orderID;
		this.fkCustomerID = fkCustomerID;
		this.firstName = firstName;
		this.surname = surname;
		this.fkItemID = fkItemID;
		this.itemName = itemName;
		this.value = value;
		this.quantity = quantity;
	}
	
	

	@Override
	public String toString() {
		return "orderID=" + orderID + ", fkCustomerID=" + fkCustomerID + ", firstName=" + firstName
				+ ", surname=" + surname + ", fkItemID=" + fkItemID + ", itemName=" + itemName + ", value=" + value
				+ ", quantity=" + quantity ;
	}
	
	

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public Long getFkCustomerID() {
		return fkCustomerID;
	}

	public void setFkCustomerID(Long fkCustomerID) {
		this.fkCustomerID = fkCustomerID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fkCustomerID == null) ? 0 : fkCustomerID.hashCode());
		result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (fkCustomerID == null) {
			if (other.fkCustomerID != null)
				return false;
		} else if (!fkCustomerID.equals(other.fkCustomerID))
			return false;
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		return true;
	}

}
