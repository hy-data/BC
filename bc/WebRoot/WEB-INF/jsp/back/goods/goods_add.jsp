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
	<base href="<%=basePath%>"><!-- jsp文件头和头部 -->
	<link rel="stylesheet" href="static/css/nht.css" />
     <script type="text/javascript" src="static/js/jquery-2.1.4.min.js"></script>
     <link  href="static/css/cropper.min.css" rel="stylesheet">
     <script type="text/javascript" src="static/js/cropper.min.js"></script>
     <!-- 编辑器 -->
	<link rel="stylesheet" type="text/css" href="static/simditor-2.3.6/styles/simditor.css" />
	<script type="text/javascript" src="static/simditor-2.3.6/scripts/module.js"></script>
	<script type="text/javascript" src="static/simditor-2.3.6/scripts/hotkeys.js"></script>
	<script type="text/javascript" src="static/simditor-2.3.6/scripts/uploader.js"></script>
	<script type="text/javascript" src="static/simditor-2.3.6/scripts/simditor.js"></script>
	</head>
<body>
	<div class="main">
        <div class="content">
            <table>
                <tbody>
                    <tr>
                        <td>卖家名称<span class="r">*</span></td>
                        <input type = "hidden" id = "GOODS_ID" />
                        <input type="hidden" id = "MEMBER_ID" name ="MEMBER_ID"/>
                        <td><input type="button" id = "MEMBER_NICKNAME" name ="MEMBER_NICKNAME"  value = "卖家名称" onclick = "addSolder();"/></td>
                    </tr>
                    <tr>
                        <td>商品类型<span class="r">*</span></td>
                        <td>
                            <select class="s3" name="first" id="first" onchange="getSortSecond('second')"></select>
                            <select class="s3" name="second" id="second" onchange="getSortThree();"></select>
                            <select class="s3" name="three" id="three" onchange="getSortFour();"></select>
                            <input type = "hidden" id = "GOODS_SIFTING_ID" name = "GOODS_SIFTING_ID"/>
                            <input type = "hidden" id = "ARTWORK_SORT_ID" name = "ARTWORK_SORT_ID"/>
                        </td>
                    </tr>
                    <tr>
                        <td>筛选</td>
                        <td id = "sifting"></td>
                    </tr>
                    <tr>
                        <td>商品标题<span class="r">*</span></td>
                        <td><input type="text" id = "GOODS_TITAL" name = "GOODS_TITAL" /></td>
                    </tr>
                    <tr>
                        <td>品牌/艺术家<span class="r">*</span></td>
                        <input type="hidden" id = "ARTIST_ID" name ="ARTIST_ID"/>
                        <td id = "tbrand"></td>
                    </tr>
                    <tr>
                    	<td>
                    		出售类型*
                    	</td>
                    	<td>
                    		<select class="s3" name="PRICE_STATUS" id="PRICE_STATUS" >
	                    		<option value = 0>正常</option>
	                    		<option value = 0>议价</option>
	                    		<option value = 0>仅展示</option>
                    		</select>
                    	</td>
                    </tr>
                    <tr>
                        <td>商品主图(首张)<span class="r">*</span></td>
                        <td>
                            <div class="imgbox" id="pic0">
                                <input type="file" name="uploadImg" id="uploadImg" onchange="upload();"/>
                                <input type="hidden" id = "hiddenimage" value=""/>
                                <img src="static/images/2.jpg" id = "imagesuo"  alt="" class="upimg" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>主图正方形规格<span class="r">*</span></td>
                        <td><div class="spic" onclick="showFLT();" id = "alimage">未上传效果</div></td>
                    </tr>
                    <tr>
                        <td>商品主图(其他)<span class="r"></span></td>
                        <td>
                            <div class="imgbox" id="pic1">
                                <input type="file" name="uploadImg" id="uploadImg" onchange="uploadother('pic1','hidimage1', 'showimage1');" />
                                <input type="hidden" id = "hidimage1" name = "hidimage1" value=""/>
                                <img src="static/images/2.jpg" id = "showimage1"  alt="" class="upimg" />

                            </div>
                            <div class="imgbox" id="pic2">
                                <input type="file"  name="uploadImg" id="uploadImg" onchange="uploadother('pic2','hidimage2', 'showimage2');" />
                                <input type="hidden" id = "hidimage2" name = "hidimage2" value=""/>
                                <img src="static/images/2.jpg" id = "showimage2" alt="" class="upimg" />
                            </div>
                            <div class="imgbox" id="pic3">
                                <input type="file" name="uploadImg" id="uploadImg" onchange="uploadother('pic3','hidimage3', 'showimage3');" />
                                <input type="hidden" id = "hidimage3" name = "hidimage3" value=""/>
                                <img src="static/images/2.jpg" alt="" class="upimg"  id = "showimage3"/>
                            </div>
                            <div class="imgbox" id="pic4">
                                <input type="file" name="uploadImg" id="uploadImg" onchange="uploadother('pic4','hidimage4', 'showimage4');" />
                                <input type="hidden" id = "hidimage4" name = "hidimage4" value=""/>
                                <img src="static/images/2.jpg"  id = "showimage4" alt="" class="upimg" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                    	<td>图文详情图片地址</td>
                    	<td><input type="file" name="uploadImg" id="uploadImg" onchange="uploadother('pic5','','');" /></td>
                    </tr>
                    <tr><td></td>
                    	<td><input type="text" id = "detailimage" /></td>
                    </tr>
                    <tr>
                         <td>图文详情<span class="r">*</span></td>
                         <td><textarea name="GOODS_DETAILS" id="GOODS_DETAILS" cols="80" rows="20"></textarea></td>
                    </tr>
                    
                </tbody>
            </table>
        </div>
        <div class="nextbt" onclick = "save();">下一步</div>
    </div>
    <div class="fltcrop">
        <div class="cropbox">
        	<input type = hidden id = "GOODS_THUMBNAIL" />
            <div class="line">上传主图/裁切 <div class="cross" onclick="closeFLT();"><img src="static/images/cross.png" alt="" /></div></div>
            <div class="line"></div>
            <div class="crop_container">
                <img id="thisIMG" src="static/images/6.jpg" alt="" />
                <!-- 上传完了改src -->
            </div>
            <div class="line"><div class="submitbt" onclick = "setCrop();">上传</div></div>
        </div>
    </div>
    
	<script type="text/javascript">
	var uploadImgUrl = "<%=basePath%>goods/upImage";
	$(document).on('click', '.close', function(event) {
        $(this).parent("div").find('.upimg').attr("src","");
        $(this).parent("div").find('input:hidden').val("");
    });
    $(function(){
    	$(top.hangge());
    });
    
    var image = document.getElementById('thisIMG');
    // var cropper = new Cropper(image,{
    function showimages(){
    	$('#thisIMG').cropper({
	        viewMode: 1,
	        aspectRatio :1,
	        dragMode: 'move',
	        minCropBoxWidth :300,
	        minCropBoxHeight :300,
	        minCanvasWidth :500,
	        minCanvasHeight :500,
	        //autoCropArea: 0.375,
	        restore: false,
	        guides: false,
	        highlight: false,
	        cropBoxMovable: true,
	        cropBoxResizable: false,
	        toggleDragModeOnDblclick :false
	    });
    }
    
    function showInfo(id,tip){
        $("#"+id).append('<div class="replace" >'+tip+'</div><img src="images/cross.png" class="cross close" />');

    }
    function closeFLT(){
        $(".fltcrop").hide();
    }
    function showFLT(){
        $(".fltcrop").show();
        showimages();
    }
    //获取图片坐标值，长度和宽度
    function setCrop(){
        var object1 = $('#thisIMG').cropper("getData");
        var object2 = $('#thisIMG').cropper("getImageData");
        var object3 = $('#thisIMG').cropper("getCropBoxData"); 
        var obj1 = eval(object1);
        var obj2 = eval(object2);
        var obj3 = eval(object3);
        var imagemain = $("#hiddenimage").val();
        $.ajax({
			type: "POST",
			url: '<%=basePath%>goods/cutoutimage.do?x='+ parseInt(obj1.x) +'&y='+ parseInt(obj1.y) +'&naturalWidth='+ parseInt(obj2.naturalWidth) +'&width='+ parseInt(obj2.width) +'&height='+ parseInt(obj2.height) +'&imagePath='+ imagemain +'&heightedge='+ parseInt(obj3.height) +'',
			dataType:'json',
			cache: false,
			success: function(data){
				var obj = eval(data);
				alert(obj.pd.path);
				$("#GOODS_THUMBNAIL").val(obj.pd.path);
				$("#alimage").html("<img src = \""+ obj.pd.path +"\" style=\"width:85px;height:66px;\" />");
				closeFLT();
			}
		});
    }
	//添加卖家    	
    function addSolder(){
    	top.jzts();
    	var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="卖家";
		 diag.URL = '<%=basePath%>goods/addSolder.do';
		 diag.Width = 900;
		 diag.Height = 500;
		 diag.CancelEvent = function(){ //关闭事件
			 var MEMBER_ID = $(diag.innerFrame.contentWindow.document.getElementById('MEMBER_ID')).val();
		  	 var MEMBER_NICKNAME = $(diag.innerFrame.contentWindow.document.getElementById('MEMBER_NICKNAME')).val();
		  	 if(MEMBER_ID != null && MEMBER_ID != "")
				 $("#MEMBER_ID").val(MEMBER_ID);
		  	if(MEMBER_NICKNAME != null && MEMBER_NICKNAME != "")
			 	$("#MEMBER_NICKNAME").val(MEMBER_NICKNAME);
			diag.close();
		 };
		 diag.show();
	}
	
	//添加品牌
    function addBrand(){
    	top.jzts();
    	var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="品牌/艺术家";
		 diag.URL = '<%=basePath%>goods/addBrand.do';
		 diag.Width = 900;
		 diag.Height = 500;
		 diag.CancelEvent = function(){ //关闭事件
			 var ARTIST_ID = $(diag.innerFrame.contentWindow.document.getElementById('ARTIST_ID')).val();
		  	 var ARTIST_NAME = $(diag.innerFrame.contentWindow.document.getElementById('ARTIST_NAME')).val();
		  	 if(ARTIST_ID != null && ARTIST_ID != "")
			 	$("#ARTIST_ID").val(ARTIST_ID);
		  	if(ARTIST_NAME != null && ARTIST_NAME != "")
			    $("#ARTIST_NAME").val(ARTIST_NAME); 
			diag.close();
		 };
		 diag.show();
	}
    </script>
    <script type="text/javascript" src="<%=basePath%>static/js/ajaxfileupload.js"></script>
		<script>
		//上传商品主图
		function upload() {
			$.ajaxFileUpload({
				url : uploadImgUrl,//处理图片脚本
				dataType : 'json',
				secureuri : false,
				async: false,
				type:'post',
				fileElementId : 'uploadImg',//file控件id
				success : function(data, status) {
					var obj = eval(data);
					if (obj.result == "ok") {//上传成功
						if (obj.path != null && obj.path.length != 0) {
							var p = obj.path;
							$("#thisIMG").attr("src", p);
							$("#imagesuo").attr("src", p);
							$("#hiddenimage").val(p);
						} else {
							//alert("图片文件不能超过300K请处理后上传");
						}
					}else{
						alert("上传图片不能超过300K");						
					}
				},
				error: function(data, status, e){
			        alert(data.responseText);
			    }
			});
		}
		//上传商品其他主图
		function uploadother(pic, hidimage, showimage) {
			$.ajaxFileUpload({
				url : uploadImgUrl,//处理图片脚本
				dataType : 'json',
				secureuri : false,
				async: false,
				type:'post',
				fileElementId : 'uploadImg',//file控件id
				success : function(data, status) {
					var obj = eval(data);
					if (obj.result == "ok") {//上传成功
						if (obj.path != null && obj.path.length != 0) {
							if(pic == 'pic5'){
								$("#detailimage").val(obj.path);
							}else{
								$("#" + showimage).attr("src",  obj.path);
								$("#" + hidimage).val(obj.path);
								showInfo(pic,'成功');
							}
						} else {
							//alert("图片文件不能超过300K请处理后上传");
							showInfo(pic,'文件超过300k');
						}
					}
				},
				error: function(data, status, e){
			        alert(data.responseText);
			    }
			});
		}
		//编辑器
		var editor = new Simditor({
			  textarea: $('#GOODS_DETAILS'),
			  toolbar: [
                        //'title',          // 标题文字
                        'bold',           // 加粗文字
                        'italic',         // 斜体文字
                        'image',
                        'underline',      // 下划线文字
                        'strikethrough',  // 删除线文字
                        'color',          // 修改文字颜色
                        'hr',             // 分割线
                        'indent',         // 向右缩进
                        'link',         // 超链接
                        'outdent'     // 向左缩进
                      
                ]

			  //optional options
			});
		/* $(function(){
			getSortFirst('0');
		}); */
		//加载分类
		function getSortFirst(id){
			jQuery.ajax({
                url: "<%=basePath%>artworksort/ajaxList.do?ARTWORK_PARENT_ID=" + id,
                async:false, 
                dataType : "json",
                success: function(data) {
                	 if(data.list != null && data.list.length>0){
                		$("#second").html("<option>一级分类</option>");
               			$("#second").html("<option>二级分类</option>");
               			$("#three").html("<option>三级分类</option>"); 
               			var html = "<option>一级分类</option>";
               			for(var i=0;i<data.list.length;i++){
                   			var ARTWORK_SORT_ID = data.list[i].ARTWORK_SORT_ID;
                   			var ARTWORK_SORT_NAME = data.list[i].ARTWORK_SORT_NAME;
                   			html += "<option id = \""+ ARTWORK_SORT_ID +"\" value = \""+ ARTWORK_SORT_ID +"\">"+ ARTWORK_SORT_NAME +"</option>";
                   		}
               			$("#first").html(html);
               		}
                }, 
			});
			
		}
		function getSortSecond(){
			var second =$("#first").val();				
			jQuery.ajax({
                url: "<%=basePath%>artworksort/ajaxList.do?ARTWORK_PARENT_ID=" + second,
                async:false, 
                dataType : "json",
                success: function(data) {
                	 if(data.list != null && data.list.length>0){
               			$("#three").html("<option>三级分类</option>"); 
               			var html = "<option>二级分类</option>";
               			for(var i=0;i<data.list.length;i++){
                   			var ARTWORK_SORT_ID = data.list[i].ARTWORK_SORT_ID;
                   			var ARTWORK_SORT_NAME = data.list[i].ARTWORK_SORT_NAME;
                   			html += "<option id = \""+ ARTWORK_SORT_ID +"\" value = \""+ ARTWORK_SORT_ID +"\">"+ ARTWORK_SORT_NAME +"</option>";
                   		}
               			$("#second").html(html);
               		}
                }, 
			});
		}
		function getSortThree(){
			var three =$("#second").val();	
			jQuery.ajax({
                url: "<%=basePath%>artworksort/ajaxList.do?ARTWORK_PARENT_ID=" + three,
                async:false, 
                dataType : "json",
                success: function(data) {
                	 if(data.list != null && data.list.length>0){
               			$("#three").html(""); 
               			var html = "<option>三级分类</option>";
               			for(var i=0;i<data.list.length;i++){
                   			var ARTWORK_SORT_ID = data.list[i].ARTWORK_SORT_ID;
                   			var ARTWORK_SORT_NAME = data.list[i].ARTWORK_SORT_NAME;
                   			var ARTWORK_SORT_ART = data.list[i].ARTWORK_SORT_ART;
                   			html += "<option id = \""+ ARTWORK_SORT_ID +"\" value = \""+ ARTWORK_SORT_ID +":"+ ARTWORK_SORT_ART +"\">"+ ARTWORK_SORT_NAME +"</option>";
               			}
               			$("#three").html(html);
               		}
                }, 
			});
		}
		function getSortFour(){
			var three = $("#three").val().split(":")[0];
			var sortart = $("#three").val().split(":")[1];
			if(sortart != 'undefined'){
				if(sortart == 0){
					$("#tbrand").html("<input type=\"button\" id = \"ARTIST_NAME\" name =\"ARTIST_NAME\"  value = \"品牌/艺术家\" onclick = \"addBrand();\"/>");
					$("#ARTIST_ID").val("");
				}else{
					$("#tbrand").html("<input type=\"button\" id = \"ARTIST_NAME\" name =\"ARTIST_NAME\"  value = \"品牌/艺术家\" />");
					if($("#MEMBER_ID").val() == null || $("#MEMBER_ID").val() == ""){
						alert("请选择卖家");	
						getSortFirst('0');
					}else{
						$("#ARTIST_ID").val($("#MEMBER_ID").val());
						jQuery.ajax({
			                url: '<%=basePath%>artistbrand/findbycode.do?ARTIST_CODE='+ $("#MEMBER_ID").val() +'',
			                async:false, 
			                dataType : "json",
			                success: function(data) {
								var obj = eval(data);
								$("#ARTIST_NAME").val(obj.pd.ARTIST_NAME);	
								$("#ARTIST_ID").val(obj.pd.ARTIST_ID);	
			                }, 
						});
					}
				}
			}else{
				$("#tbrand").html("<input type=\"button\" id = \"ARTIST_NAME\" name =\"ARTIST_NAME\"  value = \"品牌/艺术家\" onclick = \"addBrand();\"/>");
			}
			jQuery.ajax({
                url: '<%=basePath%>goods/getJsonSifting.do?ARTWORK_SORT_ID='+ three +'',
                async:false, 
                dataType : "json",
                success: function(data) {
					var html = "";	
                	if(data.list != null && data.list.length>0){
                		for(var i=0;i<data.list.length;i++)        		
							html += "<label><input type=\"checkbox\" value = \""+ data.list[i].GOODS_SIFTING_ID +"\" name=\"shift_id\"/></label><label id = \"sift_name"+ data.list[i].GOODS_SIFTING_ID +"\" for=\"a1\">"+ data.list[i].GOODS_SIFTING_NAME +"</label>";		               			
                		$("#sifting").html(html);
                	}
                	$("#sifting").html(html);
                	$("#ARTWORK_SORT_ID").val(three);
                }, 
			});
		}
		

		function save(){
			if($("#MEMBER_ID").val() == '' || $("#MEMBER_ID").val() == null){
				alert("卖家不能为空");
				return false;
			}
			if($("#GOODS_TITAL").val() == '' || $("#GOODS_TITAL").val() == null){
				alert("标题不能为空");
				return false;
			}
			if($("#ARTIST_ID").val() == '' || $("#ARTIST_ID").val() == null){
				alert("品牌/艺术家不能为空");
				return false;
			}
			
			if($("#hiddenimage").val() == '' || $("#hiddenimage").val() == null){
				alert("商品主图不能为空");
				return false;
			}
			var MEMBER_ID = $("#MEMBER_ID").val();
			var GOODS_CODE = $('#first option:selected').text(); 
			var GOODS_TITAL = $("#GOODS_TITAL").val();
			var ARTWORK_SORT_ID = $("#ARTWORK_SORT_ID").val().split(":")[0];
			var ARTIST_ID = $("#ARTIST_ID").val();
			var SIFTING_IDS = "";
			var SIFTING_NAMES = "";
			var GOODS_COVER = $("#hiddenimage").val();
			var GOODS_THUMBNAIL = $("#GOODS_THUMBNAIL").val();
			var GOODS_DETAILS = $("#GOODS_DETAILS").val();
			var PRICE_STATUS = $("#PRICE_STATUS").val();
			var hidimage1 = $("#hidimage1").val();
			var hidimage2 = $("#hidimage2").val();
			var hidimage3 = $("#hidimage3").val();
			var hidimage4 = $("#hidimage4").val();
			$("input:checkbox[name='shift_id']:checked").each(function() {
				SIFTING_NAMES += $("#sift_name" + $(this).val()).html() + ",";
				SIFTING_IDS += $(this).val() + ",";
			});
			SIFTING_IDS = SIFTING_IDS.substring(0,SIFTING_IDS.length-1);
			SIFTING_NAMES = SIFTING_NAMES.substring(0,SIFTING_NAMES.length-1);
			$.ajax({
                url: '<%=basePath%>goods/save.do',
                type:'post',
                async:false,
                data:{'MEMBER_ID':MEMBER_ID, 'GOODS_TITAL':GOODS_TITAL, 'ARTWORK_SORT_ID':ARTWORK_SORT_ID, 
                	  'ARTIST_ID':ARTIST_ID, 'SIFTING_IDS':SIFTING_IDS, 'SIFTING_NAMES':SIFTING_NAMES,
                	  'GOODS_COVER':GOODS_COVER,'GOODS_THUMBNAIL':GOODS_THUMBNAIL,'GOODS_DETAILS':GOODS_DETAILS,
                	   'PRICE_STATUS':PRICE_STATUS,'GOODS_CODE':GOODS_CODE,'hidimage1':hidimage1,'hidimage2':hidimage2,'hidimage3':hidimage3,'hidimage4':hidimage4
                     },
                dataType : "json",
                success: function(data) {
					var obj = eval(data);
					if(obj.result == 'ok'){
	                	alert("添加成功");
	                	$("#GOODS_ID").val(obj.GOODS_ID);
	                	window.location.href = '<%=basePath%>goodspara/addParaPage.do?GOODS_ID=' + $("#GOODS_ID").val();		
					}
                }
			});
		}
		
		</script>
	</body>
</html>

