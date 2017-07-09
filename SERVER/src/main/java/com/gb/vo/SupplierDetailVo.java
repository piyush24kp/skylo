package com.gb.vo;

import javax.persistence.Column;

public class SupplierDetailVo {

	private Long supplierId;
	private String supplierName;
	private String contactNo;
	private String address;
	private String amountDue;
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
	public String getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(String amountDue) {
		this.amountDue = amountDue;
	}
	public String getAmountpaid() {
		return amountPaid;
	}
	public void setAmountpaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}
	
	
}
