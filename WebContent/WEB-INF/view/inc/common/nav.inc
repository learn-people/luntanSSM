<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<% String panelIndex=request.getParameter("panelIndex");%>
<div class="easyui-accordion" style="width:100%;">
	<div title="管理员管理" id="advert" <%if("0".equals(panelIndex)){%> data-options="selected:true"<%}%>>
		<a href="${pageContext.request.contextPath}/admin/select.do"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-large-smartart'" style="width:100%;text-align:left;margin-top:5px;">管理员界面</a>
	</div>
	<div title="贴子管理" id="homepage" <%if("1".equals(panelIndex)){%> data-options="selected:true"<%}%>>
	<a href="${pageContext.request.contextPath}/post/select.do" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-tip'" style="width:100%;text-align:left;margin-top:5px;">发帖界面</a>
	<a href="${pageContext.request.contextPath}/section/select.do" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'" style="width:100%;text-align:left;margin-top:5px;">板块管理</a>
	</div>
	<div title="板块管理" id="guidepage" <%if("2".equals(panelIndex)){%> data-options="selected:true"<%}%>>
		<a href="promotionManage.jsp?panelIndex=1" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'" style="width:100%;text-align:left;margin-top:5px;">谈天说地</a>
		<a href="promotionManage.jsp?panelIndex=1" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'" style="width:100%;text-align:left;margin-top:5px;">游戏交流</a>
		<a href="promotionManage.jsp?panelIndex=1" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'" style="width:100%;text-align:left;margin-top:5px;">游戏攻略</a>
		<a href="promotionManage.jsp?panelIndex=1" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'" style="width:100%;text-align:left;margin-top:5px;">交易购物</a>
	</div>
	<div title="评论管理" id="guidepage" <%if("3".equals(panelIndex)){%> data-options="selected:true"<%}%>>
		<a href="${pageContext.request.contextPath}/comment/select.do" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'" style="width:100%;text-align:left;margin-top:5px;">评论管理</a>
	</div>
	<div title="用户管理" id="guidepage" <%if("4".equals(panelIndex)){%> data-options="selected:true"<%}%>>
		<a href="${pageContext.request.contextPath}/user/select.do" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'" style="width:100%;text-align:left;margin-top:5px;">用户界面</a>
		<a href="${pageContext.request.contextPath}/userdetail/insert.do" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'" style="width:100%;text-align:left;margin-top:5px;">用户详情信息界面</a>
		<a href="${pageContext.request.contextPath}/followed/select.do" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'" style="width:100%;text-align:left;margin-top:5px;">关注列表</a>
		<a href="${pageContext.request.contextPath}/fans/select.do" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'" style="width:100%;text-align:left;margin-top:5px;">粉丝列表</a>
	</div>
	<div title="用户注销" id="guidepage" <%if("4".equals(panelIndex)){%> data-options="selected:true"<%}%>>
	<a href="${pageContext.request.contextPath}/user/outLogin.do" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-print'" style="width:100%;text-align:left;margin-top:5px;">注销登录</a>
	</div>
</div>
<style>
 #advert a:visited{ 
	background-color:yellow;
}
</style>