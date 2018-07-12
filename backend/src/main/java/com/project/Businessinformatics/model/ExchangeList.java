package com.project.Businessinformatics.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
public class ExchangeList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exchange_list_id", updatable = false, nullable = false, insertable=false)
	private Long id;
	
	@Column(nullable = false)
	private String date;
	
	@Column(nullable = false)
	@Digits(integer=3, fraction = 0)
	private int numberOfExchangeList;
	
	@Column(nullable = false)
	private String usedSince;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, targetEntity=CurrencyExchange.class, mappedBy = "exchangeList")
	private Set<CurrencyExchange> currencyExchanges;
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setNumberOfExchangeList(int numberOfExchangeList) {
		this.numberOfExchangeList = numberOfExchangeList;
	}

	public void setUsedSince(String usedSince) {
		this.usedSince = usedSince;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	//polje banke
	@ManyToOne(optional = false)
	private Bank bank;
	
	public Bank getBank() {
		return bank;
	}

	public ExchangeList() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public int getNumberOfExchangeList() {
		return numberOfExchangeList;
	}

	public String getUsedSince() {
		return usedSince;
	}

	@JsonIgnore
	public Set<CurrencyExchange> getCurrencyExchanges() {
		return currencyExchanges;
	}

	@JsonProperty
	public void setCurrencyExchanges(Set<CurrencyExchange> currencyExchanges) {
		this.currencyExchanges = currencyExchanges;
	}
	
	

}
