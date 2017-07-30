package com.gb.repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gb.model.HistoryDetail;
import com.gb.model.OrderDetail;
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

	public List<HistoryDetail> getHistory(Date from, Date to,String type) {
		List<HistoryDetail> historyDetails = null;
		String baseQuery = "select * FROM history WHERE status = 'A' and createdDate >='"+from+"'and createdDate <= '"+to+"'";
		if(type!=null){
			baseQuery = baseQuery + "and historyType = '" +type+ "'";
		}
		historyDetails = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(HistoryDetail.class));
		return historyDetails;
		
	}
}
