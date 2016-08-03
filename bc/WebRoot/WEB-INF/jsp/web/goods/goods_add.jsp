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
	</head>
<body>
	<div class="main">
        <div class="content">
            <table>
                <tbody>
                    <form action="scgoods/${msg}.do" name="Form" id="Form" method="post">
					<table>
						<tr>
							<td>名称：<input type="text" name="name" id="name" value="${pd.name}" maxlength="32"/></td>
						</tr>
						<tr>
							<td>类型：<input type="text" name="type" id="type" value="${pd.type}" maxlength="32"/></td>
						</tr>
						<tr>
							<td>名称：<input type="text" name="content" id="content" value="${pd.content}" maxlength="32"/></td>
						</tr>
					</table>
					<input type = "submit" value = "提交">
				</form>
                </tbody>	
            </table>
        </div>
    </div>
	</body>
</html>

