<%@page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"
         import="java.sql.*" errorPage="" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!doctype html>

<html>

	<head>
	
		<meta charset="utf-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">

		<title>增加关注条目</title>
		
		<base href="<%=basePath%>">
		
		<link rel=stylesheet type=text/css href="<%=path%>/admin/css/general.css">
		<link rel=stylesheet type=text/css href="<%=path%>/admin/css/nav.css">
		<link rel=stylesheet type=text/css href="<%=path%>/admin/css/page.css">
		
		<!--[if IE]>
		<link rel="stylesheet" type="text/css" media="all"  href="<%=path%>/admin/css/ie.css" />
		<![endif]-->
		
		<script src="//code.jquery.com/jquery-1.11.0.min.js" > </script>
		<script type="text/javascript"  src="<%=path%>/admin/js/pic_upload_preview.js"  ></script>
		
	</head>

<body id="scribe">

<div id="add_scribe" class="container">

	<%@ include file="part_header.jsp" %>
  	
  	<%@ include file="part_nav.jsp" %>

	<div class="content">
		
		<div style="margin-bottom:30px;">
			
			<h1>增加关注条目</h1>
			
			<form name="form1" action="<%=path%>/subscribe/add" method="post" enctype="multipart/form-data">
					
					<table>
					
						<tr>
							<td class="right">标题：</td>
							<td class="left"><input class="input_content" type="text" name="title"></td>
						</tr>
						
						<tr>
							<td></td>
							<td class="left">
								<div id="preview">  
								    <img id="imghead" width=0 height=0 border=0 >  
								</div>
							</td>
						</tr>
						
						<tr>
							<td class="right">图片文件：</td>
							<td class="left">
								<input class="input_content" type="file" name="picPath" onchange="previewImage(this);">
								<br/>(大小限制2M, 格式：.jpg .png)
							</td>
						</tr>
						
						<tr>
							<td class="right">描述：</td>
							<td class="left"><textarea rows="5" name="description"></textarea></td>
						</tr>			
						
						<tr>
							<td class="right">指向的链接：</td>
							<td class="left"><input class="input_content" type="text" name="url"></td>
						</tr>
						
<!-- 						<tr> -->
<!-- 							<td class="right">指向的内容链接：</td> -->
<!-- 							<td class="left"><input class="input_content" type="text" name="contentUrl"></td> -->
<!-- 						</tr> -->
												
						<tr>
							<td class="right">是否为第一个：</td>
							<td class="left"><select class="input_content" name="top">
									<option value="1">是</option>
									<option value="0">否</option>
								</select>
							</td>
						</tr>
						
						<tr>
							<td class="right">是否使用：</td>
							<td class="left"><select class="input_content" name="used">
									<option value="1">是</option>
									<option value="0">否</option>
								</select>
							</td>
						</tr>
						
						<tr>
							<td class="right">显示顺序：</td>
							<td class="left"><input class="input_content"  name="showOrder" onchange="javascript:return isNumber(this);"></td>
						</tr>						
																		
						<tr>
							<td>&nbsp;</td>
						</tr>
						
					</table>
					
					
					<img alt="提交" src="<%=path%>/admin/img/submit_btn.png" style="cursor:pointer;" onclick="javascript:document.form1.submit();">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="重置" src="<%=path%>/admin/img/reset_btn.png" style="cursor:pointer;" onclick="javascript:document.form1.reset(); ">
					
				</form>
						     
		  </div>	
		     
	</div>  <!-- end .content -->
  
</div>

</body>

</html>