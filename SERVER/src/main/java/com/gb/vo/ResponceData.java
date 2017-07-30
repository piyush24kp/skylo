package com.gb.vo;

public class ResponceData {

	private Page page;
	private Object databean;
	private String message;
	private String error;
	
	public Object getDatabean() {
		return databean;
	}
	public void setDatabean(Object databean) {
		this.databean = databean;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
}
