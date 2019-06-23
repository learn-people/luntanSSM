<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 员工添加的模态框 -->
<div id="followedAddModal" class="easyui-dialog" data-options="title:'添加关注者',  closed:true" style="width:450px;height:500px;padding:20px 30px;">
   
  <div class="modal-body"> 
  <form>
  	<div class="form-group row">
  		<label class="col-sm-3 col-form-label">被关注者id</label>
  		<div class="col-sm-9">
  			<input type="text"  class="form-control" id="followedUserId_add_input"  name="followedUserId" >
  		</div>
  	</div>

  	<div class="form-group row">
  		<label class="col-sm-3 col-form-label">粉丝id</label>
  		<div class="col-sm-9">
  			<input type="number"  class="form-control" id="userId_add_input" name="userId">
  		</div>
  	</div>

  </form>
  </div>
  
  <div class="modal-footer" id="btn-guide">
    <button type="button" class="easyui-linkbutton"data-dismiss="modal" onclick="javascript:$('#followedAddModal').dialog('close')">关闭</button>
    <button type="button" class="easyui-linkbutton" id="followed_add_save_btn" >保存</button>
  </div>
      
</div>
