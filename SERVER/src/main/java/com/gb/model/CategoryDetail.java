package com.gb.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class CategoryDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="uId")
	private Long modelId;
	
	@Column(name="modelName")
	private String modelName;
	
	@Column(name="brandId")
	private Long brandId;
	
	@Column(name="category")
	private String category;
	
	@Column(name="price")
	private String price;
	
	@Column(name="color")
	private String color;

	public Long getModelId() {
		return modelId;
	}
	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}	
	public void setColor(String color) {
		this.color = color;
	}
	public String getColor() {
		return color;
	}
}
