package com.gb.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gb.model.LoginDetail;
import com.gb.model.OrderDetail;
import com.gb.model.userDetails;
import com.gb.vo.LoginDetailVo;
import com.gb.vo.UserDetailsVo;

@Repository
public class UserRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<LoginDetail> login(LoginDetail loginDetail) {
		List<LoginDetail>  detail = new ArrayList<LoginDetail>();
		String baseQuery = "select * FROM userdetails WHERE userId = '"+ loginDetail.getUserId() +"' AND password = '" + loginDetail.getPassword()+"'";
		detail = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(LoginDetail.class));
		return detail;
	}

	public Integer changePassword(LoginDetailVo loginDetail) {
		String baseQuery = "UPDATE userdetails set userId = ?,password = ? WHERE userId = ?";
		Object[] params = new Object[] { loginDetail.getUserId(),loginDetail.getPassword(),loginDetail.getUserId() };
		return jdbcTemplate.update(baseQuery, params);
	}


}
