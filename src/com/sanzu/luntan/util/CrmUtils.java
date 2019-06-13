package com.sanzu.luntan.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CrmUtils {
  //Utils，即utility，工具辅助层，一组通用的代码集合，比如处理多语言功能，网站非法信息过滤等等功能的代码集；
  //这个类是用于将ApplicationContext提取出来，是所欲要调用的类都可以使用
	private static ApplicationContext context;
	
	//静态块
	static {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}    
	
	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}
}
