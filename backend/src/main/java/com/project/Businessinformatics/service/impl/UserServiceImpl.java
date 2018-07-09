package com.project.Businessinformatics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Businessinformatics.model.user.User;
import com.project.Businessinformatics.repository.UserRepository;
import com.project.Businessinformatics.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserByEmail(String email) {
		for (User user : getAllUsers()) {
			if (user.getEmail().equals(email)) {
				return user;
			}
		}
		return null;
	}
	
	@Override
	public User getUser(Long userId) {
		return userRepository.getOne(userId);
	}

	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User findByIdAndEmail(Long id, String email) {
		User user = getUserByEmail(email);
		return user.getId() == id ? user : null;
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		User user = getUserByEmail(email);
		return user.getPassword().equals(password) ? user : null;
	}
}
