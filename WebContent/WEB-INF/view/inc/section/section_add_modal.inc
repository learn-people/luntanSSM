<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 板块添加的模态框 -->
<div id="sectionAddModal" class="easyui-dialog" data-options="title:'板块添加',closed:true" style="width:450px;height:300px;padding:20px 30px;">
<div class="modal-body"> 
  <form>
  	<div class="form-group row">
  		<label class="col-sm-3 col-form-label">板块名</label>
  			<div class="col-sm-9">
  			<input type="text"  class="form-control" id="sectionName_add_input" name="sectionName" >
  			<div class="invalid-feedback">昵称输入错误，请重新输入</div>
  		</div>
  	</div>

  </form>
  </div>
  
  <div class="modal-footer" id="btn-guide">
    <button type="button" class="easyui-linkbutton"data-dismiss="modal" onclick="javascript:$('#sectionAddModal').dialog('close')">关闭</button>
    <button type="button" class="easyui-linkbutton" id="section_add_save_btn" >保存</button>
  </div>
      
</div>
