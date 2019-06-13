package com.sanzu.luntan.controller.Admin;

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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sanzu.luntan.dao.AdminDao;
import com.sanzu.luntan.pojo.Admin;
import com.sanzu.luntan.util.CrmUtils;
import com.sanzu.luntan.util.Msg;

/**
 * 管理员登录
 * @param model
 * @param a 管理员的信息
 * @return
 * */
//注解
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	AdminDao dao = (AdminDao) CrmUtils.getBean("adminDao");
	
	@RequestMapping("/login.do")
	public String login(Model model,Admin a,HttpServletRequest request) {
		
		Admin admin = dao.logInAdmin(a);
		
		if(admin == null) {
			model.addAttribute("loginError", "账号或密码错误");
			//springMVC中的请求转发和重定向redirect和forward
			//redirect的方法
			//return "redirect:/login.jsp";
			//forward的方法
			return "forward:/login.jsp";
		}
		//model.addAttribute("user",user);
		
		//使用session可以使页面记住登录状态
		//引入 Session，用来在服务端和客户端之间保存一系列动作/消息的状态。
		HttpSession session = request.getSession();
		session.setAttribute("admin", admin);
		
		//return "index";
		//执行select.do注解映射
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
		List<Admin> list = dao.selectAll();
		model.addAttribute("list", list);
		return "adminManage";
	}
	
	/**
	 * 分页查询用户信息
	 * @param pn 默认从第一页开始  请求参数
	 * @param model
	 * @return
	 */
	@RequestMapping("/selectAll.do")
	@ResponseBody
	public Msg select(Model model,@RequestParam(value="pn",defaultValue ="1")Integer pn) {
		
		//从第一条开始，每页查询五条数据
		PageHelper.startPage(pn,5);
		List<Admin> list = dao.selectAll();
		//将用户信息放入PageInfo对象里
		PageInfo<Admin> page = new PageInfo<Admin>(list, 5);
		model.addAttribute("pageInfo", page);
		
		 return Msg.success().add( "pageInfo" , page);
	}
	
	/**
	 * 查询单个信息
	 * @param id 要查询的用户信息
	 * 
	 * */
	@RequestMapping(value="/get.do",method=RequestMethod.GET)
	@ResponseBody
	public Msg getAdmin(String id) {
		Admin admin = dao.selectAdmin(Integer.parseInt(id));
		return Msg.success().add("admin",admin);
	}
	
	/**
	 * 更新信息
	 * @param id 根据id进行修改
	 * @return 刷新页面
	 * 
	 * */
	@RequestMapping(value="/update.do",method=RequestMethod.PUT)
	@ResponseBody
	public Msg saveAdmin(Admin admin) {
		System.out.println("将要更新的用户信息"+admin);
		dao.updateAdminInfo(admin);
		return Msg.success();
	}
	
	/**
	 * 保存员工
	 * 1.支持jsr303校验
	 * 2.导入Hibernate-Validator
	 * 3.在bean的属性中添加校验注解
	 * 4.在调用方法参数中，写上@Valid启用校验功能，BindingResult 设置校验结果返回
	 * @param a 插入的数据
	 * @return
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/insert.do",method=RequestMethod.POST)
	public Msg saveAdminWithJson(@Valid Admin a,BindingResult result) {
		if(result.hasErrors()) {
			//校验失败，因该返回失败，在模态框中显示校验失败的错误信息
			Map<String, Object> map = new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
			  /*System.out.println("错误的字段名："+fieldError.getField());
			  System.out.println("错误信息："+fieldError.getDefaultMessage());*/
			  map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
			//校验成功
		  /*System.out.println(a);*/
		  dao.insertAdmin(a);
		  return Msg.success();
		}
	}
	
	/**
	 * @param adminName 需要校验的名称
	 * @return 
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/checkAdminName.do",method=RequestMethod.POST)
	public Msg checkAdminName(String adminName) {
		//先判断用户名是否是合法的表达式；
		String regx = "/(^[a-zA-Z0-9_-]{5,16}$)|(^[\u2E80-\u9FFF]{2,10})/";
		if(adminName.matches(regx)) {
			return Msg.fail().add("adminName_msg", "用户名必须是5-16位的数字和字母的组合或者2-5位中文");
		}
		int count = dao.checkAdminName(adminName);
		if(count == 0) {
			return Msg.success().add("adminName_msg", "用户名可用");
		}else {
			return Msg.fail().add("adminName_msg", "用户名不可用");
		}
	}
	
	/**
	 * @param adminNumber 需要校验的账号
	 * @return 
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/checkAdminNumber.do",method=RequestMethod.POST)
	public Msg checkAdminNumber(@Valid String adminNumber) {
		//先判断用户名是否是合法的表达式；
		String regx = "/^\\d{3,11}$/";
		if(adminNumber.matches(regx)) {
			return Msg.fail().add("adminNumber_msg", "账号必须是3-11位的数字");
		}
		
		int count = dao.checkAdminNumber(adminNumber);
		if(count == 0) {
			return Msg.success().add("adminNumber_msg", "账号可用");
		}else {
			return Msg.fail().add("adminNumber_msg", "账号不可用");
		}
	}
	
	/**
	 * 删除单个信息 
	 * @param id 根据用户的id进行删除的
	 * @return 刷新页面
	 * 
	 */
	@RequestMapping(value="/deleteOne.do",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteOne(int id) {
		dao.deleteOneAdmin(id);
		return Msg.success();
	}
	
	/**
	 * 利用选框删除数据时批量删除用户信息和删除单个用户合一起
	 * 当我们传递一个 List 实例或者数组作为参数对象传给 MyBatis。当你这么做的时 候,MyBatis 会自动将它包装在一个 Map 中,用名称在作为键。
	 * List 实例将会以“list” 作为键,而数组实例将会以“array”作为键。
	 * 所以，当我们传递的是一个List集合时，mybatis会自动把我们的list集合包装成以list为Key值的map。 
	 * @param ids 根据用户的id进行批量删除 
	 * @return 
	 */
	@RequestMapping(value="/delete.do",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg delete(String ids) {
		//批量删除
		if(ids.contains("-")) {
			List<Integer> delete_id = new ArrayList<>();
			String[] str_ids = ids.split("-");
			for(String str : str_ids) {
				delete_id.add(Integer.parseInt(str));
			}
			dao.deleteAdmin(delete_id);
		}else {
			int id = Integer.parseInt(ids);
			dao.deleteOneAdmin(id);
		}
		return Msg.success();
	}
	
}
