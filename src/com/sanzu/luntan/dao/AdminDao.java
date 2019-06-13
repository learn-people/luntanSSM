package com.sanzu.luntan.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sanzu.luntan.pojo.Admin;

public class AdminDao extends SqlSessionDaoSupport{
	//管理员登录
	public Admin logInAdmin(Admin a) {
		return this.getSqlSession().selectOne("com.sanzu.luntan.mapper.AdminMapper.logInAdmin", a);
	}
	//查找单个
	public Admin selectAdmin(int id) {
		return this.getSqlSession().selectOne("com.sanzu.luntan.mapper.AdminMapper.findAdminById",id);
	}
	//查找全部
	public List<Admin> selectAll(){
		return this.getSqlSession().selectList("com.sanzu.luntan.mapper.AdminMapper.queryAdminAll");
	}
	//插入数据
	public int insertAdmin(Admin a) {
		return this.getSqlSession().insert("com.sanzu.luntan.mapper.AdminMapper.insertAdmin", a);
	}
	//更新密码
	public int updateAdmin(Admin a) {
		return this.getSqlSession().update("com.sanzu.luntan.mapper.AdminMapper.updateAdmin", a);
	}
	//更新个人信息
	public int updateAdminInfo(Admin admin) {
		return this.getSqlSession().update("com.sanzu.luntan.mapper.AdminMapper.updateAdminInfo" ,admin);
	}
	//删除单个信息
	public int deleteOneAdmin(int id) {
		return this.getSqlSession().delete("com.sanzu.luntan.mapper.AdminMapper.deleteAdminOne",id);
	}
	//批量删除
	public int deleteAdmin(List<Integer> ids) {
		return this.getSqlSession().delete("com.sanzu.luntan.mapper.AdminMapper.deleteAdminAll", ids);
	}
	//校验名称是否可用
	public int checkAdminName(String adminName) {
		return this.getSqlSession().selectOne("com.sanzu.luntan.mapper.AdminMapper.checkAdminName",adminName);
	}
	//校验账号是否可用
	public int checkAdminNumber(String adminNumber) {
		return this.getSqlSession().selectOne("com.sanzu.luntan.mapper.AdminMapper.checkAdminNumber",adminNumber);
	}
}
