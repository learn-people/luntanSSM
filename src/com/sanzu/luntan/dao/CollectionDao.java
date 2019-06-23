package com.sanzu.luntan.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sanzu.luntan.pojo.Collection;
                                              
public class CollectionDao extends SqlSessionDaoSupport{
	//查找全部
	public List<Collection> selectAll(Collection col){
		return this.getSqlSession().selectList("com.sanzu.luntan.mapper.CollectionMapper.selectAll", col);
	}
	//查找单个收藏信息
	public int selectCollection(Collection col) {
		return this.getSqlSession().selectOne("com.sanzu.luntan.mapper.CollectionMapper.selectCollection",col);
	}
	//添加关注信息
	public int insertCollection(Collection col) {
		return this.getSqlSession().insert("com.sanzu.luntan.mapper.CollectionMapper.insertCollection", col);
	}
	//删除点赞信息
	public int deleteCollection(Collection col) {
		return this.getSqlSession().delete("com.sanzu.luntan.mapper.CollectionMapper.deleteCollection", col);
	}
}
