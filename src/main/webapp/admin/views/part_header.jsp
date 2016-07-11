<%@page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"
         import="java.sql.*" errorPage="" %>

<%
	String header_path = request.getContextPath();
// 	String header_basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!doctype html>

<html>

	<head>	</head>

<body>

  <div class="header">
  
  	<div class="fltlft">
  		<img src="<%=header_path%>/admin/img/logo.png" alt="徽标" width="180" height="120" />
    </div>
    
    <div class="fltlft">
  		<img src="<%=header_path%>/admin/img/header_bg1.jpg" width="780" height="120"/>
	</div>
	
  </div>
  
</body>

</html>