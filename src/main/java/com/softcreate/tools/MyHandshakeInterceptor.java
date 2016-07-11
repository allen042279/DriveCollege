package com.softcreate.tools;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.softcreate.model.User;

@Component
public class MyHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

	private Logger logger = Logger.getLogger(getClass());

	public MyHandshakeInterceptor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyHandshakeInterceptor(Collection<String> attributeNames) {
		super(attributeNames);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		logger.debug("GOMA ===> Before Handshake");
//		return super.beforeHandshake(request, response, wsHandler, attributes);
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession(false);
			
			if (session!=null) {
				System.out.println("ok");
				User user = (User) session.getAttribute("user");
				if (user!=null) {
					attributes.put("username", user.getUsername());	
				}
			}
		}
		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
		logger.debug("GOMA ===> After Handshake");
		super.afterHandshake(request, response, wsHandler, ex);
	}
}