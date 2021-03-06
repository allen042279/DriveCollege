package com.softcreate.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.softcreate.service.ServiceImpl.MyHandler;
import com.softcreate.tools.MyHandshakeInterceptor;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addHandler(new MyHandler(), "/webSocketServer").addInterceptors(new MyHandshakeInterceptor()).setAllowedOrigins("*");  
		
		registry.addHandler(new MyHandler(), "/sockjs/webSocketServer").addInterceptors(new MyHandshakeInterceptor()).withSockJS(); 
		
//        registry.addHandler(new MyHandler(), "/sockjs/websocket")
//                .addInterceptors(new MyHandshakeInterceptor())
//                .withSockJS(); 
	}

}