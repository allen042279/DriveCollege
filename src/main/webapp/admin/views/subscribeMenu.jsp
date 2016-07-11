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

		<title>查看关注菜单项</title>
		
		<base href="<%=basePath%>">
		
		<link rel=stylesheet type=text/css href="<%=path%>/admin/css/general.css">
		<link rel=stylesheet type=text/css href="<%=path%>/admin/css/nav.css">
		<link rel=stylesheet type=text/css href="<%=path%>/admin/css/page.css">
		
		<!--[if IE]>
		<link rel="stylesheet" type="text/css" media="all"  href="<%=path%>/admin/css/ie.css" />
		<![endif]-->
		
	    <script>
	        var js_path="<%=path%>"; 
	        var base_path="<%=basePath%>";
	        
	    </script>  
	    
		<script type="text/javascript"  src="//code.jquery.com/jquery-1.11.0.min.js" > </script>
		<script type="text/javascript"  src="<%=path%>/admin/js/jquery.myPagination5.0.js" ></script>
		<script type="text/javascript"  src="<%=path%>/admin/js/jquery.cookie.js"          ></script>
		<script type="text/javascript"  src="<%=path%>/admin/js/msgbox/msgbox.js"          ></script>
		
		<script type="text/javascript"  src="<%=path%>/admin/js/subscribe_menu.js"      ></script>
		
	</head>

<body id="scribe">

<div id="get_scribe" class="container">

	<%@ include file="part_header.jsp" %>
  	
  	<%@ include file="part_nav.jsp" %>

	  <div class="content">
			
			<div class="show" id="hint_div">
				<h1>没有可显示的数据</h1>
			</div>
			
			<div class="hiden" id="data_div">	
				
				<h1>所有关注的菜单项</h1>
				
				<table border="1" >
				
					<tr align="center">
						<th style="width:80px;">标题</th>
						<th style="width:180px;">描述</th>
						<th style="width:90px;">图片</th>
						<th style="width:260px;">链接</th>
<!-- 						<th style="width:190px;">内容链接</th> -->
						<th style="width:80px;">是否顶图</th>
						<th style="width:90px;">是否使用中</th>
						<th style="width:70px;">显示顺序</th>
						<th style="width:50px;">修改</th>
						<th style="width:50px;">删除</th>
					</tr>
					
					<tbody id="datas"> </tbody>
					
				</table>
				
				<div id="paging"></div>
	     
	     	</div>
	     
	  </div>  <!-- end .content -->
  
</div>
	    
</body>

</html>