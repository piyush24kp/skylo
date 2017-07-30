package com.gb.controller;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gb.model.SellDetail;
import com.gb.service.impl.BillingServiceImpl;
import com.gb.vo.ResponceData;
import com.gb.vo.SellDetailVo;
import com.gb.vo.SellDetailVo2;

@Controller
@RequestMapping(value="/billing")
public class billingController {
	
	@Autowired
	BillingServiceImpl billingServiceImpl;
	
	
	@RequestMapping(value = "/getSellOrders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <ResponceData> getOrders(){
		ResponceData responce = new ResponceData();
		List<SellDetailVo> detail  = billingServiceImpl.getSellOrders(); 
		if (detail.size()>0)
    	{	
			responce.setDatabean(detail);
			responce.setMessage("Success");
    		return new ResponseEntity<ResponceData>(responce, HttpStatus.OK);	
		}
		responce.setMessage("error");
		responce.setError("NO_CONTENT_FOUND");
    	return new ResponseEntity<ResponceData>(responce, HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/setSellOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <ResponceData> setSellOrder(@RequestBody SellDetailVo2 sellDetail){
		ResponceData responce = new ResponceData();
		SellDetailVo detail  = billingServiceImpl.setSellOrder(sellDetail); 
		if (detail != null)
    	{	
			responce.setDatabean(detail);
			responce.setMessage("Success");
    		return new ResponseEntity<ResponceData>(responce, HttpStatus.OK);	
		}
		responce.setMessage("error");
		responce.setError("NO_CONTENT_FOUND");
    	return new ResponseEntity<ResponceData>(responce, HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/updateSellOrder", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <ResponceData> updateSellOrder(@RequestBody SellDetailVo2 sellDetail){
		ResponceData responce = new ResponceData();
		boolean flag = billingServiceImpl.updateSellOrder(sellDetail); 
		if (flag)
    	{	
			responce.setMessage("Successfully updated");
    		return new ResponseEntity<ResponceData>(responce, HttpStatus.OK);	
		}
		responce.setMessage("error");
		responce.setError("NO_CONTENT_FOUND");
    	return new ResponseEntity<ResponceData>(responce, HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/deleteSellOrder", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponceData> deleteSellOrder(@QueryParam("id")Long id){
		ResponceData responce = new ResponceData();
		if (billingServiceImpl.deleteSellOrder(id))
    	{
			responce.setMessage("Deleted Succesfully.");
    		return new ResponseEntity<ResponceData>(responce, HttpStatus.OK);	
		}
		responce.setMessage("error");
    	return new ResponseEntity<ResponceData>(responce, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/changePaymentStatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponceData> returnSellOrder(@QueryParam("id") Long id,@QueryParam("t") String t,@QueryParam("p") Long p){
		ResponceData responce = new ResponceData();
		if (billingServiceImpl.changePaymentStatus(id,t,p))
    	{
			responce.setMessage("Status change Succesfully.");
    		return new ResponseEntity<ResponceData>(responce, HttpStatus.OK);	
		}
		responce.setMessage("error");
    	return new ResponseEntity<ResponceData>(responce, HttpStatus.BAD_REQUEST);
	}
	
}
