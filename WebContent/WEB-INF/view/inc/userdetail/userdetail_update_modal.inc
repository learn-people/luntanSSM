<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 员工信息修改的模态框 -->
<div id="userdetailUpdateModal" class="easyui-dialog" data-options="title:'员工信息修改',closed:true" style="width:450px;height:500px;padding:20px 30px;">
   
  <div class="modal-body"> 
  <form>

  	<div class="form-group row">
  		<label class="col-sm-3 col-form-label">个性签名</label>
  			<div class="col-sm-9">
  			<input type="textarea"  class="form-control" id="autography_update_input" name="autography">
  		</div>
  	</div>

  	<div class="form-group row">
  		<label class="col-sm-3 col-form-label">学校</label>
  			<div class="col-sm-9">
  			<input type="text"  class="form-control" id="school_update_input" name="school" >
  		</div>
  	</div>


  	<div class="form-group row">
  		<label class="col-sm-3 col-form-label">职业</label>
  			<div class="col-sm-9">
  			<input type="text"  class="form-control" id="job_update_input" name="job">
  		</div>
  	</div>
  	
  	<div class="form-group row">
  	<label class="col-sm-3 col-form-label">家庭住址</label>
  	<div class="col-sm-9">
  	<input type="text"  class="form-control" id="hometown_update_input" name="hometown">
  	</div>
  	</div>
  	
  </form>
  </div>
  
  <div class="modal-footer" id="btn-guide">
    <button type="button" class="easyui-linkbutton"data-dismiss="modal" onclick="javascript:$('#userdetailUpdateModal').dialog('close')">关闭</button>
    <button type="button" class="easyui-linkbutton" id="userdetail_update_done_btn" >保存</button>
  </div>
      
</div>