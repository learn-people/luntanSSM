<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!-- 引导页管理页面的数据表格 -->
<table id="userdetail_advert"  class="table table-hover"
       data-options="singleSelect:true,fit:true,toolbar:'#tb',
       pagination:true,pageSize:30,pageList:[10,20,30,50,100],
       onHeaderContextMenu:createGridHeaderContextMenu,
       loadMsg:'数据正在努力加载，请稍后...'" >
  <thead>
			<th> <input type="checkbox" id="check_all" /></th>
      <th data-options="field:'id',width:40,align:'center'" style="display:none">编号</th>
      <th data-options="field:'user.userNumber',width:200,align:'center'" >用户账号</th>
      <th data-options="field:'gender',width:150,align:'center'">性别</th>
      <th data-options="field:'birthday',width:200,align:'center'" >出生日期</th>
      <th data-options="field:'autography',width:100,align:'center'" >签名</th>
      <th data-options="field:'school',width:200,align:'center'" >学校</th>
      <th data-options="field:'job',width:100,align:'center'" >职业</th>
      <th data-options="field:'hometown',width:100,align:'center'" >家庭住址</th>
	    <th data-options="field:'setting',width:200,align:'center'" >操作</th>

  </thead>
  
  <tbody>
  
  </tbody>
  
	
</table>

<!-- 显示分页信息 -->
<div class="row">
<div class="col-md-6" id="page_info_area"></div>

<div class="col-md-6" id="page_nav_area"></div>
</div>
</div>