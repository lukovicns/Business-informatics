package com.project.Businessinformatics.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.Businessinformatics.model.xml.ClearingSettlementRequest;
import com.project.Businessinformatics.model.xml.RTGSRequest;

@Entity
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name="analytical_statement", namespace="http://com/model/statement", propOrder={
	"id", 
	"originator", 
	"purpose",
	"recipient",
	"dateOfReceipt",
	"currencyDate",
	"originatorAccount",
	"model",
	"debitAuthorizationNumber",
	"recipientAccount",
	"approvalModel",
	"approvalAuthorizationNumber",
	"amount",
	"currency",
	"urgently",
	"payment"
})
@XmlRootElement(name = "analytical_statement")
public class AnalyticalStatement {

	@XmlElement(name="id", required=true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "analytical_statement_id", updatable = false, nullable = false, insertable=false)
	private Long id;
	
	@XmlElement(name="originator", required=true)
	@Column
	@Size(max = 256)
	private String originator;
	
	@XmlElement(name="purpose", required=true)
	@Column
	@Size(max = 256)
	private String purpose;
	
	@XmlElement(name="recipient", required=true)
	@Column
	@Size(max = 256)
	private String recipient;
	
	@XmlElement(name="dateOfReceipt", required=true)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Column
	@NotNull
	private String dateOfReceipt;
	
	@XmlElement(name="currencyDate", required=true)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Column
	@NotNull
	private String currencyDate;
	
	@XmlElement(name="originatorAccount", required=true)
	@Column
	@Size(min = 0 , max = 18)
	private String originatorAccount;
	
	@XmlElement(name="model", required=true)
	@Column
	@Digits(integer = 2, fraction = 0)
	private short model;
	
	@XmlElement(name="debitAuthorizationNumber", required=true)
	@Column
	@Size(min = 0, max = 20)
	private String debitAuthorizationNumber;
	
	@XmlElement(name="recipientAccount", required=true)
	@Column
	@Size(min = 0 , max = 18)
	private String recipientAccount;
	
	@XmlElement(name="approvalModel", required=true)
	@Column
	@Digits(integer = 2 , fraction = 0)
	private short approvalModel;
	
	@XmlElement(name="approvalAuthorizationNumber", required=true)
	@Column
	@Size(min = 0, max = 20)
	private String approvalAuthorizationNumber;
	
	@XmlElement(name="urgently", required=true)
	@Column
	@NotNull
	private boolean urgently;
	
	@XmlElement(name="amount", required=true)
	@Column
	@NotNull
	@Digits(integer = 15, fraction = 2)
	private double amount;
	
	@Enumerated(EnumType.STRING)
	private AnalyticalErrors errorType;
	
	
	@OneToOne
	@JoinColumn(name="place_of_acceptance_id")
	private City placeOfAcceptance;
	
	@XmlElement(name="currency", required=true)
	@OneToOne
	@JoinColumn(name="currency_id")
	private Currency currency;
	
	@XmlElement(name="payment", required=false)
	private boolean payment;
	
	@Enumerated(EnumType.STRING)
	private AnalyticalStatementMode analyticalStatementMode;
	
	@ManyToOne(optional = true)
	private ClearingSettlementRequest clearingSettlementRequest;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = RTGSRequest.class, mappedBy="analyticalStatement")
	private Set<RTGSRequest> rtgsRequests;

	@JsonIgnore
	public Set<RTGSRequest> getRtgsRequests() {
		return rtgsRequests;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOriginator() {
		return originator;
	}

	public void setOriginator(String originator) {
		this.originator = originator;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	
	
	public String getDateOfReceipt() {
		return dateOfReceipt;
	}

	public void setDateOfReceipt(String dateOfReceipt) {
		this.dateOfReceipt = dateOfReceipt;
	}

	public String getCurrencyDate() {
		return currencyDate;
	}

	public void setCurrencyDate(String currencyDate) {
		this.currencyDate = currencyDate;
	}

	public String getOriginatorAccount() {
		return originatorAccount;
	}

	public void setOriginatorAccount(String originatorAccount) {
		this.originatorAccount = originatorAccount;
	}

	public short getModel() {
		return model;
	}

	public void setModel(short model) {
		this.model = model;
	}

	public String getDebitAuthorizationNumber() {
		return debitAuthorizationNumber;
	}

	public void setDebitAuthorizationNumber(String debitAuthorizationNumber) {
		this.debitAuthorizationNumber = debitAuthorizationNumber;
	}

	public String getRecipientAccount() {
		return recipientAccount;
	}

	public void setRecipientAccount(String recipientAccount) {
		this.recipientAccount = recipientAccount;
	}

	public short getApprovalModel() {
		return approvalModel;
	}

	public void setApprovalModel(short approvalModel) {
		this.approvalModel = approvalModel;
	}

	public String getApprovalAuthorizationNumber() {
		return approvalAuthorizationNumber;
	}

	public void setApprovalAuthorizationNumber(String approvalAuthorizationNumber) {
		this.approvalAuthorizationNumber = approvalAuthorizationNumber;
	}

	public boolean isUrgently() {
		return urgently;
	}

	public void setUrgently(boolean urgently) {
		this.urgently = urgently;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public AnalyticalErrors getErrorType() {
		return errorType;
	}

	public void setErrorType(AnalyticalErrors errorType) {
		this.errorType = errorType;
	}

	public City getPlaceOfAcceptance() {
		return placeOfAcceptance;
	}

	public void setPlaceOfAcceptance(City placeOfAcceptance) {
		this.placeOfAcceptance = placeOfAcceptance;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public boolean isPayment() {
		return payment;
	}

	public void setPayment(boolean payment) {
		this.payment = payment;
	}

	public AnalyticalStatementMode getAnalyticalStatementMode() {
		return analyticalStatementMode;
	}

	public void setAnalyticalStatementMode(AnalyticalStatementMode analyticalStatementMode) {
		this.analyticalStatementMode = analyticalStatementMode;
	}

	public ClearingSettlementRequest getClearingSettlementRequest() {
		return clearingSettlementRequest;
	}

	public void setClearingSettlementRequest(ClearingSettlementRequest clearingSettlementRequest) {
		this.clearingSettlementRequest = clearingSettlementRequest;
	}

	public void setRtgsRequests(Set<RTGSRequest> rtgsRequests) {
		this.rtgsRequests = rtgsRequests;
	}
	
}
