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

		<title>修改用户</title>
		
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
		
		<script type="text/javascript"  src="<%=path%>/admin/js/amend_user.js"    ></script>
		
	</head>

<body id="user">

<div class="container">

	<%@ include file="part_header.jsp" %>
  	
  	<%@ include file="part_nav.jsp" %>
  
	<div class="content">
			
			<h1>修改用户</h1>
			
			<form name="form1" action="<%=path%>/user/update" method="post">
					
					<input type="hidden" value='<%=request.getParameter("id")%>' id="id" name="id">
					<input type="hidden" value='' id="password" name="password">
					
					<table>
					
						<tr>
							<td class="right">用户名称：</td>
							<td class="left"><input class="input_content" type="text" id="username" name="username"></td>
						</tr>
									
						<tr>
							<td class="right">角色：</td>
							<td  class="left"><select class="input_content" id="role" name="role">
									<option value="ROLE_AMIN">管理员</option>
									<option value="ROLE_USER">普通用户</option>
								</select>
							</td>
						</tr>
																	
						<tr>
							<td class="right">是否能用：</td>
							<td  class="left"><select class="input_content" id="isEnabled" name="isEnabled">
									<option value="0">不能</option>
									<option value="1">能</option>
								</select>
							</td>
						</tr>

						
						<tr>
							<td class="right">是否锁定：</td>
							<td  class="left"><select class="input_content" id="isAccountNonLocked" name="isAccountNonLocked">
									<option value="1">已锁定</option>
									<option value="0">未锁定</option>
								</select>
							</td>
						</tr>
												
						<tr>
							<td class="right">账户是否过期：</td>
							<td  class="left"><select class="input_content" id="isAccountNonExpired" name="isAccountNonExpired">
									<option value="1">已过期</option>
									<option value="0">未过期</option>
								</select>
							</td>
						</tr>
								
						<tr>
							<td class="right">凭证是否过期：</td>
							<td  class="left"><select class="input_content" id="isCredentialsNonExpired" name="isCredentialsNonExpired">
									<option value="1">已过期</option>
									<option value="0">未过期</option>
								</select>
							</td>
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