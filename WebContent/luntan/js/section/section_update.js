
/*************************************修改用户模块**********************************************************************************/
 
/**
 * 更改的地方多了去了，好累的啊！！！
 * 
 * */
$(document).on("click",".edit-btn",function(){
	 //0、清空表单样式和内容
	 reset_form("#sectionUpdateModal form");
	 
	 //2、查询员工信息，并显示员工信息  // 坑爹 一开始写成了 attr(edit-id) 
	 getSection($(this).attr("edit-id"));
	 
	 //3、把员工的id传递给模态框的更新按钮，为了发送ajax时传递id
	 $("#section_update_done_btn").attr("edit-id",$(this).attr("edit-id"));
	 $("#sectionUpdateModal").dialog("open");
 });
 
 //校验用户名是否可用，需要在保存按钮提交前进行判断，自定义属性留个记号，让保存按钮可用来判断后端校验情况。
 $("#sectionName_update_input").change(function(){
	 //发送ajax请求，校验用户名是否可用
	 var sectionName = this.value;
	 $.ajax({
		 url:"/luntanSSM/section/checkSectionName.do",
		 type:"POST",
		 data:"sectionName=" + sectionName,
		 success:function(result){
			 if(result.code == 100){
				 show_validate_msg("#sectionName_update_input","success",g_sectionName_valid);
				 $("#section_update_done_btn").attr("ajax-check-sectionName","success");
			 }else{
				 show_validate_msg("#sectionName_update_input","error",result.extend.sectionName_msg)
				 $("#section_update_done_btn").attr("ajax-check-sectionName","error"); 
			 }
		 }
	 });
 });
 
 function getSection(id){
   $.ajax({
	 url:"/luntanSSM/section/get.do?id="+id,
	 type:"get",
	 success:function(result){
	   console.log(result);
	   var sectionData = result.extend.section;
	   $("#sectionName_update_input").val(sectionData.sectionName);
	 }
	 
   })
 }
 
//点击更新，更新员工信息
 $("#section_update_done_btn").click(function(){
	//判断之前的ajax用户名和账号重复性校验是否成功
	 if($(this).attr("ajax-check-sectionName") == "error"){
		 return false;
	 }
 	
	saveData();
 	
 });
 
 //2、发送ajax请求保存更新的员工数据，保存事件
 function saveData(){
	 $.ajax({
		 url:"/luntanSSM/section/update.do?id="+$("#section_update_done_btn").attr("edit-id"),
		 type:"post",
		 data:$("#sectionUpdateModal form").serialize()+"&_method=put",
		 success:function(result){
			 //alert(result);
			 //console.log(result);
			 if(result.code=100){
				 //1、员工修改成功，需要关闭模态框。
				 $("#sectionUpdateModal").dialog("close"); 
				 //2、来到最后一页，显示刚才的数据；发送ajax请求，显示最后一页数据即可(用总记录数请求，保证是请求足够大，mybatis 分页插件 已经在mybatis-config.xml中配置了数据合法性校验，只会返回最后一页数据)
				 to_page(currentPage);
			 }else{
				 //后端校验  显示失败信息
				 if(undefined!=result.extend.errorFileds.email){
					 //显示用户名错误信息
					 show_validate_msg("#sectionName_update_input","error",result.extend.errorFileds.sectionName);
				 }
			 }		
		 }//success 方法结束
	 });
 }
 
 /****************************修改信息结束****************************************************************************************/
 