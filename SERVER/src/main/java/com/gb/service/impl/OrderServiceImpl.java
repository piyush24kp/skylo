package com.gb.service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.model.BrandDetail;
import com.gb.model.CategoryDetail;
import com.gb.model.HistoryDetail;
import com.gb.model.OrderDetail;
import com.gb.model.SizeDetail;
import com.gb.model.SupplierDetail;
import com.gb.repository.OrderRepository;
import com.gb.service.OrderService;
import com.gb.vo.AllModelsVo;
import com.gb.vo.AllSuppliersDetailVo;
import com.gb.vo.BrandDetailVo;
import com.gb.vo.CategoryDetailVo;
import com.gb.vo.OrderDetailVo;
import com.gb.vo.OrderDetailVo2;
import com.gb.vo.Page;
import com.gb.vo.SizeDetailVo;
import com.gb.vo.SupplierDetailVo;
import com.mysql.fabric.xmlrpc.base.Array;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public List<OrderDetailVo> getOrders(Integer size, Integer page) {
		List<OrderDetailVo> orderDetailVo = new ArrayList<OrderDetailVo>();
		List<OrderDetail> orderDetail = orderRepository.getOrders(size,page);
		for(OrderDetail order : orderDetail){
			OrderDetailVo detailVo = new OrderDetailVo();
			detailVo = parseOrder(order,detailVo);
			orderDetailVo.add(detailVo);
		}
		return orderDetailVo;
	}

	@Override
	public OrderDetailVo createOrder(OrderDetailVo2 odDetailVo) {
		Long orderId = odDetailVo.getOrderId();
		OrderDetailVo detailVo =  new OrderDetailVo();
		
		try{
		if(orderId == null){
			Long millis = System.currentTimeMillis() / 1000L;
			odDetailVo.setOrderId(millis);
			orderId = millis;
		}
		
		/*if(orderRepository.createSize(odDetailVo.getSize())  == 1){
			SizeDetail sd = orderRepository.getSizeLastUpdated();
			
			odDetailVo.setSize(sd);
			Integer no = orderRepository.createOrder(odDetailVo);
			if(no.equals(1)){
				OrderDetail order = orderRepository.getOrders(orderId);
				detailVo = parseOrder(order,detailVo);
			}
		}*/
		
		List<OrderDetail> odList = orderRepository.getOrderByBrandAndModel(odDetailVo.getBrand(),odDetailVo.getModel());
		if(odList.size()>0){
			OrderDetail  detail = new OrderDetail();
			detail = odList.get(0);
			/*if(odDetailVo.getQuantity() !=null){
				detail.setQuantity(detail.getQuantity()+odDetailVo.getQuantity());
			}else{
				detail.setQuantity(detail.getQuantity()+1);
			}*/
			SizeDetail sd = orderRepository.getSizeById(detail.getSize());
			if(sd!=null){
				SizeDetail sdtemp = odDetailVo.getSize();
				sd.setS(sd.getS() + sdtemp.getS());
				sd.setM(sd.getM() + sdtemp.getM());
				sd.setL(sd.getL() + sdtemp.getL());
				sd.setXl(sd.getXl() + sdtemp.getXl());
				sd.setXxl(sd.getXxl() + sdtemp.getXxl());
				sd.setXxxl(sd.getXxxl() + sdtemp.getXxxl());
				
			}
			if(orderRepository.createSize(sd) == 1){
				SizeDetail sdtemp = orderRepository.getSizeLastUpdated();
				detail.setSize(sdtemp.getSizeId());
				detail.setAmount(detail.getAmount() + odDetailVo.getAmount());
				if(orderRepository.updateOrder(detail) == 1){
					OrderDetail order = orderRepository.getOrders(detail.getOrderId());
					detailVo = parseOrder(order,detailVo);
				}
			}
			
			/*if(updateOrder(detail)){
				OrderDetail order = orderRepository.getOrders(detail.getOrderId());
				detailVo = parseOrder(order,detailVo);
			}
			if(sd!=null){
				SizeDetail sdtemp = odDetailVo.getSize();
				sd.setS(sd.getS() + sdtemp.getS());
				sd.setM(sd.getM() + sdtemp.getM());
				sd.setL(sd.getL() + sdtemp.getL());
				sd.setXl(sd.getXl() + sdtemp.getXl());
				sd.setXxl(sd.getXxl() + sdtemp.getXxl());
				sd.setXxxl(sd.getXxxl() + sdtemp.getXxxl());
				
			}else{
				if(orderRepository.createSize(odDetailVo.getSize())  == 1){
					System.out.println("Size Created");
				}
			}
			if(orderRepository.createSize(odDetailVo.getSize())  == 1){
				OrderDetail order = orderRepository.getOrders(detail.getOrderId());
				detailVo = parseOrder(order,detailVo);
			}*/
			
		}else{
			/*Integer no = orderRepository.createOrder(odDetailVo);
			if(no.equals(1)){
				OrderDetail order = orderRepository.getOrders(orderId);
				detailVo = parseOrder(order,detailVo);
			}*/
			if(orderRepository.createSize(odDetailVo.getSize())  == 1){
				SizeDetail sd = orderRepository.getSizeLastUpdated();
 				
				odDetailVo.setSize(sd);
				Integer no = orderRepository.createOrder(odDetailVo);
				if(no.equals(1)){
					OrderDetail order = orderRepository.getOrders(orderId);
					detailVo = parseOrder(order,detailVo);
				}
			}
			
		}
		
		HistoryDetail hd =new HistoryDetail();
		hd.setHistoryType("STOCK");
		hd.setAmount(odDetailVo.getAmount());
		hd.setSize(detailVo.getSize().getSizeId());
		hd.setProductDetail(detailVo.getOrderId().toString());
		hd.setCreatedDate(new Date(Calendar.getInstance().getTimeInMillis()));

		orderRepository.createHistory(hd);
		
		}catch(Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
		
		return detailVo;
	}

	@Override
	public boolean updateOrder(OrderDetailVo2 orderDetail) {
		if( orderRepository.updateSize(orderDetail.getSize()) ==1){
			Integer no = orderRepository.updateOrder(orderDetail);
			if(no.equals(1)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean deleteOrder(Long orderId) {
		Integer no = orderRepository.deleteOrder(orderId);
		orderRepository.updateHistory(orderId);
		if(no.equals(1)){
			return true;
		}
		return false;
	}
	
	@Override
	public SupplierDetailVo createSupplier(SupplierDetailVo odDetailVo) {
		SupplierDetailVo detailVo = new SupplierDetailVo();
		Integer no = orderRepository.createSupplier(odDetailVo);
		if(no.equals(1)){
			SupplierDetail sd = orderRepository.getSupplierById();
			  detailVo.setSupplierId(sd.getSupplierId());
			  detailVo.setAddress(sd.getAddress());
			  detailVo.setAmountDue(sd.getAmountDue());
			  detailVo.setAmountpaid(sd.getAmountpaid());
			  detailVo.setSupplierName(sd.getSupplierName());
			  detailVo.setContactNo(sd.getContactNo());
			}
		return detailVo;
	}
	
	@Override
	public List<SupplierDetailVo> getSupplier() {
		List<SupplierDetailVo> supplierDetailVos = new ArrayList<SupplierDetailVo>();
		List<SupplierDetail> supplierDetails = orderRepository.getSupplier();
		for(SupplierDetail suDetail : supplierDetails){
			SupplierDetailVo detailVo = new SupplierDetailVo();
			detailVo.setSupplierId(suDetail.getSupplierId());
			detailVo.setSupplierName(suDetail.getSupplierName());
			detailVo.setContactNo(suDetail.getContactNo());
			detailVo.setAddress(suDetail.getAddress());
			detailVo.setAmountDue(suDetail.getAmountDue());
			detailVo.setAmountpaid(suDetail.getAmountpaid());
			supplierDetailVos.add(detailVo);
		}
		return supplierDetailVos;
	}
	
	@Override
	public boolean updateSupplier(SupplierDetail odDetailVo) {
		Integer no = orderRepository.updateSupplier(odDetailVo);
		if(no.equals(1)){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean deleteSupplier(Long supplierId) {
		Integer no = orderRepository.deleteSupplier(supplierId);
		if(no.equals(1)){
			return true;
		}
		return false;
	}
	
	@Override
	public List<AllSuppliersDetailVo> getSupplierId() {
		List<AllSuppliersDetailVo> supplierDetailVos = new ArrayList<AllSuppliersDetailVo>();
		List<SupplierDetail> supplierDetails = orderRepository.getSupplierIdAndName();
		for(SupplierDetail suDetail : supplierDetails){
			AllSuppliersDetailVo detailVo = new AllSuppliersDetailVo();
			detailVo.setSupplierId(suDetail.getSupplierId());
			detailVo.setSupplierName(suDetail.getSupplierName());
			supplierDetailVos.add(detailVo);
		}
		return supplierDetailVos;
	}
	
	@Override
	public List<BrandDetailVo> getBrands() {
		List<BrandDetailVo> brandDetailVos = new ArrayList<BrandDetailVo>();
		List<BrandDetail> brandDetails = orderRepository.getBrands();
		for(BrandDetail order : brandDetails){
			BrandDetailVo detailVo = new BrandDetailVo();
			detailVo.setBrandId(order.getBrandId());
			detailVo.setBrandName(order.getBrandName());
			brandDetailVos.add(detailVo);
		}
		return brandDetailVos;
	}
	@Override
	public BrandDetailVo createBrands(BrandDetailVo brandDetailVo) {
		BrandDetailVo detailVo = new BrandDetailVo();
		Integer no = orderRepository.createBrands(brandDetailVo);
		if(no.equals(1)){
		  BrandDetail order = orderRepository.getUpdateBrand(brandDetailVo);
			detailVo.setBrandId(order.getBrandId());
			detailVo.setBrandName(order.getBrandName());
		}
		return detailVo;
	}

	public List<AllModelsVo> getModelByBrandId(Long brandId) {
		List<AllModelsVo> model = new ArrayList<>();
		List<CategoryDetail> detailsList = orderRepository.getModelByBrandId(brandId);
		for(CategoryDetail bd : detailsList){
			AllModelsVo vo = new AllModelsVo();
			vo.setModelId(bd.getModelId());
			vo.setModelName(bd.getModelName() + "-" +bd.getCategory() );
			vo.setPrice(bd.getPrice());
			model.add(vo);
		}
		return model;
	}

	public OrderDetailVo parseOrder( OrderDetail order , OrderDetailVo detailVo){
		detailVo.setOrderId(order.getOrderId());
		detailVo.setAmount(order.getAmount());
		detailVo.setOrderName(order.getOrderName());
		//detailVo.setPurchasePrice(order.getPurchasePrice());
		detailVo.setOrderDate(order.getOrderDate());
		
		SupplierDetail sde= orderRepository.getSupplierId(order.getSuppliedBy());
		AllSuppliersDetailVo asd = new AllSuppliersDetailVo();
		asd.setSupplierId(sde.getSupplierId());
		asd.setSupplierName(sde.getSupplierName());
		detailVo.setSuppliedBy(asd);
		
		BrandDetail bd = orderRepository.getBrandById(Long.parseLong(order.getBrand()));
		BrandDetailVo bdv = new BrandDetailVo();
		bdv.setBrandId(bd.getBrandId());
		bdv.setBrandName(bd.getBrandName());
		detailVo.setBrand(bdv);
		
		CategoryDetail md = orderRepository.getModelById(Long.parseLong(order.getModel()));
		AllModelsVo amv = new AllModelsVo();
		amv.setModelId(md.getModelId());
		amv.setModelName(md.getModelName() +"-"+md.getCategory());
		detailVo.setModel(amv);
		
		SizeDetail sd = orderRepository.getSizeById(order.getSize());
		SizeDetailVo sdv = new SizeDetailVo();
		sdv.setS(sd.getS());
		sdv.setM(sd.getM());
		sdv.setL(sd.getL());
		sdv.setXl(sd.getXl());
		sdv.setXxl(sd.getXxl());
		sdv.setXxxl(sd.getXxxl());
		sdv.setSizeId(sd.getSizeId());
		detailVo.setSize(sdv);
		
		return detailVo;
	}

	public CategoryDetailVo setModel(CategoryDetail modelDetail) {
		CategoryDetailVo detailVo = new CategoryDetailVo();
		Integer no = orderRepository.setModel(modelDetail);
		if(no.equals(1)){
			CategoryDetail order = orderRepository.getUpdateModel(modelDetail);
			detailVo.setModelId(order.getModelId());
			detailVo.setModelName(order.getModelName());
			detailVo.setCategory(order.getCategory());
			detailVo.setColor(order.getColor());
			detailVo.setCategory(order.getCategory());
			BrandDetail brand = orderRepository.getBrandById(order.getBrandId());
			BrandDetailVo bdv = new BrandDetailVo();
			bdv.setBrandId(brand.getBrandId());
			bdv.setBrandName(brand.getBrandName());
			detailVo.setBrandId(bdv);
		}
		return detailVo;
	}

	public List<CategoryDetailVo> getModels() {
		List<CategoryDetailVo> model = new ArrayList<>();
		List<CategoryDetail> detailsList = orderRepository.getModels();
		for(CategoryDetail bd : detailsList){
			CategoryDetailVo vo = new CategoryDetailVo();
			vo.setModelId(bd.getModelId());
			vo.setModelName(bd.getModelName());
			vo.setPrice(bd.getPrice());
			vo.setCategory(bd.getCategory());
			vo.setColor(bd.getColor());
			
			BrandDetail brand = orderRepository.getBrandById(bd.getBrandId());
			BrandDetailVo bdv = new BrandDetailVo();
			bdv.setBrandId(brand.getBrandId());
			bdv.setBrandName(brand.getBrandName());
			vo.setBrandId(bdv);
			model.add(vo);
		}
		return model;
	}
	
	@Override
	public boolean updateBrands(BrandDetailVo brandDetailVo) {
		Integer no = orderRepository.updateBrands(brandDetailVo);
		if(no.equals(1)){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean deleteBrands(Long brandId) {
		Integer brandModels = orderRepository.deleteAllModelByBrand(brandId);
		Integer no = orderRepository.deleteBrands(brandId);
		if(no.equals(1)){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateModel(CategoryDetail modelDetail) {
		Integer no = orderRepository.updateModel(modelDetail);
		if(no.equals(1)){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean deleteModel(Long id) {
		Integer no = orderRepository.deleteModel(id);
		if(no.equals(1)){
			return true;
		}
		return false;
	}

	public List<OrderDetailVo> getOrdersByDate(Date from, Date to) {
		List<OrderDetailVo> orderDetailVo = new ArrayList<OrderDetailVo>();
		List<OrderDetail> orderDetail = orderRepository.getOrdersByDate(from,to);
		for(OrderDetail order : orderDetail){
			OrderDetailVo detailVo = new OrderDetailVo();
			detailVo = parseOrder(order,detailVo);
			orderDetailVo.add(detailVo);
		}
		return orderDetailVo;
	}

	public OrderDetailVo getOrderById(String orderId) {
		OrderDetailVo odv =new OrderDetailVo();
		OrderDetail order = orderRepository.getOrders(Long.parseLong(orderId));
		odv = parseOrder(order, odv);
		return odv;
	}
	
	public SizeDetailVo getSizeById(Long sizeId){
		SizeDetail sd = orderRepository.getSizeById(sizeId);
		SizeDetailVo sdv = new SizeDetailVo();
		sdv.setS(sd.getS());
		sdv.setM(sd.getM());
		sdv.setL(sd.getL());
		sdv.setXl(sd.getXl());
		sdv.setXxl(sd.getXxl());
		sdv.setXxxl(sd.getXxxl());
		sdv.setSizeId(sd.getSizeId());
		return sdv;
	}

	public Page getPagination(int size, int page) {
		Page p = new Page();
		int total = orderRepository.getOrderCount();
		p.setPage(page);
		p.setSize(size);
		p.setTotalPage((int) Math.ceil(total/size)+1);
		return p;
	}
}
