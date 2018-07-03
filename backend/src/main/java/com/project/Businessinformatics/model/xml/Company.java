package com.project.Businessinformatics.model.xml;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "COMPANY")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="company", namespace="http://com/model/company", propOrder={
		"name", 
		"pib", 
		"address"
})
public class Company implements Serializable{
	

	private static final long serialVersionUID = -5675230633531765526L;
	
	
	@Id
	@Column(name = "COMPANY_ID")
	@GeneratedValue
	private Long id;
	
	@XmlElement(name="name", required=true)
	@Column(name = "COMPANY_NAME")
	private String name;
	
	@XmlElement(name="pib", required=true)
	@Column(name = "COMPANY_PIB")
	private String pib;
	
	@XmlElement(name="address", required=true)
	@Column(name = "COMPANY_ADDRESS")
	private String address;

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

	public String getPib() {
		return pib;
	}

	public void setPib(String pib) {
		this.pib = pib;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
