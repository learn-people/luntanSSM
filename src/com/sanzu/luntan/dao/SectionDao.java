package com.sanzu.luntan.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sanzu.luntan.pojo.Section;

public class SectionDao extends SqlSessionDaoSupport {
//查找单个
	public Section selectSection(int id) {
		return this.getSqlSession().selectOne("com.sanzu.luntan.mapper.SectionMapper.findSectionById",id);
	}
	//查找全部
	public List<Section> selectAll(){
		return this.getSqlSession().selectList("com.sanzu.luntan.mapper.SectionMapper.querySectionAll");
	}
	//添加板块
	public int insertSection(Section p) {
		return this.getSqlSession().insert("com.sanzu.luntan.mapper.SectionMapper.insertSection", p);
	}
	//更新板块
	public int updateSection(Section p) {
		return this.getSqlSession().update("com.sanzu.luntan.mapper.SectionMapper.updateSection", p);
	}
	//删除单个信息
	public int deleteOneSection(int id) {
		return this.getSqlSession().delete("com.sanzu.luntan.mapper.SectionMapper.deleteSectionOne",id);
	}
	//批量删除
	public int deleteSection(List<Integer> ids) {
		return this.getSqlSession().delete("com.sanzu.luntan.mapper.SectionMapper.deleteSectionAll", ids);
	}
	//校验名称是否可用
	public int checkSectionName(String sectionName) {
		return this.getSqlSession().selectOne("com.sanzu.luntan.mapper.SectionMapper.checkSectionName",sectionName);
	}
}
