<%@page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"
         import="java.sql.*" errorPage="" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!doctype html>

<html>

<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
    
	<base href="<%=basePath%>">
	    
	<link rel="stylesheet" href="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css">
	<link rel="stylesheet" type=text/css href="<%=path%>/css/mobile_general.css">
	<link rel="stylesheet" type=text/css href="<%=path%>/css/mobile_list.css">
	<link rel=stylesheet type=text/css href="<%=path%>/css/mobile_specialist.css">
	
	
	<script src="http://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
	
	<script>
	        var js_path="<%=path%>"; 
	        var base_path="<%=basePath%>";
	        var isLogin ='<%=session.getAttribute("isLogin")%>';
	        var openIdString =window.location.search;
	        var typeId = <%=request.getParameter("typeId")%>;
	</script> 
		
<!-- 	<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script> -->
<!-- 	<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script> -->
	<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

	<script type="text/javascript"  src="<%=path%>/js/list_achievement.js"      ></script>
	
</head>

<body > 


<!--   	<div data-role="page" id="page_one"> -->
  
<!--     	<div data-role="header" data-theme="d"> -->
<!--   			<h1>精彩成就</h1>  -->
<!--   			<a href="#page_two" data-role="button" class="ui-btn-right" data-theme="a"> -->
<%--   				<img class="header_icon" style="width:20px;height:20px;" src="<%=path%>/img/strip_list.png"> --%>
<!--   				<span class="header_text">列表</span> -->
<!--   			</a> -->
<!--   		</div> -->
  
<!-- 	  	<div data-role="content"> -->
	  	
 	  		<!-- 块状显示 -->
<!-- 	  		<div id="block_part">  -->
			
<!-- 				<table> -->
				
<!-- 					<tbody id="block_datas"> </tbody> -->
					
<!-- 				</table> -->
				
<!-- 			</div> -->

<!-- 	  	</div> -->

<!-- 	</div>   -->
	
  	<div data-role="page" id="page_two">
  
    	<div data-role="header" data-theme="b">
  			<h1>精彩成就</h1> 
<!-- 			<a href="#page_one" data-role="button" class="ui-btn-right" data-theme="a"> -->
<%-- 				<img class="header_icon" style="width:20px;height:20px;" src="<%=path%>/img/block_list.png"> --%>
<!-- 				<span class="header_text">网格</span> -->
<!-- 			</a> -->
  		</div>
  
	  	<div data-role="content">
			
	 		<!-- 条状显示 -->
	 		<div id="strip_part"> 
			
				<table>
				
					<tbody id="strip_datas"> </tbody>
					
				</table>
				
			</div>
<!-- 			<ul data-role="listview" data-inset="true" id="strip_datas"></ul> -->

	  	</div>

	</div>  	
  
  
</body>

</html>