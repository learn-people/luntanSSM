<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 按钮 -->
<div class="row" style="margin:5px 15px;">
 <div class="ml-auto mr-5">
 <button class="easyui-linkbutton" id="post_add" data-options="iconCls:'icon-add'">
 <i class="icon-file-alt icon-large" ></i>  新增
 </button>
 <button class="easyui-linkbutton" id="post_delete_all_btn" data-options="iconCls:'icon-cancel'">
 <i class="icon-trash icon-large"></i> 删除
 </button>
 </div>
</div>

<!-- 引导页管理页面的数据表格 -->
<table id="post_advert"  class="table table-hover"
       data-options="singleSelect:true,fit:true,toolbar:'#tb',
       pagination:true,pageSize:30,pageList:[10,20,30,50,100],
       onHeaderContextMenu:createGridHeaderContextMenu,
       loadMsg:'数据正在努力加载，请稍后...'" >
  <thead>
			<th> <input type="checkbox" id="check_all" /></th>
      <th data-options="field:'id',width:40,align:'center'" style="display:none">编号</th>
      <th data-options="field:'user.userName',width:200,align:'center'" >用户名称</th>
      <th data-options="field:'section.sectionName',width:150,align:'center'">板块名</th>
      <th data-options="field:'postTitle',width:200,align:'center'" >贴子标题</th>
      <th data-options="field:'postContent',width:100,align:'center'" >贴子内容</th>
      <th data-options="field:'postTime',width:200,align:'center'" >发表时间</th>
      <th data-options="field:'imgUrl',width:100,align:'center'" >图片地址</th>
      <th data-options="field:'purveiw',width:100,align:'center'" >阅读权限</th>
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