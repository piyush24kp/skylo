package com.gb.vo;

import java.util.List;

public class UserDetailsVo {

	private Integer uid;
	private String FirstName;
	private String MiddleName;
	private String Lastname;
	private UserDetailsVo FatherName;
	private UserDetailsVo MotherName;
	private List<UserDetailsVo> childrens;
	private String Gender;
	private UserDetailsVo spouseName;
	private List<UserDetailsVo> siblings;
	
	
	
	public UserDetailsVo getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(UserDetailsVo spouseName) {
		this.spouseName = spouseName;
	}
	public List<UserDetailsVo> getSiblings() {
		return siblings;
	}
	public void setSiblings(List<UserDetailsVo> siblings) {
		this.siblings = siblings;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getMiddleName() {
		return MiddleName;
	}
	public void setMiddleName(String middleName) {
		MiddleName = middleName;
	}
	public String getLastname() {
		return Lastname;
	}
	public void setLastname(String lastname) {
		Lastname = lastname;
	}
	public UserDetailsVo getFatherName() {
		return FatherName;
	}
	public void setFatherName(UserDetailsVo fatherName) {
		FatherName = fatherName;
	}
	public UserDetailsVo getMotherName() {
		return MotherName;
	}
	public void setMotherName(UserDetailsVo motherName) {
		MotherName = motherName;
	}
	public List<UserDetailsVo> getChildrens() {
		return childrens;
	}
	public void setChildrens(List<UserDetailsVo> childrens) {
		this.childrens = childrens;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	
	
}
