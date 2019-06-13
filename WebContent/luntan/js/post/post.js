

 /*****************添加用户信息模块**************************************/
//添加模态框的事件

/**
 * 要改的将会很多
 * */
 $("#post_add").click(function(){
	 
   getSection("#postAddModal #section_add_select");
   $("#postAddModal").dialog("open");
 });
 
 //获取板块信息
 function getSection(ele){
	 $.ajax({
	   url:"/luntanSSM/section/get.do",
	   type:"GET",
	   success:function(result){
	     console.log(result);
	     //清除之前留下的 option 标签
	     $(ele).empty();
	     $.each(result.extend.list,function(){
	 	   var optionEle = $("<option></option>").attr("value",this.id).append(this.sectionName);
	       optionEle.appendTo(ele);
	     });
	   }
	 });
 }
 
//添加模态框 点击保存按钮的事件
 $("#post_add_save_btn").click(function(){
	 //查看是否添加了图片
	 if ($("#add_imgName").val()) {
		// 如果没有重新选择图片，就直接修改文本数据，否则先上传图片再修改数据
		if ($("#add_imgName").val() == add_imgName) {
			add_saveData()
		} else {
			/**
			 * 跟随下方的七牛上传方法名
			 * */
			uploader.start(); // 单击保存按钮后先上传图片，然后添加/修改数据到服务器
		}
	}else {
		add_saveData();
	}
	 
 });
 //保存数据
 function add_saveData(){			
	 $.ajax({
		 url:"/luntanSSM/post/insert.do",
		 type:"POST",
		 data:$("#postAddModal form").serialize(), 
		 success:function(result){
		   //经过测试，服务器返回的是 json形式的 Msg对象， 到了浏览器中 变成了 result。所以: result.msg 相当于Msg对象里面的msg属性
		   console.log(result);
		   //1、员工保存成功，需要关闭模态框。
		   $("#postAddModal").dialog("close"); 			  
		   //2、来到最后一页，显示刚才的数据；发送ajax请求，显示最后一页数据即可(用总记录数请求，保证是请求足够大，mybatis 分页插件 已经在mybatis-config.xml中配置了数据合法性校验，只会返回最后一页数据)
		   to_page(totalRecords);	
		   reset_form("#postAddModal form");
		 }
	 }); 
 }
 
 /*************************************添加用户模块结束*******************************************************************************/
 
 /*****************************七牛云上传代码****************************************************************/
 
 /**
  * 要注意的点有browse_button,container
  * */
 var token = null;// 保存token
 var key = null;// 保存上传的文件名
 var timestamp = null;// 时间戳，用于重命名key
 // 配置七牛上传和plupload
 /**
  * 有多个不同的上传文件按钮时要new几个新的，如：var Q2 = new QiniuJsSDK(); 取代Qinniu的名称
  * */
 var uploader = Qiniu.uploader({
 	disable_statistics_report : false, // 禁止自动发送上传统计信息到七牛，默认允许发送
 	runtimes : 'html5,flash,html4', // 上传模式，依次退化
 	browse_button : 'pickfiles', // 上传选择文件的点选按钮，必需
 	container : 'containers', // 上传区域DOM的ID，默认是browser_button的父元素
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
 		var val = $("#add_imgUrl").val();// val用于保存上传文件的url
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
 				/**
 		 		 * 注意下取值方式
 		 		 * */
 				$("#add_imgName").val(file.name); // easyUI的textbox设置值
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
 			$("#add_imgUrl").val(domain + data.key);
 			
 			/**
 			 * 注意下外部的保存方法的名称
 			 * */
 			add_saveData();
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

 // 获取token
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