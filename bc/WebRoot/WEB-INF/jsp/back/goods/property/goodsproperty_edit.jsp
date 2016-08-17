<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8" />
		<title></title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="static/css/bootstrap.min.css" rel="stylesheet" />
		<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/css/font-awesome.min.css" />
		<!-- 下拉框 -->
		<link rel="stylesheet" href="static/css/chosen.css" />
		
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		
		<link rel="stylesheet" href="static/css/datepicker.css" /><!-- 日期框 -->
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
<script type="text/javascript">
	
	
	//保存
	function save(){
			if($("#TYPE_ID").val()==""){
			$("#TYPE_ID").tips({
				side:3,
	            msg:'请输入所属商品类型',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TYPE_ID").focus();
			return false;
		}
		if($("#OPTIONS_STATUS").val()==""){
			$("#OPTIONS_STATUS").tips({
				side:3,
	            msg:'请输入属性是否可选',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#OPTIONS_STATUS").focus();
			return false;
		}
		if($("#PLANT_STATUS").val()==""){
			$("#PLANT_STATUS").tips({
				side:3,
	            msg:'请输入录入方式',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PLANT_STATUS").focus();
			return false;
		}
		if($("#VALUE_LIST").val()==""){
			$("#VALUE_LIST").tips({
				side:3,
	            msg:'请输入属性列表值',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#VALUE_LIST").focus();
			return false;
		}
		if($("#NAME").val()==""){
			$("#NAME").tips({
				side:3,
	            msg:'请输入属性名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#NAME").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="goodsproperty/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="ID" id="ID" value="${pd.ID}"/>
		<div id="zhongxin">
		<table>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="number" name="TYPE_ID" id="TYPE_ID" value="${pd.TYPE_ID}" maxlength="32" placeholder="这里输入所属商品类型" title="所属商品类型"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="number" name="OPTIONS_STATUS" id="OPTIONS_STATUS" value="${pd.OPTIONS_STATUS}" maxlength="32" placeholder="这里输入属性是否可选" title="属性是否可选"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="number" name="PLANT_STATUS" id="PLANT_STATUS" value="${pd.PLANT_STATUS}" maxlength="32" placeholder="这里输入录入方式" title="录入方式"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="VALUE_LIST" id="VALUE_LIST" value="${pd.VALUE_LIST}" maxlength="32" placeholder="这里输入属性列表值" title="属性列表值"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="NAME" id="NAME" value="${pd.NAME}" maxlength="32" placeholder="这里输入属性名称" title="属性名称"/></td>
			</tr>
			<tr>
				<td style="text-align: center;">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
		
	</form>
	
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript">
		$(top.hangge());
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
		});
		
		</script>
</body>
</html>