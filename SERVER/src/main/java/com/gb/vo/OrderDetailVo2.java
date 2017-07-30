package com.gb.vo;

import java.sql.Date;

import javax.persistence.Column;

import com.gb.model.SizeDetail;

public class OrderDetailVo2 {

private Integer uId;
	
	private Long orderId;
	private String orderName;
	private Long amount;
	private String brand;
	private Long suppliedBy;
	private Date orderDate;
	private String model;
	private SizeDetail size;
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Long getSuppliedBy() {
		return suppliedBy;
	}
	public void setSuppliedBy(Long suppliedBy) {
		this.suppliedBy = suppliedBy;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public SizeDetail getSize() {
		return size;
	}
	public void setSize(SizeDetail size) {
		this.size = size;
	}
	
	
}
