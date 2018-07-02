package com.project.Businessinformatics.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="klijent")
public class Klijent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "klijent_id", updatable=false, nullable=false, insertable=false)	
	private Long id;
	
	@Column(name="ime", columnDefinition="VARCHAR(40)", nullable=false)
	private String ime;
	
	@Column(name="prezime", columnDefinition="VARCHAR(40)", nullable=false)
	private String prezime;
	
	@Column(name="ulica", columnDefinition="VARCHAR(40)", nullable=false)
	private String ulica;
	
	@Column(name="grad", columnDefinition="VARCHAR(40)", nullable=false)
	private String grad;
	
	@Column(name="broj_telefona", columnDefinition="VARCHAR(40)", nullable=false)
	private String brojTelefona;
	
	@Column(name="email", columnDefinition="VARCHAR(40)", nullable=false)
	private String email;
	
	@Column(name="password", columnDefinition="VARCHAR(40)", nullable=false)
	private String password;
	
	public Klijent() {
		
	}

	public Klijent(Long id, String ime, String prezime, String ulica, String grad, String brojTelefona,
			String email, String password) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.ulica = ulica;
		this.grad = grad;
		this.brojTelefona = brojTelefona;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
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
