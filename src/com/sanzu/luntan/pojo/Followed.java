package com.sanzu.luntan.pojo;

//关注表
public class Followed {
	int id;  //id
	int userId;  //用户id 
	int followedUserId; //关注者
	int status;   //关注状态
	
	User user;

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
	
	
}
