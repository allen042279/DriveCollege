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

		<title>查看用户列表</title>
		
		<base href="<%=basePath%>">
		
		<link rel=stylesheet type=text/css href="<%=basePath%>/admin/css/general.css">
		<link rel=stylesheet type=text/css href="<%=basePath%>/admin/css/nav.css">
		<link rel=stylesheet type=text/css href="<%=basePath%>/admin/css/page.css">
		
	    <script>
	        var js_path="<%=path%>"; 
	        var base_path="<%=basePath%>";
	    </script>  
	    
		<script src="//code.jquery.com/jquery-1.11.0.min.js"> </script>
		<script type="text/javascript"  src="<%=path%>/admin/js/jquery.myPagination5.0.js" ></script>
		<script type="text/javascript"  src="<%=path%>/admin/js/jquery.cookie.js"          ></script>
		<script type="text/javascript"  src="<%=path%>/admin/js/msgbox/msgbox.js"          ></script>
		
		<script type="text/javascript"  src="<%=path%>/admin/js/user.js"      ></script>
		
	</head>

<body id="user">

<div id="get_user" class="container">

	<%@ include file="part_header.jsp" %>
  	
  	<%@ include file="part_nav.jsp" %>
  
	  <div class="content">
			
			<div class="show" id="hint_div">
				<h1>没有可显示的数据</h1>
			</div>
			
			<div class="hiden" id="data_div">	
				
				<h1>所有用户</h1>
				
				<table border="1" >
				
					<tr align="center">
						<th style="width:160px;">用户名称</th>
						<th style="width:140px;">用户角色</th>
						<th style="width:100px;">是否可用</th> 
                        <th style="width:100px;">是否锁定</th>
                        <th style="width:120px;">账户是否过期</th>
                        <th style="width:120px;">凭证是否过期</th>
						<th style="width:70px;">修改</th>
						<th style="width:70px;">删除</th>
					</tr>
					
					<tbody id="datas"> </tbody>
					
				</table>
				
				<div id="paging"></div>
	     		
	     		<h3>&nbsp;</h3>
	     		
	     	</div>
	     
	  </div>  <!-- end .content -->
  
</div><!-- end .container -->
  
</body>

</html>