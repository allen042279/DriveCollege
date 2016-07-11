<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="base" value="${pageContext.request.contextPath }/"	scope="session" />

<html>
<body>

	<h2>您请求的资源不存在！</h2>
	<h3>3秒钟之后跳转到首页。。。。。。或点击<a href="${path}">首页</a>	</h3>
	
</body>

	<script type="text/javascript">
		setTimeout(function() {
						location.href = "${base}";
						}, 
				  3000);
	</script> 

</html>