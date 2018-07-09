package com.project.Businessinformatics.model.user;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.Businessinformatics.model.Account;

@Table(name = "client")
@Entity
public class Client {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="client_id", updatable = false, nullable = false, insertable=false)
	private Long id;

	@Column(name="name", columnDefinition="VARCHAR(50)", nullable=false)
	private String name;

	@Column(name="surname", columnDefinition="VARCHAR(50)", nullable=false)
	private String surname;

	@Column(name="email", columnDefinition="VARCHAR(100)", nullable=false, unique = true)
	private String email;

	@Column(name="password", columnDefinition="VARCHAR(100)", nullable=false)
	private String password;
	
	@Column(name="address", columnDefinition="VARCHAR(50)", nullable=false)
	private String address;
	
	@Column(name="date_of_birth", columnDefinition="VARCHAR(50)", nullable=false)
	private String dateOfBirth;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade=CascadeType.ALL, orphanRemoval = true)
	private Set<Account> accounts;
	
	public Client() {
		
	}

	public Client(String name, String surname, String email, String password, String address,
			String dateOfBirth) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
	}
	
	@JsonIgnore
	public Set<Account> getAccounts() {
		return accounts;
	}
	
	@JsonProperty
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}
