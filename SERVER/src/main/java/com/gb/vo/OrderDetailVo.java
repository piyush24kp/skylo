package com.gb.vo;

import java.sql.Date;

import com.gb.model.CategoryDetail;
import com.gb.model.SupplierDetail;

public class OrderDetailVo {
	
	private Long orderId;
	private String orderName;
	private Integer amount;
	private Integer quantity;
	private BrandDetailVo brand;
	private Integer purchasePrice;
	private Integer sellPrice;
	private AllSuppliersDetailVo suppliedBy;
	private Date orderDate;
	private AllModelsVo model;
	
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
	public BrandDetailVo getBrand() {
		return brand;
	}
	public void setBrand(BrandDetailVo brand) {
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
	public AllSuppliersDetailVo getSuppliedBy() {
		return suppliedBy;
	}
	public void setSuppliedBy(AllSuppliersDetailVo suppliedBy) {
		this.suppliedBy = suppliedBy;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public AllModelsVo getModel() {
		return model;
	}
	public void setModel(AllModelsVo model) {
		this.model = model;
	}
}
