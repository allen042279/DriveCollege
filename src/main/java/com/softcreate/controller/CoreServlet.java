package com.softcreate.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softcreate.dao.ISysParamDao;
import com.softcreate.service.ICoreService;
import com.softcreate.tools.SHA1;



/**
 * 微信核心请求处理类
 * 
 */
@Controller
@RequestMapping(value="/wechat")
public class CoreServlet  {
	
	@Resource private ICoreService coreService;
	@Resource private ISysParamDao sysParamDao;
	
	/**
	 * 确认请求来自微信服务器
	 */
	@RequestMapping(method=RequestMethod.GET, produces = "text/html; charset=UTF-8")
	@ResponseBody            //直接返回String的数据
	public String doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");


		
		//old section-------------------------
//		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
//		if (SignUtil.checkSignature(TOKEN, signature, timestamp, nonce)) {
//			out.print(echostr);
//		}
		//old section----------end---------------
		
		//new section --------------------------
		String[] str = { sysParamDao.getToken() , timestamp, nonce };
		Arrays.sort(str); // 字典序排序
		String bigStr = str[0] + str[1] + str[2];
		// SHA1加密
		String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();
		
		// 确认请求来自微信
		if (digest.equals(signature)) {
			return echostr;
		}
		//new section ----------end----------------
		
		return "";
	}

	/**
	 * 处理微信服务器发来的消息
	 */
	@RequestMapping(method=RequestMethod.POST, produces = "text/html; charset=UTF-8")
	@ResponseBody            //直接返回String的数据
	public String doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 调用核心业务类接收消息、处理消息
		String respMessage = coreService.processRequest(request);
		
		// 响应消息
		return respMessage;
	}

}
