package com.sanzu.luntan.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sanzu.luntan.pojo.Post;

public class PostDao extends SqlSessionDaoSupport {
	//查找单个
	public Post selectPost(int id) {
		return this.getSqlSession().selectOne("com.sanzu.luntan.mapper.PostMapper.findPostById",id);
	}
	//查找全部
	public List<Post> selectAll(){
		return this.getSqlSession().selectList("com.sanzu.luntan.mapper.PostMapper.queryPostAll");
	}
	//查找指定板块的全部
	public List<Post> select(int sectionId){
		return this.getSqlSession().selectList("com.sanzu.luntan.mapper.PostMapper.queryPost",sectionId);
	}
	//添加贴子
	public int insertPost(Post p) {
		return this.getSqlSession().insert("com.sanzu.luntan.mapper.PostMapper.insertPost", p);
	}
	//更新贴子信息
	public int updatePost(Post p) {
		return this.getSqlSession().update("com.sanzu.luntan.mapper.PostMapper.updatePostInfo", p);
	}
	//更新贴子观看数
	public int updateLook(int id) {
		return this.getSqlSession().update("com.sanzu.luntan.mapper.PostMapper.updateLookNum", id);
	}
	//更新贴子点赞数，增加
	public int updateLikeAdd() {
		return this.getSqlSession().update("com.sanzu.luntan.mapper.PostMapper.updateLikeNumAdd");
	}
	//更新评论数
	public int updatePurview() {
		return this.getSqlSession().update("com.sanzu.luntan.mapper.PostMapper.updatePurview");
	}
	//删除单个信息
	public int deleteOnePost(int id) {
		return this.getSqlSession().delete("com.sanzu.luntan.mapper.PostMapper.deletePostOne",id);
	}
	//批量删除
	public int deletePost(List<Integer> ids) {
		return this.getSqlSession().delete("com.sanzu.luntan.mapper.PostMapper.deletePostAll", ids);
	}
}
