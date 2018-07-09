package com.project.Businessinformatics.service;

import java.util.List;

import com.project.Businessinformatics.model.user.User;

public interface UserService {

	User createUser(User user);
	User getUserByEmail(String email);
	User getUser(Long id);
	List<User> getAllUsers();
	void deleteUser(Long userId);
	User findByIdAndEmail(Long id, String email);
	User getUserByEmailAndPassword(String email, String password);
}
