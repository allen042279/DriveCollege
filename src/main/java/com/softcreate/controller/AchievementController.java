package com.softcreate.controller;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softcreate.model.Achievement;
import com.softcreate.model.AchievementType;
import com.softcreate.service.IAchievementService;
import com.softcreate.service.IUserService;

@Controller
@RequestMapping("/achievement")
public class AchievementController {

	/*
	 * @Autowired注解告诉spring，这个字段是需要自动注入的
	 */
	@Resource private IAchievementService achievementService;
	@Resource private IUserService userService;
	
	//手机端使用
	@RequestMapping("getAll/{id}")
	@ResponseBody            //直接返回json格式的数据
	public List<Achievement> getAll(@PathVariable("id") Integer id) {
		
		List<Achievement> lsitList = achievementService.getAll(id);
		return lsitList;
	}
	
	@RequestMapping("getPage")
	@ResponseBody            //直接返回json格式的数据
	public List<Achievement> getPage(int page, int pageSize) {
		
		List<Achievement> lsitList = achievementService.getPage(page, pageSize);
		return lsitList;
	}
	
	@RequestMapping("get")
	@ResponseBody            //直接返回json格式的数据
	public Achievement get(int id) {
		return achievementService.getAchievement(id);
	}
	
	
	@RequestMapping(value="add", produces = "text/html; charset=UTF-8")
	@ResponseBody            //直接返回String的数据
	public String add(HttpServletRequest request) {
		
		String resultString;
		if (!userService.isLogin(request.getSession())) {
			resultString = "<script>alert('请先登录系统！'); window.location.href='" + request.getContextPath() + "/login.jsp' </script>";
			return resultString;
		}
		
		Achievement achievement = new Achievement();
		
		achievement.setTitle(request.getParameter("name"));
		achievement.setUrl(request.getParameter("url"));
		achievement.setShowOrder(Integer.parseInt(request.getParameter("showOrder")));
		achievement.setUpdateDate(new Date());
		
		int typeId = Integer.parseInt(request.getParameter("achievement_type"));
		AchievementType type = new AchievementType(typeId, "");
		achievement.setAchievementType(type);
		
		if (achievementService.addAchievement(achievement)) {
			
			resultString = "<script>window.location.href='" + request.getContextPath() + "/admin/views/achievement.jsp' </script>";
			
		}else {
			resultString = "<script>alert('增加精彩成就失败！'); history.go(-1); </script>";
		}
			
		return resultString;
		
	}
	
	@RequestMapping(value="delete/{id}", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String delete(@PathVariable("id") Integer id, HttpServletRequest request) {
		
		String resultString;
		if (!userService.isLogin(request.getSession())) {
			resultString = "<script>alert('请先登录系统！'); window.location.href='" + request.getContextPath() + "/login.jsp' </script>";
			return resultString;
		}
		
		Achievement achievement = achievementService.getAchievement(id);
		if (achievementService.deleteAchievement(achievement)) {
			
			resultString = "<script>window.location.href='" + request.getContextPath() + "/admin/views/achievement.jsp' </script>";
			
		}else {
			
			resultString = "<script>alert('删除精彩成就失败！'); window.location.href='" + request.getContextPath() + "/admin/views/achievement.jsp' </script>";
		}
		
		return resultString;
		
	}
	
	@RequestMapping(value="update", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String update(HttpServletRequest request) {
		
		String resultString;
		if (!userService.isLogin(request.getSession())) {
			resultString = "<script>alert('请先登录系统！'); window.location.href='" + request.getContextPath() + "/login.jsp' </script>";
			return resultString;
		}
		
		Achievement achievement = new Achievement();
		
		achievement.setId(Integer.parseInt(request.getParameter("achievementId")));
		achievement.setTitle(request.getParameter("name"));
		achievement.setUrl(request.getParameter("url"));
		achievement.setShowOrder(Integer.parseInt(request.getParameter("showOrder")));
		achievement.setUpdateDate(new Date());
		
		int typeId = Integer.parseInt(request.getParameter("achievement_type"));
		AchievementType type = new AchievementType(typeId, "");
		achievement.setAchievementType(type);
		
		if (achievementService.updateAchievement(achievement)) {
			
			resultString = "<script>window.location.href='" + request.getContextPath() + "/admin/views/achievement.jsp' </script>";
			
		}else {
			resultString = "<script>alert('修改精彩成就失败！'); history.go(-1); </script>";
		}
			
		return resultString;
				
	}
	
	@RequestMapping("getAchievementType")
	@ResponseBody            //直接返回json格式的数据
	public List<AchievementType> getAchievementType() {
		return achievementService.getAllAchievementType();
	}
	
}
