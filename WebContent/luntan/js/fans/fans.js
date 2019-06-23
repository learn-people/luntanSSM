

 /*****************添加关注信息模块**************************************/
//添加模态框的事件

/**
 * 要改的将会很多
 * */
 $("#fans_add").click(function(){
  $("#fansAddModal").dialog("open");
 });
 
//添加模态框 点击保存按钮的事件
 $("#fans_add_save_btn").click(function(){
		add_saveData();
 });
 //保存数据
 function add_saveData(){			
	 $.ajax({
		 url:"/luntanSSM/fans/insert.do",
		 type:"POST",
		 data:$("#fansAddModal form").serialize(), 
		 success:function(result){
			 //经过测试，服务器返回的是 json形式的 Msg对象， 到了浏览器中 变成了 result。所以: result.msg 相当于Msg对象里面的msg属性
			 console.log(result);
			 $("#fansAddModal").dialog("close"); 			  
			 to_page(totalRecords);	
			 
			 //调用清空表单的方法
			 reset_form("#fansAddModal form");
		 }
	 }); 
 }
 
 /*************************************添加关注者信息模块结束*******************************************************************************/
