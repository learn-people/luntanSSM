package com.sanzu.luntan.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sanzu.luntan.pojo.User;


public class UserDao extends SqlSessionDaoSupport{
	//提供增删改查的方法
	//登录
	public User logInUser(User u) {
		return this.getSqlSession().selectOne("com.sanzu.luntan.mapper.UserMapper.logInUser",u);
	}
	//查找单个
	public User selectUser(int id) {
		return this.getSqlSession().selectOne("com.sanzu.luntan.mapper.UserMapper.findUserById",id);
	}
	//查找全部
	public List<User> selectAll(){
		return this.getSqlSession().selectList("com.sanzu.luntan.mapper.UserMapper.queryUserAll");
	}
	//插入数据
	public int insertUser(User u) {
		return this.getSqlSession().insert("com.sanzu.luntan.mapper.UserMapper.insertUser", u);
	}
	//更新密码
	public int updateUser(User u) {
		return this.getSqlSession().update("com.sanzu.luntan.mapper.UserMapper.updateUser", u);
	}
	//更新个人信息
	public int updateUserInfo(User user) {
		return this.getSqlSession().update("com.sanzu.luntan.mapper.UserMapper.updateUserInfo", user);
	}
	//删除单个信息
	public int deleteOneUser(int id) {
		return this.getSqlSession().delete("com.sanzu.luntan.mapper.UserMapper.deleteUserOne",id);
	}
	//批量删除
	public int deleteUser(List<Integer> nos) {
		return this.getSqlSession().delete("com.sanzu.luntan.mapper.UserMapper.deleteUserAll", nos);
	}
	//校验名称是否可用
	public int checkUserName(String userName) {
		return this.getSqlSession().selectOne("com.sanzu.luntan.mapper.UserMapper.checkUserName",userName);
	}
	//校验账号是否可用
	public int checkUserNumber(String userNumber) {
		return this.getSqlSession().selectOne("com.sanzu.luntan.mapper.UserMapper.checkUserNumber", userNumber);
	}
}
