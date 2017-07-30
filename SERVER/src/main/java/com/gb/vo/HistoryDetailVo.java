package com.gb.vo;

import java.sql.Date;

public class HistoryDetailVo {
	
	private int id;
	private SellDetailVo sellDetailVo;
	private OrderDetailVo orderDetailVo;
	private Date historyDate;
	private String historyType;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public SellDetailVo getSellDetailVo() {
		return sellDetailVo;
	}
	public void setSellDetailVo(SellDetailVo sellDetailVo) {
		this.sellDetailVo = sellDetailVo;
	}
	public OrderDetailVo getOrderDetailVo() {
		return orderDetailVo;
	}
	public void setOrderDetailVo(OrderDetailVo orderDetailVo) {
		this.orderDetailVo = orderDetailVo;
	}
	public Date getHistoryDate() {
		return historyDate;
	}
	public void setHistoryDate(Date historyDate) {
		this.historyDate = historyDate;
	}
	public String getHistoryType() {
		return historyType;
	}
	public void setHistoryType(String historyType) {
		this.historyType = historyType;
	}
	
	

}
