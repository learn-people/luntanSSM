package com.sanzu.luntan.controller.Praise;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sanzu.luntan.dao.PraiseDao;
import com.sanzu.luntan.pojo.Praise;
import com.sanzu.luntan.util.CrmUtils;

//注解
@Controller
@RequestMapping("/praise")
public class PraiseController {
	
	PraiseDao dao = (PraiseDao) CrmUtils.getBean("praiseDao");
	
	/*************移动端调用的接口*********************************/
	/**
	 * 移动端查询点赞信息
	 * 
	 * */
	@RequestMapping("/select.json")
	public void select(
			@RequestParam Integer postId,@RequestParam String userNum,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String callback = request.getParameter("callback");
		Praise p = new Praise();
		p.setPostId(postId);
		p.setUserNum(userNum);
		int JsonRS = dao.selectPraise(p);
		
		response.setContentType("application/json;charset=utf-8");
    //用回调函数名称包裹返回数据
		if(JsonRS == 1) {
			String result = callback + "(" + JsonRS + ")";
			response.getWriter().write(result);
		}else {
			String result = callback + "(" + JsonRS + ")";
			response.getWriter().write(result);
		}
	}
	
	/**
	 * 添加点赞信息
	 * 
	 * */
	@RequestMapping("/insert.json")
	public void insert(
			@RequestParam Integer postId,@RequestParam String userNum,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String callback = request.getParameter("callback");
		Praise p = new Praise();
		p.setPostId(postId);
		p.setUserNum(userNum);
		int JsonRS = dao.insertPraise(p);
		
		response.setContentType("application/json;charset=utf-8");
    //用回调函数名称包裹返回数据
		if(JsonRS == 1) {
			String result = callback + "(" + JsonRS + ")";
			response.getWriter().write(result);
		}else {
			String result = callback + "(" + JsonRS + ")";
			response.getWriter().write(result);
		}
	}
	
	/**
	 * 删除点赞信息
	 * 
	 * */
	@RequestMapping("/delete.json")
	public void delete(
			@RequestParam Integer postId,@RequestParam String userNum,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String callback = request.getParameter("callback");
		Praise p = new Praise();
		p.setPostId(postId);
		p.setUserNum(userNum);
		int JsonRS = dao.deletePraise(p);
		
		response.setContentType("application/json;charset=utf-8");
    //用回调函数名称包裹返回数据
		if(JsonRS == 1) {
			String result = callback + "(" + JsonRS + ")";
			response.getWriter().write(result);
		}else {
			String result = callback + "(" + JsonRS + ")";
			response.getWriter().write(result);
		}
	}
}
