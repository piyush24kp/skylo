package com.gb.controller;

import java.io.FileOutputStream;
import java.sql.Date;

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

import com.gb.service.impl.ReviewServiceImpl;
import com.gb.vo.ExcelDetailVo;
import com.gb.vo.ResponceData;
import com.gb.vo.chartDetailVo;

@Controller
public class ReviewController {

	@Autowired
	ReviewServiceImpl reviewServiceImpl;
	
    @RequestMapping(value="/myexcel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponceData> genrateExcel(@RequestBody ExcelDetailVo excelDetailVo){
    	
    	ResponceData responce = new  ResponceData();
    	
    	reviewServiceImpl.genrateExcel(excelDetailVo.getType(),excelDetailVo.getFrom(),excelDetailVo.getTo());

    	return new ResponseEntity<ResponceData>(responce, HttpStatus.OK);
     
    }
    
    @RequestMapping(value="/getChartDetail", method=RequestMethod.GET)
    public ResponseEntity<ResponceData> getChartDetail(){
    	
    	ResponceData responce = new  ResponceData();
    	
    	chartDetailVo vo =  reviewServiceImpl.getChartDetail();
    	
    	responce.setDatabean(vo);

    	return new ResponseEntity<ResponceData>(responce, HttpStatus.OK);
     
    }
    
}