package com.sanzu.luntan.pojo;

//用户详情表
public class Userdetail {
	int id;  //主键 
	String userNum; //关联的用户账号
	String autography;  //签名
	String school;  //学校
	String job;   //职业
	String hometown;  //家乡
	String birthday;  //生日
	String gender;  //性别
	String userName;
	String userNumber;
	
	User user;  //关联的user表
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
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
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "Userdetail [id=" + id + ", userNum=" + userNum + ", autography=" + autography + ", school=" + school
				+ ", job=" + job + ", hometown=" + hometown + ", birthday=" + birthday + ", gender=" + gender
				+ ", userName=" + userName + ", userNumber=" + userNumber + ", user=" + user + "]";
	}
	
	
	
}
