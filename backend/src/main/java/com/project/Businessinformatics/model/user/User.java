package com.project.Businessinformatics.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="user_id", updatable = false, nullable = false, insertable=false)
	private Long id;

	@Column(name="name", columnDefinition="VARCHAR(50)", nullable=false)
	private String name;

	@Column(name="surname", columnDefinition="VARCHAR(50)", nullable=false)
	private String surname;

	@Column(name="email", columnDefinition="VARCHAR(100)", nullable=false, unique = true)
	private String email;

	@Column(name="password", columnDefinition="VARCHAR(100)", nullable=false)
	private String password;
	
	public User() {
		
	}

	public User(String name, String surname, String email, String password) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
