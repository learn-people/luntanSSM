
/*************************************修改用户模块**********************************************************************************/
 
/**
 * 更改的地方多了去了，好累的啊！！！
 * 
 * */
$(document).on("click",".edit-btn",function(){
	 //0、清空表单样式和内容
	 reset_form("#userdetailUpdateModal form");
	 
	 //2、查询员工信息，并显示员工信息  // 坑爹 一开始写成了 attr(edit-id) 
	 getUserdetail($(this).attr("edit-id"));
	 
	 //3、把员工的id传递给模态框的更新按钮，为了发送ajax时传递id
	 $("#userdetail_update_done_btn").attr("edit-id",$(this).attr("edit-id"));
	 $("#userdetailUpdateModal").dialog("open");
 });
 
 function getUserdetail(id){
   $.ajax({
	 url:"/luntanSSM/userdetail/get.do?id="+id,
	 type:"get",
	 success:function(result){
	   console.log(result);
	   var userdetailData = result.extend.userdetail;
	   $("#userNum_update_input").val(userdetailData.userNum);
	   $("#userdetailUpdateModal input[name=gender] ").val([userdetailData.gender]);
	   $("#birthday_update_input").val(userdetailData.birthday);
	   $("#autography_update_input").val(userdetailData.autography);
	   $("#school_update_input").val(userdetailData.school);
	   $("#job_update_input").val(userdetailData.job);
	   $("#hometown_update_input").val(userdetailData.hometown);
	 }
	 
   })
 }
 
//点击更新，更新员工信息
 $("#userdetail_update_done_btn").click(function(){
		saveData();
 });
 
 //2、发送ajax请求保存更新的员工数据，保存事件
 function saveData(){
	 $.ajax({
		 url:"/luntanSSM/userdetail/update.do?id="+$("#userdetail_update_done_btn").attr("edit-id"),
		 type:"post",
		 data:$("#userdetailUpdateModal form").serialize()+"&_method=put",
		 success:function(result){
				 $("#userdetailUpdateModal").dialog("close"); 
				 //2、来到最后一页，显示刚才的数据；发送ajax请求，显示最后一页数据即可(用总记录数请求，保证是请求足够大，mybatis 分页插件 已经在mybatis-config.xml中配置了数据合法性校验，只会返回最后一页数据)
				 to_page(currentPage);
		 }//success 方法结束
	 });
 }
 
 /****************************修改信息结束****************************************************************************************/
 