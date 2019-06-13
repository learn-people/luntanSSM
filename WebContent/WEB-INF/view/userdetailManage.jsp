<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>



<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入样式文件 -->
<%@include file="inc/common/head.inc" %>

<title>管理员界面</title>

<style>
  .table>tbody>tr>td, .table>thead>tr>th{
	text-align: center;
  }	
</style>

</head>
<body>
  <div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north'"
		style="height: 80px; width: 100%;">
		<!-- 引入头部inc文件 -->
		<%@include file="inc/common/header.inc"%>
	</div>
	<!-- 左侧导航 -->
	<div data-options="region:'west'" title='功能导航'  style="width: 200px;">
	<!-- 引入导航栏inc文件 -->
		<%@include file="inc/common/nav.inc"%>
	</div>
	<!-- 主体部分 -->
	<div data-options="region:'center',title:'用户信息管理'">
		<%@include file="inc/userdetail/userdetail_field.inc"%>
	</div>
  </div>
</body>
<!-- 引入模态框 -->
<%@include file="inc/userdetail/userdetail_update_modal.inc"%>
<!-- 自定义js代码 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/luntan/js/userdetail/userdetail.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/luntan/js/userdetail/userdetail_update.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/luntan/js/userdetail/userdetail_delete.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/luntan/js/userdetail/util.js"></script>

</html>