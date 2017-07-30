package com.gb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SizeDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sizeId")
	private Long sizeId;
	
	@Column(name="s")
	private Long s;
	
	@Column(name="m")
	private Long m;
	
	@Column(name="l")
	private Long l;
	
	@Column(name="xl")
	private Long xl;
	
	@Column(name="xxl")
	private Long xxl;
	
	@Column(name="xxxl")
	private Long xxxl;

	public Long getSizeId() {
		return sizeId;
	}

	public void setSizeId(Long sizeId) {
		this.sizeId = sizeId;
	}

	public Long getS() {
		return s;
	}

	public void setS(Long s) {
		this.s = s;
	}

	public Long getM() {
		return m;
	}

	public void setM(Long m) {
		this.m = m;
	}

	public Long getL() {
		return l;
	}

	public void setL(Long l) {
		this.l = l;
	}

	public Long getXl() {
		return xl;
	}

	public void setXl(Long xl) {
		this.xl = xl;
	}

	public Long getXxl() {
		return xxl;
	}

	public void setXxl(Long xxl) {
		this.xxl = xxl;
	}

	public Long getXxxl() {
		return xxxl;
	}

	public void setXxxl(Long xxxl) {
		this.xxxl = xxxl;
	}
	
}
