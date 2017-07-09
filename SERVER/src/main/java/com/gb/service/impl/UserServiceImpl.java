package com.gb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.model.LoginDetail;
import com.gb.model.userDetails;
import com.gb.repository.UserRepository;
import com.gb.service.UserService;
import com.gb.vo.LoginDetailVo;
import com.gb.vo.UserDetailsVo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository ;

	@Override
	public void login() {
		// TODO Auto-generated method stub
		
	}

	public List<LoginDetail> login(LoginDetail loginDetail) {
		return userRepository.login(loginDetail);
	}
	public String changePassword(LoginDetailVo loginDetail){
		String st = ""; 
		LoginDetail login = new LoginDetail();
		login.setUserId(loginDetail.getUserId());
		login.setPassword(loginDetail.getOldPassword());
		List<LoginDetail> user = userRepository.login(login);
		if(user.size()>0){
			if(userRepository.changePassword(loginDetail).equals(1)){
				return "Password Successfully Changed";
			}else{
				return "Error While changing Password";
			}
		}
		return "Wrong Username / Password";
	}

}
