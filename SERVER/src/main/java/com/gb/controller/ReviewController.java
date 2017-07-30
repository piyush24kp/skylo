package com.gb.controller;

import java.io.FileOutputStream;
import java.sql.Date;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.model.ExpenseDetails;
import com.gb.service.impl.ExpenseServiceImpl;
import com.gb.service.impl.ReviewServiceImpl;
import com.gb.vo.ExcelDetailVo;
import com.gb.vo.HistoryDetailVo;
import com.gb.vo.ResponceData;
import com.gb.vo.chartDetailVo;

@Controller
public class ReviewController {

	@Autowired
	ReviewServiceImpl reviewServiceImpl;
	
	@Autowired
	ExpenseServiceImpl expenseServiceImpl;
	
    @RequestMapping(value="/myexcel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponceData> genrateExcel(@RequestBody ExcelDetailVo excelDetailVo){
    	
    	ResponceData responce = new  ResponceData();
    	
    	reviewServiceImpl.genrateExcel(excelDetailVo.getType(),excelDetailVo.getFrom(),excelDetailVo.getTo());

    	return new ResponseEntity<ResponceData>(responce, HttpStatus.OK);
     
    }
    
    @RequestMapping(value="/getChartDetail", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponceData> getChartDetail(){
    	
    	ResponceData responce = new  ResponceData();
    	
    	chartDetailVo vo =  reviewServiceImpl.getChartDetail();
    	
    	responce.setDatabean(vo);

    	return new ResponseEntity<ResponceData>(responce, HttpStatus.OK);
     
    }
    
    @RequestMapping(value="/getExpense", method=RequestMethod.GET)
    public ResponseEntity<ResponceData> getExpense(){
    	
    	ResponceData responce = new  ResponceData();
    	
    	List<ExpenseDetails> data = expenseServiceImpl.getExpense();
    	if(data.size()>0){
    		responce.setDatabean(data);
    		return new ResponseEntity<ResponceData>(responce, HttpStatus.OK);
    	}

    	return new ResponseEntity<ResponceData>(responce, HttpStatus.NO_CONTENT);
     
    }
    
    
    @RequestMapping(value="/setExpense", method = RequestMethod.POST, produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponceData> setExpense(@RequestBody ExpenseDetails expense){
    	
    	ResponceData responce = new  ResponceData();
    	
    	if(expenseServiceImpl.setExpense(expense)){
    		responce.setMessage("Added successfully");
    		return new ResponseEntity<ResponceData>(responce, HttpStatus.OK);
    	}

    	return new ResponseEntity<ResponceData>(responce, HttpStatus.NO_CONTENT);
     
    }
    
    
    @RequestMapping(value="/getHistory", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponceData> getHistory(@RequestBody ExcelDetailVo excelDetailVo){
    	
    	ResponceData responce = new  ResponceData();
    	
    	List<HistoryDetailVo> data= reviewServiceImpl.getHistory(excelDetailVo.getFrom(),excelDetailVo.getTo(),excelDetailVo.getType());
    	
    	responce.setDatabean(data);

    	return new ResponseEntity<ResponceData>(responce, HttpStatus.OK);
     
    }
    
    @RequestMapping(value="/searchExpence", method=RequestMethod.GET)
    public ResponseEntity<ResponceData> searchExpence(@QueryParam("q")String q ){
    	
    	ResponceData responce = new  ResponceData();
    	
    	List<ExpenseDetails> data = expenseServiceImpl.searchExpence(q);
    	if(data.size()>0){
    		responce.setDatabean(data);
    		return new ResponseEntity<ResponceData>(responce, HttpStatus.OK);
    	}

    	return new ResponseEntity<ResponceData>(responce, HttpStatus.NO_CONTENT);
     
    }
    
    
    
}