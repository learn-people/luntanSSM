package com.sanzu.luntan.pojo;

//粉丝表
public class Fans {
	int id;
	int userId; //用户id
	int followedId; //粉丝
	int status;  //关注状态
	
	User user;
	Followed followed;
	
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
	public int getFollowedId() {
		return followedId;
	}
	public void setFollowedId(int followedId) {
		this.followedId = followedId;
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
	public Followed getFollowed() {
		return followed;
	}
	public void setFollowed(Followed followed) {
		this.followed = followed;
	}
	
	
}
