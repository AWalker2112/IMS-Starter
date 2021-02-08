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

import com.qa.ims.persistence.domain.Order;

import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderID = resultSet.getLong("order_ID");
		Long fkCustomerID = resultSet.getLong("fk_customer_ID");

		return new Order(orderID, fkCustomerID);
	}

	public Long modelFromResultSetID(ResultSet resultSetID) throws SQLException {
		Long orderID = resultSetID.getLong("order_ID");

		return orderID;
	}

	public Order modelFromResultSetAll(ResultSet resultSetAll) throws SQLException {
		Long orderID = resultSetAll.getLong("order_ID");
		Long fkCustomerID = resultSetAll.getLong("fk_customer_ID");
		String firstName = resultSetAll.getString("first_name");
		String surname = resultSetAll.getString("surname");
		Long fkItemID = resultSetAll.getLong("fk_item_id");
		String itemName = resultSetAll.getString("item_name");
		
		Double value = resultSetAll.getDouble("item_value");
		Long quantity = resultSetAll.getLong("quantity");

		return new Order(orderID, fkCustomerID, firstName, surname, fkItemID, itemName, value, quantity);
	}

	public Double modelFromResultSetCost(ResultSet resultSetCost) throws SQLException {
		Double cost = resultSetCost.getDouble("SUM(item_value*quantity)");

		return cost;
	}

	/**
	 * Reads all orders from the database and formats for user 
	 * 
	 * @return A list of orders formated
	 */


	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSetAll = statement.executeQuery(
						"SELECT o.order_id, o.fk_customer_id, c.first_name, c.surname,oi.fk_item_id, i.item_name, i.item_value, oi.quantity  FROM orders o JOIN orders_items oi ON o.order_id = oi.fk_order_id JOIN customers c ON o.fk_customer_id = c.customer_id JOIN items i ON oi.fk_item_id = i.item_id ORDER BY order_id;");) {
			List<Order> orders = new ArrayList<>();
			while (resultSetAll.next()) {
				orders.add(modelFromResultSetAll(resultSetAll));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	/**
	 * Reads the latest order created in orders from the database
	 * 
	 * @return A line entry
	 */

	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Reads the latest order created in orders from the database but only returns order ID
	 * 
	 * @return A line entry
	 */

	public Long readID() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSetID = statement
						.executeQuery("SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1");) {
			resultSetID.next();
			return modelFromResultSetID(resultSetID);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0l;
	}
	

	
	/**
	 * Reads a single orders from the database and formats for user 
	 * 
	 * @return A list of 1 order formated showing all items
	 */
	
	public List<Order> readAllFormatedSingleOrder(long id) {
	
			try (Connection connection = DBUtils.getInstance().getConnection();
					PreparedStatement statement = connection.prepareStatement(
							"SELECT o.order_id, o.fk_customer_id, c.first_name, c.surname,oi.fk_item_id, i.item_name, i.item_value, oi.quantity  FROM orders o JOIN orders_items oi ON o.order_id = oi.fk_order_id JOIN customers c ON o.fk_customer_id = c.customer_id JOIN items i ON oi.fk_item_id = i.item_id WHERE fk_order_id = ? ORDER BY order_id;");) {
				List<Order> orders = new ArrayList<>();
				statement.setLong(1, id);
				try (ResultSet resultSet = statement.executeQuery();) {
					while (resultSet.next()) {
						orders.add(modelFromResultSetAll(resultSet));
					}
					
					return orders;
				}
			} catch (Exception e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
			return null;
	}
	
	
	/**
	 * Creates a order in the database
	 * 
	 */

	@Override
	public Order create(Order order) {
		
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders(fk_customer_id) VALUES (?)");) {
			statement.setLong(1, order.getFkCustomerID());
			statement.executeUpdate();
			return readLatest();

		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes a order in the database
	 * 
	 */

	@Override
	public int delete(long id) {
		
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE order_id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	public int deleteOrderItems(long id) {
		
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM orders_items WHERE fk_order_id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	/**
	 * Calculates the cost of a given orderID in the database
	 * 
	 * @param orderID - takes in a orderID
	 */

	public double orderCost(long id) {

		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT SUM(item_value*quantity)  FROM orders o JOIN orders_items oi ON o.order_id = oi.fk_order_id JOIN customers c ON o.fk_customer_id = c.customer_id JOIN items i ON oi.fk_item_id = i.item_id WHERE o.order_id= (?) ;");) {
			
						
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSetCost(resultSet);
			}

		} catch (Exception e) {
			
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;

	}

	@Override
	public Order update(Order t) {
		
		return null;
	}
	
	
	@Override
	public Order read(Long id) {
		
		return null;
	}
	

}
