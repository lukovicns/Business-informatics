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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.Businessinformatics.model.xml.ClearingSettlementRequest;

@Entity
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="currency", namespace="http://com/model/currency", propOrder={
		"officialCode"
	} 
)
public class Currency {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "currency_id", updatable = false, nullable = false, insertable=false)
	private Long id;

	@XmlElement(name="officialCode", required=true)
	@Column(nullable = false)
	@Size(min = 3, max = 3)
	private String officialCode;
	
	@Column(nullable = false)
	@Size(max = 30)
	private String name;
	
	@Column(nullable = false)
	private boolean domicilna;
	
	@OneToOne
	@JoinColumn(name="country_id")
	private Country country;
	
	@OneToMany(mappedBy = "currency", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<AnalyticalStatement> analyticalStatement = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = Account.class, mappedBy="currency")
	private Set<Account> legalPersonAccounts;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = CurrencyExchange.class, mappedBy="primaryCurrency")
	private Set<CurrencyExchange> primaryCurrencyExchanges;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = CurrencyExchange.class, mappedBy="accordingToCurrency")
	private Set<CurrencyExchange> accordingTocurrencyExchanges;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = ClearingSettlementRequest.class, mappedBy="currency")
	private Set<ClearingSettlementRequest> clearingSettlementRequests;
	
	public Country getCountry() {
		return country;
	}

	public Currency() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getOfficialCode() {
		return officialCode;
	}

	public String getName() {
		return name;
	}

	public boolean isDomicilna() {
		return domicilna;
	}

	@JsonIgnore
	public Set<AnalyticalStatement> getAnalyticalStatement() {
		return analyticalStatement;
	}

	@JsonProperty
	public void setAnalyticalStatement(Set<AnalyticalStatement> analyticalStatement) {
		this.analyticalStatement = analyticalStatement;
	}

	@JsonIgnore
	public Set<CurrencyExchange> getPrimaryCurrencyExchanges() {
		return primaryCurrencyExchanges;
	}

	@JsonProperty
	public void setPrimaryCurrencyExchanges(Set<CurrencyExchange> primaryCurrencyExchanges) {
		this.primaryCurrencyExchanges = primaryCurrencyExchanges;
	}

	@JsonIgnore
	public Set<CurrencyExchange> getAccordingTocurrencyExchanges() {
		return accordingTocurrencyExchanges;
	}

	@JsonProperty
	public void setAccordingTocurrencyExchanges(Set<CurrencyExchange> accordingTocurrencyExchanges) {
		this.accordingTocurrencyExchanges = accordingTocurrencyExchanges;
	}

	@JsonIgnore
	public Set<Account> getLegalPersonAccounts() {
		return legalPersonAccounts;
	}

	@JsonProperty
	public void setLegalPersonAccounts(Set<Account> legalPersonAccounts) {
		this.legalPersonAccounts = legalPersonAccounts;
	}

	@JsonIgnore
	public Set<ClearingSettlementRequest> getClearingSettlementRequests() {
		return clearingSettlementRequests;
	}

	@JsonProperty
	public void setClearingSettlementRequests(Set<ClearingSettlementRequest> clearingSettlementRequests) {
		this.clearingSettlementRequests = clearingSettlementRequests;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOfficialCode(String officialCode) {
		this.officialCode = officialCode;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDomicilna(boolean domicilna) {
		this.domicilna = domicilna;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	
}
