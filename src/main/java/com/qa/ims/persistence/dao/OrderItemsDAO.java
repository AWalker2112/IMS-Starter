package com.qa.ims.persistence.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.qa.ims.persistence.domain.OrderItems;
import com.qa.ims.utils.DBUtils;

public class OrderItemsDAO implements Dao<OrderItems> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public OrderItems modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderItemID = resultSet.getLong("order_items_id");
		Long quantity = resultSet.getLong("quantity");
		Long fkOrderId = resultSet.getLong("fk_order_id");
		Long fkItemId = resultSet.getLong("fk_item_id");

		return new OrderItems(orderItemID, fkOrderId, fkItemId, quantity);
	}

	@Override
	public List<OrderItems> readAll() {
		
		return new ArrayList<>();
	}

	@Override
	public OrderItems read(Long orderItemsID) {
		

		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM orders_items WHERE order_items_id = ?");) {
			statement.setLong(1, orderItemsID);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}


	
	public OrderItems readLatest() {
		
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM orders_items ORDER BY order_items_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	

	/**
	 * Creates a orderItem in the database
	 * 
	 * @param orderItem - takes in a orderItem object. orderItemid will be ignored
	 */

	@Override
	public OrderItems create(OrderItems orderItem) {
		
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO orders_items(fk_order_id, fk_item_id, quantity) VALUES (?, ?, ?)");) {
			statement.setLong(1, orderItem.getFkOrderID());
			statement.setLong(2, orderItem.getFkItemID());
			statement.setLong(3, orderItem.getQuantity());
			statement.executeUpdate();
			return readLatest();

		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a customer in the database
	 * 
	 * @param customer - takes in a customer object, the id field will be used to
	 *                 update that customer in the database
	 * @return
	 */

	@Override
	public OrderItems update(OrderItems orderItem) {
		
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"UPDATE orders_items SET fk_order_id = ?, fk_item_id = ?,quantity = ? WHERE order_items_id = ?");) {
			statement.setLong(1, orderItem.getFkOrderID());
			statement.setLong(2, orderItem.getFkItemID());
			statement.setLong(3, orderItem.getQuantity());
			statement.setLong(4, orderItem.getOrderItemsID());
			statement.executeUpdate();
			return read(orderItem.getOrderItemsID());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes a orderItem in the database
	 * 
	 * @param orderItemid - orderItemid of the orderItem
	 */

	@Override
	public int delete(long orderItemsID) {
		
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM orders_items WHERE order_items_id = ?");) {
			statement.setLong(1, orderItemsID);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	public int deleteOrderItem(long orderID, long itemID) {
		
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM orders_items WHERE fk_order_id = ? AND fk_item_id = ?");) {
			statement.setLong(1, orderID);
			statement.setLong(2, itemID);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
