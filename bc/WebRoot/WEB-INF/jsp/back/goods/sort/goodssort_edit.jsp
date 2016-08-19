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
	            msg:'请输入分类名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#NAME").focus();
			return false;
		}
		if($("#PARENT_ID").val()==""){
			$("#PARENT_ID").tips({
				side:3,
	            msg:'请输入父级分类id',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PARENT_ID").focus();
			return false;
		}
		if($("#PARENT_NAME").val()==""){
			$("#PARENT_NAME").tips({
				side:3,
	            msg:'请输入父级分类名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PARENT_NAME").focus();
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
		if($("#KEY_WORDS").val()==""){
			$("#KEY_WORDS").tips({
				side:3,
	            msg:'请输入关键字',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#KEY_WORDS").focus();
			return false;
		}
		if($("#SORT_DESCRIPTION").val()==""){
			$("#SORT_DESCRIPTION").tips({
				side:3,
	            msg:'请输入分类描述',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#SORT_DESCRIPTION").focus();
			return false;
		}
		if($("#TYPE_IDS").val()==""){
			$("#TYPE_IDS").tips({
				side:3,
	            msg:'请输入商品类型ids',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TYPE_IDS").focus();
			return false;
		}
		if($("#PROPERTY_IDS").val()==""){
			$("#PROPERTY_IDS").tips({
				side:3,
	            msg:'请输入商品属性ids',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PROPERTY_IDS").focus();
			return false;
		}
		if($("#NAVIGATION").val()==""){
			$("#NAVIGATION").tips({
				side:3,
	            msg:'请输入导航栏',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#NAVIGATION").focus();
			return false;
		}
		if($("#SORT").val()==""){
			$("#SORT").tips({
				side:3,
	            msg:'请输入排序',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#SORT").focus();
			return false;
		}
		if($("#NUMBER_UNIT").val()==""){
			$("#NUMBER_UNIT").tips({
				side:3,
	            msg:'请输入数量单位',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#NUMBER_UNIT").focus();
			return false;
		}
		if($("#HOME_RECOMMEND").val()==""){
			$("#HOME_RECOMMEND").tips({
				side:3,
	            msg:'请输入首页推荐',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#HOME_RECOMMEND").focus();
			return false;
		}
		if($("#NUM_PRICE").val()==""){
			$("#NUM_PRICE").tips({
				side:3,
	            msg:'请输入价格区间个数',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#NUM_PRICE").focus();
			return false;
		}
		if($("#STATUS").val()==""){
			$("#STATUS").tips({
				side:3,
	            msg:'请输入状态，预留字段',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#STATUS").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="goodssort/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="GOODSSORT_ID" id="GOODSSORT_ID" value="${pd.GOODSSORT_ID}"/>
		<div id="zhongxin">
		<table>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="NAME" id="NAME" value="${pd.NAME}" maxlength="32" placeholder="这里输入分类名称" title="分类名称"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="number" name="PARENT_ID" id="PARENT_ID" value="${pd.PARENT_ID}" maxlength="32" placeholder="这里输入父级分类id" title="父级分类id"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="PARENT_NAME" id="PARENT_NAME" value="${pd.PARENT_NAME}" maxlength="32" placeholder="这里输入父级分类名称" title="父级分类名称"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="number" name="IS_SHOW" id="IS_SHOW" value="${pd.IS_SHOW}" maxlength="32" placeholder="这里输入是否显示" title="是否显示"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="KEY_WORDS" id="KEY_WORDS" value="${pd.KEY_WORDS}" maxlength="32" placeholder="这里输入关键字" title="关键字"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="SORT_DESCRIPTION" id="SORT_DESCRIPTION" value="${pd.SORT_DESCRIPTION}" maxlength="32" placeholder="这里输入分类描述" title="分类描述"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="TYPE_IDS" id="TYPE_IDS" value="${pd.TYPE_IDS}" maxlength="32" placeholder="这里输入商品类型ids" title="商品类型ids"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="PROPERTY_IDS" id="PROPERTY_IDS" value="${pd.PROPERTY_IDS}" maxlength="32" placeholder="这里输入商品属性ids" title="商品属性ids"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="number" name="NAVIGATION" id="NAVIGATION" value="${pd.NAVIGATION}" maxlength="32" placeholder="这里输入导航栏" title="导航栏"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="number" name="SORT" id="SORT" value="${pd.SORT}" maxlength="32" placeholder="这里输入排序" title="排序"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="NUMBER_UNIT" id="NUMBER_UNIT" value="${pd.NUMBER_UNIT}" maxlength="32" placeholder="这里输入数量单位" title="数量单位"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="HOME_RECOMMEND" id="HOME_RECOMMEND" value="${pd.HOME_RECOMMEND}" maxlength="32" placeholder="这里输入首页推荐" title="首页推荐"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="NUM_PRICE" id="NUM_PRICE" value="${pd.NUM_PRICE}" maxlength="32" placeholder="这里输入价格区间个数" title="价格区间个数"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="number" name="STATUS" id="STATUS" value="${pd.STATUS}" maxlength="32" placeholder="这里输入状态，预留字段" title="状态，预留字段"/></td>
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