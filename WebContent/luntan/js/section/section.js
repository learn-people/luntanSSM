

 /*****************添加用户信息模块**************************************/
//添加模态框的事件

/**
 * 要改的将会很多
 * */
 $("#section_add").click(function(){
  $("#sectionAddModal").dialog("open");
 });
 
 //校验板块名是否可用，需要在保存按钮提交前进行判断，自定义属性留个记号，让保存按钮可用来判断后端校验情况。
 $("#sectionName_add_input").change(function(){
	 //发送ajax请求，校验用户名是否可用
	 var sectionName = this.value;
	 $.ajax({
		 url:"/luntanSSM/section/checkSectionName.do",
		 type:"POST",
		 data:"sectionName=" + sectionName,
		 success:function(result){
			 if(result.code == 100){
				 show_validate_msg("#sectionName_add_input","success",g_sectionName_valid);
				 $("#section_add_save_btn").attr("ajax-check-sectionName","success");
			 }else{
				 show_validate_msg("#sectionName_add_input","error",result.extend.sectionName_msg)
				 $("#section_add_save_btn").attr("ajax-check-sectionName","error"); 
			 }
		 }
	 });
 });
 
//添加模态框 点击保存按钮的事件
 $("#section_add_save_btn").click(function(){
 	 //判断之前的ajax板块名重复性校验是否成功
	 if($(this).attr("ajax-check-sectionName") == "error"){
		 return false;
	 }
	 
	 add_saveData();
	 
 });
 //保存数据
 function add_saveData(){			
	 $.ajax({
		 url:"/luntanSSM/section/insert.do",
		 type:"POST",
		 data:$("#sectionAddModal form").serialize(), 
		 success:function(result){
			 //经过测试，服务器返回的是 json形式的 Msg对象， 到了浏览器中 变成了 result。所以: result.msg 相当于Msg对象里面的msg属性
			 console.log(result);
			 if(result.code == 100){
				 //1、员工保存成功，需要关闭模态框。
				 $("#sectionAddModal").dialog("close"); 			  
				 //2、来到最后一页，显示刚才的数据；发送ajax请求，显示最后一页数据即可(用总记录数请求，保证是请求足够大，mybatis 分页插件 已经在mybatis-config.xml中配置了数据合法性校验，只会返回最后一页数据)
				 to_page(totalRecords);	
			 }else{
				 
				 if(undefined!=result.extend.errorFileds.sectionName){
					 //显示员工名字错误信息
					 show_validate_msg("#sectionName_add_input","error",result.extend.errorFileds.sectionName);
				 }
			 }
			 //调用清空表单的方法
			 reset_form("#sectionAddModal form");
		 }
	 }); 
 }
 
 /*************************************添加用户模块结束*******************************************************************************/
