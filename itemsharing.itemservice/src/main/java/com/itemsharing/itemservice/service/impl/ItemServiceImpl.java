package com.itemsharing.itemservice.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.itemsharing.itemservice.model.Item;
import com.itemsharing.itemservice.model.User;
import com.itemsharing.itemservice.repository.ItemRepository;
import com.itemsharing.itemservice.service.ItemService;
import com.itemsharing.itemservice.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private UserService userService;

	@Override
	public Item addItemByUser(Item item, String username) {
		log.info("Inside ItemServiceImpl addItemByUser method.");

		Item localItem = itemRepository.findByName(item.getName());

		if (localItem != null) {
			log.info("Item with name {} already exists.", item.getName());
			return null;
		} else {
			Date today = new Date();
			item.setAddDate(today);

			User user = userService.getUserByUsername(username);
			item.setUser(user);
			Item newItem = itemRepository.save(item);

			return newItem;
		}
	}

	@Override
	public List<Item> getAllItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> getItemsByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getItemById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item updateItem(Item item) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteItemById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
