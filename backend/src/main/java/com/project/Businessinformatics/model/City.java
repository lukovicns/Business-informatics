package com.project.Businessinformatics.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "city")
public class City {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="city_id", updatable = false, nullable = false, insertable=false)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="country_id")
	private Country country;
	
	@Column(name="name", columnDefinition="VARCHAR(50)", nullable=false)
	private String name;
	
	@Column(name="ptt_number", columnDefinition="VARCHAR(50)", nullable=false, unique = true)
	private String pttNumber;
	
	@OneToMany(mappedBy = "placeOfAcceptance", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<AnalyticalStatement> analyticalStatements = new HashSet<>();
	
	@JsonIgnore
	public Set<AnalyticalStatement> getAnalyticalStatements(){
		return analyticalStatements;
	}

	@JsonProperty
	public void setAnalyticalStatements(Set<AnalyticalStatement> analyticalStatements){
		this.analyticalStatements = analyticalStatements;
	}
	
	public City() {
		
	}

	public City(String name, String pttNumber, Country country) {
		super();
		this.name = name;
		this.pttNumber = pttNumber;
		this.country = country;
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

	public String getPttNumber() {
		return pttNumber;
	}

	public void setPttNumber(String pttNumber) {
		this.pttNumber = pttNumber;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	
	
}
