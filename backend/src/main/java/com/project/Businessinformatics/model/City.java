package com.project.Businessinformatics.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class City implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CITY_ID_GEN", allocationSize = 10)
	@GeneratedValue(generator = "CITY_ID_GEN")
	private Long id;
	
	@Column
	@Size(max = 60)
	private String name;
	
	@Column(unique = true)
	@Size(max = 12)
	private String pttNumber;
	
	@ManyToOne
	private Country country;
	
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
