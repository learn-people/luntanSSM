package com.sanzu.luntan.pojo;

import javax.validation.constraints.Pattern;

//管理员表
public class Admin {
	int id;
	
	@Pattern(regexp="(^\\d{3,11}$)",message="账号必须是3-11位的数字")
	String adminNumber;
	
	String adminPassword;
	/***
  //jsr303校验规则
  //@Pattern(regexp="(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})",message="")  
	 * 这里的 \u3333 [3333是为了不报错才添加的]
	 * java表达式是认识的 但是严格点还是要 \\u
	 ***/
	@Pattern(regexp="(^[a-zA-Z0-9_-]{5,16}$)|(^[\\u2E80-\\u9FFF]{2,10})",message="用户名必须是5-16位数字和字母的组合或者2-10位中文")
	String adminName;
	
	String adminBirthday;
	String imgUrl;
	String position;
	int adminGrade;
	int adminExp;
	int adminJurisdiction;
	int adminHandleNum;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdminNumber() {
		return adminNumber;
	}
	public void setAdminNumber(String adminNumber) {
		this.adminNumber = adminNumber;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminBirthday() {
		return adminBirthday;
	}
	public void setAdminBirthday(String adminBirthday) {
		this.adminBirthday = adminBirthday;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getAdminGrade() {
		return adminGrade;
	}
	public void setAdminGrade(int adminGrade) {
		this.adminGrade = adminGrade;
	}
	public int getAdminExp() {
		return adminExp;
	}
	public void setAdminExp(int adminExp) {
		this.adminExp = adminExp;
	}
	public int getAdminJurisdiction() {
		return adminJurisdiction;
	}
	public void setAdminJurisdiction(int adminJurisdiction) {
		this.adminJurisdiction = adminJurisdiction;
	}
	public int getAdminHandleNum() {
		return adminHandleNum;
	}
	public void setAdminHandleNum(int adminHandleNum) {
		this.adminHandleNum = adminHandleNum;
	}
	
	
}
