package com.qa.ims.controller;

import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

public class ItemController implements CrudController<Item>{
	
	/**
	 * Takes in customer details for CRUD functionality
	 *
	 */
	
	

		public static final Logger LOGGER = LogManager.getLogger();

		private ItemDAO itemDAO;
		private Utils utils;

		public ItemController(ItemDAO itemDAO, Utils utils) {
			super();
			this.itemDAO = itemDAO;
			this.utils = utils;
		}
		
		/**
		 * Reads all customers to the logger
		 */

		@Override
		public List<Item> readAll() {
			// TODO Auto-generated method stub
			List<Item> items = itemDAO.readAll();
			for (Item item : items) {
				LOGGER.info(item);
			}
			return items;
		}

		/**
		 * Creates a customer by taking in user input
		 */
		
		@Override
		public Item create() {
			// TODO Auto-generated method stub
			
			LOGGER.info("Please enter a item name");
			String itemName = utils.getString();
			
			LOGGER.info("Please enter a item value");
			Double value = utils.getDouble();
			
			Item item = itemDAO.create(new Item(itemName, value));
			LOGGER.info("Item created");
			return item;
		}

		@Override
		public Item update() {
			// TODO Auto-generated method stub
			LOGGER.info("Please enter the id of the item you would like to update");
			Long itemID = utils.getLong();
			LOGGER.info("Please enter a item name");
			String itemName = utils.getString();
			LOGGER.info("Please enter a item value");
			Double value = utils.getDouble();
			Item item = itemDAO.update(new Item(itemID, itemName, value));
			LOGGER.info("Item Updated");
			return item;
		}

		@Override
		public int delete() {
			// TODO Auto-generated method stub
			LOGGER.info("Please enter the id of the item you would like to delete");
			Long itemID = utils.getLong();
			return itemDAO.delete(itemID);
		}	
	
	
	

}

