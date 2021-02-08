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
		return "Order ID = " + orderID + ", Customer ID = " + fkCustomerID + ", First Name = " + firstName
				+ ", Surname = " + surname + ", Item ID = " + fkItemID + ", Item Name = " + itemName + ", Item Value = " + value
				+ ", Quantity = " + quantity ;
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
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((fkCustomerID == null) ? 0 : fkCustomerID.hashCode());
		result = prime * result + ((fkItemID == null) ? 0 : fkItemID.hashCode());
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (fkCustomerID == null) {
			if (other.fkCustomerID != null)
				return false;
		} else if (!fkCustomerID.equals(other.fkCustomerID))
			return false;
		if (fkItemID == null) {
			if (other.fkItemID != null)
				return false;
		} else if (!fkItemID.equals(other.fkItemID))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	

}
