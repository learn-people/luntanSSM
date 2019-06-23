package com.sanzu.luntan.controller.Followed;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.log.SystemLogHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.sanzu.luntan.dao.FollowedDao;
import com.sanzu.luntan.pojo.Followed;
import com.sanzu.luntan.pojo.User;
import com.sanzu.luntan.util.CrmUtils;
import com.sanzu.luntan.util.Msg;

@Controller
@RequestMapping("/followed")
public class FollowedController {
	FollowedDao dao = (FollowedDao) CrmUtils.getBean("followedDao");
	
	//查询全部信息并跳转到相应页面
	@RequestMapping("/select.do")
	public String selectAll(Model model){
		List<Followed> list = dao.selectAll();
		model.addAttribute("list", list);
		return "followedManage";
	}
	
	/**
	 * 分页查询关注列表
	 * 
	 * */
	@ResponseBody
	@RequestMapping("/selectAll.do")
	public Msg select(Model model,@RequestParam(value="pn",defaultValue = "1")Integer pn) {
		//从第一条开始，每页查询五条数据
		PageHelper.startPage(pn, 5);
		List<Followed> list = dao.selectAll();
		System.out.println(list);
		PageInfo<Followed> page = new PageInfo<Followed>(list,5);
		model.addAttribute("pageInfo", page);
		
		return Msg.success().add("pageInfo", page);
	}
	
	/**
	 * 查询单个用户的所有关注信息
	 * 
	 * */
	@ResponseBody
	@RequestMapping("/selectOne.do")
	public Msg select(int userId) {
		List<User> f = dao.select(userId);
		return Msg.success().add("f", f);
	}
	
	/**
	 * 插入数据
	 * 
	 * */
	@ResponseBody
	@RequestMapping("/insert.do")
	public Msg saveInfo(Followed followed) {
		
		dao.insertFollowed(followed);
		
		return Msg.success();
	}
	
	/**
	 * 删除单个信息
	 * 
	 * */
	@ResponseBody
	@RequestMapping("/delete.do")
	public Msg deleteOne(Followed followed) {
		System.out.println(followed);
		dao.deleteFollowed(followed);
		return Msg.success();
	}

/**********移动端请求**********************************************************************************/

	//移动端查询关注列表
  @RequestMapping("/selectOne.json")
  public void selectOne(@RequestParam Integer userId,
  											HttpServletResponse response,HttpServletRequest request) throws IOException{
	  String callback = request.getParameter("callback");
	  List<User> JsonRS = dao.select(userId);
	  
	  //System.out.println(JsonRS);
	  
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

}
