
/*************************************修改用户模块**********************************************************************************/
 
/**
 * 更改的地方多了去了，好累的啊！！！
 * 
 * */
$(document).on("click",".edit-btn",function(){
	 //0、清空表单样式和内容
	 reset_form("#userUpdateModal form");
	 
	 //2、查询员工信息，并显示员工信息  // 坑爹 一开始写成了 attr(edit-id) 
	 getUser($(this).attr("edit-id"));
	 
	 //3、把员工的id传递给模态框的更新按钮，为了发送ajax时传递id
	 $("#user_update_done_btn").attr("edit-id",$(this).attr("edit-id"));
	 $("#userUpdateModal").dialog("open");
 });
 
 //校验用户名是否可用，需要在保存按钮提交前进行判断，自定义属性留个记号，让保存按钮可用来判断后端校验情况。
 $("#userName_update_input").change(function(){
	 if(!validate_form_ele("#userName_update_input",g_userName_reg,g_userName_valid,g_userName_invalid_exist)){
	   return false;
	 } 
	 //发送ajax请求，校验用户名是否可用
	 var userName = this.value;
	 $.ajax({
		 url:"/luntanSSM/user/checkUserName.do",
		 type:"POST",
		 data:"userName=" + userName,
		 success:function(result){
			 if(result.code == 100){
				 show_validate_msg("#userName_update_input","success",g_userName_valid);
				 $("#user_update_done_btn").attr("ajax-check-userName","success");
			 }else{
				 show_validate_msg("#userName_update_input","error",result.extend.userName_msg)
				 $("#user_update_done_btn").attr("ajax-check-userName","error"); 
			 }
		 }
	 });
 });
 
 function getUser(id){
   $.ajax({
	 url:"/luntanSSM/user/get.do?id="+id,
	 type:"get",
	 success:function(result){
	   console.log(result);
	   var userData = result.extend.user;
	   $("#userNumber_update_input").val(userData.userNumber);
	   $("#userPassword_update_input").val(userData.userPassword);
	   $("#userName_update_input").val(userData.userName);
	   $("#userUpdateModal input[name=gender] ").val([userData.gender]);
	   $("#birthday_update_input").val(userData.birthday);
	   $("#update_imgUrl").val(userData.imgUrl);
	   update_imgName = userData.update_imgName;
	 }
	 
   })
 }
 
 //点击更新，更新员工信息
 $("#user_update_done_btn").click(function(){
	//判断之前的ajax用户名和账号重复性校验是否成功
	 if($(this).attr("ajax-check-userName") == "error"){
		 return false;
	 }
 	//验证用户名是否合法
 	if(!validate_form_ele("#userName_update_input",g_userName_reg,g_userName_valid,g_userName_invalid_format)){
         return false;
 	}
 	//查看是否添加了图片
 	if ($("#update_imgName").val()) {
		// 如果没有重新选择图片，就直接修改文本数据，否则先上传图片再修改数据
		if ($("#update_imgName").val() == update_imgName) {
			saveData()
		} else {
			/**
			 * 跟随下方的七牛上传方法名
			 * */
			uploader2.start(); // 单击保存按钮后先上传图片，然后添加/修改数据到服务器
		}
	} else {
		saveData();
	}
 	
 });
 
 //2、发送ajax请求保存更新的员工数据，保存事件
 function saveData(){
	 $.ajax({
		 url:"/luntanSSM/user/update.do?id="+$("#user_update_done_btn").attr("edit-id"),
		 type:"post",
		 data:$("#userUpdateModal form").serialize()+"&_method=put",
		 success:function(result){
			 //alert(result);
			 //console.log(result);
			 if(result.code=100){
				 //1、员工修改成功，需要关闭模态框。
				 $("#userUpdateModal").dialog("close"); 
				 //2、来到最后一页，显示刚才的数据；发送ajax请求，显示最后一页数据即可(用总记录数请求，保证是请求足够大，mybatis 分页插件 已经在mybatis-config.xml中配置了数据合法性校验，只会返回最后一页数据)
				 to_page(currentPage);
			 }else{
				 //后端校验  显示失败信息
				 if(undefined!=result.extend.errorFileds.email){
					 //显示用户名错误信息
					 show_validate_msg("#userName_update_input","error",result.extend.errorFileds.userName);
				 }
			 }		
		 }//success 方法结束
	 });
 }
 
 /****************************修改信息结束****************************************************************************************/
 
 /*****************************七牛云上传代码****************************************************************/
 
 /**
  * 要注意的点有browse_button,container
  * */
 
 var token = null;// 保存token
 var key = null;// 保存上传的文件名
 var timestamp = null;// 时间戳，用于重命名key
 // 配置七牛上传和plupload
 /**
  * 有多个不同的上传文件按钮时要new几个新的
  * */
 var Q2 = new QiniuJsSDK();
 var uploader2 = Q2.uploader({
 	disable_statistics_report : false, // 禁止自动发送上传统计信息到七牛，默认允许发送
 	runtimes : 'html5,flash,html4', // 上传模式，依次退化
 	browse_button : 'pickfile', // 上传选择文件的点选按钮，必需
 	container : 'container', // 上传区域DOM的ID，默认是browser_button的父元素
 	max_file_size : '1mb', // 最大文件体积限制
 	flash_swf_url : '/Plupload/js/Moxie.swf', // 引入flash，相对路径
 	dragdrop : false, // 关闭可拖曳上传
 	chunk_size : '1mb', // 分块上传时，每块的体积
 	domain : 'http://psvkcg5qy.bkt.clouddn.com/', // bucket域名，下载资源时用到，必需
 	auto_start : false, // 选择文件后自动上传，若关闭需要自己绑定事件触发上传
 	max_retries : 3, // 上传失败最大重试次数
 	save_key : false, // 为false则以上传文件的原名命名，否则随机命名，这里推荐false
 	get_new_uptoken : true,// 设置上传文件的时候是否每次都重新获取新的token
 	unique_names : false, // 自动生成文件名,如果值为false则保留原文件名上传
 	scope : {
 		bucket : key
 	},
 	// 在初始化时，uptoken，uptoken_url，uptoken_func三个参数中必须有一个被设置,uptoken是上传凭证，由其他程序生成;uptoken_url是提供了获取上传凭证的地址，如果需要定制获取uptoken的过程则可以设置uptoken_func;其优先级为uptoken
 	// > uptoken_url > uptoken_func
 	uptoken_func : function(file) { // 将key传给服务端获取的七牛token，上传后覆盖同名文件，即为更新操作
 		/**
 		 * 注意下取值方式
 		 * */
 		var val = $("#update_imgUrl").val();// val用于保存上传文件的url
 		// 如果val不为空，使用原key，否则创建新key
 		if (val) {
 			// 获取七牛存储的url中的key，比如“imgName.jpg”
 			key = val.split('/')[val.split('/').length - 1];
 		} else {
 			timestamp = (new Date()).valueOf();
 			// 以时间戳重命名图片，方便同名文件覆盖。
 			// 为了防止文件重名，在引入登录功能后还需加上userId这样的前缀
 			key = timestamp + '.' + file.type.split('/')[1]; // file.type的格式举例：“Image/jpg”
 		}
 		getToken(key);
 		return token;
 	},
 	filters : {
 		prevent_duplicates : false,// true:防止重复，过滤相同的文件
 		mime_types : [ {
 			title : "Image files",
 			extensions : "png,jpg,gif"
 		} // 文件类型过滤，这里限制为文件类型
 		]
 	},
 	init : {
 		'FilesAdded' : function(up, files) { // 文件添加进队列后，处理相关的事情
 			plupload.each(files, function(file) {
 				$("#update_imgName").val(file.name); // easyUI的textbox设置值
 			});
 		},
 		'BeforeUpload' : function(up, file) { // 每个文件上传前，处理相关的事情
 		},
 		'UploadProgress' : function(up, file) { // 每个文件上传时，处理相关的事情
 			console.log("上传中");
 			$("#bar").css("width", file.percent + '%');
 			$("#bar").html(file.percent + '%');
 		},
 		'FileUploaded' : function(up, file, info) { // 每个文件上传成功后，处理相关的事情
 			var domain = up.getOption('domain');
 			var data = JSON.parse(info.response);
 			$("#update_imgUrl").val(domain + data.key);
 			
 			/**
 			 * 注意下外部的保存方法的名称
 			 * */
 			saveData();
 		},
 		'Error' : function(up, err, errTip) {
 			console.log("上传出错")
 		},
 		'UploadComplete' : function() {
 			// 队列文件处理完毕后，处理相关的事情
 		},
 		'Key' : function(up, file) {
 			// 若想在前端对每个文件的key进行个性化处理，可以配置该函数
 			// 比如设置了同名文件覆盖上传，那么生成的覆盖上传token的key，必须和上传的key一致
 			return key;
 		}
 	}
 });

 // 获取token，换表时不用变。
 function getToken(key) {
 	$.ajax({
 		url : '/luntanSSM/token/get.do',
 		type : 'post',
 		async : false,
 		data : {
 			"key" : key
 		},
 		success : function(data) {
 			var obj = JSON.parse(data);
 			token = obj.token;
 		}
 	});
 }
 