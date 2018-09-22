package com.itemsharing.userservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itemsharing.userservice.model.User;
import com.itemsharing.userservice.repository.UserRepository;
import com.itemsharing.userservice.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserByUsername(String username) {
		log.info("Inside getUserByUsername");
		return userRepository.findByUsername(username);
	}

}
