package com.sanzu.luntan.pojo;

//贴子表
public class Post {
	int id; //id
	String userNum; //用户表id
	int sectionId; //板块id
	String postTitle; //标题
	String postContent;  //内容
	int likeNum; //点赞数
	int lookNum; //观看人数
	int commentNum;  //收藏数
	String postTime; //发表时间
	String imgUrl; //图片地址
	int purview;  //阅读权限
	int status;   //状态
	String userName; //用户名
	String sectionName; // 板块名
	
	User user;  //用户表
	Section section; //板块表
	
	public int getLookNum() {
		return lookNum;
	}
	public void setLookNum(int lookNum) {
		this.lookNum = lookNum;
	}
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
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
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
	public int getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public String getPostTime() {
		return postTime;
	}
	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public int getPurview() {
		return purview;
	}
	public void setPurview(int purview) {
		this.purview = purview;
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
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", userNum=" + userNum + ", sectionId=" + sectionId + ", postTitle=" + postTitle
		    + ", postContent=" + postContent + ", likeNum=" + likeNum + ", lookNum=" + lookNum + ", commentNum="
		    + commentNum + ", postTime=" + postTime + ", imgUrl=" + imgUrl + ", purview=" + purview + ", status=" + status
		    + ", userName=" + userName + ", sectionName=" + sectionName + ", user=" + user + ", section=" + section + "]";
	}
	
	
}
