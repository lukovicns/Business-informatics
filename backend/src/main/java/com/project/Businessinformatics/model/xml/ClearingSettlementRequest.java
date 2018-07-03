package com.project.Businessinformatics.model.xml;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.Businessinformatics.model.AnalyticalStatement;
import com.project.Businessinformatics.model.Bank;
import com.project.Businessinformatics.model.Currency;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="clearing_settlement_request", namespace="http://com/model/clearingAndSettlement", propOrder={
		"paymentBank",
		"recieverBank",
		"totalAmount",
		"currency",
		"currencyDate",
		"date",
		"analyticalStatements",
})
@XmlRootElement(name="MT102")
@Entity
public class ClearingSettlementRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6440091870687987147L;
	
	@GeneratedValue
	@Id
	private Long id;
	
	@XmlElement(name="paymentBank", required=true)
	@ManyToOne(optional = false)
	private Bank paymentBank;
	
	@XmlElement(name="recieverBank", required=true)
	@ManyToOne(optional = false)
	private Bank recieverBank;
	
	@XmlElement(name="totalAmount", required=true)
	@Column(nullable = false)
	private Double totalAmount;
	
	@XmlElement(name="currency", required=true)
	@ManyToOne(optional = false)
	private Currency currency;
	
	@XmlElement(name="currencyDate", required=true)
	@Column(nullable = false)
	private Date currencyDate;
	
	@XmlElement(name="date", required=true)
	@Column(nullable = false)
	private Date date;
	
	@XmlElement(name="analyticalStatements", required=true)
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "clearingSettlementRequest", targetEntity=AnalyticalStatement.class)
	private Set<AnalyticalStatement> analyticalStatements;

	@JsonIgnore
	public Set<AnalyticalStatement> getAnalyticalStatements() {
		return analyticalStatements;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Bank getPaymentBank() {
		return paymentBank;
	}

	public void setPaymentBank(Bank paymentBank) {
		this.paymentBank = paymentBank;
	}

	public Bank getRecieverBank() {
		return recieverBank;
	}

	public void setRecieverBank(Bank recieverBank) {
		this.recieverBank = recieverBank;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Date getCurrencyDate() {
		return currencyDate;
	}

	public void setCurrencyDate(Date currencyDate) {
		this.currencyDate = currencyDate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setAnalyticalStatements(Set<AnalyticalStatement> analyticalStatements) {
		this.analyticalStatements = analyticalStatements;
	}


	
}
