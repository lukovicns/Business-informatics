package com.project.Businessinformatics.service;

import java.util.Collection;

import com.project.Businessinformatics.model.user.User;

public interface UserService {

	User createUser(User user);
	User getUserByEmail(String email);
	User getUser(Long id);
	Collection<User> getAllUsers();
	void deleteUser(Long userId);
	
}
