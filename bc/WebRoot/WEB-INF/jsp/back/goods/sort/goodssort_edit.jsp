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
		var type_ids = "";
		var property_ids = "";
		for(var i = 1; i <= x; i ++){
			var html = $("#td_id" + i).html();
			if(html != ""){
				type_ids += $("#select_type" + i).val() + ",";
				property_ids += $("#select_property" + i).val() + ",";
			}
		}
		$("#TYPE_IDS").val(type_ids.substring(0, type_ids.length - 1));
		$("#PROPERTY_IDS").val(property_ids.substring(0, property_ids.length - 1));
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
		
	
</script>
	</head>
<body>
	<form action="goodssort/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="ID" id="ID" value="${pd.ID}"/>
		<div id="zhongxin">
		<table>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="NAME" id="NAME" value="${pd.NAME}" maxlength="32" placeholder="这里输入分类名称" title="分类名称"/></td>
			</tr>
			<tr>
				<td>上级分类：
					<select name="PARENT_ID"   id="PARENT_ID" data-placeholder="请选择上级分类" >
					<option value="0" >顶级分类</option>
						<c:forEach items="${sortList}" var="sort" varStatus="status">
							<option value="${sort.ID}" <c:if test="${sort.ID==pd.PARENT_ID}">selected="selected"</c:if>>${sort.NAME}</option>
						    <c:forEach items="${sort.child}" var="child">
								<option value="${child.ID}" <c:if test="${child.ID==pd.PARENT_ID}">selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;${child.NAME}</option>
							</c:forEach>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>是否显示： 
					<select  name="IS_SHOW" id="IS_SHOW"  data-placeholder="请选择" style="vertical-align:top;width: 120px;">
						<option value="0" <c:if test="${pd.IS_SHOW=='0'}"> selected="selected"</c:if>>显示</option>
						<option value="1" <c:if test="${pd.IS_SHOW=='1'}"> selected="selected"</c:if>>不显示</option>
				  	</select>
				</td>
			</tr>
			<tr>
				<td>导航栏显示： 	
					<select  name="NAVIGATION" id="NAVIGATION"  data-placeholder="请选择" style="vertical-align:top;width: 120px;">
						<option value="0" <c:if test="${pd.NAVIGATION=='0'}"> selected="selected"</c:if>>显示</option>
						<option value="1" <c:if test="${pd.NAVIGATION=='1'}"> selected="selected"</c:if>>不是显示</option>
				  	</select>
				</td>
			</tr>
			<tr>
				<td>首页推荐： 
					<select  name="HOME_RECOMMEND" id="HOME_RECOMMEND"  data-placeholder="请选择" style="vertical-align:top;width: 120px;">
						<option value="0" <c:if test="${pd.HOME_RECOMMEND=='0'}"> selected="selected"</c:if>>推荐</option>
						<option value="1" <c:if test="${pd.HOME_RECOMMEND=='1'}"> selected="selected"</c:if>>不推荐</option>
				  	</select>
				</td>
			</tr>
			<input style="width:400px;margin-top:5px;" type="hidden" name="TYPE_IDS" id="TYPE_IDS" value="${pd.TYPE_IDS}" maxlength="32" placeholder="这里输入商品类型ids" title="商品类型ids"/>
			<input style="width:400px;margin-top:5px;" type="hidden" name="PROPERTY_IDS" id="PROPERTY_IDS" value="${pd.PROPERTY_IDS}" maxlength="32" placeholder="这里输入商品属性ids" title="商品属性ids"/>
			<!-- <tr id = "add_item">	
				<td>
				
				</td>
			</tr> -->
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="KEY_WORDS" id="KEY_WORDS" value="${pd.KEY_WORDS}" maxlength="32" placeholder="这里输入关键字" title="关键字"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="SORT_DESCRIPTION" id="SORT_DESCRIPTION" value="${pd.SORT_DESCRIPTION}" maxlength="32" placeholder="这里输入分类描述" title="分类描述"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="NUMBER_UNIT" id="NUMBER_UNIT" value="${pd.NUMBER_UNIT}" maxlength="32" placeholder="这里输入数量单位" title="数量单位"/></td>
			</tr>
			
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="text" name="NUM_PRICE" id="NUM_PRICE" value="${pd.NUM_PRICE}" maxlength="32" placeholder="这里输入价格区间个数" title="价格区间个数"/></td>
			</tr>
			<tr>
				<td><input style="width:400px;margin-top:5px;" type="number" name="SORT" id="SORT" value="${pd.SORT}" maxlength="32" placeholder="这里输入排序" title="排序"/></td>
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
			getselectmes(1);
			
		});
		var x = 1; 
		function additem(){
			x ++;
			getselectmes(x);
		}
		function minitme(d){
			$(d).parent().parent().remove();
		}
		function getselectmes(j){
			var html = "";
			html += "<tr id = \"td_id"+ j +"\">";
			html += "<td>";
			html += "<div >";
			html += "<select  data-placeholder=\"请选择商品类型\" style=\"vertical-align:top;width: 120px;\" name = \"select_type"+j+"\"  id=\"select_type"+j+"\">";
			html += "</select>";
			html += "<select  data-placeholder=\"请选择商品属性\" style=\"vertical-align:top;width: 120px;\" name = \"select_property"+j+"\" id=\"select_property"+j+"\">";
			html += "	<option>请选择</option>";
			html += "</select>";
			if(j == 1)
				html += "<input type = \"button\" value = \"+\" onclick = \"additem();\"> <input type = \"button\" value = \"-\" onclick = \"minitme(this);\"/><br/>";
			else
				html += "<input type = \"button\" value = \"-\" onclick = \"minitme(this);\"/><br/>";
			html += "</div>";
			html += "</td>";
			html += "</tr>";
			$(html).insertAfter("#PROPERTY_IDS");
			getJsonType(j);
			x = j;
		}
		function getJsonType(j){
			$.ajax({
                url: '<%=basePath%>goodstype/getJsonType.do',
                async:false, 
                dataType : "json",
                success: function(data) {
					var html = "<option>请选择商品类型</option>";	
                	if(data.list != null && data.list.length>0){
                		for(var i=0;i<data.list.length;i++){
           					html += "<option  id = \""+ data.list[i].ID +"\" onclick = \"getProperty("+ j +", "+ data.list[i].ID +");\" value = \""+ data.list[i].ID +"\" >"+ data.list[i].NAME +"</option>";			
                		}
                	}
                	$("#select_type" + j).html(html);
                }
			});
		}
		function getProperty(y, id){
			$.ajax({
                url: '<%=basePath%>goodsproperty/getJsonProperty.do?TYPE_ID=' + id,
                async:false, 
                dataType : "json",
                success: function(data) {
                	var html = "<option>请选择商品属性</option>";	
                	if(data.list != null && data.list.length>0){
               			for(var i=0;i<data.list.length;i++){
              				html += "<option  id = \""+ data.list[i].ID +"\" value = \""+ data.list[i].ID +"\">"+ data.list[i].NAME +"</option>";			
                   		}
                	}
                	$("#select_property" + y).html(html);
                }
			});
		}
		</script>
</body>
</html>