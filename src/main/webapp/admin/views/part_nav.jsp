<%@page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"
         import="java.sql.*" errorPage="" %>
         
<%
// 	String nav_path = request.getContextPath();
// // 	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

// 	 Object user = null;
// 	if(request.getSession().getAttribute("user")!=null){
// 		user = request.getSession().getAttribute("user");	
// 	}
	
%>

<!doctype html>

<html>

	<head>	 
	
		<script src="${pageContext.request.contextPath}/sockjs-0.3.4.js"></script>
	    <script src="${pageContext.request.contextPath}/stomp.js"></script>
	    
	    <script type="text/javascript">
	    
	    	var loginUser = '${sessionScope.user.username}';
	    	
	  		if(undefined!=loginUser){
		    	
	  			var websocket = null;
	  			var ws = "ws://112.74.101.218/fsmmss/webSocketServer";
	  			
	  			if ('WebSocket' in window) {
	  				websocket = new WebSocket(ws);
	  			} 
	  			else if ('MozWebSocket' in window) {
	  				websocket = new MozWebSocket(ws);
	  			} 
	  			else {
	  				websocket = new SockJS("http://112.74.101.218/fsmmss/sockjs/webSocketServer");
	  			}
	  			
	  			websocket.onopen = function onOpen(openEvt) {	};
	  			websocket.onerror = function onError() {	};
	  			websocket.onclose = function onClose() {	};
	  			      	
	  			websocket.onmessage = function onMessage(evt) {
												  				alert(evt.data);
												  			}
		    }
  
	    </script>
		
	</head>
    
<body>

  <div class="nav_container"> 
       
	   <div class="nav">
	      <ul>
	         <li class="scribe"><a href="${pageContext.request.contextPath}/admin/views/subscribeMenu.jsp">关注条目</a></li>
	         <li class="menu"><a href="${pageContext.request.contextPath}/admin/views/menu.jsp" >订阅菜单</a></li>
 	         <li class="achievement"><a href="${pageContext.request.contextPath}/admin/views/achievement.jsp">精彩成就</a></li>  
             <li class="prestudent"><a  href="${pageContext.request.contextPath}/admin/views/prestudent.jsp">导师指引</a></li> 
 	         <li class="logout"><a href="${pageContext.request.contextPath}/user/logout">退出</a></li> 
	      </ul>
	    </div>
    	
    	<div class="clearfloat"></div>
    
	    <div class="out_subnav">
	    
	        <div class="sub_nav scribe">
	            <ul>
	              <li class="get_scribe"><a href="${pageContext.request.contextPath}/admin/views/subscribeMenu.jsp">查看关注条目</a></li>
	              <li class="add_scribe"><a href="${pageContext.request.contextPath}/admin/views/addSubscribeMenu.jsp">增加关注条目</a></li>  
	            </ul>
	        </div>
	        <div class="sub_nav menu">
	            <ul>
	              <li class="get_menu"><a href="${pageContext.request.contextPath}/admin/views/menu.jsp">查看订阅菜单</a></li>
	              <li class="add_menu"><a href="${pageContext.request.contextPath}/admin/views/addMenu.jsp">增加订阅菜单</a></li>
	              <li class="create_menu"><a href="${pageContext.request.contextPath}/menu/create">生成订阅菜单</a></li> 
	            </ul>
	        </div>  
	        <div class="sub_nav achievement">
	            <ul>
	              <li class="get_achievement"><a href="${pageContext.request.contextPath}/admin/views/achievement.jsp">查看成就</a></li>
	              <li class="add_achievement"><a href="${pageContext.request.contextPath}/admin/views/addAchievement.jsp">增加成就</a></li> 
	            </ul>
	        </div>
	        <div class="sub_nav prestudent">
	            <ul>
	              <li class="get_prestudent"><a href="${pageContext.request.contextPath}/admin/views/prestudent.jsp">查看指引申请</a></li>
	              <li class="export_prestudent"><a href="${pageContext.request.contextPath}/admin/views/exportPrestudent.jsp">导出指引申请</a></li>
	            </ul>
	        </div>    
	    </div>
       
  </div> 
  
</body>

</html>