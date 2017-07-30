package com.gb.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="uId")
	private Integer uId;
	
	@Column(name="orderId")
	private Long orderId;
	
	@Column(name="orderName")
	private String orderName;
	
	@Column(name="amount")
	private Long amount;
	
	@Column(name="brand")
	private String brand;
	
	@Column(name="suppliedBy")
	private Long suppliedBy;
	
	@Column(name="orderDate")
	private Date orderDate;
	
	@Column(name="model")
	private String model;
	
	@Column(name="size")
	private Long size;

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
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	
	
	
}
