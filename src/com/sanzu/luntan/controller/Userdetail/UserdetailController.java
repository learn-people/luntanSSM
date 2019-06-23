package com.sanzu.luntan.controller.Userdetail;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sanzu.luntan.dao.UserDao;
import com.sanzu.luntan.dao.UserdetailDao;
import com.sanzu.luntan.pojo.User;
import com.sanzu.luntan.pojo.Userdetail;
import com.sanzu.luntan.util.CrmUtils;
import com.sanzu.luntan.util.Msg;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 用户详情表
 * 
 * */
@Controller
@RequestMapping("/userdetail")
public class UserdetailController {
	
	UserdetailDao dao = (UserdetailDao) CrmUtils.getBean("userdetailDao");
	UserDao userDao = (UserDao) CrmUtils.getBean("userDao");
	/**
	 *	从用户表中调用数据插入用户详情表中 
	 * @return 
	 * 
	 * */
	@RequestMapping("/insert.do")
	public String saveUserdetail() {
		dao.insertUserdetail(null);
		return "forward:select.do";
	}
	
	/**
	 * 用户查询并转到jsp页面
	 * 
	 * 
	 * */
//查询全部信息
	@RequestMapping("/select.do")
	public String select(Model model) {
		List<Userdetail> list = dao.selectAll();
		model.addAttribute("list", list);
		return "userdetailManage";
	}
	
	/**
	 * 分页查询用户信息
	 * @param pn 默认从第一页开始  请求参数
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectAll.do")
	public Msg select(Model model,@RequestParam(value="pn",defaultValue ="1")Integer pn) {
		
		//从第一条开始，每页查询五条数据
		PageHelper.startPage(pn,5);
		List<Userdetail> list = dao.selectAll();
		//将用户信息放入PageInfo对象里
		PageInfo<Userdetail> page = new PageInfo<Userdetail>(list, 5);
		model.addAttribute("pageInfo", page);
		
		 return Msg.success().add( "pageInfo" , page);
	}
	
	/**
	 * 查询单个信息
	 * @param id 要查询的用户信息
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/get.do",method=RequestMethod.GET)
	public Msg getUserdetail(String id) {
		Userdetail userdetail = dao.selectUserdetail(Integer.parseInt(id));
		return Msg.success().add("userdetail",userdetail);
	}
	
	/**
	 * 更新信息
	 * @param id 根据id进行修改
	 * @return 刷新页面
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/update.do",method=RequestMethod.PUT)
	public Msg saveUserdetail(Userdetail userdetail) {
		System.out.println("将要更新的用户信息"+userdetail);
		dao.updateUserdetailInfo(userdetail);
		return Msg.success();
	}
	
//////////////////////////////////////////////移动端/////////////////////////////////////////
/**
* 更新信息
* 
* */
	@ResponseBody
	@RequestMapping(value="/update.json")
	/**
	* 移动端的用户注册，数据插入数据表
	* 
	* */
	public void updateMyDetail(@RequestParam("callback") String callback,
															HttpServletResponse response,HttpServletRequest request)throws IOException {
	
			Userdetail userdetail = new Userdetail();
			User user  = new User();
			//获取Android端数据
			String data = request.getParameter("data");
			System.out.println(data);
			//将string类型转换为json数组
			JSONArray rows = JSONArray.fromObject(data);
			JSONObject row = null;
			row = rows.getJSONObject(0);
			user.setUserPassword((String) row.get("password"));
			user.setUserNumber((String) row.get("userNumber"));
			user.setUserName((String) row.get("userName"));
			user.setGender((String) row.get("sex"));
			user.setImgUrl((String) row.get("imgUrl"));
			
			//userdetail.setId((int) row.get("id"));
			userdetail.setUserNum((String) row.get("userNumber"));
			userdetail.setAutography((String) row.get("autography"));
			userdetail.setHometown((String) row.get("hometown"));
			userdetail.setJob((String) row.get("job"));
			userdetail.setSchool((String) row.get("school"));
			userDao.updateUserInfo(user);
			PrintWriter out = response.getWriter();
			//用回调函数名称包裹返回数据
			String result = callback + "(" +dao.updateUserdetailInfo(userdetail) + ")";
			response.getWriter().write(result);
			//return Msg.success();
}
	
}
