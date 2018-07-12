package com.project.Businessinformatics.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;

@Entity
public class CurrencyExchange {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "currency_exchange_id", updatable = false, nullable = false, insertable=false)
	private Long id;
	
	@Column(nullable = false)
	@Digits(fraction = 4, integer = 13)
	private Double buyRate;
	
	@Column(nullable = false)
	@Digits(fraction = 4, integer = 13)
	private Double middleRate;
	
	@Column(nullable = false)
	@Digits(fraction = 4, integer = 13)
	private Double sellRate;
	
	@OneToOne
	@JoinColumn(name="exchange_list_id")
	private ExchangeList exchangeList;
	
	@OneToOne
	@JoinColumn(name="primary_currency_id")
	private Currency primaryCurrency;

	@OneToOne
	@JoinColumn(name="according_to_currency_id")
	private Currency accordingToCurrency;

	public CurrencyExchange() {
		super();
	}

	public Long getId() {
		return id;
	}

	public Double getBuyRate() {
		return buyRate;
	}

	public Double getMiddleRate() {
		return middleRate;
	}

	public Double getSellRate() {
		return sellRate;
	}

	public ExchangeList getExchangeList() {
		return exchangeList;
	}

	public Currency getPrimaryCurrency() {
		return primaryCurrency;
	}

	public Currency getAccordingToCurrency() {
		return accordingToCurrency;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setBuyRate(Double buyRate) {
		this.buyRate = buyRate;
	}

	public void setMiddleRate(Double middleRate) {
		this.middleRate = middleRate;
	}

	public void setSellRate(Double sellRate) {
		this.sellRate = sellRate;
	}

	public void setExchangeList(ExchangeList exchangeList) {
		this.exchangeList = exchangeList;
	}

	public void setPrimaryCurrency(Currency primaryCurrency) {
		this.primaryCurrency = primaryCurrency;
	}

	public void setAccordingToCurrency(Currency accordingToCurrency) {
		this.accordingToCurrency = accordingToCurrency;
	}
	
	
}
