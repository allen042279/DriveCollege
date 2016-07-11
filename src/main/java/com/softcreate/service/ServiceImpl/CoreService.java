package com.softcreate.service.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.softcreate.dao.ISubscribeMenuDao;
import com.softcreate.model.SubscribeMenu;
import com.softcreate.pojo.MessageUtil;
import com.softcreate.service.ICoreService;

import message.response.Article;
import message.response.NewsMessage;
import message.response.TextMessage;

/**
 * 核心服务类
 * 
 */
@Service
public class CoreService implements ICoreService {
	
	private static Logger logger = LoggerFactory.getLogger(CoreService.class);
	
	@Resource private ISubscribeMenuDao subscribeMenuDao;
	
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public String processRequest(HttpServletRequest request) {
		
		String respMessage = null;
		
		try {
			// 默认返回的文本消息内容
			String respContent = "";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			//默认回复文本消息 -------------------------
				TextMessage textMessage = new TextMessage();
				textMessage.setToUserName(fromUserName);
				textMessage.setFromUserName(toUserName);
				textMessage.setCreateTime(new Date().getTime());
				textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
				textMessage.setFuncFlag(0);
				
				respContent = "一所帮助成就一生成功的学校";
				
				textMessage.setContent(respContent);
	            respMessage = MessageUtil.textMessageToXml(textMessage);
	            
				// 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义
	//			textMessage.setContent("欢迎访问<a href=\"http://blog.csdn.net/lyq8479\">柳峰的博客</a>!");
//				textMessage.setContent(respContent);
	            // 将文本消息对象转换成xml字符串  
//	            respMessage = MessageUtil.textMessageToXml(textMessage);
            //默认回复文本消息 -------------------------
            
			// 文本消息(图文消息)
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
				
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				
				logger.info("事件推送");
				
				// 事件类型
				String eventType = requestMap.get("Event");
				
				String path = request.getContextPath();
				String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
				
				// 订阅（关注）
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					
					logger.info("事件推送-->订阅");
					
	                NewsMessage newsMessage = createSubscribeMenu(fromUserName, toUserName, basePath);  
                    
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);  
                    
                    logger.info("data:" + respMessage);
				}
				// 取消订阅（取消关注）
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					
					logger.info("事件推送-->自定义菜单点击事件");
					
					// TODO 自定义菜单权没有开放，暂不处理该类消息
					String eventKey = requestMap.get("EventKey");
					
					//一键导航(没有使用这项目)
//					if("onekeytonavigation20160130".equals(eventKey)){
//						
//					}
					//一键呼叫
					if("onekeytocall20160130".equals(eventKey)){
						
						String tel = String.valueOf(Character.toChars(0x1F4DE));
						
//						respContent = "请点击以下电话号码：\n";
//						respContent += "地址：佛山市南海区佛山市现代商贸技工学校\n";
//						respContent += tel + ": 0757-86766801";
						
						respContent = "点击学习咨询热线：\n" + tel + ":0757-86766801";

						textMessage.setContent(respContent);
						respMessage = MessageUtil.textMessageToXml(textMessage);
			            
//						NewsMessage newsMessage = new NewsMessage();
//						newsMessage.setToUserName(fromUserName);  
//						newsMessage.setFromUserName(toUserName);  
//						newsMessage.setCreateTime(new Date().getTime());  
//						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
//						newsMessage.setFuncFlag(0);
//						
//						List<Article> articleList = new ArrayList<Article>();
//						
//						Article article = new Article();  
//	                    article.setTitle("联系我们");  
//	                    article.setDescription("地址：佛山市南海区佛山市现代商贸技工学校\n电话：075786766788");  
//	                    article.setPicUrl(basePath + "img/contact.png");
//	                    article.setUrl("");  
//	                    
//	                    articleList.add(article);  
//	                    newsMessage.setArticleCount(articleList.size());  
//	                    newsMessage.setArticles(articleList);
//	                    
//	                    respMessage = MessageUtil.newsMessageToXml(newsMessage);
	                    
					}
					//重复弹出首页
					else if("onekeytocallwelcompage".equals(eventKey)){
						
		                NewsMessage newsMessage = createSubscribeMenu(fromUserName,	toUserName, basePath);  
	                    
	                    respMessage = MessageUtil.newsMessageToXml(newsMessage);  
					}
				}
				
			}else{
				
//				textMessage.setContent(respContent);
//				respMessage = MessageUtil.textMessageToXml(textMessage);
			}

//			textMessage.setContent(respContent);
//			respMessage = MessageUtil.textMessageToXml(textMessage);
			
		} catch (Exception e) {
			logger.info("exception:" + e.getMessage());
		}
		
		return respMessage;
	}

	private NewsMessage createSubscribeMenu(String fromUserName, String toUserName, String basePath) {
		// 创建图文消息  
		NewsMessage newsMessage = new NewsMessage();  
		newsMessage.setToUserName(fromUserName);  
		newsMessage.setFromUserName(toUserName);  
		newsMessage.setCreateTime(new Date().getTime());  
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
		newsMessage.setFuncFlag(0);  
  
		//获取所有的关注条目（分顶图条目和一般关注条目）
		List<SubscribeMenu> topList = subscribeMenuDao.getTop();
		
		List<SubscribeMenu> sonList = subscribeMenuDao.getSup();
		
		List<Article> articleList = new ArrayList<Article>();
		
		//组织第一个顶图关注条目
		for (SubscribeMenu subscribeMenu : topList) {
			
			Article article1 = new Article();  
		    article1.setTitle(subscribeMenu.getTitle());  
		    article1.setDescription(subscribeMenu.getDescription());  
		    article1.setPicUrl(basePath + subscribeMenu.getPicPath());  
		    article1.setUrl(subscribeMenu.getUrl());  
		    
		    articleList.add(article1);
		    
		    break;
		}
		
		//组织一般关注条目
		for (SubscribeMenu item : sonList) {
			
			Article article = new Article();
			
		    article.setTitle(item.getTitle() + "\n" + item.getDescription());  
		    article.setDescription(item.getDescription());  
		    article.setPicUrl(basePath + item.getPicPath()); 
		    //增加一个openid参数到链接上，方便后面标记用户
		    article.setUrl(item.getUrl());// + "?openId=" + fromUserName);
		    
		    articleList.add(article);
		}

		newsMessage.setArticleCount(articleList.size());  
		newsMessage.setArticles(articleList);

		return newsMessage;
	}
}
