package com.gb.repository;

import java.sql.Date;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gb.model.OrderDetail;
import com.gb.model.SupplierDetail;
import com.gb.model.BrandDetail;
import com.gb.model.CategoryDetail;
import com.gb.model.userDetails;
import com.gb.vo.AllModelsVo;
import com.gb.vo.BrandDetailVo;
import com.gb.vo.OrderDetailVo;
import com.gb.vo.SupplierDetailVo;

@Repository
public class OrderRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<OrderDetail> getOrders() {
		List<OrderDetail> orderDetail = null;
		String baseQuery = "select * FROM orderDetails";
		orderDetail = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(OrderDetail.class));
		return orderDetail;
	}
	
	public List<OrderDetail> getOrdersByDate(Date from ,Date to) {
		List<OrderDetail> orderDetail = null;
		String baseQuery = "select * FROM orderDetails WHERE orderDate >='"+from+"'and orderDate <= '"+to+"'";
		orderDetail = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(OrderDetail.class));
		return orderDetail;
	}

	public OrderDetail getOrders(Long orderID) {
		List<OrderDetail> orderDetail = null;
		String baseQuery = "select * FROM orderDetails WHERE orderId =" + orderID;
		orderDetail = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(OrderDetail.class));
		return orderDetail.get(0);
	}

	public Integer createOrder(OrderDetail odDetailVo) {
		String baseQuery = "INSERT INTO orderDetails(orderId, orderName,quantity,brand,purchasePrice,sellPrice,suppliedBy,orderDate,model) VALUES(?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] { odDetailVo.getOrderId(), odDetailVo.getOrderName(), odDetailVo.getQuantity(),
				odDetailVo.getBrand(),  odDetailVo.getPurchasePrice(),
				odDetailVo.getSellPrice(), odDetailVo.getSuppliedBy(),odDetailVo.getOrderDate(),odDetailVo.getModel() };
		return jdbcTemplate.update(baseQuery, params);
	}

	public Integer updateOrder(OrderDetail odDetailVo) {
		String baseQuery = "UPDATE orderDetails set orderId = ?, orderName = ?,quantity = ?,brand = ?,purchasePrice = ?,sellPrice = ?,suppliedBy = ?,orderDate = ?,model = ? WHERE orderId = ?";
		Object[] params = new Object[] { odDetailVo.getOrderId(), odDetailVo.getOrderName(), odDetailVo.getQuantity(),
				odDetailVo.getBrand(),  odDetailVo.getPurchasePrice(),
				odDetailVo.getSellPrice(), odDetailVo.getSuppliedBy(), odDetailVo.getOrderDate(),odDetailVo.getModel() ,odDetailVo.getOrderId() };
		return jdbcTemplate.update(baseQuery, params);
	}
	
	public Integer deleteOrder(Long odDetailVo) {
		String baseQuery = "DELETE FROM orderDetails where orderId = ?";
		Object[] params = new Object[] {odDetailVo };
		return jdbcTemplate.update(baseQuery, params);
	}
	
	public List<OrderDetail> getOrderByBrandAndModel(String brand,String model) {
		List<OrderDetail> details = null;
		String baseQuery = "select * FROM orderDetails WHERE brand = '" + brand+ "' AND model = '"+ model+ "'";
		details = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(OrderDetail.class));
		return details;
	}
	
	

	public Integer createSupplier(SupplierDetailVo odDetailVo) {
		String baseQuery = "INSERT INTO supplier(supplierName,contactNo,address,amountDue,amountPaid) VALUES(?,?,?,?,?)";
		Object[] params = new Object[] { odDetailVo.getSupplierName(), odDetailVo.getContactNo(),
				odDetailVo.getAddress(), odDetailVo.getAmountDue(), odDetailVo.getAmountpaid() };
		return jdbcTemplate.update(baseQuery, params);
	}

	public SupplierDetail getSupplier(Long supplierID) {
		List<SupplierDetail> supplierDetails = null;
		String baseQuery = "select * FROM supplier WHERE orderId =" + supplierID;
		supplierDetails = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(SupplierDetail.class));
		return supplierDetails.get(0);
	}

	public List<SupplierDetail> getSupplier() {
		List<SupplierDetail> orderDetail = null;
		String baseQuery = "select * FROM supplier";
		orderDetail = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(SupplierDetail.class));
		return orderDetail;
	}

	public List<SupplierDetail> getSupplierIdAndName() {
		List<SupplierDetail> orderDetail = null;
		String baseQuery = "select supplierId,supplierName FROM supplier";
		orderDetail = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(SupplierDetail.class));
		return orderDetail;
	}

	public SupplierDetail getSupplierId(Long supplierId) {
		List<SupplierDetail> orderDetail = null;
		String baseQuery = "Select * FROM supplier WHERE supplierId = "+ supplierId;
		orderDetail = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(SupplierDetail.class));
		return orderDetail.get(0);
	}
	
	public Integer updateSupplier(SupplierDetail s) {
		String baseQuery = "UPDATE supplier set supplierId = ?, supplierName = ?,contactNo = ?,address = ?,amountDue = ?,amountPaid = ? WHERE supplierId = ?";
		Object[] params = new Object[] {s.getSupplierId(),s.getSupplierName(),s.getContactNo(),s.getAddress(),s.getAmountDue(),s.getAmountpaid() };
		return jdbcTemplate.update(baseQuery, params);
	}
	
	public Integer deleteSupplier(Long supplierId) {
		String baseQuery = "DELETE FROM supplier where supplierId = ?";
		Object[] params = new Object[] {supplierId };
		return jdbcTemplate.update(baseQuery, params);
	}
	
	public SupplierDetail getSupplierById() {
		List<SupplierDetail> details = new ArrayList();
		String baseQuery = "select * FROM supplier WHERE supplierId = (select MAX(supplierId) from supplier)";
		details = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(SupplierDetail.class));
		return details.get(0);
	}
	
	public List<BrandDetail> getBrands() {
		
		List<BrandDetail> brandDetail = new ArrayList();
		String baseQuery = "select * FROM brands";
		brandDetail = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(BrandDetail.class));
		return brandDetail;
	}
	
	public Integer createBrands(BrandDetailVo b) {
		String baseQuery = "INSERT INTO brands(brandName) VALUES(?)";
		Object[] params = new Object[] { b.getBrandName() };
		return jdbcTemplate.update(baseQuery, params);
	}
	
	public Integer updateBrands(BrandDetailVo b) {
		String baseQuery = "UPDATE brands set brandName = ? WHERE brandId = ?";
		Object[] params = new Object[] { b.getBrandName(),b.getBrandId() };
		return jdbcTemplate.update(baseQuery, params);
	}
	
	public Integer deleteBrands(Long brandId) {
		String baseQuery = "DELETE FROM brands WHERE brandId = ?";
		Object[] params = new Object[] { brandId };
		return jdbcTemplate.update(baseQuery, params);
	}
	
	public BrandDetail getUpdateBrand(BrandDetailVo b) {
		List<BrandDetail> brandDetail = new ArrayList();
		String baseQuery = "select * FROM brands WHERE brandId = (select MAX(brandId) from brands)";
		brandDetail = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(BrandDetail.class));
		return brandDetail.get(0);
	}
	
	public BrandDetail getBrandById(Long brandId) {
		List<BrandDetail> brandDetail = new ArrayList();
		String baseQuery = "select * FROM brands WHERE brandId = "+ brandId;
		brandDetail = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(BrandDetail.class));
		return brandDetail.get(0);
	}

	public List<CategoryDetail> getModelByBrandId(Long brandId) {
		List<CategoryDetail> brandDetail = new ArrayList();
		String baseQuery = "select * FROM models WHERE brandId = " + brandId;
		brandDetail = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(CategoryDetail.class));
		return brandDetail;
	}

	public CategoryDetail getModelById(Long modelId) {
		List<CategoryDetail> details = new ArrayList();
		String baseQuery = "select * FROM models WHERE modelId = " + modelId;
		details = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(CategoryDetail.class));
		return details.get(0);
	}

	public Integer setModel(CategoryDetail modelDetail) {
		String baseQuery = "INSERT INTO models(modelName,brandId,category,price,color) VALUES(?,?,?,?,?)";
		Object[] params = new Object[] { modelDetail.getModelName(),modelDetail.getBrandId(),modelDetail.getCategory(),modelDetail.getPrice() ,modelDetail.getColor()};
		return jdbcTemplate.update(baseQuery, params);
	}

	public CategoryDetail getUpdateModel(CategoryDetail modelDetail) {
		List<CategoryDetail> details = new ArrayList();
		String baseQuery = "select * FROM models WHERE modelId = (select MAX(modelId) from models)";
		details = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(CategoryDetail.class));
		return details.get(0);
	}

	public List<CategoryDetail> getModels() {
		List<CategoryDetail> details = new ArrayList();
		String baseQuery = "select * FROM models";
		details = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(CategoryDetail.class));
		return details;
	}

	public Integer updateModel(CategoryDetail md) {
		String baseQuery = "UPDATE model set modelName = ?,brandId=?,category = ?,price = ? ,color = ? WHERE modelId = ?";
		Object[] params = new Object[] { md.getModelName(),md.getBrandId(),md.getCategory(),md.getPrice(),md.getColor(),md.getModelId() };
		return jdbcTemplate.update(baseQuery, params);
	}

	public Integer deleteModel(Long id) {
		String baseQuery = "DELETE model brands WHERE modelId = ?";
		Object[] params = new Object[] { id };
		return jdbcTemplate.update(baseQuery, params);
	}
}
