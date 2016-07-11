package com.softcreate.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softcreate.model.SubscribeMenu;
import com.softcreate.service.ICoreService;
import com.softcreate.service.ISubscribeMenuService;


@Controller
@RequestMapping("/common")
public class CommonPageController {

	/*
	 * @Autowired注解告诉spring，这个字段是需要自动注入的
	 */
	@Resource private ICoreService coreService;
	@Resource private ISubscribeMenuService subscribeMenuService;
	
	@RequestMapping(value="commonPage/{id}")
	public void getCommonPage(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response) {
		
		String resultString = "";
		
		String path = request.getContextPath();
		String headerString = "";
		String urlString = "";
		
		List<SubscribeMenu> subscribeMenus = subscribeMenuService.getAll();
		for (SubscribeMenu item : subscribeMenus) {
			if (item.getShowOrder()==id) {
				headerString = item.getTitle();
//				urlString =  item.getContentUrl();
			}
		}
		
		resultString += "<!doctype html><html><head>";
		resultString += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";
		resultString += "<meta content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;\" name=\"viewport\" />";
		
		resultString += "<link rel=\"stylesheet\" type=\"text/css\" href=\"http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css\">";
		resultString += "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + path + "/css/mobile_specialist.css\">";
		
		resultString += "<script src=\"http://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js\"></script>";
		resultString += "<script src=\"http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js\"></script>";
		
		resultString += "</head><body>";
		resultString += "<div data-role=\"page\"> <div data-role=\"header\" data-theme=\"b\"> <h1>" + headerString + "</h1> </div>";
		
		resultString += "<div data-role=\"main\" class=\"ui-content\">";
		resultString += "<iframe id=\"mainFrame\" name=\"mainFrame\" width=\"100%\" seamless src=\"" + urlString + "\"></iframe>";
		resultString += "</div>";
		
		resultString += "<div data-role=\"footer\" data-theme=\"b\">";
		resultString += "<div data-role=\"navbar\">";
		resultString += "<ul>";
		resultString += "<li><a href=\"" + path + "/views/addPreStudent.jsp\" data-icon=\"user\">导师指引</a></li>";
		resultString += "<li><a href=\"" + path + "/views/showAchievements.jsp\" data-icon=\"phone\">一键咨询</a></li>";
		resultString += "</ul>";
		resultString += "</div>";
		resultString += "</div>";
		resultString += "</div>";
		resultString += "</body></html>";
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter printWriter;
		
		try {
			
			printWriter = response.getWriter();
			printWriter.print(resultString);
			
			printWriter.flush();
			printWriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
