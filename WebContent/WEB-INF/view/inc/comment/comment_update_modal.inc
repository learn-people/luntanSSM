<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 员工添加的模态框 -->
<div id="commentUpdateModal" class="easyui-dialog" data-options="title:'员工信息修改',closed:true" style="width:450px;height:500px;padding:20px 30px;">
   
  <div class="modal-body"> 
  <form>
  
	  <div class="form-group row">
			<label class="col-sm-3 col-form-label">贴子Id</label>
		  	<div class="col-sm-9">
		    <input type="text"  class="form-control" id="postId_update_input" readonly="readonly" name="postId" >
		  </div>
	  </div>

		<div class="form-group row">
			<label class="col-sm-3 col-form-label">用户账号</label>
			<div class="col-sm-9">
				<input type="text"  class="form-control" id="userNum_update_input" readonly="readonly" name="userNum" >
			</div>
		</div>

		<div class="form-group row">
			<label class="col-sm-3 col-form-label">评论内容</label>
				<div class="col-sm-9">
				<input type="text"  class="form-control" id="commentContent_update_input" name="commentContent">
			</div>
		</div>
	
		<div id="container" class="form-group row">
  		<label class="col-sm-3 col-form-label">图片</label>
  		<div class="col-sm-9">
  			<!-- 显示文件名的文本框 -->
  			<input id="update_imgName"class="form-control" readonly="readonly">
  			<!-- 处理图片的url -->
  			<input name="imgUrl" id="update_imgUrl" type="hidden">
  			<button type="button" id="pickfile" style="font-size:12px;display: inline">选择文件</button>
  	  </div>
    </div>
  </form>
  </div>
  
  <div class="modal-footer" id="btn-guide">
    <button type="button" class="easyui-linkbutton"data-dismiss="modal" onclick="javascript:$('#commentUpdateModal').dialog('close')">关闭</button>
    <button type="button" class="easyui-linkbutton" id="comment_update_done_btn" >保存</button>
  </div>
      
</div>