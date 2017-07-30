package com.gb.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ExpenseDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="uId")
	private Long uId;
	
	@Column(name="expenseName")
	private String expenseName;
	
	@Column(name="amount")
	private Long amount;
	
	@Column(name="expenseDate")
	private Date expenseDate;
	
	@Column(name="type")
	private String type;

	public Long getuId() {
		return uId;
	}
	public void setuId(Long uId) {
		this.uId = uId;
	}
	public String getExpenseName() {
		return expenseName;
	}

	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
	

}
