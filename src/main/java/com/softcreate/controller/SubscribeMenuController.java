package com.softcreate.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.softcreate.model.SubscribeMenu;
import com.softcreate.service.ISubscribeMenuService;
import com.softcreate.service.IUserService;
import com.softcreate.tools.MyDate;

@Controller
@RequestMapping("/subscribe")
public class SubscribeMenuController {

	/*
	 * @Autowired注解告诉spring，这个字段是需要自动注入的
	 */
	@Resource private ISubscribeMenuService subscribeMenuService;
	@Resource private IUserService userService;
	
	@RequestMapping("getAll")
	@ResponseBody            //直接返回json格式的数据
	public List<SubscribeMenu> getAll() {		
		List<SubscribeMenu> lsitList = subscribeMenuService.getAll();
		return lsitList;
	}
	
	@RequestMapping("getPage")
	@ResponseBody            //直接返回json格式的数据
	public List<SubscribeMenu> getPage(int page, int pageSize) {		
		List<SubscribeMenu> lsitList = subscribeMenuService.getPage(page, pageSize);
		return lsitList;
	}
	
	@RequestMapping("get")
	@ResponseBody            //直接返回json格式的数据
	public SubscribeMenu get(int id) {
		return subscribeMenuService.getMenu(id);
	}
	
	
	@RequestMapping(value="add", produces = "text/html; charset=UTF-8")
	@ResponseBody            //直接返回String的数据
	public String add(@RequestParam("picPath") MultipartFile uploadFile, HttpServletRequest request) {
		
		String resultString;
		
		if (!userService.isLogin(request.getSession())) {
			resultString = "<script>alert('请先登录系统！'); window.location.href='" + request.getContextPath() + "/login.jsp' </script>";
			return resultString;
		}
		
		//处理图片上传
		String realPath = "admin/img/app_imgs";
		String basePath = request.getSession().getServletContext().getRealPath("/" + realPath);
		
		//
		String extendName = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf("."), uploadFile.getOriginalFilename().length());
		String currentTime = MyDate.getCurrentTime();
		String newFileName = currentTime + extendName;
		
		File newFile = new File(basePath + "/" + newFileName);  
		
		try {
			
			uploadFile.transferTo(newFile);
			
			//获取除文件以外的上传参数
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String linkurl = request.getParameter("url");
//			String contentUrl = request.getParameter("contentUrl");
			String top = request.getParameter("top");
			String used = request.getParameter("used");
			String order = request.getParameter("showOrder");
			
			//将所有数据保存到数据库
			SubscribeMenu item = new SubscribeMenu();
			
			item.setTitle(title);
			item.setDescription(description);
			item.setUrl(linkurl);
//			item.setContentUrl(contentUrl);
			item.setPicPath(realPath + "/" + newFileName);
			item.setTop("1".equals(top)?true:false);
			item.setUsed("1".equals(used)?true:false);
			item.setShowOrder(Integer.parseInt(order));
			
			if (subscribeMenuService.addMenu(item)) {
				
				resultString = "<script>window.location.href='" + request.getContextPath() + "/admin/views/subscribeMenu.jsp' </script>";
				
			}else {
				resultString = "<script>alert('增加关注菜单失败！'); history.go(-1); </script>";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			resultString = "<script>alert('增加关注菜单失败！'); history.go(-1); </script>";
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
		
		SubscribeMenu subscribeMenu = subscribeMenuService.getMenu(id);
		if (subscribeMenuService.deleteMenu(subscribeMenu)) {
			
			resultString = "<script>window.location.href='" + request.getContextPath() + "/admin/views/subscribeMenu.jsp' </script>";
			
		}else {
			
			resultString = "<script>alert('删除关注菜单失败！'); window.location.href='" + request.getContextPath() + "/admin/views/subscribeMenu.jsp' </script>";
		}
		
		return resultString;
		
	}
	
	@RequestMapping(value="update", produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String update(@RequestParam("picPath") MultipartFile uploadFile, HttpServletRequest request) {
		
		String resultString;
		
		if (!userService.isLogin(request.getSession())) {
			resultString = "<script>alert('请先登录系统！'); window.location.href='" + request.getContextPath() + "/login.jsp' </script>";
			return resultString;
		}
		
		//处理图片上传
		String realPath = "admin/img/app_imgs";
		String basePath = request.getSession().getServletContext().getRealPath("/" + realPath);
		
		String newFileName = "";
		
		//
		if (!"".equals(uploadFile.getOriginalFilename())) {
			
			String extendName = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf("."), uploadFile.getOriginalFilename().length());
			String currentTime = MyDate.getCurrentTime();
			newFileName = currentTime + extendName;
			
			File newFile = new File(basePath + "/" + newFileName);
			
			try {
				uploadFile.transferTo(newFile);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				resultString = "<script>alert('修改关注菜单失败！'); history.go(-1); </script>";
			} 
			
		}
		
		//获取除文件以外的上传参数
		String id = request.getParameter("menuid");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String linkurl = request.getParameter("url");
//		String contentUrl = request.getParameter("contentUrl");
		String top = request.getParameter("top");
		String used = request.getParameter("used");
		String order = request.getParameter("showOrder");
		
		//将所有数据保存到数据库
		SubscribeMenu item = new SubscribeMenu();
		
		item.setId(Integer.parseInt(id));
		item.setTitle(title);
		item.setDescription(description);
		item.setUrl(linkurl);
//		item.setContentUrl(contentUrl);
		item.setTop("1".equals(top)?true:false);
		item.setUsed("1".equals(used)?true:false);
		item.setShowOrder(Integer.parseInt(order));
		if ("".equals(newFileName)) { 
			item.setPicPath(request.getParameter("oldPicPath"));
		}else{
			item.setPicPath(realPath + "/" + newFileName);
		}
		
		if (subscribeMenuService.updateMenu(item)) {
			
			resultString = "<script>window.location.href='" + request.getContextPath() + "/admin/views/subscribeMenu.jsp' </script>";
			
		}else {
			resultString = "<script>alert('修改关注菜单失败！'); history.go(-1); </script>";
		}
			
		return resultString;
				
	}
}
