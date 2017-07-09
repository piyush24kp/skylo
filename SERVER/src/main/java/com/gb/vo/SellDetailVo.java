package com.gb.vo;

import java.sql.Date;

public class SellDetailVo {
	private Long orderId;
	private String invoiceNo;
	private String customerName;
	private Long contantNo;
	private BrandDetailVo brand;
	private AllModelsVo model;
	private String saleType;
	private String address;
	private Date sellDate;
	private Long amount;
	private String vendor;
	private String payment;
	private Date returnDate;
	private Integer quantity;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Long getContantNo() {
		return contantNo;
	}
	public void setContantNo(Long contantNo) {
		this.contantNo = contantNo;
	}
	public BrandDetailVo getBrand() {
		return brand;
	}
	public void setBrand(BrandDetailVo brand) {
		this.brand = brand;
	}
	public AllModelsVo getModel() {
		return model;
	}
	public void setModel(AllModelsVo model) {
		this.model = model;
	}
	public String getSaleType() {
		return saleType;
	}
	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getSellDate() {
		return sellDate;
	}
	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
