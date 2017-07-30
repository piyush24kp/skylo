package com.gb.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.gb.vo.OrderDetailVo;

public class HistoryDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="historyType")
	private String historyType;
	
	@Column(name="productDetail")
	private String productDetail;
	
	@Column(name="createdDate")
	private Date createdDate;
	
	@Column(name="size")
	private Long size;
	
	@Column(name="amount")
	private Long amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHistoryType() {
		return historyType;
	}

	public void setHistoryType(String historyType) {
		this.historyType = historyType;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	
}
