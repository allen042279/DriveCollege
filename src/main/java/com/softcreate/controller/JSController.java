package com.softcreate.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softcreate.model.JsApiTicket;
import com.softcreate.service.IJSService;


@Controller
@RequestMapping("/js")
public class JSController {

	/*
	 * @Autowired注解告诉spring，这个字段是需要自动注入的
	 */
	@Resource private IJSService jSService;
	
	@RequestMapping("/config")
	@ResponseBody      //直接返回json格式的数据
	public JsApiTicket getConfig(HttpServletRequest request) {
		return jSService.getConfig(request.getParameter("url"));
		
	}
}
