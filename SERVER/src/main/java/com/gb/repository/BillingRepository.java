package com.gb.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gb.model.OrderDetail;
import com.gb.model.SellDetail;
import com.gb.vo.SellDetailVo2;
import com.gb.vo.SupplierDetailVo;

@Repository
public class BillingRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<SellDetail> getSellOrders() {
		List<SellDetail> sellDetails = null;
		String baseQuery = "select * FROM sellDetail";
		sellDetails = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(SellDetail.class));
		return sellDetails;
	}
	public List<SellDetail> getSellOrdersByDate(Date from, Date to) {
		List<SellDetail> sellDetails = null;
		String baseQuery = "select * FROM sellDetail WHERE orderDate >='"+from+"'and orderDate <= '"+to+"'";
		sellDetails = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(SellDetail.class));
		return sellDetails;
	}
	public Integer setSellOrder(SellDetailVo2 sellDetail) {
		String baseQuery = "INSERT INTO sellDetail(invoiceNo,customerName,contantNo,brand,model,saleType,address,sellDate,amount,size,payment,amountPaid) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] {sellDetail.getInvoiceNo(),sellDetail.getCustomerName(),sellDetail.getContantNo(),sellDetail.getBrand(),sellDetail.getModel(),sellDetail.getSaleType(),sellDetail.getAddress(),sellDetail.getSellDate(),sellDetail.getAmount(),sellDetail.getSize().getSizeId(),sellDetail.getPayment(),sellDetail.getAmountPaid()};
		return jdbcTemplate.update(baseQuery, params);
	}
	
	public Integer updateSellOrder(SellDetailVo2 sellDetail) {
		String baseQuery = "UPDATE sellDetail set orderId = ?, invoiceNo = ?,customerName = ?,contantNo = ?,brand = ?,model = ?,saleType = ?,address = ?,sellDate = ?,amount = ?,returnDate = ?,size = ? ,payment =?, amountPaid= ? WHERE orderId = ?";
		Object[] params = new Object[] { sellDetail.getOrderId(),sellDetail.getInvoiceNo(),sellDetail.getCustomerName(),sellDetail.getContantNo(),sellDetail.getBrand(),sellDetail.getModel(),sellDetail.getSaleType(),sellDetail.getAddress(),sellDetail.getSellDate(),sellDetail.getAmount(),sellDetail.getReturnDate(),sellDetail.getSize().getSizeId(),sellDetail.getPayment(),sellDetail.getAmountPaid(),sellDetail.getOrderId() };
		return jdbcTemplate.update(baseQuery, params);
	}
	
	public Integer deleteSellOrder(Long sellOrderId) {
		String baseQuery = "DELETE FROM sellDetail where orderId = ?";
		Object[] params = new Object[] {sellOrderId };
		return jdbcTemplate.update(baseQuery, params);
	}
	public SellDetail getSellOrdersById(String orderId) {
		List<SellDetail> sellDetails = null;
		String baseQuery = "select * FROM sellDetail WHERE orderId = '" + orderId +"'";
		sellDetails = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(SellDetail.class));
		return sellDetails.get(0);
	}
	
	public SellDetail getSellOrderByInvoiceId(String invoiceNo) {
		List<SellDetail> sellDetails = null;
		String baseQuery = "select * FROM sellDetail WHERE invoiceNo = '" + invoiceNo +"'";
		sellDetails = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(SellDetail.class));
		return sellDetails.get(0);
	}
	public int changePaymentStatus(Long id, String t, Long p) {
		String baseQuery = "UPDATE sellDetail set payment = ?,amountPaid = ?  WHERE orderId = ?";
		Object[] params = new Object[] { t,p,id };
		return jdbcTemplate.update(baseQuery, params);
	}
}
