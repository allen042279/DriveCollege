<%@ page contentType="text/html; charset=utf-8" language="java" errorPage=""   %>

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!doctype html>
<html>
<body>

	<c:url value="/login" var="loginUrl"/>
	<form action="${loginUrl}" method="post">       
		<c:if test="${param.error != null}">        
			<p>
				Invalid username and password.
			</p>
		</c:if>
		<c:if test="${param.logout != null}">       
			<p>
				You have been logged out.
			</p>
		</c:if>
		<p>
			<label for="username">Username</label>
			<input type="text" id="username" name="username"/>	
		</p>
		<p>
			<label for="password">Password</label>
			<input type="password" id="password" name="password"/>	
		</p>
		<input type="hidden"                        
			name="${_csrf.parameterName}"
			value="${_csrf.token}"/>
		<button type="submit" class="btn">Log in</button>
	</form>
	
</body>
</html>

<!-- <!doctype html> -->

<!-- <html> -->

<!-- 	<head> -->
	
<%-- 	<base href="<%=basePath%>"> --%>
	
<!-- 	<meta charset="utf-8"> -->
<!-- 	<title>登 录</title> -->

<!-- <style type="text/css"> -->
/* <!-- */
/* 	body { */
/* 		text-align:center; */
/* 		background:url(img/login_body_bg.png) repeat; */
/* 		padding:0; */
/* 		margin:0; */
/* 	} */
	
/* 	/* ~~ 此固定宽度容器包含所有其它元素 ~~ */ */
/* 	.container { */
/* 		width: 590px; */
/* 		height:344px; */
/* 		position:absolute; */
/* 		top:50%; */
/* 		left:50%; */
/* 		margin-top:-172px; */
/* 		margin-left:-295px;   */
/* 		background:url(img/login_bg.png); */
/* 	} */
	
	
/* 	.fail { */
/* 		width: 316px; */
/* 		height: 52px; */
/* 		padding: 0 0; */
/* 		background: url(img/login_fail.png); */
/* 		margin-left: auto; */
/* 		margin-right: auto; */
/* 		position: relative; */
/* 		margin-left: 137px; */
/* 		margin-top: 26px; */
/* 		z-index: 2; */
/* 	} */
	
/* 	table { */
/* 		margin-left: auto; */
/* 		margin-right:auto; */
/* 		margin-top: 80px; */
/* 		position: relative; */
/* 	} */
	
/* 	.input_content{ */
/* 		font-size:18px; */
/* 		width:180px; */
/* 		height:25px; */
/* 		line-height: 25px; */
/* 	} */
	
/* 	#loginbtn{ */
/* 		background:url(img/login_btn.png);		 */
/* 	} */
/* --> */
<!-- </style> -->

<!-- <script type="text/javascript" src="js/menu.js"></script> -->

<!-- </head> -->

<!-- <body> -->

<!-- <div class="container"> -->

<%-- 	<a href="<%=path %>/j_spring_security_logout"></a> --%>
	
<%-- 		<form name="form1" method="post" action="<%=path %>/user/login"> --%>
		
<!-- <!-- 			<input type="hidden" name="actionMethod" value="login"> --> -->
			
<!-- 			<table>            	 -->
            	
<!--             	<tr> -->
<!--                 	<td align="right">用户名称：</td> -->
<!--                     <td><input class="input_content" type="text" name="name" value="" ></td> -->
<!--                 </tr> -->
<!--                 <tr> -->
<!--                 	<td align="right">密  码：</td> -->
<!--                     <td><input class="input_content" type="password" name="pwd" value=""></td> -->
<!--                 </tr> -->
<!--                 <tr><td>&nbsp;</td></tr> -->
<!--                 <tr> -->
<!-- <!--                	  <td><input type="checkbox" name="remember_me" id="remember_me"  checked>记住我</td> --> -->
<!-- 				  <td> </td> -->
<!--                   <td align="right"> -->
<!-- 						<img alt="提交" src="img/login_btn.png" style="cursor:pointer;" onclick="javascript:document.form1.submit(); "> -->
<!--                   </td> -->
<!--                 </tr> -->
                
<!--             </table> -->
            
<!--         </form> -->
	
        
<%--         <% if(request.getAttribute("no_user")!=null){ %> --%>
<!--         <div class="fail" id="fail_div"> </div>      -->
<%-- 		<%  } %> --%>
        
<!-- </div>end .container -->
  
<!-- </body> -->

<!-- </html> -->
