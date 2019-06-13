package com.sanzu.luntan.controller.Section;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter.Magenta;
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
import com.sanzu.luntan.dao.SectionDao;
import com.sanzu.luntan.pojo.Section;
import com.sanzu.luntan.util.CrmUtils;
import com.sanzu.luntan.util.Msg;

//注解
@Controller
@RequestMapping("/section")
public class SectionController {
	
	SectionDao dao = (SectionDao) CrmUtils.getBean("sectionDao");
	
	/**
	 * 板块查询并转到jsp页面
	 * 
	 * 
	 * */
  //查询全部信息
	@RequestMapping("/select.do")
	public String select(Model model) {
		List<Section> list = dao.selectAll();
		model.addAttribute("list", list);
		return "sectionManage";
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
		PageHelper.startPage(pn,10);
		List<Section> list = dao.selectAll();
		//将用户信息放入PageInfo对象里
		PageInfo<Section> page = new PageInfo<Section>(list, 10);
		model.addAttribute("pageInfo", page);
		
		 return Msg.success().add( "pageInfo" , page);
	}
	
	/**
	 * 查询信息
	 * @param id 要查询的用户信息
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/get.do",method=RequestMethod.GET)
	public Msg getSection(Model model) {
		List<Section> list = dao.selectAll();
		model.addAttribute("list", list);
		return Msg.success().add("list",list);
	}
	
	/**
	 * 更新板块
	 * @param id 根据id进行修改
	 * @return 刷新页面
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/update.do",method=RequestMethod.PUT)
	public Msg saveSection(Section section) {
		System.out.println("将要更新的板块信息"+section);
		dao.updateSection(section);
		return Msg.success();
	}
	
	/**
	 * 添加板块
	 * @param a 插入的数据
	 * @return
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/insert.do",method=RequestMethod.POST)
	public Msg saveSectionWithJson(@Valid Section s,BindingResult result) {
		if(result.hasErrors()) {
			//校验失败，因该返回失败，在模态框中显示校验失败的错误信息
			Map<String, Object> map = new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
			  map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
		  dao.insertSection(s);
		  return Msg.success();
		}
	}
	
	/**
	 * @param adminName 需要校验的名称
	 * @return 
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/checkSectionName.do",method=RequestMethod.POST)
	public Msg checkSectionName(String sectionName) {
		int count = dao.checkSectionName(sectionName);
		if(count == 0) {
			return Msg.success().add("sectionName_msg", "板块名可用");
		}else {
			return Msg.fail().add("sectionName_msg", "板块名不可用");
		}
	}
	
	/**
	 * 删除单个信息 
	 * @param id 根据用户的id进行删除的
	 * @return 刷新页面
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/deleteOne.do",method=RequestMethod.DELETE)
	public Msg deleteOne(int id) {
		dao.deleteOneSection(id);
		return Msg.success();
	}
	
	/**
	 * @param ids 根据用户的id进行批量删除 
	 * @return 
	 */
	@ResponseBody
	@RequestMapping(value="/delete.do",method=RequestMethod.DELETE)
	public Msg delete(String ids) {
		//批量删除
		if(ids.contains("-")) {
			List<Integer> delete_id = new ArrayList<>();
			String[] str_ids = ids.split("-");
			for(String str : str_ids) {
				delete_id.add(Integer.parseInt(str));
			}
			dao.deleteSection(delete_id);
		}else {
			int id = Integer.parseInt(ids);
			dao.deleteOneSection(id);
		}
		return Msg.success();
	}
	
}
