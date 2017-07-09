package com.gb.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gb.model.SellDetail;
import com.gb.vo.OrderCountVo;

@Repository
public class ReviewRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<OrderCountVo> getOrderCountByBrandId() {
		List<OrderCountVo> details = null;
		String baseQuery = "select brand,count(*) as count,Sum(quantity) as quantity FROM orderdetails GROUP BY brand";
		details = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(OrderCountVo.class));
		return details;
	}

	public List<OrderCountVo> getSellCountByBrandId() {
		List<OrderCountVo> details = null;
		String baseQuery = "select brand,count(*) as count FROM selldetail GROUP BY brand";
		details = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(OrderCountVo.class));
		return details;
	}
}
