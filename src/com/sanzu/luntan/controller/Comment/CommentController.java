package com.sanzu.luntan.controller.Comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.sanzu.luntan.dao.CommentDao;
import com.sanzu.luntan.dao.PostDao;
import com.sanzu.luntan.pojo.Comment;
import com.sanzu.luntan.pojo.User;
import com.sanzu.luntan.util.CrmUtils;
import com.sanzu.luntan.util.Msg;

@Controller
@RequestMapping("/comment")
public class CommentController {
	//定义comment的dao
	CommentDao dao = (CommentDao) CrmUtils.getBean("commentDao");
	//定义post的dao，调用post的Dao层
	PostDao postDao = (PostDao) CrmUtils.getBean("postDao");
	
	//查询全部信息
	@RequestMapping("/select.do")
	public String select(Model model) {
		List<Comment> list = dao.selectAll();
		System.out.println(list);
		model.addAttribute("list",list);
		return "commentManage";
	}
	
	/**
	 * 分页查询评论信息
	 * 
	 * */
	@ResponseBody
	@RequestMapping("/selectAll.do")
	public Msg select(Model model,@RequestParam(value="pn",defaultValue = "1")Integer pn) {
	//从第一条开始，每页查询五条数据
			PageHelper.startPage(pn,5);
			List<Comment> list = dao.selectAll();
			//将用户信息放入PageInfo对象里
			PageInfo<Comment> page = new PageInfo<Comment>(list, 5);
			model.addAttribute("pageInfo", page);
			
			 return Msg.success().add( "pageInfo" , page);
	}
	
	/**
	 * 查询单个评论
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/get.do",method=RequestMethod.GET)
	public Msg getComment(String id) {
		List<Comment> comment = dao.selectComment(Integer.parseInt(id));
		return Msg.success().add("comment", comment);
	}
	
	/**
	 * 更新信息
	 * @param id 根据id进行修改
	 * @return 刷新页面
	 * 
	 * */
	@RequestMapping(value="/update.do",method=RequestMethod.PUT)
	@ResponseBody
	public Msg saveComment(Comment comment) {
		System.out.println("将要更新的用户信息"+comment);
		dao.updateComment(comment);
		return Msg.success();
	}
	
	/**
	 * 添加评论
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/insert.do",method=RequestMethod.POST)
	public Msg saveCommentwithJson(Comment comment,HttpServletRequest request,HttpSession httpSession) {
		
		User user = (User) httpSession.getAttribute("user");
		String userNumber = user.getUserNumber();
		comment.setUserNum(userNumber);
		
	    dao.insertComment(comment);
	    return Msg.success();
	}
	
	/**
	 *删除信息
	 *@param id 根据id进行删除的
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/deleteOne.do",method=RequestMethod.DELETE)
	public Msg deleteOne(int id) {
		dao.deleteComment(id);
		return Msg.success();
	}
	
	/**
	 * 批量删除
	 * @param ids 根据id进行批量删除
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
			dao.deleteComment(delete_id);
		}else {
			int id = Integer.parseInt(ids);
			dao.deleteComment(id);
		}
		return Msg.success();
	}
	
	/**********移动端获取数据*************************************************************/
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
		List<Comment> comment = dao.selectComment(id);
		System.out.println(comment);
		//解决后台数据传输时的乱码问题
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (callback != null && callback.length() > 0) {
			out.print(callback + "(" + new Gson().toJson(comment) + ")");
		} else {
			out.print(new Gson().toJson(comment));
		}
		out.flush();
		out.close();
	}
	
	/**
	 * 添加评论
	 * */
	@ResponseBody
	@RequestMapping(value="/insert.json",method=RequestMethod.GET)
	public void insert(@RequestParam Integer postId,
										 @RequestParam String userNum,
										 @RequestParam String commentContent,
										 @RequestParam String commentTime,
										 HttpServletRequest request,HttpServletResponse response)throws IOException{
		
		String callback = request.getParameter("callback");
		Comment c = new Comment();
		c.setPostId(postId);
		c.setUserNum(userNum);
		c.setCommentContent(commentContent);
		c.setCommentTime(commentTime);
		System.out.println(c);
		int JsonRS = dao.insertComment(c);
		
		//更新贴子数据库中的评论数
		postDao.updatePurview();
		
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
	 * 删除评论
	 * */
	@ResponseBody
	@RequestMapping(value="/delete.json",method=RequestMethod.GET)
	public void delete(@RequestParam String userNum,@RequestParam Integer id, 
											HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String callback = request.getParameter("callback");
		Comment c = new Comment();
		c.setId(id);
		c.setUserNum(userNum);
		int JsonRS = dao.deleteOneComment(c);
		
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
	
}
