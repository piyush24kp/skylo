package com.gb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gb.model.LoginDetail;
import com.gb.vo.LoginDetailVo;

public interface UserService {

	public void login();
	public List<LoginDetail> login(LoginDetail loginDetail);
	public String changePassword(LoginDetailVo loginDetail);
	
}
