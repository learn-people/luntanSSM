 //定义 全局变量，总记录数
 var totalRecords ;
 var currentPage;
 
 /**
  * 以下不用更改
  * */
//表单内容和样式重置方法
 function reset_form(ele){
   //重置表单内容
   $(ele)[0].reset();
 
   //清空表单样式
   $(ele).find("*").removeClass("is-invalid is-valid");
   $(ele).find(".valid-feedback .invalid-feedback").text("");
 }
 
//页面加载完成以后，直接发送ajax请求，拿到分页数据
 
 //1、页面加载完成以后，直接去发送ajax请求,要到分页数据
 $(function(){
 //去首页
   to_page(1);
 });
 /*******************公共方法（上面）********************************/
 
 /*******************获取信息并分页*********************************/
 /**
  * 换表是要更改url地址第65行
  *  
  */
 function to_page(pn){
   $.ajax({
	 url:"/luntanSSM/post/selectAll.do",
     data:"pn="+pn,
     type:"GET",
     success:function(result){
    	 console.log(result);
     //console.log(result);
     //1、解析并显示员工数据
     build_post_advert(result);
     //2、解析并显示分页信息
     build_page_info(result);
     //3、解析显示分页条数据
     build_page_nav(result);
     }
   });
 }
 
/**
 * 换表时要更改名称大部分都要改
 * 时间：2019/6/11
 * 
 */
 function build_post_advert(result) {
	 //清空table表格,如果不请空，下一次请求时，数据会在原来的基础上，不断添加。
	 $("#post_advert tbody").empty();
	 var post = result.extend.pageInfo.list;
	
	 $.each(post, function(index, item) {
		 //alert(item.empName);
		 //构建单元格
		 var checkBoxTd = $("<td><input type='checkbox' class='check-item' /></td>");
		 var postIdTd = $("<td></td>").append(item.id);
		 var userNameTd = $("<td></td>").append(item.user.userName);
		 var sectionNameTd = $("<td></td>").append(item.section.sectionName);
		 var postTitleTd = $("<td></td>").append(item.postTitle);
		 var postContentTd = $("<td></td>").append(item.postContent);
		 var postTimeTd = $("<td></td>").append(item.postTime);
		 var imgUrlTd = $("<td></td>").append(item.imgUrl);
		 var purviewTd = $("<td></td>").append(item.purview);
		 //设置id属性为不可见
		 postIdTd.attr("style","display:none");
		 
		 var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm edit-btn")
		 .append($("<i></i>").addClass("icon-edit icon-large")).append(" 编辑");
		 //为编辑按钮添加一个自定义的属性，来表示当前员工id
		 editBtn.attr("edit-id",item.id);
		 var deleteBtn =  $("<button></button>").addClass("btn btn-danger btn-sm delete-btn")
		 .append($("<i></i>").addClass("icon-trash icon-large")).append(" 删除");
	     //为删除按钮添加一个自定义的属性来表示当前删除的员工id
	     deleteBtn.attr("delete-id",item.id);
	     var btnTd = $("<td></td>").append(editBtn).append(" ").append(deleteBtn);
	 
	     
	     //append方法执行完成以后还是返回原来的元素
		 $("<tr></tr>")
		 .append(checkBoxTd)
		 .append(postIdTd)
		 .append(userNameTd)
		 .append(sectionNameTd)
		 .append(postTitleTd)
		 .append(postContentTd)
		 .append(postTimeTd)
		 .append(imgUrlTd)
		 .append(purviewTd)
		 //.append(editBtn)
		 //.append(deleteBtn)
		 .append(btnTd)
		 .appendTo("#post_advert tbody");
	 
	 });
 }

 /**
  * 以下不用更改
  * */
//解析显示分页信息
 function build_page_info(result){
 //请空之前的分页显示信息，如果不请空，下一次请求时，数据会在原来的基础上，不断添加。
   $("#page_info_area").empty();
 
   $("#page_info_area").append("当前第"+result.extend.pageInfo.pageNum+
   "页，总共"+result.extend.pageInfo.pages+"页,总"+result.extend.pageInfo.total+"记录");
   
   currentPage = result.extend.pageInfo.pageNum;
   totalRecords = result.extend.pageInfo.total;
 }
 
   //解析显示分页条
 function build_page_nav(result) {
     //page_nav_area
     //请空之前的分页条，如果不请空，下一次请求时，数据会在原来的基础上，不断添加。
     $("#page_nav_area").empty();
 
     var ul = $("<ul></ul>").addClass("pagination");
 
     //构建元素
     var firstPageLi = $("<li></li>").addClass("page-item").append(  $("<a></a>").addClass("page-link").attr("href","#").append("首页")  );
     var prePageLi = $("<li></li>").addClass("page-item").append(  $("<a></a>").addClass("page-link").append("&laquo;")  );
 
     var lastPageLi = $("<li></li>").addClass("page-item").append(  $("<a></a>").addClass("page-link").attr("href","#").append("末页")   );
     var nextPageLi = $("<li></li>").addClass("page-item").append(  $("<a></a>").addClass("page-link").append("&raquo;")   );
 
     //添加首页和前一页 的提示
     ul.append(firstPageLi).append(prePageLi);
     if(result.extend.pageInfo.hasPreviousPage == false){
     firstPageLi.addClass("disabled");
     prePageLi.addClass("disabled");
   }else{
   //为元素添加点击翻页的事件
     firstPageLi.click(function(){
       to_page(1);
     });
     prePageLi.click(function(){
       to_page(result.extend.pageInfo.pageNum -1);
     });
   }
 
   //遍历页码号1,2，3等，给ul中添加页码提示
   $.each(result.extend.pageInfo.navigatepageNums,function(index,item){
     var numLi = $("<li></li>").addClass("page-item").append(  $("<a></a>").addClass("page-link").append(item)  );
     if(result.extend.pageInfo.pageNum == item){
       numLi.addClass("active");
     }
 
     numLi.click(function(){
       to_page(item);
     });
 
     ul.append(numLi);
   })
 
   //添加下一页和末页 的提示
   ul.append(nextPageLi).append(lastPageLi);
   if(result.extend.pageInfo.hasNextPage == false){
     nextPageLi.addClass("disabled");
     lastPageLi.addClass("disabled");
   }else{
     nextPageLi.click(function(){
     to_page(result.extend.pageInfo.pageNum +1);
   });
     lastPageLi.click(function(){
       to_page(result.extend.pageInfo.pages);
     });
   }
 
   //把ul加入到nav
   var navEle = $("<nav></nav>").append(ul);
     navEle.appendTo("#page_nav_area");
  }
 
 /*****************加载网页模块结束*******************************/