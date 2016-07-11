package com.softcreate.service.ServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class MyHandler extends TextWebSocketHandler {

	private static Map<String, WebSocketSession> sessionList = new HashMap<String, WebSocketSession>();

	/**
	 * 连接成功时候，会触发UI上onopen方法
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
	        throws Exception {
	    // TODO Auto-generated method stub
	    super.afterConnectionEstablished(session);
	    sessionList.put(session.getAttributes().get("username").toString(), session);
	}

	/**
	 * 在UI上用js调用websocket.send()时候，会调用该方法
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	    // TODO Auto-generated method stub
	    super.handleTextMessage(session, message);
	    System.out.println("begin send msg");
	    WebSocketSession usersession = sessionList.get(session.getAttributes().get("username").toString());
	    if(usersession!=null && usersession.isOpen()){
	        usersession.sendMessage(message);
	    }
	}

	@Override
	public void handleTransportError(WebSocketSession session,
	        Throwable exception) throws Exception {
	    // TODO Auto-generated method stub
	    super.handleTransportError(session, exception);
	    if (session.isOpen()){
	        sessionList.remove(session.getAttributes().get("username").toString());
	    }
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session,
	        CloseStatus status) throws Exception {
	    super.afterConnectionClosed(session, status);
//	    if (session.isOpen()){
	        sessionList.remove(session.getAttributes().get("username").toString());
//	    }
	}

	/**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
    	
//        for (WebSocketSession user : users) {
//            if (user.getAttributes().get(Constants.WEBSOCKET_USERNAME).equals(userName)) {
//                try {
//                    if (user.isOpen()) {
//                        user.sendMessage(message);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                break;
//            }
//        }
    }
    
    /**
     * 给所有在线用户发送消息
     *
     * @param message
     * @throws IOException 
     */
    public void sendMessageToUsers(TextMessage message) throws IOException {
    	for (Entry<String, WebSocketSession> item : sessionList.entrySet()) {
              if (item.getValue().isOpen()) {
            	  item.getValue().sendMessage(message);
              }
		}
//        for (WebSocketSession user : users) {
//            try {
//                if (user.isOpen()) {
//                    user.sendMessage(message);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
