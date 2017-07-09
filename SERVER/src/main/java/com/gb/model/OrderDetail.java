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
	private Integer amount;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@Column(name="brand")
	private String brand;
	
	@Column(name="purchasePrice")
	private Integer purchasePrice;
	
	@Column(name="sellPrice")
	private Integer sellPrice;
	
	@Column(name="suppliedBy")
	private Long suppliedBy;
	
	@Column(name="orderDate")
	private Date orderDate;
	
	@Column(name="orderDate")
	private String model;

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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Integer purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Integer getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Integer sellPrice) {
		this.sellPrice = sellPrice;
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
	
}
