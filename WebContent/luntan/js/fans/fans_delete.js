/****************************删除信息********************************************************************************************/

 // delete-btn 这个是在一开始展示表格信息的时候，就已经添加了。
 $(document).on("click",".delete-btn",function(){
   //弹出是否确认删除对话框
   //alert($(this).parents("tr").find("td:eq(1)").text());
   var userId = $(this).parents("tr").find("td:eq(1)").text();
   var followedUserId = $(this).parents("tr").find("td:eq(5)").text();
   alert(userId);
   alert(followedUserId);
   if(confirm("确认删除【"+userId+"】")){
     $.ajax({
       url:"/luntanSSM/fans/delete.do?userId="+userId+"&followedUserId="+followedUserId,
       type:"delete",
       success:function(result){
         alert(result.msg);
         to_page(currentPage);
       }
     });
   }      
 });

 /*****************************删除功能结束*******************************************************************/