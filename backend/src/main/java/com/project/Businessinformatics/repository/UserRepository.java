package com.project.Businessinformatics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Businessinformatics.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findUserByEmail(String username);
	
}
