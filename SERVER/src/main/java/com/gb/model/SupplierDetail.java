package com.gb.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SupplierDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="supplierId")
	private Long supplierId;
	
	@Column(name="supplierName")
	private String supplierName;
	
	@Column(name="contactNo")
	private String contactNo;
	
	@Column(name="address")
	private String address;
	
	@Column(name="amountDue")
	private String amountDue;
	
	@Column(name="amountPaid")
	private String amountPaid;

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAmountpaid() {
		return amountPaid;
	}
	public void setAmountpaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}
	public void setAmountDue(String amountDue) {
		this.amountDue = amountDue;
	}
	public String getAmountDue() {
		return amountDue;
	}
	
}
