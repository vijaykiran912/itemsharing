package com.itemsharing.itemservice.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itemsharing.itemservice.model.Item;
import com.itemsharing.itemservice.model.User;
import com.itemsharing.itemservice.repository.ItemRepository;
import com.itemsharing.itemservice.service.ItemService;
import com.itemsharing.itemservice.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
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
		return (List<Item>) itemRepository.findAll();
	}

	@Override
	public List<Item> getItemsByUsername(String username) {
		User user = userService.getUserByUsername(username);
		return (List<Item>) itemRepository.findByUser(user);
	}

	@Override
	public Item getItemById(Long id) {
		return itemRepository.findOne(id);
	}

	@Override
	public Item updateItem(Item item) throws IOException {

		Item localItem = getItemById(item.getId());

		if (localItem == null) {
			throw new IOException("Item was not found");
		} else {

			localItem.setName(item.getName());
			localItem.setItemCondition(item.getItemCondition());
			localItem.setStatus(item.getStatus());
			localItem.setDescription(item.getDescription());
			return itemRepository.save(localItem);

		}

	}

	@Override
	public void deleteItemById(Long id) {
		itemRepository.delete(id);

	}

	@Override
	public User getUserByUsername(String username) {
		return userService.getUserByUsername(username);
	}

}
