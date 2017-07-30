package com.gb.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class HistoryMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="historyId")
	private Long historyId;
	
	@Column(name="historyType")
	private String historyType;


	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

	public String getHistoryType() {
		return historyType;
	}

	public void setHistoryType(String historyType) {
		this.historyType = historyType;
	}
	
	

}
