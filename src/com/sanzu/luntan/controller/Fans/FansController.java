package com.sanzu.luntan.controller.Fans;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.sanzu.luntan.dao.FansDao;
import com.sanzu.luntan.pojo.Followed;
import com.sanzu.luntan.pojo.User;
import com.sanzu.luntan.util.CrmUtils;
import com.sanzu.luntan.util.Msg;


@Controller
@RequestMapping("/fans")
public class FansController {
	FansDao dao = (FansDao) CrmUtils.getBean("fansDao");
	
	//查询全部信息并跳转到相应页面
	@RequestMapping("/select.do")
	public String selectAll(Model model){
		List<Followed> list = dao.selectAll();
		model.addAttribute("list", list);
		return "fansManage";
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
		PageInfo<Followed> page = new PageInfo<Followed>(list,5);
		model.addAttribute("pageInfo", page);
		
		return Msg.success().add("pageInfo", page);
	}
	
	/**
	 * 查询单个用户的所有粉丝信息
	 * 
	 * */
	@ResponseBody
	@RequestMapping("/selectOne.do")
	public Msg select(int followedUserId) {
		List<User> f = dao.select(followedUserId);
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


/**********移动端的数据交互***************************************************************************/
/**
 * 移动端查询粉丝列表
 * 
 * */
	@RequestMapping("/selectOne.json")
  public void selectOne(@RequestParam Integer followedUserId,
  											HttpServletResponse response,HttpServletRequest request) throws IOException{
	  String callback = request.getParameter("callback");
	  List<User> JsonRS = dao.select(followedUserId);
	  
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

