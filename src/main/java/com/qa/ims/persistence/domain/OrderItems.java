package com.qa.ims.persistence.domain;

public class OrderItems {

	private Long orderItemsID;
	private Long fkOrderID;
	private Long fkItemID;
	private Long quantity;

	public OrderItems(Long fkOrderID, Long fkItemID, Long quantity) {

		this.fkOrderID = fkOrderID;
		this.fkItemID = fkItemID;
		this.quantity = quantity;
	}

	public OrderItems(Long orderItemsID, Long fkOrderID, Long fkItemID, Long quantity) {

		this.orderItemsID = orderItemsID;
		this.fkOrderID = fkOrderID;
		this.fkItemID = fkItemID;
		this.quantity = quantity;
	}

	public OrderItems(Long fkItemID, Long quantity) {
		super();
		this.fkItemID = fkItemID;
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "orderItemsID=" + orderItemsID + ", fkOrderID=" + fkOrderID + ", fkItemID=" + fkItemID + ", quantity="
				+ quantity;

	}

	public Long getOrderItemsID() {
		return orderItemsID;
	}

	public void setOrderItemsID(Long orderItemsID) {
		this.orderItemsID = orderItemsID;
	}

	public Long getFkOrderID() {
		return fkOrderID;
	}

	public void setFkOrderID(Long fkOrderID) {
		this.fkOrderID = fkOrderID;
	}

	public Long getFkItemID() {
		return fkItemID;
	}

	public void setFkItemID(Long fkItemID) {
		this.fkItemID = fkItemID;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fkItemID == null) ? 0 : fkItemID.hashCode());
		result = prime * result + ((fkOrderID == null) ? 0 : fkOrderID.hashCode());
		result = prime * result + ((orderItemsID == null) ? 0 : orderItemsID.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
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
		OrderItems other = (OrderItems) obj;
		if (fkItemID == null) {
			if (other.fkItemID != null)
				return false;
		} else if (!fkItemID.equals(other.fkItemID))
			return false;
		if (fkOrderID == null) {
			if (other.fkOrderID != null)
				return false;
		} else if (!fkOrderID.equals(other.fkOrderID))
			return false;
		if (orderItemsID == null) {
			if (other.orderItemsID != null)
				return false;
		} else if (!orderItemsID.equals(other.orderItemsID))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}

}
