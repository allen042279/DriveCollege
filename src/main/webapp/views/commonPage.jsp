<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8" errorPage="" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!doctype html>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> -->

<html>

<head>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
    
<%--     <base href="<%=basePath%>"> --%>
    <script>
	
	    var js_path = "<%=path%>"; 
	    var url;
	    var height = window.screen.availHeight;
	</script>
	
	<link rel="stylesheet" href="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css">
	<link rel=stylesheet type="text/css" href="<%=path%>/css/mobile_specialist.css">
	
	<script src="http://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

	<script type="text/javascript"  src="<%=path%>/js/commonPage.js"      ></script>
	
</head>

<body> 
    	
  	<div data-role="page">
  
    	<div data-role="header" data-theme="b">    	
    		<h1 id="headline"></h1>
  		</div>
  
	  	<div data-role="main" class="ui-content">
	  	
<!-- 	  		<div id="urlContent" width="100%" height="1000px"> -->
	  		
<!-- 	  		</div> -->

	  		<iframe id="mainFrame" name="mainFrame" width="100%" seamless></iframe>
			
	  	</div>
		
	  <div data-role="footer" data-theme="b">
	    <div data-role="navbar">
	      <ul>
	        <li><a href="<%=path%>/views/addPreStudent.jsp" data-icon="user">导师指引</a></li>
	        <li><a href="<%=path%>/views/showAchievements.jsp" data-icon="phone">一键咨询</a></li>
	      </ul>
	    </div>
	  </div>
  
	</div>  

<script>
	
<%-- 	    var js_path = "<%=path%>";  --%>
<%-- 	    var x = <%=index%>; --%>
// 	    var url;
	    
// 	    $.ajax({
	    	
// 			cache : false, 
			
// 			type : 'POST',
			
// 			url : js_path + '/subscribe/getAll',
			
// 			async: false,
			
// 			dataType : 'json',
			
// 			success : function(result){
				
// 				if((result!=undefined || result!=null ) && result!=="") {
					
// 					$.each(result, function(index, items) {
// 						if(x==items.showOrder){
// 							$("#headline").text(items.title);
// 							url = items.contentUrl;
// 						}
// 					});
// 				}
// 			},
// 			complete: function (XMLHttpRequest, textStatus) {
// 				//完成处理
//            },
//            error: function (e) {
//                //异常处理
//            }
			
// 		});
	    
// 		$.ajax({
		    	
// 				cache : false, 
				
// 				type : 'POST',
				
// 				url : js_path + '/user/getUrlContent',
				
// 				async: false,
				
// 				dataType : 'html',
				
// 				data:{
// 					urlString: url
// 				},
// 				success : function(result){
// 					$("#urlContent").html(result);
// 				},
// 				complete: function (XMLHttpRequest, textStatus) {

// 				},
// 	            error: function (e) {
// 	                //异常处理
// 	            }
				
// 			});
	  
	</script>
</body>

</html>