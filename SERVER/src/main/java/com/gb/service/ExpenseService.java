package com.gb.service;

import java.util.List;

import com.gb.model.ExpenseDetails;

public interface ExpenseService {
	public List<ExpenseDetails> getExpense();
	public boolean setExpense(ExpenseDetails expense);
	public List<ExpenseDetails> searchExpence( String keyword);
	
}
