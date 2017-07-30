package com.gb.vo;

import java.sql.Date;
import com.gb.model.SizeDetail;

public class SellDetailVo2 {

	private Long orderId;
	private String invoiceNo;
	private String customerName;
	private Long contantNo;
	private String brand;
	private String model;
	private String saleType;
	private String address;
	private Date sellDate;
	private Long amount;
	private Date returnDate;
	private String payment;
	private SizeDetail size;
	private Long amountPaid;
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
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
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
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public SizeDetail getSize() {
		return size;
	}
	public void setSize(SizeDetail size) {
		this.size = size;
	}
	public Long getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(Long amountPaid) {
		this.amountPaid = amountPaid;
	}
	
	
}
