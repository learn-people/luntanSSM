package com.sanzu.luntan.controller.Post;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.config.YamlProcessor.ResolutionMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.sanzu.luntan.dao.PostDao;
import com.sanzu.luntan.pojo.Post;
import com.sanzu.luntan.pojo.User;
import com.sanzu.luntan.util.CrmUtils;
import com.sanzu.luntan.util.Msg;


@Controller
@RequestMapping("/post")
public class PostController {
	
	PostDao dao = (PostDao) CrmUtils.getBean("postDao");
	
	/**
	 *	从用户表中调用数据插入用户详情表中 
	 * @return 
	 * 
	 * */
	@RequestMapping("/selectLike.do")
	public String saveUserdetail() {
		dao.updateLikeAdd();
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
		
		List<Post> list = dao.selectAll();
		model.addAttribute("list", list);
		return "postManage";
	}
	
	/**
	 * 分页查询信息
	 * @param pn 默认从第一页开始  请求参数
	 * @param model
	 * @return
	 */
	@RequestMapping("/selectAll.do")
	@ResponseBody
	public Msg select(Model model,@RequestParam(value="pn",defaultValue ="1")Integer pn) {
		
		//从第一条开始，每页查询五条数据
		PageHelper.startPage(pn,5);
		List<Post> list = dao.selectAll();
		//将用户信息放入PageInfo对象里
		PageInfo<Post> page = new PageInfo<Post>(list, 5);
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
	public Msg getPost(String id) {
		Post post = dao.selectPost(Integer.parseInt(id));
		return Msg.success().add("post",post);
	}
	
	/**
	 * 更新信息
	 * @param id 根据id进行修改
	 * @return 刷新页面
	 * 
	 * */
	@RequestMapping(value="/update.do",method=RequestMethod.PUT)
	@ResponseBody
	public Msg savePost(Post post) {
		System.out.println("将要更新的用户信息"+post);
		dao.updatePost(post);
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
	public Msg savePostWithJson(Post post,HttpServletRequest request,HttpSession httpSession) {
		
		User user = (User) httpSession.getAttribute("user");
		String userNumber = user.getUserNumber();
		post.setUserNum(userNumber);
		
	    dao.insertPost(post);
	    return Msg.success();
	}
	
	/**
	 *删除单个信息
	 *@param id 根据用户的id进行删除的
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/deleteOne.do",method=RequestMethod.DELETE)
	public Msg deleteOne(int id) {
		dao.deleteOnePost(id);
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
			dao.deletePost(delete_id);
		}else {
			int id = Integer.parseInt(ids);
			dao.deleteOnePost(id);
		}
		return Msg.success();
	}
	


/*******************移动端请求获取数据**************************************************************************/

/**
 *移动端查询全部贴子 
 * 
 * */
 
	@RequestMapping(value="/selectAll.json")
	public void selectAll(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		String callback = request.getParameter("callback");
		
		List<Post> JsonRS = dao.selectAll();
		//System.out.println(JsonRS);
		//解决后台数据传输时的乱码问题
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (callback != null && callback.length() > 0) {
			out.print(callback + "(" + new Gson().toJson(JsonRS) + ")");
		} else {
			out.print(new Gson().toJson(JsonRS));
		}
		out.flush();
		out.close();
	}
	
	/**
	 *移动端查询指定板块的全部贴子 
	 * 
	 * */
	
	@RequestMapping(value="/select.json")
	public void select(@RequestParam Integer sectionId,HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		String callback = request.getParameter("callback");
		
		List<Post> JsonRS = dao.select(sectionId);
		//System.out.println(JsonRS);
		//解决后台数据传输时的乱码问题
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (callback != null && callback.length() > 0) {
			out.print(callback + "(" + new Gson().toJson(JsonRS) + ")");
		} else {
			out.print(new Gson().toJson(JsonRS));
		}
		out.flush();
		out.close();
	}
	
	/**
	 * 查询贴子的详细信息
	 * @param id 要查询的信息
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/get.json",method=RequestMethod.GET)
	public void get(@RequestParam Integer id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String callback = request.getParameter("callback");
		System.out.println(id);
		Post post = dao.selectPost(id);
		System.out.println(post);
		//解决后台数据传输时的乱码问题
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (callback != null && callback.length() > 0) {
			out.print(callback + "(" + new Gson().toJson(post) + ")");
		} else {
			out.print(new Gson().toJson(post));
		}
		out.flush();
		out.close();
	}
	
	/**
	 * 更新贴子的观看人数
	 * */
	@ResponseBody
	@RequestMapping(value="/updateLook.json",method=RequestMethod.GET)
	public void updateLook(@RequestParam Integer id,HttpServletResponse response,HttpServletRequest request) throws IOException{
		String callback = request.getParameter("callback");
		int i = dao.updateLook(id);
		
		if(i == 1) {
			String result = callback + "(" + i + ")";
			response.getWriter().write(result);
		}else {
			String result = callback + "(" + i + ")";
			response.getWriter().write(result);
		}
		
	}
	
	/**
	 * 更新贴子的点赞人数,增加
	 * */
	@ResponseBody
	@RequestMapping(value="/updateLikeAdd.json",method=RequestMethod.GET)
	public void updateLikeAdd(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String callback = request.getParameter("callback");
		int i = dao.updateLikeAdd();
		
		if(i == 1) {
			String result = callback + "(" + i + ")";
			response.getWriter().write(result);
		}else {
			String result = callback + "(" + i + ")";
			response.getWriter().write(result);
		}
		
	}
	
}

