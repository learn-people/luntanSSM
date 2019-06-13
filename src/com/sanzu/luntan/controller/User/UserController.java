package com.sanzu.luntan.controller.User;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sanzu.luntan.dao.UserDao;
import com.sanzu.luntan.pojo.Admin;
import com.sanzu.luntan.pojo.User;
import com.sanzu.luntan.util.CrmUtils;
import com.sanzu.luntan.util.Msg;

//注解
@Controller
@RequestMapping("/user")
//这里用了@SessionAttributes，可以直接把model中的user(也就key)放入其中
//这样保证了session中存在user这个对象
@SessionAttributes("user")
public class UserController {
	
	UserDao dao = (UserDao) CrmUtils.getBean("userDao");
	
	//用户登录
	@RequestMapping("/login.do")
	public String login(Model model,User u,HttpServletRequest request) {
		
		User user = dao.logInUser(u);
		
		if(user == null) {
			model.addAttribute("loginError", "账号或密码错误");
			//springMVC中的请求转发和重定向redirect和forward
			//redirect的方法
			//return "redirect:/login.jsp";
			//forward的方法
			return "forward:/login.jsp";
		}
		//model.addAttribute("user",user);
		
		
		//return "index";
		//执行select.do注解映射
		return "forward:select.do";
	}
	
	/**
	 * 注销登录
	 * 
	 * */
	@RequestMapping("/outLogin.do")
	public String outLogin(HttpSession session) {
		//通过session.invalidata()方法来注销当前的session
		session.invalidate();
		return "forward:/login.jsp";
	}
	
	/**
	 * 用户查询并转到jsp页面
	 * 
	 * 
	 * */
//查询全部信息
	@RequestMapping("/select.do")
	public String select(Model model) {
		List<User> list = dao.selectAll();
		model.addAttribute("list", list);
		return "userManage";
	}
	
	/**
	  * 分页查询用户的全部信息
	 * 
	 * */	
	@ResponseBody
	@RequestMapping("/selectAll.do")
	public Msg select(Model model,@RequestParam(value="pn",defaultValue="1")Integer pn) {
		
		//从第一条开始，每页查询五条数据
		PageHelper.startPage(pn,5);
		List<User> list = dao.selectAll();
		//将用户信息放入PageInfo对象里
		PageInfo<User> page = new PageInfo<User>(list,5);
		model.addAttribute("pageInfo", page);
		
		return Msg.success().add("pageInfo", page);
	}
	
	/**
	 * 查询单个信息
	 * 
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/get.do",method=RequestMethod.GET)
	public Msg getUser(String id) {
		User user = dao.selectUser(Integer.parseInt(id));
		return Msg.success().add("user",user);
	}
	
	/**
	 * 更新信息
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/update.do",method=RequestMethod.PUT)
	public Msg saveUser(User user) {
		System.out.println("将要更新的数据是"+user);
		dao.updateUserInfo(user);
		return Msg.success();
	}
	
	/**
	 * 保存员工
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/insert.do",method=RequestMethod.POST)
	public Msg saveUserWithJson(@Valid User u,BindingResult result){
		if(result.hasErrors()) {
			//校验失败，应返回失败，在模态框中显示校验失败的错误信息
			Map<String, Object> map = new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for(FieldError fieldError : errors) {
			  /*System.out.println("错误的字段名："+fieldError.getField());
			  System.out.println("错误信息："+fieldError.getDefaultMessage());*/
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
			//校验成功
			dao.insertUser(u);
			return Msg.success();
		}
	}
	
	/**
	 * 校验用户名是否可用
	 * @param userName 需要校验的名称
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/checkUserName.do",method=RequestMethod.POST)
	public Msg checkUserName(String userName) {
		//先判断用户名是否合法的表达式
		String regx = "/(^[a-zA-Z0-9_-]{5,16}$)|(^[\u2E80-\u9FFF]{2,10})/";
		if(userName.matches(regx)) {
			return Msg.fail().add("userName_msg","用户名必须是5-16位的数字和字母或2-10位的中文");
		}
		int count = dao.checkUserName(userName);
		if(count == 0) {
			return Msg.success().add("userName_msg", "用户名可用");
		}else {
			return Msg.fail().add("userName_msg", "用户名不可用");
		}
	}
	
	/**
	 * 校验账号
	 * @param adminNumber 需要校验的账号
	 * */
	@ResponseBody
	@RequestMapping(value="/checkUserNumber.do",method=RequestMethod.POST)
	public Msg checkUserNumber(@Valid String userNumber) {
		//先判断用户名是否合法
		String regx = "/^\\d{3,11}$/";
		if(userNumber.matches(regx)) {
			return Msg.fail().add("userNumber_msg", "账号必须是3-11位的数字");
		}
		int count = dao.checkUserNumber(userNumber);
		if(count == 0) {
			return Msg.success().add("userNumber_msg", "账号可用");
		}else {
			return Msg.fail().add("userNumber_msg", "账号不可用");
		}
	}
	
	/**
	 *删除单个信息
	 *@param id 根据用户的id进行删除的
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/deleteOne.do",method=RequestMethod.DELETE)
	public Msg deleteOne(int id) {
		dao.deleteOneUser(id);
		return Msg.success();
	}
	
	/**
	 * 批量删除用户
	 * @param ids 根据用户的id进行批量删除
	 * */
	@ResponseBody
	@RequestMapping(value="/delete.do",method=RequestMethod.DELETE)
	public Msg delete(String ids) {
		//批量删除的
		if(ids.contains("-")) {
			List<Integer> delete_id = new ArrayList<>();
			String[] str_ids = ids.split("-");
			for(String str: str_ids) {
				delete_id.add(Integer.parseInt(str));
			}
			dao.deleteUser(delete_id);
		}else {
			int id = Integer.parseInt(ids);
			dao.deleteOneUser(id);
		}
		return Msg.success();
	}
	
}





