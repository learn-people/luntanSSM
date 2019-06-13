/****************************删除信息********************************************************************************************/

 // delete-btn 这个是在一开始展示表格信息的时候，就已经添加了。
 $(document).on("click",".delete-btn",function(){
   //弹出是否确认删除对话框
   //alert($(this).parents("tr").find("td:eq(1)").text());
   var postTitle= $(this).parents("tr").find("td:eq(1)").text()
   var id = $(this).attr("delete-id");
   if(confirm("确认删除【"+postTitle+"】")){
     $.ajax({
       url:"/luntanSSM/post/deleteOne.do?id="+id,
       type:"delete",
       success:function(result){
         alert(result.msg);
         to_page(currentPage);
       }
     });
   }      
 });
              
//完成全选、全不选功能
 $("#check_all").click(function(){
 	// attr获取checked是undefined;
 	// alert($(this).attr("checked"));  弹出的是 undefined
 	// 用prop修改和读取dom原生属性的值；用attr获取自定义属性的值；这样就不容易出错			
 	// alert($(this).prop("checked")); 
    // 可以正常弹出结果,也就是说：$(this).prop("checked") 返回是否选中的  true or false
 	// 设置 是否 checked  $(this).prop("checked",true/false)
 	//设置全选和全不选
 	$(".check-item").prop("checked",$(this).prop("checked"));
 });
 		
 //check_item 触发回调函数
 $(document).on("click",".check-item",function(){
 	//判断当前选择中的元素是否5个
 	var flag = $(".check-item:checked").length==$(".check-item").length;
 	$("#check_all").prop("checked",flag);
 });
 		
 //点击全部删除，就是批量删除
 $("#post_delete_all_btn").click(function(){			
 			
 	var postTitles ="";
 	//组织员工id字符串
 	var delete_ids_str="";
 			
 	$.each($(".check-item:checked"),function(){
 		postTitles = postTitles+$(this).parents("tr").find("td:eq(2)").text() +",";
 	    delete_ids_str += $(this).parents("tr").find("td:eq(1)").text() +"-";
 	});
 			
 	//去除adminNames多余的，
 	postTitles = postTitle.substring(0,postTitle.length-1);
 	//去除删除的id的多余的-
 	delete_ids_str = delete_ids_str.substring(0,delete_ids_str.length-1);
 			
 	if(confirm("确认删除【"+postTitles+"】?")){
 	//发送ajax请求
 	  $.ajax({
		url:"/luntanSSM/post/delete.do?ids="+delete_ids_str,
		type:"delete",
		success:function(result){
			alert(result.msg);
			to_page(currentPage);
		}
	  });
 	}
 });
 /*****************************删除功能结束*******************************************************************/