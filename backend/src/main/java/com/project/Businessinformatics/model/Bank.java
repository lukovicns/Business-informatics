package com.project.Businessinformatics.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.Businessinformatics.model.xml.ClearingSettlementRequest;
import com.project.Businessinformatics.model.xml.RTGSRequest;
import com.project.Businessinformatics.model.xml.RTGSResponse;

@Entity
@Table(name = "BANK")
@XmlType(name = "bank", namespace = "http://com/model/bank", propOrder = { "swift",
		"transactionAccount" })
@XmlAccessorType(XmlAccessType.NONE)
public class Bank implements Serializable {

	private static final long serialVersionUID = -5790070241207558112L;

	@Id
	@Column(name = "BANK_ID")
	@GeneratedValue
	private Long id;

	// lista kursnih lista...
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = ExchangeList.class, mappedBy = "bank")
	private Set<ExchangeList> exchangeLists;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bank", orphanRemoval = true, targetEntity = Account.class)
	private Set<Account> accounts;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = RTGSResponse.class, mappedBy = "bank")
	private Set<RTGSResponse> rTGSResponses;
	
	@ManyToOne
	private Country country;

	@Column(name = "BANK_PIB", nullable = false, length = 10)
	private String pib;

	@Column(name = "BANK_NAME", nullable = false, length = 120)
	private String name;

	@Column(name = "BANK_ADDRESS", nullable = false, length = 120)
	private String address;

	@Column(name = "BANK_EMAIL", length = 128)
	private String email;

	@Column(name = "BANK_WEB", length = 128)
	private String web;

	@Column(name = "BANK_TEL", length = 20)
	private String telephone;

	@Column(name = "BANK_FAX", length = 20)
	private String fax;

	@Column(name = "BANK_ACT", nullable = false)
	private boolean banka;

	@XmlElement(name = "swift", required = true)
	@Column(name = "BANK_SWT", nullable = false, length = 8)
	private String swift;

	@XmlElement(name = "transactionAccount", required = true)
	@Column(name = "BANK_TR_ACC")
	private String transactionAccount;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = RTGSRequest.class, mappedBy="paymentBank")
	private Set<RTGSRequest> rtgsRequestsPaymentBank;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = RTGSRequest.class, mappedBy="recieverBank")
	private Set<RTGSRequest> rtgsRequestsRecieverBank;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = ClearingSettlementRequest.class, mappedBy="paymentBank")
	private Set<ClearingSettlementRequest> cSPaymentBank;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = ClearingSettlementRequest.class, mappedBy="recieverBank")
	private Set<ClearingSettlementRequest> cSRecieverBank;
	
	public String getSwift() {
		return swift;
	}

	@JsonIgnore
	public Set<ExchangeList> getExchangeLists() {
		return exchangeLists;
	}

	@JsonProperty
	public void setExchangeLists(Set<ExchangeList> exchangeLists) {
		this.exchangeLists = exchangeLists;
	}

	@JsonIgnore
	public Set<Account> getAccounts() {
		return accounts;
	}

	@JsonProperty
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	@JsonIgnore
	public Set<RTGSRequest> getRtgsRequestsPaymentBank() {
		return rtgsRequestsPaymentBank;
	}

	@JsonIgnore
	public Set<RTGSRequest> getRtgsRequestsRecieverBank() {
		return rtgsRequestsRecieverBank;
	}

	@JsonIgnore
	public Set<RTGSResponse> getrTGSResponses() {
		return rTGSResponses;
	}

	@JsonIgnore
	public Set<ClearingSettlementRequest> getcSPaymentBank() {
		return cSPaymentBank;
	}
	
	@JsonIgnore
	public Set<ClearingSettlementRequest> getcSRecieverBank() {
		return cSRecieverBank;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getPib() {
		return pib;
	}

	public void setPib(String pib) {
		this.pib = pib;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public boolean isBanka() {
		return banka;
	}

	public void setBanka(boolean banka) {
		this.banka = banka;
	}

	public String getTransactionAccount() {
		return transactionAccount;
	}

	public void setTransactionAccount(String transactionAccount) {
		this.transactionAccount = transactionAccount;
	}

	public void setrTGSResponses(Set<RTGSResponse> rTGSResponses) {
		this.rTGSResponses = rTGSResponses;
	}

	public void setSwift(String swift) {
		this.swift = swift;
	}

	public void setRtgsRequestsPaymentBank(Set<RTGSRequest> rtgsRequestsPaymentBank) {
		this.rtgsRequestsPaymentBank = rtgsRequestsPaymentBank;
	}

	public void setRtgsRequestsRecieverBank(Set<RTGSRequest> rtgsRequestsRecieverBank) {
		this.rtgsRequestsRecieverBank = rtgsRequestsRecieverBank;
	}

	public void setcSPaymentBank(Set<ClearingSettlementRequest> cSPaymentBank) {
		this.cSPaymentBank = cSPaymentBank;
	}

	public void setcSRecieverBank(Set<ClearingSettlementRequest> cSRecieverBank) {
		this.cSRecieverBank = cSRecieverBank;
	}
	
	
	
}
