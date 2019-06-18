package com.sanzu.luntan.pojo;

//关注表
public class Followed {
	int id;  //id
	int userId;  //用户id 
	String userName; //用户名称
	String userNumber; //用户账号
	String gender; //用户性别
	String imgUrl;  //用户头像
	int followedUserId; //关注者
	int status;   //关注状态
	
	User user;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

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

	public int getFollowedUserId() {
		return followedUserId;
	}

	public void setFollowedUserId(int followedUserId) {
		this.followedUserId = followedUserId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFollowedUser() {
		return followedUserId;
	}

	public void setFollowedUser(int followedUserId) {
		this.followedUserId = followedUserId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Followed [id=" + id + ", userId=" + userId + ", userName=" + userName + ", userNumber=" + userNumber
				+ ", gender=" + gender + ", imgUrl=" + imgUrl + ", followedUserId=" + followedUserId + ", status="
				+ status + ", user=" + user + "]";
	}
	
	
}
