package com.project.Businessinformatics.model.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "invoiceItem", 
		namespace="http://com/model/invoiceItem", 
		propOrder = {"ordinate",
					 "merchandiseOrServiceName",
					 "amount",
					 "unitOfMeasure",
					 "unitPrice",
					 "value",
					 "discountPercentage",
					 "discountValue",
					 "discountTaken",
					 "totalTaxes"
					})
public class InvoiceItem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7807483718505008986L;
	
	@XmlElement(name="ordinate", required=true)
	private Integer ordinate;
	
	@XmlElement(name="merchandiseOrServiceName", required=true)
	private String merchandiseOrServiceName;
	
	@XmlElement(name="amount", required=true)
	private Double amount;
	
	@XmlElement(name="unitOfMeasure", required=true)
	private String unitOfMeasure;
	
	@XmlElement(name="unitPrice", required=true)
	private Double unitPrice;
	
	@XmlElement(name="value", required=true)
	private Double value;
	
	@XmlElement(name="discountPercentage", required=true)
	private Double discountPercentage;
	
	@XmlElement(name="discountValue", required=true)
	private Double discountValue;
	
	@XmlElement(name="discountTaken", required=true)
	private Double discountTaken;
	
	@XmlElement(name="totalTaxes", required=true)
	private Double totalTaxes;

	public Integer getOrdinate() {
		return ordinate;
	}

	public void setOrdinate(Integer ordinate) {
		this.ordinate = ordinate;
	}

	public String getMerchandiseOrServiceName() {
		return merchandiseOrServiceName;
	}

	public void setMerchandiseOrServiceName(String merchandiseOrServiceName) {
		this.merchandiseOrServiceName = merchandiseOrServiceName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public Double getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(Double discountValue) {
		this.discountValue = discountValue;
	}

	public Double getDiscountTaken() {
		return discountTaken;
	}

	public void setDiscountTaken(Double discountTaken) {
		this.discountTaken = discountTaken;
	}

	public Double getTotalTaxes() {
		return totalTaxes;
	}

	public void setTotalTaxes(Double totalTaxes) {
		this.totalTaxes = totalTaxes;
	}
	
	
	
}
