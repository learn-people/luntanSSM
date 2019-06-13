package com.sanzu.luntan.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sanzu.luntan.pojo.Userdetail;


public class UserdetailDao extends SqlSessionDaoSupport{
//查找单个
	public Userdetail selectUserdetail(int id) {
		return this.getSqlSession().selectOne("com.sanzu.luntan.mapper.UserdetailMapper.findUserdetailById",id);
	}
	//查找全部
	public List<Userdetail> selectAll(){
		return this.getSqlSession().selectList("com.sanzu.luntan.mapper.UserdetailMapper.queryUserdetailAll");
	}
	//从用户表中获取数据
	public int insertUserdetail(Userdetail u) {
		return this.getSqlSession().insert("com.sanzu.luntan.mapper.UserdetailMapper.insertUserdetail", u);
	}
	//更新个人信息
	public int updateUserdetailInfo(Userdetail userdetail) {
		return this.getSqlSession().update("com.sanzu.luntan.mapper.UserdetailMapper.updateUserdetailInfo" ,userdetail);
	}
	//删除单个信息
	public int deleteOneUserdetail(int id) {
		return this.getSqlSession().delete("com.sanzu.luntan.mapper.UserdetailMapper.deleteUserdetailOne",id);
	}
	//批量删除
	public int deleteUserdetail(List<Integer> ids) {
		return this.getSqlSession().delete("com.sanzu.luntan.mapper.UserdetailMapper.deleteUserdetailAll", ids);
	}
}
