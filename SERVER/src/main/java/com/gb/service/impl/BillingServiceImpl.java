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
import com.gb.model.SellDetail;
import com.gb.model.SizeDetail;
import com.gb.repository.BillingRepository;
import com.gb.repository.OrderRepository;
import com.gb.service.BillingService;
import com.gb.vo.AllModelsVo;
import com.gb.vo.BrandDetailVo;
import com.gb.vo.SellDetailVo;
import com.gb.vo.SellDetailVo2;
import com.gb.vo.SizeDetailVo;

@Service
public class BillingServiceImpl implements BillingService{

	@Autowired
	BillingRepository billingRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public void genrateBill() {
		// TODO Auto-generated method stub
		
	}

	public List<SellDetailVo> getSellOrders() {
		List<SellDetailVo> sellDetailList = new ArrayList<SellDetailVo>();
		List<SellDetail> sellDetails = billingRepository.getSellOrders();
		for(SellDetail sellDetail: sellDetails){
			SellDetailVo detailVo = new SellDetailVo();
			detailVo = parseSellDetail(detailVo, sellDetail);
			sellDetailList.add(detailVo);
		}
		return sellDetailList;
	}public List<SellDetailVo> getSellOrdersByDate(Date from, Date to) {
		List<SellDetailVo> sellDetailList = new ArrayList<SellDetailVo>();
		List<SellDetail> sellDetails = billingRepository.getSellOrdersByDate(from,to);
		for(SellDetail sellDetail: sellDetails){
			SellDetailVo detailVo = new SellDetailVo();
			detailVo = parseSellDetail(detailVo, sellDetail);
			sellDetailList.add(detailVo);
		}
		return sellDetailList;
	}
	
	
	
	public SellDetailVo parseSellDetail(SellDetailVo detailVo,SellDetail detail ){
		detailVo.setOrderId(detail.getOrderId());
		detailVo.setCustomerName(detail.getCustomerName());
		detailVo.setContantNo(detail.getContantNo());
		detailVo.setInvoiceNo(detail.getInvoiceNo());
		detailVo.setAddress(detail.getAddress());
		detailVo.setAmount(detail.getAmount());
		detailVo.setSellDate(detail.getSellDate());
		detailVo.setSaleType(detail.getSaleType());
		detailVo.setPayment(detail.getPayment());
		detailVo.setReturnDate(detail.getReturnDate());
		detailVo.setAmountPaid(detail.getAmountPaid());
		
		BrandDetail bd = orderRepository.getBrandById(Long.parseLong(detail.getBrand()));
		BrandDetailVo bdv = new BrandDetailVo();
		bdv.setBrandId(bd.getBrandId());
		bdv.setBrandName(bd.getBrandName());
		detailVo.setBrand(bdv);
		
		CategoryDetail md = orderRepository.getModelById(Long.parseLong(detail.getModel()));
		AllModelsVo amv = new AllModelsVo();
		amv.setModelId(md.getModelId());
		amv.setModelName(md.getModelName() +"-"+md.getCategory());
		detailVo.setModel(amv);
		
		SizeDetail sd = orderRepository.getSizeById(detail.getSize());
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

	public SellDetailVo setSellOrder(SellDetailVo2 sellDetail) {
		
		SellDetailVo sellDetailVo =  new SellDetailVo();
		
		List<OrderDetail> odList = orderRepository.getOrderByBrandAndModel(sellDetail.getBrand(),sellDetail.getModel());
		if(odList.size()>0){
			OrderDetail detail = odList.get(0);
			SizeDetail sd  = orderRepository.getSizeById(detail.getSize());
			SizeDetail sdTmp = sellDetail.getSize();
			sd.setS(sd.getS() - sdTmp.getS());
			sd.setM(sd.getM() - sdTmp.getM());
			sd.setL(sd.getL() - sdTmp.getL());
			sd.setXl(sd.getXl() - sdTmp.getXl());
			sd.setXxl(sd.getXxl() - sdTmp.getXxl());
			sd.setXxxl(sd.getXxxl() - sdTmp.getXxxl());
			
			SizeDetail sdTemp2 = new SizeDetail();
			
			if(orderRepository.updateSize(sd) == 1){
				String invoiceNo = "S07";
				Long millis = System.currentTimeMillis() / 1000L;
				invoiceNo = invoiceNo + "-" + millis;
				sellDetail.setInvoiceNo(invoiceNo);
				if(orderRepository.createSize(sdTmp) == 1){
					SizeDetail sdtemp = orderRepository.getSizeLastUpdated();
					sdTemp2.setSizeId(sdtemp.getSizeId());
					sellDetail.setSize(sdTemp2);
				}
				if(sellDetail.getPayment().equalsIgnoreCase("paid")){
					sellDetail.setAmountPaid(sellDetail.getAmount());
				}else{
					sellDetail.setAmountPaid(0l);
				}
				billingRepository.setSellOrder(sellDetail);
			}
			
			SellDetail sDetail= billingRepository.getSellOrderByInvoiceId(sellDetail.getInvoiceNo());
			parseSellDetail(sellDetailVo, sDetail);
			HistoryDetail hd =new HistoryDetail();
			hd.setHistoryType("SELL");
			hd.setAmount(sellDetail.getAmountPaid());
			hd.setSize(sdTemp2.getSizeId());
			Long orderId = sDetail.getOrderId();
			hd.setProductDetail(orderId.toString());
			hd.setCreatedDate(new Date(Calendar.getInstance().getTimeInMillis()));

			orderRepository.createHistory(hd);
		}
		return sellDetailVo;
	}
	
	@Override
	public boolean updateSellOrder(SellDetailVo2 sellDetail) {
		
		if(sellDetail.getPayment().equals("Paid") || sellDetail.getPayment().equals("Due")){
			sellDetail.setReturnDate(null);
		}else{
			 Calendar cal = Calendar.getInstance();
			 Date sqldate = new Date((cal.getTime()).getTime());
			 sellDetail.setReturnDate(sqldate);
		}
		if(orderRepository.updateSize(sellDetail.getSize()) == 1){
			Integer no = billingRepository.updateSellOrder(sellDetail);
			if(no.equals(1)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean deleteSellOrder(Long sellOrderId) {
		Integer no = billingRepository.deleteSellOrder(sellOrderId);
		orderRepository.updateHistory(sellOrderId);
		if(no.equals(1)){
			return true;
		}
		return false;
	}


	public SellDetailVo getSellOrderById(String invoiceNo) {
		SellDetail sd = billingRepository.getSellOrdersById(invoiceNo);
		SellDetailVo sdv = new SellDetailVo();
		 sdv = parseSellDetail(sdv, sd);
		return sdv;
	}

	public boolean changePaymentStatus(Long id, String t, Long p) {
		
		SellDetail sd = billingRepository.getSellOrdersById(id.toString());
		p = p + sd.getAmountPaid();
		
		if(billingRepository.changePaymentStatus(id,t,p) == 1){
			
			HistoryDetail hd =new HistoryDetail();
			hd.setHistoryType("SELL");
			hd.setAmount(p);
			hd.setSize(sd.getSize());
			Long orderId = sd.getOrderId();
			hd.setProductDetail(orderId.toString());
			hd.setCreatedDate(new Date(Calendar.getInstance().getTimeInMillis()));
			orderRepository.createHistory(hd);
			
			return true;
		}
		return false;
	}

}
