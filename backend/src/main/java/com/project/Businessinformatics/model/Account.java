package com.project.Businessinformatics.model;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.Businessinformatics.model.user.Client;


@Entity
@Table(name = "ACCOUNT")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "account", namespace = "http://com/model/account", propOrder = { "accountNumber" })
public class Account implements Serializable {


	private static final long serialVersionUID = 4207693779878640627L;

	@Id
	@Column(name = "ACCOUNT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@XmlElement(name = "accountNumber", required = true)
	@Column(name = "ACCOUNT_NUM")
	private String accountNumber;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "ACCOUNT_DATE")
	private String openingDate;

	@Column(name = "ACCOUNT_ACTIVE")
	private boolean active;

	@OneToOne
	@JoinColumn(name="bank_id")
	private Bank bank;

	@OneToOne
	@JoinColumn(name="client_id")
	private Client client;

	@OneToOne
	@JoinColumn(name="currency_id")
	private Currency currency;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account", orphanRemoval = true)
	private Set<RevokedAccount> revokedAccounts;

	@JsonIgnore
	public Set<RevokedAccount> getRevokedAccounts() {
		return revokedAccounts;
	}

	@JsonProperty
	public void setRevokedAccounts(Set<RevokedAccount> revokedAccounts) {
		this.revokedAccounts = revokedAccounts;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(String openingDate) {
		this.openingDate = openingDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}


}
