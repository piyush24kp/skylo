package com.gb.vo;

import java.util.List;
import java.util.Map;

public class chartDetailVo {

	private List brands;
	private Map<String,List> series;
	public List getBrands() {
		return brands;
	}
	public void setBrands(List brands) {
		this.brands = brands;
	}
	public Map<String, List> getSeries() {
		return series;
	}
	public void setSeries(Map<String, List> series) {
		this.series = series;
	}
}
