package com.project.Businessinformatics.model.xml;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.project.Businessinformatics.model.Account;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="statement")
@XmlType(name = "invoice", 
		namespace="http://com/model/invoice", 
		propOrder = {"messageId",
					 "supplier",
					 "purchaser",
					 "billingNumber",
					 "billingDate",
					 "merchandiseValue",
					 "serviceValue",
					 "merchandiseAndServiceValue",
					 "discount",
					 "taxes",
					 "amountForPayment",
					 "accountNumber",
					 "currency",
					 "currencyDate",
					 "invoiceItems"
					})
public class Invoice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7679221176377334279L;
	
	@XmlElement(name="messageId", required=true)
	private Long messageId;
	
	@XmlElement(name="supplier", required=true)
	private Company supplier;
	
	@XmlElement(name="purchaser", required=true)
	private Company purchaser;
	
	@XmlElement(name="billingNumber", required=true)
	private Integer billingNumber;
	
	@XmlElement(name="billingDate", required=true)
	private Date billingDate;
	
	@XmlElement(name="merchandiseValue", required=true)
	private Double merchandiseValue;
	
	@XmlElement(name="serviceValue", required=true)
	private Double serviceValue;
	
	@XmlElement(name="merchandiseAndServiceValue", required=true)
	private Double merchandiseAndServiceValue;
	
	@XmlElement(name="discount", required=true)
	private Double discount;
	
	@XmlElement(name="taxes", required=true)
	private Double taxes;
	
	@XmlElement(name="amountForPayment", required=true)
	private Double amountForPayment;
	
	@XmlElement(name="invoiceItems", required=true)
	private Set<InvoiceItem> invoiceItems;
	
	@XmlElement(name="accountNumber", required=true)
	private Account accountNumber;
	
	@XmlElement(name="currency", required=true)
	private String currency;
	
	@XmlElement(name="currencyDate", required=true)
	private Date currencyDate;

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Company getSupplier() {
		return supplier;
	}

	public void setSupplier(Company supplier) {
		this.supplier = supplier;
	}

	public Company getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(Company purchaser) {
		this.purchaser = purchaser;
	}

	public Integer getBillingNumber() {
		return billingNumber;
	}

	public void setBillingNumber(Integer billingNumber) {
		this.billingNumber = billingNumber;
	}

	public Date getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}

	public Double getMerchandiseValue() {
		return merchandiseValue;
	}

	public void setMerchandiseValue(Double merchandiseValue) {
		this.merchandiseValue = merchandiseValue;
	}

	public Double getServiceValue() {
		return serviceValue;
	}

	public void setServiceValue(Double serviceValue) {
		this.serviceValue = serviceValue;
	}

	public Double getMerchandiseAndServiceValue() {
		return merchandiseAndServiceValue;
	}

	public void setMerchandiseAndServiceValue(Double merchandiseAndServiceValue) {
		this.merchandiseAndServiceValue = merchandiseAndServiceValue;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getTaxes() {
		return taxes;
	}

	public void setTaxes(Double taxes) {
		this.taxes = taxes;
	}

	public Double getAmountForPayment() {
		return amountForPayment;
	}

	public void setAmountForPayment(Double amountForPayment) {
		this.amountForPayment = amountForPayment;
	}

	public Set<InvoiceItem> getInvoiceItems() {
		return invoiceItems;
	}

	public void setInvoiceItems(Set<InvoiceItem> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	public Account getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Account accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getCurrencyDate() {
		return currencyDate;
	}

	public void setCurrencyDate(Date currencyDate) {
		this.currencyDate = currencyDate;
	}
	
	
}
