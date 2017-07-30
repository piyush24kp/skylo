package com.gb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gb.model.SellDetail;
import com.gb.vo.SellDetailVo;
import com.gb.vo.SellDetailVo2;

public interface BillingService {

	public void genrateBill();
	public List<SellDetailVo> getSellOrders();
	public boolean deleteSellOrder(Long sellOrderId);
	public boolean updateSellOrder(SellDetailVo2 sellDetail);
	public boolean changePaymentStatus(Long id, String t, Long p);
}
