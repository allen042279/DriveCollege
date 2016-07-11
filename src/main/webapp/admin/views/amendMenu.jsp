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

		<title>修改订阅菜单</title>
		
		<base href="<%=basePath%>">
		
		<link rel=stylesheet type=text/css href="<%=basePath%>/admin/css/general.css">
		<link rel=stylesheet type=text/css href="<%=basePath%>/admin/css/nav.css">
		<link rel=stylesheet type=text/css href="<%=basePath%>/admin/css/page.css">
		
		<!--[if IE]>
		<link rel="stylesheet" type="text/css" media="all"  href="<%=path%>/admin/css/ie.css" />
		<![endif]-->
		
	    <script>
	        var js_path="<%=path%>"; 
	        var base_path="<%=basePath%>";
	    </script>  
	    
		<script src="//code.jquery.com/jquery-1.11.0.min.js"> </script>
		<script type="text/javascript"  src="<%=path%>/admin/js/jquery.cookie.js"   ></script>
		
		<script type="text/javascript"  src="<%=path%>/admin/js/add_menu.js"      ></script>
		<script type="text/javascript"  src="<%=path%>/admin/js/amend_menu.js"    ></script>
		
	</head>

<body id="menu">

<div class="container">

	<%@ include file="part_header.jsp" %>
  	
  	<%@ include file="part_nav.jsp" %>
  
	<div class="content">
			
			<h1>修改关注条目</h1>
			
			<form name="form1" action="<%=path%>/menu/update" method="post">
					
					<input type="hidden" value='<%=request.getParameter("id")%>' id="id" name="id">
					
					<table>
					
						<tr>
							<td class="right">名称：</td>
							<td class="left"><input class="input_content" type="text" id="name" name="name"></td>
						</tr>
											
						<tr>
							<td class="right">健值：</td>
							<td class="left"><input class="input_content" type="text" id="menuKey" name="menuKey"></td>
						</tr>			

						<tr>
							<td class="right">类型：</td>
							<td  class="left"><select class="input_content" id="type" name="type" onchange="checkType()">
<!-- 									<option value="click">单击型</option> -->
									<option value="view">链接型</option>
								</select>
							</td>
						</tr>
						
						<tr>
							<td class="right">原父菜单名称：</td>
							<td  class="left"><input class="input_content" type="text" id="old_parentId" name="old_parentId">
							</td>
						</tr>
												
						<tr>
							<td class="right">父菜单名称：</td>
							<td  class="left"><select class="input_content" id="parentId" name="parentId"></select>
							</td>
						</tr>
								
                        <tr>
							<td class="right">显示顺序：</td>
							<td class="left"><input class="input_content" type="text" id="showOrder" name="showOrder" onchange="javascript:return isNum(this);"></td>
						</tr>    
                                                										
						<tr>
							<td>&nbsp;</td>
						</tr>
						
					</table>
					
					
					<img alt="提交" src="<%=path%>/admin/img/submit_btn.png" style="cursor:pointer;" onclick="javascript:document.form1.submit(); ">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<img alt="重置" src="<%=path%>/admin/img/reset_btn.png" style="cursor:pointer;" onclick="javascript:document.form1.reset(); ">
					
				</form>
		     
	</div>  <!-- end .content -->
  
</div>
  
</body>

</html>