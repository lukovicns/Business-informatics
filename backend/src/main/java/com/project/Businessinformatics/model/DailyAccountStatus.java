package com.project.Businessinformatics.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="daily_account_status", namespace="http://com/model/dailyAccountStatus", propOrder={
	"id", 
	"date", 
	"previousAmount",
	"transferInFavor",
	"numberOfChanges",
	"transferExpenses",
	"currentAmount",
	"account"
})
public class DailyAccountStatus implements Serializable{

	private static final long serialVersionUID = 8421999162724817258L;

	@Id
	@XmlElement(name="id", required=true)
	@SequenceGenerator(name = "DAILY_ACCOUNT_STATUS_ID_GEN", allocationSize = 10)
	@GeneratedValue(generator = "DAILY_ACCOUNT_STATUS_ID_GEN")
	private Long id;
	
	@Column
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")
	@XmlElement(name="date", required=true)
	private Date date;
	
	@Column
	@NotNull
	@Digits(integer=15, fraction=2)
	@XmlElement(name="previousAmount", required=true)
	private double previousAmount;
	
	@Column
	@NotNull
	@Digits(integer=15, fraction=2)
	@XmlElement(name="transferInFavor", required=true)
	private double transferInFavor;
	
	@Column
	@NotNull
	@Digits(integer=6, fraction=0)
	@XmlElement(name="numberOfChanges", required=true)
	private int numberOfChanges;
	
	@Column
	@NotNull
	@Digits(integer=15, fraction=2)
	@XmlElement(name="transferExpenses", required=true)
	private double transferExpenses;
	
	@Column
	@NotNull
	@Digits(integer=15, fraction=2)
	@XmlElement(name="currentAmount", required=true)
	private double currentAmount;
	
	@ManyToOne
	@XmlElement(name="account", required=true)
	private Account account;
	
	@OneToMany(mappedBy = "dailyAccountStatus", fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getPreviousAmount() {
		return previousAmount;
	}

	public void setPreviousAmount(double previousAmount) {
		this.previousAmount = previousAmount;
	}

	public double getTransferInFavor() {
		return transferInFavor;
	}

	public void setTransferInFavor(double transferInFavor) {
		this.transferInFavor = transferInFavor;
	}

	public int getNumberOfChanges() {
		return numberOfChanges;
	}

	public void setNumberOfChanges(int numberOfChanges) {
		this.numberOfChanges = numberOfChanges;
	}

	public double getTransferExpenses() {
		return transferExpenses;
	}

	public void setTransferExpenses(double transferExpenses) {
		this.transferExpenses = transferExpenses;
	}

	public double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
}
