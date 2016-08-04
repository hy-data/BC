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
	            msg:'请输入名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#NAME").focus();
			return false;
		}
		if($("#INTRO").val()==""){
			$("#INTRO").tips({
				side:3,
	            msg:'请输入简介',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#INTRO").focus();
			return false;
		}
		if($("#DESCRIPTION").val()==""){
			$("#DESCRIPTION").tips({
				side:3,
	            msg:'请输入介绍',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#DESCRIPTION").focus();
			return false;
		}
		if($("#KEYSWORD").val()==""){
			$("#KEYSWORD").tips({
				side:3,
	            msg:'请输入关键字',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#KEYSWORD").focus();
			return false;
		}
		if($("#INFO").val()==""){
			$("#INFO").tips({
				side:3,
	            msg:'请输入公告',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#INFO").focus();
			return false;
		}
		if($("#LEVEL").val()==""){
			$("#LEVEL").tips({
				side:3,
	            msg:'请输入等级',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#LEVEL").focus();
			return false;
		}
		if($("#ADDR").val()==""){
			$("#ADDR").tips({
				side:3,
	            msg:'请输入地址',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ADDR").focus();
			return false;
		}
		if($("#COUNTRY").val()==""){
			$("#COUNTRY").tips({
				side:3,
	            msg:'请输入国家',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#COUNTRY").focus();
			return false;
		}
		if($("#PROVINCE").val()==""){
			$("#PROVINCE").tips({
				side:3,
	            msg:'请输入省份',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PROVINCE").focus();
			return false;
		}
		if($("#CITY").val()==""){
			$("#CITY").tips({
				side:3,
	            msg:'请输入城市',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CITY").focus();
			return false;
		}
		if($("#PHONE").val()==""){
			$("#PHONE").tips({
				side:3,
	            msg:'请输入电话',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PHONE").focus();
			return false;
		}
		if($("#EMAIL").val()==""){
			$("#EMAIL").tips({
				side:3,
	            msg:'请输入客服邮箱',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#EMAIL").focus();
			return false;
		}
		if($("#ISCLOSED").val()==""){
			$("#ISCLOSED").tips({
				side:3,
	            msg:'请输入是否闭店',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ISCLOSED").focus();
			return false;
		}
		if($("#CLOSEREASON").val()==""){
			$("#CLOSEREASON").tips({
				side:3,
	            msg:'请输入闭店理由',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CLOSEREASON").focus();
			return false;
		}
		if($("#CLOSEDATE").val()==""){
			$("#CLOSEDATE").tips({
				side:3,
	            msg:'请输入闭店时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CLOSEDATE").focus();
			return false;
		}
		if($("#ISINTEGRAL").val()==""){
			$("#ISINTEGRAL").tips({
				side:3,
	            msg:'请输入是否开启积分策略',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ISINTEGRAL").focus();
			return false;
		}
		if($("#TAGNUMBER").val()==""){
			$("#TAGNUMBER").tips({
				side:3,
	            msg:'请输入商品标签显示数量',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TAGNUMBER").focus();
			return false;
		}
		if($("#ORDERFAILURETIME").val()==""){
			$("#ORDERFAILURETIME").tips({
				side:3,
	            msg:'请输入未支付订单失效时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ORDERFAILURETIME").focus();
			return false;
		}
		if($("#REPAIRTIME").val()==""){
			$("#REPAIRTIME").tips({
				side:3,
	            msg:'请输入维护时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#REPAIRTIME").focus();
			return false;
		}
		if($("#CREATEDATE").val()==""){
			$("#CREATEDATE").tips({
				side:3,
	            msg:'请输入创建时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CREATEDATE").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="shop/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="SHOP_ID" id="SHOP_ID" value="${pd.SHOP_ID}"/>
		<div id="zhongxin">
		<table>
			<tr>
				<td><input type="text" name="NAME" id="NAME" value="${pd.NAME}" maxlength="32" placeholder="这里输入名称" title="名称"/></td>
			</tr>
			<tr>
				<td><input type="text" name="INTRO" id="INTRO" value="${pd.INTRO}" maxlength="32" placeholder="这里输入简介" title="简介"/></td>
			</tr>
			<tr>
				<td><input type="text" name="DESCRIPTION" id="DESCRIPTION" value="${pd.DESCRIPTION}" maxlength="32" placeholder="这里输入介绍" title="介绍"/></td>
			</tr>
			<tr>
				<td><input type="text" name="KEYSWORD" id="KEYSWORD" value="${pd.KEYSWORD}" maxlength="32" placeholder="这里输入关键字" title="关键字"/></td>
			</tr>
			<tr>
				<td><input type="text" name="INFO" id="INFO" value="${pd.INFO}" maxlength="32" placeholder="这里输入公告" title="公告"/></td>
			</tr>
			<tr>
				<td><input type="text" name="LEVEL" id="LEVEL" value="${pd.LEVEL}" maxlength="32" placeholder="这里输入等级" title="等级"/></td>
			</tr>
			<tr>
				<td><input type="text" name="ADDR" id="ADDR" value="${pd.ADDR}" maxlength="32" placeholder="这里输入地址" title="地址"/></td>
			</tr>
			<tr>
				<td><input type="text" name="COUNTRY" id="COUNTRY" value="${pd.COUNTRY}" maxlength="32" placeholder="这里输入国家" title="国家"/></td>
			</tr>
			<tr>
				<td><input type="text" name="PROVINCE" id="PROVINCE" value="${pd.PROVINCE}" maxlength="32" placeholder="这里输入省份" title="省份"/></td>
			</tr>
			<tr>
				<td><input type="text" name="CITY" id="CITY" value="${pd.CITY}" maxlength="32" placeholder="这里输入城市" title="城市"/></td>
			</tr>
			<tr>
				<td><input type="text" name="PHONE" id="PHONE" value="${pd.PHONE}" maxlength="32" placeholder="这里输入电话" title="电话"/></td>
			</tr>
			<tr>
				<td><input type="text" name="EMAIL" id="EMAIL" value="${pd.EMAIL}" maxlength="32" placeholder="这里输入客服邮箱" title="客服邮箱"/></td>
			</tr>
			<tr>
				<td><input type="text" name="ISCLOSED" id="ISCLOSED" value="${pd.ISCLOSED}" maxlength="32" placeholder="这里输入是否闭店" title="是否闭店"/></td>
			</tr>
			<tr>
				<td><input type="text" name="CLOSEREASON" id="CLOSEREASON" value="${pd.CLOSEREASON}" maxlength="32" placeholder="这里输入闭店理由" title="闭店理由"/></td>
			</tr>
			<tr>
				<td><input type="text" name="CLOSEDATE" id="CLOSEDATE" value="${pd.CLOSEDATE}" maxlength="32" placeholder="这里输入闭店时间" title="闭店时间"/></td>
			</tr>
			<tr>
				<td><input type="text" name="ISINTEGRAL" id="ISINTEGRAL" value="${pd.ISINTEGRAL}" maxlength="32" placeholder="这里输入是否开启积分策略" title="是否开启积分策略"/></td>
			</tr>
			<tr>
				<td><input type="number" name="TAGNUMBER" id="TAGNUMBER" value="${pd.TAGNUMBER}" maxlength="32" placeholder="这里输入商品标签显示数量" title="商品标签显示数量"/></td>
			</tr>
			<tr>
				<td><input type="number" name="ORDERFAILURETIME" id="ORDERFAILURETIME" value="${pd.ORDERFAILURETIME}" maxlength="32" placeholder="这里输入未支付订单失效时间" title="未支付订单失效时间"/></td>
			</tr>
			<tr>
				<td><input type="number" name="REPAIRTIME" id="REPAIRTIME" value="${pd.REPAIRTIME}" maxlength="32" placeholder="这里输入维护时间" title="维护时间"/></td>
			</tr>
			<tr>
				<td><input type="text" name="CREATEDATE" id="CREATEDATE" value="${pd.CREATEDATE}" maxlength="32" placeholder="这里输入创建时间" title="创建时间"/></td>
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