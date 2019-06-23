package com.sanzu.luntan.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sanzu.luntan.pojo.Comment;

public class CommentDao extends SqlSessionDaoSupport{
	//查找单个
	public List<Comment> selectComment(int id) {
		return this.getSqlSession().selectList("com.sanzu.luntan.mapper.CommentMapper.findCommentById", id);
	}
	//查找全部
	public List<Comment> selectAll() {
		return this.getSqlSession().selectList("com.sanzu.luntan.mapper.CommentMapper.queryCommentAll");
	}
	//添加评论
	public int insertComment(Comment c) {
		return this.getSqlSession().insert("com.sanzu.luntan.mapper.CommentMapper.insertComment", c);
	}
	//更新评论
	public int updateComment(Comment c) {
		return this.getSqlSession().update("com.sanzu.luntan.mapper.CommentMapper.updateComment", c);
	}
	//删除评论
	public int deleteOneComment(Comment c) {
		return this.getSqlSession().delete("com.sanzu.luntan.mapper.CommentMapper.deleteCommentOne", c);
	}
	//删除评论
	public int deleteComment(int id) {
		return this.getSqlSession().delete("com.sanzu.luntan.mapper.CommentMapper.deleteComment", id);
	}
	//批量删除
	public int deleteComment(List<Integer> ids) {
		return this.getSqlSession().delete("com.sanzu.luntan.mapper.deleteCommentAll",ids);
	}

}
