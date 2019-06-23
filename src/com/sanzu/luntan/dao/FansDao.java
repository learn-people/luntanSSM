package com.sanzu.luntan.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sanzu.luntan.pojo.Followed;
import com.sanzu.luntan.pojo.User;



public class FansDao extends SqlSessionDaoSupport{
	//查找全部
	public List<Followed> selectAll(){
		return this.getSqlSession().selectList("com.sanzu.luntan.mapper.FansMapper.queryFansAll");
	}
	//添加关注信息
	public int insertFollowed(Followed f) {
		return this.getSqlSession().insert("com.sanzu.luntan.mapper.FansMapper.insertFans" , f);
	}
	//删除关注信息
	public int deleteFollowed(Followed f) {
		return this.getSqlSession().delete("com.sanzu.luntan.mapper.FansMapper.deleteFansOne", f);
	}
	//查找单个用户的全部关注列表
	public List<User> select(int followedUserId) {
		return this.getSqlSession().selectList("com.sanzu.luntan.mapper.FansMapper.findFansOne",followedUserId);
	}
}
