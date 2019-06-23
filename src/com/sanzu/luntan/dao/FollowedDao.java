package com.sanzu.luntan.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sanzu.luntan.pojo.Followed;
import com.sanzu.luntan.pojo.User;


public class FollowedDao extends SqlSessionDaoSupport{
	//查找全部
	public List<Followed> selectAll(){
		return this.getSqlSession().selectList("com.sanzu.luntan.mapper.FollowedMapper.queryFollowedAll");
	}
	//添加关注信息
	public int insertFollowed(Followed f) {
		return this.getSqlSession().insert("com.sanzu.luntan.mapper.FollowedMapper.insertFollowed" , f);
	}
	//删除关注信息
	public int deleteFollowed(Followed f) {
		return this.getSqlSession().delete("com.sanzu.luntan.mapper.FollowedMapper.deleteFollowedOne", f);
	}
	//查找单个用户的全部粉丝列表
	public List<User> select(int userId) {
		return this.getSqlSession().selectList("com.sanzu.luntan.mapper.FollowedMapper.findFollowedOne",userId);
	}
}
