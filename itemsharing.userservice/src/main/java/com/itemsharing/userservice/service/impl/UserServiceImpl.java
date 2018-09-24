package com.itemsharing.userservice.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itemsharing.userservice.model.Role;
import com.itemsharing.userservice.model.User;
import com.itemsharing.userservice.model.UserRole;
import com.itemsharing.userservice.repository.UserRepository;
import com.itemsharing.userservice.service.UserService;
import com.itemsharing.userservice.utility.SecurityUtililty;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User getUserByUsername(String username) {
		log.info("Inside getUserByUsername");
		return userRepository.findByUsername(username);
	}

	@Override
	public User createUser(User user) {

		User localUser = userRepository.findByUsername(user.getUsername());
		if (localUser != null) {
			log.info("User with username {} already exists, Nothing to do..", user.getUsername());
		} else {
			Role role = new Role();
			role.setRoleId(1);
			Set<UserRole> userRoles = new HashSet<UserRole>();
			userRoles.add(new UserRole(user, role));

			user.getUserRoles().addAll(userRoles);

			Date today = new Date();
			user.setJoinDate(today);

			String encryptedPassword = SecurityUtililty.passwordEncoder().encode(user.getPassword());
			user.setPassword(encryptedPassword);
			localUser = userRepository.save(user);
		}

		return localUser;
	}

}
