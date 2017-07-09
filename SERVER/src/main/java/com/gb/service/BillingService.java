package com.gb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gb.model.SellDetail;
import com.gb.vo.SellDetailVo;

public interface BillingService {

	public void genrateBill();
	public List<SellDetailVo> getSellOrders();
	public boolean updateSellOrder(SellDetail sellDetail);
	public boolean deleteSellOrder(Long sellOrderId);
	public boolean returnSellOrder(SellDetail detail);
	
}
