package com.gb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gb.model.ExpenseDetails;


@Repository
public class ExpenseRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<ExpenseDetails> getExpense() {
		List<ExpenseDetails> details = null;
		String baseQuery = "select * FROM expense";
		details = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(ExpenseDetails.class));
		return details;
	}

	public boolean setExpense(ExpenseDetails ed) {
		String baseQuery = "INSERT INTO expense(expenseName, amount,expenseDate,type) VALUES(?,?,?,?)";
		Object[] params = new Object[] { ed.getExpenseName(),ed.getAmount(),ed.getExpenseDate(),ed.getType() };
		 if(jdbcTemplate.update(baseQuery, params) ==1){
			 return true;
		 }
		return false;
	}

	public List<ExpenseDetails> searchExpence( String keyword) {
		List<ExpenseDetails> details = null;
		String baseQuery = "select * FROM expense where expenseName like '%"+keyword+"%'";
		details = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(ExpenseDetails.class));
		return details;
	}

}
