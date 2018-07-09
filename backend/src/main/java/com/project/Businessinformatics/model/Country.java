package com.project.Businessinformatics.model;

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

@Table(name="country")
@Entity
public class Country {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="country_id", updatable = false, nullable = false, insertable=false)
	private Long id;

	@Column(name="name", columnDefinition="VARCHAR(50)", nullable=false)
	private String name;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, targetEntity = City.class, mappedBy="country")
	private Set<City> cities;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, targetEntity = Bank.class, mappedBy="country")
	private Set<Bank> banks;

	public Country() {
		
	}

	public Country(String name) {
		super();
		this.name = name;
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

	@JsonIgnore
	public Set<City> getCities() {
		return cities;
	}
	
	@JsonProperty
	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

	@JsonIgnore
	public Set<Bank> getBanks() {
		return banks;
	}

	@JsonProperty
	public void setBanks(Set<Bank> banks) {
		this.banks = banks;
	}
}
