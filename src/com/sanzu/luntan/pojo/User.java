package com.sanzu.luntan.pojo;

import javax.validation.constraints.Pattern;

//用户表
public class User {
	int id;
	
	@Pattern(regexp="(^\\d{3,11}$)",message="账号必须是3-11位的数字")
	String userNumber; //账号
	
	String userPassword; //密码
	
	@Pattern(regexp="(^[a-zA-Z0-9_-]{5,16}$)|(^[\\u2E80-\\u9FFF]{2,10})",message="用户名必须是5-16位数字和字母的组合或者2-10位中文")
	String userName;  //用户名
	
	int grade;    //用户等级
	int exp;     //经验
	int jurisdiction;   //权限级别
	int followsNum;    //关注数
	int fansNum;  //粉丝数
	int postsNum;  //贴子数
	String imgUrl;

	
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getImgUrl() {
		return imgUrl;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(int jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public int getFollowsNum() {
		return followsNum;
	}

	public void setFollowsNum(int followsNum) {
		this.followsNum = followsNum;
	}

	public int getFansNum() {
		return fansNum;
	}

	public void setFansNum(int fansNum) {
		this.fansNum = fansNum;
	}

	public int getPostsNum() {
		return postsNum;
	}

	public void setPostsNum(int postsNum) {
		this.postsNum = postsNum;
	}

	public User(int id, String userNumber, String userPassword, String userName, int grade, int exp, int jurisdiction,
			int followsNum, int fansNum, int postsNum, String imgUrl) {
		super();
		this.id = id;
		this.userNumber = userNumber;
		this.userPassword = userPassword;
		this.userName = userName;
		this.grade = grade;
		this.exp = exp;
		this.jurisdiction = jurisdiction;
		this.followsNum = followsNum;
		this.fansNum = fansNum;
		this.postsNum = postsNum;
		this.imgUrl = imgUrl;
	}

	public User() {
		super();
	}
	
	
}
