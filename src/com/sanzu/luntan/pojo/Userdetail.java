package com.sanzu.luntan.pojo;

//用户详情表
public class Userdetail {
	int id;  //主键 
	String userNum; //关联的用户账号
	String gender;  //性别
	String birthday; //出生日期
	String autography;  //签名
	String school;  //学校
	String job;   //职业
	String hometown;  //家乡
	
	User user;  //关联的user表
	
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAutography() {
		return autography;
	}
	public void setAutography(String autography) {
		this.autography = autography;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	
}
