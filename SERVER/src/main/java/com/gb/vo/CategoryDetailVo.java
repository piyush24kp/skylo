package com.gb.vo;

public class CategoryDetailVo {

	private Long modelId;
	private String modelName;
	private BrandDetailVo brandId;
	private String category;
	private String price;
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
	public BrandDetailVo getBrandId() {
		return brandId;
	}
	public void setBrandId(BrandDetailVo brandId) {
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
