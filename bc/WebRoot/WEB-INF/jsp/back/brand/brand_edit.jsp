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
			if($("#NAME").val()==""){
			$("#NAME").tips({
				side:3,
	            msg:'请输入品牌名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#NAME").focus();
			return false;
		}
		if($("#LOGO_IMAGE").val()==""){
			$("#LOGO_IMAGE").tips({
				side:3,
	            msg:'请输入品牌LOGO',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#LOGO_IMAGE").focus();
			return false;
		}
		if($("#DESCRIPTION").val()==""){
			$("#DESCRIPTION").tips({
				side:3,
	            msg:'请输入品牌描述',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#DESCRIPTION").focus();
			return false;
		}
		if($("#IS_SHOW").val()==""){
			$("#IS_SHOW").tips({
				side:3,
	            msg:'请输入是否显示',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#IS_SHOW").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="brand/${msg}.do" name="Form" id="Form" method="post" enctype="multipart/form-data">
		<input type="hidden" name="ID" id="ID" value="${pd.ID}"/>
		<div id="zhongxin">
		<table>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="NAME" id="NAME" value="${pd.NAME}" maxlength="32" placeholder="这里输入品牌名称" title="品牌名称"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="URL" id="URL" value="${pd.URL}" maxlength="32" placeholder="这里输入品牌网址" title="品牌网址"/></td>
			</tr>
			<tr>
				<c:if test="${pd.ID!=null}">
				<input type="hidden" id="LOGO_IMAGE" name = "LOGO_IMAGE" value="${pd.LOGO_IMAGE}"  maxlength="32"/>
				<td>品牌Logo：<input style="width:400px;margin-top:5px;" type="file" name="LOGO_IMAGE" maxlength="32" placeholder="这里输入品牌LOGO" title="品牌LOGO"/></td>
				</c:if>
				<c:if test="${pd.ID==null}">
				<td>品牌Logo：<input style="width:400px;margin-top:5px;" type="file" name="LOGO_IMAGE" value="${pd.LOGO_IMAGE}" maxlength="32" placeholder="这里输入品牌LOGO" title="品牌LOGO"/></td>
				</c:if>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="DESCRIPTION" id="DESCRIPTION" value="${pd.DESCRIPTION}" maxlength="32" placeholder="这里输入品牌描述" title="品牌描述"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="number" name="SORT" id="SORT" value="${pd.SORT}" maxlength="32" placeholder="排序" title="排序"/></td>
			</tr>
			<tr>
				<td>是否显示： 
					<select class="chzn-select" name="IS_SHOW" id="IS_SHOW"  data-placeholder="请选择" style="vertical-align:top;width: 120px;">
						<option value="0" <c:if test="${pd.IS_SHOW=='0'}"> selected="selected"</c:if>>是</option>
						<option value="1" <c:if test="${pd.IS_SHOW=='1'}"> selected="selected"</c:if>>否</option>
				  	</select>
				</td>
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
			
			//单选框r
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
		});
		
		</script>
</body>
</html>