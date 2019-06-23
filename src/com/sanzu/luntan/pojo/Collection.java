package com.sanzu.luntan.pojo;

//收藏表
public class Collection {
	int id;  //id
	String userNum; //用户表id
	int postId; //贴子表id
	String userName; // 用户名
	String postTitle; //贴子标题
	String postContent; //贴子内容
	String postTime; //贴子发表时间
	int lookNum; //查看人数
	String imgUrl; //贴子图片
	int purview; //评论数
	 
	User user;  //用户表
	Post post;  //贴子表
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public String getPostTime() {
		return postTime;
	}
	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	public int getLookNum() {
		return lookNum;
	}
	public void setLookNum(int lookNum) {
		this.lookNum = lookNum;
	}
	public int getPurview() {
		return purview;
	}
	public void setPurview(int purview) {
		this.purview = purview;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	@Override
	public String toString() {
		return "Collection [id=" + id + ", userNum=" + userNum + ", postId=" + postId + ", userName=" + userName
		    + ", postTitle=" + postTitle + ", postContent=" + postContent + ", postTime=" + postTime + ", lookNum="
		    + lookNum + ", imgUrl=" + imgUrl + ", purview=" + purview + ", user=" + user + ", post=" + post + "]";
	}
}
