package com.sanzu.luntan.pojo;

//评论表
public class Comment {
	int id;  //id
	int postId; //贴子id
	String userNum; //发表评论的用户id
	String userName; //用户昵称
	String userImgUrl; //用户头像
	String commentContent; //评论内容
	int commentLikeNum;  //点赞数
	String commentTime; //评论时间
	String imgUrl;   //图片地址
	
	Post post;   //贴子表
	User user;  //用户表
	
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserImgUrl() {
		return userImgUrl;
	}
	public void setUserImgUrl(String userImgUrl) {
		this.userImgUrl = userImgUrl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public int getCommentLikeNum() {
		return commentLikeNum;
	}
	public void setCommentLikeNum(int commentLikeNum) {
		this.commentLikeNum = commentLikeNum;
	}
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Comment [id=" + id + ", postId=" + postId + ", userNum=" + userNum + ", userName=" + userName
		    + ", userImgUrl=" + userImgUrl + ", commentContent=" + commentContent + ", commentLikeNum=" + commentLikeNum
		    + ", commentTime=" + commentTime + ", imgUrl=" + imgUrl + ", post=" + post + ", user=" + user + "]";
	}
	
}
