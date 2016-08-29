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
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="propertyvalue/${msg}.do" name="Form" id="Form" method="post">
		<input type="hidden" name="ID" id="ID" value="${pd.ID}"/>
		<%-- <input type="hidden" name="GOODS_ID" id="GOODS_ID" value="${pd.GOODS_ID}"/> --%>
		<input type="hidden" name="GOODS_ID" id="GOODS_ID" value="1"/>
		<div id="zhongxin">
		<table >
			<tr>
				<td></td>
				<td >商品类型：
				</td>
				<td>
					<select  data-placeholder="请选择" style="vertical-align:top;width: 120px;" name = "TYPE_ID"  id="select_type" onchange = "getlist();">
						
					</select>
				</td>
			</tr>
		</table>
		<table id = "list">
			
		</table>
		<div>
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
		</div>
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
			getJsonType();
		});
		function getJsonType(){
			var TYPE_ID = $("#TYPE_ID").val();
			$.ajax({
                url: '<%=basePath%>goodstype/getJsonType.do',
                async:false, 
                dataType : "json",
                success: function(data) {
					var html = "<option>请选择</option>";	
                	if(data.list != null && data.list.length>0){
                		for(var i=0;i<data.list.length;i++){
                			if(TYPE_ID != null){
                				if(TYPE_ID == data.list[i].ID){
                					html += "<option selected = \"selected\" id = \""+ data.list[i].ID +"\" value = \""+ data.list[i].ID +"\">"+ data.list[i].NAME +"</option>";			
                				}else{
                					html += "<option id = \""+ data.list[i].ID +"\" value = \""+ data.list[i].ID +"\">"+ data.list[i].NAME +"</option>";			
                				}
                			}else{
                				html += "<option id = \""+ data.list[i].ID +"\" value = \""+ data.list[i].ID +"\">"+ data.list[i].NAME +"</option>";
                			}
                		}
                	}
                	$("#select_type").html(html);
                }
			});
		}
		function getlist(){
			var type_id = $("#select_type").val();
			$.ajax({
                url: '<%=basePath%>goodsproperty/getJsonProperty.do?TYPE_ID='+type_id,
                async:false, 
                dataType : "json",
                success: function(data) {
					var html = "";
                	if(data.list != null && data.list.length>0){
                		for(var i = 0; i < data.list.length; i ++){
                			if(data.list[i].PLANT_STATUS == "0"){
                				html += "<tr>";
                        		html += "	<td></td>";
                        		html += "	<td>"+ data.list[i].NAME +"：</td>";    
                        		html += "	<td> <input style=\"width:320px;margin-top:5px;\" name=\""+ data.list[i].NAME +","+ data.list[i].PLANT_STATUS +","+ data.list[i].ID +"\" id=\"PROPERTY_NUM\"  maxlength=\"32\" /></td>";
                        		html += "</tr>";	
                			}else if(data.list[i].PLANT_STATUS == "1"){
                				html += "<tr>";
                        		html += "	<td></td>";
                        		html += "	<td>"+ data.list[i].NAME +"：</td>";
                        		html += "	<td>";    
                        		html += "		<select name=\""+ data.list[i].NAME +","+ data.list[i].PLANT_STATUS +","+ data.list[i].ID +"\" id=\"STATUS\"  data-placeholder=\"请选择\" style=\"vertical-align:top;width: 120px;\">";
            					var str = data.list[i].VALUE_LIST.split(",");
                        		for(var j = 0; j < str.length; j ++){
            						html += "<option value = \""+ str[j] +"\">"+ str[j] +"</option>";
            					}
                        		html += "	  	</select>";
                        		html += "	</td>";
                        		html += "</tr>";
                			}else{
                				
                			}
                		}
                		$("#list").html(html);  					                		
                	}
                }
			});
		}
		</script>
</body>
</html>