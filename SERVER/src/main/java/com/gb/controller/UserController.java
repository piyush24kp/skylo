package com.gb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gb.model.LoginDetail;
import com.gb.service.impl.UserServiceImpl;
import com.gb.utility.EmailServiceImpl;
import com.gb.vo.LoginDetailVo;
import com.gb.vo.ResponceData;

@RestController  
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;
	
	@Autowired
	EmailServiceImpl emailServiceImpl;
	
	@RequestMapping("/")
	public ModelAndView hello(){
		return new ModelAndView("index.html");
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponceData> updateOrder(@RequestBody LoginDetail loginDetail) { 
		List<LoginDetail> detail = userServiceImpl.login(loginDetail);
		ResponceData responce = new ResponceData();
		if(detail.size()>0){
			responce.setDatabean(detail);
			responce.setMessage("Success");
			return new ResponseEntity<ResponceData>(responce, HttpStatus.OK); 
		}
		responce.setMessage("error");
		responce.setError("Wrong UsernName/Password.");
		return new ResponseEntity<ResponceData>(responce, HttpStatus.NO_CONTENT); 
    }
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponceData> changePassword(@RequestBody LoginDetailVo detailVo) { 
		ResponceData responce = new ResponceData();
		String st = userServiceImpl.changePassword(detailVo);
		if(st.equals("Password Successfully Changed")){
			responce.setMessage("Password Successfully Changed");
			return new ResponseEntity<ResponceData>(responce, HttpStatus.OK); 
		}
		responce.setMessage(st);
		responce.setError("Error");
		return new ResponseEntity<ResponceData>(responce, HttpStatus.BAD_REQUEST); 
    }
	
	@RequestMapping(value = "/sendMail", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ResponceData> sendMail(){
		ResponceData responce = new ResponceData();
		emailServiceImpl.sendSimpleMessage("dev.piyush12@gmail.com","test","test");
		return new ResponseEntity<ResponceData>(responce, HttpStatus.OK); 
	}
}
