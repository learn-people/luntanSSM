<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 员工添加的模态框 -->
<div id="adminUpdateModal" class="easyui-dialog" data-options="title:'员工信息修改',closed:true" style="width:450px;height:500px;padding:20px 30px;">
   
  <div class="modal-body"> 
  <form>
  	<div class="form-group row">
  		<label class="col-sm-3 col-form-label">账号</label>
  		<div class="col-sm-9">
  			<input type="text"  class="form-control" id="adminNumber_update_input"  readonly name="adminNumber" >
  		</div>
  	</div>

  	<div class="form-group row">
  		<label class="col-sm-3 col-form-label">密码</label>
  			<div class="col-sm-9">
  			<input type="password"  class="form-control" id="adminPassword_update_input" name="adminPassword">
  		</div>
  	</div>

  	<div class="form-group row">
  		<label class="col-sm-3 col-form-label">昵称</label>
  			<div class="col-sm-9">
  			<input type="text"  class="form-control" id="adminName_update_input" name="adminName" >
  			<div class="invalid-feedback">昵称输入错误，请重新输入</div>
  		</div>
  	</div>

  	<div class="form-group row">
  		<label class="col-sm-3 col-form-label">出生日期</label>
  			<div class="col-sm-9">
  			<input type="date"  class="form-control" id="adminBirthday_update_input" name="adminBirthday">
  		</div>
  	</div>

  	<div class="form-group row">
  		<label class="col-sm-3 col-form-label">职务</label>
  			<div class="col-sm-9">
  			<input type="text"  class="form-control" id="position_update_input" name="position">
  		</div>
  	</div>
  	
  	<div id="container" class="form-group row">
      <label class="col-sm-3 col-form-label">背景图片</label>
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
    <button type="button" class="easyui-linkbutton"data-dismiss="modal" onclick="javascript:$('#adminUpdateModal').dialog('close')">关闭</button>
    <button type="button" class="easyui-linkbutton" id="admin_update_done_btn" >保存</button>
  </div>
      
</div>