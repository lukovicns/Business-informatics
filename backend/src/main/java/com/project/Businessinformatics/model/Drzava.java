package com.project.Businessinformatics.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="drzava")
public class Drzava {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "drzava_id", updatable=false, nullable=false, insertable=false)
	private Long id;
	
	@Column(name="naziv", columnDefinition="VARCHAR(40)", nullable=false)
	private String naziv;
	
	public Drzava() {
		
	}

	public Drzava(Long id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
}
