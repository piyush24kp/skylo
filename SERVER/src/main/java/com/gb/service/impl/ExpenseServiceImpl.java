package com.gb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.model.ExpenseDetails;
import com.gb.repository.ExpenseRepository;
import com.gb.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService{
	
	@Autowired
	ExpenseRepository expenseRepository;

	public List<ExpenseDetails> getExpense() {
		return expenseRepository.getExpense();
		
	}

	public boolean setExpense(ExpenseDetails expense) {
		return expenseRepository.setExpense(expense);
	}

	public List<ExpenseDetails> searchExpence(String keyword) {
		return expenseRepository.searchExpence(keyword);
	}

}
