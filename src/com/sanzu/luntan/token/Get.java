package com.sanzu.luntan.token;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.sanzu.luntan.util.QiniuUploadImageUtil;


//注解
@Controller
@RequestMapping("/token")
public class Get {
	
	@RequestMapping(value="/get.do",method=RequestMethod.POST)
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String key = request.getParameter("key");
		PrintWriter out = response.getWriter(); // 用PrintWriter对象将返回结果写入服务器
		Map<String, String> map = new HashMap<>();
		String token = QiniuUploadImageUtil.getToken(key); 	// 生成普通上传的Token
		map.put("token", token);
		out.print(new Gson().toJson(map));
		out.flush();
		out.close();
	}
}
