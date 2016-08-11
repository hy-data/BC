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
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript" src="static/js/jquery-2.1.4.min.js"></script>
	     <link  href="static/css/cropper.min.css" rel="stylesheet">
<script type="text/javascript">
	
	
	//保存
	function save(){
			if($("#SID").val()==""){
			$("#SID").tips({
				side:3,
	            msg:'请输入商店',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#SID").focus();
			return false;
		}
		if($("#NAME").val()==""){
			$("#NAME").tips({
				side:3,
	            msg:'请输入商品名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#NAME").focus();
			return false;
		}
		if($("#TYPE").val()==""){
			$("#TYPE").tips({
				side:3,
	            msg:'请输入商品主类型',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TYPE").focus();
			return false;
		}
		if($("#CODE").val()==""){
			$("#CODE").tips({
				side:3,
	            msg:'请输入商品货号',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CODE").focus();
			return false;
		}
		if($("#KEYSWORK").val()==""){
			$("#KEYSWORK").tips({
				side:3,
	            msg:'请输入商品关键字',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#KEYSWORK").focus();
			return false;
		}
		if($("#TITLE").val()==""){
			$("#TITLE").tips({
				side:3,
	            msg:'请输入商品简单描述',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TITLE").focus();
			return false;
		}
		if($("#PICTUREURL").val()==""){
			$("#PICTUREURL").tips({
				side:3,
	            msg:'请输入主图片地址',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PICTUREURL").focus();
			return false;
		}
		if($("#PRICE").val()==""){
			$("#PRICE").tips({
				side:3,
	            msg:'请输入商品原始价格',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PRICE").focus();
			return false;
		}
		if($("#STARTTIME").val()==""){
			$("#STARTTIME").tips({
				side:3,
	            msg:'请输入开始销售时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#STARTTIME").focus();
			return false;
		}
		if($("#ISPUB").val()==""){
			$("#ISPUB").tips({
				side:3,
	            msg:'请输入是否发布',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ISPUB").focus();
			return false;
		}
		if($("#TOTALNUM").val()==""){
			$("#TOTALNUM").tips({
				side:3,
	            msg:'请输入库存总数',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TOTALNUM").focus();
			return false;
		}
		if($("#TOTALWARN").val()==""){
			$("#TOTALWARN").tips({
				side:3,
	            msg:'请输入库存警告',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TOTALWARN").focus();
			return false;
		}
		if($("#BRAND").val()==""){
			$("#BRAND").tips({
				side:3,
	            msg:'请输入品牌',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#BRAND").focus();
			return false;
		}
		if($("#PROMOTIONPRICE").val()==""){
			$("#PROMOTIONPRICE").tips({
				side:3,
	            msg:'请输入促销价格',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PROMOTIONPRICE").focus();
			return false;
		}
		if($("#PROMOTIONTIME").val()==""){
			$("#PROMOTIONTIME").tips({
				side:3,
	            msg:'请输入促销时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PROMOTIONTIME").focus();
			return false;
		}
		if($("#LIMITNUM").val()==""){
			$("#LIMITNUM").tips({
				side:3,
	            msg:'请输入限购数量',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#LIMITNUM").focus();
			return false;
		}
		if($("#MARKETPRICE").val()==""){
			$("#MARKETPRICE").tips({
				side:3,
	            msg:'请输入市场售价',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#MARKETPRICE").focus();
			return false;
		}
		if($("#FEEDBACKPRICE").val()==""){
			$("#FEEDBACKPRICE").tips({
				side:3,
	            msg:'请输入分成金额',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#FEEDBACKPRICE").focus();
			return false;
		}
		if($("#ISSHOW").val()==""){
			$("#ISSHOW").tips({
				side:3,
	            msg:'请输入是否在店铺显示',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ISSHOW").focus();
			return false;
		}
		if($("#LABELID").val()==""){
			$("#LABELID").tips({
				side:3,
	            msg:'请输入推荐标签',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#LABELID").focus();
			return false;
		}
		if($("#WEIGHT").val()==""){
			$("#WEIGHT").tips({
				side:3,
	            msg:'请输入商品重量',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#WEIGHT").focus();
			return false;
		}
		if($("#ISSALE").val()==""){
			$("#ISSALE").tips({
				side:3,
	            msg:'请输入是否允许做为普通商品销售，否则只能赠送及配件',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ISSALE").focus();
			return false;
		}
		if($("#EXPRESSPRICE").val()==""){
			$("#EXPRESSPRICE").tips({
				side:3,
	            msg:'请输入快递费',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#EXPRESSPRICE").focus();
			return false;
		}
		if($("#ISEXPRESS").val()==""){
			$("#ISEXPRESS").tips({
				side:3,
	            msg:'请输入是否免费邮寄',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ISEXPRESS").focus();
			return false;
		}
		if($("#LASTUPDATE").val()==""){
			$("#LASTUPDATE").tips({
				side:3,
	            msg:'请输入更新时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#LASTUPDATE").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="goods/${msg}.do" name="Form" id="Form" method="post" enctype="multipart/form-data">
		<input type="hidden" name="ID" id="ID" value="${pd.ID}"/>
		<div id="zhongxin">
		<table>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="number" name="SID" id="SID" value="${pd.SID}" maxlength="32" placeholder="这里输入商店" title="商店"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="NAME" id="NAME" value="${pd.NAME}" maxlength="32" placeholder="这里输入商品名称" title="商品名称"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="TYPE" id="TYPE" value="${pd.TYPE}" maxlength="32" placeholder="这里输入商品主类型" title="商品主类型"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="CODE" id="CODE" value="${pd.CODE}" maxlength="32" placeholder="这里输入商品货号" title="商品货号"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="KEYSWORK" id="KEYSWORK" value="${pd.KEYSWORK}" maxlength="32" placeholder="这里输入商品关键字" title="商品关键字"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="TITLE" id="TITLE" value="${pd.TITLE}" maxlength="32" placeholder="这里输入商品简单描述" title="商品简单描述"/></td>
			</tr>
			<tr>
				<td>商品主图：<input style="width:400px;margin-top:5px;" type="file" name="PICTUREURL" id="PICTUREURL" /></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="PRICE" id="PRICE" value="${pd.PRICE}" maxlength="32" placeholder="这里输入商品原始价格" title="商品原始价格"/></td>
			</tr>
			<tr>
				<td><input  style="width:400px;margin-top:5px;" class="span10 date-picker" name="STARTTIME" id="STARTTIME" value="${pd.STARTTIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="开始销售时间" title="开始销售时间"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="ISPUB" id="ISPUB" value="${pd.ISPUB}" maxlength="32" placeholder="这里输入是否发布" title="是否发布"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="number" name="TOTALNUM" id="TOTALNUM" value="${pd.TOTALNUM}" maxlength="32" placeholder="这里输入库存总数" title="库存总数"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="TOTALWARN" id="TOTALWARN" value="${pd.TOTALWARN}" maxlength="32" placeholder="这里输入库存警告" title="库存警告"/></td>
			</tr>
			<tr>
				<td><input style="width:100px;margin-top:5px;" type="button" name="BRAND" id="BRAND" value="品牌" maxlength="32"  title="品牌" onclick = "addBrand();"/></td>
			</tr>
				<input type = "hidden" id = "BRAND_ID" />	
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="PROMOTIONPRICE" id="PROMOTIONPRICE" value="${pd.PROMOTIONPRICE}" maxlength="32" placeholder="这里输入促销价格" title="促销价格"/></td>
			</tr>
			<tr>
				<td><input  style="width:400px;margin-top:5px;" class="span10 date-picker" name="PROMOTIONTIME" id="PROMOTIONTIME" value="${pd.PROMOTIONTIME}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="促销时间" title="促销时间"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="number" name="LIMITNUM" id="LIMITNUM" value="${pd.LIMITNUM}" maxlength="32" placeholder="这里输入限购数量" title="限购数量"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="MARKETPRICE" id="MARKETPRICE" value="${pd.MARKETPRICE}" maxlength="32" placeholder="这里输入市场售价" title="市场售价"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="FEEDBACKPRICE" id="FEEDBACKPRICE" value="${pd.FEEDBACKPRICE}" maxlength="32" placeholder="这里输入分成金额" title="分成金额"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="number" name="ISSHOW" id="ISSHOW" value="${pd.ISSHOW}" maxlength="32" placeholder="这里输入是否在店铺显示" title="是否在店铺显示"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="LABELID" id="LABELID" value="${pd.LABELID}" maxlength="32" placeholder="这里输入推荐标签" title="推荐标签"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="WEIGHT" id="WEIGHT" value="${pd.WEIGHT}" maxlength="32" placeholder="这里输入商品重量" title="商品重量"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="ISSALE" id="ISSALE" value="${pd.ISSALE}" maxlength="32" placeholder="这里输入是否允许做为普通商品销售，否则只能赠送及配件" title="是否允许做为普通商品销售，否则只能赠送及配件"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="EXPRESSPRICE" id="EXPRESSPRICE" value="${pd.EXPRESSPRICE}" maxlength="32" placeholder="这里输入快递费" title="快递费"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="ISEXPRESS" id="ISEXPRESS" value="${pd.ISEXPRESS}" maxlength="32" placeholder="这里输入是否免费邮寄" title="是否免费邮寄"/></td>
			</tr>
			<tr>
                  	<td>图文详情图片地址<input type="file" name="uploadImg" id="uploadImg" onchange="uploadother('DETAILIMAGE');" /></td>
                   </tr>
                   <tr>
                   	<td><input type="text" id = "detailimage" /></td>
                   </tr>
			<tr>
                  <td><textarea name="DESCRIPTION" id="DESCRIPTION" cols="40" rows="40"></textarea></td>
                  <script>
	                // Replace the <textarea id="editor1"> with a CKEditor
	                // instance, using default configuration.
	                CKEDITOR.replace( 'DESCRIPTION' );
	            </script>
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