<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 按钮 -->
<div class="row" style="margin:5px 15px;">
 <div class="ml-auto mr-5">
 <button class="easyui-linkbutton" id="followed_add" data-options="iconCls:'icon-add'">
 <i class="icon-file-alt icon-large" ></i>  新增
 </button>
 <button class="easyui-linkbutton" id="followed_delete_all_btn" data-options="iconCls:'icon-cancel'">
 <i class="icon-trash icon-large"></i> 删除
 </button>
 </div>
</div>

<!-- 引导页管理页面的数据表格 -->
<table id="followed_advert"  class="table table-hover"
       data-options="singleSelect:true,fit:true,toolbar:'#tb',
       pagination:true,pageSize:30,pageList:[10,20,30,50,100],
       onHeaderContextMenu:createGridHeaderContextMenu,
       loadMsg:"'数据正在努力加载，请稍后...'" >
  <thead>
	  <th> <input type="checkbox" id="check_all" /></th>
      <th data-options="field:'id',width:40,align:'center'" style="display:none">编号</th>
      <th data-options="field:'followedUserId',width:40,align:'center'">被关注者id</th>
      <th data-options="field:'user.userNumber',width:200,align:'center'" >被关注者账号</th>
      <th data-options="field:'user.userName',width:200,align:'center'" >被关注者名称</th>
      <th data-options="field:'user.gender',width:50,align:'center'" >被关注者性别</th>
      <th data-options="field:'user.imgUrl',width:200,align:'center'" >被关注者头像</th>
      <th data-options="field:'userId',width:200,align:'center'" >关注者Id</th>
      <th data-options="field:'setting',width:40,align:'center'" >操作</th>
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