package com.gb.vo;

import java.sql.Date;

import com.gb.model.CategoryDetail;
import com.gb.model.SupplierDetail;

public class OrderDetailVo {
	
	private Long orderId;
	private String orderName;
	private Long amount;
	private BrandDetailVo brand;
	private AllSuppliersDetailVo suppliedBy;
	private Date orderDate;
	private AllModelsVo model;
	private SizeDetailVo size;
	
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
	public BrandDetailVo getBrand() {
		return brand;
	}
	public void setBrand(BrandDetailVo brand) {
		this.brand = brand;
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
	public SizeDetailVo getSize() {
		return size;
	}
	public void setSize(SizeDetailVo size) {
		this.size = size;
	}
}
