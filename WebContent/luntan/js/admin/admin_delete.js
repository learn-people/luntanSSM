/****************************删除信息********************************************************************************************/

 // delete-btn 这个是在一开始展示表格信息的时候，就已经添加了。
 $(document).on("click",".delete-btn",function(){
   //弹出是否确认删除对话框
   //alert($(this).parents("tr").find("td:eq(1)").text());
   var adminName = $(this).parents("tr").find("td:eq(1)").text()
   var id = $(this).attr("delete-id");
   if(confirm("确认删除【"+adminName+"】")){
     $.ajax({
       url:"/luntanSSM/admin/deleteOne.do?id="+id,
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
 	/***
 	//获取id为p的标签的属性值
         $('#p').attr('class')
         //如果存在就是修改，如果不存在就是添加
         $('#p').attr('class','red')
         // 删除某个属性
         $('#p').removeAttr('name')
         //添加class名称
         $('#p').addClass('blue')
         //删除class名称
         $('#p').removeClass('yellow')
         //有class就删除 没有就添加
         $('#p').toggleClass('asdf')
         //val()获取输入框内容
         var s = $('input[name="user"]').val()
 	***/
 			
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
 $("#admin_delete_all_btn").click(function(){			
 			
 	var adminNames ="";
 	//组织员工id字符串
 	var delete_ids_str="";
 			
 	$.each($(".check-item:checked"),function(){
 		adminNames = adminNames+$(this).parents("tr").find("td:eq(2)").text() +",";
 	    delete_ids_str += $(this).parents("tr").find("td:eq(1)").text() +"-";
 	});
 			
 	//去除adminNames多余的，
 	adminNames = adminNames.substring(0,adminNames.length-1);
 	//去除删除的id的多余的-
 	delete_ids_str = delete_ids_str.substring(0,delete_ids_str.length-1);
 			
 	if(confirm("确认删除【"+adminNames+"】?")){
 	//发送ajax请求
 	  $.ajax({
		url:"/luntanSSM/admin/delete.do?ids="+delete_ids_str,
		type:"delete",
		success:function(result){
			alert(result.msg);
			to_page(currentPage);
		}
	  });
 	}
 });
 /*****************************删除功能结束*******************************************************************/