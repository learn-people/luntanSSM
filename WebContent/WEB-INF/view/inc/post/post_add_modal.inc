<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 员工添加的模态框 -->
<div id="postAddModal" class="easyui-dialog" data-options="title:'添加贴子',closed:true" style="width:450px;height:500px;padding:20px 30px;">
   
  <div class="modal-body"> 
  <form>
  	<div class="form-group row">
  		<label class="col-sm-3 col-form-label">用户账号</label>
  		<div class="col-sm-9">
  			<input type="text"  class="form-control" id="userNum_add_input" name="userNum" value="${user.userNumber}" >
  		</div>
  	</div>

  	<div class="form-group row">
      <label  class="col-sm-3 col-form-label">发表板块</label>  
      <div class="col-sm-6">
        <!-- 板块提交板块id即可 -->
        <select class="custom-select my-1 mr-sm-2" name="sectionId" id="section_add_select">
        </select>
      </div>
    </div>

  	<div class="form-group row">
  		<label class="col-sm-3 col-form-label">标题</label>
  			<div class="col-sm-9">
  			<input type="text"  class="form-control" id="postTitle_add_input" name="postTitle">
  		</div>
  	</div>

  	<div class="form-group row">
  		<label class="col-sm-3 col-form-label">内容</label>
  			<div class="col-sm-9">
  			<input type="textarea"  class="form-control" id="postContent_add_input" name="postContent">
  		</div>
  	</div>
  	
  	<div id="containers" class="form-group row">
    <label class="col-sm-3 col-form-label">插入图片</label>
    <div class="col-sm-9">
      <!-- 显示文件名的文本框 -->
      <input id="add_imgName"class="form-control" readonly="readonly">
      <!-- 处理图片的url -->
      <input name="imgUrl" id="add_imgUrl" type="hidden">
      <button type="button" id="pickfiles" style="font-size:12px;display: inline">选择文件</button>
    </div>
  </div>
  
  <div class="form-group row">
  	<label  class="col-sm-3 col-form-label">阅读权限</label>  
  	<div class="col-sm-6">
    	<!-- 阅读权限提交权限值即可 -->
    	<select class="custom-select my-1 mr-sm-2" name="purview" id="purview_add_select">
    		<option> 1</option>
    		<option> 2</option>
    		<option> 3</option>
    		<option> 4</option>
    		<option> 5</option>
    	</select>
    </div>
  </div>
  </form>
  </div>
  
  <div class="modal-footer" id="btn-guide">
    <button type="button" class="easyui-linkbutton"data-dismiss="modal" onclick="javascript:$('#postAddModal').dialog('close')">关闭</button>
    <button type="button" class="easyui-linkbutton" id="post_add_save_btn" >保存</button>
  </div>
      
</div>
