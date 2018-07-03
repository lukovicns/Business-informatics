package com.project.Businessinformatics.service.impl;

import java.util.Collection;

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
		return userRepository.findUserByEmail(email);
	}
	
	@Override
	public User getUser(Long userId) {
		return userRepository.getOne(userId);
	}

	@Override
	public Collection<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}
}
