package com.sanzu.luntan.controller.Collection;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.sanzu.luntan.dao.CollectionDao;
import com.sanzu.luntan.pojo.Collection;
import com.sanzu.luntan.util.CrmUtils;

@Controller
@RequestMapping("/collection")
public class CollectionController {
	CollectionDao dao = (CollectionDao) CrmUtils.getBean("collectionDao");
	
	/*******移动端的操作***************************************/
	
	/**
	 * 移动端查找收藏的贴子详细信息
	 * */
	@RequestMapping("/selectAll.json")
	public void selectAll(@RequestParam String userNum,
												HttpServletRequest request ,HttpServletResponse response)throws IOException{
		String callback = request.getParameter("callback");
		Collection col = new Collection();
		col.setUserNum(userNum);
		List<Collection> JsonRS = dao.selectAll(col);
		
		//防止乱码
		response.setContentType("application/json;charset=utf-8");
	  PrintWriter out = response.getWriter();
	  if(callback != null && callback.length() > 0) {
	  	out.print(callback + "(" + new Gson().toJson(JsonRS) + ")");
	  }else {
	  	out.print(new Gson().toJson(JsonRS));
	  }
	  out.flush();
	  out.close();
	}  
	
	/**
	 * 移动端查询是否收藏
	 * 
	 * */
	@RequestMapping("/select.json")
	public void select(@RequestParam String userNum,@RequestParam Integer postId,
											HttpServletRequest request,HttpServletResponse response)throws IOException{
		String callback = request.getParameter("callback");
		Collection col = new Collection();
		col.setUserNum(userNum);
		col.setPostId(postId);
		int JsonRS = dao.selectCollection(col);
		
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
	 * 添加收藏信息
	 * 
	 * */
	@RequestMapping("/insert.json")
	public void insert(@RequestParam Integer postId,@RequestParam String userNum,
											HttpServletRequest request,HttpServletResponse response)throws IOException{
		
		String callback = request.getParameter("callback");
		Collection col = new Collection();
		col.setPostId(postId);
		col.setUserNum(userNum);
		int JsonRS = dao.insertCollection(col);
		
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
	 * 删除收藏信息
	 * 
	 * */
	@RequestMapping("/delete.json")
	public void delete(@RequestParam Integer postId,@RequestParam String userNum,
										 HttpServletRequest request,HttpServletResponse response)throws IOException {
		
		String callback = request.getParameter("callback");
		Collection col = new Collection();
		col.setPostId(postId);
		col.setUserNum(userNum);
		int JsonRS = dao.deleteCollection(col);
		
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
