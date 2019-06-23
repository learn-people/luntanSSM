package com.sanzu.luntan.pojo;

public class Praise {
	int id;
	String userNum; //用户账号
	int postId;   //贴子id
	int state; //点赞状态
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "praise [id=" + id + ", userNum=" + userNum + ", postId=" + postId + ", state=" + state + "]";
	}
	
	
	
}
