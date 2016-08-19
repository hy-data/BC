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
		<input type="hidden"  id="TYPE_ID" value="${pd.TYPE_ID}"/>
		<input type="hidden"  id="OPTIONS_STATUS" value="${pd.OPTIONS_STATUS}"/>
		<input type="hidden"  id="PLANT_STATUS" value="${pd.PLANT_STATUS}"/>
		<div id="zhongxin">
		<table>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="NAME" id="NAME" value="${pd.NAME}" maxlength="32" placeholder="这里输入属性名称" title="属性名称"/></td>
			</tr>
			<%-- <tr>
				<td><input style="width:400px;margin-top:5px;" type="number" name="TYPE_ID" id="TYPE_ID" value="${pd.TYPE_ID}" maxlength="32" placeholder="这里输入所属商品类型" title="所属商品类型"/></td>
			</tr> --%>
			<tr>
				<td >
					<select  data-placeholder="请选择" style="vertical-align:top;width: 120px;" name = "TYPE_ID"  id="select_type">
						
					</select>
				</td>
			</tr>
			<tr>
				<td >
					<select  data-placeholder="请选择" style="vertical-align:top;width: 120px;" name = "OPTIONS_STATUS" id = "options_status" >
						
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<select  data-placeholder="请选择" style="vertical-align:top;width: 120px;" name = "PLANT_STATUS" id = "plant_status">
						
					</select>
				</td>
			</tr>
			<tr>
				<td>可选值列表：
					<textarea id = "VALUE_LIST" name = "VALUE_LIST" style = "width:270px;height:112px;">${pd.VALUE_LIST}</textarea>
					注：多个可选值中间用“，”隔开
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
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			//获取下拉列表
			getJsonType();
			options_status();
			plant_status();
		});
		function getJsonType(){
			var TYPE_ID = $("#TYPE_ID").val();
			alert(TYPE_ID);
			$.ajax({
                url: '<%=basePath%>goodstype/getJsonType.do',
                async:false, 
                dataType : "json",
                success: function(data) {
					var html = "";	
                	if(data.list != null && data.list.length>0){
                		for(var i=0;i<data.list.length;i++){
                			if(TYPE_ID != null){
                				if(TYPE_ID == data.list[i].ID){
                					html += "<option selected = \"selected\" id = \""+ data.list[i].ID +"\" value = \""+ data.list[i].ID +"\">"+ data.list[i].NAME +"</option>";			
                				}else{
                					html += "<option id = \""+ data.list[i].ID +"\" value = \""+ data.list[i].ID +"\">"+ data.list[i].NAME +"</option>";			
                				}
                			}
                		}
                	}
                	$("#select_type").html(html);
                }
			});
		}
		function options_status(){
			var html = "";
			var OPTIONS_STATUS = $("#OPTIONS_STATUS").val();
			if(OPTIONS_STATUS == 0|| OPTIONS_STATUS == ""){
				html += "<option value = \"0\" selected = \"selected\">唯一属性</option>";
				html += "<option value = \"1\" >单选属性</option>";
				html += "<option value = \"2\" >复选属性</option>";
			}else if(OPTIONS_STATUS == 1){
				html += "<option value = \"0\" >唯一属性</option>";
				html += "<option value = \"1\" selected = \"selected\">单选属性</option>";
				html += "<option value = \"2\" >复选属性</option>";
			}else if(OPTIONS_STATUS == 2){
				html += "<option value = \"0\" >唯一属性</option>";
				html += "<option value = \"1\" >单选属性</option>";
				html += "<option value = \"2\" selected = \"selected\">复选属性</option>";
			}
			$("#options_status").html(html);
		}
		function plant_status(){
			var html = "";
			var PLANT_STATUS = $("#PLANT_STATUS").val();
			if(PLANT_STATUS == 0 || PLANT_STATUS == ""){
				html += "<option value = \"0\" selected = \"selected\">手工录入</option>";
				html += "<option value = \"1\" >下面的列表中选择</option>";
			}else {
				html += "<option value = \"0\" >手工录入</option>";
				html += "<option value = \"1\" selected = \"selected\" >下面的列表中选择</option>";
			}
			$("#plant_status").html(html);
		}
		</script>
</body>
</html>