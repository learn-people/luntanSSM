package com.sanzu.luntan.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sanzu.luntan.pojo.Praise;

public class PraiseDao extends SqlSessionDaoSupport{
	//查找单个
	public int selectPraise(Praise p) {
		return this.getSqlSession().selectOne("com.sanzu.luntan.mapper.PraiseMapper.selectPraise",p);
	}
	//添加点赞信息
	public int insertPraise(Praise p) {
		return this.getSqlSession().insert("com.sanzu.luntan.mapper.PraiseMapper.insertPraise",	p);
	}
	//删除信息
	public int deletePraise(Praise p) {
		return this.getSqlSession().delete("com.sanzu.luntan.mapper.PraiseMapper.deletePraise", p);
	}                             
}
